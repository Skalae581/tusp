package de.tuhh.tusp.app.vorabcheck.controllers;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.Extra;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.pojo.StudiengangsauswahlArchive;
import de.tuhh.tusp.app.vorabcheck.service.BereichArchiveService;
import de.tuhh.tusp.app.vorabcheck.service.BereichService;
import de.tuhh.tusp.app.vorabcheck.service.ExtraService;
import de.tuhh.tusp.app.vorabcheck.service.FachArchiveService;
import de.tuhh.tusp.app.vorabcheck.service.FachService;
import de.tuhh.tusp.app.vorabcheck.service.StudiengangsauswahlArchiveService;
import de.tuhh.tusp.app.vorabcheck.service.StudiengangsauswahlService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.InMemoryResource;

@Controller
@RequestMapping(value = "/vorabcheck/admin")
@SessionAttributes("vorabInteressent")
@AccessConfig(path = "/vorabcheck/admin/**", access = { Rollen.ADMIN, Rollen.APP_ADMIN })
public class VorabcheckAdminController {

	private static Logger logger = LogManager.getLogger(VorabcheckAdminController.class);

	@Autowired
	private StudiengangsauswahlService studiengangsauswahlService;

	@Autowired
	private BereichService bereichService;

	@Autowired
	private FachService fachService;

	@Autowired
	private ExtraService extraService;

	@Autowired
	private StudiengangsauswahlArchiveService studiengangsauswahlArchiveService;

	@Autowired
	private BereichArchiveService bereichArchiveService;

	@Autowired
	private FachArchiveService fachArchiveService;

	@Autowired
	private BewVervollstController bewVervollstController;

	/**
	 * Ruft die Methode auf zum Archivieren (& Löschen?) eines ECTS-Bogens
	 *
	 * @param model
	 * @param bereich
	 * @param fach
	 * @param studiengangsauswahl
	 * @param requestParams
	 * @param stgId
	 * @param shorttext
	 * @param request
	 * @return Listenansicht der Studiengänge
	 */
	@RequestMapping(value = "/archiveDelete", method = RequestMethod.GET)
	public String archiveDelete(ModelMap model, Bereich bereich, Fach fach, Studiengangsauswahl studiengangsauswahl,
			@RequestParam Map<String, String> requestParams, @RequestParam("stgId") Long stgId,
			@RequestParam("shorttext") String shorttext, HttpServletRequest request) {

		studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

		if (studiengangsauswahl != null) {
			studiengangsauswahlArchiveService.archive(stgId, "Archiviert");
			// TODO: ggf. Loeschen aus Tabelle?
		} else {
			logger.error("Kein Archiv zum ECTS-Bogen des Studienganges " + shorttext + " angelegt.");
		}

		return home(model);
	}

	/**
	 * Ruft Methode auf zum Deaktivieren oder Aktivieren einen ECTS-Bogen bzw. zur
	 * Veränderung des Aktiv-Kennzeichens eines ECTS-Bogens
	 *
	 * @param mdl
	 * @param stgId
	 * @param action
	 * @return Listenansicht der Studiengänge
	 */
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String deActivState(ModelMap mdl, @RequestParam("stgId") Long stgId, @RequestParam("action") String action) {

		Studiengangsauswahl studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

		if (action.matches("vorab")) {
			if (studiengangsauswahl.getAktivVorab() == 1) {
				studiengangsauswahl.setAktivVorab(0);
				studiengangsauswahlService.update(studiengangsauswahl);
				studiengangsauswahlArchiveService.archive(stgId, "Für Vorab-Check deaktiviert");
			} else {
				studiengangsauswahl.setAktivVorab(1);
				studiengangsauswahlService.update(studiengangsauswahl);
			}
		} else if (action.matches("vervollst")) {
			if (studiengangsauswahl.getAktivVervollst() == 1) {
				studiengangsauswahl.setAktivVervollst(0);
				studiengangsauswahlService.update(studiengangsauswahl);
				studiengangsauswahlArchiveService.archive(stgId, "Für BewVervollst deaktiviert");
			} else {
				studiengangsauswahl.setAktivVervollst(1);
				studiengangsauswahlService.update(studiengangsauswahl);
			}
		}

		return home(mdl);
	}

