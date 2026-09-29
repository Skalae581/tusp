package de.tuhh.tusp.app.fame.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;
import de.tuhh.tusp.app.fame.service.RollenService;
import de.tuhh.tusp.app.fame.service.StatusBerechtigungService;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;

@Controller
@RequestMapping(value = "/fame/admin/statusberechtigung")
@AccessConfig(path = "/fame/admin/statusberechtigung/**", access = { Rollen.ADMIN })
public class FameAdminStatusBerechtigungController {

	private static Logger logger = LogManager.getLogger(FameAdminStatusBerechtigungController.class);

	@Autowired
	private StatusBerechtigungService statusBerechtigungService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private RollenService rollenService;

	@Autowired
	private WorkflowService workflowService;

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StatusBerechtigung add(ModelMap model, Long rolleId, Long statusFromId, Long statusToId) {

		StatusBerechtigung ib = new StatusBerechtigung();
		ib.setStatusToId(statusToId);
		ib.setStatusFromId(statusFromId);
		ib.setRolleId(rolleId);
		ib.setId(statusBerechtigungService.insert(ib));

		return ib;
	}

	@RequestMapping(value = { "/get" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StatusBerechtigung get(ModelMap model, Long id) {

		return statusBerechtigungService.fetchById(id);

	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model, Long id, HttpServletRequest request) {

		rollenService.aktualisiere();

		List<Status> stati = statusService.findByWorkflow(id, true);

		model.put("workflow", workflowService.fetchById(id));
		model.put("stati", stati);
		model.put("rollen", rollenService.findAll());
		model.put("empf", request.getParameter("empf"));

		return "fame/admin/statusberechtigung/list";
	}

	@RequestMapping(value = { "/remove" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody boolean remove(ModelMap model, Long id) {

		statusBerechtigungService.delete(id);

		return true;

	}

	@RequestMapping(value = { "/rollen" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<de.tuhh.tusp.app.fame.pojo.Rollen> rollen(ModelMap model, Long id) {

		return rollenService.findAll();

	}

	@RequestMapping(value = { "/statusberechtigung" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<StatusBerechtigung> statusberechtigung(ModelMap model, Long id) {

		return statusBerechtigungService.findByWorkflow(id);

	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody boolean update(ModelMap model, Long id, String bedingungen, String kommentar) {

		StatusBerechtigung sb = statusBerechtigungService.fetchById(id);
		statusBerechtigungService.update(sb);

		return true;

	}

}
