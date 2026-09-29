package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.pojo.Log;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedLog;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {

	/**
	 * Construct
	 */
	public LogDaoImpl() {
		super(Log.class, FAME_LOG, FAME_LOG.ID);
	}

	@Override
	public List<ExtendedLog> findByVorgang(Long vorgangId) {

		return context	.select(table.fields()).select(FAME_INPUT.NAME.as("input_name"))

						.from(table)

						.leftJoin(FAME_VALUE).on(FAME_VALUE.ID.eq(FAME_LOG.VALUE_ID))

						.leftJoin(FAME_INPUT).on(FAME_VALUE.INPUT_ID.eq(FAME_INPUT.ID))

						.where(FAME_LOG.VORGANG_ID.eq(vorgangId))

						.orderBy(FAME_LOG.CHANGE_TIME.desc())

						.fetchInto(ExtendedLog.class);
	}
}
