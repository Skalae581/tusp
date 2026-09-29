package de.tuhh.tusp.psv.unihh.dao;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.psv.unihh.pojo.GewProcessedFile;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class GewProcessedFileDaoImpl extends BaseDaoImpl<GewProcessedFile> implements GewProcessedFileDao {

	/**
	 * erstellt eine Instanz
	 */
	public GewProcessedFileDaoImpl() {
		super(GewProcessedFile.class, org.jooq.generated.tables.GewProcessedFile.GEW_PROCESSED_FILE,
				org.jooq.generated.tables.GewProcessedFile.GEW_PROCESSED_FILE.ID);
	}

	@Override
	public GewProcessedFile fetchByInName(String name) {

		return context.select().from(table)
				.where(org.jooq.generated.tables.GewProcessedFile.GEW_PROCESSED_FILE.IN_NAME.eq(name))
				.fetchAnyInto(this.typeParameterClass);
	}

}
