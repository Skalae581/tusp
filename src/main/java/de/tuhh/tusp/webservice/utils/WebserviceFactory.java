package de.tuhh.tusp.webservice.utils;

public interface WebserviceFactory {

	/**
	 * Erzeugt einen Service Endpoint
	 *
	 * @return GetApplicationDataServicePortType Erstellt ein Endpoint-Objekt über
	 *         den eine Verbindung zu generierten Klassen von WS aufgebaut wird.
	 * @throws RuntimeException Wenn der Service falsch konfiguriert oder nicht
	 *                          erreichbar ist
	 */
//	GetApplicationDataServicePortType getGetApplicationDataService() throws RuntimeException;

	/**
	 * Erstellt einen neuen Port und aktualisiert den Cache
	 *
	 * @param serviceClass
	 * @return Den Port
	 * @throws RuntimeException wenn der Port nicht erzeigt werden kann
	 */
	<E extends Object> E getNewWebservicePort(Class<?> serviceClass) throws RuntimeException;

	/**
	 * Erzeugt einen WebservicePort
	 *
	 * @param serviceClass Die Klasse des Webservice (z.B. AccountService.class)
	 * @return Den Port
	 * @throws RuntimeException wenn der Port nicht erzeigt werden kann
	 */
	<E extends Object> E getWebservicePort(Class<?> serviceClass) throws RuntimeException;

}
