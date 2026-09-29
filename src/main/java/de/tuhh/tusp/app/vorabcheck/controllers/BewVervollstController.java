package de.tuhh.tusp.app.vorabcheck.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.CryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.app.vorabcheck.pojo.EctsForm;
import de.tuhh.tusp.app.vorabcheck.pojo.Extra;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.app.vorabcheck.pojo.Studiengangsauswahl;
import de.tuhh.tusp.app.vorabcheck.service.BereichService;
import de.tuhh.tusp.app.vorabcheck.service.BewerberService;
import de.tuhh.tusp.app.vorabcheck.service.EctsService;
import de.tuhh.tusp.app.vorabcheck.service.ExtraService;
import de.tuhh.tusp.app.vorabcheck.service.FachService;
import de.tuhh.tusp.app.vorabcheck.service.StudiengangsauswahlService;
import de.tuhh.tusp.app.vorabcheck.utils.pdf.de.EctsBogenDownload;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.pdf.PdfUtils;

@Controller
@RequestMapping(value = "/bewVervollst")
@SessionAttributes("bewVervollst")
@AccessConfig(path = "/bewVervollst/**", access = { Rollen.PUBLIC })

public class BewVervollstController {

	public class NoAttachmentException extends RuntimeException {

		private static final long serialVersionUID = -23846355535152018L;

	}

	private static Logger logger = LogManager.getLogger(BewVervollstController.class);

	@Autowired
	private ConfigService configService;

	@Autowired
	private StudiengangsauswahlService studiengangsauswahlService;

	@Autowired
	private BewerberService bewerberService;

	@Autowired
	private BereichService bereichService;

	@Autowired
	private FachService fachService;

	@Autowired
	private EctsService ectsService;

	@Autowired
	private ExtraService extraService;

	private Date zeitstempel;

	@Autowired
	private PdfUtils pdfUtils;

	/**
	 * Erzeugt eine InMemoryResource mit nicht erratbarem Namen.
	 *
	 * @param stgName
	 * @return die Resource
	 */
	private InMemoryResource createOutResource(String stgName) {

		zeitstempel = new Date();
		String xmlHash = bewerberService.hashCode(stgName, zeitstempel);
		String xmlFileName = "ects_" + xmlHash + ".xml";
		return new InMemoryResource(xmlFileName);
	}

