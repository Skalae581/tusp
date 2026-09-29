package de.tuhh.tusp.stu.timsl.controller;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.stu.timsl.pojo.TimSonderStudent;
import de.tuhh.tusp.stu.timsl.service.TimSonderStudentService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.DatePropertyEditorSupport;
import de.tuhh.tusp.utils.MessageContainer;

@Controller
@RequestMapping(value = "/tsstudent")
@SessionAttributes("tsstudent")
@AccessConfig(path = "/tsstudent/**", access = { Rollen.STU_SENIOR_MANAGER, Rollen.ADMIN })
public class TimSonderStudentController {

	private static Logger logger = LogManager.getLogger(TimSonderStudentController.class);

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private TimSonderStudentService timSonderStudentService;

	@InitBinder
	public void binder(WebDataBinder binder) {

		binder.registerCustomEditor(Date.class, new DatePropertyEditorSupport());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public String Delete(ModelMap model, Long id) {

		try {
			if (timSonderStudentService.delete(id) > 0) {
				messageContainer.addSuccessKey("success.delete");
			}
			return List(model);
		} catch (Exception e) {
			messageContainer.addSuccessKey(e.getMessage());
			return Edit(model, id);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	public String Edit(ModelMap model, Long id) {

		TimSonderStudent c = timSonderStudentService.fetchById(id);
		model.put("student", c);

		return "tsstudent/edit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/edit")
	public String EditPost(ModelMap model, Long id, Long mtknr, Date from, Date to) {

		try {
			if (id == null) {
				TimSonderStudent student = new TimSonderStudent();
				student.setMtknr(mtknr);
				student.setStartdate(from);
				student.setEnddate(to);
				id = timSonderStudentService.insert(student);

				messageContainer.addSuccessKey("success.insert");
			} else {
				TimSonderStudent student = timSonderStudentService.fetchById(id);
				student.setMtknr(mtknr);
				student.setStartdate(from);
				student.setEnddate(to);

				timSonderStudentService.update(student);

				messageContainer.addSuccessKey("success.update");
			}
		} catch (Exception e) {
			messageContainer.addErrorKey("backend.message", e.getMessage());
		}

		return Edit(model, id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String List(ModelMap model) {

		java.util.List<TimSonderStudent> students = timSonderStudentService.findAll();
		model.put("students", students);

		return "tsstudent/list";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/new")
	public String New(ModelMap model) {

		TimSonderStudent stud = new TimSonderStudent();
		int sem = CalendarUtils.getSemester(new GregorianCalendar());
		stud.setStartdate(new java.sql.Date(new Date().getTime()));
		stud.setEnddate(
				new java.sql.Date(CalendarUtils.lastDayOfSemester(sem).toGregorianCalendar().getTime().getTime()));
		model.put("student", stud);
		return "tsstudent/edit";
	}

}
