package de.tuhh.tusp.utils.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class AbstractNewPdfWithAttachment extends AbstractNewPdfView {

	private Document document;
	private HttpServletResponse response;

	/**
	 * Erzeugt eine neue Instanz und legt den Contenttype als PDF fest.
	 */
	public AbstractNewPdfWithAttachment() {
		document = newDocument();
		setContentType("application/pdf");
	}

	@Override
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
	@Override
	protected void addHttpHeader(String name, String value) {

		response.setHeader(name, value);
	}

	@Override
	protected Paragraph addParagraph(	String content, int alignment, int spacingAfter,
										Font font) throws DocumentException {

		Paragraph p = new Paragraph(content);
		p.setAlignment(alignment);
		p.setSpacingAfter(spacingAfter);
		p.setFont(font);
		document.add(p);
		return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(	Map<String, Object> model, HttpServletRequest request,
											HttpServletResponse response) throws Exception {

		this.response = response;

		Map<String, Object> map = new HashMap<>();
		map = (Map<String, Object>) model.get("map");

		document = new Document();
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		configureWriter(writer);
		buildPdfMetadata(model, document, request);

		document.open();
		buildPdfDocument(model, document);
		if (map.get("setAmpelsystem") == "true") {
			addAttachmentToWriter(model, writer);
		}
		document.close();

		writeToResponse(response, baos);
	}
}