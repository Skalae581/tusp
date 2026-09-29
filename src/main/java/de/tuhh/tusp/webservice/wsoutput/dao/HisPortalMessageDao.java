package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import de.tuhh.tusp.webservice.exception.ServiceFault;

public interface HisPortalMessageDao {

	/**
	 * Sendet eine PortalMessage ab.
	 *
	 * @param personIds
	 *            PersonIds der Empfänger
	 * @param categoryUniquename
	 * @param publishedDate
	 * @param expiredDate
	 * @param params
	 *            Liste der Parameter meist: HEADER(portalText), MailSubject, MailBody
	 * @return ID der Nachricht
	 * @throws ServiceFault
	 *             wenn HIO Probeme macht
	 */
	List<Long> sendMessage(	List<Long> personIds, String categoryUniquename, XMLGregorianCalendar publishedDate,
							XMLGregorianCalendar expiredDate, String[] params) throws ServiceFault;
}
