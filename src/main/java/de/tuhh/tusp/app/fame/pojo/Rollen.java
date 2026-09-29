package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import lombok.Data;

/**
 * Rollen, die in FaME vorkommen
 *
 * @author rztjm
 *
 */
@Data
public class Rollen implements Serializable {

	private static final long serialVersionUID = 5625711715062960386L;
	Long id;
	String name;
}
