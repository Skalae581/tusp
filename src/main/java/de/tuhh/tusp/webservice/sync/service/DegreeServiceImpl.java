package de.tuhh.tusp.webservice.sync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.DegreeDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Degree;

@Component
public class DegreeServiceImpl extends AbstractHioSyncImpl implements DegreeService {

	private static final int ORDER_NUM = 5;

	@Autowired
	protected DegreeDao dao;

	@Override
	public List<Degree> findAll() {

		return dao.findAll();
	}

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Degree();
	}

	@Override
	protected String getValueClass() {

		return "Degree";
	}

	@Override
	public int orderNum() {

		return ORDER_NUM;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		de.his.ws.keyvalueservice.Degree hisDegree = (de.his.ws.keyvalueservice.Degree) de;
		Degree tuspDegree = (Degree) pojo;

		tuspDegree.setAstatBund(hisDegree.getAstatBund());
		tuspDegree.setAstatLand(hisDegree.getAstatLand());
		tuspDegree.setValidFrom(CalendarUtils.toDate(de.getValidFrom()));
		tuspDegree.setValidTo(CalendarUtils.toDate(de.getValidTo()));
	}

}
