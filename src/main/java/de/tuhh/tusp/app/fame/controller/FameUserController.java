package de.tuhh.tusp.app.fame.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import de.tuhh.tusp.app.fame.dao.InputDao;
import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchergebnis;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.exception.PflichtfeldException;
import de.tuhh.tusp.app.fame.exception.RegexException;
import de.tuhh.tusp.app.fame.exception.SecurityException;
import de.tuhh.tusp.app.fame.exception.StatusuebergangVerbotenException;
import de.tuhh.tusp.app.fame.exception.ValidationException;
import de.tuhh.tusp.app.fame.exception.VorgangGesperrtException;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFileMitRecht;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedWorkflowInstance;
import de.tuhh.tusp.app.fame.service.FileService;
import de.tuhh.tusp.app.fame.service.InputService;
import de.tuhh.tusp.app.fame.service.InputTypeService;
import de.tuhh.tusp.app.fame.service.InputValidationHelper;
import de.tuhh.tusp.app.fame.service.LogService;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.VorgangService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportResult;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.app.fame.utils.VorgangCsvCreator;
import de.tuhh.tusp.app.fame.utils.VorgangPdfCreator;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.MessageContainer;

@Controller
@RequestMapping(value = "/fame/user/")
@AccessConfig(path = "/fame/user/**", access = { Rollen.ADMIN, Rollen.APP_ADMIN, Rollen.ZULBEARB, Rollen.MITARBEITER })
public class FameUserController {

	private static Logger logger = LogManager.getLogger(FameUserController.class);

	private static final String USER_WORKFLOWS = "userWorkFlows";
	private static final String USER_WORKFLOWS_DETAILS = "userWorkFlowsDetails";
	private static final String ACTIVE_WORKFLOW_INSTANCE_ID = "activeWorkflowId";
	private static final String ACTIVE_WORKFLOW_STATES = "activeWorkflowStats";

	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private WorkflowInstanceService workflowInstanceService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private VorgangService vorgangService;
	@Autowired
	private InputService inputService;
	@Autowired
	private InputTypeService inputTypeService;
	@Autowired
	private FileService fileService;
	@Autowired
	private AuthorityUtils authorityUtils;
	@Autowired
	private InputValidationHelper inputValidationHelper;
	@Autowired
	private LogService logService;

	@Autowired
	private MessageContainer messageContainer;

	@RequestMapping(value = "/csv", method = RequestMethod.GET)
	public ModelAndView csv(HttpServletResponse response, ModelMap model, String ids) {

		List<String> roles = authorityUtils.getRoles();

		List<Vorgang> list = new ArrayList<>();
		Map<Vorgang, List<ExtendedInput>> values = new HashMap<>();
		for (String s : ids.split(",")) {
			try {
				Long id = Long.parseLong(s);
				Vorgang vorgang = vorgangService.fetchById(id);
				List<ExtendedInput> inputs = inputService
						.findByWorkflowInstanceVorgangAndRoles(vorgang.getWorkflowInstanceId(), id, roles);

				list.add(vorgang);
				values.put(vorgang, inputs);
			} catch (NumberFormatException e) {
				// NOPMD
				// egal
			}
		}

		model.put("cases", list);
		model.put("values", values);

		return new ModelAndView(new VorgangCsvCreator(), "map", model);
	}

