package de.tuhh.tusp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;

import javax.el.MethodNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.tuhh.tusp.webservice.wsoutput.pojo.HisAccount;
import lombok.Data;

/**
 * Klasse soll Methoden für Standard-Tests bereitstellen.
 *
 * Standard-Tests klingen sinnlos, es muss aber irgendwie sichergestellt werden,
 * dass JOOQ mit einem gültigen Stand der Datenbank arbeitet, ...
 *
 * Außerdem: Ein Dao-Test ist doch immer das Gleiche :)
 *
 * @author rztjm
 *
 * @param <T>
 */
public class TestUtils {

	/**
	 * Pojo-Klasse, mit der Änderungen an einem Objekt abgebildet werden können.
	 *
	 * @author rztjm
	 *
	 */
	@Data
	public static class Change {

		String methodName;

		Class<?>[] parameterTypes;
		Object[] parameterValues;

		public Change(String methodName, Object... methodParams) {

			this.methodName = methodName;
			parameterTypes = new Class<?>[methodParams.length];
			parameterValues = new Object[methodParams.length];

			for (int i = 0; i < methodParams.length; i++) {
				Object o = methodParams[i];
				parameterTypes[i] = o.getClass();
				parameterValues[i] = o;
			}
		}
	}

	private static Logger logger = LogManager.getLogger(TestUtils.class);

	/**
	 * Liest den Wert aus einem Field unabhängig von von Modifiern
	 *
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object getField(Object object, String fieldName) {

		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object invokeMethod(Object object, String methodName, Object[] args) {

		Class<?>[] argClasses;
		if (args == null) {
			argClasses = null;
		} else {
			argClasses = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				argClasses[i] = args[i].getClass();
			}
		}
		try {
			Method method = object.getClass().getDeclaredMethod(methodName, argClasses);
			method.setAccessible(true);
			return method.invoke(object, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Erzeugt ein Objekt mit zufällig gefüllten Properties
	 *
	 * @param clazz
	 * @return Eine Dummy-Instanz
	 */
	public static <T> T manufacturePojo(Class<T> clazz) {

		return manufacturePojo(clazz, 0);
	}

