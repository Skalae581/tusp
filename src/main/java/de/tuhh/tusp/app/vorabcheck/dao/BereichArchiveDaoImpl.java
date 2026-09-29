package de.tuhh.tusp.app.vorabcheck.dao;

import static org.jooq.generated.Tables.VORABCHECK_BEREICH_ARCHIV;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.BereichArchive;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class BereichArchiveDaoImpl extends BaseDaoImpl<BereichArchive> implements BereichArchiveDao {

	/**
	 * Super-Konstruktor
	 */
	public BereichArchiveDaoImpl() {
		super(BereichArchive.class, VORABCHECK_BEREICH_ARCHIV, VORABCHECK_BEREICH_ARCHIV.ID, true);
	}

}
