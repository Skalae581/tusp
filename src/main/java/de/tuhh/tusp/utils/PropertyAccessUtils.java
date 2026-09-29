package de.tuhh.tusp.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Klasse stellt Methoden bereit um von einem Pojo oder einer Hashmap einen Wert abzufragen
 *
 * @author rztjm
 *
 */
public class PropertyAccessUtils {

	/**
	 * Holt einen Wert aus einer Map oder einem Pojo anhand des Namens. Throws RuntimeExceptions!
	 *
	 * @param object
	 *            Map oder Pojo
	 * @param property
	 *            Name der Property
	 *
	 * @throws RuntimeException
	 *             Bei wenn die Property nicht existiert oder nicht zugreifbar ist
	 *
	 * @return den Wert der Eigenschaft oder null, wenn {@code object} null ist
	 */
	public static Object getProperty(Object object, String property) throws RuntimeException {

		if (object == null) {
			return null;
		}

		if (property == null || property.isEmpty()) {
			throw new IllegalArgumentException("Empty property not allowed");
		}

		// Aus einer Map einfach nehmen :)
		if (object instanceof Map<?, ?>) {
			return ((Map<?, ?>) object).get(property);
		}

		// Aus einem Pojo herauskitzel :s
		String propertyName = "get" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
		try {
			Method method = object.getClass().getMethod(propertyName, new Class[0]);
			return method.invoke(object, new Object[0]);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
