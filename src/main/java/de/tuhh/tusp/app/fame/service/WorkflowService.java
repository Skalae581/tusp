package de.tuhh.tusp.app.fame.service;

import java.io.IOException;

import de.tuhh.tusp.app.fame.pojo.Workflow;
import de.tuhh.tusp.utils.CrudService;

public interface WorkflowService extends CrudService<Workflow> {

	/**
	 * Löscht den Workflow und alle Inputs und Status die dazugehören.
	 */
	@Override
	int delete(Long id);

	void deserialize(byte[] data) throws IOException, ClassNotFoundException;

	/**
	 * Erstellt den Workflow und den Status "ALL" und den Input "ALL"
	 */
	@Override
	Long insert(Workflow object);

	byte[] serialize(Long workflowId) throws IOException;
}
