package de.tuhh.tusp.webservice.sync.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.keyvalueservice.AbstractKeyValue;
import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.dao.GenderDao;
import de.tuhh.tusp.webservice.sync.pojo.AbstractHioPojo;
import de.tuhh.tusp.webservice.sync.pojo.Gender;

@Component
public class GenderServiceImpl extends AbstractHioSyncImpl implements GenderService {

	@Autowired
	protected GenderDao dao;

	@Override
	public Gender fetchByString(String string) throws NoSuchElementException {

		if (string == null) {
			return null;
		}

		switch (string) {
			case "w":
			case "W":
			case "f":
			case "F":
			case "weiblich":
				return dao.fetchByUniqename("W");

			case "m":
			case "M":
			case "männlich":
				return dao.fetchByUniqename("M");

			case "u":
			case "U":
				return dao.fetchByUniqename("U");

			case "d":
			case "D":
				return dao.fetchByUniqename("D");

			default:
				throw new NoSuchElementException("Für " + string + " konnte kein Gender ermittelt werden.");

		}

	}

	@Override
	public List<Gender> findAll() {

		return dao.findAll();
	}

	@Override
	protected BaseDao<?> getDao() {

		return dao;
	}

	@Override
	protected AbstractHioPojo getEmptyObject() {

		return new Gender();
	}

	@Override
	protected String getValueClass() {

		return "GenderValue";
	}

	@Override
	public int orderNum() {

		return 1;
	}

	@Override
	protected void setAdditionalValues(AbstractHioPojo pojo, AbstractKeyValue de, AbstractKeyValue en) {

	}

}
