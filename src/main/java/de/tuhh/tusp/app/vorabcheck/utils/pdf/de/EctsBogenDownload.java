package de.tuhh.tusp.app.vorabcheck.utils.pdf.de;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.bouncycastle.crypto.CryptoException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.app.vorabcheck.pojo.Extra;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.pdf.AbstractNewPdfView;
import de.tuhh.tusp.utils.pdf.ExtendedPdfPTable;
import de.tuhh.tusp.utils.pdf.PdfUtils;

public class EctsBogenDownload extends AbstractNewPdfView {

	private PdfUtils pdfUtils;

	private ConfigService configService;

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void addAttachmentToWriter(Map<String, Object> model, PdfWriter writer) throws IOException {

		Map<String, Object> map = (Map<String, Object>) model.get("map");
		Resource attachment = (Resource) map.get("xml");

		pdfUtils = getApplicationContext().getBean(PdfUtils.class);
		configService = getApplicationContext().getBean(ConfigService.class);

		String key = configService.get("de.tuhh.tusp.global.crypt_key");

		try {
			attachment = pdfUtils.encrypt(key, attachment);
		}
		catch (CryptoException e) {
			logger.error("Konnte Anhang nicht verschlüsseln: " + e);
		}

		pdfUtils.addAttachment(writer, attachment);
	}

	/**
	 * Daten des Bewerbers, d.h. Studiengang & Anmerkungen, sowie den Infotext
	 * einfügen
	 *
	 * @param map
	 * @param doc
	 * @param ampelsystemExists
	 * @throws DocumentException
	 *             wenn bei der PDF Erzeugung Probleme sind
	 */
	public void bewerberdatenEinfuegen(	Map<String, Object> map, Document doc,
										Boolean setAmpelsystem) throws DocumentException {

		/** Bewerberdaten */
		ExtendedPdfPTable table = newTable(2);
		table.setWidthPercentage(50.0f);
		table.setWidths(new float[] { 1, 3 });
		table.setSpacingBefore(10);
		table.setSpacingAfter(20);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);

		PdfPCell dataCell = new PdfPCell();
		dataCell.setRowspan(1);
		dataCell.setPadding(3);

		Font regularFont = new Font(Font.FontFamily.TIMES_ROMAN, 8);
		Font ampelsystemFont = new Font(Font.FontFamily.TIMES_ROMAN, 9);

		Bewerber bewerber = (Bewerber) map.get("bew");

		table.addCell("Studiengang", regularFont, dataCell);
		table.addCell(bewerber.getStgName(), regularFont, dataCell);

		doc.add(table);

		if (setAmpelsystem != null && setAmpelsystem == true) {
			String ampelsystem = null;
			doc.add(Chunk.NEWLINE);
			ampelsystemFont.setStyle(Font.BOLD);
			ampelsystemFont.setSize(12);

			if ("gruen".equals(bewerber.getErgebnis())) {
				ampelsystem = "Grün";
				ampelsystemFont.setColor(BaseColor.GREEN);
			}
			else if ("gelb".equals(bewerber.getErgebnis())) {
				ampelsystemFont.setColor(BaseColor.ORANGE);
				ampelsystem = "Gelb";
			}
			else if ("rot".equals(bewerber.getErgebnis())) {
				ampelsystem = "Rot";
				ampelsystemFont.setColor(BaseColor.RED);
			}
			else {
				ampelsystem = "Undefiniert";
				ampelsystemFont.setColor(BaseColor.BLUE);
			}

			doc.add(new Paragraph("Ampel-System: " + ampelsystem, ampelsystemFont));
		}

		doc.add(Chunk.NEWLINE);
		doc.add(new Paragraph("Freiraum für Anmerkungen:", regularFont));

