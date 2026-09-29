package de.tuhh.tusp.app.fame.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.InputTypeDao;
import de.tuhh.tusp.app.fame.dao.VorgangDao;
import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchergebnis;
import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchparameter;
import de.tuhh.tusp.app.fame.dao.WorkflowInstanceDao;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.StatusuebergangVerbotenException;
import de.tuhh.tusp.app.fame.exception.VorgangGesperrtException;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.service.InputService.OptionsType;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.CalendarUtils;

@Component
public class VorgangServiceImpl implements VorgangService {

	private static Pattern PATTERN_GERMAN_DATE = Pattern.compile("(\\d{2}).(\\d{2}).(\\d{4})");
	private static Pattern PATTERN_TIME = Pattern.compile("(\\d{2}):(\\d{2}):(\\d{2})");
	private static Pattern PATTERN_TIME_SHORT = Pattern.compile("(\\d{2}):(\\d{2})");
	private static Pattern PATTERN_ENGLISH_DATE = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");

	@Autowired
	private VorgangDao dao;

	@Autowired
	private ConfigService configService;

	@Autowired
	private WorkflowInstanceDao workflowInstanceDao;

	@Autowired
	private InputBerechtigungDao inputBerechtigungDao;

	@Autowired
	private InputTypeDao inputTypeDao;

	@Autowired
	private StatusService statusService;

	@Autowired
	private InputService inputService;

	@Autowired
	private AuthorityUtils authorityUtils;

	@Autowired
	private LogService logService;

	@Override
	public int delete(Long id) {

		return dao.delete(id);
	}

	@Override
	public Vorgang fetchById(Long id) {

		return dao.fetchById(id);
	}

	@Override
	public Vorgang fetchByWorkflowIntsanceAndConnectorKey(Long workflowId, String connectorId) {

		return dao.fetchByWorkflowInstanceAndConnectorKey(workflowId, connectorId);
	}

	@Override
	public List<Vorgang> findAll() {

		return dao.findAll();
	}

	@Override
	public List<Map<String, String>> findByStatusAndWorkflowInstance(Long statusId, Long instanceId) {

		return dao.findByStatusAndWorkflowInstance(statusId, instanceId);
	}

	@Override
	public String inBearbeitungBei(Vorgang vorgang) {

		// Vorgang soll nicht weiter bearbeitet werden
		if ("none".equals(vorgang.getGesperrtDurch())) {
			return "none";
		}

		// Wenn Sperre kein Dateum hat, null zurückgeben
		if (vorgang.getGesperrtDatum() == null) {
			return null;
		}

		// Wenn Vorgang Sperre veraltet, null zurückgeben
		Calendar sperrzeit = new GregorianCalendar();
		sperrzeit.setTime(vorgang.getGesperrtDatum());
		sperrzeit.add(Calendar.MINUTE, configService.get("de.tuhh.tusp.fame.vorgang.lock_time", 30, true));
		if (!CalendarUtils.isFuture(sperrzeit)) {
			Vorgang v = dao.fetchById(vorgang.getId());
			v.setGesperrtDatum(null);
			v.setGesperrtDurch(null);
			dao.update(vorgang);
			vorgang.setGesperrtDatum(null);
			vorgang.setGesperrtDurch(null);
			return null;
		}

		// Sonst Sperre verlängern wenn ich es bin
		if (authorityUtils.getCurrentUserName().equals(vorgang.getGesperrtDurch())) {

			// Das Objekt verändern
			vorgang.setGesperrtDatum(new Date());

			// In der DB Ändern
			Vorgang v = dao.fetchById(vorgang.getId());
			v.setGesperrtDatum(new Date());
			dao.update(vorgang);

			return "me";
		}

		return vorgang.getGesperrtDurch();
	}

	@Override
	public void inBearbeitungNehmen(Vorgang vorgang) throws VorgangGesperrtException {

		String owner = inBearbeitungBei(vorgang);
		if (owner != null && !owner.equals("me") && !owner.equals("none")) {
			throw new VorgangGesperrtException(vorgang);
		}

		vorgang.setGesperrtDatum(new Date());
		vorgang.setGesperrtDurch(authorityUtils.getCurrentUserName());

		dao.update(vorgang);
	}

	@Override
	public Long insert(Vorgang vorgang) {

		return dao.insert(vorgang);
	}

	@Override
	public void keineWeitereBearbeitung(Vorgang vorgang) throws VorgangGesperrtException {

		String owner = inBearbeitungBei(vorgang);
		if (owner != null && !owner.equals("me") && !owner.equals("none")) {
			throw new VorgangGesperrtException(vorgang);
		}

		// Das Objekt verändern
		vorgang.setGesperrtDatum(null);
		vorgang.setGesperrtDurch("none");

		// In der DB Ändern
		Vorgang v = dao.fetchById(vorgang.getId());
		v.setGesperrtDatum(null);
		v.setGesperrtDurch("none");
		dao.update(vorgang);
	}

