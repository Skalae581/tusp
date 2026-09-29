package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Speichert einen Eingabewert in FaME für ein Eingabefeld in einem Vorgang.
 *
 * @author rztjm
 *
 */
@Data
public class Value implements Serializable {

	private static final long serialVersionUID = 6910290995268375398L;
	Long id;

	@NotNull
	Long inputId;

	// @NotNull
	String eingabe;

	@NotNull
	Long vorgangId;

	@NotNull
	Date createTime;
}
