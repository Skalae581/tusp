package de.tuhh.tusp.app.fame.pojo.extended;

import de.tuhh.tusp.app.fame.pojo.Log;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ExtendedLog extends Log {

	private static final long serialVersionUID = 8314356674463867515L;

	private String inputName;
}
