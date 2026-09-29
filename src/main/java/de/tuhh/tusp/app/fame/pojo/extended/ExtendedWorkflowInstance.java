package de.tuhh.tusp.app.fame.pojo.extended;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.tuhh.tusp.app.fame.dao.StatusDao.ExtendedStatus;
import lombok.Data;

@Data
public class ExtendedWorkflowInstance implements Serializable {

	private static final long serialVersionUID = -2158716247333241964L;
	Long id;
	Long workflowId;
	String name;
	Integer numVorgaenge;
	List<ExtendedStatus> statuses = new ArrayList<>();
}