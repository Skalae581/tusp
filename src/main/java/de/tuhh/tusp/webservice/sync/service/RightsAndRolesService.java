package de.tuhh.tusp.webservice.sync.service;

import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;

public interface RightsAndRolesService extends WebserviceSync {

	/**
	 * Holt eine Rolle anhand der RoleID
	 *
	 * @param roleId
	 * @return die Rolle oder null
	 */
	RightsAndRoles fetchByRoleId(Long roleId);

	/**
	 * Holt eine Rolle anhand des Uniquename
	 *
	 * @param uniquename
	 * @return die Rolle oder null
	 */
	RightsAndRoles fetchByUniquename(String uniquename);
}
