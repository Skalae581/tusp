package de.tuhh.tusp.webservice.wsoutput.service;

import static de.tuhh.tusp.webservice.wsoutput.ConfigKeys.DEFAULT_ROLE_VALIDITY;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.SetPersonToOrgunitWithRole;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.sync.service.OrgUnitService;
import de.tuhh.tusp.webservice.sync.service.RightsAndRolesService;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisPerson;
import de.tuhh.tusp.webservice.wsoutput.pojo.HisRole;

@Component
public class HisRolesServiceOutputImpl implements HisRolesServiceOutput {

	private static Logger logger = LogManager.getLogger(HisRolesServiceOutputImpl.class);

	@Autowired
	private OrgUnitService ouService;

	@Autowired
	private RightsAndRolesService rolesService;

	@Autowired
	private HisRoleHelperDao hisRoleHelperDao;

	@Autowired
	private ConfigService config;

	@Override
	public void updateOrCreateRolesForPerson(HisPerson user, long personId) throws ServiceFault {

		if (user == null || user.getRoles() == null) {
			return;
		}

		for (HisRole sollRole : user.getRoles()) {

			List<Orgrole> userRoles = hisRoleHelperDao.getAllRolesForPerson(personId);
			Orgrole bestehendeRole = hisRoleHelperDao.filterOrgrole(userRoles, sollRole.getRollenUniquename());

			if (bestehendeRole != null) {
				// Rollengültigkeit verlängern
				if (sollRole.getValidTo() != null && bestehendeRole.getValidTo().compare(sollRole.getValidTo()) < 0) {
					bestehendeRole.setValidTo(sollRole.getValidTo());
					hisRoleHelperDao.updateOrgRole(bestehendeRole);

					logger.info("Rolle verlängert [" + sollRole.getRollenUniquename() + "] für Benutzer "
							+ user.toShortString());
				}
			} else {

				OrgUnit ou = ouService.fetchByUniquename(sollRole.getOuUniquename());
				RightsAndRoles rar = rolesService.fetchByUniquename(sollRole.getRollenUniquename());

				if (ou == null) {
					throw new IllegalStateException("Keine OU gefunden für " + sollRole.getOuUniquename());
				}
				if (rar == null) {
					throw new IllegalStateException("Keine Rolle gefunden für " + sollRole.getRollenUniquename());
				}

				// Rolle anlegen
				SetPersonToOrgunitWithRole parameters = new SetPersonToOrgunitWithRole();
				parameters.setOrgunitLid(ou.getLid());
				parameters.setPersonId(personId);
				parameters.setOrgroleId(rar.getRoleId());
				parameters.setValidFrom(sollRole.getValidFrom() != null ? sollRole.getValidFrom()
						: new CalendarUtils().getNowAsXMLGregorianCalendar());
				parameters.setValidTo(sollRole.getValidTo() != null ? sollRole.getValidTo()
						: CalendarUtils.getXMLGregorianCalendar(GregorianCalendar.DATE,
								config.get(DEFAULT_ROLE_VALIDITY, 30, true)));

				logger.info(
						"Rolle erstellt [" + sollRole.getRollenUniquename() + "] für Benutzer " + user.toShortString());

				hisRoleHelperDao.setPersonToOrgunitWithRole(parameters);
			}
		}

	}

}
