package de.tuhh.tusp.psv.impma.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.SearchedRole;
import de.his.ws.rightsandrolesservice.SearchedRolesType;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;

@Component
public class MitarbeiterStudentFilterDaoImpl implements MitarbeiterUndStudentFilterDao {

	private final static String MITARBEITER_ROLLE = "Employee_tuhh";
	private final static String STUDENT_ROLLE = "student";

	private static Logger logger = LogManager.getLogger(MitarbeiterStudentFilterDaoImpl.class);

	@Autowired
	private HisRoleHelperDao roleHelperDao;
	@Autowired
	private RightsAndRolesDao rightsAndRolesDao;

	@Override
	public boolean filterEmployeeAndStudent(Long id) {

		List<Orgrole> roles = null;

		try {
			roles = roleHelperDao.getAllRolesForPerson(id);

			if (null != roles) {

				for (Orgrole role : roles) {

					if (role != null) {

						SearchedRolesType roleType = roleHelperDao.readRoleById(role.getRoleId() + "");
						if (null != roleType) {

							if (roleType.getSearchedRole().size() >= 1) {

								for (SearchedRole searchedRole : roleType.getSearchedRole()) {

									String roleUname = "";

									if (null != searchedRole) {

										roleUname = searchedRole.getUniquename();

										if (roleUname.equals(STUDENT_ROLLE)) {

											if (isRoleExpired(id, STUDENT_ROLLE)) {
												return false;
											}
											System.out.println(".............Rolle Student gefunden.............");
											return true;
										} else {
											continue;
										}
									}
								}
							}

							// keine elemente in der liste

						}
					} else {
						continue;
					}
				}

			} else {
				logger.debug("Keine Rollen Gefunden!");
			}
		} catch (Exception e) {
			logger.debug("");
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * ist die angefragte Rolle abgelaufen?
	 *
	 * @param person
	 * @param roleName
	 * @return
	 */
	private boolean isRoleExpired(Long personId, String roleName) {

		List<Orgrole> roles = null;

		try {
			roles = roleHelperDao.getAllRolesForPerson(personId);

			if (roles.size() > 0) {

				logger.debug("Mehrere Rollen gefunden");

				List<Orgrole> roleNameList = new ArrayList<>();

				for (Orgrole role : roles) {

					if (role != null) {

						if (role.getRoleId() == rightsAndRolesDao.fetchByUniqename(roleName).getRoleId()) {
							roleNameList.add(role);
						}
					}
				}

				int invalid = 0;
				for (Orgrole rr : roleNameList) {

					XMLGregorianCalendar validTo = rr.getValidTo();
					if (CalendarUtils.isPast(CalendarUtils.toDate(validTo))) {

						logger.error("Die Rolle " + roleName + " ist abgelaufen!");
						invalid++;

					} else {
						invalid--;
					}

				}

				// rolle ist abgelaufen
				if (roleNameList.size() == invalid) {
					return true;
				}

			} else {
				return true; // keine Rollen vorhanden
			}
		} catch (Exception e) {
			logger.debug("Etwas ist falsch gelaufen!  NoSuchElementException | ServiceFault_Exception");
			e.printStackTrace();
		}

		return false;
	}
}
