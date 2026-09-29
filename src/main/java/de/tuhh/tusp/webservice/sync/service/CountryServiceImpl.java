package de.tuhh.tusp.webservice.sync.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.his.ws.keyvalueservice.CountryValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.CountryDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Country;

@Component
public class CountryServiceImpl extends AbstractHioSyncImpl implements CountryService {

	@Autowired
	protected CountryDao dao;

	@Override
	public List<Country> findAll() {

		return dao.findAll();
	}

	@Override
	public List<Country> findAllAscending() {

		return dao.findAllAscending();
	}

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Country();
	}

	@Override
	protected String getValueClass() {

		return "CountryValue";
	}

	@Override
	public int orderNum() {

		return 2;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

		CountryValue country = (CountryValue) de;
		Country c = (Country) pojo;

		c.setLid(country.getLid());
		c.setIso3166_1_alpha(country.getIso31661Alpha2());
		c.setEuMember(country.getEuMember() == null ? null : country.getEuMember().intValue());
		c.setEwrMember(country.getEwrMember() == null ? null : country.getEwrMember().intValue());
	}

}
