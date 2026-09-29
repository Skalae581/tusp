package de.tuhh.tusp.stu.gdm.dao;

import static org.jooq.generated.tables.GdmHistory.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.gdm.pojo.History;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class HistoryDaoImpl extends BaseDaoImpl<History> implements HistoryDao {

	/**
	 * erstellt eine Instanz
	 */
	public HistoryDaoImpl() {
		super(History.class, GDM_HISTORY, GDM_HISTORY.ID);
	}

	@Override
	public List<History> findByFilename(String name) {

		return context	.select().from(table).where(GDM_HISTORY.FILENAME.eq(name)).orderBy(GDM_HISTORY.ZEIT.desc())
						.fetchInto(History.class);
	}

}
