package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.StudentstatusValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.StudentstatusDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Studentstatus;

@Component
public class StudentstatusServiceImpl extends AbstractHioSyncImpl implements StudentstatusService {

	@Autowired
	protected StudentstatusDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Studentstatus();
	}

	@Override
	protected String getValueClass() {

		return "StudentstatusValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		StudentstatusValue degree = (StudentstatusValue) de;
		Studentstatus d = (Studentstatus) pojo;
		d.setAstat(degree.getAstat());
		d.setPriority(degree.getPriority() == null ? null : degree.getPriority().longValue());
		d.setValidFrom(CalendarUtils.toDate(de.getValidFrom()));
		d.setValidTo(CalendarUtils.toDate(de.getValidTo()));
	}

}