		if (bewerber.getAnmerk() != null && !bewerber.getAnmerk().isEmpty()) {
			doc.add(new Paragraph(bewerber.getAnmerk(), regularFont));
		}
		else {
			doc.add(new Paragraph("Keine Anmerkungen", regularFont));
		}
		doc.add(Chunk.NEWLINE);
	}

	/**
	 * PDF-Dokument erstellen
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc) throws Exception {

		Map<String, Object> map = (Map<String, Object>) model.get("map");
		Bewerber bewerber = (Bewerber) map.get("bew");

		addHttpHeader("Content-Disposition", "attachment;filename=\"ECTS-Bogen_" + bewerber.getStgName() + ".pdf\"");

		bewerberdatenEinfuegen(map, doc, (Boolean) map.get("setAmpelsystem"));

		ectsDatenEinfuegen(map, doc, (Boolean) map.get("setAmpelsystem"));
	}

	/**
	 * ECTS-Daten in einer Tabelle einfügen und ggf. Zusatzleistungen in einer
	 * zusätzlichen Tabelle aufführen
	 *
	 * @param map
	 * @param doc
	 * @throws DocumentException
	 *             wenn bei der PDF Erzeugung Probleme sind
	 */
	@SuppressWarnings("unchecked")
	public void ectsDatenEinfuegen(	Map<String, Object> map, Document doc,
									Boolean setAmpelsystem) throws DocumentException {

		ExtendedPdfPTable table = newTable(8);
		table.setWidthPercentage(110.0f);
		table.setWidths(new float[] { 2.0f, 3.0f, 1.5f, 1.1f, 1.5f, 1.5f, 1.5f, 1.5f });
		table.setSpacingBefore(10);

		PdfPCell alignCenterCell = new PdfPCell();
		alignCenterCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		alignCenterCell.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell alignLeftCell = new PdfPCell();
		alignLeftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		alignLeftCell.setVerticalAlignment(Element.ALIGN_LEFT);

		/** Reguläre Schrift */
		Font regularFont = new Font(Font.FontFamily.TIMES_ROMAN, 9);

		/** Fettgedruckte Schrift */
		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);

		/** Kleinere Schrift, z.B. für viel Text */
		Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 7);

		table.addCell("Bereich", regularFont, alignCenterCell);
		table.addCell("Anforderungen", regularFont, alignCenterCell);
		table.addCell("Zusatzinfo", regularFont, alignCenterCell);
		table.addCell("Vorausgesetzte ECTS", smallFont, alignCenterCell);
		table.addCell("Im Vorstudium erbrachte ECTS (1)", smallFont, alignCenterCell);
		table.addCell("Kenntniserwerb ohne Erhalt von ECTS (2)", smallFont, alignCenterCell);
		table.addCell("Zu (1) Vergleiche Angaben im angefügten Modulhandbuch (Seite)", smallFont, alignCenterCell);
		table.addCell("Zu (2) Vergleiche sonstigen beigefügten Nachweis (Seite)", smallFont, alignCenterCell);

		List<Bereich> listBereich = (List<Bereich>) map.get("bereich");
		List<Ects> listEcts = (List<Ects>) map.get("listEcts");
		List<Fach> listFach = (List<Fach>) map.get("fach");
		List<Extra> listExtra = (List<Extra>) map.get("extra");

		/** Durch die Bereiche loopen */
		for (Bereich bereich : listBereich) {
			table.addCell(bereich.getName(), boldFont, alignCenterCell);
			table.addCell(new Phrase());
			if (bereich.getZusatz() == null || bereich.getZusatz().isEmpty()) {
				table.addCell(new Phrase());
			}
			else {
				table.addCell(bereich.getZusatz(), boldFont, alignCenterCell);
			}
			if (bereich.getPunkte() == null) {
				table.addCell(new Phrase());
			}
			else {
				table.addCell(bereich.getPunkte().toString(), boldFont, alignCenterCell);
			}

			Double gesamtPunkte = getEcts(listEcts, bereich, bereich.getId());

			if (gesamtPunkte != null) {
				if (setAmpelsystem != null) {
					if (setAmpelsystem == true) {
						String ergebnis = getErgebnis(listEcts, bereich.getId());

						if (ergebnis != null) {
							if (ergebnis.equals("G")) {
								alignCenterCell.setBackgroundColor(BaseColor.GREEN);
							}
							else if (ergebnis.equals("Y")) {
								alignCenterCell.setBackgroundColor(BaseColor.YELLOW);
							}
							else if (ergebnis.equals("R")) {
								alignCenterCell.setBackgroundColor(BaseColor.RED);
							}
						}
						table.addCell(gesamtPunkte.toString() + "/" + ergebnis, boldFont, alignCenterCell);
						alignCenterCell.setBackgroundColor(null);
					}
				}
				else {
					table.addCell(gesamtPunkte.toString(), boldFont, alignCenterCell);
				}
				table.addCell(new Phrase());
				table.addCell(new Phrase());
				table.addCell(new Phrase());
			}
			else {
				table.fillRow();
			}

			/** Durch die Fächer loopen */
			for (Fach fach : listFach) {
				if (fach.getBereichId().equals(bereich.getId())) {
					table.addCell(new Phrase());
					table.addCell(fach.getName(), regularFont, alignLeftCell);
					table.addCell(fach.getZusatz(), regularFont, alignLeftCell);

					if (fach.getPunkte() != null) {
						table.addCell(fach.getPunkte().toString(), regularFont, alignCenterCell);
					}
					else {
						table.addCell(new Phrase());
					}

					if (listEcts == null || listEcts.size() == 0) {
						table.fillRow();
					}
					else {
						Double fachPunkte = (Double) getPunkte(listEcts, "punkte", fach.getId(), fach.getBereichId());
						if (fachPunkte == null) {
							table.addCell(new Phrase());
						}
						else {
							table.addCell(fachPunkte.toString(), regularFont, alignCenterCell);
						}

						String erwerb = (String) getPunkte(listEcts, "erwerb", fach.getId(), fach.getBereichId());
						if (erwerb == null) {
							table.addCell(new Phrase());
						}
						else {
							table.addCell(erwerb.toString(), regularFont, alignCenterCell);
						}

						String modul = (String) getPunkte(listEcts, "modul", fach.getId(), fach.getBereichId());
						if (modul == null) {
							table.addCell(new Phrase());
						}
						else {
							table.addCell(modul.toString(), regularFont, alignCenterCell);
						}

						String nachweis = (String) getPunkte(listEcts, "nachweis", fach.getId(), fach.getBereichId());
						if (nachweis == null) {
							table.addCell(new Phrase());
						}
						else {
							table.addCell(nachweis.toString(), regularFont, alignCenterCell);
						}
						alignCenterCell.setBackgroundColor(null);
					}
				}

			}
		}

		doc.add(table);
		doc.add(Chunk.NEWLINE);

		Bewerber bewerber = (Bewerber) map.get("bew");
		String strIWI = "IWI";
		if (bewerber.getStgName().toLowerCase().contains(strIWI.toLowerCase())) {

			ExtendedPdfPTable zusatzTable = newTable(3);
			zusatzTable.setWidthPercentage(110.0f);
			zusatzTable.setWidths(new float[] { 2.0f, 3.0f, 3.0f });
			zusatzTable.setSpacingBefore(10);

			// TODO: "Seite 1/2" bzw "Seite 2/2" implementieren
			// TODO: auf zweite Seite verschieben
			doc.newPage();
			doc.add(new Paragraph(bewerber.getExtratitel() + ": ", boldFont));

			zusatzTable.addCell(new Phrase(new Chunk("Mögliche Lehrveranstaltungen: ", regularFont)));
			zusatzTable.addCell(new Phrase(new Chunk("Habe ich bereits erbracht am: ", regularFont)));
			zusatzTable.addCell(new Phrase(new Chunk("Werde ich erbringen am: ", regularFont)));

			for (Extra extra : listExtra) {
				zusatzTable.addCell(extra.getName(), regularFont, alignLeftCell);

				if (!extra.getZusatzErbracht().isEmpty()) {
					zusatzTable.addCell(extra.getZusatzErbracht(), regularFont, alignLeftCell);
				}
				else {
					zusatzTable.addCell(new Phrase());
				}

				if (!extra.getZusatzAusstehend().isEmpty()) {
					zusatzTable.addCell(extra.getZusatzAusstehend(), regularFont, alignLeftCell);
				}
				else {
					zusatzTable.addCell(new Phrase());
				}

			}

			doc.add(zusatzTable);
			doc.add(Chunk.NEWLINE);
		}
		else {
			doc.add(Chunk.NEWLINE);
		}

		/** Infotext */
		try {
			ClassPathResource infoText = new ClassPathResource("bewVervollstInfo/de/ectsPdfInfo.txt");
			BufferedReader br = new BufferedReader(new FileReader(infoText.getFile()));

			String currentLine;

			com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
			list.setIndentationLeft(20);
			list.setListSymbol(new Chunk(""));

			while ((currentLine = br.readLine()) != null) {
				if (currentLine.contains("bewerber.getStgName()")) {

					currentLine = currentLine.replace(	"bewerber.getStgName()",
														"\"" + bewerber.getStgName().toString() + "\"");
				}

				if (!currentLine.isEmpty()) {
					if (Character.isDigit(currentLine.charAt(0))) {
						list.add(new ListItem(currentLine, regularFont));
					}
					else {
						doc.add(new Paragraph(currentLine, regularFont));
					}
				}
				else {
					doc.add(Chunk.NEWLINE);
				}
			}

			doc.add(list);
			br.close();

		}
		catch (IOException e) {
			logger.error("Konnte Infotext nicht einlesen / verarbeiten: " + e);
		}
	}

	private Double getEcts(List<Ects> listEcts, Object obj, Long id) {

		for (Ects ects : listEcts) {
			if (obj.getClass() == Bereich.class && ects.getBereichId().equals(id)) {
				return ects.getGesamtPunkte();
			}
			if (obj.getClass() == Fach.class && ects.getFachId().equals(id)) {
				return ects.getPunkte();
			}
		}
		return null;
	}

	private String getErgebnis(List<Ects> listEcts, Long bereichId) {

		for (Ects ects : listEcts) {
			if (ects.getErgebnis() == null) {
				continue;
			}

			if (!ects.getBereichId().equals(bereichId)) {
				continue;
			}

			if (ects.getErgebnis().equals("gruen")) {
				return "G";
			}
			else if (ects.getErgebnis().equals("gelb")) {
				return "Y";
			}
			else if (ects.getErgebnis().equals("rot")) {
				return "R";
			}
		}
		return null;
	}

	private Object getPunkte(List<Ects> listEcts, String name, Long id, Long bereichId) {

		for (Ects ects : listEcts) {
			if (ects.getFachId() == null) {
				continue;
			}

			if (ects.getFachId().equals(id) && ects.getBereichId().equals(bereichId)) {
				if (name == "punkte") {
					return ects.getPunkte();
				}
				else if (name == "erwerb") {
					return ects.getErwerb();
				}
				else if (name == "modul") {
					return ects.getModul();
				}
				else if (name == "nachweis") {
					return ects.getNachweis();
				}
			}
		}
		return null;
	}

	public void onEndPage(PdfWriter writer, Document document) {

		ColumnText.showTextAligned(	writer.getDirectContent(), Element.ALIGN_CENTER,
									new Phrase("http://www.xxxx-your_example.com/"), 110, 30, 0);
		ColumnText.showTextAligned(	writer.getDirectContent(), Element.ALIGN_CENTER,
									new Phrase("page " + document.getPageNumber()), 550, 30, 0);
	}

	public void onStartPage(PdfWriter writer, Document document) {

		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 30, 800, 0);
		ColumnText.showTextAligned(	writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800,
									0);
	}

}