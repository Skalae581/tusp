package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.KeyValue;
import de.his.ws.keyvalueservice.KeyvalueService;
import de.his.ws.keyvalueservice.KeyvalueServicePortType;
import de.his.ws.keyvalueservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisOrgunitHelperDaoImpl implements HisOrgunitHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;
	private KeyvalueServicePortType keyValue;

	/**
	 * Gibt die Liste von Orgunits zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public List<KeyValue> getOrgunit() throws ServiceFault_Exception {

		List<KeyValue> list = getPort().getAll("Orgunit", "de").getValue();
		return list;
	}

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

}
