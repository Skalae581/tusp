package de.tuhh.tusp.app.fame.utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.NoSuchPaddingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.utils.pdf.AbstractNewPdfView;
import de.tuhh.tusp.utils.pdf.ExtendedPdfPTable;

public class VorgangPdfCreator extends AbstractNewPdfView {

	private static Logger logger = LogManager.getLogger(VorgangPdfCreator.class);

	@Override
	protected void addAttachmentToWriter(Map<String, Object> model, PdfWriter writer)
			throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		// Wird nicht gebraucht
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc) throws Exception {

		// Dateiname festlegen
		addHttpHeader("Content-Disposition", "attachment;filename=\"FaME-Export.pdf\"");

		List<Vorgang> cases = (List<Vorgang>) model.get("cases");
		Map<Vorgang, List<ExtendedInput>> values = (Map<Vorgang, List<ExtendedInput>>) model.get("values");

		PdfPCell labelConfig = new PdfPCell();
		Font labelFont = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);

		PdfPCell dataConfig = new PdfPCell();
		Font dataFont = new Font(Font.FontFamily.TIMES_ROMAN, 11);

		for (Vorgang v : cases) {

			logger.debug("Verabeite Vorgang " + v.getId());

			addParagraph(v.getConnectorKey());
			ExtendedPdfPTable table = newTable(4);
			for (ExtendedInput input : values.get(v)) {

				logger.debug(input.getName() + " = " + input.getEingabe());

				table.addCell(input.getName(), labelFont, labelConfig);

				String value = input.getEingabe();

				// Label umwandelt
				if (InputTypeEnum.SELECT == input.getInputTypeUniqName()) {
					String[] lines = input.getOptions().split("\n");
					for (String line : lines) {
						String[] parts = line.split(";");
						if (parts[0].equals(value)) {
							value = parts[1];
							break;
						}
					}
				}

				table.addCell(value, dataFont, dataConfig);
			}
			table.fillRow();
			doc.add(table);
		}
	}

}