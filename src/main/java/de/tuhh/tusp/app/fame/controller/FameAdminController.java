package de.tuhh.tusp.app.fame.controller;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tuhh.tusp.app.fame.dao.InputDao;
import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.app.fame.service.InputService;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.MessageContainer;

@Controller
@RequestMapping(value = "/fame/admin")
@AccessConfig(path = "/fame/admin/**", access = { Rollen.ADMIN })
public class FameAdminController {

	private static Logger logger = LogManager.getLogger(FameAdminController.class);

	@Autowired
	private InputService inputService;

	@Autowired
	private WorkflowInstanceService workflowInstanceService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private WorkflowService workflowService;

	@RequestMapping(value = { "/overview" }, method = RequestMethod.GET)
	public String overview(ModelMap model, Long id) {

		// Versuche zu laden und springe zurück wenn nicht klappt
		Workflow workflow = null;
		try {
			workflow = workflowService.fetchById(id);
		} catch (NoSuchElementException e) {
			if (workflow == null) {
				messageContainer.addWarningKey("error.data_not_fount");
				return start(model);
			}
		}

		model.put("workflow", workflow);
		model.put("stati", statusService.findByWorkflow(id, false));
		model.put("inputs", inputService.findByWorkflow(id, InputDao.ORDER_BY_POSITION));
		model.put("instances", workflowInstanceService.findByWorkflow(id));

		return "fame/admin/overview";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String start(ModelMap model) {

		model.put("objects", workflowService.findAll());

		return "fame/admin/start";
	}

}