	/**
	 * Ruft Methoden für einzelne Request-Parameters auf: Löschen & Aktualisieren.
	 * Außerdem die Methode zum Prozentsatz aktualisieren
	 *
	 * @param model
	 * @param bereich
	 * @param fach
	 * @param studiengangsauswahl
	 * @param extra
	 * @param requestParams
	 * @param stgId
	 * @param shorttext
	 * @param request
	 * @return Bearbeitungsansicht des ECTS-Bogens
	 */
	@RequestMapping(value = { "/delete", "/update", "/updateProzent" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public String delete(ModelMap model, Bereich bereich, Fach fach, Studiengangsauswahl studiengangsauswahl,
			Extra extra, @RequestParam Map<String, String> requestParams, @RequestParam("stgId") Long stgId,
			@RequestParam("shorttext") String shorttext, HttpServletRequest request) {

		String requestPath = request.getServletPath();

		/** Bereich **/
		if (requestParams.containsKey("bereichId") && !requestParams.containsKey("fachId")) {
			Long bereichId = Long.parseLong(requestParams.get("bereichId"));

			switch (requestPath) {
			case "/vorabcheck/admin/delete":
				List<Fach> fachListe = fachService.findAllByBereichId(bereichId);

				if (!fachListe.isEmpty() && fachListe != null) {
					for (Fach delFach : fachListe) {
						fachService.delete(delFach.getId());
					}
				}

				bereichService.delete(bereichId);
				break;
			case "/vorabcheck/admin/update":
				String zusatz = bereich.getZusatz() == "" || bereich.getZusatz().isEmpty() ? null : bereich.getZusatz();
				bereich.setZusatz(zusatz);

				bereichService.update(bereich);
				break;
			default:
				break;
			}

		}
		/** Fach **/
		if (requestParams.containsKey("fachId")) {
			logger.debug("vac_fachId_requestPath: " + requestPath);
			Long fachId = Long.parseLong(requestParams.get("fachId"));

			switch (requestPath) {
			case "/vorabcheck/admin/delete":
				fachService.delete(fachId);
				break;

			case "/vorabcheck/admin/update":
				logger.debug("vac_update_requestPath: " + requestPath);
				String zusatz = fach.getZusatz() == "" || fach.getZusatz().isEmpty() ? null : fach.getZusatz();
				fach.setZusatz(zusatz);

				logger.debug("vac_update_fach: " + fach);

				fachService.update(fach);
				break;

			default:
				break;
			}

		}

		/** Extra bzw. zusätzliche Leistung **/
		if (requestParams.containsKey("extraId")) {
			Long extraId = Long.parseLong(requestParams.get("extraId"));
			studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

			switch (requestPath) {
			case "/vorabcheck/admin/delete":
				extraService.delete(extraId);
				studiengangsauswahl.setExtrakz(null);
				studiengangsauswahlService.update(studiengangsauswahl);
				break;

			case "vorabcheck/admin/update":
				extraService.update(extra);
				break;

			default:
				break;
			}
		}

		/** Extra-Titel **/
		if (requestParams.containsKey("extraTitel")) {
			studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

			switch (requestPath) {
			case "/vorabcheck/admin/delete":
				studiengangsauswahl.setExtratitel(null);
				studiengangsauswahl.setExtrakz(null);
				studiengangsauswahlService.update(studiengangsauswahl);
				break;

			case "/vorabcheck/admin/update":
				String extrakz = studiengangsauswahl.getExtrakz() == null || studiengangsauswahl.getExtrakz() == "0"
						? "1"
						: "0";
				studiengangsauswahl.setExtrakz(extrakz);

				studiengangsauswahl.setExtratitel(extra.getTitel());
				studiengangsauswahlService.update(studiengangsauswahl);
				break;

			default:
				break;
			}
		}

		/** Prozentsatz **/
		if (requestPath.equals("/vorabcheck/admin/updateProzent")) {
			studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

			if (studiengangsauswahl.getStandardkz() == null) {
				Integer prozent = Integer.parseInt(requestParams.get("prozentsatz"));
				studiengangsauswahl.setProzentsatz(prozent);
				studiengangsauswahlService.update(studiengangsauswahl);
			} else if (studiengangsauswahl.getStandardkz().contains("N")) {
				/**
				 * Standardkz == N ist für Studiengänge ohne ECTS-Bogen (N = Null), Falls doch
				 * ein Bogen angelegt wird, muss Prozentsatz mit 75 und Standardkz mit null
				 * belegt werden
				 */
				studiengangsauswahl.setProzentsatz(75);
				studiengangsauswahl.setStandardkz(null);
				studiengangsauswahlService.update(studiengangsauswahl);
			}

		}

		return insert(model, stgId, shorttext);
	}

	/**
	 * Holt zur Darstellung alle Studiengänge inkl. der entsprechenden Archiv-IDs
	 *
	 * @param mdl
	 * @return Listenansicht der Studiengänge
	 */
	@RequestMapping(value = { "/", "/studiengaenge" }, method = RequestMethod.GET)
	public String home(ModelMap mdl) {

		mdl.put("bereichStgIds", studiengangsauswahlService.findBereichStgId());
		mdl.put("stg", studiengangsauswahlService.findAllStg());
		mdl.put("stgMD", studiengangsauswahlService.findAllStgMD());
		mdl.put("stgAOS", studiengangsauswahlService.findAllStgAOS());
		mdl.put("locale", LocaleContextHolder.getLocale());
		mdl.put("archivOriginIds", studiengangsauswahlArchiveService.findArchiveOriginIds());

		return "vorabcheck/admin/studiengaenge";
	}

	/**
	 * Ruft Seite auf zum Anlegen / Modifizieren eines ECTS-Bogens
	 *
	 * @param mdl
	 * @param stgId
	 * @param shorttext
	 * @return Bearbeitungsansicht des ECTS-Bogens
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(ModelMap mdl, @RequestParam("stgId") Long stgId, @RequestParam("shorttext") String shorttext) {

		Studiengangsauswahl studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

		mdl.put("stgId", stgId);
		mdl.put("stgName", shorttext);
		mdl.put("bereiche", bereichService.findAll());
		mdl.put("faecher", fachService.findAll());
		mdl.put("extraTitel", studiengangsauswahl.getExtratitel());
		mdl.put("leistungen", extraService.findAll());
		mdl.put("standardkz", studiengangsauswahl.getStandardkz());
		mdl.put("prozentsatz", studiengangsauswahl.getProzentsatz());

		return "vorabcheck/admin/insert";
	}

	/**
	 * Ruft Methode auf zum Einfügen eines Bereichs in den entsprechenden
	 * Studiengangsbogen
	 *
	 * @param model
	 * @param bereich
	 * @param studiengangsauswahl
	 * @param stgId
	 * @param shorttext
	 * @return Bearbeitungsansicht des ECTS-Bogens
	 */
	@RequestMapping(value = "/insertBereich", method = RequestMethod.POST)
	public String insertBereich(ModelMap model, Bereich bereich, Studiengangsauswahl studiengangsauswahl,
			@RequestParam("stgId") Long stgId, @RequestParam("shorttext") String shorttext) {

		// TODO: Position implementieren, in DB Attribut pos
		studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

		if (studiengangsauswahl.getStandardkz() != null && studiengangsauswahl.getStandardkz().contains("N")) {
			/**
			 * Standardkz == N ist für Studiengänge ohne ECTS-Bogen (N = Null), Falls doch
			 * ein Bogen angelegt wird, muss Prozentsatz mit 75 und Standardkz mit null
			 * belegt werden
			 */
			studiengangsauswahl.setProzentsatz(75);
			studiengangsauswahl.setStandardkz(null);
			studiengangsauswahlService.update(studiengangsauswahl);
		}

		/**
		 * Wenn "Zusatz" nicht überprüft und nicht auf null gesetzt wird, werden in der
		 * Datenbank zwei Apostrophen gespeichert. Bei "Zusatz" für Bereich gibt es
		 * nämlich eine Dropdown-Auswahl, wo keine NULL-Werte möglich sind, nur leere
		 * Strings.
		 **/
		String zusatz = bereich.getZusatz() == "" || bereich.getZusatz().isEmpty() ? null : bereich.getZusatz();
		bereich.setZusatz(zusatz);

		bereichService.insert(bereich);

		return insert(model, stgId, shorttext);
	}

	/**
	 * Ruft Methode auf zum Einfügen eines zusätzlichen Bemerkungsfeldes in den
	 * entsprechenden Studiengangsbogen
	 *
	 * @param model
	 * @param extra
	 * @param studiengangsauswahl
	 * @param stgId
	 * @param shorttext
	 * @return Bearbeitungsansicht des ECTS-Bogens
	 */
	@RequestMapping(value = "/insertExtra", method = RequestMethod.POST)
	public String insertExtra(ModelMap model, Extra extra, Studiengangsauswahl studiengangsauswahl,
			@RequestParam("stgId") Long stgId, @RequestParam("shorttext") String shorttext) {

		studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);
		Object extrakz = studiengangsauswahl.getExtrakz();

		if (extrakz == null || extrakz == "0") {
			studiengangsauswahl.setExtrakz("1");
			studiengangsauswahlService.update(studiengangsauswahl);
		}

		extraService.insert(extra);

		return insert(model, stgId, shorttext);
	}

	/**
	 * Ruft Methode auf zum Einfügen eines Titels für zusätzliches Bemerkungsfeld in
	 * den entsprechenden Studiengangsbogen
	 *
	 * @param model
	 * @param extra
	 * @param studiengangsauswahl
	 * @param stgId
	 * @param shorttext
	 * @return Bearbeitungsansicht des ECTS-Bogens
	 */
	@RequestMapping(value = "/insertExtraTitel", method = RequestMethod.POST)
	public String insertExtraTitel(ModelMap model, Extra extra, Studiengangsauswahl studiengangsauswahl,
			@RequestParam("stgId") Long stgId, @RequestParam("shorttext") String shorttext) {

		studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

		Object extrakz = studiengangsauswahl.getExtrakz();
		if (extrakz == null || extrakz == "0") {
			studiengangsauswahl.setExtrakz("1");
		}
		studiengangsauswahl.setExtratitel(extra.getTitel());
		studiengangsauswahlService.update(studiengangsauswahl);

		return insert(model, stgId, shorttext);
	}

	/**
	 * Ruft Methode auf zum Einfügen eines Fachs unter dem entsprechenden Bereich
	 *
	 * @param model
	 * @param fach
	 * @param stgId
	 * @param shorttext
	 * @return Bearbeitungsansicht des ECTS-Bogens
	 */
	@RequestMapping(value = "/insertFach", method = RequestMethod.POST)
	public String insertFach(ModelMap model, Fach fach, @RequestParam("stgId") Long stgId,
			@RequestParam("shorttext") String shorttext) {

		// TODO: Position implementieren, in DB Attribut pos
		String zusatz = fach.getZusatz() == "" || fach.getZusatz().isEmpty() ? null : fach.getZusatz();
		fach.setZusatz(zusatz);

		fachService.insert(fach);

		return insert(model, stgId, shorttext);
	}

	/**
	 * Methode, um festzulegen, dass zu einem Studiengang kein ECTs-Bogen existiert
	 * bzw. existieren soll
	 *
	 * @param model
	 * @param studiengangsauswahl
	 * @param requestParams
	 * @param stgId
	 * @param shorttext
	 * @param request
	 * @return Listenansicht der Studiengänge
	 */
	@RequestMapping(value = "/nullify", method = RequestMethod.GET)
	public String nullify(ModelMap model, Studiengangsauswahl studiengangsauswahl,
			@RequestParam Map<String, String> requestParams, @RequestParam("stgId") Long stgId,
			@RequestParam("shorttext") String shorttext, HttpServletRequest request) {

		studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);
		studiengangsauswahl.setStandardkz("N");
		try {
			studiengangsauswahlService.update(studiengangsauswahl);
		} catch (Exception e) {
			logger.error("Hat nicht geklappt: Keinen ECTS-Bogen zuweisen", e);
		}

		return home(model);
	}

