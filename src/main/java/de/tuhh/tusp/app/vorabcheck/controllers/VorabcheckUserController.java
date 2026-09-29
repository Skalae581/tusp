package de.tuhh.tusp.app.vorabcheck.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.app.vorabcheck.pojo.EctsForm;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.pojo.VorabInteressent;
import de.tuhh.tusp.app.vorabcheck.service.BereichService;
import de.tuhh.tusp.app.vorabcheck.service.StudiengangsauswahlService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;

@Controller
@RequestMapping(value = "/vorabcheck")
@SessionAttributes("vorabInteressent")
@AccessConfig(path = "/vorabcheck/**", access = { Rollen.PUBLIC })
public class VorabcheckUserController {

	@Autowired
	private StudiengangsauswahlService studiengangsauswahlService;

	@Autowired
	private BereichService bereichService;

	/**
	 * Seite mit dem ECTS-Bogen zum Ausfüllen
	 *
	 * @return ECTS-Bogen
	 **/
	@RequestMapping(value = "/ects_page")
	public String ectsPage(	@ModelAttribute("vorabInteressent") VorabInteressent vorabInteressent, ModelMap mdl,
							Model model) {

		Long stgId = Long.parseLong(vorabInteressent.getStgId());
		model.addAttribute("stgName", vorabInteressent.getStgName());
		model.addAttribute("stgId", vorabInteressent.getStgId());

		List<Long> bereichIds = bereichService.getBereichIdsById(stgId);
		mdl.put("bereich", studiengangsauswahlService.findAllBereichById(stgId));
		mdl.put("fach", studiengangsauswahlService.findAllFachByIds(bereichIds));

		return "vorabcheck/user/ects_page";
	}

	/**
	 * Startseite des Vorabchecks
	 *
	 * @return Startseite
	 **/
	@RequestMapping(value = { "/", "/start_page" }, method = RequestMethod.GET)
	public String home(Model model, ModelMap mdl) {

		model.addAttribute("vorabInteressent", new VorabInteressent());
		mdl.put("stg", studiengangsauswahlService.findAllStgMD());

		return "vorabcheck/user/start_page";
	}

	/**
	 * Seite mit Informationen zum Vorab-Check
	 *
	 * @return Info-Seite
	 **/
	@RequestMapping(value = "/info_page", method = RequestMethod.POST)
	public String infoPage(	@ModelAttribute("vorabInteressent") VorabInteressent vorabInteressent,
							Studiengangsauswahl studiengangsauswahl, ModelMap model, HttpServletRequest request) {

		Long stgId = Long.parseLong(vorabInteressent.getStgId());
		studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);
		vorabInteressent.setStgName(studiengangsauswahlService.getShorttextDe(studiengangsauswahl.getCourseofstudyId()));

		model.addAttribute("stgName", vorabInteressent.getStgName());
		model.addAttribute("stgId", vorabInteressent.getStgId());
		model.addAttribute("standardkz", studiengangsauswahl.getStandardkz());

		return "vorabcheck/user/info_page";
	}

	/**
	 * EctsForm mit Ects-Feldern befüllen
	 *
	 * @return ectsForm
	 */
	@ModelAttribute("ectsForm")
	public EctsForm populateEcts() {

		EctsForm ectsForm = new EctsForm();
		List<Ects> ects = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			ects.add(new Ects());
		}
		ectsForm.setEcts(ects);

		return ectsForm;
	}

	/**
	 * Seite mit dem Ergebnistext nach dem Ampelsystem
	 *
	 * @return Ergebnisseite
	 **/
	@RequestMapping(value = "/result_page", method = RequestMethod.POST)
	public String resultPage(	@ModelAttribute("ectsForm") EctsForm ectsForm, ModelMap mdl, Model model,
								VorabInteressent vorabInteressent, @ModelAttribute("bewerber") Bewerber bewerber,
								HttpServletRequest req) {

		Long stgId = Long.parseLong(vorabInteressent.getStgId());
		String ergebnis = studiengangsauswahlService.getResult(ectsForm, stgId);
		mdl.put("result", ergebnis);

		return "vorabcheck/user/result_page";
	}
}
