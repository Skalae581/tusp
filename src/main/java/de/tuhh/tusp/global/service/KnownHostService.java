package de.tuhh.tusp.global.service;

import java.util.List;

import de.tuhh.tusp.global.pojo.KnownHost;
import de.tuhh.tusp.utils.CrudService;

public interface KnownHostService extends CrudService<KnownHost> {

	/**
	 * Liefert die gültigen KnownHosts
	 * 
	 * @return Liste der KnownHosts
	 */
	List<KnownHost> findValid();
}
