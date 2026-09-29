package de.tuhh.tusp.app.fame.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import de.tuhh.tusp.app.fame.dao.FileDao;
import de.tuhh.tusp.app.fame.exception.SecurityException;
import de.tuhh.tusp.app.fame.pojo.File;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFile;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFileMitRecht;
import de.tuhh.tusp.utils.CrudService;

public interface FileService extends CrudService<File> {

	/**
	 * Loscht ein File und das dazugehörige Value-Obekt
	 * 
	 * @param file
	 * @throws SecurityException
	 *             wenn der Nutzer die Rechte nicht hat
	 */
	void delete(ExtendedFileMitRecht file) throws SecurityException;

	/**
	 * @see FileDao
	 * @param id
	 * @return Das File
	 */
	ExtendedFile fetchByValueId(Long id);

	/**
	 * {@link FileDao#fetchByValueId(Long, List)}
	 *
	 * @param valueId
	 * @param rollen
	 * @return Das File
	 */
	ExtendedFileMitRecht fetchByValueId(Long valueId, List<String> rollen);

	/**
	 * Speichert eine Ressource zu einem Vorgang ab. Wenn nötig werden fame_file und fame_value Einträge generiert
	 *
	 * @param vorgangId
	 * @param inputId
	 * @param file
	 * @throws IOException
	 *             Wenn die Datei nicht gelesen werden kann
	 */
	void saveFile(Long vorgangId, Long inputId, MultipartFile file) throws IOException;
}
