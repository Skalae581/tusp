package de.tuhh.tusp.webservice.utils;

import java.net.URLClassLoader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.TestUtils;

/**
 * Testet die Robustheit der WebserviceFactory
 *
 * @author rztjm
 *
 */
public class WebserviceFactoryTest {

	private WebserviceFactory factory;
	private ConfigService config = Mockito.mock(ConfigService.class);

	@Before
	public void before() {

		Mockito	.when(config.get("de.tuhh.tusp.webservice.his.urlPrefix", "", true))
				.then(i -> "https://portal-dev.rz.tuhh.de/qisserver/services2/");
		factory = new WebserviceFactoryImpl();
		TestUtils.setField(factory, "config", config);
	}

	// // Nicht testbar
	// @Test
	// public void getWebservicePort_existingPort() {
	//
	// Mockito .when(config.get("de.tuhh.tusp.webservice.his.urlPrefix", "", true))
	// .then(i -> "https://portal-dev.rz.tuhh.de/qisserver/services2/");
	// Mockito.when(config.get("de.tuhh.tusp.webservice.his.username", "", true)).then(i -> "user");
	// Mockito.when(config.get("de.tuhh.tusp.webservice.his.password", "", true)).then(i -> "pwd");
	//
	// AccountServicePortType port = factory.getWebservicePort(AccountService.class);
	// assertNotNull(port);
	// }

	// // Nicht testbar
	// @Test
	// public void getWebservicePort_malFormatedUrl() {
	//
	// Mockito.when(config.get("de.tuhh.tusp.webservice.his.urlPrefix", "", true)).then(i -> "a");
	// Mockito.when(config.get("de.tuhh.tusp.webservice.his.username", "", true)).then(i -> "s");
	// Mockito.when(config.get("de.tuhh.tusp.webservice.his.password", "", true)).then(i -> "d");
	// AccountServicePortType port = factory.getWebservicePort(AccountService.class);
	// assertNotNull(port);
	// }

	@Test(expected = RuntimeException.class)
	public void getWebservicePort_notExistingService() {

		factory.getWebservicePort(URLClassLoader.class);
	}

	@Test(expected = RuntimeException.class)
	public void getWebservicePort_notExistingServiceConstructor() {

		factory.getWebservicePort(Long.class);
	}

	// Nicht testbar
	// @Test
	// public void getWebservicePort_returnSingleton() {
	//
	// Mockito .when(config.get("de.tuhh.tusp.webservice.his.urlPrefix", "", true))
	// .then(i -> "https://portal-dev.rz.tuhh.de/qisserver/services2/");
	// Mockito.when(config.get("de.tuhh.tusp.webservice.his.username", "", true)).then(i -> "user");
	// Mockito.when(config.get("de.tuhh.tusp.webservice.his.password", "", true)).then(i -> "pwd");
	//
	// AccountServicePortType port1 = factory.getWebservicePort(AccountService.class);
	// AccountServicePortType port2 = factory.getWebservicePort(AccountService.class);
	//
	// assertEquals(port1.hashCode(), port2.hashCode());
	// }

}
