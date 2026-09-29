package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.PERSONSERVICE_PERSON;

import java.time.LocalDate;
import java.util.Date;

import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.pojo.Person;

@Component
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao {

	/**
	 * Instanziert das Dao
	 */
	public PersonDaoImpl() {

		super(Person.class, PERSONSERVICE_PERSON, PERSONSERVICE_PERSON.ID, false);
	}

	@Override
	public Date getMaxUpdated() {

		LocalDate ret =
				context.select(DSL.max(PERSONSERVICE_PERSON.UPDATED_AT)).from(PERSONSERVICE_PERSON).fetchAny().value1();

		if (ret == null) {
			return null;
		}

		return CalendarUtils.toDate(ret);
	}

}
