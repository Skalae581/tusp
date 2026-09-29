package de.tuhh.tusp.saeulen.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import de.tuhh.tusp.saeulen.dao.SaeuleDao;
import de.tuhh.tusp.saeulen.dao.SaeuleUserDao;
import de.tuhh.tusp.saeulen.dao.SaeuleaktualisierungDao;
import de.tuhh.tusp.saeulen.dao.ZustimmungenDao;
import de.tuhh.tusp.saeulen.pojo.Saeule;
import de.tuhh.tusp.saeulen.pojo.SaeuleUser;
import de.tuhh.tusp.saeulen.pojo.Saeuleaktualisierung;
import de.tuhh.tusp.saeulen.pojo.Zustimmungen;
import de.tuhh.tusp.saeulen.service.MailService;
import lombok.Data;

@Controller
@RequestMapping(value = "/saeulen/saeulen")
@AccessConfig(path = "/saeulen/saeulen/**", access = { Rollen.PUBLIC })
public class AktualisierungController {

	@Data
	public class ProtokollWrapper {
		public String zielSaeule;
		public String vonSaeule;
		public String uid;
		public String user;
		public String datum;
		public String begruendung;
		public boolean abgeschlossen;

	}

	@Autowired
	private SaeuleDao saeuleDao;
	@Autowired
	private SaeuleaktualisierungDao aktualisierungDao;
	@Autowired
	private SaeuleUserDao saeuleUserDao;
	@Autowired
	private MailService mailService;
	@Autowired
	private ZustimmungenDao zustimmungenDao;

	private final String accUrl = "/tusp/saeulen/saeulen/zustim?aktualisierungUID=";
	private final String notAccUrl = "/tusp/saeulen/saeulen/zustimn?aktualisierungUID=";

