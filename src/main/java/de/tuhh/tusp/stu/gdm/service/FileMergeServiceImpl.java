package de.tuhh.tusp.stu.gdm.service;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;

@Component
public class FileMergeServiceImpl implements FileMergeService {

	private static Logger logger = LogManager.getLogger(FileMergeServiceImpl.class);

	/**
	 * Methode fügt der Hauptdatei eine elektronische Sequenznummer zu
	 *
	 * @param document
	 * @param nummer
	 */
	private void addSeqNr(Document document, String nummer) {

		Node acct = document.getElementsByTagName("Acct").item(0);

		Text seqNr = document.createTextNode(nummer);
		Element sequence = document.createElement("ElctrncSeqNb");
		sequence.appendChild(seqNr);
		acct.getParentNode().insertBefore(sequence, acct);
	}

	/**
	 * Methode legt für die entsprechende File ein Document an. Es werden mindestens
	 * zwei Documents im Code benötigt, daher gibt es diese separate Methode
	 *
	 * @param file
	 * @return Document
	 */
	private Document createDoc(File file) {

		try {
			DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(file);
			doc.getDocumentElement().normalize();
			return doc;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Document mergeFiles(List<Resource> files, int sequenceNumber) {

		MessageCollector collector = MessageCollectorImpl.getInstance(Thread.currentThread());

		try {
			File firstFile = files.get(0).getFile();
			Document doc = createDoc(firstFile);

			addSeqNr(doc, Integer.toString(sequenceNumber));

			Node ntryDtls = doc.getElementsByTagName("NtryDtls").item(0);

			for (Resource nextFile : files.subList(1, files.size())) {
				File nxtFile = nextFile.getFile();
				Document docNext = createDoc(nxtFile);
				NodeList ntryDtlsNext = docNext.getElementsByTagName("NtryDtls");

				for (int i = 0; i < ntryDtlsNext.getLength(); i++) {
					Node ntryDtl = ntryDtlsNext.item(i);

					for (int j = 0; j < ntryDtl.getChildNodes().getLength(); j++) {
						Node importNode = doc.importNode(ntryDtl.getChildNodes().item(j), true);
						ntryDtls.appendChild(importNode);
					}

					collector.addUpdated();
				}
			}

			return doc;
		} catch (Exception e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

}
