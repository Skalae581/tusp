package de.tuhh.tusp.exa.lvsb.controllers;

import java.util.List;

import javax.naming.directory.SearchResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.exa.lvsb.pojo.Lehrvertreter;
import de.tuhh.tusp.exa.lvsb.service.LehrvertreterService;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.LdapDao;
import de.tuhh.tusp.utils.MessageContainer;

@Controller
@RequestMapping(value = "/lvsb")
@SessionAttributes("lehrvertreter")
@AccessConfig(path = "/lvsb/**", access = { Rollen.LEHRENDER, Rollen.MITARBEITER, Rollen.ADMIN })
public class LehrvertreterUserController {

	private static Logger logger = LogManager.getLogger(LehrvertreterUserController.class);

	@Autowired
	private LehrvertreterService lehrvertreterService;

	@Autowired
	private AuthorityUtils authUtils;

	@Autowired
	private LdapDao ldap;

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private ConfigService configService;

	/**
	 * Seite zum Bestätigen der eingegebenen Formularangaben
	 *
	 * @return Übertragungsergebnis
	 **/
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(ModelMap mdl, Model model, Lehrvertreter lehrvertreter) {

		logger.debug("lehrvertreter: " + lehrvertreter);
		model.addAttribute("lehrvertreter", lehrvertreter);

		mdl.put("rolle", isOnlyEmployee());

		if (!lehrvertreter.getRep_email().endsWith("@tuhh.de")) {
			messageContainer.addWarningKey("pvsb.email.domain_error");
		}

		SearchResult res = ldap.getLdapPerson("mail=" + lehrvertreter.getRep_email());
		if (res == null) {
			messageContainer.addWarningKey("pvsb.email.not_found");
		}
		ldap.closeContext();

		return "lvsb/user/confirm";
	}

	/**
	 * Letzte Seite der Prüfervertreter-SB
	 *
	 * @return Meldung, ob Prüfervertreter:in angelegt bzw. Antrag eingegangen
	 **/
	@RequestMapping(value = { "/done" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String end(Model model, ModelMap mdl, Lehrvertreter lehrvertreter, SessionStatus status) {

		try {

			lehrvertreter.setApplicant(authUtils.getCurrentUserName());
			if (isOnlyEmployee() && !lehrvertreter.getReason().isEmpty()) {
				lehrvertreter.setEmployee(true);
			} else if (isOnlyEmployee() && lehrvertreter.getReason().isEmpty()) {

				return "lvsb/user/errorReason";
			} else {
				lehrvertreter.setEmployee(false);
			}

			lehrvertreterService.insert(lehrvertreter);

			status.setComplete();

			return "lvsb/user/done";

		} catch (Exception e) {
			logger.error("Konnte die Prüfervertreter-Daten nicht in DB schreiben.", e);
			return "lvsb/user/error";
		}
	}

	private Boolean isOnlyEmployee() {

		List<String> userRollen = authUtils.getRoles();

		long lehrperson = userRollen.stream().filter(s -> s.contains("ROLE_LEHRENDER")).count();
		long employee = userRollen.stream().filter(s -> s.startsWith("ROLE_EMPLOYEE")).count();

		if (lehrperson == 0 && employee >= 1) {
			return true;
		}

		return false;
	}

	/**
	 * Startseite der Prüfervertreter-SB
	 *
	 * @return Startseite
	 **/
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String start(Model model, ModelMap mdl) {

		Lehrvertreter lehrvertreter = new Lehrvertreter();
		model.addAttribute("lehrvertreter", lehrvertreter);

		return start(model, mdl, lehrvertreter);
	}

	/**
	 * Startseite der Prüfervertreter-SB, weitergeleitet von /confirm
	 *
	 * @return Startseite
	 **/
	@RequestMapping(value = { "/start" }, method = RequestMethod.POST)
	public String start(Model model, ModelMap mdl, Lehrvertreter lehrvertreter) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (lehrvertreter.getTeacher_account() == null) {
			lehrvertreter.setTeacher_account(authentication.getName());
		}

		if (lehrvertreter.getTeacher_email() == null || lehrvertreter.getTeacher_name() == null) {
			SearchResult res = ldap.getLdapPerson("uid=" + authentication.getName());
			if (lehrvertreter.getTeacher_email() == null) {
				lehrvertreter.setTeacher_email(ldap.getStringAttribute(res, "mail"));
			}
			if (lehrvertreter.getTeacher_name() == null) {
				lehrvertreter.setTeacher_name(ldap.getStringAttribute(res, "displayName"));
			}
			ldap.closeContext();
		}

		mdl.put("rolle", isOnlyEmployee());

		return "lvsb/user/start";
	}
}
