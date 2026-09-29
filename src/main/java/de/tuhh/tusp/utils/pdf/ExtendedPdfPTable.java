package de.tuhh.tusp.utils.pdf;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class ExtendedPdfPTable extends PdfPTable {

	/**
	 * Erzeugt eine Instanz
	 *
	 * @param columns
	 */
	public ExtendedPdfPTable(int columns) {

		super(columns);
	}

	/**
	 * Fügt eine Zelle hinzu mit einer konfigurierbaren Vorlage.
	 *
	 * Wenn integer == null wird eine leere Zelle erzeugt
	 *
	 * @param integer
	 * @param vorlage
	 */
	public void addCell(Integer integer, PdfPCell vorlage) {

		if (integer == null) {
			vorlage.setPhrase(new Phrase());
		}
		else {
			vorlage.setPhrase(new Phrase(integer.toString()));
		}

		if (vorlage != null) {
			vorlage.setPhrase(new Phrase(integer));
		}
		this.addCell(vorlage);
	}

	/**
	 * Fügt eine Zelle hinzu mit einer konfigurierbaren Vorlage
	 *
	 * @param text
	 * @param font
	 * @param vorlage
	 */
	public void addCell(String text, Font font, PdfPCell vorlage) {

		vorlage.setPhrase(new Phrase(text, font));
		this.addCell(vorlage);
	}

	/**
	 * Fügt eine Zelle hinzu mit einer konfigurierbaren Vorlage
	 *
	 * @param text
	 * @param vorlage
	 */
	public void addCell(String text, PdfPCell vorlage) {

		vorlage.setPhrase(new Phrase(text));
		this.addCell(vorlage);
	}

	/**
	 * Füllt die letzte Zeile mit leeren Zellen auf
	 */
	public void fillRow() {

		while (!rowCompleted) {
			addCell(new Phrase());
		}
	}
}
