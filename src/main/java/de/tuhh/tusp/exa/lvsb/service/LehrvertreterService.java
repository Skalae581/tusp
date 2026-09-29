package de.tuhh.tusp.exa.lvsb.service;

import java.util.List;

import de.tuhh.tusp.exa.lvsb.pojo.Lehrvertreter;
import de.tuhh.tusp.utils.CrudService;

public interface LehrvertreterService extends CrudService<Lehrvertreter> {

	List<Lehrvertreter> findAllRequests();

}
