package de.tuhh.tusp.global.service;

import java.util.List;

import de.tuhh.tusp.global.pojo.Identity;
import de.tuhh.tusp.utils.CrudService;

public interface IdentityService extends CrudService<Identity> {

	/**
	 * Liefert die gültigen Identity
	 *
	 * @return Liste der Identity
	 */
	List<Identity> findValid();
}
