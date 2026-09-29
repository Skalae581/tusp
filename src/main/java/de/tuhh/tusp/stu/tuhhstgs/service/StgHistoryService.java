package de.tuhh.tusp.stu.tuhhstgs.service;

import java.util.List;

import de.tuhh.tusp.stu.tuhhstgs.pojo.StgHistory;

public interface StgHistoryService {

	List<StgHistory> findHistoryByFilename(String filename);

}
