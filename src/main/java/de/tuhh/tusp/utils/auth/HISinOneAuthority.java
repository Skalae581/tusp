package de.tuhh.tusp.utils.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import de.his.ws.rightsandrolesservice.RoleparametersType;
import lombok.Data;

public class HISinOneAuthority implements GrantedAuthority {

	/**
	 * Dieser innere Typ ist nötig, da die HisRollenParameter nicht Serialisiert
	 * werdne können. Würde man diese nicht verwenden, könnte die Session vom Tomcat
	 * nicht mehr serialisiert und gespeichert werden.
	 *
	 * @author rztjm
	 *
	 */
	@Data
	class RoleParameter implements Serializable {

		private static final long serialVersionUID = -3574267944668346985L;
		private Long roleId;
		private Long id;
		private String parameter;
	}

	private static final long serialVersionUID = 6540586217359596265L;

	private String roleName;
	private Long roleId;
	private List<RoleParameter> parameters;

	/**
	 * Erstellt eine HISinOneAuthority
	 *
	 * @param id
	 * @param name
	 * @param roleparameters
	 */
	public HISinOneAuthority(Long id, String name, RoleparametersType roleparameters) {

		roleId = id;
		roleName = name.toUpperCase().replace(' ', '_');
		parameters = new ArrayList<>();

		for (de.his.ws.rightsandrolesservice.RoleParameter role : roleparameters.getRoleParameter()) {
			RoleParameter roleParam = new RoleParameter();
			roleParam.setRoleId(role.getRoleId());
			roleParam.setId(role.getId());
			roleParam.setParameter(role.getParametervalue());
			parameters.add(roleParam);
		}
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}

		HISinOneAuthority other = (HISinOneAuthority) o;

		return getAuthority().equals(other.getAuthority()) && parameters.equals(other.parameters) && this.roleId != null
				&& this.roleId.equals(other.roleId);
	}

	/**
	 * Teilt die Rollenparameter an Komata auf und legt diese als einzelnen
	 * AuthorityStrings ab:
	 *
	 * Rolle XY mit Parameter "a,b,c"
	 *
	 * wird zu ROLE_XY(a), ROLE_XY(b), ROLE_XY(c)
	 *
	 * @return Liste der Rollen mit Parametern
	 */
	public List<String> getAtomarAuthorities() {

		List<String> authorities = new ArrayList<>();
		if (parameters.isEmpty()) {
			authorities.add(getAuthority());
			return authorities;
		}
		for (RoleParameter param : parameters) {
			String[] paramParts = param.parameter.split(",");
			for (String part : paramParts) {
				authorities.add(getAuthority() + "(" + part + ")");
			}
		}
		return authorities;
	}

	/**
	 * Gibt den Namen der Rolle zurück mit dem für spring nötigen Prefix "ROLE_"
	 */
	@Override
	public String getAuthority() {

		return "ROLE_" + roleName;
	}

	/**
	 * gibt die Rolle mit Parametern zurück
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder(getAuthority());
		if (!parameters.isEmpty()) {
			sb.append("(");
			for (RoleParameter param : parameters) {
				sb.append(param.parameter).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");
		}
		return sb.toString();
	}
}
