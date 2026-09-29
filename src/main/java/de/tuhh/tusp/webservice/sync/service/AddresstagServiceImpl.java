package de.tuhh.tusp.webservice.sync.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.AddresstagDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Addresstag;

@Component
public class AddresstagServiceImpl extends AbstractHioSyncImpl implements AddresstagService {

	@Autowired
	protected AddresstagDao dao;

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Addresstag();
	}

	@Override
	protected String getValueClass() {

		return "Addresstags";
	}

	@Override
	public int orderNum() {

		return 5;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

	}

}
