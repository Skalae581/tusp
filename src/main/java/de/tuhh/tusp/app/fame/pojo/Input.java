package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * Bildet ein Eingabefeld in FaME ab.
 *
 * @author rztjm
 *
 */
@Data
public class Input implements Serializable {

	private static final long serialVersionUID = -7469563958722706427L;
	Long id;

	@NotNull
	Long inputTypeId;

	@NotNull
	Long workflowId;

	@NotNull
	@Length(min = 3, max = 255)
	String name;
	String beschreibung;
	String options;

	Long tablePos;

	@NotNull
	Long position;

	@NotNull
	@Min(0)
	@Max(1)
	Integer pflicht;

	@NotNull
	@Min(0)
	@Max(1)
	Integer hidden;

	String regex;
	String connectorMapping;

	String kommentar;
}
