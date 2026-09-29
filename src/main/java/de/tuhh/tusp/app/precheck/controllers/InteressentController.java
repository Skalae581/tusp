package de.tuhh.tusp.app.precheck.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import de.tuhh.tusp.app.precheck.pojo.Interessent;
import de.tuhh.tusp.app.precheck.pojo.Studiengang;
import de.tuhh.tusp.app.precheck.service.InteressentService;
import de.tuhh.tusp.app.precheck.service.StudiengangService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.dao.ConfigDao;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.DatePropertyEditorSupport;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.MessageContainer;
import de.tuhh.tusp.webservice.sync.pojo.Country;
import de.tuhh.tusp.webservice.sync.pojo.Gender;
import de.tuhh.tusp.webservice.sync.service.CountryService;
import de.tuhh.tusp.webservice.sync.service.GenderService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

@Controller
@RequestMapping(value = "/precheck")
@SessionAttributes("interessent")
@AccessConfig(path = "/precheck/**", access = { Rollen.PUBLIC })
public class InteressentController {

	private static Logger logger = LogManager.getLogger(InteressentController.class);

	@Autowired
	private InteressentService interessentService;

	@Autowired
	private StudiengangService studiengangService;

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private CountryService countryService;

	@Autowired
	private GenderService genderService;

	@Autowired
	private MailUtils mailUtils;

	@Autowired
	private ConfigDao configDao;

	@InitBinder
	public void binder(WebDataBinder binder) {

		binder.registerCustomEditor(Date.class, new DatePropertyEditorSupport());
	}

