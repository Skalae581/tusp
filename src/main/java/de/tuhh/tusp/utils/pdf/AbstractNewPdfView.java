package de.tuhh.tusp.utils.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * The AbstractPdfView class only supports old API version of iText (package com.lowagie.*, iText version <= 2.1.7)
 * while the recent iText's package uses com.itextpdf.*, meaning iText version >= 5.x
 *
 * The old iText version is no longer available nor supported, so subclassing AbstractPdfView class as of Spring 3.x
 * won't help. Instead with AbstractNewPdfView, a subclass is implemented which creates an iText 5.x-compatible version
 *
 */
public abstract class AbstractNewPdfView extends AbstractView {

	private Document document;
	private HttpServletResponse response;

	/**
	 * Erzeugt eine neue Instanz und legt den Contenttype als PDF fest.
	 */
	public AbstractNewPdfView() {
		document = newDocument();
		setContentType("application/pdf");
	}

	/**
	 * An Pdf / Writer anhängen
	 *
	 * @param model
	 * @param writer
	 * @throws IOException
	 *             Bei IO-Problemen mit der Datei
	 * @throws NoSuchAlgorithmException
	 *             Wenn der Verschlüsselungsalgorythmus nicht bekannt ist
	 * @throws NoSuchPaddingException
	 *             Wenn das Padding Probkleme macht
	 * @throws InvalidKeyException
	 *             Wenn der Key falsch ist
	 */
	protected abstract void addAttachmentToWriter(	Map<String, Object> model,
													PdfWriter writer)	throws IOException, NoSuchAlgorithmException,
																		NoSuchPaddingException, InvalidKeyException;

	/**
	 * Fügt einen HttpHeader hinzu
	 *
	 * @param name
	 *            Name des Headers
	 * @param value
	 *            Wert des Headers
	 */
	protected void addHttpHeader(String name, String value) {

		response.setHeader(name, value);
	}

	/**
	 * Fügt einen neuen Absatz in das Dokument ein
	 *
	 * @param content
	 *            Der Inhalt des Absatzes
	 * @return Der neue Absatz
	 * @throws DocumentException
	 *             Bei Prblemen mit dem Einfügen
	 */
	protected Paragraph addParagraph(String content) throws DocumentException {

		return addParagraph(content, Element.ALIGN_LEFT, 0);
	}

	/**
	 * Fügt einen neuen Absatz in das Dokument ein
	 *
	 * @param content
	 * @param font
	 * @return neuer Absatz
	 * @throws DocumentException
	 *             wenn bei der PDF Erzeugung Probleme sind
	 */
	protected Paragraph addParagraph(String content, Font font) throws DocumentException {

		return addParagraph(content, Element.ALIGN_LEFT, 0, font);
	}

	/**
	 * Fügt einen neuen Absatz in das Dokument ein
	 *
	 * @param content
	 *            Der Inhalt des Absatzes
	 * @param alignment
	 *            Die Ausrichtung des Absatzes
	 * @param spacingAfter
	 *            Der Abstand unter dem Absatz
	 * @return Der neue Absatz
	 * @throws DocumentException
	 *             Bei Prblemen mit dem Einfügen
	 */
	protected Paragraph addParagraph(String content, int alignment, int spacingAfter) throws DocumentException {

		Paragraph p = new Paragraph(content);
		p.setAlignment(alignment);
		p.setSpacingAfter(spacingAfter);
		document.add(p);
		return p;
	}

	protected Paragraph addParagraph(	String content, int alignment, int spacingAfter,
										Font font) throws DocumentException {

		Paragraph p = new Paragraph(content);
		p.setAlignment(alignment);
		p.setSpacingAfter(spacingAfter);
		p.setFont(font);
		document.add(p);
		return p;
	}

	/**
	 * PDF wird in anderer Java-Datei zusammengestellt
	 *
	 * @param model
	 * @param document
	 * @throws Exception
	 *             Exceptions werden vom Spring-MVC-Container behandelt
	 */
	protected abstract void buildPdfDocument(Map<String, Object> model, Document document) throws Exception;

	/**
	 * Kann überschrieben werden, um dem Dokument Metadaten zu übergeben
	 *
	 * @param model
	 * @param document
	 * @param request
	 */
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {}

	/**
	 * Legt die Einstellungen des PDFs fest
	 */
	protected void configureWriter(PdfWriter writer) {

		writer.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);
	}

	@Override
	protected boolean generatesDownloadContent() {

		return true;
	}

	/**
	 * Neues Document anlegen
	 *
	 * @return Document (in bestimmter Seitengröße)
	 */
	protected Document newDocument() {

		return new Document(PageSize.A4);
	}

	/**
	 * Erstellt eine neue Tabelle
	 *
	 * @param columns
	 *            Anzahl der Spalten
	 * @return Die neue Tabelle
	 */
	protected ExtendedPdfPTable newTable(int columns) {

		ExtendedPdfPTable table = new ExtendedPdfPTable(columns);
		return table;
	}

	/**
	 * Falls etwas an die Pdf angehängt werden soll, bitte im Controller in die entsprechende Map (Map<String, Object>)
	 * Folgendes hinzufügen: map.put("setAttachment", true); andernfalls ignorieren
	 */
	@Override
	protected void renderMergedOutputModel(	Map<String, Object> model, HttpServletRequest request,
											HttpServletResponse response) throws Exception {

		this.response = response;

		document = new Document();
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		configureWriter(writer);
		buildPdfMetadata(model, document, request);

		document.open();
		buildPdfDocument(model, document);

		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) model.get("map");
		if (map.containsKey("setAttachment") && (Boolean) map.get("setAttachment") == true) {
			addAttachmentToWriter(model, writer);
		}
		document.close();

		writeToResponse(response, baos);
	}
}