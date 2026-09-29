package de.tuhh.tusp.stu.sptim.service;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.sptim.dao.StudentColumns;
import de.tuhh.tusp.stu.sptim.pojo.TimPerson;
import de.tuhh.tusp.stu.sptim.utils.SyscallUtilsImpl.CmdFaildException;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.csv.ParseException;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

public interface TimStudentenService {

	/**
	 * Holt die exprotierbaren Studenten
	 *
	 * @param mc
	 * @param jobLogger
	 * @return Liste der Daten
	 */
	List<Map<StudentColumns, String>> getExportStudents(MessageCollector mc, JobLogger jobLogger);

	/**
	 * Importiert eine Liste an Rückläufern
	 *
	 * @param personList
	 * @param mc
	 */
	void importiere(List<TimPerson> personList, MessageCollector mc);

	/**
	 * Holt ein Rückläufer-File vom externen Server und liest es ein.
	 *
	 * @param mc
	 * @return Liste der Rückläufer
	 * @throws ParseException       Wenn die Datei nicht geparst werden kann
	 * @throws IOException          wenn die Datei nicht abrufbar ist
	 * @throws InterruptedException wenn das Abrufprogramm abgebrochen wird
	 * @throws CmdFaildException    Wenn das Abrufen nicht funktioniert
	 */
	List<TimPerson> readRuecklaeufer(MessageCollector mc, JobLogger jobLogger)
			throws ParseException, IOException, CmdFaildException, InterruptedException;

	/**
	 * Aktualisiert (status = u) einen Account und eine Mailadresse oder legt sie an
	 * (Status = n)
	 *
	 * @param status
	 * @param hisPerson
	 *
	 * @throws RuntimeException Wirft RuntimeException, wenn status n und der
	 *                          Account existiert schon oder wenn status u und der
	 *                          Account gehört einer anderen Person.
	 */
	void savePerson(String status, HisPerson hisPerson) throws RuntimeException;

	/**
	 * Übertragt ein Studenten als File an den Zielserver
	 *
	 * @param studenten
	 * @param time
	 * @param mc
	 * @throws IOException          Wenn das File nicht erstellt werden kann
	 * @throws InterruptedException wenn das Abrufprogramm abgebrochen wird
	 * @throws CmdFaildException    Wenn das Senden nicht funktioniert
	 */
	void sentStudentFile(List<Map<StudentColumns, String>> studenten, GregorianCalendar time, MessageCollector mc)
			throws IOException, CmdFaildException, InterruptedException;
}
