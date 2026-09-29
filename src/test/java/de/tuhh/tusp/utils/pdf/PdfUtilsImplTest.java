package de.tuhh.tusp.utils.pdf;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.itextpdf.text.pdf.PdfReader;

public class PdfUtilsImplTest {

	private PdfUtils pdfUtils = new PdfUtilsImpl();

	@Test
	public void readAttachments_test() throws Exception {

		Resource testfile = new ClassPathResource("app/vorabcheck/ECTS-Bogen_Bioverfahrenstechnik.pdf");
		PdfReader reader = new PdfReader(testfile.getInputStream());
		List<Resource> files = pdfUtils.readAttachments(reader);
		assertEquals("ects_8bebb81112a8.xml", files.get(0).getFilename());
	}
}
