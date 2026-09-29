package de.tuhh.tusp.exa.pvsb.controllers;

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
import de.tuhh.tusp.exa.pvsb.pojo.Mail;
import de.tuhh.tusp.exa.pvsb.pojo.Pruefervertreter;
import de.tuhh.tusp.exa.pvsb.service.PruefervertreterService;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.BaseCrudController;
import de.tuhh.tusp.utils.LdapDao;
import de.tuhh.tusp.utils.MailUtils;

@Controller
@RequestMapping(value = "/pvsb/admin")
@SessionAttributes("pruefervertreter")
@AccessConfig(path = "/pvsb/admin/**", access = { Rollen.ADMIN })
public class PruefervertreterAdminController extends BaseCrudController<Pruefervertreter> {

	private static Logger logger = LogManager.getLogger(PruefervertreterAdminController.class);

	@Autowired
	private PruefervertreterService pruefervertreterService;

	@Autowired
	private AuthorityUtils authUtils;

	@Autowired
	private MailUtils mailer;

	@Autowired
	private ConfigService config;

	@Autowired
	private LdapDao ldap;

	@Autowired
	protected PruefervertreterAdminController(PruefervertreterService service) {

		super(service, Pruefervertreter.class, "pvsb/admin/");
	}

	/**
	 * Startseite der Adminverwaltung für Prüfervertreter-SB
	 *
	 * @return Startseite
	 **/
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(ModelMap model) {

		model.put("pruefervertreter", pruefervertreterService.findAllRequests());

		return "pvsb/admin/start";
	}

	@RequestMapping(value = { "/mail" }, method = RequestMethod.GET)
	public String mail(ModelMap model, Long id) {

		Pruefervertreter pruefervertreter = pruefervertreterService.fetchById(id);
		Mail mail = new Mail();
		mail.setTo(pruefervertreter.getExaminer_email());
		mail.setCc(pruefervertreter.getRep_email());

		model.put("pruefervertreterId", pruefervertreter.getId());
		model.put("pruefervertreterExName", pruefervertreter.getExaminer_name());
		model.put("pruefervertreterRepName",
				pruefervertreter.getRep_firstname() + " " + pruefervertreter.getRep_surname());
		model.put("mail", mail);

		return "pvsb/admin/mail";
	}

	@RequestMapping(value = { "/done" }, method = RequestMethod.POST)
	public String mailSent(ModelMap model, Long id, Mail mail) {

		String from = config.get("de.tuhh.tusp.exa.pvsb", "no-reply@tuhh.de", true);
		Pruefervertreter pruefervertreter = pruefervertreterService.fetchById(id);
		pruefervertreter.setMailsent(true);

		try {
			mailer.sendHTMLMailCC(mail.getTo(), mail.getCc(), from, mail.getSubject(), mail.getBody());
			pruefervertreterService.update(pruefervertreter);

			return "pvsb/admin/done";
		} catch (AddressException e) {
			logger.error("Konnte Mail nicht senden, Adresse falsch - " + e);

			return "pvsb/admin/error";
		} catch (MessagingException e) {
			logger.error("Konnte Mail nicht senden - " + e);

			return "pvsb/admin/error";
		}

	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/show")
	public String save(ModelMap model, @RequestParam Long id, Pruefervertreter pruefervertreter) {

		try {
			pruefervertreter.setProcessor(authUtils.getCurrentUserName());
			pruefervertreter.setTimestamp_process(new Timestamp(System.currentTimeMillis()));
			pruefervertreterService.update(pruefervertreter);
			messageContainer.addSuccessKey("success.update");
		} catch (Exception e) {
			logger.error("Update Pruefervertreter fehlgeschlagen!", e);
			messageContainer.addErrorKey("error.generic");
		}

		return show(model, id);
	}

	@Override
	public String show(ModelMap model, Long id) {

		Pruefervertreter pruefervertreter = pruefervertreterService.fetchById(id);
		model.put("pruefervertreter", pruefervertreter);

		if (pruefervertreter.getApplicant() == null
				|| !pruefervertreter.getApplicant().equals(pruefervertreter.getExaminer_account())) {
			messageContainer.addWarningKey("psvb.applicant_is_not_examiner", pruefervertreter.getApplicant());
		}

		try {
			ldap.initContextWithDn(config.get("de.tuhh.tusp.utils.ldap.userDn", "", true),
					config.get("de.tuhh.tusp.utils.ldap.password", "", true));

			SearchResult res = ldap.getLdapPerson("mail=" + pruefervertreter.getRep_email());
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
