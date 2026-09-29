package de.tuhh.tusp.stu.tuhhstgs.dao;

import static org.jooq.generated.tables.StgHistory.STG_HISTORY;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.tuhhstgs.pojo.StgHistory;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class StgHistoryDaoImpl extends BaseDaoImpl<StgHistory> implements StgHistoryDao {

	public StgHistoryDaoImpl() {
		super(StgHistory.class, STG_HISTORY, STG_HISTORY.ID);
	}

	@Override
	public List<StgHistory> findByFilename(String name) {

		return context.select().from(table).where(STG_HISTORY.FILENAME.eq(name)).orderBy(STG_HISTORY.ZEIT.desc())
				.fetchInto(StgHistory.class);
	}

}
