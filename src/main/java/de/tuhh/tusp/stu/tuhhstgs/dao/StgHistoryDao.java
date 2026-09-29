package de.tuhh.tusp.stu.tuhhstgs.dao;

import java.util.List;

import de.tuhh.tusp.stu.tuhhstgs.pojo.StgHistory;
import de.tuhh.tusp.utils.BaseDao;

public interface StgHistoryDao extends BaseDao<StgHistory> {

	List<StgHistory> findByFilename(String name);

}
