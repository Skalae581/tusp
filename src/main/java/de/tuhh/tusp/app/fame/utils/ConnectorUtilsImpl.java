package de.tuhh.tusp.app.fame.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.Connector;
import de.tuhh.tusp.app.fame.connector.pojo.ConnectorPojo;
import de.tuhh.tusp.app.fame.dao.WorkflowDao;
import de.tuhh.tusp.app.fame.dao.WorkflowInstanceDao;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.app.fame.service.InputService;
import de.tuhh.tusp.app.fame.service.LogService;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.VorgangService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportFilter;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportResult;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;

@Component
public class ConnectorUtilsImpl implements ConnectorUtils {

	private static Logger logger = LogManager.getLogger(ConnectorUtils.class);

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private WorkflowDao workflowDao;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private StatusService statusService;

	@Autowired
	private InputService inputService;

	@Autowired
	private VorgangService vorgangService;

	@Autowired
	private AuthorityUtils authorityUtils;

	@Autowired
	private ConfigService config;

	@Autowired
	private LogService logService;

	@Autowired
	private WorkflowInstanceService workflowInstanceService;

	@Override
	public List<Case> findCases(Connector connector, Long workflowInstanceId, ImportFilter filter)
			throws ImportException {

		return connector.findCases(filter);
	}

	@Override
	public Vorgang importCase(Case c, ImportResult result, Long workflowInstanceId, Long startStatus, Long endStatus)
			throws ImportException, RegexException, PflichtfeldException {

		if (result == null) {
			result = new ImportResult();
		}

		// Wenn bereits existiert nix tun
		Vorgang vorgang = vorgangService.fetchByWorkflowIntsanceAndConnectorKey(workflowInstanceId, c.getId());

		// Vorgang anlegen wenn noch nicht vorhanden
		if (vorgang == null) {
			c.lazyLoad();
			result.setAngelegt(result.getAngelegt() + 1);
			vorgang = new Vorgang();
			vorgang.setId(0L);
			vorgang.setConnectorKey(c.getId());
			vorgang.setWorkflowInstanceId(workflowInstanceId);
			vorgang.setStatusId(startStatus);
			vorgang.setId(vorgangService.insert(vorgang));
			logger.debug("Neuer Vorgang für Case: " + c.getId());
		} else {
			if (endStatus != null && endStatus.equals(vorgang.getStatusId())) {
				logger.debug("Kein Update weil Vorgang abgeschlossen ist. Case: " + c.getId());
				vorgang.setWarn("Vorgang ist abgeschlossen und wird nicht mehr aktualisiert.");
				vorgangService.update(vorgang);
				return vorgang;
			} else {
				logger.debug("Update Vorgang von Case: " + c.getId());
				vorgang.setWarn(c.getImportErrors());
				vorgangService.update(vorgang);
				c.lazyLoad();
				result.setAktualisiert(result.getAktualisiert() + 1);
			}
		}

		logger.debug("Importiere Vorgang " + vorgang);

		// Values übernehmen
		List<ExtendedInput> inputs = inputService.findByWorkflowInstanceVorgangAndRoles(workflowInstanceId,
				vorgang.getId(), authorityUtils.getAdminRoles());
		for (ExtendedInput input : inputs) {

			// Nicht Abbrechen, wenn der Benutzer eigentlich keine Rechte für
			// ein Feld hat. Beim Import darf er alles.
			if (input.getConnectorMapping() != null && !input.getConnectorMapping().isEmpty()) {
				input.setRecht(InputRechteEnum.WRITE);
			}

			// Kein Mapping
			if ("".equals(input.getConnectorMapping())) {
				continue;
			}

			ConnectorPojo pojo = c.getPropertyByName(input.getConnectorMapping());
			if (pojo == null) {
				for (String alternativMapping : config
						.get("de.tuhh.tusp.app.fame.alternative.mapping." + input.getConnectorMapping(), "", false)
						.split(";")) {
					if (alternativMapping == "") {
						continue;
					}
					pojo = c.getPropertyByName(alternativMapping);
					if (pojo != null) {
						logger.info("Verwende für das Feld " + input.getName() + " '" + alternativMapping + "' statt '"
								+ input.getConnectorMapping());
						break;
					}
				}
			}

			if (pojo == null) {
				logger.info("Das ConnectorMapping des Feldes '" + input.getName() + "' (" + input.getConnectorMapping()
						+ ") passt nicht zu " + c.getPropertyNames());
				continue;

			}

			pojo.save(input);
		}

		return vorgang;
	}

	@Override
	public ImportResult importiereVorgang(Connector connector, Long workflowInstanceId, Case c)
			throws ImportException, PflichtfeldException, RegexException {

		ImportResult result = new ImportResult();

		WorkflowInstance instance = workflowInstanceService.fetchById(workflowInstanceId);

		// Ersten Status des Workflow finden
		Status startStatus = statusService.fetchFirstStatusByWorkflow(instance.getWorkflowId());
		Status endStatus = statusService.fetchLastStatusByWorkflow(instance.getWorkflowId());

		// Vorgänge importieren
		if (c.getImportErrors().isEmpty()) {
			importCase(c, result, workflowInstanceId, startStatus.getId(), endStatus.getId());
		} else {
			resetCase(c, result, workflowInstanceId, startStatus.getId());
		}

		logger.debug("FaME-Vorgangsimport abgeschlossen");

		return result;
	}

