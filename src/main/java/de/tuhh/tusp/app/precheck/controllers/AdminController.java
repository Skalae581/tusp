package de.tuhh.tusp.app.precheck.controllers;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.tuhh.tusp.app.precheck.pojo.Studiengang;
import de.tuhh.tusp.app.precheck.service.StudiengangService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;

@Controller
@RequestMapping(value = "/precheck/admin")
@AccessConfig(path = "/precheck/admin/**", access = { Rollen.ADMIN })
public class AdminController {

	private static Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	private StudiengangService studiengangService;

	@Autowired
	MessageSource messageSource;

	Locale locale = LocaleContextHolder.getLocale();

	@RequestMapping(method = RequestMethod.GET, value = "/studiengang/list")
	public String list(ModelMap model) {

		model.put("list", studiengangService.findLabeldAll());

		return "precheck/admin/studiengang_liste";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/studiengang/list/change")
	public String listChangeState(ModelMap model, @RequestParam("id") Long id) {

		Studiengang studiengang = studiengangService.fetchByCoursOfStudyId(id);
		if (studiengang == null) {
			studiengang = new Studiengang();
			studiengang.setAktiv(1);
			studiengang.setCourseofstudyId(id);
			studiengangService.insert(studiengang);
		} else if (studiengang.getAktiv() == 1) {
			studiengang.setAktiv(0);
			studiengangService.update(studiengang);
		} else {
			studiengang.setAktiv(1);
			studiengangService.update(studiengang);
		}

		return list(model);
	}
}
