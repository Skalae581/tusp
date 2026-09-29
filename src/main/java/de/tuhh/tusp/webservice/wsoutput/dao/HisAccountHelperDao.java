package de.tuhh.tusp.webservice.wsoutput.dao;

import java.util.List;

import de.his.ws.accountservice.AccountInfo;
import de.his.ws.accountservice.CompleteAccount;
import de.his.ws.accountservice.CompleteAccount60;
import de.his.ws.accountservice.NewAccount;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.wrapper.HisAccountWrapper;

/**
 *
 * @author jjakus His Account Methoden die Informationen über einen
 *         Account-Objekt zurückgeben
 */
public interface HisAccountHelperDao {

	long createAccount(HisAccountWrapper account);

	/**
	 * Speichert einen neuen Account
	 *
	 * @deprecated
	 * @param account
	 * @return die neue ID
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	@Deprecated
	long createAccount(NewAccount account) throws ServiceFault;

	/**
	 * Liefert Account-Information über accountId
	 *
	 * @param accountId
	 * @return AccountInfo (HIS)
	 * @throws ServiceFault          Im Falle von Exceptions bedingt durch die HIS
	 *                               Webservices
	 * @throws IllegalStateException Wenn nicht genau ein Account gefunden wird
	 */
	AccountInfo fetchAccountByAccountId(Long accountId) throws ServiceFault, IllegalStateException;

	/**
	 * Liefert Account-Information über uid (Benutzername)
	 *
	 * @param uid
	 * @return AccountInfo (HIS) oder null wenn nicht gefunden
	 * @throws ServiceFault          Im Falle von Exceptions bedingt durch die HIS
	 *                               Webservices
	 * @throws IllegalStateException Wenn mehrere Accounts gefunden werden
	 */
	AccountInfo fetchAccountByUsername(String uid) throws ServiceFault, IllegalStateException;

	AccountInfo fetchAccountByUsernameIgnoreDate(String uid);

	/**
	 * Such nach accounts die Valid From ab heute haben
	 *
	 * @return
	 */
	List<AccountInfo> findAccountFromDate(String date);

	/**
	 * Liefert die erste AccountId für eine Person
	 *
	 * @param personId
	 * @return Account id (HIS) wenn eine existiert sonst null
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	Long getFirstAccountIdForPerson(Long personId) throws ServiceFault;

	/**
	 * Liefert gesamte Account Info für ein Account
	 *
	 * @param id Account id
	 * @return CompleteAccount60 (HIS)
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	HisAccountWrapper readAccount(Long id) throws ServiceFault;

	/**
	 * Such nach accounts für person
	 *
	 * @param personId
	 * @return
	 */
	List<CompleteAccount> searchAccountForPerson(Long personId);

	/**
	 *
	 * @param personId
	 * @return
	 */
	List<CompleteAccount60> searchAccountForPerson60(Long personId);

	/**
	 * Updatet einen Account
	 *
	 * @param account
	 * @throws ServiceFault Im Falle von Exceptions bedingt durch die HIS
	 *                      Webservices
	 */
	void updateAccount(HisAccountWrapper account) throws ServiceFault;

	/**
	 *
	 * @param account
	 * @throws ServiceFault
	 */
	void updateAccount60(CompleteAccount60 account) throws ServiceFault;

}
