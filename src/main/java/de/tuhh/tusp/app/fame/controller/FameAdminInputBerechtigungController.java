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

import de.tuhh.tusp.app.fame.dao.InputDao;
import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.service.InputBerechtigungService;
import de.tuhh.tusp.app.fame.service.InputService;
import de.tuhh.tusp.app.fame.service.RollenService;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.app.fame.service.WorkflowService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;

@Controller
@RequestMapping(value = "/fame/admin/inputberechtigung")
@AccessConfig(path = "/fame/admin/inputberechtigung/**", access = { Rollen.ADMIN })
public class FameAdminInputBerechtigungController {

	private static Logger logger = LogManager.getLogger(FameAdminInputBerechtigungController.class);

	@Autowired
	private InputBerechtigungService inputBerechtigungService;

	@Autowired
	private InputService inputService;

	@Autowired
	private StatusService statusService;

	@Autowired
	private RollenService rollenService;

	@Autowired
	private WorkflowService workflowService;

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody InputBerechtigung add(ModelMap model, Long rolleId, String recht, Long statusId,
			Long inputId) {

		InputBerechtigung ib = new InputBerechtigung();
		ib.setInputId(inputId);
		ib.setStatusId(statusId);
		ib.setRolleId(rolleId);
		ib.setRecht(InputRechteEnum.valueOf(recht));
		ib.setId(inputBerechtigungService.insert(ib));

		return ib;

	}

	@RequestMapping(value = { "/inputberechtigung" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<InputBerechtigung> inputberechtigung(ModelMap model, Long id) {

		return inputBerechtigungService.findByWorkflow(id);

	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model, Long id, HttpServletRequest request) {

		rollenService.aktualisiere();

		List<Input> inputs = inputService.findByWorkflow(id, InputDao.ORDER_BY_POSITION);
		List<Status> stati = statusService.findByWorkflow(id, true);

		model.put("workflow", workflowService.fetchById(id));
		model.put("stati", stati);
		model.put("inputs", inputs);
		model.put("rechte", InputRechteEnum.getAll());
		model.put("rollen", rollenService.findAll());
		model.put("empf", request.getParameter("empf"));

		return "fame/admin/inputberechtigung/list";
	}

	@RequestMapping(value = { "/remove" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody boolean remove(ModelMap model, Long id) {

		inputBerechtigungService.delete(id);

		return true;

	}

	@RequestMapping(value = { "/rollen" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<de.tuhh.tusp.app.fame.pojo.Rollen> rollen(ModelMap model, Long id) {

		return rollenService.findAll();

	}

}
