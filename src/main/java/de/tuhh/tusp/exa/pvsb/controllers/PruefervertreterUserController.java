package de.tuhh.tusp.exa.pvsb.controllers;

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
import de.tuhh.tusp.exa.pvsb.pojo.Pruefervertreter;
import de.tuhh.tusp.exa.pvsb.service.PruefervertreterService;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.LdapDao;
import de.tuhh.tusp.utils.MessageContainer;

@Controller
@RequestMapping(value = "/pvsb")
@SessionAttributes("pruefervertreter")
@AccessConfig(path = "/pvsb/**", access = { Rollen.PRUEFER, Rollen.ADMIN })
public class PruefervertreterUserController {

	private static Logger logger = LogManager.getLogger(PruefervertreterUserController.class);

	@Autowired
	private PruefervertreterService pruefervertreterService;

	@Autowired
	private AuthorityUtils authUtils;

	@Autowired
	private LdapDao ldap;

	@Autowired
	private MessageContainer messageContainer;

	/**
	 * Seite zum Bestätigen der eingegebenen Formularangaben
	 *
	 * @return Übertragungsergebnis
	 **/
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String confirm(ModelMap mdl, Model model, Pruefervertreter pruefervertreter) {

		logger.debug("pruefervertreter: " + pruefervertreter);
		model.addAttribute("pruefervertreter", pruefervertreter);

		if (!pruefervertreter.getRep_email().endsWith("@tuhh.de")) {
			messageContainer.addWarningKey("pvsb.email.domain_error");
		}

		SearchResult res = ldap.getLdapPerson("mail=" + pruefervertreter.getRep_email());
		if (res == null) {
			messageContainer.addWarningKey("pvsb.email.not_found");
		}
		ldap.closeContext();

		return "pvsb/user/confirm";
	}

	/**
	 * Letzte Seite der Prüfervertreter-SB
	 *
	 * @return Meldung, ob Prüfervertreter:in angelegt bzw. Antrag eingegangen
	 **/
	@RequestMapping(value = { "/done" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String end(Model model, ModelMap mdl, Pruefervertreter pruefervertreter, SessionStatus status) {

		try {
			pruefervertreter.setApplicant(authUtils.getCurrentUserName());
			pruefervertreterService.insert(pruefervertreter);

			status.setComplete();

			return "pvsb/user/done";
		} catch (Exception e) {
			logger.error("Konnte die Prüfervertreter-Daten nicht in DB schreiben.", e);
			return "pvsb/user/error";
		}
	}

	/**
	 * Startseite der Prüfervertreter-SB
	 *
	 * @return Startseite
	 **/
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String start(Model model, ModelMap mdl) {

		Pruefervertreter pruefervertreter = new Pruefervertreter();
		model.addAttribute("pruefervertreter", pruefervertreter);

		return start(model, mdl, pruefervertreter);
	}

	/**
	 * Startseite der Prüfervertreter-SB, weitergeleitet von /confirm
	 *
	 * @return Startseite
	 **/
	@RequestMapping(value = { "/start" }, method = RequestMethod.POST)
	public String start(Model model, ModelMap mdl, Pruefervertreter pruefervertreter) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (pruefervertreter.getExaminer_account() == null) {
			pruefervertreter.setExaminer_account(authentication.getName());
		}

		if (pruefervertreter.getExaminer_email() == null || pruefervertreter.getExaminer_name() == null) {
			SearchResult res = ldap.getLdapPerson("uid=" + authentication.getName());
			if (pruefervertreter.getExaminer_email() == null) {
				pruefervertreter.setExaminer_email(ldap.getStringAttribute(res, "mail"));
			}
			if (pruefervertreter.getExaminer_name() == null) {
				pruefervertreter.setExaminer_name(ldap.getStringAttribute(res, "displayName"));
			}
			ldap.closeContext();
		}

		return "pvsb/user/start";
	}
}
