package de.tuhh.tusp.app.vorabcheck.dao;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.vorabcheck.pojo.Extra;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class ExtraDaoImpl extends BaseDaoImpl<Extra> implements ExtraDao {

	/**
	 * Super-Konstruktor
	 */
	public ExtraDaoImpl() {
		super(Extra.class, org.jooq.generated.Tables.VORABCHECK_EXTRA, org.jooq.generated.Tables.VORABCHECK_EXTRA.ID,
				true);
	}

}
