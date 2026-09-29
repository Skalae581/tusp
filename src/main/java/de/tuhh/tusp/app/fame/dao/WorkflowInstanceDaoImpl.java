package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.generated.tables.FameRollen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class WorkflowInstanceDaoImpl extends BaseDaoImpl<WorkflowInstance> implements WorkflowInstanceDao {

	@Autowired
	private VorgangDao vorgangDao;

	/**
	 * Erzeugt eine Instanz
	 */
	public WorkflowInstanceDaoImpl() {
		super(WorkflowInstance.class, FAME_WORKFLOW_INSTANCE, FAME_WORKFLOW_INSTANCE.ID);
	}

	@Override
	public int delete(Long id) {

		// Lösche alle Vorgänge
		for (Vorgang vorgang : vorgangDao.findByWorkflowInstance(id)) {
			vorgangDao.delete(vorgang.getId());
		}
		return super.delete(id);
	}

	@Override
	public List<WorkflowInstance> findByRoles(List<String> array) {

		FameRollen inputRollen = FAME_ROLLEN.as("input_rollen");
		FameRollen statusRollen = FAME_ROLLEN.as("status_rollen");

		SelectConditionStep<Record> select = context

													.selectDistinct(table.fields())

													.from(table)

													.join(FAME_WORKFLOW)
													.on(FAME_WORKFLOW.ID.eq(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID))

													// Rollen über Input-Berechtigung ran joinen
													.leftJoin(FAME_INPUT)
													.on(FAME_INPUT.WORKFLOW_ID.eq(FAME_WORKFLOW.ID))
													.leftJoin(FAME_INPUT_BERECHTIGUNG)
													.on(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(FAME_INPUT.ID))
													.leftJoin(inputRollen)
													.on(inputRollen.ID.eq(FAME_INPUT_BERECHTIGUNG.ROLLE_ID))

													// Rollen über Staus ran Joinen
													.leftJoin(FAME_STATUS)
													.on(FAME_STATUS.WORKFLOW_ID.eq(FAME_WORKFLOW.ID))
													.leftJoin(FAME_STATUS_BERECHTIGUNG)
													.on(FAME_STATUS_BERECHTIGUNG.STATUS_FROM_ID.eq(FAME_STATUS.ID))
													.leftJoin(statusRollen)
													.on(statusRollen.ID.eq(FAME_STATUS_BERECHTIGUNG.ROLLE_ID))

													.where(" false ");

		// Die Rollen als OR anhängen
		for (String rolle : array) {
			String roleName = rolle;
			if (rolle.charAt(rolle.length() - 1) == ')') {
				roleName = rolle.substring(0, rolle.indexOf('('));
				String roleParam = rolle.substring(rolle.indexOf('(') + 1, rolle.length() - 1);
				select.or(inputRollen.NAME.eq(roleName).and(FAME_WORKFLOW_INSTANCE.RECHTE_PARAMETER.eq(roleParam)));
				select.or(statusRollen.NAME.eq(roleName).and(FAME_WORKFLOW_INSTANCE.RECHTE_PARAMETER.eq(roleParam)));
			}
			else {
				select.or(inputRollen.NAME.eq(roleName));
				select.or(statusRollen.NAME.eq(roleName));
			}
		}

		return select.orderBy(FAME_WORKFLOW_INSTANCE.NAME).fetchInto(WorkflowInstance.class);
	}

	@Override
	public List<WorkflowInstance> findByWorkflow(Long id) {

		return context	.select().from(table).where(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.eq(id))
						.fetchInto(WorkflowInstance.class);
	}
}
