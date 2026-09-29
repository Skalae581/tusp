package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.FAME_INPUT_BERECHTIGUNG;
import static org.jooq.generated.Tables.FAME_ROLLEN;
import static org.jooq.generated.Tables.FAME_STATUS;
import static org.jooq.generated.Tables.FAME_STATUS_BERECHTIGUNG;
import static org.jooq.generated.Tables.FAME_VORGANG;
import static org.jooq.generated.Tables.FAME_WORKFLOW_INSTANCE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedWorkflowInstance;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class StatusDaoImpl extends BaseDaoImpl<Status> implements StatusDao {

	/**
	 * Erzeugt eine neue Instanz
	 */
	public StatusDaoImpl() {
		super(Status.class, FAME_STATUS, FAME_STATUS.ID);
	}

	@Override
	public int delete(Long id) {

		// Berechtigungen löschen
		context.deleteFrom(FAME_STATUS_BERECHTIGUNG)
				.where(FAME_STATUS_BERECHTIGUNG.STATUS_FROM_ID.eq(id).or(FAME_STATUS_BERECHTIGUNG.STATUS_TO_ID.eq(id)))
				.execute();

		return super.delete(id);
	}

	@Override
	public List<Status> findByIds(List<Long> ids) {

		return context.select().from(table).where(FAME_STATUS.ID.in(ids)).orderBy(FAME_STATUS.POSITION)
				.fetchInto(Status.class);
	}

	@Override
	public List<Status> findByWorkflow(Long workflowId, boolean includeHidden) {

		SelectConditionStep<Record> select = context.select(table.fields()).from(table)

				.where(FAME_STATUS.WORKFLOW_ID.equal(workflowId));

		if (!includeHidden) {
			select = select.and(FAME_STATUS.HIDDEN.ne(1));
		}

		return select.orderBy(FAME_STATUS.POSITION).fetchInto(Status.class);
	}

	@Override
	public List<Status> findByWorkflowInstance(Long workflowInstanceId, boolean includeHidden) {

		SelectConditionStep<Record> select = context.select(table.fields()).from(table)

				.leftJoin(FAME_WORKFLOW_INSTANCE).on(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.eq(FAME_STATUS.WORKFLOW_ID))

				.where(FAME_WORKFLOW_INSTANCE.ID.equal(workflowInstanceId));

		if (!includeHidden) {
			select = select.and(FAME_STATUS.HIDDEN.ne(1));
		}

		return select.orderBy(FAME_STATUS.POSITION).fetchInto(Status.class);
	}

	@Override
	public List<ExtendedWorkflowInstance> findDetaildByWorkflowInstancesAndRoles(List<WorkflowInstance> userWorkflows,
			List<String> roles) {

		if (userWorkflows == null) {
			return null;
		}

		Collections.sort(userWorkflows, new Comparator<WorkflowInstance>() {

			@Override
			public int compare(WorkflowInstance lhs, WorkflowInstance rhs) {

				return lhs.getName().compareTo(rhs.getName());
			}
		});

		List<ExtendedWorkflowInstance> ret = new ArrayList<>();
		for (WorkflowInstance wi : userWorkflows) {

			// RollenIds Des Nutzers
			List<Long> roleIDs = context.select(FAME_ROLLEN.ID)

					.from(FAME_ROLLEN)

					.where(FAME_ROLLEN.NAME.in(roles))

					.or(FAME_ROLLEN.NAME.concat("(" + wi.getRechteParameter() + ")").in(roles)).fetchInto(Long.class);

			// Hat das Recht für die All Rolle?
			boolean hasAll = context.select()

					.from(FAME_INPUT_BERECHTIGUNG)

					.leftJoin(FAME_STATUS).on(FAME_STATUS.ID.eq(FAME_INPUT_BERECHTIGUNG.STATUS_ID))

					.where(FAME_STATUS.NAME.eq("ALL"))

					.and(FAME_INPUT_BERECHTIGUNG.ROLLE_ID.in(roleIDs))

					.and(FAME_INPUT_BERECHTIGUNG.RECHT.ne(InputRechteEnum.NONE))

					.fetch().size() > 0;

			List<Long> statusIds;
			if (hasAll) {
				statusIds = context.selectDistinct(FAME_STATUS.ID).from(FAME_STATUS).fetchInto(Long.class);
			} else {
				statusIds = context.selectDistinct(FAME_INPUT_BERECHTIGUNG.STATUS_ID)

						.from(FAME_INPUT_BERECHTIGUNG)

						.where(FAME_INPUT_BERECHTIGUNG.ROLLE_ID.in(roleIDs))

						.and(FAME_INPUT_BERECHTIGUNG.RECHT.ne(InputRechteEnum.NONE))

						.fetchInto(Long.class);
			}

			@SuppressWarnings("deprecation")
			Result<Record3<Long, String, Integer>> result = context
					.select(FAME_STATUS.ID.as("sid"), FAME_STATUS.NAME.as("sname"), FAME_VORGANG.ID.count())

					.from(FAME_STATUS)

					.leftJoin(FAME_VORGANG).on(FAME_STATUS.ID.eq(FAME_VORGANG.STATUS_ID))

					.and(FAME_VORGANG.WORKFLOW_INSTANCE_ID.eq(wi.getId())
							.or(FAME_VORGANG.WORKFLOW_INSTANCE_ID.isNull()))

					.where(FAME_STATUS.ID.in(statusIds))

					.and(FAME_STATUS.WORKFLOW_ID.in(context.select(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID)
							.from(FAME_WORKFLOW_INSTANCE).where(FAME_WORKFLOW_INSTANCE.ID.eq(wi.getId()))))

					.groupBy(FAME_STATUS.ID, FAME_STATUS.NAME, FAME_STATUS.POSITION)

					.orderBy(FAME_STATUS.POSITION.asc())

					.fetch();

			ExtendedWorkflowInstance currentWF = new ExtendedWorkflowInstance();
			currentWF.setId(wi.getId());
			currentWF.setWorkflowId(wi.getWorkflowId());
			currentWF.setName(wi.getName());
			currentWF.setNumVorgaenge(0);
			ret.add(currentWF);

			for (Record r : result) {
				ExtendedStatus s = new ExtendedStatus();
				s.setId((Long) r.get("sid"));
				s.setName((String) r.get("sname"));
				s.setNumVorgaenge((Integer) r.get("count"));
				currentWF.setNumVorgaenge(currentWF.getNumVorgaenge() + s.getNumVorgaenge());
				currentWF.getStatuses().add(s);
			}
		}
		return ret;
	}
}
