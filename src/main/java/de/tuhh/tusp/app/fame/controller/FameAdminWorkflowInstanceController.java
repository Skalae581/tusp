package de.tuhh.tusp.app.fame.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.BaseCrudController;

@Controller
@RequestMapping(value = "/fame/admin/workflowinstance")
@AccessConfig(path = "/fame/admin/workflowinstance/**", access = { Rollen.ADMIN })
public class FameAdminWorkflowInstanceController extends BaseCrudController<WorkflowInstance> {

	private static Logger logger = LogManager.getLogger(FameAdminWorkflowInstanceController.class);

	@Autowired
	FameAdminController fameAdminController;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	protected FameAdminWorkflowInstanceController(WorkflowInstanceService service) {
		super(service, WorkflowInstance.class, "fame/admin/workflowinstance/");
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String add(ModelMap model, Long workflowId) {

		model.put("workflows", workflowService.findAll());
		WorkflowInstance object = new WorkflowInstance();
		object.setWorkflowId(workflowId);

		model.put("object", object);

		return "fame/admin/workflowinstance/form";
	}

	@Override
	public String list(ModelMap model) {

		return fameAdminController.start(model);
	}

	@Override
	public String show(ModelMap model, Long id) {

		model.put("workflows", workflowService.findAll());
		return super.show(model, id);
	}

}
