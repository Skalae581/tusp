package de.tuhh.tusp.webservice.sync.dao;

import static org.jooq.generated.Tables.RIGHTSANDROLESSERVICE_ROLES;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;

@Component
public class RightsAndRolesDaoImpl extends BaseDaoImpl<RightsAndRoles> implements RightsAndRolesDao {

	/**
	 * Instanziert das Dao
	 */
	public RightsAndRolesDaoImpl() {

		super(RightsAndRoles.class, RIGHTSANDROLESSERVICE_ROLES, RIGHTSANDROLESSERVICE_ROLES.ROLE_ID, false);
	}

	@Override
	public RightsAndRoles fetchByUniqename(String name) {

		RightsAndRoles result = context	.select().from(table).where(RIGHTSANDROLESSERVICE_ROLES.UNIQUENAME.equal(name))
										.fetchAnyInto(typeParameterClass);
		if (result == null) {
			throw new NoSuchElementException("No object with name " + name + " found!");
		}
		return result;
	}

}
