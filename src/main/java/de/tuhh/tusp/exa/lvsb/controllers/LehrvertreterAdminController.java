package de.tuhh.tusp.exa.lvsb.controllers;

import java.sql.Timestamp;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.exa.lvsb.pojo.Lehrvertreter;
import de.tuhh.tusp.exa.lvsb.service.LehrvertreterService;
import de.tuhh.tusp.exa.pvsb.pojo.Mail;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.BaseCrudController;
import de.tuhh.tusp.utils.LdapDao;
import de.tuhh.tusp.utils.MailUtils;

@Controller
@RequestMapping(value = "/lvsb/admin")
@SessionAttributes("lehrvertreter")
@AccessConfig(path = "/lvsb/admin/**", access = { Rollen.ADMIN })
public class LehrvertreterAdminController extends BaseCrudController<Lehrvertreter> {

	private static Logger logger = LogManager.getLogger(LehrvertreterAdminController.class);

	@Autowired
	private LehrvertreterService lehrvertreterService;

	@Autowired
	private AuthorityUtils authUtils;

	@Autowired
	private MailUtils mailer;

	@Autowired
	private ConfigService config;

	@Autowired
	private LdapDao ldap;

	@Autowired
	protected LehrvertreterAdminController(LehrvertreterService service) {

		super(service, Lehrvertreter.class, "lvsb/admin/");
	}

	/**
	 * Startseite der Adminverwaltung für Lehrvertreter-SB
	 *
	 * @return Startseite
	 **/
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(ModelMap model) {

		model.put("lehrvertreter", lehrvertreterService.findAllRequests());

		return "lvsb/admin/start";
	}

	@RequestMapping(value = { "/mail" }, method = RequestMethod.GET)
	public String mail(ModelMap model, Long id) {

		Lehrvertreter lehrvertreter = lehrvertreterService.fetchById(id);
		Mail mail = new Mail();
		mail.setTo(lehrvertreter.getTeacher_email());
		mail.setCc(lehrvertreter.getRep_email());

		model.put("lehrvertreterId", lehrvertreter.getId());
		model.put("lehrvertreterTeaName", lehrvertreter.getTeacher_name());
		model.put("lehrvertreterRepName", lehrvertreter.getRep_firstname() + " " + lehrvertreter.getRep_surname());
		model.put("mail", mail);

		return "lvsb/admin/mail";
	}

	@RequestMapping(value = { "/done" }, method = RequestMethod.POST)
	public String mailSent(ModelMap model, Long id, Mail mail) {

		String from = config.get("de.tuhh.tusp.exa.pvsb", "no-reply@tuhh.de", true);
		Lehrvertreter lehrvertreter = lehrvertreterService.fetchById(id);
		lehrvertreter.setMailsent(true);

		try {
			mailer.sendHTMLMailCC(mail.getTo(), mail.getCc(), from, mail.getSubject(), mail.getBody());
			lehrvertreterService.update(lehrvertreter);

			return "lvsb/admin/done";
		} catch (AddressException e) {
			logger.error("Konnte Mail nicht senden, Adresse falsch - " + e);

			return "lvsb/admin/reasonError";
		} catch (MessagingException e) {
			logger.error("Konnte Mail nicht senden - " + e);

			return "lvsb/admin/error";
		}

	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/show")
	public String save(ModelMap model, @RequestParam Long id, Lehrvertreter lehrvertreter) {

		try {
			lehrvertreter.setProcessor(authUtils.getCurrentUserName());
			lehrvertreter.setTimestamp_process(new Timestamp(System.currentTimeMillis()));
			lehrvertreterService.update(lehrvertreter);
			messageContainer.addSuccessKey("success.update");
		} catch (Exception e) {
			logger.error("Update Pruefervertreter fehlgeschlagen!", e);
			messageContainer.addErrorKey("error.generic");
		}

		return show(model, id);
	}

	@Override
	public String show(ModelMap model, Long id) {

		Lehrvertreter lehrvertreter = lehrvertreterService.fetchById(id);
		model.put("lehrvertreter", lehrvertreter);

		if (lehrvertreter.getApplicant() == null
				|| !lehrvertreter.getApplicant().equals(lehrvertreter.getTeacher_account())) {
			messageContainer.addWarningKey("lsvb.applicant_is_not_examiner", lehrvertreter.getApplicant());
		}

		try {
			ldap.initContextWithDn(config.get("de.tuhh.tusp.utils.ldap.userDn", "", true),
					config.get("de.tuhh.tusp.utils.ldap.password", "", true));

			SearchResult res = ldap.getLdapPerson("mail=" + lehrvertreter.getRep_email());
			if (res == null) {
				messageContainer.addErrorKey("psvb.ldap.user_not_found");
			} else {
				HashMap<String, String> ldapResult = new HashMap<>();
				ldapResult.put("displayName", ldap.getStringAttribute(res, "displayName"));
				ldapResult.put("uid", ldap.getStringAttribute(res, "uid"));
				ldapResult.put("ou", ldap.getStringAttribute(res, "ou"));
				ldapResult.put("hhEduPersonDateOfBirth", ldap.getStringAttribute(res, "hhEduPersonDateOfBirth"));
				model.addAttribute("ldapResult", ldapResult);
			}
			ldap.closeContext();
		} catch (NamingException e) {
			logger.error("Error bei LDAP Init", e);
			messageContainer.addErrorKey("psvb.ldap.error");
		}

		return super.show(model, id);
	}
}
