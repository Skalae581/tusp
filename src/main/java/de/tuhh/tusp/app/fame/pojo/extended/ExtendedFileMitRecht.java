package de.tuhh.tusp.app.fame.pojo.extended;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ExtendedFileMitRecht extends ExtendedFile {

	private static final long serialVersionUID = 8314356374467867515L;

	private InputRechteEnum recht;
}