	@Override
	public List<Map<String, String>> search(Long workflowId, Map<Long, String> searchFields) {

		for (Long id : searchFields.keySet()) {
			searchFields.put(id, searchFields.get(id).trim());
		}

		// Verhindern, dass Nutzer Vorgänge sehen, die sie nichts angehen.
		List<WorkflowInstance> filteredWIs = new ArrayList<>();
		List<WorkflowInstance> userWIs = workflowInstanceDao.findByRoles(authorityUtils.getRoles());
		for (WorkflowInstance wi : userWIs) {
			if (wi.getWorkflowId().equals(workflowId)) {
				filteredWIs.add(wi);
			}
		}
		return dao.search(filteredWIs, searchFields);
	}

	@Override
	public List<Suchergebnis> search(String word) {

		word = word.trim();

		List<Suchparameter> suchparameter = new ArrayList<>();
		for (Input input : inputService.findAll()) {
			if (input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.TEXT))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.TEXTAREA))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.CONNECTOR_TEXT))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.BEARB_NAME))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.FILE_NAME))) {

				suchparameter.add(new Suchparameter(input, word));
			}
			else if (input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.SELECT))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.CHECKBOX))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.RADIOBUTTON))) {

				List<OptionsType> options = inputService.parseOptions(input);
				for (OptionsType ot : options) {
					if (ot.getName().contains(word)) {
						suchparameter.add(new Suchparameter(input, ot.getId()));
					}
				}

			}
			else if (input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.DATE))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.DATETIME))
					|| input.getInputTypeId().equals(inputTypeDao.getId(InputTypeEnum.BEARB_DATE))) {

				searchDateConvert(word, input, suchparameter);
			}
		}

		List<Suchergebnis> result = new ArrayList<>();
		for (Suchergebnis erg : dao.search(suchparameter)) {
			if (inputBerechtigungDao.canRead(	erg.getValue().getInputId(), erg.getVorgang().getStatusId(),
												erg.getVorgang().getId(), authorityUtils.getRoles())) {
				result.add(erg);
			}
		}

		return result;
	}

	/**
	 * Versucht das Datum in ein brauchbares Suchwort umzuwandeln, wenn das
	 * nicht gelingt wird kein weiterer Suchparameter erstellt
	 *
	 * @param word
	 * @param input
	 * @param suchparameter
	 */
	private void searchDateConvert(String word, Input input, List<Suchparameter> suchparameter) {

		String datePart = "";

		Matcher deDate = PATTERN_GERMAN_DATE.matcher(word);
		if (deDate.find()) {
			datePart = deDate.group(3) + "-" + deDate.group(2) + "-" + deDate.group(1) + " ";
		}
		else {
			Matcher enDate = PATTERN_ENGLISH_DATE.matcher(word);
			if (enDate.find()) {
				datePart = enDate.group(1) + "-" + enDate.group(2) + "-" + enDate.group(3) + " ";
			}
		}

		Matcher time = PATTERN_TIME.matcher(word);
		if (time.find()) {
			datePart += time.group(1) + ":" + time.group(2) + ":" + time.group(3);
		}
		else {
			Matcher timeShort = PATTERN_TIME_SHORT.matcher(word);
			if (timeShort.find()) {
				datePart += timeShort.group(1) + ":" + timeShort.group(2);
			}
		}

		if (!"".equals(datePart)) {
			suchparameter.add(new Suchparameter(input, datePart.trim()));
		}
	}

	@Override
	public void statusUebergang(Vorgang vorgang, Long newStatusId) throws StatusuebergangVerbotenException {

		if (vorgang.getStatusId() == newStatusId) {
			return;
		}

		List<Status> erlaubte = statusService.findByVorgangAndRoles(vorgang, authorityUtils.getRoles());
		for (Status status : erlaubte) {
			if (status.getId().equals(newStatusId)) {

				Long from = vorgang.getStatusId();
				vorgang.setStatusId(newStatusId);
				vorgang.setGesperrtDatum(null);
				vorgang.setGesperrtDurch(null);
				dao.update(vorgang);
				logService.logStatusChange(statusService.fetchById(from), status, vorgang.getId());
				return;
			}
		}

		throw new StatusuebergangVerbotenException();
	}

	@Override
	public void update(Vorgang vorgang) {

		dao.update(vorgang);
	}

	@Override
	public void zurBearbeitungFreigeben(Vorgang vorgang) throws VorgangGesperrtException {

		String owner = inBearbeitungBei(vorgang);
		if (owner != null && !owner.equals("me") && !owner.equals("none")) {
			throw new VorgangGesperrtException(vorgang);
		}

		// Das Objekt verändern
		vorgang.setGesperrtDatum(null);
		vorgang.setGesperrtDurch(null);

		// In der DB Ändern
		Vorgang v = dao.fetchById(vorgang.getId());
		v.setGesperrtDatum(null);
		v.setGesperrtDurch(null);
		dao.update(vorgang);
	}

}
