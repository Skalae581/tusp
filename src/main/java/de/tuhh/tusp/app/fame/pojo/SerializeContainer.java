package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;
import java.util.List;

public class SerializeContainer implements Serializable {

	private static final long serialVersionUID = -1583089676412209190L;
	public Workflow w;
	public List<Rollen> rs;
	public List<WorkflowInstance> wis;
	public List<Input> is;
	public List<Status> ss;
	public List<StatusBerechtigung> sbs;
	public List<InputBerechtigung> ibs;
	public List<InputType> its;
}
