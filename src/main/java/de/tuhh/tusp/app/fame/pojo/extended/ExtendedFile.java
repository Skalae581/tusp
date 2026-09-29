package de.tuhh.tusp.app.fame.pojo.extended;

import de.tuhh.tusp.app.fame.pojo.File;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ExtendedFile extends File {

	private static final long serialVersionUID = 8314356674467867515L;

	private String filename;
	private Long vorgangId;

	@Override
	public boolean equals(Object o) {

		if (o instanceof ExtendedFile) {
			ExtendedFile other = (ExtendedFile) o;
			return super.equals(other) && filename.equals(other.getFilename())
					&& vorgangId.equals(other.getVorgangId());
		}
		else if (o instanceof File) {
			return super.equals(o);
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {

		int hash = super.hashCode();

		if (filename != null) {
			hash += filename.hashCode();
		}

		if (vorgangId != null) {
			hash += vorgangId.hashCode();
		}

		return hash;
	}
}
