package de.tuhh.tusp.webservice.wsoutput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.webservice.wsoutput.dao.TuHHStgDao;

@Component
public class TuHHStgServiceImpl implements TuHHStgService {

	@Autowired
	private TuHHStgDao tuhhDao;

	@Override
	public String getStgByAstatUni(String astatUni) {

		return tuhhDao.getStgByAstatUni(astatUni);
	}

}
