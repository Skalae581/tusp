package de.tuhh.tusp.app.precheck.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.TableField;
import org.jooq.generated.Tables;
import org.jooq.generated.tables.records.PrecheckInteressentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.tuhh.tusp.app.precheck.dao.InteressentDao.ExtendedInteressent;
import de.tuhh.tusp.app.precheck.pojo.Interessent;
import de.tuhh.tusp.app.precheck.pojo.Status;
import de.tuhh.tusp.app.precheck.service.InteressentService;
import de.tuhh.tusp.app.precheck.service.PrecheckStatusService;
import de.tuhh.tusp.app.precheck.service.StudiengangService;
import de.tuhh.tusp.app.precheck.utils.CookieUtils;
import de.tuhh.tusp.app.precheck.utils.Filter;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.DatePropertyEditorSupport;
import de.tuhh.tusp.utils.MessageContainer;
import de.tuhh.tusp.webservice.sync.service.CountryService;

@Controller
@RequestMapping(value = "/precheck/bearbeiter")
@AccessConfig(path = "/precheck/bearbeiter/**", access = { Rollen.ADMIN, Rollen.ZULBEARB })
public class BearbeiterController {

	private static Logger logger = LogManager.getLogger(BearbeiterController.class);

	Locale locale = LocaleContextHolder.getLocale();

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private InteressentService interessentService;

	@Autowired
	private StudiengangService studiengangService;

	@Autowired
	private PrecheckStatusService statusService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CookieUtils cookieUtils;