	/**
	 * Ruft Methode auf zum Downloaden des PDFs seitens der/s Bewerbers/in
	 *
	 * @param bewerber
	 * @return ModelAndView / übergibt Daten an die PDF
	 * @throws JAXBException unwahrscheinlich
	 * @throws IOException   nie
	 */
	@RequestMapping(value = "/aos/downloadPdf", method = RequestMethod.POST)
	public ModelAndView downloadAosPdf(@ModelAttribute("bewerber") Bewerber bewerber,
			@ModelAttribute("ectsForm") EctsForm ectsForm, Ects obj) throws JAXBException, IOException {

		List<Ects> listEcts = new ArrayList<>();

		JAXBContext jc = JAXBContext.newInstance(Bewerber.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		for (Ects ects : ectsForm.getEcts()) {
			ects.setNachweis(StringEscapeUtils.unescapeHtml4(ects.getNachweis()));
			listEcts.add(ects);
		}

		Studiengangsauswahl stg = studiengangsauswahlService.fetchById(bewerber.getStgId());
		bewerber.setStgName(studiengangsauswahlService.getShorttextDe(stg.getCourseofstudyId()));

		bewerber.setEcts(listEcts);
		bewerber.setAnmerk(StringEscapeUtils.unescapeHtml4(bewerber.getAnmerk()));

		WritableResource xmlFile = createOutResource(bewerber.getStgName());
		marshaller.marshal(bewerber, xmlFile.getOutputStream());

		List<Bereich> listBereich = bereichService.findAllByStgId(bewerber.getStgId());
		List<Long> bereichIds = bereichService.getBereichIdsById(bewerber.getStgId());
		List<Fach> listFach = fachService.findAllByBereichIds(bereichIds);
		Map<String, Object> map = new HashMap<>();

		map.put("bew", bewerber);
		map.put("listEcts", listEcts);
		map.put("bereich", listBereich);
		map.put("fach", listFach);
		map.put("xml", xmlFile);
		map.put("setAttachment", false);

		return new ModelAndView("pdfViewEn", "map", map);
	}

	/**
	 * Ruft Methode auf zum Downloaden des MD-PDFs seitens der/s Bewerbers/in
	 *
	 * @param bewerber
	 * @return ModelAndView / übergibt Daten an die PDF
	 * @throws JAXBException unwahrscheinlich
	 * @throws IOException   nie
	 */
	@RequestMapping(value = "/md/downloadPdf", method = RequestMethod.POST)
	public ModelAndView downloadMdPdf(@ModelAttribute("bewerber") Bewerber bewerber,
			@ModelAttribute("ectsForm") EctsForm ectsForm, Ects obj) throws JAXBException, IOException {

		List<Ects> listEcts = new ArrayList<>();
		List<Extra> listExtra = new ArrayList<>();

		JAXBContext jc = JAXBContext.newInstance(Bewerber.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		String einzelErgebnis;
		for (Ects ects : ectsForm.getEcts()) {
			if (ects.getGesamtPunkte() != null && ects.getGesamtPunkte() != 0) {
				einzelErgebnis = studiengangsauswahlService.getColor(ects.getBereichPunkte(), ects.getGesamtPunkte(),
						bewerber.getStgId());
				ects.setErgebnis(einzelErgebnis);
			}

			ects.setErwerb(StringEscapeUtils.unescapeHtml4(ects.getErwerb()));
			ects.setNachweis(StringEscapeUtils.unescapeHtml4(ects.getNachweis()));
			ects.setModul(StringEscapeUtils.unescapeHtml4(ects.getModul()));
			listEcts.add(ects);
		}

		if (ectsForm.getExtra() != null) {
			for (Extra extra : ectsForm.getExtra()) {
				if (extra.getId() != null) {
					extra.setZusatzAusstehend(StringEscapeUtils.unescapeHtml4(extra.getZusatzAusstehend()));
					extra.setZusatzErbracht(StringEscapeUtils.unescapeHtml4(extra.getZusatzErbracht()));
					listExtra.add(extra);
				}
			}

			Collections.sort(listExtra, new Comparator<Extra>() {

				@Override
				public int compare(final Extra object1, final Extra object2) {

					return object1.getName().compareTo(object2.getName());
				}
			});
		}

		String ergebnis = null;
		try {
			ergebnis = studiengangsauswahlService.getResult(ectsForm, bewerber.getStgId());
		} catch (Exception e) {
			logger.error("Konnte ECTS-Ergebnis nicht ermitteln", e);
		}

		Studiengangsauswahl stg = studiengangsauswahlService.fetchById(bewerber.getStgId());

		bewerber.setStgName(studiengangsauswahlService.getShorttextDe(stg.getCourseofstudyId()));
		bewerber.setExtratitel(stg.getExtratitel());
		bewerber.setErgebnis(ergebnis);
		bewerber.setEcts(listEcts);
		if (listExtra != null) {
			bewerber.setExtra(listExtra);
		}
		bewerber.setAnmerk(StringEscapeUtils.unescapeHtml4(bewerber.getAnmerk()));

		WritableResource xmlFile = createOutResource(bewerber.getStgName());

		marshaller.marshal(bewerber, xmlFile.getOutputStream());

		Map<String, Object> map = new HashMap<>();

		map.put("bew", bewerber);
		map.put("ergebnis", ergebnis);
		map.put("listEcts", listEcts);
		if (listExtra != null) {
			map.put("extra", listExtra);
		}
		List<Bereich> listBereich = bereichService.findAllByStgId(bewerber.getStgId());
		map.put("bereich", listBereich);
		List<Long> bereichIds = bereichService.getBereichIdsById(bewerber.getStgId());
		List<Fach> listFach = fachService.findAllByBereichIds(bereichIds);
		map.put("fach", listFach);
		map.put("xml", xmlFile);
		map.put("setAttachment", true);

		return new ModelAndView("pdfViewDe", "map", map);
	}

	/**
	 * Ruft Seite auf für AOS-Bewerber zum Eintragen der ECTS sowie weiteren
	 * Bemerkungen
	 *
	 * @param bewerber
	 * @param mdl
	 * @param model
	 * @return Seite mit dem ECTS-Formular
	 */
	@RequestMapping(value = "/aos/ects", method = RequestMethod.POST)
	public String ectsAos(@ModelAttribute("bewerber") Bewerber bewerber, ModelMap mdl, Model model) {

		model.addAttribute("stgName", bewerber.getStgName());
		model.addAttribute("stgId", bewerber.getStgId());

		Long stgId = bewerber.getStgId();
		Studiengangsauswahl studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);
		bewerber.setStgName(studiengangsauswahlService.getShorttextDe(studiengangsauswahl.getCourseofstudyId()));

		List<Long> bereichIds = bereichService.getBereichIdsById(stgId);
		mdl.put("bereich", studiengangsauswahlService.findAllBereichById(stgId));
		mdl.put("fach", studiengangsauswahlService.findAllFachByIds(bereichIds));

		mdl.put("extrakz", studiengangsauswahl.getExtrakz());
		mdl.put("extraTitel", studiengangsauswahl.getExtratitel());
		mdl.put("leistungen", extraService.findAll());

		/** Infotext */
		List<String> list = new ArrayList<>();
		try {
			ClassPathResource infoText = new ClassPathResource("bewVervollstInfo/en/creditsPdfInfo.txt");
			BufferedReader br = new BufferedReader(new FileReader(infoText.getFile()));

			String currentLine;

			while ((currentLine = br.readLine()) != null) {
				if (currentLine.contains("bewerber.getStgName()")) {

					currentLine = currentLine.replace("bewerber.getStgName()",
							"\"" + bewerber.getStgName().toString() + "\"");
				}

				list.add(currentLine);
			}

			br.close();

		} catch (IOException e) {
			logger.error("Konnte Infotext nicht einlesen / verarbeiten: " + e);
		}

		mdl.put("infotext", list);

		return "bewVervollst/aos/ects";
	}

	/**
	 * Ruft Seite auf für MD-Bewerber zum Eintragen der ECTS sowie weiteren
	 * Bemerkungen
	 *
	 * @param bewerber
	 * @param mdl
	 * @param model
	 * @return Seite mit dem ECTS-Formular
	 */
	@RequestMapping(value = "/md/ects", method = RequestMethod.POST)
	public String ectsMd(@ModelAttribute("bewerber") Bewerber bewerber, ModelMap mdl, Model model) {

		model.addAttribute("codenummer", bewerber.getCodenummer());
		model.addAttribute("stgName", bewerber.getStgName());
		model.addAttribute("stgId", bewerber.getStgId());

		Long stgId = bewerber.getStgId();
		Studiengangsauswahl studiengangsauswahl = studiengangsauswahlService.fetchById(stgId);
		bewerber.setStgName(studiengangsauswahlService.getShorttextDe(studiengangsauswahl.getCourseofstudyId()));

		List<Long> bereichIds = bereichService.getBereichIdsById(stgId);
		mdl.put("bereich", studiengangsauswahlService.findAllBereichById(stgId));
		mdl.put("fach", studiengangsauswahlService.findAllFachByIds(bereichIds));
		mdl.put("ects", null);

		mdl.put("extrakz", studiengangsauswahl.getExtrakz());
		mdl.put("extraTitel", studiengangsauswahl.getExtratitel());
		mdl.put("leistungen", extraService.findAll());

		return "bewVervollst/md/ects";
	}

	/**
	 * Versucht aus einer Ressource (mit einem Ectsbogen mit Attachment) ein PDF zu
	 * machen, in dem die Ampel abgebildet ist
	 *
	 * @param resource Resource mit PDF und Attachment
	 * @return Ressource mit Ampel-ECTS-Bogen
	 * @throws JAXBException         Wenn das Attachment nicht geparst werden kann
	 * @throws IOException           Wenn etwas nicht lesbar ist
	 * @throws SAXParseException     Wenn das Attachment nicht geparst werden kann
	 * @throws SAXException          Wenn das Attachment nicht geparst werden kann
	 * @throws XMLStreamException    Wenn das Attachment nicht geparst werden kann
	 * @throws DocumentException     Wenn das PDF fehlerhaft ist
	 * @throws NoAttachmentException Wenn kein Attachment vorhanden ist
	 * @throws RuntimeException      Wenn allgemeine Fehler auftreten
	 */
	public InMemoryResource generatePdf(Resource resource) throws JAXBException, IOException, SAXParseException,
			SAXException, XMLStreamException, DocumentException, NoAttachmentException, RuntimeException {

		PdfReader reader = new PdfReader(ByteStreams.toByteArray(resource.getInputStream()));
		List<Resource> attachedFiles = new ArrayList<>();
		Resource attachFile = null;

		try {
			attachedFiles = pdfUtils.readAttachments(reader);
			attachFile = attachedFiles.get(0);
		} catch (IndexOutOfBoundsException e) {
			throw new NoAttachmentException();
		} catch (Exception e) {
			throw new RuntimeException("Konnte keine Anhänge lesen.", e);
		}

		String key = configService.get("de.tuhh.tusp.global.crypt_key", UUID.randomUUID().toString().substring(0, 15),
				true);

		try {
			attachFile = pdfUtils.decrypt(key, attachFile);
		} catch (CryptoException e) {
			throw new RuntimeException("Konnte Datei nicht entschlüsseln", e);
		}

		Bewerber bew = null;

		try {
			JAXBContext jContext = JAXBContext.newInstance(Bewerber.class);
			Unmarshaller unmarshaller = jContext.createUnmarshaller();
			bew = (Bewerber) unmarshaller.unmarshal(attachFile.getInputStream());
		} catch (Exception e) {
			throw new RuntimeException("Konnte Anhang / XML nicht entpacken", e);
		}

		List<Ects> listEcts = new ArrayList<>();

		for (Ects ects : bew.getEcts()) {
			listEcts.add(ects);
		}

		List<Extra> listExtra = new ArrayList<>();
		if (bew.getExtra() != null) {
			for (Extra extra : bew.getExtra()) {
				if (extra.getId() != null) {
					listExtra.add(extra);
				}
			}
		}

		Map<String, Object> map = new HashMap<>();
		List<Bereich> listBereich = bereichService.findAllByStgId(bew.getStgId());
		List<Long> bereichIds = bereichService.getBereichIdsById(bew.getStgId());
		List<Fach> listFach = fachService.findAllByBereichIds(bereichIds);

		map.put("bew", bew);
		map.put("listEcts", listEcts);
		map.put("bereich", listBereich);
		map.put("fach", listFach);
		if (listExtra != null) {
			map.put("extra", listExtra);
		}
		map.put("setAttachment", false);
		map.put("setAmpelsystem", true);

		Document doc = new Document(PageSize.A4);

		InMemoryResource result = new InMemoryResource(bew.getErgebnis());
		PdfWriter writer = PdfWriter.getInstance(doc, result.getOutputStream());
		writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);

		doc.open();
		EctsBogenDownload bogen = new EctsBogenDownload();
		bogen.bewerberdatenEinfuegen(map, doc, true);
		bogen.ectsDatenEinfuegen(map, doc, true);
		doc.close();

		return result;
	}

