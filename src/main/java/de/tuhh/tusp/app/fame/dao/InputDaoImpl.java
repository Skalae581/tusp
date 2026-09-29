package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.*;

import java.util.List;

import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSeekStep3;
import org.jooq.Table;
import org.jooq.impl.SQLDataType;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.enums.converter.InputRechteEnumConverter;
import de.tuhh.tusp.app.fame.enums.converter.InputTypeEnumConverter;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedInput;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class InputDaoImpl extends BaseDaoImpl<Input> implements InputDao {

	/**
	 * Erstellt eiene Instanz
	 */
	public InputDaoImpl() {
		super(Input.class, FAME_INPUT, FAME_INPUT.ID);
	}

	@Override
	public int delete(Long id) {

		// Berechtigungen löschen
		context.deleteFrom(FAME_INPUT_BERECHTIGUNG).where(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(id)).execute();

		return super.delete(id);
	}

	@Override
	public List<Input> findByWorkflow(Long workflowId) {

		return context.select().from(table).where(FAME_INPUT.WORKFLOW_ID.eq(workflowId)).fetchInto(Input.class);
	}

	@Override
	public List<Input> findByWorkflow(Long workflowId, Field<?> order) {

		return context	.select().from(table)

						.where(FAME_INPUT.WORKFLOW_ID.equal(workflowId)).and(FAME_INPUT.HIDDEN.ne(1))

						.orderBy(order, FAME_INPUT.NAME)

						.fetchInto(Input.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Input> findByWorkflowAndRoles(Long wokflowId, List<String> roles) {

		// Subselect für die Berechtigungen aufbauen
		SelectConditionStep<Record> subselectBuilder =
				context	.select(FAME_INPUT_BERECHTIGUNG.RECHT.max().as("recht")).select(FAME_INPUT_BERECHTIGUNG.INPUT_ID)

						.from(FAME_INPUT_BERECHTIGUNG)

						.leftJoin(FAME_ROLLEN).on(FAME_INPUT_BERECHTIGUNG.ROLLE_ID.eq(FAME_ROLLEN.ID))

						.leftJoin(FAME_INPUT).on(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(FAME_INPUT.ID))

						.leftJoin(FAME_WORKFLOW_INSTANCE)
						.on(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.eq(FAME_INPUT.WORKFLOW_ID))

						.where(" false ");

		// Die Rollen als OR anhängen
		for (String rolle : roles) {
			String roleName = rolle;
			if (rolle.charAt(rolle.length() - 1) == ')') {
				roleName = rolle.substring(0, rolle.indexOf('('));
				String roleParam = rolle.substring(rolle.indexOf('(') + 1, rolle.length() - 1);
				subselectBuilder.or(FAME_ROLLEN.NAME.eq(roleName)
													.and(FAME_WORKFLOW_INSTANCE.RECHTE_PARAMETER.eq(roleParam)));
			}
			else {
				subselectBuilder.or(FAME_ROLLEN.NAME.eq(roleName));
			}
		}

		DataType<InputRechteEnum> rechteType = SQLDataType.VARCHAR.asConvertedDataType(new InputRechteEnumConverter());

		Table<Record> subselect = subselectBuilder.groupBy(FAME_INPUT_BERECHTIGUNG.INPUT_ID).asTable();

		List<Input> ret;
		ret = context	.selectDistinct(table.fields()).select(subselect.field("recht", rechteType).max())

						.from(FAME_INPUT)

						.leftJoin(subselect).on(FAME_INPUT.ID.equal((Field<Long>) subselect.field("input_id")))

						.where(FAME_INPUT.HIDDEN.ne(1))

						.and(FAME_INPUT.WORKFLOW_ID.eq(wokflowId))

						.groupBy(table.fields())

						.orderBy(FAME_INPUT.POSITION.asc(), subselect.field("recht").max().desc())

						.fetchInto(Input.class);

		return ret;
	}

	@Override
	public List<ExtendedInput> findByWorkflowAndVorgang(Long workflowId, Long vorgangId) {

		DataType<InputTypeEnum> inputTypeType = SQLDataType.VARCHAR.asConvertedDataType(new InputTypeEnumConverter());
		Field<InputTypeEnum> field =
				FAME_INPUT_TYPE.field(FAME_INPUT_TYPE.UNIQ_NAME.getName(), inputTypeType).as("input_type_uniq_name");

		return context	.select(table.fields()).select(FAME_VALUE.EINGABE).select(field)
						.select(inline(vorgangId).as("vorgang_id"), FAME_VALUE.CREATE_TIME,
								FAME_VALUE.ID.as("value_id"), FAME_FILE.MIME_TYPE)

						.from(FAME_INPUT)

						.leftJoin(FAME_INPUT_TYPE).on(FAME_INPUT.INPUT_TYPE_ID.eq(FAME_INPUT_TYPE.ID))

						.leftJoin(FAME_VALUE).on(FAME_INPUT.ID.eq(FAME_VALUE.INPUT_ID))
						.and(FAME_VALUE.VORGANG_ID.eq(vorgangId))

						.leftJoin(FAME_FILE).on(FAME_FILE.VALUE_ID.eq(FAME_VALUE.ID))

						.where(FAME_INPUT.WORKFLOW_ID.eq(workflowId))

						.fetchInto(ExtendedInput.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExtendedInput> findByWorkflowInstanceStatusAndRoles(Long instanceId, Long statusId,
																	List<String> roles) {

		// Subselect für die Berechtigungen aufbauen
		SelectConditionStep<Record3<InputRechteEnum, Long, String>> subselectBuilder =
				context	.select(FAME_INPUT_BERECHTIGUNG.RECHT, FAME_INPUT_BERECHTIGUNG.INPUT_ID,
								FAME_STATUS.NAME.as("status"))

						.from(FAME_INPUT_BERECHTIGUNG)

						.join(FAME_ROLLEN).on(FAME_INPUT_BERECHTIGUNG.ROLLE_ID.eq(FAME_ROLLEN.ID))

						.join(FAME_STATUS).on(FAME_INPUT_BERECHTIGUNG.STATUS_ID.eq(FAME_STATUS.ID))

						.and(FAME_STATUS.ID.eq(statusId).or(FAME_STATUS.NAME.eq("ALL")))

						.leftJoin(FAME_INPUT).on(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(FAME_INPUT.ID))

						.leftJoin(FAME_WORKFLOW_INSTANCE)
						.on(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.eq(FAME_INPUT.WORKFLOW_ID))

						.where(" false ");

		// Die Rollen als OR anhängen
		for (String rolle : roles) {
			String roleName = rolle;
			if (rolle.charAt(rolle.length() - 1) == ')') {
				roleName = rolle.substring(0, rolle.indexOf('('));
				String roleParam = rolle.substring(rolle.indexOf('(') + 1, rolle.length() - 1);
				subselectBuilder.or(FAME_ROLLEN.NAME.eq(roleName)
													.and(FAME_WORKFLOW_INSTANCE.RECHTE_PARAMETER.eq(roleParam)));
			}
			else {
				subselectBuilder.or(FAME_ROLLEN.NAME.eq(roleName));
			}
		}

		Table<?> subselect =
				context	.select(subselectBuilder.field("recht").max().as("recht"), subselectBuilder.field("input_id"))

						.from(subselectBuilder.asTable())

						.groupBy(subselectBuilder.field("input_id"))

						.asTable();

		DataType<InputTypeEnum> inputTypeType = SQLDataType.VARCHAR.asConvertedDataType(new InputTypeEnumConverter());
		DataType<InputRechteEnum> rechteType = SQLDataType.VARCHAR.asConvertedDataType(new InputRechteEnumConverter());
		Field<InputTypeEnum> field =
				FAME_INPUT_TYPE.field(FAME_INPUT_TYPE.UNIQ_NAME.getName(), inputTypeType).as("input_type_uniq_name");

		return context	.selectDistinct(table.fields()).select(field).select(subselect.field("recht", rechteType))

						.from(FAME_WORKFLOW_INSTANCE)

						.join(FAME_INPUT).on(FAME_INPUT.WORKFLOW_ID.eq(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID))

						.join(FAME_INPUT_TYPE).on(FAME_INPUT.INPUT_TYPE_ID.eq(FAME_INPUT_TYPE.ID))

						.leftJoin(subselect).on(FAME_INPUT.ID.equal((Field<Long>) subselect.field("input_id")))

						.where(FAME_WORKFLOW_INSTANCE.ID.eq(instanceId)).and(FAME_INPUT.HIDDEN.ne(1))

						.orderBy(FAME_INPUT.TABLE_POS.asc(), subselect.field("recht").desc())
						.fetchInto(ExtendedInput.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExtendedInput> findByWorkflowInstanceVorgangAndRoles(	Long workflowInstanceId, Long vorgangId,
																		List<String> roles) {

		Record1<Long> result = context	.select(FAME_VORGANG.STATUS_ID).from(FAME_VORGANG)
										.where(FAME_VORGANG.ID.eq(vorgangId)).fetchAny();
		Long statusID;
		if (result != null) {
			statusID = result.value1();
		}
		else {
			statusID = -10000L; // Gibts sicher nicht
		}

		// Subselect für die Berechtigungen aufbauen
		SelectConditionStep<Record3<InputRechteEnum, Long, String>> subselectBuilder =
				context	.select(FAME_INPUT_BERECHTIGUNG.RECHT, FAME_INPUT_BERECHTIGUNG.INPUT_ID,
								FAME_STATUS.NAME.as("status"))

						.from(FAME_INPUT_BERECHTIGUNG)

						.join(FAME_ROLLEN).on(FAME_INPUT_BERECHTIGUNG.ROLLE_ID.eq(FAME_ROLLEN.ID))

						.join(FAME_STATUS).on(FAME_INPUT_BERECHTIGUNG.STATUS_ID.eq(FAME_STATUS.ID))

						.and(FAME_STATUS.ID.eq(statusID).or(FAME_STATUS.NAME.eq("ALL")))

						.leftJoin(FAME_INPUT).on(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(FAME_INPUT.ID))

						.leftJoin(FAME_WORKFLOW_INSTANCE)
						.on(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.eq(FAME_INPUT.WORKFLOW_ID))

						.where(" false ");

		// Die Rollen als OR anhängen
		for (String rolle : roles) {
			String roleName = rolle;
			if (rolle.charAt(rolle.length() - 1) == ')') {
				roleName = rolle.substring(0, rolle.indexOf('('));
				String roleParam = rolle.substring(rolle.indexOf('(') + 1, rolle.length() - 1);
				subselectBuilder.or(FAME_ROLLEN.NAME.eq(roleName)
													.and(FAME_WORKFLOW_INSTANCE.RECHTE_PARAMETER.eq(roleParam)));
			}
			else {
				subselectBuilder.or(FAME_ROLLEN.NAME.eq(roleName));
			}
		}

		Table<?> subselect =
				context	.select(subselectBuilder.field("recht").max().as("recht"), subselectBuilder.field("input_id"))

						.from(subselectBuilder.asTable())

						.groupBy(subselectBuilder.field("input_id"))

						.asTable();

		DataType<InputTypeEnum> inputTypeType = SQLDataType.VARCHAR.asConvertedDataType(new InputTypeEnumConverter());
		DataType<InputRechteEnum> rechteType = SQLDataType.VARCHAR.asConvertedDataType(new InputRechteEnumConverter());
		Field<InputTypeEnum> field =
				FAME_INPUT_TYPE.field(FAME_INPUT_TYPE.UNIQ_NAME.getName(), inputTypeType).as("input_type_uniq_name");

		SelectSeekStep3<?, ?, ?, ?> sql;
		sql = context	.selectDistinct(table.fields()).select(FAME_VALUE.EINGABE).select(field)
						.select(inline(vorgangId).as("vorgang_id"), FAME_VALUE.CREATE_TIME,
								FAME_VALUE.ID.as("value_id"), subselect.field("recht", rechteType), FAME_FILE.MIME_TYPE)

						.from(FAME_WORKFLOW_INSTANCE)

						.join(FAME_INPUT).on(FAME_INPUT.WORKFLOW_ID.eq(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID))

						.join(FAME_INPUT_TYPE).on(FAME_INPUT.INPUT_TYPE_ID.eq(FAME_INPUT_TYPE.ID))

						.leftJoin(subselect).on(FAME_INPUT.ID.equal((Field<Long>) subselect.field("input_id")))

						.leftJoin(FAME_VALUE).on(FAME_INPUT.ID.eq(FAME_VALUE.INPUT_ID))
						.and(FAME_VALUE.VORGANG_ID.eq(vorgangId))

						.leftJoin(FAME_FILE).on(FAME_FILE.VALUE_ID.eq(FAME_VALUE.ID))

						.where(FAME_WORKFLOW_INSTANCE.ID.eq(workflowInstanceId)).and(FAME_INPUT.HIDDEN.ne(1))

						.orderBy(	FAME_INPUT.POSITION.asc(), FAME_VALUE.CREATE_TIME.asc().nullsLast(),
									subselect.field("recht").desc());

		return sql.fetchInto(ExtendedInput.class);
	}

}
