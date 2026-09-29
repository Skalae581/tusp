package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.KeyvalueService;
import de.his.ws.keyvalueservice.KeyvalueServicePortType;
import de.his.ws.keyvalueservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisTermTypeValueHelperDaoImpl implements HisTermTypeValueHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;
	private KeyvalueServicePortType keyValue;

	/**
	 * Service Port Initialisierung
	 *
	 * @return den Port
	 */
	private KeyvalueServicePortType getPort() {

		if (keyValue == null) {
			keyValue = serviceFactory.getWebservicePort(KeyvalueService.class);
		}

		return keyValue;
	}

	/**
	 * Gibt die Liste von TermTypes zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<AbstractKeyValue> getTermTypes() throws ServiceFault_Exception {

		List<AbstractKeyValue> list = getPort().getAllExtended("TermTypeValue", "de").getValue();

		return list;

	}
}