	@RequestMapping(value = "/file", method = { RequestMethod.GET, RequestMethod.HEAD })
	public String file(ModelMap model, HttpServletRequest request, HttpServletResponse response, Long id, String action,
			Long instanceId, Long vorgangId) {

		intializePageelements(request);

		ExtendedFileMitRecht file = fileService.fetchByValueId(id, authorityUtils.getRoles());

		// File not found
		if (file == null) {
			messageContainer.addErrorKey("error.file_not_found");
			return home(model, request);
		}

		// Permission denied
		if (file.getRecht() == null || InputRechteEnum.NONE.equals(file.getRecht())) {
			messageContainer.addErrorKey("error.accessdenied");
			return vorgang(model, request, file.getVorgangId());
		}

		// Löschen
		if ("delete".equals(action)) {
			try {
				fileService.delete(file);
				messageContainer.addSuccessKey("success.delete_file");
				return vorgang(model, request, file.getVorgangId());
			} catch (SecurityException e) {
				messageContainer.addErrorKey("error.accessdenied");
				return vorgang(model, request, file.getVorgangId());
			}
		}

		// Versuche Vorschau anzubieten
		if ("preview".equals(action) && "application/pdf".equals(file.getMimeType())) {
			model.put("url", "fame/user/file?id=" + id + "&action=download&instanceId=" + instanceId);
			model.put("back", "fame/user/vorgang?id=" + vorgangId);
			return "global/viewerjs";
		}

		// download
		try {
			InputStream is = new ByteArrayInputStream(file.getContent());
			String filename = URLEncoder.encode(file.getFilename(), StandardCharsets.UTF_8.toString());

			response.setHeader("Content-Type", file.getMimeType());
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);

			if (!request.getMethod().equals(RequestMethod.HEAD.toString())) {
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
				return null;
			}
		} catch (IOException ex) {
			messageContainer.addErrorKey("error.generic");
		}

