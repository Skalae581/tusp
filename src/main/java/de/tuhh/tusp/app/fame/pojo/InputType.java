package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import lombok.Data;

/**
 * Liste der verschiedenen InputTypen.
 *
 * @author rztjm
 *
 */
@Data
public class InputType implements Serializable {

	private static final long serialVersionUID = -2347612212276713170L;
	Long id;

	@NotNull
	@Size(max = 255)
	String name;

	@NotNull
	InputTypeEnum uniqName;

	@Size(max = 255)
	String kommentar;
}