	/**
	 * Ruft Seiten auf zum Anzeigen oder Modifizieren von ECTS-Bögen (anhand des
	 * RequestMapping-Werts)
	 *
	 * @param mdl
	 * @param stgId
	 * @param shorttext
	 * @param request
	 * @return entweder Lese-Ansicht oder Bearbeitungsansicht des ECTS-Bogens
	 */
	@RequestMapping(value = { "/show", "/modify" }, method = RequestMethod.GET)
	public String show(ModelMap mdl, @RequestParam("stgId") Long stgId, @RequestParam("shorttext") String shorttext,
			HttpServletRequest request) {

		Studiengangsauswahl studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);

		mdl.put("bereich", bereichService.findAll());
		mdl.put("fach", fachService.findAll());
		mdl.put("leistungen", extraService.findAll());
		mdl.put("stgId", stgId);
		mdl.put("stgName", shorttext);
		mdl.put("extraTitel", studiengangsauswahl.getExtratitel());
		mdl.put("leistungen", extraService.findAll());
		mdl.put("prozentsatz", studiengangsauswahl.getProzentsatz());
		mdl.put("extrakz", studiengangsauswahl.getExtrakz());

		String requestPath = request.getServletPath();

		switch (requestPath) {
		case "/vorabcheck/admin/show":
			return "vorabcheck/admin/show";
		case "/vorabcheck/admin/modify":
			return insert(mdl, stgId, shorttext);
		default:
			return null;
		}
	}

	/**
	 * Ruft Ansicht auf zur Darstellung von archivierten ECTS-Bögen
	 *
	 * @param mdl
	 * @param stgId
	 * @param shorttext
	 * @param sessionId
	 * @param request
	 * @return Seite mit archivierten Studiengängen
	 */
	@RequestMapping(value = "/showArchive", method = RequestMethod.GET)
	public String showArchive(ModelMap mdl, @RequestParam("stgId") Long stgId,
			@RequestParam("shorttext") String shorttext,
			@RequestParam(value = "sessionId", required = false) Long sessionId, HttpServletRequest request) {

		mdl.put("size", studiengangsauswahlArchiveService.findAllByOriginId(stgId).size());
		mdl.put("stg", studiengangsauswahlArchiveService.findAllByOriginId(stgId));
		mdl.put("bereich", bereichArchiveService.findAll());
		mdl.put("fach", fachArchiveService.findAll());
		mdl.put("stgName", shorttext);
		Boolean sessionIsSet = sessionId != null ? true : false;

		StudiengangsauswahlArchive studiengangsauswahlArchive = null;
		if (sessionIsSet) {
			try {
				studiengangsauswahlArchive = studiengangsauswahlArchiveService.fetchById(stgId);
			} catch (Exception e) {
				logger.error("Konnte Studiengangsarchiv " + shorttext + " nicht abrufen, " + e);
			}
		} else {
			try {
				studiengangsauswahlArchive = studiengangsauswahlArchiveService.fetchByOriginId(stgId);
			} catch (Exception e) {
				logger.error("Konnte Archive von Studiengang " + shorttext + " nicht finden, " + e);
			}
		}
		mdl.put("prozentsatz", studiengangsauswahlArchive.getProzentsatz());
		mdl.put("sessionId", studiengangsauswahlArchive.getSessionId());
		mdl.put("stgId", studiengangsauswahlArchive.getId());
		mdl.put("bearbeiter", studiengangsauswahlArchive.getBearbeiter());
		mdl.put("grund", studiengangsauswahlArchive.getGrund());
		mdl.put("datum", new SimpleDateFormat("dd-MM-yyyy").format(studiengangsauswahlArchive.getZeitstempel()));
		mdl.put("originStgId", studiengangsauswahlArchive.getOriginId());
		mdl.put("sessionIsSet", sessionIsSet);
		mdl.put("standardkz", studiengangsauswahlArchive.getStandardkz());

		return "vorabcheck/admin/showArchive";
	}

	@RequestMapping(value = "/vervollstTest", method = RequestMethod.GET)
	public String vervollstTest() {

		return "vorabcheck/admin/vervollstForm";
	}

	@RequestMapping(value = "/vervollstTest", method = RequestMethod.POST)
	public void vervollstTestPost(@RequestParam("file") MultipartFile file, HttpServletResponse response,
			ModelMap model) {

		try {
			InMemoryResource inFile = new InMemoryResource(file.getName());
			IOUtils.copy(file.getInputStream(), inFile.getOutputStream());
			Resource outFile = bewVervollstController.generatePdf(inFile);

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + outFile + ".pdf");
			IOUtils.copy(outFile.getInputStream(), response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {

			logger.error("DownloadError", e);
		}
	}
}
