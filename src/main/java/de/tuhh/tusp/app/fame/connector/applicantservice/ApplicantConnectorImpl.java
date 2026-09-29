package de.tuhh.tusp.app.fame.connector.applicantservice;

import static de.tuhh.tusp.utils.CalendarUtils.DATEFORMAT_ENGLISH;
import static de.tuhh.tusp.utils.CalendarUtils.stringToXMLGregorianCalendar;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import de.his.ws.applicantservice.Applicant;
import de.his.ws.applicantservice.ApplicantPerson;
import de.his.ws.applicantservice.Application;
import de.his.ws.applicantservice.ApplicationDataSetType;
import de.his.ws.applicantservice.DefaultRequestDataSetType;
import de.his.ws.applicantservice.GenderType;
import de.his.ws.applicantservice.Request;
import de.his.ws.applicationservice.ApplicationInfo;
import de.tuhh.tusp.app.fame.connector.Case;
import de.tuhh.tusp.app.fame.connector.Validity;
import de.tuhh.tusp.app.fame.connector.pojo.SingleLineText;
import de.tuhh.tusp.app.fame.exception.ImportException;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.service.ValueService;
import de.tuhh.tusp.app.fame.service.VorgangService;
import de.tuhh.tusp.app.fame.service.WorkflowInstanceService.ImportFilter;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.webservice.exception.ServiceFault;

/**
 * Schnittstelle zum HISinOne.
 *
 * Die CaseID (Identifizierungsnummer) besteht aus der Bewerbernummer und dem
 * Abschluss (z.B. 123456-MS), da Bewerber sich für MD und MS bewerben dürfen.
 * Dies soll zu getrennten Fällen führen.
 *
 * @author rztjm
 *
 */
public class ApplicantConnectorImpl implements ApplicantConnector {

	private static Logger logger = LogManager.getLogger(ApplicantConnectorImpl.class);

	// Beans
	private ValueService valueService;
	private VorgangService vorgangService;
	private ApplicantDao applicantDao;
	private RequestValidatorImpl requestValidator;
	private ApplicationContext context;
	private ConfigService config;

	private String stg;
	private String abschl;
	private Long workflowInstanceId;

	/**
	 * Construct
	 *
	 * @param valueService
	 * @param vorgangService
	 * @param fileService
	 * @param applicantDao
	 * @param config
	 * @param webserviceFactory
	 * @param bewVervollstController
	 * @param cosService
	 */
	public ApplicantConnectorImpl(ApplicationContext context) {

		this.valueService = context.getBean(ValueService.class);
		this.vorgangService = context.getBean(VorgangService.class);
		this.applicantDao = context.getBean(ApplicantDao.class);
		this.requestValidator = context.getBean(RequestValidatorImpl.class);
		this.config = context.getBean(ConfigService.class);
		this.context = context;
	}

	@Override
	public Case findCase(String caseId) throws ImportException {

		ApplicantCase c = new ApplicantCase(context);
		c.setId(caseId);

		String[] idParts = caseId.split("-");

		List<ApplicationInfo> applicationInfos = applicantDao.findApplicationByNr(Long.parseLong(idParts[0]));

		if (applicationInfos.isEmpty()) {
			String msg = "Keine Bewerbungsinfos für " + caseId;
			logger.error(msg);
			c.setImportErrors(msg);
			return c;
		}

		Applicant applicant = null;

		try {
			applicant = applicantDao.readApplicant(Long.parseLong(idParts[0]));
		} catch (ServiceFault e) {

			if ("com.sun.xml.internal.ws.client.ClientTransportException".equals(e.getCause().getClass().getName())) {
				throw new ImportException(e);
			}

			String msg = "Konnte Bewerbung (" + caseId + ") nicht lesen und deaktiviere sie!";
			logger.error(msg, e);
			c.setImportErrors(msg);
			return c;
		}

		ApplicationInfo applicationInfo = null;
		for (ApplicationInfo ai : applicationInfos) {
			if (idParts[1].equals(ai.getDegree())) {
				applicationInfo = ai;
			}
		}

		Application application = null;
		for (Application a : applicant.getApplicationDataSet().getApplication()) {
			if (idParts[2].equals(a.getTerm())) {
				application = a;
			}
		}
		if (application == null) {
			String msg = "Für Vorgang " + caseId + " konnte keine gültige Application gefunden werden";
			logger.warn(msg);
			c.setImportErrors(msg);
			return c;
		}

		Request request = null;
		Validity v = Validity.INVALID;
		List<Request> requests = application.getDefaultRequestDataSet().getRequest();
		for (Request r : requests) {
			v = requestValidator.isValid(r, stg, abschl);
			if (v == Validity.VALID || v == Validity.ONLY_IF_EXISTS) {
				request = r;
				break;
			}
		}
		if (request == null) {
			String msg = "Bewerbung von " + applicationInfo.getApplicantNumber() + " hat in " + application.getTerm()
					+ " keinen gültigen Request";
			logger.info(msg);
			c.setImportErrors(msg);
			if (logger.isDebugEnabled()) {
				requestValidator.logDecisionData(stg, abschl, requests);
			}
			return c;
		}

		c.setValid(v);
		c.setData(applicant, applicationInfo, application, request);

		return c;
	}

