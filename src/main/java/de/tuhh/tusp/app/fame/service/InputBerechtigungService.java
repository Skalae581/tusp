package de.tuhh.tusp.app.fame.service;

import java.util.List;

import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;

public interface InputBerechtigungService {

	void delete(Long id);

	List<InputBerechtigung> findByWorkflow(Long id);

	Long insert(InputBerechtigung s);

	void update(InputBerechtigung s);

}
