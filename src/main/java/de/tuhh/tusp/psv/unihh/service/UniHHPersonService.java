package de.tuhh.tusp.psv.unihh.service;

import java.util.List;

import de.tuhh.tusp.psv.unihh.main.UniHHImport;
import de.tuhh.tusp.psv.unihh.pojo.UniHHPerson;
import de.tuhh.tusp.psv.unihh.pojo.UniHHStud;

public interface UniHHPersonService {

	/**
	 * Importiert die Daten aus personList und studList und Stoppt, falls threat.isRunning() == false ist
	 * 
	 * @param personList
	 * @param studList
	 * @param threat
	 */
	void importHHData(List<UniHHPerson> personList, List<UniHHStud> studList, UniHHImport threat);
}
