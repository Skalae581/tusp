package de.tuhh.tusp.td.utils;

import java.util.Map;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.td.dao.TdColumns;
import de.tuhh.tusp.td.pojo.Td;

public interface TdConverter {

	Map<TdColumns, String> tdToMap(Td td, JobLogger jobLogger);

}
