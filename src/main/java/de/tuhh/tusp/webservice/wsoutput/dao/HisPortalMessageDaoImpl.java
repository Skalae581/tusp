package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.portalmessageservice.MessageIdsType;
import de.his.ws.portalmessageservice.ParamsType;
import de.his.ws.portalmessageservice.PortalMessageService;
import de.his.ws.portalmessageservice.PortalMessageServicePortType;
import de.his.ws.portalmessageservice.RecipientsType;
import de.his.ws.portalmessageservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisPortalMessageDaoImpl implements HisPortalMessageDao {

	@Autowired
	private WebserviceFactory serviceFactory;

	@Override
	public List<Long> sendMessage(	List<Long> personIds, String categoryUniquename, XMLGregorianCalendar publishedDate,
									XMLGregorianCalendar expiredDate, String[] params) throws ServiceFault {

		PortalMessageServicePortType port = serviceFactory.getWebservicePort(PortalMessageService.class);

		try {
			RecipientsType recipients = new RecipientsType();
			recipients.getPersonId().addAll(personIds);

			ParamsType param = new ParamsType();
			for (String s : params) {
				param.getParam().add(s);
			}

			MessageIdsType res =
					port.sendPortalMessage(recipients, categoryUniquename, publishedDate, expiredDate, param);
			return res.getMessageId();
		}
		catch (ServiceFault_Exception e) {
			throw new ServiceFault(e);
		}
	}
}
