package de.tuhh.tusp.webservice.sync.dao;

import java.util.NoSuchElementException;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.Gender;

@Component
public class GenderDaoImpl extends BaseDaoImpl<Gender> implements GenderDao {

	/**
	 * Instanziert das Dao
	 */
	public GenderDaoImpl() {

		super(Gender.class, Tables.VALUESERVICE_GENDER, Tables.VALUESERVICE_GENDER.ID, false);
	}

	@Override
	public Gender fetchByUniqename(String name) {

		Gender result = context	.select().from(table).where(Tables.VALUESERVICE_GENDER.UNIQUENAME.equal(name))
								.fetchAnyInto(typeParameterClass);
		if (result == null) {
			throw new NoSuchElementException("No object with name " + name + " found!");
		}
		return result;
	}

	@Override
	public Gender fetchUniqenameById(Long id) {

		return context	.select().from(table).where(Tables.VALUESERVICE_GENDER.ID.equal((int) (long) id)) // TODO cast
																											// entfernen
						.fetchAnyInto(typeParameterClass);

	}

}
