package de.tuhh.tusp.webservice.wsoutput.service;

import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisAccount;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

public interface HisAccountServiceOutput {

	long addAccountForPerson(HisPerson user);

	void updateAccount(HisPerson user, Long accountId) throws ServiceFault;

	/**
	 * Account für eine Person wird entweder angelegt oder aktualisiert.
	 *
	 * Hierzu werden die Daten aus dem {@link HisAccount} der HisPerson
	 * genommen. Bei einem Create werden alle Null-Werte durch Default-Werte
	 * ergänzt. Bei einem Update werden alle Werte ungleich null aktualisiert.
	 *
	 * @param user
	 * @return die Account ID
	 * @throws ServiceFault
	 *             wenn Webservices Exceptions werfen
	 */
	Long updateOrCreateAccount(HisPerson user) throws ServiceFault;

}
