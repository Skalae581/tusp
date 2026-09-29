package de.tuhh.tusp.stu.tuhhstgs.dao;

import static org.jooq.generated.tables.StgProcessedFile.STG_PROCESSED_FILE;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.tuhhstgs.pojo.StgProcessedFile;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class StgProcesedFileDaoImpl extends BaseDaoImpl<StgProcessedFile> implements StgProcessedFileDao {

	/**
	 * erstellt eine Instanz
	 */
	public StgProcesedFileDaoImpl() {
		super(StgProcessedFile.class, STG_PROCESSED_FILE, STG_PROCESSED_FILE.ID);
	}

	@Override
	public StgProcessedFile fetchByInName(String name) {

		return context.select().from(table).where(STG_PROCESSED_FILE.IN_NAME.eq(name))
				.fetchAnyInto(this.typeParameterClass);
	}
}
