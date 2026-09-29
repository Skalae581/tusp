package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.DistrictDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.District;

@Component
public class DistrictServiceImpl extends AbstractHioSyncImpl implements DistrictService {

	@Autowired
	protected DistrictDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new District();
	}

	@Override
	protected String getValueClass() {

		return "DistrictValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		District d = (District) pojo;

		d.setLid(de.getLid());
		d.setAstat(de.getAstat());
		d.setValidFrom(CalendarUtils.toDate(de.getValidFrom()));
		d.setValidTo(CalendarUtils.toDate(de.getValidTo()));
	}

}