	@Override
	public ImportResult importiereVorgang(Connector connector, Long workflowInstanceId, String connectorId)
			throws ImportException, PflichtfeldException, RegexException {

		Case c = connector.findCase(connectorId);
		return importiereVorgang(connector, workflowInstanceId, c);
	}

	@Override
	public Connector initializeConnector(Long workflowInstanceId) throws RuntimeException {

		WorkflowInstance workflowInstance = workflowInstanceDao.fetchById(workflowInstanceId);

		Connector connector = initializeConnectorByWorkflow(workflowInstance.getWorkflowId());

		if (connector == null) {
			return null;
		}

		connector.setParameter(workflowInstance.getParameter());
		connector.setWorkflowInstanceId(workflowInstanceId);
		return connector;
	}

	@Override
	public Connector initializeConnectorByWorkflow(Long workflowId) {

		Workflow workflow = workflowDao.fetchById(workflowId);

		String className = workflow.getConnector();

		if (className == null || className.isEmpty()) {
			return null;
		}

		// Versuche Spring-Bean z ubekommen
		// Das darf nicht mehr gemacht werden, da es Racekonditions provoziert

		// Versuche eigene Instanz zu erzeugen
		try {
			// Numm den ersten Constructor (und hoffentlich einzigen...)
			Class<?> clazz = Class.forName(className);
			Constructor<?> construct = clazz.getConstructors()[0];

			Class<?>[] paramTypes = construct.getParameterTypes();
			Object[] params = new Object[paramTypes.length];
			for (int i = 0; i < paramTypes.length; i++) {
				if (paramTypes[i] == ApplicationContext.class) {
					params[i] = appContext;
				} else {
					params[i] = appContext.getBean(paramTypes[i]);
				}
			}

			Connector connector = (Connector) construct.newInstance(params);
			return connector;

		} catch (ClassNotFoundException e) {
			String msg = "Connector-Klasse '" + className + "' nicht gefunden";
			throw new RuntimeException(msg, e);
		} catch (IndexOutOfBoundsException e) {
			String msg = "Connector-Klasse '" + className + "' fehlt eine nötige Methode";
			throw new RuntimeException(msg, e);
		} catch (SecurityException e) {
			String msg = "In Connector-Klasse '" + className + "' eine nötige Methode ist nicht zugreifbar";
			throw new RuntimeException(msg, e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			String msg = "Connector-Klasse '" + className + "' lässt Constructor nicht ausführen";
			throw new RuntimeException(msg, e);
		}
	}

	/**
	 * Setzt einen Vorgang in der DB zurück, wenn nötig
	 *
	 * @param c
	 * @param result
	 * @param workflowInstanceId
	 * @param statusId
	 */
	private void resetCase(Case c, ImportResult result, Long workflowInstanceId, Long statusId) {

		Vorgang vorgang = vorgangService.fetchByWorkflowIntsanceAndConnectorKey(workflowInstanceId, c.getId());
		if (vorgang == null) {
			return;
		}

		if (statusId != null && statusId.equals(vorgang.getStatusId())) {
			vorgang.setWarn(c.getImportErrors());
			vorgangService.update(vorgang);
			return; // Nichts zu tun
		}

		String resetKey = "de.tuhh.tusp.app.fame.utils.ConnectorUtilsImpl.reset_from." + vorgang.getStatusId();
		if (!"true".equals(config.get(resetKey, "false", true))) {
			return;
		}

		vorgang.setStatusId(statusId);
		vorgang.setWarn(c.getImportErrors());
		vorgangService.update(vorgang);
		result.setResetted(result.getResetted() + 1);
		logService.logChange("Status wurde zurückgesetzt da kein gültiger Antrag in HISinOne vorlag", vorgang.getId());
		logger.debug("Reset Case: " + c.getId());
	}

	@Override
	public ImportResult sucheUndImportiereVorgaenge(String searchword) {

		searchword = searchword.trim();

		ImportResult result = new ImportResult();

		for (WorkflowInstance wi : workflowInstanceDao.findAll()) {

			// Connector initialisieren
			Connector connector = initializeConnector(wi.getId());
			if (connector == null) {
				logger.debug("Kein Connector hinterlegt für " + wi.getName());
				continue;
			}

			// Fälle suchen
			List<Case> cases;
			try {
				cases = connector.search(searchword);
			} catch (Exception e) {
				logger.error("Suche nach " + searchword + " fehlgeschlagen!", e);
				Workflow w = workflowDao.fetchById(wi.getWorkflowId());
				result.getErrors().add("Konnte Suche für " + w.getName() + " - " + wi.getName() + " nicht abschließen: "
						+ e.getMessage());
				continue;
			}

			// Fälle importieren
			for (Case c : cases) {
				Status startStatus = statusService.fetchFirstStatusByWorkflow(wi.getWorkflowId());
				Status endStatus = statusService.fetchLastStatusByWorkflow(wi.getWorkflowId());
				try {
					c = connector.findCase(c.getId()); // Neu laden, weil noch
														// keine Properties
					Vorgang vorgang = importCase(c, result, wi.getId(), startStatus.getId(), endStatus.getId());
					result.getVorgaenge().add(vorgang);
				} catch (Exception e) {

					logger.error("Import fehlgeschlagen", e);
					result.getErrors().add(
							"Import fehlgeschlagen für " + c.getId() + " in " + wi.getName() + ": " + e.getMessage());
				}
			}
		}

		return result;
	}
}
