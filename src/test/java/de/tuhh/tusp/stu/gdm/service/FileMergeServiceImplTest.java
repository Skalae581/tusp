package de.tuhh.tusp.stu.gdm.service;

import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import de.tuhh.tusp.utils.MessageCollector;

@RunWith(MockitoJUnitRunner.class)
public class FileMergeServiceImplTest {

	private FileMergeServiceImpl service = new FileMergeServiceImpl();

	@Mock
	private MessageCollector collector;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

	}

	private String documentToString(Document document) throws Exception {

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(writer));
		return writer.getBuffer().toString();
	}

	@Test
	public void mergeFiles_entriesAusZweiDateienVorhanden() throws Exception {

		List<Resource> files = new ArrayList<>();
		files.add(new ClassPathResource("gdm/Beispiel1.xml"));
		files.add(new ClassPathResource("gdm/Beispiel2.xml"));

		int seqNr = 0;

		Document document = service.mergeFiles(files, seqNr);
		String content = documentToString(document);

		assertTrue("Es fehlt der Beispieldatensatz aus gdm/Beispiel1.xml", content.contains("test1"));
		assertTrue("Es fehlt der Beispieldatensatz aus gdm/Beispiel2.xml", content.contains("test2"));
		// Hinweis (rztmm): aus den Dateien 2 bis n wird jeweils nur der
		// NtryDtls-Block extrahiert. Dieser Test schlug
		// fehl, weil der String "test2" nicht innerhalb des NtryDtls-Blocks
		// vorhanden war. Beispiel2.xml wurde
		// entsprechend angepasst.
	}
}
