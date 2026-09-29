package de.tuhh.tusp.stu.gdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.stu.gdm.dao.HistoryDao;
import de.tuhh.tusp.stu.gdm.pojo.History;

@Component
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryDao dao;

	@Override
	public List<History> findHistoryByFilename(String filename) {

		return dao.findByFilename(filename);
	}

}
