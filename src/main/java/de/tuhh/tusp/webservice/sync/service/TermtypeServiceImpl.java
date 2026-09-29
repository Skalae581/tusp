package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.TermTypeValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.TermtypeDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Termtype;

@Component
public class TermtypeServiceImpl extends AbstractHioSyncImpl implements TermtypeService {

	@Autowired
	protected TermtypeDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Termtype();
	}

	@Override
	protected String getValueClass() {

		return "TermTypeValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		Termtype d = (Termtype) pojo;
		TermTypeValue tt = (TermTypeValue) de;
		d.setTermCategory(tt.getTermCategory() == null ? null : tt.getTermCategory().longValue());
		d.setTermNumber(tt.getTermNumber() == null ? null : tt.getTermNumber().longValue());
	}

}
