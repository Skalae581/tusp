package de.tuhh.tusp.app.fame.service;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;
import de.tuhh.tusp.utils.CrudService;

public interface StatusBerechtigungService extends CrudService<StatusBerechtigung> {

	List<StatusBerechtigung> findByWorkflow(Long id);

}
