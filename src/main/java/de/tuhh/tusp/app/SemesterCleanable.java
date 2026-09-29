package de.tuhh.tusp.app;

import java.util.List;

/**
 * Interface, für Klassen die eine Semesterbereinigung in APP umsetzen.
 *
 * @author rztjm
 *
 */
public interface SemesterCleanable {

	class NotImplementedException extends Exception {

		private static final long serialVersionUID = -4039893428732002854L;
	}

	/**
	 * Führt die Bereinigung eines Semesters aus.
	 *
	 * @param semester
	 *            Angabe des Semesters z.B. 20182 oder 20181
	 * @return Anzahl der Bereinigten Datensätze
	 * @throws Exception
	 *             Wenn etwas schief geht.
	 */
	Integer cleanSemeser(String semester) throws Exception;

	/**
	 * Gibt eine Liste aller Semester zurück, die Bereinigt werden könnten.
	 *
	 * Ein Semester wird als Zahl in der Form 20181 (Sose 2018) oder 20182 (Wise2018/2019) erwartet
	 *
	 * @return Gibt eine Liste der vorhandenen Semester zurück.
	 *
	 * @throws NotImplementedException
	 *             Wenn diese Methode nicht implementiert ist. Es muss nur mindestens eine Implementierung geben, die
	 *             Semester liefert.
	 */
	List<String> getSemester() throws NotImplementedException;
}
