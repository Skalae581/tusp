package de.tuhh.tusp.app.fame.controller;

import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tuhh.tusp.app.fame.connector.Connector;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.service.InputService;
import de.tuhh.tusp.app.fame.service.InputTypeService;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.app.fame.utils.ConnectorUtils;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.BaseCrudController;

@Controller
@RequestMapping(value = "/fame/admin/input")
@AccessConfig(path = "/fame/admin/input/**", access = { Rollen.ADMIN })
public class FameAdminInputController extends BaseCrudController<Input> {

	private static Logger logger = LogManager.getLogger(FameAdminInputController.class);

	@Autowired
	private FameAdminController fameAdminController;

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private InputTypeService inputTypeService;

	@Autowired
	private InputService inputService;

	@Autowired
	private ConnectorUtils connectorUtils;

	@Autowired
	protected FameAdminInputController(InputService service) {
		super(service, Input.class, "fame/admin/input/");
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String add(ModelMap model, Long workflowId) {

		model.put("types", inputTypeService.findAll());
		model.put("workflows", workflowService.findAll());

		Input object = new Input();
		object.setWorkflowId(workflowId);

		Connector connector = connectorUtils.initializeConnectorByWorkflow(workflowId);

		model.put("object", object);
		model.put("connectorFields",
				connector == null ? new HashSet<String>() : connector.readExapleCase().getPropertyNames());

		return "fame/admin/input/form";
	}

	@Override
	public String list(ModelMap model) {

		return fameAdminController.start(model);
	}

	@Override
	public String show(ModelMap model, Long id) {

		Input input = inputService.fetchById(id);
		Connector connector = connectorUtils.initializeConnectorByWorkflow(input.getWorkflowId());

		model.put("workflows", workflowService.findAll());
		model.put("types", inputTypeService.findAll());
		model.put("connectorFields",
				connector == null ? new HashSet<String>() : connector.readExapleCase().getPropertyNames());

		return super.show(model, id);
	}

}