	/**
	 * Setzt eine Form auf für ECTS für die Webview & die Übermittlung der Werte
	 *
	 * @return Objekt von EctsForm
	 */
	@ModelAttribute("ectsForm")
	public EctsForm populateEcts() {

		EctsForm ectsForm = new EctsForm();
		List<Ects> ects = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			ects.add(new Ects());
		}
		ectsForm.setEcts(ects);

		return ectsForm;
	}

	/**
	 * Weiterleitung des Vorab-Check-Codelinks aus der E-Mail
	 *
	 * @param model
	 * @param mdl
	 * @return Startseite
	 */
	@RequestMapping(value = "/codelink", method = { RequestMethod.GET, RequestMethod.POST })
	public String receiveCodeLink(@ModelAttribute("bewerber") Bewerber bewerber, Model model, ModelMap mdl,
			@RequestParam Map<String, String> requestParams, @RequestParam("bewhash") String bewHash,
			HttpServletRequest request) {

		String hashCodenummer = null;
		try {
			hashCodenummer = bewerberService.fetchByCode(bewHash).getCodenummer();
		} catch (Exception e) {
			logger.error("Keine Codenummer zum Hash gefunden", e);
		}

		if (hashCodenummer == null) {
			model.addAttribute("bewerber", new Bewerber());
			mdl.put("stg", studiengangsauswahlService.findAllActiveVervollstWithoutStandardkz());
			mdl.put("redirect", "codeNull");
			return "bewVervollst/md/start";
		} else if (bewHash.matches(hashCodenummer)) {
			Bewerber bew = bewerberService.fetchByCode(bewHash);
			if (studiengangsauswahlService.fetchById(bew.getStgId()).getAktivVervollst() == 0) {
				mdl.put("stg", studiengangsauswahlService.findAllActiveVervollstWithoutStandardkz());
				mdl.put("redirect", "stgNull");
				return "bewVervollst/md/start";
			} else {
				bewerber.setCodenummer(bew.getCodenummer());
				bewerber.setStgName(bewerberService.findStgNameByCode(bewHash).toString());
				bewerber.setStgId(bew.getStgId());

				return ectsMd(bewerber, mdl, model);
			}
		} else {
			return "bewVervollst/md/start";
		}
	}

	/**
	 * Spezial-URL für die ECTS-Bögen zu AOS-Studiengängen
	 *
	 * @param bewerber
	 * @param model
	 * @param mdl
	 * @return view name
	 */
	@RequestMapping(value = "/aos", method = { RequestMethod.GET, RequestMethod.POST })
	public String showEctsAos(@ModelAttribute("bewerber") Bewerber bewerber, Model model, ModelMap mdl) {

		model.addAttribute("bewerber", new Bewerber());
		mdl.put("stg", studiengangsauswahlService.findAllStg());

		return "bewVervollst/aos/start";
	}

	/**
	 * Startseite der Bewerbungsvervollständigung
	 *
	 * @param model
	 * @param mdl
	 * @return Startseite
	 */
	@RequestMapping(value = { "/", "/start" }, method = RequestMethod.GET)
	public String start(Model model, ModelMap mdl) {

		model.addAttribute("bewerber", new Bewerber());
		mdl.put("stg", studiengangsauswahlService.findAllStg());

		return "bewVervollst/md/start";
	}
}
