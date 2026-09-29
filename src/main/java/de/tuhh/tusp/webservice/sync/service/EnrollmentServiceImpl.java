package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.EnrollmentDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Enrollment;

@Component
public class EnrollmentServiceImpl extends AbstractHioSyncImpl implements EnrollmentService {

	@Autowired
	protected EnrollmentDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Enrollment();
	}

	@Override
	protected String getValueClass() {

		return "EnrollmentValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		Enrollment d = (Enrollment) pojo;
		d.setAstat(de.getAstat());

	}

}
