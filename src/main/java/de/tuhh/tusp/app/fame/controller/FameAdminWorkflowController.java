package de.tuhh.tusp.app.fame.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import de.tuhh.tusp.app.fame.connector.Connector;
import de.tuhh.tusp.app.fame.dao.InputDao;
import de.tuhh.tusp.app.fame.enums.converter.InputRechteEnumConverter;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.service.InputService;
import de.tuhh.tusp.app.fame.service.InputTypeService;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.BaseCrudController;
import de.tuhh.tusp.utils.XssFilter;

@Controller
@RequestMapping(value = "/fame/admin/workflow")
@AccessConfig(path = "/fame/admin/workflow/**", access = { Rollen.ADMIN })
public class FameAdminWorkflowController extends BaseCrudController<Workflow> {

	private static Logger logger = LogManager.getLogger(FameAdminWorkflowController.class);

	@Autowired
	private FameAdminController fameAdminController;

	@Autowired
	private StatusService statusService;

	@Autowired
	private InputService inputService;

	@Autowired
	private WorkflowService workfloService;

	@Autowired
	private InputTypeService inputTypeService;

	/**
	 * Erstellt eine neue Instanz
	 *
	 * @param workflowService
	 * @param conserterService
	 */
	@Autowired
	protected FameAdminWorkflowController(WorkflowService workflowService,
			FormattingConversionService conserterService) {
		super(workflowService, Workflow.class, "fame/admin/workflow/");

		conserterService.addConverter(new InputRechteEnumConverter());

		XssFilter.disableFor("/fame/admin/workflow/inputValidationScript");
		XssFilter.disableFor("/fame/admin/workflow/deserialize");
	}

	@Override
	public String delete(ModelMap model, Long id) {

		try {
			service.delete(id);
			messageContainer.addSuccessKey("success.delete");
			return list(model);
		} catch (Exception e) {
			messageContainer.getErrors().add(e.getMessage());
			return show(model, id);
		}
	}

	@RequestMapping(value = "/deserialize", method = RequestMethod.GET)
	public String deserialize() {

		return "fame/admin/workflow/deserialize";
	}

	@RequestMapping(value = "/deserialize", method = RequestMethod.POST)
	public String deserialize(@RequestParam("file") MultipartFile file, ModelMap model)
			throws IOException, ClassNotFoundException {

		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			IOUtils.copy(file.getInputStream(), os);
			workfloService.deserialize(os.toByteArray());
			messageContainer.getSuccesses().add("Done!");
		} catch (Exception e) {
			messageContainer.getErrors().add(e.getMessage());
		}

		return deserialize();
	}

	@RequestMapping(value = "/inputValidationScript", method = RequestMethod.GET)
	public String inputValidationScript(ModelMap model, HttpServletRequest request, Long id) {

		model.put("workflow", service.fetchById(id));
		model.put("stati", statusService.findByWorkflow(id, false));
		model.put("inputs", inputService.findByWorkflow(id, InputDao.ORDER_BY_POSITION));
		model.put("inputTypes", inputTypeService.findAll());

		return "fame/admin/workflow/script";
	}

	@RequestMapping(value = "/inputValidationScript", method = RequestMethod.POST)
	public String inputValidationScriptPost(ModelMap model, HttpServletRequest request, Long id,
			String inputValidationCode) {

		Workflow w = service.fetchById(id);
		w.setInputValidationCode(inputValidationCode);
		service.update(w);

		messageContainer.addSuccessKey("fame.admin.import_success");

		return inputValidationScript(model, request, id);
	}

	@Override
	public String list(ModelMap model) {

		return fameAdminController.start(model);
	}

	@RequestMapping(value = "/serialize", method = RequestMethod.GET)
	public void serialize(HttpServletRequest request, HttpServletResponse response, Long id) throws IOException {

		response.setContentType("application/octet-stream");
		ByteArrayInputStream is = new ByteArrayInputStream(workfloService.serialize(id));
		IOUtils.copy(is, response.getOutputStream());
		response.flushBuffer();
	}

	@Override
	public String show(ModelMap model, Long id) {

		Reflections ref = new Reflections("de.tuhh.tusp", new SubTypesScanner());
		List<String> connectors = new ArrayList<>();
		connectors.add("");
		for (Class<? extends Connector> type : ref.getSubTypesOf(Connector.class)) {
			if (type.isInterface()) {
				continue;
			}
			connectors.add(type.getName());
		}
		model.put("connectors", connectors);

		return super.show(model, id);
	}
}
