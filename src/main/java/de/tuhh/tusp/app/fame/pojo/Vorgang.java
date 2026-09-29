package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import de.tuhh.tusp.app.fame.service.VorgangService;
import de.tuhh.tusp.app.fame.service.VorgangServiceImpl;
import lombok.Data;

/**
 * Ein Vorgang entspricht einer Bewerbung in FaME. Er ist immer einem Workflow zugeordnet und hat einen Status.
 *
 * @author rztjm
 *
 */
@Data
public class Vorgang implements Serializable {

	private static final long serialVersionUID = -3543621747742498071L;
	Long id;

	@NotNull
	Long workflowInstanceId;

	@NotNull
	Long statusId;
	/**
	 * Gibt die Klasse an, aus der Vorgänge importiert werden sollen.
	 */
	String connectorKey;

	/**
	 * Infos zur Sperre sind in {@link VorgangService} und {@link VorgangServiceImpl}
	 */
	Date gesperrtDatum;

	/**
	 * Infos zur Sperre sind in {@link VorgangService} und {@link VorgangServiceImpl}
	 */
	String gesperrtDurch;

	String warn;
}
