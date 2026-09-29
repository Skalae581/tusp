package de.tuhh.tusp.lvp.service;

import java.util.Map;

import de.tuhh.tusp.lvp.dao.LvpColumns;
import de.tuhh.tusp.lvp.pojo.Lvp;

public interface LvpConverter {

	Map<LvpColumns, String> lvpToMap(Lvp lvp);

}
