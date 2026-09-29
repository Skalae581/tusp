package de.tuhh.tusp.saeulen.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.saeulen.dao.KonfigurationseigenschaftDao;
import de.tuhh.tusp.saeulen.dao.KonfigurationsquelleDao;
import de.tuhh.tusp.saeulen.dao.KonfigurationstransferDao;
import de.tuhh.tusp.saeulen.dao.SaeuleDao;
import de.tuhh.tusp.saeulen.dao.SaeuleUserDao;
import de.tuhh.tusp.saeulen.dao.ZustimmungenDao;
import de.tuhh.tusp.saeulen.pojo.Konfigurationseigenschaft;
import de.tuhh.tusp.saeulen.pojo.Konfigurationsquelle;
import de.tuhh.tusp.saeulen.pojo.Konfigurationstransfer;
import de.tuhh.tusp.saeulen.pojo.Saeule;
import de.tuhh.tusp.saeulen.pojo.SaeuleUser;
import de.tuhh.tusp.saeulen.pojo.Zustimmungen;
import de.tuhh.tusp.saeulen.service.MailService;

@Controller
@RequestMapping(value = "/saeulen/saeulen")
@AccessConfig(path = "/saeulen/saeulen/**", access = { Rollen.PUBLIC })
public class TransferController {
	@Autowired
	private SaeuleDao saeuleDao;
	@Autowired
	private SaeuleUserDao saeuleUserDao;
	@Autowired
	private KonfigurationsquelleDao konfigurationsquelleDao;
	@Autowired
	private KonfigurationseigenschaftDao konfigurationseigenschaftDao;
	@Autowired
	private KonfigurationstransferDao konfigurationstransferDao;
	@Autowired
	private MailService mailService;
	@Autowired
	private ZustimmungenDao zustimmungenDao;

	private final String accUrl = "/tusp/saeulen/saeulen/zustimmen?transferUID=";
	private final String notAccUrl = "/tusp/saeulen/saeulen/ablehnen?transferUID=";

