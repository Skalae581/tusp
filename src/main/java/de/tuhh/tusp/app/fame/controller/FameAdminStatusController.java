package de.tuhh.tusp.app.fame.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.BaseCrudController;

@Controller
@RequestMapping(value = "/fame/admin/status")
@AccessConfig(path = "/fame/admin/status/**", access = { Rollen.ADMIN })
public class FameAdminStatusController extends BaseCrudController<Status> {

	private static Logger logger = LogManager.getLogger(FameAdminStatusController.class);

	@Autowired
	FameAdminController fameAdminController;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	protected FameAdminStatusController(StatusService service) {
		super(service, Status.class, "fame/admin/status/");
	}

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String add(ModelMap model, Long workflowId) {

		model.put("workflows", workflowService.findAll());
		Status object = new Status();
		object.setWorkflowId(workflowId);

		model.put("object", object);

		return "fame/admin/status/form";
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
