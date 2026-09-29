package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.*;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.PersonRoles;

@Component
public class PersonRolesDaoImpl extends BaseDaoImpl<PersonRoles> implements PersonRolesDao {

	/**
	 * Instanziert das Dao
	 */
	public PersonRolesDaoImpl() {
		super(PersonRoles.class, RIGHTSANDROLESSERVICE_ROLESFORPERSON, RIGHTSANDROLESSERVICE_ROLESFORPERSON.ID, false);
	}

}
