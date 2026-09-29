package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 *
 * @author rztjm
 *
 */
@Data
public class Log implements Serializable {

	private static final long serialVersionUID = -6401692066703141443L;

	private Long id;
	private Long valueId;
	private Long vorgangId;
	private String subject;
	private String user;
	private Date changeTime;
}
