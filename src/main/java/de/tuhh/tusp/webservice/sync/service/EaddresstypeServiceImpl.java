package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.EAddresstypeValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.EaddresstypeDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Eaddresstype;

@Component
public class EaddresstypeServiceImpl extends AbstractHioSyncImpl implements EaddresstypeService {

	@Autowired
	protected EaddresstypeDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Eaddresstype();
	}

	@Override
	protected String getValueClass() {

		return "EAddresstypeValue";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		Eaddresstype eat = (Eaddresstype) pojo;
		eat.setAddresstype(((EAddresstypeValue) de).getAddresstype());
	}

}
