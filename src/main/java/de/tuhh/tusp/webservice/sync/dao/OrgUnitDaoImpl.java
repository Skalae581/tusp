package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.*;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;

@Component
public class OrgUnitDaoImpl extends BaseDaoImpl<OrgUnit> implements OrgUnitDao {

	/**
	 * Instanziert das Dao
	 */
	public OrgUnitDaoImpl() {
		super(OrgUnit.class, ORGUNITSERVICE_ORGUNITS, ORGUNITSERVICE_ORGUNITS.ID, false);
	}

	@Override
	public OrgUnit fetchByShorttextDe(String name) {

		OrgUnit result = context.select().from(table).where(ORGUNITSERVICE_ORGUNITS.SHORTTEXT_DE.equal(name))
								.fetchAnyInto(typeParameterClass);
		if (result == null) {
			throw new NoSuchElementException("No object with name " + name + " found!");
		}
		return result;
	}

	@Override
	public OrgUnit fetchByUniquename(String ouUniquename) {

		OrgUnit result = context.select().from(table).where(ORGUNITSERVICE_ORGUNITS.UNIQUENAME.equal(ouUniquename))
								.fetchAnyInto(typeParameterClass);
		if (result == null) {
			throw new NoSuchElementException("No object with name " + ouUniquename + " found!");
		}
		return result;
	}
}