	@Override
	public List<Case> findCases(ImportFilter filter) throws ImportException {

		List<ApplicationInfo> applicationInfos = applicantDao.findApplicationByStudiengang(stg, abschl);

		List<Case> cases = new ArrayList<>();
		for (ApplicationInfo applicationInfo : applicationInfos) {

			ApplicantCase c = null;
			try {
				Applicant applicant = applicantDao.readApplicant(applicationInfo.getApplicantNumber().longValueExact());
				for (Application application : applicant.getApplicationDataSet().getApplication()) {

					c = new ApplicantCase(context);
					c.setApplicant(applicant);
					String caseId = applicationInfo.getApplicantNumber() + "-" + applicationInfo.getDegree() + "-"
							+ application.getTerm();
					c.setId(caseId);

					if (filter == ImportFilter.NurNeue) {
						Vorgang caseFromDb = vorgangService
								.fetchByWorkflowIntsanceAndConnectorKey(this.workflowInstanceId, caseId);
						if (caseFromDb != null) {
							// Ignoriere alles, was du schon kennst
							continue;
						}
					}

					cases.add(c);

					Request request = null;
					Validity v = Validity.INVALID;
					for (Request r : application.getDefaultRequestDataSet().getRequest()) {
						v = requestValidator.isValid(r, stg, abschl);
						if (v == Validity.VALID || v == Validity.ONLY_IF_EXISTS) {
							request = r;
							break;
						}
					}
					if (request == null) {
						String msg = "Bewerbung von " + applicationInfo.getApplicantNumber() + " hat in "
								+ application.getTerm() + " keinen gültigen Request";
						logger.info(msg);
						c.setImportErrors(msg);
						continue;
					}
					c.setValid(v);
				}
			} catch (ServiceFault e) {
				if (c != null) {
					logger.error("Fehler in Case:" + c.getId());
					cases.remove(c);
				}
				logger.error(applicationInfo.getApplicantNumber() + " " + applicationInfo.getFirstname() + " "
						+ applicationInfo.getSurname(), e);
			}
		}

		return cases;
	}

	@Override
	public Case readExapleCase() {

		ApplicationInfo applicationInfo = new ApplicationInfo();
		applicationInfo.setDegree("MD");
		applicationInfo.setSubject("MB");

		Applicant applicant = new Applicant();
		applicant.setApplicantNumber(12345l);
		applicant.setApplicantPerson(new ApplicantPerson());
		applicant.getApplicantPerson().setSurname("Mustermann");
		applicant.getApplicantPerson().setFirstName("Max");
		applicant.getApplicantPerson().setDateOfBirth(stringToXMLGregorianCalendar("1990-01-01", DATEFORMAT_ENGLISH));
		applicant.getApplicantPerson().setEmail("nobody@tuhh.de");
		applicant.getApplicantPerson().setStreet("Nebenstraße");
		applicant.getApplicantPerson().setNumber("5");
		applicant.getApplicantPerson().setPostcode("12334");
		applicant.getApplicantPerson().setCity("Kleinstadt");
		applicant.getApplicantPerson().setGender(GenderType.MAENNLICH);
		applicant.getApplicantPerson().setNationality("DE");
		applicant.getApplicantPerson().setCountry("DE");

		applicant.setApplicationDataSet(new ApplicationDataSetType());
		Application application = new Application();
		applicant.getApplicationDataSet().getApplication().add(application);
		application.setTerm("WiSe 2018/2019");

		Request request = new Request();
		application.setDefaultRequestDataSet(new DefaultRequestDataSetType());
		application.getDefaultRequestDataSet().getRequest().add(request);
		request.setRequestStatusId(new BigInteger("3"));
		request.setReceiptDate(stringToXMLGregorianCalendar("2018-11-01", DATEFORMAT_ENGLISH));

		ApplicantCase c = new ApplicantCase(context);

		c.setData(applicant, applicationInfo, application, request);

		List<Config> zusatzfelder = config.findByKeyPrefix(ApplicantCase.FELD_PREFIX);
		for (Config item : zusatzfelder) {
			c.put(item.getValue(), new SingleLineText("undefinable", valueService));
		}

		c.put("bew_ergebnis", new SingleLineText("test", valueService));

		return c;
	}

	@Override
	public List<Case> search(String searchword) {

		List<Case> ret = new ArrayList<>();
		Applicant applicant;
		try {

			long applicantNumber = Long.parseLong(searchword);
			applicant = applicantDao.readApplicant(applicantNumber);
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}

		for (Application application : applicant.getApplicationDataSet().getApplication()) {
			for (Request request : application.getDefaultRequestDataSet().getRequest()) {
				Validity v = requestValidator.isValid(request, stg, abschl);
				if (v == Validity.VALID) {
					ApplicantCase c = new ApplicantCase(context);
					c.setApplicant(applicant);
					c.setId(searchword + "-" + abschl + "-" + application.getTerm());
					ret.add(c);
				}
			}
		}

		return ret;
	}

	@Override
	public void setParameter(String parameter) {

		String[] params = parameter.split(" ");
		if (params.length != 2) {
			throw new RuntimeException(
					"Der Parameter der Workflowinstanz muss der Form 'stg abschl' entsprechen, Beispiel: 'LM BS'");
		}

		stg = params[0];
		abschl = params[1];
	}

	@Override
	public void setWorkflowInstanceId(Long id) {

		this.workflowInstanceId = id;
	}

	@Override
	public void writeCase(Case c) {

	}
}
