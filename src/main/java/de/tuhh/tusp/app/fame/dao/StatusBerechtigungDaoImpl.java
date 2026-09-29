package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.ArrayList;
import java.util.List;

import org.jooq.generated.tables.FameStatus;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.pojo.StatusBerechtigung;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class StatusBerechtigungDaoImpl extends BaseDaoImpl<StatusBerechtigung> implements StatusBerechtigungDao {

	public StatusBerechtigungDaoImpl() {
		super(StatusBerechtigung.class, FAME_STATUS_BERECHTIGUNG, FAME_STATUS_BERECHTIGUNG.ID);
	}

	@Override
	public void deleteByStatus(Long id) {

		context	.delete(table).where(FAME_STATUS_BERECHTIGUNG.STATUS_FROM_ID.eq(id))
				.or(FAME_STATUS_BERECHTIGUNG.STATUS_TO_ID.eq(id)).execute();
	}

	@Override
	public List<ExtendedStatusBerechtigung> findByRollenAndWorkflowInstance(List<String> roles,
																			Long workflowInstanceId) {

		List<String> cleanRoles = new ArrayList<>();
		for (String role : roles) {
			cleanRoles.add(role.replaceAll("\\([A-Z]+\\)", ""));
		}

		FameStatus from = FAME_STATUS.as("FROM");
		FameStatus to = FAME_STATUS.as("TO");

		return context	.select(table.fields())
						.select(to.NAME.as("status_to_name"), from.NAME.as("status_from_name"),
								to.POSITION.as("status_to_position"), from.POSITION.as("status_from_position"),
								to.HIDDEN.as("status_to_hidden"))
						.from(table)

						.leftJoin(from).on(from.ID.eq(FAME_STATUS_BERECHTIGUNG.STATUS_FROM_ID))

						.leftJoin(to).on(to.ID.eq(FAME_STATUS_BERECHTIGUNG.STATUS_TO_ID))

						.leftJoin(FAME_WORKFLOW_INSTANCE).on(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.eq(from.WORKFLOW_ID))

						.leftJoin(FAME_ROLLEN).on(FAME_ROLLEN.ID.eq(FAME_STATUS_BERECHTIGUNG.ROLLE_ID))

						.where(FAME_WORKFLOW_INSTANCE.ID.equal(workflowInstanceId)).and(FAME_ROLLEN.NAME.in(cleanRoles))
						.fetchInto(ExtendedStatusBerechtigung.class);
	}

	@Override
	public List<StatusBerechtigung> findByWorkflow(Long id) {

		FameStatus from = FAME_STATUS.as("FROM");
		FameStatus to = FAME_STATUS.as("TO");

		return context	.select(table.fields()).from(table)

						.leftJoin(from).on(from.ID.eq(FAME_STATUS_BERECHTIGUNG.STATUS_FROM_ID))

						.leftJoin(to).on(to.ID.eq(FAME_STATUS_BERECHTIGUNG.STATUS_TO_ID))

						.where(from.WORKFLOW_ID.eq(id)).orderBy(from.NAME, to.NAME, FAME_STATUS_BERECHTIGUNG.ROLLE_ID)
						.fetchInto(StatusBerechtigung.class);
	}

}