	/**
	 * schauen ob Browser-Vresion veraltet ist
	 *
	 * @param userAgentHeaderValue
	 * @return
	 */
	public boolean browserSupported(UserAgent userAgent) {

		if (null == userAgent) {
			return false;
		}

		Version safariSupport = new Version("9.1.3", null, null);
		Version operaSupport = new Version("42", null, null);
		Version chromeSupport = new Version("50.0.2661", null, null);
		Version firefoxSupport = new Version("51.0.1", null, null);

		System.out.println("Current Version: " + userAgent.getBrowserVersion());

		if (userAgent.getBrowser().getGroup() == Browser.FIREFOX
				&& userAgent.getBrowserVersion().compareTo(firefoxSupport) >= 0) {
			System.out.println("Version Supported");
			return true;
		}
		if (userAgent.getBrowser().getGroup() == Browser.CHROME
				&& userAgent.getBrowserVersion().compareTo(chromeSupport) >= 0) {
			return true;
		}
		if (userAgent.getBrowser().getGroup() == Browser.OPERA
				&& userAgent.getBrowserVersion().compareTo(operaSupport) >= 0) {
			return true;
		}

		if (userAgent.getOperatingSystem() == OperatingSystem.WINDOWS) {
			if (userAgent.getBrowser().getGroup() == Browser.SAFARI
					&& userAgent.getBrowserVersion().compareTo(safariSupport) >= 0) {
				return true;
			}
		} else if (userAgent.getOperatingSystem() == OperatingSystem.MAC_OS
				&& userAgent.getOperatingSystem() == OperatingSystem.MAC_OS_X) {
			if (userAgent.getBrowser().getGroup() == Browser.SAFARI
					&& userAgent.getBrowserVersion().compareTo(safariSupport) >= 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * guck ob precheck aktiv/inaktiv ist
	 *
	 * @return
	 * @throws ParseException
	 */
	private boolean checkperiod(HttpServletRequest request) throws ParseException {

		String disableAllFrom = configDao.fetchByKey("de.tuhh.tusp.app.precheck.disableAllFrom").getValue();

		String allowAllUntil = configDao.fetchByKey("de.tuhh.tusp.app.precheck.allowAllUntil").getValue();

		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		if (CalendarUtils.isPast(disableAllFrom)) {
			return false;
		}
		if (CalendarUtils.isFuture(allowAllUntil)) {
			return true;
		}
		return false;

	}

	@RequestMapping("/end_page")
	public String endPage(@ModelAttribute Interessent interessent, SessionStatus status, HttpServletRequest request)
			throws ParseException {

		if (checkperiod(request)) {
			try {

				// Daten speichern
				interessent.setBewerbungsdatum(new Date());
				interessent.setBearbeitet("n");

				interessentService.insert(interessent);

				try {
					mailUtils.sendHTMLMail(interessent.getEmail(), "no-reply@tuhh.de",
							messageContainer.getString("precheck.subject.empf_bestaetigung"),
							messageContainer.getString("precheck.body.empf_bestaetigung"));
				} catch (Exception e) {
					messageContainer.addErrorKey("precheck.empf_bestaetigung_fail");
				}

				// Daten vergessen
				status.setComplete();

				// Erfolg melden
				messageContainer.addSuccessKey("precheck.submit_success");
			} catch (Exception e) {
				logger.error("Interessent konnte nicht gespeichert werden: " + interessent, e);
				messageContainer.addErrorKey("precheck.submit_fail");
			}
			return "precheck/forms/end_page";
		} else {
			return "precheck/forms/offline_page";
		}

	}

	@RequestMapping(value = "/personal_details")
	public String form1(@ModelAttribute Interessent interessent, ModelMap model, HttpServletRequest request)
			throws ParseException {

		if (checkperiod(request)) {
			getBrowserMessage(request);

			Map<Long, String> genders = new HashMap<>();
			for (Gender gender : genderService.findAll()) {

				if (LocaleContextHolder.getLocale().equals(Locale.GERMAN)) {
					genders.put(gender.getId(), gender.getDefaulttextDe());
				} else {
					genders.put(gender.getId(), gender.getDefaulttextEn());
				}

			}
			model.addAttribute("genders", genders);

			Map<Long, String> countries = new LinkedHashMap<>();
			for (Country c : countryService.findAllAscending()) {
				countries.put(c.getId(), c.getDefaulttextEn());
			}

			model.addAttribute("countries", countries);

			logger.debug(interessent);
			return "precheck/forms/personal_details";
		} else {
			return "precheck/forms/offline_page";
		}

	}

	@RequestMapping(value = "/study_details")
	public String form2(@Validated(Interessent.PersonDetails.class) @ModelAttribute Interessent interessent,
			BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws ParseException {

		if (checkperiod(request)) {
			getBrowserMessage(request);

			// Wenn Probleme auftreten zurück zum vorigen Formular
			if (bindingResult != null && bindingResult.hasErrors()) {
				messageContainer.addBindingResult("precheck.", bindingResult);
				return form1(interessent, model, request);
			}

			// studiengänge
			Map<Long, String> studiengaenge = new HashMap<>();
			Studiengang studiengang = new Studiengang();

			for (Map<String, Object> map : studiengangService.findLabelNames()) {

				studiengang.setShorttext(map.get("defaulttext_de").toString());
				studiengang.setCourseofstudyId((Long) map.get("courseofstudy_id"));
				studiengang.setId((Long) map.get("id"));

				studiengaenge.put(studiengang.getCourseofstudyId(), studiengang.getShorttext());

			}

			model.put("studiengaenge", studiengaenge);

			// länder
			Map<Long, String> countries = new LinkedHashMap<>();
			for (Country c : countryService.findAllAscending()) {
				if (LocaleContextHolder.getLocale().equals(Locale.GERMAN)) {
					countries.put(c.getId(), c.getDefaulttextDe());
				} else {
					countries.put(c.getId(), c.getDefaulttextEn());
				}

			}
			model.addAttribute("countries", countries);

			logger.debug(interessent);
			return "precheck/forms/study_details";
		} else {
			return "precheck/forms/offline_page";
		}

	}

	@RequestMapping(value = "/overview_page")
	public String form3(@Validated(Interessent.StudyDetails.class) @ModelAttribute Interessent interessent,
			BindingResult bindingResult, ModelMap model, HttpServletRequest request) throws ParseException {

		if (checkperiod(request)) {
			getBrowserMessage(request);

			// Wenn Probleme auftreten zurück zum vorigen Formular
			if (bindingResult != null && bindingResult.hasErrors()) {
				messageContainer.addBindingResult("precheck.", bindingResult);
				return form2(interessent, null, model, request);
			}

			// datum formatieren
			Calendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(interessent.getGeburtsdatum().getTime());

			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			GregorianCalendar cal = new GregorianCalendar(year, month, day);
			SimpleDateFormat format = new SimpleDateFormat(CalendarUtils.DATEFORMAT_GERMAN);
			String formatted = format.format(cal.getTime());

			model.addAttribute("gebDatum", formatted);

			// studiengangsname
			Long stgId = interessent.getStgTuhh();
			String stgTuhh = studiengangService.findLabelNameById(stgId).value1();
			model.addAttribute("stgTuhh", stgTuhh);

			// gender
			String gender = interessentService.findGenderNameById(interessent.getGeschlecht()).value1();
			model.addAttribute("gender", gender);

			// geburtsland
			String gland = interessentService.findCountryNameById(interessent.getGeburtsland()).value1();
			model.addAttribute("gland", gland);

			// geburtsland
			String stgland = interessentService.findCountryNameById(interessent.getStudienland()).value1();
			model.addAttribute("stgland", stgland);

			// institution
			String institution = interessent.getInstitution();
			model.addAttribute("institution", institution);

			logger.debug(interessent);
			return "precheck/forms/overview_page";
		} else {
			return "precheck/forms/offline_page";
		}

	}

	private void getBrowserMessage(HttpServletRequest request) {

		// Ist Version veraltet
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		String version = userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion();
		boolean supported = browserSupported(userAgent);

		String message = "";

		if (!supported) {
			message = "You are using a version of the Browser " + version
					+ " that is not supportet anymore. You may not be able to use some functions due to your browser version. Please update your browser or use another one in case that some of the functions do not work propertly.";

			messageContainer.addErrorKey(message);
		}

	}

	@ModelAttribute("interessent")
	public Interessent getInteressent() {

		return new Interessent();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) throws ParseException {

		if (checkperiod(request)) {
			getBrowserMessage(request);
			return "precheck/forms/start_page";
		} else {
			return "precheck/forms/offline_page";
		}

	}

}
