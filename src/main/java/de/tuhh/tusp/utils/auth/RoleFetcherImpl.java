package de.tuhh.tusp.utils.auth;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import de.his.ws.accountservice.AccountInfo;
import de.his.ws.rightsandrolesservice.Orgrole;
import de.his.ws.rightsandrolesservice.RoleparametersType;
import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;
import de.tuhh.tusp.webservice.wsoutput.dao.HisRoleHelperDao;

@Component
public class RoleFetcherImpl implements RoleFetcher {

	private static Logger logger = LogManager.getLogger(RoleFetcherImpl.class);

	@Autowired
	private RightsAndRolesDao rightsAndRolesDao;

	@Autowired
	private HisAccountHelperDao hisAccountDao;

	@Autowired
	private HisRoleHelperDao hisRolesDao;

	@Autowired
	private ConfigService config;

	@Value("${dialect}")
	private String dialect;

	@Override
	public Collection<GrantedAuthority> getAuthoritiersByUsername(String username, AuthTypes authType)
			throws ServiceFault_Exception {

		Collection<GrantedAuthority> authorities = new HashSet<>();

		if (username == null || username.isEmpty()) {
			return authorities;
		}

		// Komischer Authentifizierungstyp bekommt keine Rollen
		if (authType.equals(AuthTypes.NULL)) {
			return authorities;
		}

		// Nich-Shibboleth-Nutzer bekommen extra Rollen
		if (authType.equals(AuthTypes.HIO)) {
			authorities.add(new LocalAuthority());
			return authorities;
		}

		// im Testsystem muss der Benutzername der benötigten Rolle entsprechen
		if ("h2".equals(dialect)) {
			// TODO Datenbankdialekt sollte nicht der Switch fürs Testsystem sein
			authorities.add(new HISinOneAuthority(0L, username, new RoleparametersType()));
			return authorities;
		}

		// HISinOne personId laden
		AccountInfo account = hisAccountDao.fetchAccountByUsername(username);

		// Authorities aus Config laden
		List<Config> plus = config.findByKeyPrefix(ConfigService.ROLES_KEY + username);
		for (Config c : plus) {
			authorities.add(new LocalAuthority(c.getValue()));
		}

		// Benutzer nicht gefunden, bekommt gast rolle
		if (account == null) {
			authorities.add(new GuestAuthority());
			return authorities;
		}

		// Benutzer bekomt hio Rollen
		else {
			for (Orgrole role : hisRolesDao.findValidRolesForPerson(account.getPersonId())) {

				String roleName = "HISinOne-Role-" + role.getRoleId();
				try {
					// Name aus Cache holen
					RightsAndRoles rar = rightsAndRolesDao.fetchById(role.getRoleId());
					roleName = rar.getUniquename();
				} catch (NoSuchElementException e) {
					logger.warn("Konnte HIS-Rolle nicht laden", e);
				}
				authorities.add(new HISinOneAuthority(role.getRoleId(), roleName, role.getRoleparameters()));
			}
			return authorities;
		}
	}
}
