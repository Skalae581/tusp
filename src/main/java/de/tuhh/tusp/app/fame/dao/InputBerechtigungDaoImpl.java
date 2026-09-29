package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.jooq.Condition;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.pojo.InputBerechtigung;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class InputBerechtigungDaoImpl extends BaseDaoImpl<InputBerechtigung> implements InputBerechtigungDao {

	/**
	 * Construct
	 */
	public InputBerechtigungDaoImpl() {
		super(InputBerechtigung.class, FAME_INPUT_BERECHTIGUNG, FAME_INPUT_BERECHTIGUNG.ID);
	}

	@Override
	public boolean canRead(Long inputId, Long statusId, Long vorgangId, List<String> roles) {

		Condition rolePart = FAME_INPUT_BERECHTIGUNG.ID.ne(FAME_INPUT_BERECHTIGUNG.ID); // immer false!
		for (String role : roles) {
			if (role.endsWith(")") && role.contains("(")) {
				String roleName = role.substring(0, role.indexOf("(") - 1);
				String wfInstance = role.substring(roleName.length() + 1, role.length() - 1);
				rolePart = rolePart.or(FAME_ROLLEN.NAME.eq(roleName).and(FAME_WORKFLOW_INSTANCE.NAME.eq(wfInstance)));
			}
			else {
				rolePart = rolePart.or(FAME_ROLLEN.NAME.eq(role));
			}
		}
		return context	.select()

						.from(FAME_VORGANG)

						.leftJoin(FAME_WORKFLOW_INSTANCE)
						.on(FAME_VORGANG.WORKFLOW_INSTANCE_ID.eq(FAME_WORKFLOW_INSTANCE.ID))

						.leftJoin(FAME_INPUT).on(FAME_INPUT.WORKFLOW_ID.eq(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID))

						.leftJoin(FAME_INPUT_BERECHTIGUNG).on(FAME_INPUT.ID.eq(FAME_INPUT_BERECHTIGUNG.INPUT_ID))

						.leftJoin(FAME_STATUS).on(FAME_STATUS.ID.eq(FAME_INPUT_BERECHTIGUNG.STATUS_ID))

						.leftJoin(FAME_ROLLEN).on(FAME_ROLLEN.ID.eq(FAME_INPUT_BERECHTIGUNG.ROLLE_ID))

						.where(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(inputId))

						.and(FAME_INPUT_BERECHTIGUNG.STATUS_ID.eq(statusId).or(FAME_STATUS.NAME.eq("ALL")))

						.and(FAME_INPUT_BERECHTIGUNG.RECHT.ne(InputRechteEnum.NONE))

						.and(rolePart)

						.execute() > 0;
	}

	@Override
	public void deleteByInput(Long id) {

		context.delete(table).where(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(id)).execute();
	}

	@Override
	public void deleteByStatus(Long id) {

		context.delete(table).where(FAME_INPUT_BERECHTIGUNG.STATUS_ID.eq(id)).execute();
	}

	@Override
	public List<InputBerechtigung> findByWorkflow(Long id) {

		return context	.select(table.fields()).from(table)

						.leftJoin(FAME_INPUT).on(FAME_INPUT.ID.equal(FAME_INPUT_BERECHTIGUNG.INPUT_ID))

						.leftJoin(FAME_STATUS).on(FAME_STATUS.ID.equal(FAME_INPUT_BERECHTIGUNG.STATUS_ID))

						.where(FAME_INPUT.WORKFLOW_ID.equal(id))
						.orderBy(	FAME_STATUS.NAME, FAME_INPUT.NAME, FAME_INPUT_BERECHTIGUNG.ROLLE_ID,
									FAME_INPUT_BERECHTIGUNG.RECHT)
						.fetchInto(InputBerechtigung.class);
	}
}
