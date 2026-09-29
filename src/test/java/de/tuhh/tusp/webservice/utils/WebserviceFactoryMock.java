package de.tuhh.tusp.webservice.utils;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

//import com.sop.service.GetApplicationDataServicePortType;

import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.TestUtils;
import de.tuhh.tusp.webservice.utils.ResponsesCache.NotFoundException;

public class WebserviceFactoryMock implements WebserviceFactory {

	@XmlRootElement
	public static class ResponsesType {

		public HashMap<String, Object> responses = new HashMap<>();
	}

	private static Logger logger = LogManager.getLogger(WebserviceFactoryMock.class);

	private Resource responsesFile;
	private ResponsesCache responses;
	private Map<Class<?>, Object> singletons = new HashMap<>();

	private InvocationHandler invo = new InvocationHandler() {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			String key = method.getDeclaringClass().getTypeName() + " " + method.getName() + "(" + argsToString(args)
					+ ")";

			try {
				Response response = responses.fetch(key);
				return response.getResponse();
			} catch (NotFoundException e) {
				printExample(key, method);

				throw new RuntimeException(
						"Keine Antwort für '" + key + "' in " + responsesFile.getFile().getAbsolutePath());
			}

		}

		private void printExample(String key, Method method) {

			try {
				ResponsesType example = new ResponsesType();
				example.responses.put(key, TestUtils.manufacturePojo(method.getReturnType(), 3));

				JAXBContext jContext = JAXBContext.newInstance(ResponsesType.class, method.getReturnType());
				Marshaller marshallerObj = jContext.createMarshaller();
				marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter stringWriter = new StringWriter();

				marshallerObj.marshal(example, stringWriter);

				System.out.println("Beispiel für " + key + " könnte ergänzt werden in "
						+ responsesFile.getFile().getAbsolutePath());
				System.out.println(stringWriter.toString());
			} catch (Exception e) {
				System.out.println(key);
				throw new RuntimeException(e);
			}

		}
	};

	public WebserviceFactoryMock(Resource testResponses) {
		this.responsesFile = testResponses;
		this.responses = new ResponsesCache(testResponses);
	}

	private String argsToString(Object[] args) {

		if (args.length == 0) {
			return "";
		}

		StringBuilder ret = new StringBuilder();
		for (Object o : args) {
			ret.append(argsToStringHelper(o, null));
			ret.append(", ");
		}

		return ret.substring(0, ret.length() - 2);
	}

	private String argsToStringHelper(Object o, Object parent) {

		if (o == null) {
			return "null";
		} else if (o instanceof String) {
			return "\"" + o + "\"";
		} else if (o instanceof Number || o instanceof Boolean) {
			return o + "";
		} else if (o.getClass().isEnum()) {
			return ((Enum<?>) o).name();
		} else if (o instanceof List) {
			return o.getClass().getSimpleName() + "(size=" + ((List<?>) o).size() + ")";
		} else if (o instanceof Map) {
			return o.getClass().getSimpleName() + "(size=" + ((Map<?, ?>) o).size() + ")";
		} else if (o instanceof XMLGregorianCalendar) {
			return CalendarUtils.toString((XMLGregorianCalendar) o, CalendarUtils.DATEFORMAT_ENGLISH);
		} else if (o instanceof Object) {
			StringBuilder builder = new StringBuilder();
			builder.append(o.getClass().getSimpleName());
			builder.append("{");
			if (o.getClass().getDeclaredFields().length == 0) {
				builder.append("}");
				return builder.toString();
			}
			for (Field field : o.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				try {
					builder.append(argsToStringHelper(field.get(o), o));
					builder.append(", ");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.println(e.getStackTrace());
				}
			}
			return builder.substring(0, builder.length() - 2) + "}";
		} else {
			throw new RuntimeException("Type not implemented: " + o.getClass());
		}
	}

//	@Override
//	public GetApplicationDataServicePortType getGetApplicationDataService() throws RuntimeException {
//
//		return Mockito.mock(GetApplicationDataServicePortType.class);
//	}

	public Map<String, Integer> getInvocations() {

		return responses.getInvocations();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> E getNewWebservicePort(Class<?> serviceClass) throws RuntimeException {

		String classname = serviceClass.getName() + "PortType";

		try {
			Class<?> clazz = Class.forName(classname);

			return (E) java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(), new java.lang.Class[] { clazz },
					invo);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> E getWebservicePort(Class<?> serviceClass) throws RuntimeException {

		if (singletons.containsKey(serviceClass)) {
			return (E) singletons.get(serviceClass);
		}

		return getNewWebservicePort(serviceClass);
	}
}
