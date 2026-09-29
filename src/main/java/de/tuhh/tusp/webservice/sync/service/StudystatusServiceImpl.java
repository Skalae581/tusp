package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.StudystatusValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.StudystatusDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Studystatus;

@Component
public class StudystatusServiceImpl extends AbstractHioSyncImpl implements StudystatusService {

	@Autowired
	protected StudystatusDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Studystatus();
	}

	@Override
	protected String getValueClass() {

		return "StudystatusValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		Studystatus d = (Studystatus) pojo;
		StudystatusValue degree = (StudystatusValue) de;
		d.setAstat(de.getAstat());
		d.setPriority(degree.getPriority() == null ? null : degree.getPriority().longValue());
	}

}
