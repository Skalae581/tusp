package de.tuhh.tusp.app.fame.connector.applicantservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.applicantservice.Request;
import de.his.ws.applicantservice.RequestSubject;
import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.keyvalueservice.KeyvalueService;
import de.his.ws.keyvalueservice.KeyvalueServicePortType;
import de.his.ws.keyvalueservice.ServiceFault_Exception;
import de.tuhh.tusp.app.fame.connector.Validity;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.sync.service.CourseOfStudyService;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class RequestValidatorImpl {

	private static Logger logger = LogManager.getLogger(RequestValidatorImpl.class);

	private static List<Long> gultig = null;
	private static List<Long> noreset = null;
	@Autowired
	private CourseOfStudyService cosService;
	@Autowired
	private WebserviceFactory webserviceFactory;

	@Autowired
	private ConfigService config;

	/**
	 * Initialisiert die Liste der als gültig akzeptierten Status anhand der Config
	 */
	private void initCache() {

		gultig = new ArrayList<>();
		noreset = new ArrayList<>();

		String gueltigStati = config.get("de.tuhh.tusp.app.fame.connector.applicantservice.GultigStatusUniqueNames",
				"GL", true);
		String noResStatus = config.get("de.tuhh.tusp.app.fame.connector.applicantservice.NoResetStatusUniqueNames",
				"EA", true);

		KeyvalueServicePortType webService = webserviceFactory.getWebservicePort(KeyvalueService.class);

		try {
			for (KeyValue status : webService.getAll("RequestStatusValue", "de").getValue()) {
				for (String s : gueltigStati.split(";")) {
					if (s.equals(status.getUniquename())) {
						gultig.add(status.getId());
					}
				}
				for (String s : noResStatus.split(";")) {
					if (s.equals(status.getUniquename())) {
						noreset.add(status.getId());
					}
				}
			}
		} catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	/**
	 * Prüft ob ein Gültiger Reqtest für den Stg existiert
	 *
	 * Achtung: synchronized, damit der Cache immer in einem definierten Zustand
	 * ist.
	 *
	 * @param request
	 * @return true wenn ja
	 */
	public synchronized Validity isValid(Request request, String stg, String abschl) {

		if (gultig == null) {
			initCache();
		}

		if (gultig.isEmpty()) {
			throw new RuntimeException("Es muss mindestens ein Status gültig sein!");
		}

		Validity v = Validity.VALID;
		Long status = Long.valueOf(request.getRequestStatusId().longValueExact());
		if (noreset.contains(status)) {
			v = Validity.ONLY_IF_EXISTS;
		} else if (!gultig.contains(status)) {
			return Validity.INVALID;
		}

		for (RequestSubject requestSubject : request.getRequestSubjectDataSet().getRequestSubject()) {

			CourseOfStudy cos = cosService.fetchById(requestSubject.getCourseOfStudyId());
			if (cos.getUniquename().startsWith(abschl + "|" + stg + "|")) {
				return v;
			}
		}

		return Validity.INVALID;

	}

	public void logDecisionData(String stg, String abschl, List<Request> requests) {

		for (Request r : requests) {
			for (RequestSubject rs : r.getRequestSubjectDataSet().getRequestSubject()) {
				CourseOfStudy cos = cosService.fetchById(rs.getCourseOfStudyId());
				logger.debug(String.format("RequestId %d, RequestStatus %d, RequestSubjectCOS %s, gebraucht %s %s %s",
						r.getId(), r.getRequestStatusId().longValueExact(), cos.getUniquename(), stg, abschl,
						gultig.toString()));
			}
		}
	}
}
