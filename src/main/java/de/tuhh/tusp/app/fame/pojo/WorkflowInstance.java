package de.tuhh.tusp.app.fame.pojo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * WorkflowsInstanz in FaME. Also z.B. im Masterzulassungsworkflow der Studiengang MEM
 *
 * @author rztjm
 *
 */
@Data
public class WorkflowInstance implements Serializable {

	private static final long serialVersionUID = -3802146343469857530L;
	Long id;

	@NotNull
	@Size(min = 3, max = 255)
	String name;

	/**
	 * Parameter, die an den Workflow-Connector als Einschränkung übergeben werden
	 */
	String parameter;

	@NotNull
	Long workflowId;

	String kommentar;

	String rechteParameter;
}
