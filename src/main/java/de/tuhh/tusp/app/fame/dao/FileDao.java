package de.tuhh.tusp.app.fame.dao;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.File;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFile;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFileMitRecht;
import de.tuhh.tusp.utils.BaseDao;

public interface FileDao extends BaseDao<File> {

	/**
	 * Holt einen File-Eintrag aus der DB anhand der ID des zugehörigen Value-Eintrags
	 *
	 * @param valueId
	 * @return
	 */
	ExtendedFile fetchByValueId(Long valueId);

	/**
	 * Holt einen File-Eintrag aus der DB anhand der ID des zugehörigen Value-Eintrags
	 *
	 * @param valueId
	 * @return
	 */
	ExtendedFileMitRecht fetchByValueId(Long valueId, List<String> rollen);
}
