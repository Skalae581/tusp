package de.tuhh.tusp.global.pojo;

import lombok.Data;

@Data
public class KnownHost {

	private Long id;
	private String hostname;
	private String hostKey;
	private boolean valid;

	public boolean getValid() {

		return valid;
	}
}
