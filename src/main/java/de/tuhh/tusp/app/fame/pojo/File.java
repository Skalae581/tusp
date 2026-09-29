package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author rztjm
 *
 */
@Data
public class File implements Serializable {

	private static final long serialVersionUID = -6401692064703141443L;

	private transient Long id;

	private Long valueId;
	private String mimeType;
	private byte[] content;
}
