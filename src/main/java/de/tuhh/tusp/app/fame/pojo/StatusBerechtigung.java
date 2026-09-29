package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Workflow StatusBerechtigung regelt wer einen Vorgang aus welchem Status in welchen anderen Status überführen darf
 *
 * @author rztjm
 *
 */
@Data
public class StatusBerechtigung implements Serializable {

	private static final long serialVersionUID = -7664260833314011137L;
	Long id;

	@NotNull
	Long statusFromId;

	@NotNull
	Long rolleId;

	@NotNull
	Long statusToId;

}
