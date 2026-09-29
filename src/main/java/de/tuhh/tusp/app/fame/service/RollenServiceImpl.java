package de.tuhh.tusp.app.fame.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.RollenDao;
import de.tuhh.tusp.app.fame.pojo.Rollen;
import de.tuhh.tusp.global.dao.ConfigDao;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;

@Component
public class RollenServiceImpl implements RollenService {

	private static Logger logger = LogManager.getLogger(RollenServiceImpl.class);

	@Autowired
	private RollenDao dao;

	@Autowired
	private RightsAndRolesDao rolesDao;

	@Autowired
	private ConfigDao configDao;

	@Override
	public void aktualisiere() {

		Map<String, Rollen> existing = new HashMap<>();
		Map<String, Rollen> delete = new HashMap<>();
		for (Rollen rolle : dao.findAll()) {
			existing.put(rolle.getName(), rolle);
			delete.put(rolle.getName(), rolle);
		}

		// Rollen aus Config verarbeiten
		for (Config c : configDao.search("de.tuhh.tusp.app.fame.roles.%", "%")) {
			String name = "ROLE_" + c.getValue();

			// Klammern entfernen
			int pos = name.indexOf('(');
			if (pos > 0) {
				name = name.substring(0, pos);
			}

			if (!existing.containsKey(name)) {
				logger.debug("Erstelle FaME-Rolle " + name);
				Rollen newRolle = new Rollen();
				newRolle.setName(name);
				dao.insert(newRolle);
				existing.put(name, newRolle);
			}

			delete.remove(name);
		}

		// Rollen aus HIO verarbeiten
		for (RightsAndRoles role : rolesDao.findAll()) {

			String name = "ROLE_" + role.getUniquename().toUpperCase().replace(' ', '_');
			if (!existing.containsKey(name)) {
				logger.debug("Erstelle FaME-Rolle " + name);
				Rollen newRolle = new Rollen();
				newRolle.setName(name);
				dao.insert(newRolle);
				existing.put(name, newRolle);
			}

			delete.remove(name);
		}

		// Überflüssige Rollen löschen
		for (String key : delete.keySet()) {
			try {
				dao.delete(delete.get(key).getId());
				logger.debug("FaME-Rolle " + key + " gelöscht");
			} catch (Exception e) {
				logger.debug("FaME-Rolle " + key + " nicht gelöscht weil sie in Verwendung ist");
			}
		}
	}

	@Override
	public List<Rollen> findAll() {

		return dao.findAll();
	}

}
