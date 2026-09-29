package de.tuhh.tusp.app.fame.connector.applicantservice;

import java.util.List;

import de.his.ws.applicantservice.Applicant;
import de.his.ws.applicantservice.ApplicationContent;
import de.his.ws.applicationservice.ApplicationInfo;
import de.his.ws.mimedataservice.Mimedata;
import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface ApplicantDao {

	/**
	 * Liest eine Bewerbung anhand ihrer Numme
	 *
	 * @param applicantNumber
	 * @return Liste der Bewerbungen
	 * @throws ServiceFault
	 *             wenn Der Webservice Probleme macht
	 */
	List<ApplicationInfo> findApplicationByNr(long applicantNumber) throws ServiceFault;

	/**
	 * Sucht alle Bewerbungen für einen Studiengang
	 *
	 * @param studiengang
	 * @param abschluss
	 * @return Liste der Bewerbungen
	 * @throws ServiceFault
	 *             wenn Der Webservice Probleme macht
	 */
	List<ApplicationInfo> findApplicationByStudiengang(String studiengang, String abschluss) throws ServiceFault;

	/**
	 * Liest die Details einer Bewerbung
	 *
	 * @param applicantNumber
	 * @return Die Bewerbungsdetails
	 * @throws ServiceFault
	 *             wenn Der Webservice Probleme macht
	 */
	Applicant readApplicant(long applicantNumber) throws ServiceFault;

	/**
	 * Läd die Bewerbungsbestandteile einer Bewerbung
	 *
	 * @param id
	 * @return Bewerbungsbestandteile
	 * @throws ServiceFault
	 *             wenn Der Webservice Probleme macht
	 */
	List<ApplicationContent> readApplicationContent(long id) throws ServiceFault;

	/**
	 * Liest eine Datei
	 *
	 * @param value
	 * @return Dateiinhalt
	 * @throws ServiceFault
	 *             wenn Der Webservice Probleme macht
	 */
	Mimedata readMimeData(String value) throws ServiceFault;

}
