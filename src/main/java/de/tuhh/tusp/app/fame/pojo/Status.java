package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Status eines Vorgangs
 *
 * @author rztjm
 *
 */
@Data
public class Status implements Serializable {

	private static final long serialVersionUID = 7676778156286562436L;
	Long id;

	@NotNull
	@Size(max = 255)
	String name;

	@NotNull
	Long workflowId;

	@NotNull
	Long position;

	@NotNull
	@Min(0)
	@Max(1)
	Integer hidden;

	String kommentar;
}
