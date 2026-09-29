package de.tuhh.tusp.app.fame.connector.applicantservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.applicantservice.Applicant;
import de.his.ws.applicantservice.ApplicantService;
import de.his.ws.applicantservice.ApplicantServicePortType;
import de.his.ws.applicantservice.ApplicationContent;
import de.his.ws.applicationservice.ApplicationInfo;
import de.his.ws.applicationservice.ApplicationService;
import de.his.ws.applicationservice.ApplicationServicePortType;
import de.his.ws.applicationservice.ServiceFault_Exception;
import de.his.ws.mimedataservice.Mimedata;
import de.his.ws.mimedataservice.MimedataService;
import de.his.ws.mimedataservice.MimedataServicePortType;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class ApplicantDaoImpl implements ApplicantDao {

	@Autowired
	private WebserviceFactory webserviceFactory;

	/**
	 *
	 * @return ApplicantService
	 */
	private ApplicantServicePortType applicantService() {

		return webserviceFactory.getWebservicePort(ApplicantService.class);
	}

	/**
	 *
	 * @return ApplicationService
	 */
	private ApplicationServicePortType applicationService() {

		return webserviceFactory.getWebservicePort(ApplicationService.class);
	}

	@Override
	public List<ApplicationInfo> findApplicationByNr(long applicantNumber) throws ServiceFault {

		try {
			List<ApplicationInfo> applications =
					applicationService().findApplication("", "", "", "=" + applicantNumber, "", "", "")
										.getApplicationInfo();

			return applications;
		}
		catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public List<ApplicationInfo> findApplicationByStudiengang(	String studiengang,
																String abschluss) throws ServiceFault {

		try {
			return applicationService()	.findApplication("", "", "", "", "=" + abschluss, "=" + studiengang, "")
										.getApplicationInfo();
		}
		catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}

	/**
	 *
	 * @return MimeService
	 */
	private MimedataServicePortType mimeDataService() {

		return webserviceFactory.getWebservicePort(MimedataService.class);
	}

	@Override
	public Applicant readApplicant(long applicantNumber) throws ServiceFault {

		try {
			return applicantService().readApplicant(applicantNumber);
		}
		catch (Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public List<ApplicationContent> readApplicationContent(long id) throws ServiceFault {

		try {
			return applicantService().readApplicationContent(id).getApplicationContent();
		}
		catch (Exception e) {
			throw new ServiceFault(e);
		}
	}

	@Override
	public Mimedata readMimeData(String value) throws ServiceFault {

		if (value == null) {
			return null;
		}

		try {
			Long id = Long.parseLong(value);
			// return mimeDataService().readMimeData(id);
			return mimeDataService().readMimedata201912(id);
		}
		catch (Exception e) {
			throw new ServiceFault(e);
		}
	}

}
