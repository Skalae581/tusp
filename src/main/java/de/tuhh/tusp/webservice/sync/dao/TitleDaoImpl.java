package de.tuhh.tusp.webservice.sync.dao;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Title;

@Component
public class TitleDaoImpl extends BaseDaoImpl<Title> implements TitleDao {

	/**
	 * Instanziert das Dao
	 */
	public TitleDaoImpl() {
		super(Title.class, Tables.VALUESERVICE_TITLEVALUE, Tables.VALUESERVICE_TITLEVALUE.ID, false);
	}
}
