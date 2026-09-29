package de.tuhh.tusp.webservice.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;

/**
 * Diese Klasse wird als Bean verwendet und bietet Zugriff auf die HisInOne
 * Webservices. Die Webservices können nicht selbst als Beans verwendet werden,
 * weil Beans beim Start der Anwendung erzeugt werden und die Webservices dabei
 * unter umständen die WSDL-Dateien nicht abrufen können und durch eine
 * Exception den Start verhindern.
 *
 * @author rztjm
 *
 */
@Component
public class WebserviceFactoryImpl implements WebserviceFactory {

	private static Logger logger = LogManager.getLogger(WebserviceFactory.class);

	@Autowired
	private ConfigService config;

	private Map<Class<?>, Object> singletons = new HashMap<>();

//	@Override
//	public GetApplicationDataServicePortType getGetApplicationDataService() throws RuntimeException {
//
//		GetApplicationDataService service = new GetApplicationDataServiceLocator();
//
//		String urlPrefix = config.get("de.tuhh.tusp.psv.mobon.urlPrefix", "", true);
//		try {
//			URL url = new URL(urlPrefix + GetApplicationDataService.class.getSimpleName() + "?wsdl");
//
//			return service.getGetApplicationDataServiceHttpsSoap11Endpoint(url);
//		}
//		catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Object> E getNewWebservicePort(Class<?> serviceClass) throws RuntimeException {

		String urlPrefix = config.get("de.tuhh.tusp.webservice.his.urlPrefix", "", true);
		String username = config.get("de.tuhh.tusp.webservice.his.username", "", true);
		String password = config.get("de.tuhh.tusp.webservice.his.password", "", true);

		if (urlPrefix.isEmpty() || username.isEmpty() || password.isEmpty()) {
			throw new RuntimeException(
					"Die HIS-Webserviceschnittstelle ist nicht configuriert: de.tuhh.tusp.webservice.his.urlPrefix, de.tuhh.tusp.webservice.his.username und de.tuhh.tusp.webservice.his.password");

		}

		E service = null;
		try {
			try {

				URL url = new URL(urlPrefix + serviceClass.getSimpleName() + "?wsdl");
				logger.debug(url);
				Constructor<?> constructor = serviceClass.getConstructor(URL.class);
				service = (E) constructor.newInstance(url);
			} catch (MalformedURLException e) {
				logger.error("", e);
				Constructor<?> constructor;
				constructor = serviceClass.getConstructor();
				service = (E) constructor.newInstance();
			}
		} catch (NoSuchMethodException | SecurityException | InvocationTargetException | IllegalAccessException
				| InstantiationException | IllegalArgumentException e1) {
			logger.fatal("Konnte keine WebserviceInstanz für " + serviceClass.getCanonicalName() + " erzeugen", e1);
			throw new RuntimeException("Konnte Webservice nicht instanzieren: " + serviceClass.getName());
		}

		try {
			Method portGetter = service.getClass().getMethod("get" + serviceClass.getSimpleName() + "Port");
			E port = (E) portGetter.invoke(service);

			// Add Handler Chain
			@SuppressWarnings("rawtypes")
			List<Handler> handlerChain = new ArrayList<>();
			handlerChain.add(new HisWssHandler(username, password));
			((BindingProvider) port).getBinding().setHandlerChain(handlerChain);

			singletons.put(serviceClass, port);
			return port;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NullPointerException e) {
			logger.fatal("Konnte Port für WebserviceInstanz " + serviceClass.getCanonicalName() + " nicht erzeugen", e);
			throw new RuntimeException("Konnte Webservice nicht instanzieren: " + serviceClass.getName());
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public <E extends Object> E getWebservicePort(Class<?> serviceClass) throws RuntimeException {

		if (singletons.containsKey(serviceClass)) {
			return (E) singletons.get(serviceClass);
		}

		return (E) getNewWebservicePort(serviceClass);
	}

}
