package de.tuhh.tusp.exa.massmails.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.his.ws.accountservice.CompleteAccount;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.exa.massmails.pojo.MailErrors;
import de.tuhh.tusp.exa.massmails.pojo.MailProtokoll;
import de.tuhh.tusp.exa.massmails.pojo.Receiver;
import de.tuhh.tusp.exa.massmails.service.MailErrorsService;
import de.tuhh.tusp.exa.massmails.service.MailProtokollService;
import de.tuhh.tusp.exa.massmails.service.ReceiverService;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.dao.ConfigDao;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MailUtils;
import de.tuhh.tusp.utils.XssFilter;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;

@Controller
@RequestMapping(value = "/massmails/")
@AccessConfig(path = "/massmails/**", access = { Rollen.MASSENMAIL })
public class MailsController {

	private static Logger logger = LogManager.getLogger(MailsController.class);

	@Autowired
	private ReceiverService recevierService;
	@Autowired
	private MailProtokollService mailProtokollService;
	@Autowired
	private MailUtils mailUtils;
	@Autowired
	private AuthorityUtils authorityUtils;
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private HisRoleHelperDao roleHelperDao;
	@Autowired
	private HisAccountHelperDao hisAccountHelperDao;
	@Autowired
	private RightsAndRolesDao rightsAndRolesDao;
	@Autowired
	private MailErrorsService mailErrorsService;

