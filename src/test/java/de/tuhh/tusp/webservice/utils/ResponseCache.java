package de.tuhh.tusp.webservice.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

import de.tuhh.tusp.webservice.utils.WebserviceFactoryMock.ResponsesType;

class ResponsesCache {

	class NotFoundException extends Exception {

		private static final long serialVersionUID = 5758596867187935245L;

		public NotFoundException(String string) {

			super(string);
		}
	}

	private static Logger logger = LogManager.getLogger(ResponsesCache.class);

	private List<Response> responses = new ArrayList<>();
	private boolean init = false;

	private Resource responsesFile;
	private Map<String, Integer> invocations = new TreeMap<>();

	public ResponsesCache(Resource testResponses) {

		this.responsesFile = testResponses;
	}

	public Response fetch(String key) throws NotFoundException {

		if (!init) {
			init();
		}

		logger.debug("Search Key: " + key);

		for (Response r : responses) {
			if (r.getRegex().matcher(key).matches()) {
				Integer invo = invocations.get(r.getKey()) + 1;

				if (r.getRequestNr() != null && !r.getRequestNr().equals(invo)) {
					logger.debug("Wrong sequence " + invo + " Response: " + r.getRequestNr() + ' ' + r.getKey());
					continue;
				}

				logger.debug("Found Response: " + r.getKey());
				invocations.put(r.getKey(), invo);
				return r;
			}
		}

		throw new NotFoundException("Keine Antwort für '" + key);

	}

	public Map<String, Integer> getInvocations() {

		return invocations;
	}

	private void init() {

		init = true;
		logger.debug("Response init...");

		try {
			// Erstes unmarshallen um alle benötigten Klassen zu finden
			JAXBContext jContext = JAXBContext.newInstance(ResponsesType.class);
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			ResponsesType responses = (ResponsesType) unmarshallerObj.unmarshal(responsesFile.getInputStream());

			// Für Typen die eben nicht bekannt waren steht als value null
			ArrayList<Class<?>> classes = new ArrayList<>();
			classes.add(ResponsesType.class);
			for (String key : responses.responses.keySet()) {
				String[] parts = key.split(" ");
				int index = StringUtils.isNumeric(parts[0]) ? 1 : 0;
				Class<?> clazz = Class.forName(parts[index]);

				for (Method method : clazz.getMethods()) {
					if (!classes.contains(method.getReturnType())) {
						classes.add(method.getReturnType());
					}
				}
			}

			// Jetzt alles laden
			jContext = JAXBContext.newInstance(classes.toArray(new Class[classes.size()]));
			unmarshallerObj = jContext.createUnmarshaller();
			responses = (ResponsesType) unmarshallerObj.unmarshal(responsesFile.getInputStream());
			for (String key : responses.responses.keySet()) {
				Object res = responses.responses.get(key);
				logger.debug(key + " => " + res);
				Response resp = new Response(key, res);
				this.responses.add(resp);
				invocations.put(resp.getKey(), 0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}