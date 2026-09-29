package de.tuhh.tusp.webservice.wsoutput.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.termservice.ServiceFault_Exception;
import de.his.ws.termservice.Term;
import de.his.ws.termservice.TermService;
import de.his.ws.termservice.TermServicePortType;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component
public class HisTermHelperDaoImpl implements HisTermHelperDao {

	@Autowired
	private WebserviceFactory serviceFactory;
	private TermServicePortType termService;

	/**
	 * gibt aktuelles Semester zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public Term getCurrentLectureTerm() throws ServiceFault_Exception {
		return getPort().getCurrentLectureTerm();
	}

	/**
	 * gibt aktuelles Semester zurück
	 *
	 * @return
	 * @throws ServiceFault_Exception
	 */
	@Override
	public Term getCurrentTerm() throws ServiceFault_Exception {
		return getPort().getCurrentTerm("de");
	}

	/**
	 * Service Port Initialisierung
	 *
	 * @return den Port
	 */
	private TermServicePortType getPort() {

		if (termService == null) {
			termService = serviceFactory.getWebservicePort(TermService.class);
		}

		return termService;
	}

}