	/**
	 * Ablehnung einer Zustimmung / link wird per Mail verschickt
	 *
	 * @param request
	 * @param modelMap
	 * @param aktualisierungUID
	 * @param uname
	 * @return
	 */
	@RequestMapping(value = "/zustimn", method = RequestMethod.GET)
	public String ablehnen(HttpServletRequest request, ModelMap modelMap, @RequestParam Long aktualisierungUID,
			@RequestParam String uname) {

		boolean aktualisierungAbgeschlossen = aktualisierungDao.findByUID(aktualisierungUID).get(0).isAbgeschlossen();

		if (aktualisierungAbgeschlossen) {
			return "saeulen/saeulen/aktualisierungAbgeschlossenPage";
		}

		System.out.println("Aktualisierung UID : " + aktualisierungUID);

		Saeuleaktualisierung saeuleaktualisierung = aktualisierungDao.findByUID(aktualisierungUID).get(0);
		Saeule vonWoAktualisieren = saeuleDao.fetchById(saeuleaktualisierung.getSaeulenquelle_id());
		Saeule zielSaeule = saeuleDao.fetchById(saeuleaktualisierung.getSaeule_von_id());
		SaeuleUser user = saeuleUserDao.fetchById(saeuleaktualisierung.getSaeule_user_id());
		List<Zustimmungen> zustimmungen = zustimmungenDao.findZustimmungenFromUID(aktualisierungUID);

		Long userId = saeuleUserDao.findIdByName(uname).get(0).getId();

		// guck mal ob er schon zugestimmt hat
		if (null != userId) {
			for (Zustimmungen zustimmung : zustimmungen) {

				if (zustimmung.getSaeule_user_id() == userId) {
					modelMap.addAttribute("hatAbgelehnt", zustimmung.isAbgelehnt());

				}

			}
		}

		modelMap.addAttribute("saeuleaktualisierung", saeuleaktualisierung);
		modelMap.addAttribute("zustimmungen", zustimmungen);
		modelMap.addAttribute("vonWoAktualisieren", vonWoAktualisieren);
		modelMap.addAttribute("aufAktualisieren", zielSaeule);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("currentUser", uname);
		modelMap.addAttribute("datumHeute", new Date());
		modelMap.addAttribute("aktualisierungUID", aktualisierungUID);

		return "saeulen/saeulen/zustimmenAblehnenPage";

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
	@RequestMapping(value = "/zustimn", method = RequestMethod.POST)
	public String ablehnenPost(HttpServletRequest request, ModelMap modelMap, String aktualisierungUID,
			String currentUser, String begruendung) {

		System.out.println("aktualisierunsUID: " + aktualisierungUID);
		System.out.println("currentUser: " + currentUser);
		System.out.println("begruendung: " + begruendung);

		List<Zustimmungen> zustim = zustimmungenDao.findZustimmungenFromUID(Long.parseLong(aktualisierungUID));

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

	@RequestMapping(value = "/aktualisieren")
	public String aktualisieren(ModelMap modelMap, @RequestParam("id") String id) {

		Saeule saeule = saeuleDao.fetchById(Long.parseLong(id));
		List<Saeule> quellSaeule = saeuleDao.findAll();
		List<Saeuleaktualisierung> aktu = aktualisierungDao.findAll(); // test
		List<SaeuleUser> users = saeuleUserDao.getUsersForQuelle(id);

		// datum formatierung

		System.out.println(quellSaeule);
		System.out.println(aktu);
		System.out.println(users);
		System.out.println("datum Heute: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));

		modelMap.put("saeule", saeule);
		modelMap.put("quellSaeule", quellSaeule);
		modelMap.put("users", users);
		modelMap.put("aktu", aktu);
		modelMap.put("datumHeute", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY")));

		return "saeulen/saeulen/aktualisieren";

	}

	@RequestMapping(value = "/aktualisierungPost", method = RequestMethod.POST)
	public String aktualisierungPost(ModelMap modelMap, @ModelAttribute Saeule saeule, HttpServletRequest request,
			HttpServletResponse response, Long zielSaeuleId, Long[] zustimmung_user, String datum, String begruendung,
			String saeule_user_id, Long quellSaeule, RedirectAttributes redirectAttrs) {

		System.out.println("ID: " + zielSaeuleId);
		System.out.println("Datum : " + datum);
		System.out.println("Begründung : " + begruendung);
		System.out.println("Quelle von wo?: " + quellSaeule);
		System.out.println("Wer?: " + saeule_user_id);
		// System.out.println("datumHeute: " + LocalDateTime.now());
		Saeuleaktualisierung aktualisierung = new Saeuleaktualisierung();

		if (null != zustimmung_user && zustimmung_user.length != 0) {

			for (Long s : zustimmung_user) {
				System.out.println("Benutzer für die Zustimmung: " + s); // benutzer id
			}
		} else {
			aktualisierung.setAbgeschlossen(true);
		}

		Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		aktualisierung.setUid(uuid);
		aktualisierung.setBegruendung(begruendung);
		aktualisierung.setDatum(datum);
		aktualisierung.setSaeulenquelle_id(quellSaeule);
		aktualisierung.setSaeule_von_id(zielSaeuleId);

		Long id = saeuleUserDao.findIdByName(saeule_user_id).get(0).getId();
		aktualisierung.setSaeule_user_id(id);

		aktualisierungDao.insert(aktualisierung); // aktualisierung tabelle

		if (null != zustimmung_user) {
			for (int i = 0; i < zustimmung_user.length; i++) {
				Zustimmungen zustimmung = new Zustimmungen();
				zustimmung.setSaeule_user_id(zustimmung_user[i]);
				zustimmung.setZugestimmt(false);
				zustimmung.setUid(aktualisierung.getUid());

				System.out.println("Zustimmung UID: " + zustimmung.getUid());
				Long sauleAktualisierungId = aktualisierungDao.findByUID(zustimmung.getUid()).get(0).getId();
				System.out.println("sauleAktualisierungId : " + sauleAktualisierungId);
				zustimmung.setSaeulenaktualisierungs_id(sauleAktualisierungId);

				zustimmungenDao.insert(zustimmung); // zustimmungen tabelle

				String email = saeuleUserDao.findEmailFromId(zustimmung_user[i]).get(0).getEmail();
				System.out.println("EMail Addr: " + email);

				SaeuleUser user = saeuleUserDao.fetchById(zustimmung_user[i].longValue());
				System.out.println("UNAME: " + user.getBenutzername());

				System.out.println("Email Params: MailAddr:" + email + " Benutzername: " + user.getBenutzername());
//				mailService.sendEmail(email, request, aktualisierung.getUid(), user.getBenutzername(), accUrl,
//						notAccUrl);

			}

		}
		return "saeulen/saeulen/aktualisierungEndPage";

	}

	@RequestMapping(value = "/aktualisierungsProtokoll", method = RequestMethod.GET)
	public String aktualisierungsProtokoll(ModelMap modelMap, @RequestParam("id") Long id) {

		System.out.println("SäauleID Protokoll: " + id);
		// aktualisierungen für die säule
		List<Saeuleaktualisierung> saeuleaktualisierungen = aktualisierungDao.findeAktualisierungenFuerDieQuelle(id);

		List<ProtokollWrapper> protokollList = new ArrayList<>();

		for (Saeuleaktualisierung aktualisierung : saeuleaktualisierungen) {
			ProtokollWrapper protokollWrapper = new ProtokollWrapper();
			protokollWrapper.setZielSaeule(saeuleDao.fetchById(id).getBezeichnung());
			protokollWrapper.setVonSaeule(saeuleDao.fetchById(aktualisierung.getSaeulenquelle_id()).getBezeichnung());
			protokollWrapper.setUid(aktualisierung.getUid().toString());
			protokollWrapper.setUser(saeuleUserDao.fetchById(aktualisierung.getSaeule_user_id()).getBenutzername());
			protokollWrapper.setDatum(aktualisierung.getDatum());
			protokollWrapper.setBegruendung(aktualisierung.getBegruendung());
			protokollWrapper.setAbgeschlossen(aktualisierung.isAbgeschlossen());

			protokollList.add(protokollWrapper);
		}

		System.out.println("gefundene aktualisierungen: " + saeuleaktualisierungen);
		modelMap.addAttribute("protokollList", protokollList);
		modelMap.addAttribute("zielSaeuleId", id);

		return "saeulen/saeulen/aktualisierungsProtokollPage";
	}

	/**
	 * Hier wird der Zustimmung auf true gesetzt für den Benutzer, wenn der das Link
	 * in der Mail aufruft
	 *
	 * @param request
	 * @param modelMap
	 * @param aktualisierungUID
	 * @param uname
	 * @return
	 */
	@RequestMapping(value = "/saeulen/zustim", method = RequestMethod.GET)
	public String zustimmen(HttpServletRequest request, ModelMap modelMap, @RequestParam Long aktualisierungUID,
			@RequestParam String uname) {

		boolean aktualisierungAbgeschlossen = aktualisierungDao.findByUID(aktualisierungUID).get(0).isAbgeschlossen();

		if (aktualisierungAbgeschlossen) {
			return "saeulen/saeulen/aktualisierungAbgeschlossenPage";
		}

		List<Zustimmungen> zustim = zustimmungenDao.findZustimmungenFromUID(aktualisierungUID);
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

		Saeuleaktualisierung aktualisierung = aktualisierungDao.findByUID(aktualisierungUID).get(0);

		List<Zustimmungen> zustimmungen = zustimmungenDao.findZustimmungenFromUID(aktualisierung.getUid());

		boolean nichtAlleZugestimmt = zustimmungen.stream().filter(o -> o.isZugestimmt() == false).findFirst()
				.isPresent();

		if (nichtAlleZugestimmt == false) {
			Saeule aufSaeule = saeuleDao.fetchById(aktualisierung.getSaeule_von_id());
			Saeule vonSaeule = saeuleDao.fetchById(aktualisierung.getSaeulenquelle_id());

			aufSaeule.setHiodbDatum(vonSaeule.getHiodbDatum());
			aufSaeule.setHiodbQuelle(vonSaeule.getHiodbQuelle());
			aufSaeule.setHisoneDatum(vonSaeule.getHisoneDatum());
			aufSaeule.setHisoneHotfixDatum(vonSaeule.getHisoneHotfixDatum());
			aufSaeule.setHisoneHotfixNr(vonSaeule.getHisoneHotfixNr());
			aufSaeule.setHisoneSuperxVersion(vonSaeule.getHisoneSuperxVersion());
			aufSaeule.setSosposDatum(vonSaeule.getSosposDatum());
			aufSaeule.setSosposVersion(vonSaeule.getSosposVersion());

			// vorgang hier abgeschloßen
			aktualisierung.setAbgeschlossen(true);
			aktualisierungDao.update(aktualisierung);

			saeuleDao.update(aufSaeule);

		}

		return "saeulen/saeulen/zustimmenEndPage";
	}

	/**
	 * Deteils über einer Aktualiserung / Zustimmung
	 *
	 * @param request
	 * @param modelMap
	 * @param aktualisierungUID
	 * @param uname
	 * @return
	 */
	@RequestMapping(value = "/zustimmungInfo", method = RequestMethod.GET)
	public String zustimmungInfo(HttpServletRequest request, ModelMap modelMap, @RequestParam Long aktualisierungUID,
			@RequestParam Long zielSaeuleId) {

		System.out.println("Aktualisierung UID : " + aktualisierungUID);
		System.out.println("zielSaeuleId : " + zielSaeuleId);

		Saeuleaktualisierung saeuleaktualisierung = aktualisierungDao.findByUID(aktualisierungUID).get(0);
		Saeule vonWoAktualisieren = saeuleDao.fetchById(saeuleaktualisierung.getSaeulenquelle_id());
		Saeule zielSaeule = saeuleDao.fetchById(zielSaeuleId);
		SaeuleUser user = saeuleUserDao.fetchById(saeuleaktualisierung.getSaeule_user_id());

		List<Zustimmungen> zustimmungen = zustimmungenDao.findZustimmungenFromUID(aktualisierungUID);

		// benutzer die zustimmen sollen
		List<Map<SaeuleUser, Zustimmungen>> zustimmungList = new ArrayList<>();

		for (Zustimmungen zustimmung : zustimmungen) {

			Map<SaeuleUser, Zustimmungen> map = new HashMap<>();

			SaeuleUser saeuleUser = saeuleUserDao.fetchById(zustimmung.getSaeule_user_id());

			map.put(saeuleUser, zustimmung);

			zustimmungList.add(map);
		}
		modelMap.addAttribute("saeuleaktualisierung", saeuleaktualisierung);
		modelMap.addAttribute("vonWoAktualisieren", vonWoAktualisieren);
		modelMap.addAttribute("aufAktualisieren", zielSaeule);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("zustimmungen", zustimmungen);
		modelMap.addAttribute("zustimmungList", zustimmungList);
		modelMap.addAttribute("zielSaeuleId", zielSaeuleId);

		return "saeulen/saeulen/zustimmungInfoPage";

	}

}