	@RequestMapping(value = "/anschalten", method = RequestMethod.GET)
	public String activate(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request,
			@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {

		Interessent object = interessentService.fetchById(id);
		object.setBearbeitungsdatum(null);

		object.setBearbeitet("n");

		String message = "";
		try {
			interessentService.update(object);
			message = "Fall wurde erfolgreich aktiviert!";
			redirectAttrs.addFlashAttribute("SuccessMessage", message);
		} catch (Exception e) {
			message = "Es ist ein Fehler aufgetreten!";
			redirectAttrs.addFlashAttribute("ErrorMessage", message);
			logger.error(e);
		}

		return "redirect:/precheck/bearbeiter/";

	}

	@RequestMapping(value = "/anschaltenEdit", method = RequestMethod.GET)
	public String activateEdit(ModelMap modelMap, @RequestParam("id") Long id) {

		Interessent object = interessentService.fetchById(id);
		object.setBearbeitungsdatum(null);
		object.setBearbeitet("n");

		try {
			interessentService.update(object);
			messageContainer.addSuccessKey("Fall würde erfolgreich aktiviert!");
		} catch (Exception e) {
			messageContainer.addErrorKey("Es ist ein Fehler aufgetreten ");
			logger.error(e);
		}

		return "precheck/bearbeiter/edit";

	}

	@InitBinder
	public void binder(WebDataBinder binder) {

		binder.registerCustomEditor(Date.class, new DatePropertyEditorSupport());
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(ModelMap modelMap, HttpServletResponse response, HttpServletRequest request,
			@RequestParam("id") Long id, RedirectAttributes redirectAttrs) {

		String message = "";
		try {
			interessentService.delete(id);
			message = "Datensatz wurde erfolgreich gelöscht!";
			redirectAttrs.addFlashAttribute("SuccessMessage", message);
			logger.debug("Datensatz wurde erfolgreich gelöscht!");
		} catch (Exception e) {
			message = "Es ist ein Fehler aufgetreten!";
			redirectAttrs.addFlashAttribute("ErrorMessage", message);
			logger.error(e);
		}

		return "redirect:/precheck/bearbeiter/";

	}

	@RequestMapping(value = "/doubles", method = RequestMethod.POST)
	public String doubleEntries() {

		return null;
	}

	/**
	 * Donload csv Data
	 *
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/downloadCSV")
	public void downloadCSV(HttpServletResponse response) throws IOException {

		response.setContentType("text/csv");
		String reportName = "Precheck-Auswertung.csv";
		response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		response.setCharacterEncoding("UTF-8");

		ArrayList<String> rows = new ArrayList<>();

		List<Interessent> interessentList = interessentService.findAll();
		logger.debug("Bewerber: " + interessentList.size());

		String id, vorname, nachname, birthDate, birthCountry, visitedUniversity, countryOfUniversity, degree,
				courseOfStudyPast, durationOfStudyPast, graduationYear, deNote, status, studiengangTUHH;

		// Header
		rows.add("Precheck-ID" + " ; " + "Nachname" + " ; " + "Vorname" + " ; " + "Geburtsdatum" + " ; " + "Geburtsort"
				+ " ; " + "Abschluss" + " ; " + "Besuchte Universität" + " ; " + "Land der Universität" + " ; "
				+ "Abschluss Jahr" + " ; " + "Studienlänge" + " ; " + "Abgeschlossener Studiengang" + " ; " + "DE-Note"
				+ " ; " + " ; " + "TUHH-Ausgewählter Studiengang" + " ; " + "Status");
		rows.add("\n");

		for (Interessent i : interessentList) {

			id = i.getId() + "";
			// Vorname
			vorname = i.getVorname();
			// Nachname
			nachname = i.getNachname();
			// Geb- Datum
			birthDate = formatDate(i.getGeburtsdatum());
			// Geb-land
			birthCountry = interessentService.findCountryNameById(i.getGeburtsland()).value1();
			// UNI Land
			countryOfUniversity = interessentService.findCountryNameById(i.getStudienland()).value1();
			// Uni
			visitedUniversity = i.getUni();
			// Abschluss
			degree = i.getAbschluss();
			// was hast du studiert?
			courseOfStudyPast = i.getAbgeschlossenesstd();
			// studiengangs- dauer
			durationOfStudyPast = i.getStdDauer().toString();
			// wann warst Du fertig?
			graduationYear = i.getAbschlussJahr().toString();
			// hast du 1 bekommen?
			deNote = i.getTuhhNote() + "";
			// Status-Name
			status = statusService.fetchById(i.getStatus()).getName();
			// Studiengang -TUHH
			studiengangTUHH = studiengangService.fetchByCoursOfStudyId(i.getStgTuhh()).getShorttext();

			// Die Daten
			rows.add(id + " ; " + nachname + " ; " + vorname + " ; " + birthDate + " ; " + birthCountry + " ; " + degree
					+ " ; " + visitedUniversity + " ; " + countryOfUniversity + " ; " + graduationYear + " ; "
					+ durationOfStudyPast + " ; " + courseOfStudyPast + " ; " + deNote + " ; " + " ; " + studiengangTUHH
					+ " ; " + status);
			rows.add("\n");
		}

		// csv schreiben
		Iterator<String> iter = rows.iterator();
		while (iter.hasNext()) {
			String outputString = iter.next();
			response.getOutputStream().write(outputString.getBytes("UTF-8"));

		}

		response.getOutputStream().flush();

	}

	@RequestMapping(value = "/edit")
	public String edit(ModelMap modelMap, @RequestParam("id") Long id) {

		Interessent interessent = interessentService.fetchById(id);

		modelMap.put("interessent", interessent);

		// datum formatieren
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(interessent.getGeburtsdatum().getTime());

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		GregorianCalendar cal = new GregorianCalendar(year, month, day);
		SimpleDateFormat format = new SimpleDateFormat(CalendarUtils.DATEFORMAT_GERMAN);
		String formatted = format.format(cal.getTime());

		modelMap.addAttribute("gebDatum", formatted);

		// studiengangsname
		Long stgId = interessent.getStgTuhh();
		String stgTuhh = studiengangService.findLabelNameById(stgId).value1();
		modelMap.addAttribute("stgTuhh", stgTuhh);

		// gender
		String gender = interessentService.findGenderNameById(interessent.getGeschlecht()).value1();
		modelMap.addAttribute("gender", gender);

		// geburtsland
		String gland = interessentService.findCountryNameById(interessent.getGeburtsland()).value1();
		modelMap.addAttribute("gland", gland);

		// studienland
		String stgland = interessentService.findCountryNameById(interessent.getStudienland()).value1();

		if (interessent.getInstitution() != null && !"".equals(interessent.getInstitution())) {
			stgland = stgland + " (" + interessent.getInstitution() + ")";
		}
		modelMap.addAttribute("stgland", stgland);

		// status
		String status = statusService.fetchById(interessent.getStatus()).getName();
		modelMap.addAttribute("status", status);

		// all statuses
		List<Status> statusList = statusService.findAll();
		modelMap.addAttribute("statusList", statusList);

		return "precheck/bearbeiter/edit";

	}

	// Mail sending works here!
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Long id,
			Long statusValue, RedirectAttributes redirectAttrs) {

		return sendEmailAndUpdate(id, statusValue, redirectAttrs);
	}

	private String formatDate(Date datum) {
		// datum formatieren
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(datum.getTime());

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		GregorianCalendar cal = new GregorianCalendar(year, month, day);
		SimpleDateFormat format = new SimpleDateFormat(CalendarUtils.DATEFORMAT_GERMAN);
		return format.format(cal.getTime());
	}

	@ModelAttribute("interessent")
	public Interessent getInteressent() {

		return new Interessent();
	}

	private TableField<PrecheckInteressentRecord, ?> getOrderCol(String order) {

		if (order == null) {
			return Tables.PRECHECK_INTERESSENT.ID;
		}

		switch (order) {
		case "nachname":
			return Tables.PRECHECK_INTERESSENT.NACHNAME;
		case "abschluss":
			return Tables.PRECHECK_INTERESSENT.ABSCHLUSS;
		case "abgeschlstg":
			return Tables.PRECHECK_INTERESSENT.ABGESCHLOSSENESSTD;
		case "denote":
			return Tables.PRECHECK_INTERESSENT.TUHH_NOTE;
		case "bewdat":
			return Tables.PRECHECK_INTERESSENT.BEWERBUNGSDATUM;
		}

		return Tables.PRECHECK_INTERESSENT.ID;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String interessentenListe(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("SuccessMessage") String sMessage, @ModelAttribute("ErrorMessage") String eMessage,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "elem", required = false) String elementList,
			@RequestParam(value = "doubles", required = false) boolean doubles,
			@RequestParam(value = "doubles_mail", required = false) boolean doubles_mail) {

		logger.debug("------Interessenten Liste aufgerufen-------");

		model.put("interessentenListe", interessentService.findAll());
		model.put("countries", countryService.findAllAscending());
		model.put("statuses", statusService.findAll());
		model.put("studies", studiengangService.findByAktiv());

		Filter filter = cookieUtils.getCookieFilter(response, request);

		model.put("selectedElements", filter.getElementList());

		// aktive seite
		int currentPage;
		if (null == page) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(page);
		}

		List<ExtendedInteressent> dataList = interessentService.findAllFilteredExtended(filter.getCountryValue(),
				filter.getStatusValue(), filter.getStudyValue(), filter.getDoneValue(), null, filter.getSearch(),
				filter.getElementList(), currentPage, getOrderCol(filter.getOrder()), filter.isDoubles(),
				filter.isDoublesMail());
		List<ExtendedInteressent> counterList = interessentService.findAllFilteredExtended(filter.getCountryValue(),
				filter.getStatusValue(), filter.getStudyValue(), filter.getDoneValue(), null, filter.getSearch(), 0, 1,
				getOrderCol(filter.getOrder()), filter.isDoubles(), filter.isDoublesMail());

		for (ExtendedInteressent ii : dataList) {
			if (ii.getInstitution() != null && !"".equals(ii.getInstitution())) {
				ii.setStudyCountry(ii.getStudyCountry() + " (" + ii.getInstitution() + ")");
			}
		}

		model.put("data", dataList);

		int noOfPages = (int) Math.ceil(counterList.size() * 1.0 / filter.getElementList());

		model.put("selectedElements", filter.getElementList());
		model.put("filter", filter);
		model.put("counterAll", interessentService.getCount());
		model.put("counter", dataList.size());
		model.put("noOfPages", noOfPages);
		model.put("currentPage", currentPage);

		if (!sMessage.equals("")) {
			messageContainer.addSuccessKey(sMessage);
		} else if (!eMessage.equals("")) {
			messageContainer.addErrorKey(eMessage);
		}

		return "precheck/bearbeiter/interessenten_liste";

	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String interessentenListe(ModelMap model, HttpServletResponse response, HttpServletRequest request,
			@RequestParam("countryList") String countryId, @RequestParam("statusList") String statusId,
			@RequestParam("studyList") String stgId, @RequestParam("doneList") String done,
			@RequestParam(value = "search", required = false) String search, @RequestParam("order") String order,
			@RequestParam("elementList") int elementList,
			@RequestParam(value = "doubles", required = false) boolean doubles,
			@RequestParam(value = "doubles_mail", required = false) boolean doubles_mail) {

		Filter filter = new Filter();
		filter.setCountryValue(countryId);
		filter.setStatusValue(statusId);
		filter.setStudyValue(stgId);
		filter.setDoneValue(done);
		filter.setElementList(elementList);
		filter.setSearch(search);
		filter.setOrder(order);
		filter.setDoubles(doubles);
		filter.setDoublesMail(doubles_mail);

		int currentPage = 1;

		cookieUtils.setCookieValues("precheck_filter", filter.toString(), response);

		List<ExtendedInteressent> dataList = interessentService.findAllFilteredExtended(countryId, statusId, stgId,
				done, null, search, elementList, currentPage, getOrderCol(order), doubles, doubles_mail);

		for (ExtendedInteressent ii : dataList) {
			if (ii.getInstitution() != null && !"".equals(ii.getInstitution())) {
				ii.setStudyCountry(ii.getStudyCountry() + " (" + ii.getInstitution() + ")");
			}
		}

		model.put("data", dataList);

		int counter = interessentService.findAllFilteredExtended(countryId, statusId, stgId, done, null, search, 0, 1,
				getOrderCol(order), doubles, doubles_mail).size();

		int noOfPages = (int) Math.ceil(counter * 1.0 / elementList);

		model.put("selectedElements", elementList);
		model.put("noOfPages", noOfPages);
		model.put("currentPage", currentPage);

		model.put("counter", counter);
		model.put("counterAll", interessentService.getCount());
		model.put("filter", filter);
		model.put("countries", countryService.findAllAscending());
		model.put("statuses", statusService.findAll());
		model.put("studies", studiengangService.findByAktiv());

		return "precheck/bearbeiter/interessenten_liste";
	}

	/**
	 * Mail verschicken und status bei Interessent obj. setzen / update
	 *
	 * @param id
	 * @param status
	 * @param redirectAttrs
	 * @return
	 */
	private String sendEmailAndUpdate(Long id, Long status, RedirectAttributes redirectAttrs) {

		String message = "";
		Interessent interessent = interessentService.fetchById(id);

		try {

			String statusName = statusService.fetchById(status).getName();
			interessent.setStatus(status);
			interessentService.sendErgebnisMail(statusName, interessent.getEmail(), "no-reply@tuhh.de", interessent);
			interessent.setBearbeitungsdatum(new Date());
			interessent.setBearbeitet("y");

			if (!"AKZEPTIERT".equals(statusName) && !"FRAGLICH".equals(statusName)) {
				interessent.setVerifierCode(null);
			} else {
				String ziffer = interessentService.getZiffer(interessent);
				interessent.setVerifierCode(ziffer);
			}

			interessentService.update(interessent);

			// Erfolg melden
			message = "Datensatz wurde erfolgreich bearbeitet!";
			redirectAttrs.addFlashAttribute("SuccessMessage", message);
		} catch (Exception e) {
			logger.error("Interessent konnte nicht bearbeitet werden: " + interessent, e);
			logger.error("Fehler! E-Mail kann nicht an " + interessent.getEmail() + " versendet werden!");
			message = "Fehler! E-Mail kann nicht an " + interessent.getEmail() + " versendet werden!";
			redirectAttrs.addFlashAttribute("ErrorMessage", message);
		}

		return "redirect:/precheck/bearbeiter/";
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public String sendMail(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			@RequestParam("id") Long id, @RequestParam("status") Long status, RedirectAttributes redirectAttrs) {

		System.out.println("SendMail ID: " + id + " STATUS: " + status);
		return sendEmailAndUpdate(id, status, redirectAttrs);

	}

}