	/**
	 * Erzeugt ein Objekt mit zufällig gefüllten Properties
	 *
	 * @param clazz
	 * @param complexTypeLevels Gibt an, bis zu wie vielen Ebenen auch komplexe
	 *                          Typen erzeugt werden sollen
	 * @return Eine Dummy-Instanz
	 */
	@SuppressWarnings("unchecked")
	public static <T> T manufacturePojo(Class<T> clazz, int complexTypeLevels) {

		if (clazz.getName().equals("long")) {
			return (T) randomLong();
		}
		if (clazz.getName().equals("int")) {
			return (T) randomInteger();
		}

		if (clazz.getName().equals("void")) {
			return null;
		}
		if (clazz.getName().equals("long")) {
			return (T) randomLong();
		}
		if (clazz.getName().equals("int")) {
			return (T) randomInteger();
		}
		if (clazz.getName().equals("boolean")) {
			return (T) (randomInteger(0, 1) == 1 ? Boolean.TRUE : Boolean.FALSE);
		}
		if (clazz.getName().equals("byte")) {
			return (T) randomByte();
		}
		if (clazz.getName().equals("java.lang.Long")) {
			return (T) randomLong();
		}

		try {

			// Default immer den ersten Constructor nehmen oder den ohne
			// Parameter
			if (clazz.getConstructors().length == 0) {
				throw new MethodNotFoundException(clazz.getCanonicalName() + " Hat keinen Constructor!");
			}
			Constructor<?> construct = clazz.getConstructors()[0];
			for (Constructor<?> c : clazz.getConstructors()) {
				if (c.getParameterCount() == 0) {
					construct = c;
					break;
				}
			}

			// Leeres Objekt instanzieren
			Parameter[] parameters = construct.getParameters();
			Object[] parameterMock = new Object[parameters.length];
			construct.setAccessible(true);
			T object = (T) construct.newInstance(parameterMock);

			Class<?> c = clazz;
			while (c != null) {
				for (Field field : c.getDeclaredFields()) {
					field.setAccessible(true);

					if (field.getName().equals("serialVersionUID")) {
						continue;
					}

					if (Modifier.isStatic(field.getModifiers())) {
						continue;
					}

					if (field.getType().isEnum()) {
						field.set(object, field.getType().getEnumConstants()[0]);
						continue;
					}

					if ((field.getModifiers() & java.lang.reflect.Modifier.FINAL) == java.lang.reflect.Modifier.FINAL) {
						continue;
					}

					switch (field.getType().getName()) {
					case "java.lang.String":
						field.set(object, randomString(32));
						break;

					case "java.lang.Long":
						field.set(object, randomLong());
						break;

					case "long": // unerwünscht
						field.set(object, randomLong());
						break;

					case "java.lang.Integer":
						field.set(object, randomInteger());
						break;

					case "java.lang.Double":
						field.set(object, (double) randomInteger() / (double) randomInteger());
						break;

					case "int": // unerwünscht
						field.set(object, randomInteger());
						break;

					case "java.math.BigInteger":
						field.set(object, new BigInteger(randomInteger(5, 64), new Random()));
						break;

					case "java.math.BigDecimal":
						field.set(object, new BigDecimal((double) randomInteger() / (double) randomInteger()));
						break;

					case "java.lang.Boolean":
						field.set(object, randomInteger(0, 1) == 1);
						break;

					case "boolean":
						field.set(object, randomInteger(0, 1) == 1);
						break;

					case "java.util.Date":
						field.set(object, new Date(randomLong(-2208988800000L, 4133980799000L)));
						break;

					case "java.time.LocalDate":
						field.set(object,
								LocalDate.of(randomInteger(-100, 3000), randomInteger(1, 12), randomInteger(1, 28)));
						break;

					case "java.time.LocalTime":
						field.set(object,
								LocalTime.of(randomInteger(0, 23), randomInteger(0, 59), randomInteger(0, 59)));
						break;

					case "java.time.LocalDateTime":
						field.set(object,
								LocalDateTime.of(randomInteger(1900, 2100), randomInteger(1, 12), randomInteger(1, 28),
										randomInteger(0, 23), randomInteger(0, 59), randomInteger(0, 59)));
						break;

					case "java.sql.Timestamp":
						field.set(object, new Timestamp(randomInteger()));
						break;

					case "javax.xml.datatype.XMLGregorianCalendar":
						field.set(object,
								CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.SECOND, randomInteger()));
						break;

					case "java.util.List":
						field.set(object, new ArrayList<>());
						break;

					case "[B": // byte[]
						byte[] bytes = new byte[randomInteger(0, 1024)];
						for (int i = 0; i < bytes.length; i++) {
							bytes[i] = randomByte();
						}
						field.set(object, bytes);
						break;

					default:
						if (complexTypeLevels > 0) {
							field.set(object, manufacturePojo(field.getType(), complexTypeLevels - 1));
						} else {
							throw new NotImplementedException(
									"Typ " + field.getType().getName() + " wird noch nicht unterstützt");
						}
					}
				}
				c = c.getSuperclass(); // Neue Runde
			}

			return object;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			logger.warn("Testobjekt vom Typ " + clazz.getName() + " nicht erstellt " + e.toString());
			throw new RuntimeException(e);
		}
	}

	/**
	 * Erstellt einen Mock für mit der Klasse clazz und setzt diesen wenn möglich in
	 * das entsprechende Filed von component
	 *
	 * @param component
	 * @param clazz
	 * @throws RuntimeException
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mockAutowiredField(Object component, Class<T> clazz) throws RuntimeException {

		Object mock = Mockito.mock(clazz);

		return mockAutowiredField(component, (T) mock);
	}

	/**
	 * Setzt mock wenn möglich in das entsprechende Filed von component
	 *
	 * @param component
	 * @param clazz
	 * @throws RuntimeException
	 * @return
	 */
	public static <T> T mockAutowiredField(Object component, T mock) throws RuntimeException {

		String fieldName = null;
		String mockClass = mock.getClass().getName();
		mockClass = mockClass.substring(0, mockClass.indexOf("$"));
		for (Field field : component.getClass().getDeclaredFields()) {
			String fieldClass = field.getType().getName();

			if (!mockClass.equals(fieldClass)) {
				continue;
			}

			Autowired annotation = field.getAnnotation(Autowired.class);

			if (annotation != null) {
				fieldName = field.getName();
				break;
			}

		}

		if (fieldName == null) {
			throw new RuntimeException("Kein Field mit Typ " + mock.getClass().getName() + " gefunden.");
		}

		setField(component, fieldName, mock);

		return mock;
	}

	private static Byte randomByte() {

		if (Math.random() > 0.5) {
			return Byte.valueOf((byte) (Math.random() * Byte.MAX_VALUE));
		} else {
			return Byte.valueOf((byte) (Math.random() * Byte.MIN_VALUE));
		}
	}

	private static Integer randomInteger() {

		return randomInteger(Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2);
	}

	private static Integer randomInteger(int min, int max) {

		int interval = max - min + 1;
		int rand = (int) (Math.random() * interval);
		return rand + min;
	}

	private static Long randomLong() {

		return randomLong(Long.MIN_VALUE / 2, Long.MAX_VALUE / 2);
	}

	private static Long randomLong(long min, long max) {

		long interval = max - min + 1;
		long rand = (long) (Math.random() * interval);
		return rand + min;
	}

	private static String randomString(int maxLength) {

		String chars = "0123456789" // Ziffern
				+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ" // Großbuchstaben
				+ "abcdefghijklmnopqrstuvw‌​xyz" // Kleinbuchstaben
				+ "\"'+*~-.,_:;<>|!§$%&/()=?[]{}" // ASCII Sonderzeichen
				+ "öäüÖÜÄß" // Deutsch Sonderzeichen
				+ "áàâ" // Was für Franzosen
				+ "µ"; // Und Griechen
		int max = (int) (Math.random() * maxLength);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= max; i++) {
			char c = chars.charAt((int) (Math.random() * chars.length()));
			sb.append(c);
		}
		return sb.toString();
	}

	public static void setField(Object object, String fieldName, Object value) {

		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, value);
		} catch (Exception e) {
			logger.error("Problem bei " + object.getClass().getName() + "." + fieldName, e);
			throw new RuntimeException(e);
		}
	}

	Reflections reflections = new Reflections("de.tuhh.tusp", new MethodAnnotationsScanner(),
			new TypeAnnotationsScanner(), new SubTypesScanner(false));

	/**
	 * Diese Methode stellt sicher, dass kein Getter eines Pojos null zurückliefert,
	 * es sei denn, er wurde explizit genannt. Dadurch soll verhindert werden, dass
	 * durch die Erweiterung eines Pojos die Tests unzureichend werden.
	 *
	 * @param object             Das Testobjekt
	 * @param ignoreGetterByName Liste aller Getter die null zurückgeben dürfen
	 */
	public void assertNoFieldIsNull(Object object, String... ignoreGetterByName) {

		for (Method m : object.getClass().getMethods()) {

			if (!m.getName().startsWith("get")) {
				continue;
			}

			boolean skip = false;
			for (int i = 0; i < ignoreGetterByName.length; i++) {
				if (m.getName().equals(ignoreGetterByName[i])) {
					skip = true;
					break;
				}
			}

			if (skip) {
				continue;
			}

			Object ret = null;
			try {
				ret = m.invoke(object);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}

			if (ret == null) {
				throw new IllegalStateException("Method " + m.getName() + " returned null!");
			}
		}

	}

	private void comparePojos(Object pojo1, Object pojo2, String[] ignoreChangesInGetters) {

		if (!pojo1.getClass().getName().equals(pojo2.getClass().getName())) {
			throw new IllegalArgumentException("Beide Testkandidaten müssen den gleichen Typ haben: "
					+ pojo1.getClass().getName() + " " + pojo2.getClass().getName());
		}

		for (Method m : pojo1.getClass().getMethods()) {

			if (!m.getName().startsWith("get")) {
				continue;
			}

			boolean skip = false;
			for (int i = 0; i < ignoreChangesInGetters.length; i++) {
				if (m.getName().equals(ignoreChangesInGetters[i])) {
					skip = true;
					break;
				}
			}

			if (skip) {
				continue;
			}

			Object ret1 = null;
			Object ret2 = null;
			try {
				ret1 = m.invoke(pojo1);
				ret2 = m.invoke(pojo2);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}

			String date1 = ret1 == null ? "null" : ret1.getClass().getName() + "(" + ret1.toString() + ")";
			String date2 = ret2 == null ? "null" : ret2.getClass().getName() + "(" + ret2.toString() + ")";

			if (!date1.equals(date2)) {
				throw new IllegalStateException("Werte stimmen nicht überein für '" + date1 + "' != '" + date2 + "'");
			}
		}
	}

	/**
	 * Dieser Test sorgt im Wesentlichen dafür, dass die Testabdeckung nicht durch
	 * lombock-Methoden heruntergezogen wird.
	 */
	@Test
	public void equalsTest() throws Exception {

		for (Class<?> clazz : getPojoClasses()) {
			try {
				equalsTest(clazz);
				logger.info(clazz.getName() + "#equals() aufgerufen. ");
			} catch (NotImplementedException e) {
				logger.warn("Das Pojo " + clazz.getName() + " ist zu komplex für den equalsTest(). " + e.toString());
			} catch (RuntimeException e) {
				if (e.getCause() != null && e.getCause() instanceof InvocationTargetException
						&& e.getCause().getCause() != null && e.getCause().getCause() instanceof NullPointerException) {
					logger.warn("Das Pojo " + clazz.getName()
							+ " verwendet vermutlich @NonNull fields und kann daher nicht abgedeckt werden.");
				} else {
					throw e;
				}
			}
		}
	}

	/**
	 * Testet, ob jedes Feld eines Objekts auch auf die Berechnung von hashCode()
	 * Einfluss hat.
	 *
	 * @param clazz
	 */
	public void equalsTest(Class<?> clazz) throws Exception {

		if (clazz.getSuperclass() != Object.class) {
			// TODO pojos, die andere erweitern erzeugen Fehler in der Lombock
			// .equals()
			return;
		}

		for (Method m : clazz.getMethods()) {
			m.setAccessible(true);
		}

		Object object1 = manufacturePojo(clazz);
		Object object2 = manufacturePojo(clazz);
		Object object3 = manufacturePojo(clazz);

		List<Field> testableFields = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();

		Class<?> c = clazz;
		while (c != null) {
			for (Field field : fields) {
				if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				if (field.getName().contains("this$0")) {
					continue;
				}
				if (Modifier.isTransient(field.getModifiers())) {
					continue;
				}

				field.setAccessible(true);

				// 3 wird wie 1
				field.set(object3, field.get(object1));

				// Sicherstellen, dass Object 2 != Object 1
				if ("java.lang.Boolean".equals(field.getType().getName())
						|| "boolean".equals(field.getType().getName())) {
					field.set(object2, !(boolean) field.get(object1));
				} else if (field.getType().isEnum()) {
					field.set(object2, field.getType().getEnumConstants()[1]);
				} else if ("[B".equals(field.getType().getName())) {
					continue; // Byte-Arrays verändern den hashCode nur manchmal
								// :-O
				} else if ("java.util.List".equals(field.getType().getName())) {
					field.set(object2, null);
				}

				testableFields.add(field);
			}
			c = c.getSuperclass();
		}

		assertTrue(object1.equals(object1));
		assertFalse(object1.equals(object2));
		assertFalse(object1.equals(new String()));
		assertFalse(object1.equals(null));

		for (Field field : testableFields) {

			// Unterschied erstellen
			field.set(object1, field.get(object2));

			assertFalse("Field " + field.getName() + " " + object1 + " should not equal " + object3,
					object1.equals(object3));

			// Unterschied zurücknehmen
			field.set(object1, field.get(object3));

			assertTrue(object1 + " should equal " + object3, object1.equals(object3));
		}

	}

	private List<Class<?>> getPojoClasses() throws ClassNotFoundException {

		List<Class<?>> result = new ArrayList<>();
		Set<String> classes = reflections.getAllTypes();
		for (String clazzName : classes) {
			if (!clazzName.contains(".pojo.")) {
				continue;
			}
			if (clazzName.contains("Wrapper")) {
				continue;
			}
			Class<?> clazz = Class.forName(clazzName);
			if (clazz.isInterface()) {
				continue;
			}
			if (Modifier.isAbstract(clazz.getModifiers())) {
				continue;
			}
			result.add(clazz);
		}
		return result;
	}

	/**
	 * Dieser Test sorgt im Wesentlichen dafür, dass die Testabdeckung nicht durch
	 * lombock-Methoden heruntergezogen wird.
	 */
	@Test
	public void hashCodeTest() throws Exception {

		for (Class<?> clazz : getPojoClasses()) {
			try {
				hashCodeTest(clazz);
				logger.info(clazz.getName() + "#hashCode() aufgerufen. ");
			} catch (NotImplementedException e) {
				logger.warn("Das Pojo " + clazz.getName() + " ist zu komplex für den hashCodeTest(). " + e.toString());
			} catch (RuntimeException e) {
				if (e.getCause() != null && e.getCause() instanceof InvocationTargetException
						&& e.getCause().getCause() != null && e.getCause().getCause() instanceof NullPointerException) {
					logger.warn("Das Pojo " + clazz.getName()
							+ " verwendet vermutlich @NonNull fields und kann daher nicht abgedeckt werden.");
				} else {
					throw e;
				}
			}
		}
	}

	/**
	 * Testet, ob jedes Feld eines Objekts auch auf die Berechnung von hashCode()
	 * Einfluss hat.
	 *
	 * @param clazz
	 */
	public void hashCodeTest(Class<?> clazz) throws Exception {

		Object object1 = manufacturePojo(clazz);
		Object object2 = manufacturePojo(clazz);
		Method hashCode = clazz.getMethod("hashCode");
		hashCode.setAccessible(true);
		Integer hashCodeBisher = (Integer) hashCode.invoke(object1);

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			field.setAccessible(true);

			// Sicherstellen, dass Object 2 != Object 1
			if ("java.lang.Boolean".equals(field.getType().getName())) {
				field.set(object2, !(boolean) field.get(object1));
			} else if (field.getType().isEnum()) {
				field.set(object2, field.getType().getEnumConstants()[1]);
			} else if ("[B".equals(field.getType().getName())) {
				continue; // Byte-Arrays verändern den hashCode nur manchmal :-O
			}

			if (field.getName().startsWith("this$")) {
				// Innerclasses haben die Parnet-Klasse
				continue;
			}

			// Notbremse, wenn werte gleich sind
			if (field.get(object2).equals(field.get(object1))) {
				logger.warn("Field " + field.getName() + " ist identisch und verändert hashCode nicht. Wert:"
						+ field.get(object1) + "  ==  " + field.get(object2));
				continue;
			}

			// Wert aus Objekt 2 in Objekt 1 schrieben
			field.set(object1, field.get(object2));

			Integer hashCodeNeu = (Integer) hashCode.invoke(object1);
			if (hashCodeBisher.equals(hashCodeNeu)) {
				logger.info("Field " + field.getName() + " hatte keinen Einfluss auf hashCode()");
			}
			hashCodeBisher = hashCodeNeu;
		}

		// Test mit leeren Objekt
		Constructor<?> construct = clazz.getConstructors()[0];
		construct.setAccessible(true);
		if (construct.getParameterCount() > 0) {
			return;
		}
		Object o1 = construct.newInstance();
		Object o2 = construct.newInstance();
		assertEquals(o1.hashCode(), o2.hashCode());
	}

	/**
	 * Funktion macht einfache Tests mit dem Dao (insert, read, update und delete).
	 *
	 * @param dao     Das zu testende Dao
	 * @param pojo    Ein Beispielobjekt
	 * @param changes Änderungen, die an dem Objekt vor dem Update gemacht werden
	 *                sollen
	 */
	public <T> void simpleCrudTestForDao(BaseDao<T> dao, T pojo, String[] ignoreChangesInGetters, Change... changes) {

		if (ignoreChangesInGetters == null) {
			ignoreChangesInGetters = new String[0];
		}

		// Pojo validieren
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(pojo);
		if (!constraintViolations.isEmpty()) {
			String list = "\nFolgende ConstraintViolations sind aufgetreten: \n";
			for (ConstraintViolation<T> cv : constraintViolations) {
				list += cv.getPropertyPath().iterator().next().getName() + " " + cv.getMessage() + "\n";
			}
			throw new ConstraintViolationException(list, constraintViolations);
		}

		// initial speichern
		Long id = dao.insert(pojo);

		// gespeichertes Obj laden
		T persistentPojo = dao.fetchById(id);

		// Testen, das alles identisch geblieben ist außer der angegebenen
		// Methoden
		comparePojos(pojo, persistentPojo, ignoreChangesInGetters);

		// Änderungen machen
		for (Change c : changes) {

			try {
				Method method = pojo.getClass().getMethod(c.methodName, c.parameterTypes);
				if (method != null) {

					method.invoke(pojo, c.parameterValues);
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				throw new RuntimeException(e);
			}
		}

		// Änderungen persistieren
		dao.update(persistentPojo);

		// Testen ob Änderungen gemacht wurden
		T newestPojo = dao.fetchById(id);
		comparePojos(persistentPojo, newestPojo, ignoreChangesInGetters);

		// Objekt löschen
		dao.delete(id);

		// Testen, dass Obj echt weg ist
		try {
			dao.fetchById(id);
			throw new IllegalStateException(
					"Löschen des Objekts mit " + dao.getClass().getName() + " hat nicht funktioniert.");
		} catch (NoSuchElementException e) {
			// Alles super, die Exception musss ja kommen
		}
	}

	/**
	 * Dieser Test sorgt im Wesentlichen dafür, dass die Testabdeckung nicht durch
	 * lombock-Methoden heruntergezogen wird.
	 *
	 * @throws Exception
	 */
	@Test
	public void toStringTest() throws Exception {

		for (Class<?> clazz : getPojoClasses()) {
			try {
				toStringTest(clazz);
				logger.info(clazz.getName() + "toString() aufgerufen. ");
			} catch (NotImplementedException e) {
				logger.warn("Das Pojo " + clazz.getName() + " ist zu komplex für den toStringTest(). " + e.toString());
			} catch (RuntimeException e) {
				if (e.getCause() != null && e.getCause() instanceof InvocationTargetException
						&& e.getCause().getCause() != null && e.getCause().getCause() instanceof NullPointerException) {
					logger.warn("Das Pojo " + clazz.getName()
							+ " verwendet vermutlich @NonNull fields und kann daher nicht abgedeckt werden.");
				} else {
					throw e;
				}
			}
		}
	}

	/**
	 * Testet, ob die toString() Methode alle Felder des Objekts und deren Werte mit
	 * in die Ausgabe einbaut.
	 *
	 * @param object
	 * @throws IllegalAccessException   egal
	 * @throws IllegalArgumentException egal
	 */
	public void toStringTest(Class<?> clazz) throws Exception {

		if (clazz == HisAccount.class) {
			return;
		}

		Method toString = clazz.getMethod("toString");
		toString.setAccessible(true);
		Object object = manufacturePojo(clazz);
		String string = object.toString();

		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			String fName = field.getName();

			if (fName.startsWith("this$")) {
				// Innerclasses haben die Parnet-Klasse
				continue;
			}

			field.setAccessible(true);
			Object value = field.get(object);
			String fValue = value != null ? value.toString() : "null";

			String part = fName + "=" + fValue;

			if (field.getType().isArray()) {
				// Hier wäre es zu viel Aufwand Arrays genauer zu analysieren
				part = fName;
			}

			boolean result = string.contains(part);
			assertTrue("'" + string + "' enthält '" + part + "' nicht", result);
		}
	}

	@Test
	public void touchGetterAndSetterTest() throws Exception {

		for (Class<?> clazz : getPojoClasses()) {
			try {
				touchGetterAndSetterTest(clazz);
				logger.info(clazz.getName() + "touchGetterAndSetterTest() aufgerufen. ");
			} catch (NotImplementedException e) {
				logger.warn("Das Pojo " + clazz.getName() + " ist zu komplex für den touchGetterAndSetterTest(). "
						+ e.toString());
			} catch (RuntimeException e) {
				if (e.getCause() != null && e.getCause() instanceof InvocationTargetException
						&& e.getCause().getCause() != null && e.getCause().getCause() instanceof NullPointerException) {
					logger.warn("Das Pojo " + clazz.getName()
							+ " verwendet vermutlich @NonNull fields und kann daher nicht abgedeckt werden.");
				} else {
					throw e;
				}
			}
		}
	}

	public void touchGetterAndSetterTest(Class<?> clazz) throws Exception {

		Object object = manufacturePojo(clazz);

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
				continue;
			}

			field.setAccessible(true);

			Object oldVal = field.get(object);

			String fieldname = field.getName();
			fieldname = fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);

			if (fieldname.startsWith("This$")) {
				// Innerclasses haben die Parnet-Klasse
				continue;
			}

			Method getter;
			try {
				getter = clazz.getMethod("get" + fieldname);
			} catch (NoSuchMethodException e) {
				// Wenn es keinen getter gibt, gibt es vlt eine is-Methode
				getter = clazz.getMethod("is" + fieldname);
			}
			Method setter = clazz.getMethod("set" + fieldname, getter.getReturnType());

			getter.setAccessible(true);
			setter.setAccessible(true);

			try {
				if (field.getType().equals(Integer.TYPE)) {
					setter.invoke(object, 1);
					assertEquals(1, field.get(object));
				} else if (field.getType().equals(Long.TYPE)) {
					setter.invoke(object, 1L);
					assertEquals(1L, field.get(object));
				} else {
					setter.invoke(object, (Object) null);

					assertNull(field.get(object));
					assertNull(getter.invoke(object));
				}
			} catch (IllegalArgumentException e) {

				logger.error("Nichtunterstützter Typ in " + clazz.getName() + "#" + fieldname);
			}

			setter.invoke(object, oldVal);

			assertNotNull(field.get(object));
			assertNotNull(getter.invoke(object));
		}
	}

	@Test
	public void valueOfTest() throws Exception {

		ImmutableSet<ClassPath.ClassInfo> classes = ClassPath.from(ClassLoader.getSystemClassLoader())
				.getTopLevelClasses();
		for (ClassInfo clazzInfo : classes) {
			String clazzName = clazzInfo.getName();
			if (!clazzName.contains(".tusp.")) {
				continue;
			}
			Class<?> clazz = Class.forName(clazzName);
			if (!clazz.isEnum()) {
				continue;
			}

			Method valueOf = clazz.getMethod("valueOf", String.class);
			List<?> consts = Arrays.asList(clazz.getEnumConstants());
			for (Object o : consts) {
				assertTrue(o.equals(valueOf.invoke(o, o.toString())));
			}
			if (!consts.isEmpty()) {
				try {
					valueOf.invoke(consts.get(0), "__test1233__");
					fail("Enumkomstante scheint ernsthaft zu existieren: __test1233__");
				} catch (InvocationTargetException e) {
					// Jupii
				}
			}
		}
	}
}
