package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import lombok.Data;

/**
 * Bildet die Zuordnung einer Rolle zu einem Eingabefeld in FaME ab.
 *
 * @author rztjm
 *
 */
@Data
public class InputBerechtigung implements Serializable {

	private static final long serialVersionUID = -2499083401249734585L;
	Long id;

	@NotNull
	Long inputId;

	@NotNull
	Long rolleId;

	@NotNull
	Long statusId;

	/**
	 * {@link Rechte}
	 */
	@NotNull
	InputRechteEnum recht;

	String kommentar;
}