		return vorgang(model, request, file.getVorgangId());
	}

	private List<WorkflowInstance> getUserWorkflowInstances(HttpServletRequest request) {

		@SuppressWarnings("unchecked")
		List<WorkflowInstance> userWorkflowInstances = (List<WorkflowInstance>) request.getSession()
				.getAttribute("USER_WORKFLOW_KEY");
		if (userWorkflowInstances == null) {

			// Workflows des Benutzer laden
			userWorkflowInstances = workflowInstanceService.findByRoles(authorityUtils.getRoles());
			request.getSession().setAttribute(USER_WORKFLOWS_DETAILS, userWorkflowInstances);
		}

		List<ExtendedWorkflowInstance> wis = statusService.findDetaildByWorkflowInstancesAndRoles(userWorkflowInstances,
				authorityUtils.getRoles());

		request.getSession().setAttribute(USER_WORKFLOWS_DETAILS, wis);

		return userWorkflowInstances;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {

		intializePageelements(request);

		List<WorkflowInstance> userWorkflowInstances = getUserWorkflowInstances(request);
		model.put(USER_WORKFLOWS,
				statusService.findDetaildByWorkflowInstancesAndRoles(userWorkflowInstances, authorityUtils.getRoles()));

		return "fame/user/home";
	}

	private void intializePageelements(HttpServletRequest request) {

		getUserWorkflowInstances(request);

		// Status(pl) des aktiven Workflows laden
		Long activeWorkflowInstanceId = (Long) request.getSession().getAttribute(ACTIVE_WORKFLOW_INSTANCE_ID);
		if (activeWorkflowInstanceId != null) {
			try {
				WorkflowInstance wi = workflowInstanceService.fetchById(activeWorkflowInstanceId);
				List<Status> stats = statusService.findByWorkflow(wi.getWorkflowId(), false);
				request.getSession().setAttribute(ACTIVE_WORKFLOW_STATES, stats);
			} catch (Exception e) {
				logger.error("Konnte das Menü nicht laden", e);
			}
		}
	}

	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public String move(HttpServletRequest request, ModelMap model, String ids, Long to, Long instanceId) {

		int num = 0;
		for (String s : ids.split(",")) {
			try {
				Long id = Long.parseLong(s);
				Vorgang v = vorgangService.fetchById(id);
				vorgangService.statusUebergang(v, to);
				num++;
			} catch (StatusuebergangVerbotenException e) {
				messageContainer.addErrorKey("fame.user.statusChangeDenied", s);
			}
		}

		if (num > 0) {
			messageContainer.addSuccessKey("fame.user.statusChangeNumber", num);
		}

		return status(model, request, to, instanceId);
	}

	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public ModelAndView pdf(HttpServletResponse response, ModelMap model, String ids) {

		List<String> roles = authorityUtils.getRoles();

		List<Vorgang> list = new ArrayList<>();
		Map<Vorgang, List<ExtendedInput>> values = new HashMap<>();
		for (String s : ids.split(",")) {
			try {
				Long id = Long.parseLong(s);
				Vorgang vorgang = vorgangService.fetchById(id);
				List<ExtendedInput> inputs = inputService
						.findByWorkflowInstanceVorgangAndRoles(vorgang.getWorkflowInstanceId(), id, roles);

				list.add(vorgang);
				values.put(vorgang, inputs);
			} catch (NumberFormatException e) {
				// NOPMD
				// egal
			}
		}

		model.put("cases", list);
		model.put("values", values);

		return new ModelAndView(new VorgangPdfCreator(), "map", model);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(ModelMap model, HttpServletRequest request, String search) {

		ImportResult result = workflowInstanceService.searchAndImportInConnectors(search);

		if (result.getAktualisiert() > 0 || result.getAngelegt() > 0) {
			messageContainer.addSuccessKey("fame.user.anzahl_importierte_vorgaenge", result.getAngelegt(),
					result.getAktualisiert());
		} else {
			if (result.getErrors().isEmpty()) {
				messageContainer.getWarnings().add("Keine gültige Bewerbung gefunden");
			}
		}

		HashMap<String, String> imported = new HashMap<>();
		for (Vorgang v : result.getVorgaenge()) {
			WorkflowInstance wi = workflowInstanceService.fetchById(v.getWorkflowInstanceId());
			imported.put(v.getId() + "", "Öffne Vorgang in " + wi.getName());
		}
		model.put("imported", imported);

		messageContainer.getErrors().addAll(result.getErrors());

		return home(model, request);
	}

	@RequestMapping(value = "/search2", method = RequestMethod.GET)
	public String search2(ModelMap model, HttpServletRequest request, String search) {

		List<Suchergebnis> ergebnisse = vorgangService.search(search);
		model.put("ergebnisse", ergebnisse);
		model.put("word", search);

		return "fame/user/search";
	}

	@RequestMapping(value = "/searchform", method = RequestMethod.GET)
	public String searchform(ModelMap model, HttpServletRequest request, Long workflowId) {

		List<Input> inputs = inputService.findByWorkflowAndRoles(workflowId, authorityUtils.getRoles());

		model.put("inputs", inputs);
		model.put("workflowId", workflowId);

		return "fame/user/searchform";
	}

	@RequestMapping(value = "/searchresult", method = RequestMethod.POST)
	public String searchresult(ModelMap model, HttpServletRequest request, Long workflowId) {

		Map<Long, String> params = new HashMap<>();
		List<Input> inputs = inputService.findByWorkflowAndRoles(workflowId, authorityUtils.getRoles());
		for (Input input : inputs) {
			String val = request.getParameter("input_" + input.getId());
			if (val != null && !val.isEmpty()) {
				params.put(input.getId(), val);
			}
		}

		List<Map<String, String>> result = vorgangService.search(workflowId, params);
		if (result == null || result.isEmpty()) {
			messageContainer.addWarningKey("fame.error.kein_vorgang_gefunden");
			return searchform(model, request, workflowId);
		}

		Long selectTypeId = inputTypeService.fetchByUniqueName(InputTypeEnum.SELECT).getId();

		List<Input> cols = new ArrayList<>();
		for (Input input : inputService.findByWorkflow(workflowId, InputDao.ORDER_BY_TABLE_POSITION)) {
			if (input.getTablePos() == null || input.getTablePos() <= 0) {
				continue;
			}
			cols.add(input);

			String fieldname = "value_" + input.getId();
			if (selectTypeId != null && selectTypeId.equals(input.getInputTypeId())) {
				for (Map<String, String> map : result) {
					String value = map.get(fieldname);
					String[] lines = input.getOptions().split("\n");
					for (String line : lines) {
						String[] parts = line.split(";");
						if (parts[0].equals(value)) {
							map.put(fieldname, parts[1]);
							break;
						}
					}
				}
			}
		}

		model.addAttribute("inputs", cols);
		model.put("vorgaenge", result);

		return "fame/user/searchresult";
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public String status(ModelMap model, HttpServletRequest request, Long statusId, Long instanceId) {

		intializePageelements(request);
		request.getSession().setAttribute(ACTIVE_WORKFLOW_INSTANCE_ID, instanceId);

		// Label umwandelt
		List<Map<String, String>> vorgaenge = vorgangService.findByStatusAndWorkflowInstance(statusId, instanceId);

		List<Input> cols = new ArrayList<>();
		for (ExtendedInput input : inputService.findByWorkflowInstanceStatusAndRoles(instanceId, statusId,
				authorityUtils.getRoles())) {

			if (InputRechteEnum.NONE.equals(input.getRecht())) {
				continue;
			}
			if (input.getTablePos() == null || input.getTablePos() <= 0) {
				continue;
			}
			cols.add(input);

			String fieldname = "value_" + input.getId();
			if (InputTypeEnum.SELECT == input.getInputTypeUniqName()) {
				for (Map<String, String> map : vorgaenge) {
					String value = map.get(fieldname);
					String[] lines = input.getOptions().split("\n");
					for (String line : lines) {
						String[] parts = line.split(";");
						if (parts[0].equals(value)) {
							map.put(fieldname, parts[1]);
							break;
						}
					}
				}
			}

		}

		Status status = statusService.fetchById(statusId);
		model.addAttribute("inputs", cols);
		model.addAttribute("vorgaenge", vorgaenge);
		model.addAttribute("status", status);
		model.addAttribute("workflow", workflowService.fetchById(status.getWorkflowId()));
		model.addAttribute("stati", statusService.findByWorkflow(status.getWorkflowId(), false));
		model.addAttribute("instanceId", instanceId);

		return "fame/user/status";
	}

	@RequestMapping(value = "/vorgang", method = RequestMethod.GET)
	public String vorgang(ModelMap model, HttpServletRequest request, Long id) {

		intializePageelements(request);

		Vorgang vorgang = null;
		Long workflowInstanceId = 0L;
		if (id == 0L) {
			workflowInstanceId = Long.parseLong(request.getParameter("workflowInstanceId"));
			vorgang = new Vorgang();
		} else {
			vorgang = vorgangService.fetchById(id);
			workflowInstanceId = vorgang.getWorkflowInstanceId();
		}

		request.getSession().setAttribute(ACTIVE_WORKFLOW_INSTANCE_ID, workflowInstanceId);

		// Vorgang aktualisieren
		if (request.getParameter("start") != null) {
			try {
				ImportResult anz = workflowInstanceService.triggerConnector(workflowInstanceId,
						vorgang.getConnectorKey());
				messageContainer.addSuccessKey("fame.user.anzahl_importierte_vorgaenge", anz.getAngelegt(),
						anz.getAktualisiert());
				vorgang = vorgangService.fetchById(id);
			} catch (ImportException e) {
				messageContainer.addErrorKey("backend.message", e.getMessage());
				logger.error("FaME Import fehlgeschlagen", e);
			} catch (Exception e) {
				messageContainer.addErrorKey("error.generic");
				logger.error("FaME Import fehlgeschlagen", e);
			}
		}

		try {
			// Vorgang sperren
			if (request.getParameter("edit") != null) {
				vorgangService.inBearbeitungNehmen(vorgang);
			}
			// Sperre aufheben
			if (request.getParameter("done") != null) {
				vorgangService.zurBearbeitungFreigeben(vorgang);
			}
			// Keine weitere Bearbeitung
			if (request.getParameter("none") != null) {
				vorgangService.keineWeitereBearbeitung(vorgang);
			}
		} catch (VorgangGesperrtException e) {
			messageContainer.addWarningKey("fame.user.vorgang_gesperrt", e.getVorgang().getGesperrtDurch());
		}

		List<String> roles = authorityUtils.getRoles();
		List<ExtendedInput> inputs = inputService.findByWorkflowInstanceVorgangAndRoles(workflowInstanceId, id, roles);
		List<Status> status = statusService.findByVorgangAndRoles(vorgang, roles);

		if (inputs.stream().filter(input -> !InputRechteEnum.NONE.equals(input.getRecht())).count() == 0) {
			return workflow(model, request, workflowInstanceId);
		}

		WorkflowInstance wi = workflowInstanceService.fetchById(workflowInstanceId);
		model.addAttribute("inputs", inputs);
		model.addAttribute("statuses", status);
		model.addAttribute("vorgang", vorgang);
		model.put("workflowId", wi.getWorkflowId());
		model.addAttribute("readonly", !authorityUtils.getCurrentUserName().equals(vorgang.getGesperrtDurch()));
		model.addAttribute("gesperrt_durch", vorgangService.inBearbeitungBei(vorgang));
		model.put("log", logService.findByVorgang(id));

		for (ExtendedInput input : inputs) {
			model.put("options" + input.getId(), inputService.parseOptions(input));
		}

		return "fame/user/vorgang";
	}

	@RequestMapping(value = "/vorgang", method = RequestMethod.POST)
	public String vorgangPost(ModelMap model, HttpServletRequest request, Long id, MultipartHttpServletRequest mrequest)
			throws ModelAndViewDefiningException {

		try {
			Vorgang vorgang = null;
			Long workflowInstanceId = 0L;
			if (id == 0L) {
				workflowInstanceId = Long.parseLong(request.getParameter("workflowInstanceId"));
			} else {
				vorgang = vorgangService.fetchById(id);
				workflowInstanceId = vorgang.getWorkflowInstanceId();
			}

			Long nextStatusId = Long.parseLong(request.getParameter("statusId"));

			WorkflowInstance instance = workflowInstanceService.fetchById(workflowInstanceId);
			Workflow workflow = workflowService.fetchById(instance.getWorkflowId());
			List<ExtendedInput> inputs = inputService.findByWorkflowInstanceVorgangAndRoles(workflowInstanceId, id,
					authorityUtils.getRoles());
			List<ExtendedInput> allInputs = inputService.findByWorkflowAndVorgang(instance.getWorkflowId(), id);
			List<Status> status = statusService.findByWorkflow(workflow.getId(), false);
			inputValidationHelper.validateAndSave(inputs, allInputs, status, request.getParameterMap(),
					workflow.getInputValidationCode(), mrequest, nextStatusId);

			messageContainer.addSuccessKey("success.update");
		} catch (StatusuebergangVerbotenException e) {
			messageContainer.addErrorKey("fame.user.StatusuebergangVerbotenException");
		} catch (NumberFormatException e) {
			messageContainer.addErrorKey("fame.user.NeuerStatusFehlerhaft");
		} catch (PflichtfeldException | RegexException e) {
			messageContainer.addErrorKey("backend.message", e.getMessage());
		} catch (ValidationException e) {
			for (String s : e.getErrors()) {
				messageContainer.addErrorKey(s);
			}
		} catch (Exception e) {
			logger.error("Konnte Vorgang nicht speichern", e);
			messageContainer.addErrorKey("error.generic");
		} finally {
			if (!messageContainer.getErrors().isEmpty()) {
				ModelAndView view = new ModelAndView(vorgang(model, request, id), model);
				throw new ModelAndViewDefiningException(view);
			}
		}

		return vorgang(model, request, id);
	}

	@RequestMapping(value = "/workflow", method = RequestMethod.GET)
	public String workflow(ModelMap model, HttpServletRequest request, Long id) {

		intializePageelements(request);
		request.getSession().setAttribute(ACTIVE_WORKFLOW_INSTANCE_ID, id);

		return "fame/user/workflow";
	}

	@RequestMapping(value = "/workflowValidation", method = RequestMethod.GET)
	public String workflowValidation(ModelMap model, HttpServletRequest request, Long id) {

		Workflow w = workflowService.fetchById(id);
		model.put("script", w.getInputValidationCode());
		model.put("status", statusService.findByWorkflow(id, true));

		return "fame/user/workflow_validation";
	}
}
