package de.tuhh.tusp.psv.unihh.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.core.io.Resource;

import de.tuhh.tusp.psv.unihh.pojo.UniHHPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;

public interface UniHHPersonDao {

	/**
	 * Convertiert eine {@link UniHHPerson} in eine {@link HisPerson}
	 *
	 * @param user
	 * @return die person
	 */
	HisPerson convertPersonData(UniHHPerson user);

	/**
	 * Liest die Personendaten aus einer Datei
	 *
	 * @param csv
	 * @param delimiter
	 * @return Die Studierendendaten
	 * @throws FileNotFoundException
	 *             Wenn Daitei nicht existiert
	 * @throws IOException
	 *             Wenn Datei nicht lesbar ist
	 */
	List<UniHHPerson> readPersonData(Resource csv, char delimiter, Charset charset)	throws FileNotFoundException,
																					IOException;

}
