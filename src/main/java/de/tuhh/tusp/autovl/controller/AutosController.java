package de.tuhh.tusp.autovl.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.tuhh.tusp.autovl.dao.AutoDao;
import de.tuhh.tusp.autovl.pojo.Auto;
import de.tuhh.tusp.autovl.pojo.AvlUser;
import de.tuhh.tusp.autovl.pojo.Buchung;
import de.tuhh.tusp.autovl.service.AvlUserService;
import de.tuhh.tusp.autovl.service.BuchungService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.MessageContainer;

@Controller
@RequestMapping(value = "/autos")
@SessionAttributes({ "auto", "buchung", "user" })
@AccessConfig(path = "/autos/**", access = { Rollen.ADMIN })
public class AutosController {
	private static Logger logger = LogManager.getLogger(AutosController.class);

	@Autowired
	private AutoDao autoDao;
	@Autowired
	private AvlUserService avlUserService;
	@Autowired
	private BuchungService buchungService;
	@Autowired
	private MessageContainer messageContainer;

	public List<Buchung> findByAutoId(Long id) {

		List<Buchung> buchungen = getBuchungData();
		List<Buchung> gefundeneBuchungen = new ArrayList<>();
		for (Buchung b : buchungen) {

			if (id == b.getAutoId()) {
				gefundeneBuchungen.add(b);
			}

		}

		return gefundeneBuchungen;
	}

	public Auto findByName(String name) {
		Auto gefundenAuto = null;
		List<Auto> autos = getAutoData();

		for (Auto a : autos) {
			if (name.equals(a.getName())) {
				gefundenAuto = a;
			}

		}

		return gefundenAuto;
	}

	public String findNameById(Long id) {
		String autoName = "";
		List<Auto> autos = getAutoData();
		for (Auto a : autos) {
			if (id == a.getId()) {
				autoName = a.getName();
			}

		}

		return autoName;
	}

	@ModelAttribute("auto")
	public Auto getAuto() {

		return new Auto();
	}

	private List<Auto> getAutoData() {

		List<Auto> autos = new ArrayList<>();

		Auto a1 = new Auto();
		a1.setId(1L);
		a1.setName("Honda-2000");
		a1.setAnhaengerkopplung("Anhänger kömplung Value");
		a1.setBreite("2,5 M");
		a1.setDieselOElektro("Diesel");
		a1.setLadevolumen("200 L");
		a1.setReichweite("1000 KM");
		a1.setHoehe("1,5 M");
		a1.setLadeKante("Ladekante Wert");
		a1.setLadeflaeche("1 M");
		a1.setLaenge("4 M");
		autos.add(a1);

		Auto a2 = new Auto();
		a2.setId(2L);
		a2.setName("BMW-Elektro");
		a2.setAnhaengerkopplung("Anhänger kömplung Value");
		a2.setBreite("3,5 m");
		a2.setDieselOElektro("Elektro");
		a2.setLadevolumen("250 L");
		a2.setReichweite("1000 KM");
		a2.setHoehe("1,5 M");
		a2.setLadeKante("Ladekante Wert");
		a2.setLadeflaeche("1 M");
		a2.setLaenge("4 M");
		autos.add(a2);

		Auto a3 = new Auto();
		a3.setId(3L);
		a3.setName("Audi A3");
		a3.setAnhaengerkopplung("Anhänger kömplung Value");
		a3.setBreite("3,0 m");
		a3.setDieselOElektro("Benzin");
		a3.setLadevolumen("400 L");
		a3.setReichweite("1000 KM");
		a3.setHoehe("1,5 M");
		a3.setLadeKante("Ladekante Wert");
		a3.setLadeflaeche("1 M");
		a3.setLaenge("4 M");
		autos.add(a3);

		Auto a4 = new Auto();
		a4.setId(4L);
		a4.setName("Audi A8 TDI");
		a4.setAnhaengerkopplung("Anhänger kömplung Value");
		a4.setBreite("3,0 m");
		a4.setDieselOElektro("Diesel");
		a4.setLadevolumen("400 L");
		a4.setReichweite("1000 KM");
		a4.setHoehe("1,5 M");
		a4.setLadeKante("Ladekante Wert");
		a4.setLadeflaeche("1 M");
		a4.setLaenge("4 M");
		autos.add(a4);

		Auto a5 = new Auto();
		a5.setId(5L);
		a5.setName("Mitsubishi GT3000");
		a5.setAnhaengerkopplung("Anhänger kömplung Value");
		a5.setBreite("1,80 m");
		a5.setDieselOElektro("Benzin 4 T");
		a5.setLadevolumen("40 L");
		a5.setReichweite("250 KM");
		a5.setHoehe("1,10 M");
		a5.setLadeKante("Ladekante Wert");
		a5.setLadeflaeche("1 M");
		a5.setLaenge("3,20 M");
		autos.add(a5);

		return autos;
	}

	private List<AvlUser> getAvlUserData() {

		List<AvlUser> avlUser = new ArrayList<>();

		AvlUser a1 = new AvlUser();

		a1.setVorname("Max");
		a1.setNachname("Mustermann");
		a1.setTel("01600");
		a1.setId(1L);
		a1.setInstitut("tuhh");
		a1.setEmail("max.mustermann@tuhh.de");
		a1.setFuererschein("ja");
		a1.setGueltigkeit("12/21/2025");
		avlUser.add(a1);

		AvlUser a2 = new AvlUser();
		a2.setVorname("Marie");
		a2.setNachname("Musterfrau");
		a2.setTel("01900");
		a2.setId(1L);
		a2.setInstitut("tuhh");
		a2.setEmail("marie.musterfrau@tuhh.de");
		a2.setFuererschein("ja");
		a2.setGueltigkeit("12/21/2030");
		avlUser.add(a2);

		return avlUser;

	}

