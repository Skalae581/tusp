package de.tuhh.tusp.utils;

import org.jooq.DSLContext;

public interface DatabaseUpdater {

	void buildTestTables(DSLContext context) throws Exception;

	void initDb(DSLContext context) throws Exception;

}
