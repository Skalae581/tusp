package de.tuhh.tusp.global.pojo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * @author rztjj
 *
 */
@Data
public class User {

	private long id;
	private String benutzername;

	@Length(min = 2, max = 255)
	private String name;

	@Email
	private String mailadresse;
	private String passwort;
	private boolean enabled;

}
