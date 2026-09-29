package de.tuhh.tusp.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.auth.HISinOneAuthority;

@Component
public class AuthorityUtilsImpl implements AuthorityUtils {

	/**
	 * Wandelt {@link GrantedAuthority} in Rollen um
	 *
	 * @param authorities
	 * @return Liste der Rollen
	 */
	private static List<String> convertAuthoritiesToRoles(Collection<? extends GrantedAuthority> authorities) {

		List<String> roles = new ArrayList<>();
		for (java.util.Iterator<? extends GrantedAuthority> iterator = authorities.iterator(); iterator.hasNext();) {
			GrantedAuthority author = iterator.next();
			if (author instanceof HISinOneAuthority) {
				roles.addAll(((HISinOneAuthority) author).getAtomarAuthorities());
			} else {
				roles.add(author.getAuthority());
			}

		}

		return roles;
	}

	@Autowired
	private ConfigService config;

	@Override
	public List<String> getAdminRoles() {

		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		return roles;
	}

	@Override
	public String getCurrentUserDisplayName() {

		String username = getCurrentUserName();
		return config.get("de.tuhh.tusp.userprefs." + username + ".displayName", username, true);
	}

	@Override
	public String getCurrentUserName() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return "Systemuser";
		}

		return authentication.getName();
	}

	@Override
	public List<String> getRoles() {

		return convertAuthoritiesToRoles(getUserAuthorities());
	}

	@Override
	public Collection<? extends GrantedAuthority> getUserAuthorities() {

		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
}
