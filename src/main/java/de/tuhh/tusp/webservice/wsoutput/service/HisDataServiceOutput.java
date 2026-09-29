package de.tuhh.tusp.webservice.wsoutput.service;

import org.springframework.core.io.WritableResource;

import de.tuhh.tusp.psv.UpdateStudentFileWriter;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisStudent;

public interface HisDataServiceOutput {

	/**
	 * Speichert eine {@link HisPerson}. Ein Insert oder Update aller Eigenschaften wird gemacht.
	 *
	 * @param user
	 * @param student
	 *            StudentenDaten falls vorhanden, sonst null
	 * @param collector
	 *            Bekommt die Info wie viele Personen erstellt / aktualisiert wurden
	 * @return die PersonID
	 * @throws ServiceFault
	 *             Bei Webservice Problemen
	 */
	Long savePerson(HisPerson user, HisStudent student, MessageCollector collector, WritableResource file,
					UpdateStudentFileWriter updateStudentFileWriter) throws ServiceFault;
}
