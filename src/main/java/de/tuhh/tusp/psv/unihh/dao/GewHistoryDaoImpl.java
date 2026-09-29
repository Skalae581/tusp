package de.tuhh.tusp.psv.unihh.dao;

import static org.jooq.generated.tables.GewHistory.GEW_HISTORY;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.psv.unihh.pojo.GewHistory;
import de.tuhh.tusp.stu.gdm.pojo.History;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class GewHistoryDaoImpl extends BaseDaoImpl<GewHistory> implements GewHistoryDao {

	/**
	 * erstellt eine Instanz
	 */
	public GewHistoryDaoImpl() {
		super(GewHistory.class, GEW_HISTORY, GEW_HISTORY.ID);
	}

	@Override
	public List<History> findByFilename(String name) {

		return context.select().from(table).where(GEW_HISTORY.FILENAME.eq(name)).orderBy(GEW_HISTORY.ZEIT.desc())
				.fetchInto(History.class);
	}

}
