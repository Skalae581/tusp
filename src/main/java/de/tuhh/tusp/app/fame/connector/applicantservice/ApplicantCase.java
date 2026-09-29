package de.tuhh.tusp.app.fame.connector.applicantservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

import com.google.common.io.ByteStreams;
import com.itextpdf.text.DocumentException;

import de.his.ws.applicantservice.Applicant;
import de.his.ws.applicantservice.Application;
import de.his.ws.applicantservice.ApplicationContent;
import de.his.ws.applicantservice.ApplicationContentInput;
import de.his.ws.applicantservice.FieldInput;
import de.his.ws.applicantservice.Request;
import de.his.ws.applicationservice.ApplicationInfo;
import de.his.ws.mimedataservice.Mimedata;
import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.Validity;
import de.tuhh.tusp.app.fame.connector.pojo.ConnectorPojo;
import de.tuhh.tusp.app.fame.connector.pojo.PdfFile;
import de.tuhh.tusp.app.fame.connector.pojo.SingleLineText;
import de.tuhh.tusp.app.fame.service.FileService;
import de.tuhh.tusp.app.fame.service.ValueService;
import de.tuhh.tusp.app.vorabcheck.controllers.BewVervollstController;
import de.tuhh.tusp.app.vorabcheck.controllers.BewVervollstController.NoAttachmentException;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.InMemoryResource;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class ApplicantCase implements Case {

	private static Logger logger = LogManager.getLogger(ApplicantCase.class);

	public static final String FELD_PREFIX = "de.tuhh.tusp.app.fame.applicantservice.bestandteile";

	@Getter
	@Setter
	private String id;

	@Getter
	@Setter
	private String importErrors = "";

	@Getter
	@Setter
	private Validity valid;

	@Getter
	private Map<String, ConnectorPojo> properties = new HashMap<>();

	private Applicant applicant;
	private Request request;

	@NonNull
	private ApplicationContext context;

	/**
	 * {@inheritDoc}
	 */
	public ConnectorPojo get(String key) {

		return properties.get(key);
	}

	@Override
	public void lazyLoad() {

		logger.debug("LazyLoad: " + id);

		ValueService valueService = context.getBean(ValueService.class);
		BewVervollstController bewVervollstController = context.getBean(BewVervollstController.class);
		ApplicantDao applicantDao = context.getBean(ApplicantDao.class);
		FileService fileService = context.getBean(FileService.class);
		ConfigService config = context.getBean(ConfigService.class);

		long requestSubjectId = request.getRequestSubjectDataSet().getRequestSubject().get(0).getId();
		List<ApplicationContent> applicationContents = applicantDao.readApplicationContent(requestSubjectId);
		for (ApplicationContent ac : applicationContents) {
			for (ApplicationContentInput applicationContentInput : ac.getApplicationContentInputDataSet()
					.getApplicationContentInput()) {
				for (FieldInput fieldInput : applicationContentInput.getFieldInputDataSet().getFieldInput()) {
					String key = ac.getUniquename() + "-" + applicationContentInput.getContentInputType() + "-"
							+ fieldInput.getUniquename();
					if ("DOCUMENTUPLOADFIELD".equals(fieldInput.getFieldUniquename())) {

						if (fieldInput.getValue() == null) {
							PdfFile file = new PdfFile(valueService, fileService);
							file.setDelete(true);
							put(key, file);
							continue; // Feld ist leer
						}

						String fileName = fieldInput.getFieldDefaulttext();
						Mimedata mimedata = applicantDao.readMimeData(fieldInput.getValue());
						Resource resource = new InMemoryResource(fileName, mimedata.getMimeBlob());
						try {
							resource = bewVervollstController.generatePdf(resource);
							put("bew_ergebnis", new SingleLineText(resource.getFilename(), valueService));
						} catch (NoAttachmentException e) {
							logger.debug("Kein Attachement vorhanden für " + getId() + " " + key);
						} catch (JAXBException | IOException | SAXException | XMLStreamException | DocumentException
								| RuntimeException e) {
							logger.debug("Konnte PDF nicht in bunten ECTS-Bogen konvertieren");
						}

						try {
							PdfFile file = new PdfFile(valueService, fileService);
							file.setType("application/pdf");
							// alt:
							// file.setFileName(applicant.getApplicantNumber() +
							// "-" + ac.getUniquename() +
							// ".pdf");
							file.setFileName(mimedata.getMimeFilename());
							file.setContent(ByteStreams.toByteArray(resource.getInputStream()));
							put(key, file);
						} catch (IOException e) {
							logger.error("Konnte Resource nicht einlesen", e);
						}
					} else {
						put(key, new SingleLineText(fieldInput.getValue(), valueService));
					}
					config.set(FELD_PREFIX + "." + key, key);
				}
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void put(String key, ConnectorPojo value) {

		logger.debug("put(" + key + "," + value + ")");
		properties.put(key, value);
	}

	/**
	 * Setzt die Bewerbungsdaten für den Vorgang zum späteren Initialisieren
	 *
	 * @param applicant
	 * @param applicationInfo
	 * @param application
	 * @param request
	 */
	public void setData(Applicant applicant, ApplicationInfo applicationInfo, Application application,
			Request request) {

		logger.debug("Init: " + id);

		this.applicant = applicant;
		this.request = request;
		ValueService valueService = context.getBean(ValueService.class);

		put("degree", new SingleLineText(applicationInfo.getDegree() + "", valueService));
		put("subject", new SingleLineText(applicationInfo.getSubject() + "", valueService));

		put("applicantNumber", new SingleLineText(applicant.getApplicantNumber() + "", valueService));
		put("surname", new SingleLineText(applicant.getApplicantPerson().getSurname(), valueService));
		put("firstName", new SingleLineText(applicant.getApplicantPerson().getFirstName(), valueService));
		String dateOfBirth = CalendarUtils.toString(applicant.getApplicantPerson().getDateOfBirth(),
				CalendarUtils.DATEFORMAT_GERMAN);
		put("dateOfBirth", new SingleLineText(dateOfBirth, valueService));
		put("email", new SingleLineText(applicant.getApplicantPerson().getEmail(), valueService));
		put("street", new SingleLineText(applicant.getApplicantPerson().getStreet(), valueService));
		put("number", new SingleLineText(applicant.getApplicantPerson().getNumber(), valueService));
		put("postcode", new SingleLineText(applicant.getApplicantPerson().getPostcode(), valueService));
		put("city", new SingleLineText(applicant.getApplicantPerson().getCity(), valueService));
		put("gender", new SingleLineText(applicant.getApplicantPerson().getGender().name(), valueService));
		put("nationality", new SingleLineText(applicant.getApplicantPerson().getNationality(), valueService));
		put("country", new SingleLineText(applicant.getApplicantPerson().getCountry(), valueService));
		put("term", new SingleLineText(application.getTerm(), valueService));
		put("requestStatus", new SingleLineText(request.getRequestStatusId() + "", valueService));
		String requestDate = CalendarUtils.toString(request.getReceiptDate(), CalendarUtils.DATEFORMAT_GERMAN);
		put("requestDate", new SingleLineText(requestDate, valueService));

		// Leeren default anlegen
		put("bew_ergebnis", new SingleLineText("", valueService));

		// BewerbungsBestandteile
		if (request.getId() == 0) {
			return; // Abbrechen, wenn ExampleCase
		}

		if (request.getRequestSubjectDataSet().getRequestSubject().size() != 1) {
			this.setImportErrors("Importerror: Anzahl der RequestsSubjects = "
					+ request.getRequestSubjectDataSet().getRequestSubject().size());
		}
	}

	@Override
	public String toString() {

		return id;
	}
}
