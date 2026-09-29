package de.tuhh.tusp.stu.tuhhstgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.tuhhstgs.dao.StgHistoryDao;
import de.tuhh.tusp.stu.tuhhstgs.pojo.StgHistory;

@Component
public class StgHistoryServiceImpl implements StgHistoryService {

	@Autowired
	private StgHistoryDao stgHistoryDao;

	@Override
	public List<StgHistory> findHistoryByFilename(String filename) {

		return stgHistoryDao.findByFilename(filename);
	}
}
