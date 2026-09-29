package de.tuhh.tusp.lvp.service;

import java.util.Map;

import de.tuhh.tusp.lvp.dao.TermineColumns;
import de.tuhh.tusp.lvp.pojo.Termine;

public interface TermineConverter {

	Map<TermineColumns, String> termineToMap(Termine termine);

}
