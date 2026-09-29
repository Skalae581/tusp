package de.tuhh.tusp.app.vorabcheck.utils.pdf.en;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.bouncycastle.crypto.CryptoException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import de.tuhh.tusp.app.vorabcheck.pojo.Bereich;
import de.tuhh.tusp.app.vorabcheck.pojo.Bewerber;
import de.tuhh.tusp.app.vorabcheck.pojo.Ects;
import de.tuhh.tusp.app.vorabcheck.pojo.Fach;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.pdf.AbstractNewPdfView;
import de.tuhh.tusp.utils.pdf.ExtendedPdfPTable;
import de.tuhh.tusp.utils.pdf.PdfUtils;

public class CreditsTemplateDownload extends AbstractNewPdfView {

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

		Bewerber bewerber = (Bewerber) map.get("bew");

		table.addCell("Program", regularFont, dataCell);
		table.addCell(bewerber.getStgName(), regularFont, dataCell);

		doc.add(table);

		/** Infotext */
		try {
			ClassPathResource infoText = new ClassPathResource("bewVervollstInfo/en/creditsPdfInfo.txt");
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

		doc.add(Chunk.NEWLINE);
		Font regularBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
		doc.add(new Paragraph(	"You may supply supplementary information if necessary (a maximum of 500 characters):",
								regularBoldFont));

		if (bewerber.getAnmerk() != null && !bewerber.getAnmerk().isEmpty()) {
			doc.add(new Paragraph(bewerber.getAnmerk(), regularFont));
		}
		else {
			doc.add(new Paragraph("No supplementary information", regularFont));
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

		ExtendedPdfPTable table = newTable(5);
		table.setWidthPercentage(110.0f);
		table.setWidths(new float[] { 2.0f, 3.0f, 2.0f, 1.0f, 2.0f });
		table.setSpacingBefore(10);

		PdfPCell alignCenterCell = new PdfPCell();
		alignCenterCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		alignCenterCell.setVerticalAlignment(Element.ALIGN_CENTER);

		PdfPCell alignLeftCell = new PdfPCell();
		alignLeftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		alignLeftCell.setVerticalAlignment(Element.ALIGN_LEFT);

		/** Reguläre Schrift */
		Font regularFont = new Font(Font.FontFamily.TIMES_ROMAN, 9);
		Font regularBoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);

		table.addCell("Field", regularFont, alignCenterCell);
		table.addCell("Requirements", regularFont, alignCenterCell);
		table.addCell("Required units (ECTS)", regularFont, alignCenterCell);
		table.addCell("Units achieved after completion of studies", regularFont, alignCenterCell);
		table.addCell(	"Explanation (name of the subject according to the Transcript of Records e.g.)", regularFont,
						alignCenterCell);

		List<Bereich> listBereich = (List<Bereich>) map.get("bereich");
		List<Ects> listEcts = (List<Ects>) map.get("listEcts");
		List<Fach> listFach = (List<Fach>) map.get("fach");

		/** Durch die Bereiche loopen */
		for (Bereich bereich : listBereich) {
			table.addCell(StringEscapeUtils.unescapeHtml4(bereich.getName()), regularBoldFont, alignCenterCell);
			table.addCell(new Phrase());

			if (bereich.getPunkte() == null) {
				table.addCell(new Phrase());
			}
			else {
				table.addCell(bereich.getPunkte().toString(), regularBoldFont, alignCenterCell);
			}

			Double gesamtPunkte = getEcts(listEcts, bereich, bereich.getId());

			if (gesamtPunkte != null) {

				table.addCell(gesamtPunkte.toString(), regularBoldFont, alignCenterCell);

				table.addCell(new Phrase());

			}
			else {
				table.fillRow();
			}

			/** Durch die Fächer loopen */
			for (Fach fach : listFach) {
				if (fach.getBereichId().equals(bereich.getId())) {
					table.addCell(new Phrase());
					table.addCell(StringEscapeUtils.unescapeHtml4(fach.getName()), regularFont, alignLeftCell);

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

						String nachweis = (String) getPunkte(listEcts, "nachweis", fach.getId(), fach.getBereichId());

						if (fachPunkte == null) {
							table.addCell(new Phrase());
						}
						else {

							table.addCell(fachPunkte.toString(), regularFont, alignCenterCell);
						}

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
		if (bewerber.getZusatzAusstehend() == null || bewerber.getZusatzErbracht() == null
				|| bewerber.getZusatzAusstehend().isEmpty() || bewerber.getZusatzErbracht().isEmpty()) {

			doc.add(Chunk.NEWLINE);
		}

		ExtendedPdfPTable zusatzTable = newTable(2);
		zusatzTable.setWidthPercentage(60.0f);
		zusatzTable.setWidths(new float[] { 3.0f, 2.0f });
		zusatzTable.setSpacingBefore(10);

		// TODO: Text aus Properties holen?
		zusatzTable.addCell("Unit used in this form (hours, ECTS e.g.):", regularFont, alignCenterCell);
		if (bewerber.getUnittype() != null) {
			zusatzTable.addCell(bewerber.getUnittype(), regularFont, alignCenterCell);
		}
		else {
			zusatzTable.fillRow();
		}

		zusatzTable.addCell("Total number of units achieved during the studies:", regularFont, alignCenterCell);
		if (bewerber.getUnittotal() != null) {
			zusatzTable.addCell(bewerber.getUnittotal(), regularFont, alignCenterCell);
		}
		else {
			zusatzTable.fillRow();
		}

		zusatzTable.addCell("Regular duration of studies (in years):", regularFont, alignCenterCell);
		if (bewerber.getUnitduration() != null) {
			zusatzTable.addCell(bewerber.getUnitduration(), regularFont, alignCenterCell);
		}
		else {
			zusatzTable.fillRow();
		}

		doc.add(zusatzTable);

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

}