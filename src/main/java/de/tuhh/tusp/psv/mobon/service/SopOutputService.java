package de.tuhh.tusp.psv.mobon.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.json.JSONException;

import de.tuhh.tusp.psv.mobon.pojo.SopArgumentsHolder;
import de.tuhh.tusp.webservice.exception.NoRolesDefinedForOuException;
import de.tuhh.tusp.webservice.exception.RolesFromFileException;

public interface SopOutputService {

	/**
	 * Importiert SOP-Studies
	 * 
	 * @param sopFilter
	 *            Der Suchfilter
	 * @throws RolesFromFileException
	 *             Wenn keine Rollen konfiguriert sind
	 * @throws JSONException
	 *             Wenn die Rollen nicht geladen werden können
	 * @throws NoRolesDefinedForOuException
	 *             Wenn für die OU "MobilityOnline" keine Rollen definiert sind
	 * @throws RemoteException
	 *             Wenn die Sop-Webservices Probleme machen
	 * @throws ServiceException
	 *             Wenn die Sop-Webservices Probleme machen
	 */
	void importSopData(SopArgumentsHolder sopFilter)	throws RolesFromFileException, JSONException,
														NoRolesDefinedForOuException, RemoteException, ServiceException;

}
