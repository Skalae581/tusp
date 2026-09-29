package de.tuhh.tusp.stu.gdm.dao;

import static org.jooq.generated.tables.GdmProcessedFile.*;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.gdm.pojo.ProcessedFile;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class ProcessedFileDaoImpl extends BaseDaoImpl<ProcessedFile> implements ProcessedFileDao {

	/**
	 * erstellt eine Instanz
	 */
	public ProcessedFileDaoImpl() {
		super(ProcessedFile.class, GDM_PROCESSED_FILE, GDM_PROCESSED_FILE.ID);
	}

	@Override
	public ProcessedFile fetchByInName(String name) {

		return context	.select().from(table).where(GDM_PROCESSED_FILE.IN_NAME.eq(name))
						.fetchAnyInto(this.typeParameterClass);
	}

}
