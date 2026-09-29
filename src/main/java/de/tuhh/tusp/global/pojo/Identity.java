package de.tuhh.tusp.global.pojo;

import lombok.Data;

@Data
public class Identity {

	private Long id;
	private String name;
	private String privateKey;
	private String publicKey;
	private boolean valid;

	public boolean getValid() {

		return valid;
	}
}
