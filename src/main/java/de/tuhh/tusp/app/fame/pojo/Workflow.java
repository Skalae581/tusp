package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Workflows in FaME.
 *
 * @author rztjm
 *
 */
@Data
public class Workflow implements Serializable {

	private static final long serialVersionUID = -3802146385469857530L;
	Long id;

	@NotNull
	@Size(min = 3)
	String name;

	String connector;

	String inputValidationCode;

	String kommentar;
}