	/**
	 * Ablehnung einer Zustimmung / link wird per Mail verschickt
	 *
	 * @param request
	 * @param modelMap
	 * @param transferUID
	 * @param uname
	 * @return
	 */
	@RequestMapping(value = "/ablehnen", method = RequestMethod.GET)
	public String ablehnen(HttpServletRequest request, ModelMap modelMap, @RequestParam Long transferUID,
			@RequestParam String uname, @ModelAttribute Konfigurationstransfer konfigurationstransfer,
			@ModelAttribute Konfigurationseigenschaft konfigurationseigenschaft) {

		boolean transferAbgeschlossen = konfigurationstransferDao.findByUID(transferUID).get(0).isAbgeschlossen();

		if (transferAbgeschlossen) {
			return "saeulen/saeulen/transferAbgeschlossenPage";
		}

		System.out.println("Konfigurationstransfer UID : " + transferUID);

		// speichert daten in der tabelle "kofigurationstransfer"
		Konfigurationstransfer konftransfer = konfigurationstransferDao.findByUID(transferUID).get(0);
		// leifert konfeingeschaften
		String konfeigenschaften = konftransfer.getKonfigurationseingeschaften();
		List<String> konfeigenschaftenList = Arrays.asList(konfeigenschaften.split(","));
		List<String> konfnamen = new ArrayList<>();
		for (String keigenschaft : konfeigenschaftenList) {
			String bezeichnung = konfigurationseigenschaftDao.findNamebyId(Long.parseLong(keigenschaft));
			konfnamen.add(bezeichnung);
		}
		// zeigt id von konfquelle anhang der bezeichnung von tabelle
		// konfigurationsquelle
		Konfigurationsquelle kofigurationsquelle = null;
		if (konftransfer.getKonfigurationsquelle_id() != null) {
			kofigurationsquelle = konfigurationsquelleDao.fetchById(konftransfer.getKonfigurationsquelle_id());
		} else {

			kofigurationsquelle = konfigurationsquelleDao.fetchById(7L);
		}
		Saeule saeule = saeuleDao.fetchById(konftransfer.getSaeule_id());
		SaeuleUser user = saeuleUserDao.fetchById(konftransfer.getSaeule_user_id());
//		restkonfeingenschaft
		List<Zustimmungen> zustimmungen = zustimmungenDao.findZustimmungenFromUID(transferUID);

		Long userId = saeuleUserDao.findIdByName(uname).get(0).getId();// gibt id zurück

		// guck mal ob er schon zugestimmt hat
		if (null != userId) {
			for (Zustimmungen zustimmung : zustimmungen) {

				if (zustimmung.getSaeule_user_id() == userId) {
					modelMap.addAttribute("hatAbgelehnt", zustimmung.isAbgelehnt());

				}

			}
		}

		modelMap.addAttribute("transferUID", transferUID);

		modelMap.addAttribute("kofigurationsquelle", kofigurationsquelle);
		modelMap.addAttribute("saeule", saeule);
		modelMap.addAttribute("zustimmungen", zustimmungen);
		modelMap.addAttribute("konfeingenschaftnamen", konfnamen);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("currentUser", uname);
		modelMap.put("datumHeute", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));
		return "saeulen/saeulen/transferzustimmenAblehnen";

	}

	//
	public List<SaeuleUser> findVorgesetzte(List<SaeuleUser> users) {
		List<SaeuleUser> vorgesetzte = new ArrayList<>();
		for (SaeuleUser s : users) {
			if (s.isVorgesetzter() == true) {
				vorgesetzte.add(s);
			}
		}
		return vorgesetzte;
	}

	@RequestMapping(value = "/transfer")
	public String pageTransfer(ModelMap modelMap, @RequestParam("id") String id,
			@ModelAttribute Konfigurationstransfer konfigurationstransfer,
			@ModelAttribute Konfigurationseigenschaft konfigurationseigenschaft,
			@ModelAttribute Konfigurationsquelle konfigurationsquelle, HttpServletRequest request)
			throws ParseException {

		Saeule saeule = saeuleDao.fetchById(Long.parseLong(id));
		List<Konfigurationsquelle> konfigurationsquellen = konfigurationsquelleDao.findAll();
		List<Konfigurationseigenschaft> konfeingenschaft = konfigurationseigenschaftDao.findeAlleAndere();
		List<Konfigurationseigenschaft> konfeingenschaftMitQuelle = konfigurationseigenschaftDao
				.findeAlleFuerDieQuelle();

		List<SaeuleUser> alluser = saeuleUserDao.findAll();
		List<SaeuleUser> users = findVorgesetzte(alluser);

		System.out.println("--------------Änderugen------------------");

		modelMap.addAttribute("konfeingenschaft", konfeingenschaft);
		modelMap.addAttribute("konfeingenschaftMitQuelle", konfeingenschaftMitQuelle);
		modelMap.addAttribute("saeuleObj", saeule);
		modelMap.addAttribute("users", users);
		modelMap.addAttribute("konfigurationsquellen", konfigurationsquellen);
		modelMap.put("datumHeute", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));

		return "saeulen/saeulen/transferieren";
	}

	/**
	 * Im Fall, dass jemand NICHT zustimmen will
	 *
	 * @param request
	 * @param modelMap
	 * @param aktualisierungUID
	 * @param uname
	 * @return
	 */
	@RequestMapping(value = "/ablehnen", method = RequestMethod.POST)
	public String transferAblehnenPost(HttpServletRequest request, ModelMap modelMap, String transferUID,
			String currentUser, String begruendung) {

		System.out.println("transferUID: " + transferUID);
		System.out.println("currentUser: " + currentUser);
		System.out.println("begruendung: " + begruendung);

		List<Zustimmungen> zustim = zustimmungenDao.findZustimmungenFromUID(Long.parseLong(transferUID));

		Long userId = saeuleUserDao.findIdByName(currentUser).get(0).getId();

		for (Zustimmungen zustimmung : zustim) {
			if (zustimmung.getSaeule_user_id() == userId) {

				zustimmung.setZugestimmt(false);
				zustimmung.setAbgelehnt(true);
				zustimmung.setAblehnungsgrund(begruendung);
				zustimmung.setAblehnungsdatum(new Date().toString());

				zustimmungenDao.update(zustimmung);

				System.out.println("Zustimmung ID: " + zustimmung.getId());
			}
		}

		return "saeulen/saeulen/zustimmenEndPage";
	}

	@RequestMapping(value = "/transferierungPost", method = RequestMethod.POST)
	public String transferierungPost(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
			String zielSaeuleId, Long konfigurationsquelle, Long[] konfeigenschaft, Long[] restKonfeigenschaft,
			String begruendung, Long[] zustimmung_user, String saeule_user_id, String datum,
			RedirectAttributes redirectAttrs) {

		System.out.println("------------------------------Transfer------------------------ :");
		System.out.println("ID konfquelle      :" + konfigurationsquelle);// Select-element mit optionen

		System.out.println("Saeule             :" + zielSaeuleId);
		String konfigurationseingenschaften = "";

		if (null != konfeigenschaft && konfeigenschaft.length != 0) {
			for (Long s : konfeigenschaft) {
				System.out.println("Konfeigenschaft    :" + s);
				konfigurationseingenschaften += s + ",";
			}
		} // grund für transfer(mit Quelle)

		if (null != restKonfeigenschaft && restKonfeigenschaft.length != 0) {
			for (Long s : restKonfeigenschaft) {
				System.out.println("RestKonfeigenschaft:" + s);
				konfigurationseingenschaften += s + ",";
			} // grund ohne Quelle
		}

		System.out.println("Konfigurationeigenschaften :" + konfigurationseingenschaften);
		System.out.println("Begründung             :" + begruendung);
		System.out.println("Benutzer               :" + saeule_user_id);

		System.out.println("Datum                  :" + datum);

		Konfigurationstransfer konftransfer = new Konfigurationstransfer();

		if (null != zustimmung_user && zustimmung_user.length != 0) {

			for (Long s : zustimmung_user) {
				System.out.println("Benutzer für die Zustimmung: " + s); // benutzer id
			}
		} else {
			konftransfer.setAbgeschlossen(true);
		}
		System.out.println("------------------------------Endetransfer-------------------- ");
		Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		konftransfer.setUid(uuid);
		konftransfer.setBegruendung(begruendung);
		konftransfer.setDatum(datum);
		konftransfer.setKonfigurationsquelle_id(konfigurationsquelle);
		konftransfer.setSaeule_id(Long.parseLong(zielSaeuleId));
		konftransfer.setKonfigurationseingeschaften(konfigurationseingenschaften);

		Long id = saeuleUserDao.findIdByName(saeule_user_id.trim()).get(0).getId();
		konftransfer.setSaeule_user_id(id);
		System.out.println("Object konftransfer              :" + konftransfer);
		konfigurationstransferDao.insert(konftransfer);// insert into Tabelle Konfigurationstransfer

		if (null != zustimmung_user) {
			for (int i = 0; i < zustimmung_user.length; i++) {
				Zustimmungen zustimmung = new Zustimmungen();
				zustimmung.setSaeule_user_id(zustimmung_user[i]);
				zustimmung.setZugestimmt(false);
				zustimmung.setUid(konftransfer.getUid());

				zustimmungenDao.insert(zustimmung); // insert into zustimmungen tabelle

				String email = saeuleUserDao.findEmailFromId(zustimmung_user[i]).get(0).getEmail();
				System.out.println("EMail Addr: " + email);

				SaeuleUser user = saeuleUserDao.fetchById(zustimmung_user[i].longValue());
				System.out.println("UNAME: " + user.getBenutzername());

				System.out.println("Email Params: MailAddr:" + email + " Benutzername: " + user.getBenutzername());

				mailService.sendEmail(email, request, konftransfer.getUid(), user.getBenutzername(), accUrl, notAccUrl);

			}

		}
		return "saeulen/saeulen/transferEndPage";

	}

	@RequestMapping(value = "/zustimmen", method = RequestMethod.GET)
	public String transferzustimmen(HttpServletRequest request, ModelMap modelMap, @RequestParam Long transferUID,
			@RequestParam String uname) {

		boolean transferAbgeschlossen = konfigurationstransferDao.findByUID(transferUID).get(0).isAbgeschlossen();

		if (transferAbgeschlossen) {
			return "saeulen/saeulen/transferAbgeschlossenPage";
		}

		List<Zustimmungen> zustim = zustimmungenDao.findZustimmungenFromUID(transferUID);
		Long userId = saeuleUserDao.findIdByName(uname).get(0).getId();

		for (Zustimmungen zustimmung : zustim) {

			if (zustimmung.getSaeule_user_id() == userId) {

				if (!zustimmung.isZugestimmt()) {

					zustimmung.setAbgelehnt(false);
					zustimmung.setZugestimmt(true);
					zustimmung.setZusgestimmtdatum(new Date().toString());

					zustimmungenDao.update(zustimmung);
				} else {
					System.out.println("Bereits Zugestimmt " + zustimmung.getZusgestimmtdatum());
					return "saeulen/saeulen/zustimmenErfolgt";
				}

				System.out.println("Zustimmung ID: " + zustimmung.getId());

			}
		}

		Konfigurationstransfer konftransfer = konfigurationstransferDao.findByUID(transferUID).get(0);
		List<Zustimmungen> zustimmungen = zustimmungenDao.findZustimmungenFromUID(konftransfer.getUid());

		boolean nichtAlleZugestimmt = zustimmungen.stream().filter(o -> o.isZugestimmt() == false).findFirst()
				.isPresent();

		if (nichtAlleZugestimmt == false) {
			Saeule zielSauele = saeuleDao.fetchById(konftransfer.getSaeule_id());
			Konfigurationsquelle konfQuelle = konfigurationsquelleDao
					.fetchById(konftransfer.getKonfigurationsquelle_id());

			// vorgang hier abgeschloßen
			konftransfer.setAbgeschlossen(true);
			konfigurationstransferDao.update(konftransfer);

		}

		return "saeulen/saeulen/zustimmenEndPage";
	}
}
