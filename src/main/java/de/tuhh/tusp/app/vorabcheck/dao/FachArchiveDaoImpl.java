package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.VORABCHECK_FACH_ARCHIV;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.FachArchive;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class FachArchiveDaoImpl extends BaseDaoImpl<FachArchive> implements FachArchiveDao {

	/**
	 * Super-Konstruktor
	 */
	public FachArchiveDaoImpl() {
		super(FachArchive.class, VORABCHECK_FACH_ARCHIV, VORABCHECK_FACH_ARCHIV.ID, true);
	}

}