	@Autowired
	public MailsController() {

		XssFilter.disableFor("/massmails/sendMail/");
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(ModelMap model, @ModelAttribute MailProtokoll mailProtokoll, HttpServletRequest request,
			HttpServletResponse response) {

		model.addAttribute("mailProtokoll", mailProtokoll.getReceiver());

		return "massmails/confirm";
	}

	/**
	 * Donload csv Data
	 *
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendMail/downloadEmailCSV", method = RequestMethod.GET)
	public void downloadCSV(HttpServletResponse response, HttpServletRequest request) throws IOException {

		String uid = request.getParameter("uid");

		response.setContentType("text/csv");
		String reportName = LocalDate.now().toString() + "-Massen-Mails.csv";
		response.setHeader("Content-disposition", "attachment;filename=" + reportName);
		response.setCharacterEncoding("UTF-8");

		ArrayList<String> rows = new ArrayList<>();

		List<MailErrors> mailErrorsList = mailErrorsService.findForUid(uid);

		// Header
		rows.add("Status Name" + " ; " + "E-Mail Datum" + " ; " + "Vorname" + " ; " + "Nachname" + " ; "
				+ "Zusammenfassung");
		rows.add("\n");

		for (MailErrors mail : mailErrorsList) {

			// Die Daten
			rows.add(mail.getErrorname() + " ; " + mail.getMaildatum() + " ; " + mail.getVorname() + " ; "
					+ mail.getNachname() + " ; " + mail.getMessage());
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model, @ModelAttribute MailProtokoll mailProtokoll, HttpServletRequest request,
			HttpServletResponse response) {

		model.addAttribute("verteiler", recevierService.getVerteiler());
		model.addAttribute("recevier", mailProtokoll);

		String body = "<p>Sehr geehrte Damen und Herren,</p><p></p><p> "
				+ "</p><p></p><p></p><p></p><p></p><p>Mit freundlichen Grüßen,</p><p></p>";

		mailProtokoll.setBody(body);
		model.addAttribute("body", mailProtokoll.getBody());
		model.addAttribute("replyto", "servicedesk+tune@tuhh.de");

		return "massmails/massmails";
	}

	/**
	 * ist die angefragte Rolle abgelaufen?
	 *
	 * @param person
	 * @param roleName
	 * @return
	 */
	private boolean isRoleExpired(Receiver person, String roleName) {

		List<Orgrole> roles = null;

		try {
			roles = roleHelperDao.getAllRolesForPerson(person.getPersonid());

			if (roles.size() > 0) {

				logger.debug("Mehrere Rollen gefunden");

				List<Orgrole> roleNameList = new ArrayList<>();

				for (Orgrole role : roles) {

					if (role != null) {

						if (role.getRoleId() == rightsAndRolesDao.fetchByUniqename(roleName).getRoleId()) {
							roleNameList.add(role);
						}
					}
				}

				int invalid = 0;
				for (Orgrole rr : roleNameList) {

					XMLGregorianCalendar validTo = rr.getValidTo();
					if (CalendarUtils.isPast(CalendarUtils.toDate(validTo))) {
						logger.error("Die Rolle " + roleName + " für " + person.getVorname() + " "
								+ person.getNachname() + " ist abgelaufen!");
						invalid++;
					} else {
						invalid--;
					}

				}

				// rolle ist abgelaufen
				if (roleNameList.size() == invalid) {
					return true;
				}

			} else {
				return true; // keine Rollen vorhanden
			}
		} catch (Exception e) {
			logger.debug("Etwas ist falsch gelaufen!  NoSuchElementException | ServiceFault_Exception");
			e.printStackTrace();
		}

		return false;
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(ModelMap model, @ModelAttribute MailProtokoll mailProtokoll, HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "receiver", required = false) String verteilerName,
			@RequestParam("subject") String subject, @RequestParam("body") String body,
			@RequestParam("replyto") String replyto) {

		/**
		 * Map für Alle Personen die in augewäter Verteiler sind
		 */
		HashMap<Long, Receiver> recievers = new HashMap<>();

		/**
		 * uid für diese Mail
		 */
		String uid = UUID.randomUUID().getMostSignificantBits() + "";

		/*
		 * Default reply to Adresse falls keine eingetragen ist
		 */
		if (replyto == null || replyto.equals("")) {
			replyto = "servicedesk+tune@tuhh.de";
		}

		/*
		 * lese Verteiler aus DB
		 */
		String[] verteilerList = null;
		if (verteilerName != null) {
			verteilerList = verteilerName.split(",");
		}

		// der Mail verschickt
		Receiver me = new Receiver();

		// Angemeldeter Benutzer
		String currUser = authorityUtils.getCurrentUserName();

		/**
		 * Überprüfung -Die jenige, die das Nutzen dürfen, müssen in die DB eingetragen
		 * werden Benutzername und die E-Mail Adresse
		 */
		String key = "de.tuhh.tusp.exa.massmails." + currUser + ".email";
		String emailAddress = configDao.fetchByKey(key).getValue();

		me.setEmail(emailAddress);

		recievers.put(0l, me);

		// lese Verteiler aus DB und nehme die Personen die dazu gehören
		if (verteilerList != null) {
			for (String name : verteilerList) {

				String[] split = name.split("###");
				Long semester = Long.parseLong(split[1]);

				List<Receiver> addr = recevierService.findAllByVerteilername(split[0], semester);
				for (Receiver person : addr) {

					logger.debug("Verteiler: " + name + "   Email: " + person.getEmail());
					logger.debug("PERSON ID: " + person.getPersonid() + " PERSON_Email: " + person.getEmail());

					recievers.put(person.getPersonid(), person);

				}
			}
		}

		/*
		 * sende einzeln Mails an die Personen und guck nach Fehler
		 */
		int success = 0, errors = 0, mailsNotFound = 0, accountsExpired = 0, rolePrueferExpiredCount = 0,
				roleLehrenderExpiredCount = 0, roleLehrenderVertreterExpiredCount = 0,
				rolePrueferVertreterExpiredCount = 0;

		List<MailErrors> successList = new ArrayList<>();
		List<MailErrors> mailErrorList = new ArrayList<>();
		List<MailErrors> rolePrueferExpiredList = new ArrayList<>();
		List<MailErrors> roleLehrenderExpiredList = new ArrayList<>();
		List<MailErrors> roleLehrenderVertereterExpiredList = new ArrayList<>();
		List<MailErrors> rolePrueferVertereterExpiredList = new ArrayList<>();

		List<MailErrors> accountsExpiredList = new ArrayList<>();
		List<MailErrors> exceptionList = new ArrayList<>();

		for (Entry<Long, Receiver> recieverEntry : recievers.entrySet()) {
			Receiver reciever = recieverEntry.getValue();

			MailProtokoll protokoll2 = new MailProtokoll();

			try {
				protokoll2.setReceiver(reciever.getEmail());
				protokoll2.setSubject(subject);
				protokoll2.setReplyto(replyto);
				protokoll2.setSender(me.getEmail());
				protokoll2.setUsername(currUser);
				protokoll2.setTimestamp(new Date().toString());

				logger.debug("Verteiler: " + reciever.getVerteilername());
				String vName = reciever.getVerteilername();

				/*
				 * ist die Email NULL ...nicht da?
				 */
				if (null == reciever.getEmail() || reciever.getEmail().equals("")
						|| reciever.getEmail().equals("NULL")) {

					String message = reciever.getPersonid() + " " + reciever.getVerteilername() + " "
							+ reciever.getEmail() + " " + reciever.getVorname() + " " + reciever.getNachname();

					MailErrors mailErrors = new MailErrors(new String("Keine E-Mail Adresse"),
							new String(LocalDate.now().toString()), reciever.getVorname(), reciever.getNachname(),
							message, uid);

					mailErrorList.add(mailErrors);
					mailsNotFound++;

					continue;
				}

				if (null != reciever.getPersonid()) {
					/*
					 * alle Accounts abgelaufen ?
					 */
					List<CompleteAccount> completeAccountList = hisAccountHelperDao
							.searchAccountForPerson(reciever.getPersonid());

					if (completeAccountList.size() > 0) {

						int count = 0;
						for (CompleteAccount completeAccount : completeAccountList) {

							logger.debug("Account für Benutzername: " + completeAccount.getUsername() + " gültig bis: "
									+ completeAccount.getValidTo());

							if (CalendarUtils.isPast(CalendarUtils.toDate(completeAccount.getValidTo()))) {
								count++;
							}
						}
						if (count == completeAccountList.size()) {

							logger.debug("Alle Accounts für " + reciever.getVorname() + " " + reciever.getNachname()
									+ " sind Abgelaufen");
							String message = reciever.getVorname() + " " + reciever.getNachname();
							MailErrors account = new MailErrors("Alle Accounts Abgelaufen: ",
									LocalDate.now().toString(), reciever.getVorname(), reciever.getNachname(), message,
									uid);

							accountsExpiredList.add(account);
							accountsExpired++;

							continue;
						}

					}

					// Lehrpersonen
					if (vName.equals("Personen mit Rolle Lehrperson")
							|| vName.startsWith("Personen mit Lehrtätigkeit im Semester")) {
						System.out.println("Lehrperson");

						if (reciever.getNachname().equals("Clemens")) {
							System.out.println("stop");
						}

						if (isRoleExpired(reciever, "lehrender")) {

							String message = reciever.getPersonid() + " " + reciever.getVerteilername() + " "
									+ reciever.getEmail() + " " + reciever.getVorname() + " " + reciever.getNachname();
							logger.debug("Lehrender-Rolle für " + reciever.getVorname() + " " + reciever.getNachname()
									+ " ist abgelaufen!");
							MailErrors roleErrorLehrender = new MailErrors("Lehrperson Rolle Abgelaufen",
									LocalDate.now().toString(), reciever.getVorname(), reciever.getNachname(), message,
									uid);
							roleLehrenderExpiredList.add(roleErrorLehrender);

							roleLehrenderExpiredCount++;
							continue;
						}
					}
					// Lehrperson-Vertreter
					if (vName.equals("Personen mit Rolle Lehrperson-Vertreter")) {
						System.out.println("Lehrperson-Vertreter");
						/*
						 * Lehrvertreter Rolle Abgelaufen?
						 */

						if (reciever.getNachname().equals("Clemens")) {
							System.out.println("stop");
						}

						if (isRoleExpired(reciever, "lehrender-Vertreter")) {

							String message = reciever.getPersonid() + " " + reciever.getVerteilername() + " "
									+ reciever.getEmail() + " " + reciever.getVorname() + " " + reciever.getNachname();
							logger.debug("Lehrvertreter-Rolle für " + reciever.getVorname() + " "
									+ reciever.getNachname() + " ist abgelaufen!");
							MailErrors roleErrorLehrender = new MailErrors("Lehperson-Vertreter Rolle Abgelaufen",
									LocalDate.now().toString(), reciever.getVorname(), reciever.getNachname(), message,
									uid);
							roleLehrenderVertereterExpiredList.add(roleErrorLehrender);

							roleLehrenderVertreterExpiredCount++;
							continue;
						}
					}

					// Prüfer
					if (vName.equals("Personen mit Rolle Prüfer")
							|| vName.startsWith("Personen mit Prüfungstätigkeit im Semeser")) {
						System.out.println("Prüfer");

						if (isRoleExpired(reciever, "pruefer")) {
							String message = reciever.getPersonid() + " " + reciever.getVerteilername() + " "
									+ reciever.getEmail() + " " + reciever.getVorname() + " " + reciever.getNachname();
							MailErrors roleErrorPruefer = new MailErrors("Prüfer Rolle Abgelaufen",
									LocalDate.now().toString(), reciever.getVorname(), reciever.getNachname(), message,
									uid);
							rolePrueferExpiredList.add(roleErrorPruefer);
							logger.debug("Prüfer Rolle für " + reciever.getVorname() + " " + reciever.getNachname()
									+ " ist abgelaufen!");
							rolePrueferExpiredCount++;
							continue;
						}

					}
					// Prüfer-Vertreter
					if (vName.equals("Personen mit Rolle Prüfervertreter")
							|| vName.startsWith("Prüfervertreter mit Aktivität im Semester")) {
						System.out.println("Prüfer-Vertreter");
						/*
						 * Prüfer-Vertreter Rollen Abgelaufen?
						 */
						if (reciever.getPersonid() == 66623L) {
							System.out.println("Stop!");
						}
						if (isRoleExpired(reciever, "representative_examiner")) {

							String message = reciever.getPersonid() + " " + reciever.getVerteilername() + " "
									+ reciever.getEmail() + " " + reciever.getVorname() + " " + reciever.getNachname();
							logger.debug("Prüfer-vertreter-Rolle für " + reciever.getVorname() + " "
									+ reciever.getNachname() + " ist abgelaufen!");
							MailErrors roleErrorLehrender = new MailErrors("Prüfer-Vertreter Rolle Abgelaufen",
									LocalDate.now().toString(), reciever.getVorname(), reciever.getNachname(), message,
									uid);
							rolePrueferVertereterExpiredList.add(roleErrorLehrender);

							rolePrueferVertreterExpiredCount++;
							continue;
						}
					}

				}

				/**
				 * E-Mail vorbeireiten und verschicken
				 */
				String text = body;
				protokoll2.setBody(text);

				mailUtils.sendHTMLMailWithCC(reciever.getEmail(), me.getEmail(), subject, text, replyto);

				String message = reciever.getPersonid() + " " + reciever.getVerteilername() + " " + reciever.getEmail()
						+ " " + reciever.getVorname() + " " + reciever.getNachname();
				MailErrors successObj = new MailErrors("Erfolgreich", LocalDate.now().toString(), reciever.getVorname(),
						reciever.getNachname(), message, uid);

				successList.add(successObj);
				success++;

				protokoll2.setStatus("Done");

				logger.debug("Email erforlgreich gesendet an: " + reciever.getEmail());

			} catch (Exception e) {

				logger.error("Mail konnte nicht gesendet werden", e);

				protokoll2.setStatus("Failed");

				String message = reciever.getPersonid() + " " + reciever.getVerteilername() + " " + reciever.getEmail()
						+ " " + reciever.getVorname() + " " + reciever.getNachname();

				MailErrors exception = new MailErrors(new String("Ausnahme-Fehler"),
						new String(LocalDate.now().toString()), reciever.getVorname(), reciever.getNachname(), message,
						uid);

				exceptionList.add(exception);
				errors++;
			}

			/*
			 * in die Datenbank speichern
			 */
			mailProtokollService.insert(protokoll2);

		}

		// in die Datenbank Speichern
		// neue liste bauen
		List<MailErrors> mailSendZusammenfassungList = Stream
				.of(successList, mailErrorList, accountsExpiredList, exceptionList, rolePrueferExpiredList,
						roleLehrenderExpiredList, rolePrueferVertereterExpiredList, roleLehrenderVertereterExpiredList)
				.flatMap(Collection::stream).collect(Collectors.toList());

		// in die Datenbank speichern
		for (MailErrors mailError : mailSendZusammenfassungList) {
			mailErrorsService.insert(mailError);
		}

		// uid
		model.put("uid", uid);

		// die Zahlen
		model.put("successes", success);
		model.put("errors", errors);
		model.put("mailsNotFound", mailsNotFound);
		model.put("accountsExpired", accountsExpired);
		model.put("rolePrueferExpiredCount", rolePrueferExpiredCount);

		model.put("rolePrueferVertreterExpiredCount", rolePrueferVertreterExpiredCount);

		model.put("roleLehrenderExpiredCount", roleLehrenderExpiredCount);
		model.put("roleLehrenderVertreterExpiredCount", roleLehrenderVertreterExpiredCount);

		// E-Mail Fehlt Fehler
		model.put("mailErrorList", mailErrorList);

		// Erfolg Liste
		model.put("successList", successList);

		// Rollen Fehler
		model.put("rolePrueferExpiredList", rolePrueferExpiredList);
		model.put("roleLehrenderExpiredList", roleLehrenderExpiredList);
		model.put("rolePrueferVertereterExpiredList", rolePrueferVertereterExpiredList);
		model.put("roleLehrenderVertereterExpiredList", roleLehrenderVertereterExpiredList);

		model.put("accountsExpiredList", accountsExpiredList);
		model.put("exceptionList", exceptionList);

		return "massmails/confirm";

	}

	@RequestMapping(value = "/verteiler", method = RequestMethod.GET)
	public String showVerteiler(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		model.put("entries", recevierService.findAll());

		return "massmails/verteiler";
	}

}
