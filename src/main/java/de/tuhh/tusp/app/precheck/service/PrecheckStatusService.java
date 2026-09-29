package de.tuhh.tusp.app.precheck.service;

import java.util.List;

import de.tuhh.tusp.app.precheck.pojo.Status;

public interface PrecheckStatusService {

	void delete(Long id);

	Status fetchById(Long id);

	List<Status> findAll();

	void insert(Status status);

	void update(Status status);

}