	@ModelAttribute("buchung")
	public Buchung getBuchung() {

		return new Buchung();
	}

	private List<Buchung> getBuchungData() {

		List<Buchung> buchungen = new ArrayList<>();

		Buchung b1 = new Buchung();
		b1.setId(1L);
		b1.setAvlUserId(1L);
		b1.setAutoId(1L);
		b1.setVon("2021-11-08");
		b1.setBis("2021-11-10");
		buchungen.add(b1);

		Buchung b2 = new Buchung();
		b2.setId(2L);
		b2.setAvlUserId(2L);
		b2.setAutoId(2L);
		b2.setVon("2021-11-09");
		b2.setBis("2021-11-13");
		buchungen.add(b2);

		Buchung b3 = new Buchung();
		b3.setId(3L);
		b3.setAvlUserId(2L);
		b3.setAutoId(1L);
		b3.setVon("2021-11-15");
		b3.setBis("2021-11-20");
		buchungen.add(b3);

		return buchungen;

	}

	public JSONArray getJsonData(Long autoId) {

		JSONArray jsonArray = new JSONArray();

		List<Buchung> buchungen = findByAutoId(autoId);

		for (Buchung buchung : buchungen) {

			JSONObject jsonOBJ = new JSONObject();

			String autoName = findNameById(autoId);
			try {
				jsonOBJ.put("title", autoName);
				jsonOBJ.put("start", buchung.getVon());
				jsonOBJ.put("end", buchung.getBis());
				jsonOBJ.put("color", "#ababab"); // TODO get color for auto
			} catch (JSONException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}

			jsonArray.put(jsonOBJ);

		}

		return jsonArray;

	}

	@ModelAttribute("user")
	public AvlUser getUser() {

		return new AvlUser();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "autos/startseite";

	}

	@RequestMapping(value = "/benutzer/autosinfo")
	public String pageAutoInfos(@ModelAttribute Auto auto, ModelMap model, HttpServletRequest request)
			throws ParseException {

		model.addAttribute("autos", getAutoData());

		return "autos/benutzer/autosinfo";

	}

	@RequestMapping(value = "/benutzer/buchung")
	public String pageBuchungInfos(@ModelAttribute Buchung buchung, @ModelAttribute Auto auto,
			BindingResult bindingResult, @RequestParam(value = "autoSelect", required = false) String autoSelect,
			ModelMap model, HttpServletRequest request) throws ParseException {

		// Wenn Probleme auftreten zurück zum vorigen Formular
		if (bindingResult != null && bindingResult.hasErrors()) {
			messageContainer.addBindingResult("autos.", bindingResult);
			return pageAutoInfos(auto, model, request);
		}

		System.out.println(autoSelect);
		if (null == autoSelect) {

			autoSelect = "Honda-2000";
		}

		System.out.println("PARAM: " + request.getParameter("autoId"));

		// fine Auto über Name und lade die Zeiten aus, wo auto weg ist
		// Benutzer diese Zeiten ,um im Kalender die Tage auszublenden
		// versuche als json zu speichern
		Auto a = findByName(autoSelect);
		model.addAttribute("auto", a);

		List<Buchung> buchungen = findByAutoId(a.getId());
		model.addAttribute("buchungen", buchungen);

		JSONArray jsonData = getJsonData(a.getId());
		model.addAttribute("jsonData", jsonData);

		return "autos/benutzer/buchung";

	}

	@RequestMapping(value = "/benutzer/lastpage")
	public String pageEnde(ModelMap model, HttpServletRequest request) throws ParseException {

		return "autos/benutzer/lastpage";

	}

	@RequestMapping(value = "/benutzer/overviewpage")
	public String pageOverview(@ModelAttribute Buchung buchung, @ModelAttribute Auto auto,
			@ModelAttribute AvlUser avlUser, BindingResult bindingResult,
			@RequestParam(value = "autoSelect", required = false) String autoSelect, ModelMap model,
			HttpServletRequest request) throws ParseException {

		// Wenn Probleme auftreten zurück zum vorigen Formular
		if (bindingResult != null && bindingResult.hasErrors()) {
			messageContainer.addBindingResult("buchung.", bindingResult);
			return pageUserInfos(avlUser, buchung, auto, bindingResult, null, model, request);
		}

		// hier sind alle Daten die wir angegeben haben
		model.addAttribute("autoObj", auto);
		model.addAttribute("buchungObj", buchung);
		model.addAttribute("userObj", avlUser);

		return "autos/benutzer/overviewpage";

	}

	@RequestMapping(value = "/benutzer/userinfo")
	public String pageUserInfos(@ModelAttribute AvlUser avlUser, @ModelAttribute Buchung buchung,
			@ModelAttribute Auto auto, BindingResult bindingResult, @RequestParam(required = false) String autoSelect,
			ModelMap model, HttpServletRequest request) throws ParseException {

		// Wenn Probleme auftreten zurück zum vorigen Formular
		if (bindingResult != null && bindingResult.hasErrors()) {
			messageContainer.addBindingResult("buchung.", bindingResult);
			return pageBuchungInfos(buchung, auto, bindingResult, autoSelect, model, request);
		}

		model.addAttribute("autoId: ", auto.getId());

		System.out.println("autoId: " + auto.getId());
		System.out.println("AutoSelect: " + autoSelect);

		return "autos/benutzer/userinfo";

	}

}
