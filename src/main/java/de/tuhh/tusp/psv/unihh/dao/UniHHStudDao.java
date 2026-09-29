package de.tuhh.tusp.psv.unihh.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.core.io.Resource;

import de.tuhh.tusp.psv.unihh.pojo.UniHHStud;

public interface UniHHStudDao {

	/**
	 * Liest die Studierendendaten aus einer Datei
	 *
	 * @param csv
	 * @param delimiter
	 * @return Die Studierendendaten
	 * @throws FileNotFoundException
	 *             Wenn Daitei nicht existiert
	 * @throws IOException
	 *             Wenn Datei nicht lesbar ist
	 */
	List<UniHHStud> readStudData(Resource csv, Character delimiter, Charset charset)	throws FileNotFoundException,
																						IOException;
}
