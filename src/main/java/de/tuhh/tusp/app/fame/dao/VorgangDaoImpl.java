package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.FAME_INPUT;
import static org.jooq.generated.Tables.FAME_VALUE;
import static org.jooq.generated.Tables.FAME_VORGANG;
import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.groupConcat;
import static org.jooq.impl.DSL.inline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSeekStep1;
import org.jooq.SelectSelectStep;
import org.jooq.generated.tables.FameInput;
import org.jooq.generated.tables.FameValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchergebnis.InputValue;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.app.fame.service.StatusService;
import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.utils.CalendarUtils;

@Component
public class VorgangDaoImpl extends BaseDaoImpl<Vorgang> implements VorgangDao {

	@Autowired
	private InputDao inputDao;

	@Autowired
	private StatusService statusService;

	/**
	 * Erzeugt eine Instanz
	 */
	public VorgangDaoImpl() {

		super(Vorgang.class, FAME_VORGANG, FAME_VORGANG.ID);
	}

	@Override
	public int delete(Long id) {

		// Lösche erst alle Werte des Vorgangs
		context.deleteFrom(FAME_VALUE).where(FAME_VALUE.VORGANG_ID.eq(id)).execute();

		return super.delete(id);
	}

	@Override
	public Vorgang fetchByWorkflowInstanceAndConnectorKey(Long workflowId, String connectorId) {

		return context	.select().from(table)

						.where(FAME_VORGANG.WORKFLOW_INSTANCE_ID.eq(workflowId))
						.and(FAME_VORGANG.CONNECTOR_KEY.eq(connectorId))

						.fetchAnyInto(Vorgang.class);

	}

	@Override
	public List<Map<String, String>> findByStatusAndWorkflowInstance(Long statusId, Long instanceId) {

		Status status = statusService.fetchById(statusId);
		List<Input> cols = new ArrayList<>();
		for (Input input : inputDao.findByWorkflow(status.getWorkflowId(), InputDao.ORDER_BY_TABLE_POSITION)) {
			if (input.getTablePos() != null && input.getTablePos() > 0) {
				cols.add(input);
			}
		}

		FameValue[] joins = new FameValue[cols.size()];
		List<Map<String, String>> ret = new ArrayList<>();
		SelectSelectStep<Record1<Long>> select = context.selectDistinct(FAME_VORGANG.ID);
		for (int i = 0; i < cols.size(); i++) {
			joins[i] = FAME_VALUE.as("input_" + cols.get(i).getId());
			select.select(joins[i].EINGABE.as("value_" + cols.get(i).getId()));
		}
		SelectJoinStep<Record1<Long>> from = select.from(table);
		for (int i = 0; i < cols.size(); i++) {
			from.leftJoin(joins[i]).on(FAME_VORGANG.ID.eq(joins[i].VORGANG_ID))
				.and(joins[i].INPUT_ID.eq(cols.get(i).getId()));
		}
		SelectSeekStep1<Record1<Long>, Long> sql =
				from.where(FAME_VORGANG.STATUS_ID.eq(statusId)).and(FAME_VORGANG.WORKFLOW_INSTANCE_ID.eq(instanceId))
					.orderBy(FAME_VORGANG.ID);
		Result<Record1<Long>> vorgaenge = sql.fetch();

		for (Record vorgang : vorgaenge) {
			Map<String, String> map = new HashMap<>();
			map.put("id", vorgang.get("id") + "");
			for (int i = 0; i < cols.size(); i++) {
				String key = "value_" + cols.get(i).getId();
				map.put(key, (String) vorgang.get(key));
			}
			ret.add(map);
		}
		return ret;
	}

	@Override
	public List<Vorgang> findByWorkflowInstance(Long instanceId) {

		return context	.select().from(table).where(FAME_VORGANG.WORKFLOW_INSTANCE_ID.in(instanceId))
						.fetchInto(Vorgang.class);
	}

	@Override
	public List<Suchergebnis> search(List<Suchparameter> suchparameter) {

		FameValue valueTreffer = FAME_VALUE.as("val1");
		FameValue valueDesc = FAME_VALUE.as("val2");
		FameInput inputTreffer = FAME_INPUT.as("in1");
		FameInput inputDesc = FAME_INPUT.as("in2");

		Condition condition = valueTreffer.ID.ne(valueTreffer.ID);

		for (Suchparameter p : suchparameter) {
			condition =
					condition.or(valueTreffer.INPUT_ID	.eq(p.getInput().getId())
														.and(valueTreffer.EINGABE.like("%" + p.getSearchword() + "%")));
		}

		List<Field<?>> groupByFields = new ArrayList<>();
		groupByFields.addAll(Arrays.asList(FAME_VORGANG.fields()));
		groupByFields.add(valueTreffer.CREATE_TIME);
		groupByFields.add(valueTreffer.EINGABE);
		groupByFields.add(valueTreffer.INPUT_ID);
		groupByFields.add(valueTreffer.VORGANG_ID);
		groupByFields.add(valueTreffer.ID.as("val_id"));
		groupByFields.add(inputTreffer.NAME.as("input_name"));

		AggregateFunction<String> descField =
				groupConcat(concat(inputDesc.NAME, inline("#=#"), valueDesc.EINGABE))	.orderBy(inputDesc.TABLE_POS)
																						.separator("#;#");

		List<Map<String, Object>> results = context	.select(groupByFields)

													.select(descField.as("description"))

													.from(valueTreffer)

													.leftJoin(inputTreffer)
													.on(valueTreffer.INPUT_ID.eq(inputTreffer.ID))

													.leftJoin(FAME_VORGANG)
													.on(FAME_VORGANG.ID.eq(valueTreffer.VORGANG_ID))

													.leftJoin(valueDesc)
													.on(valueDesc.VORGANG_ID.eq(valueTreffer.VORGANG_ID))

													.leftJoin(inputDesc).on(inputDesc.ID.eq(valueDesc.INPUT_ID))

													.where(condition)

													.and(inputDesc.TABLE_POS.isNotNull())

													.groupBy(groupByFields)

													.fetchMaps();

		List<Suchergebnis> list = new ArrayList<>();
		for (Map<String, Object> row : results) {
			Vorgang vor = new Vorgang();
			vor.setId((Long) row.get(valueTreffer.VORGANG_ID.getName()));
			vor.setConnectorKey((String) row.get(FAME_VORGANG.CONNECTOR_KEY.getName()));
			vor.setGesperrtDatum(CalendarUtils.toDate((LocalDateTime) row.get(FAME_VORGANG.GESPERRT_DATUM.getName())));
			vor.setGesperrtDurch((String) row.get(FAME_VORGANG.GESPERRT_DURCH.getName()));
			vor.setWarn((String) row.get(FAME_VORGANG.WARN.getName()));
			vor.setWorkflowInstanceId((Long) row.get(FAME_VORGANG.WORKFLOW_INSTANCE_ID.getName()));
			vor.setStatusId((Long) row.get(FAME_VORGANG.STATUS_ID.getName()));

			Value val = new Value();
			val.setId((Long) row.get("val_id"));
			val.setEingabe((String) row.get(valueTreffer.EINGABE.getName()));
			val.setCreateTime(CalendarUtils.toDate((LocalDateTime) row.get(valueTreffer.CREATE_TIME.getName())));
			val.setInputId((Long) row.get(valueTreffer.INPUT_ID.getName()));
			val.setVorgangId((Long) row.get(valueTreffer.VORGANG_ID.getName()));

			Suchergebnis s = new Suchergebnis();
			s.setValue(val);
			s.setVorgang(vor);
			s.setInputName((String) row.get("input_name"));

			String[] felder = ((String) row.get("description")).split("#;#");
			InputValue[] values = new InputValue[felder.length];
			for (int i = 0; i < felder.length; i++) {
				String[] split = felder[i].split("#=#");
				if (split.length != 2) {
					values[i] = s.new InputValue(split[0], "");
				}
				else {
					values[i] = s.new InputValue(split[0], split[1]);
				}
			}
			s.setDescription(values);

			list.add(s);
		}

		return list;
	}

	@Override
	public List<Map<String, String>> search(List<WorkflowInstance> workflowInstances, Map<Long, String> searchFields) {

		if (workflowInstances == null || workflowInstances.isEmpty() || searchFields == null
				|| searchFields.isEmpty()) {
			return null;
		}

		// IDs alle WorkflowInstances sammeln
		List<Long> wis = new ArrayList<>();
		for (WorkflowInstance wi : workflowInstances) {
			wis.add(wi.getId());
		}

		// Hole alle Eingebafelder
		List<Input> inputs =
				inputDao.findByWorkflow(workflowInstances.get(0).getWorkflowId(), InputDao.ORDER_BY_TABLE_POSITION);

		if (inputs.isEmpty()) {
			return null;
		}

		// Suche die Heraus, die angezeigt werden müssen
		List<Input> selectCols = new ArrayList<>();
		for (Input input : inputs) {
			if (input.getTablePos() != null && input.getTablePos() > 0) {
				selectCols.add(input);
			}
		}

		// Aliase vorbereiten
		FameValue[] joins = new FameValue[inputs.size()];
		for (int i = 0; i < inputs.size(); i++) {
			joins[i] = FAME_VALUE.as("input_" + inputs.get(i).getId());
		}

		// Select
		SelectSelectStep<Record1<Long>> select = context.select(FAME_VORGANG.ID);
		for (int i = 0; i < inputs.size(); i++) {
			if (selectCols.contains(inputs.get(i))) {
				select.select(joins[i].EINGABE.as("value_" + inputs.get(i).getId()));
			}
		}

		// Joins
		SelectJoinStep<Record1<Long>> from = select.from(table);
		for (int i = 0; i < inputs.size(); i++) {
			from.leftJoin(joins[i]).on(FAME_VORGANG.ID.eq(joins[i].VORGANG_ID))
				.and(joins[i].INPUT_ID.eq(inputs.get(i).getId()));
		}

		// Where
		SelectConditionStep<Record1<Long>> sql = from.where(FAME_VORGANG.WORKFLOW_INSTANCE_ID.in(wis));
		for (int i = 0; i < inputs.size(); i++) {
			String val = searchFields.get(inputs.get(i).getId());
			if (val == null || val.isEmpty()) {
				continue;
			}
			sql.and(joins[i].EINGABE.like("%" + val + "%"));
		}

		// Order and Execute
		Result<Record1<Long>> vorgaenge = sql.orderBy(FAME_VORGANG.ID).fetch();

		List<Map<String, String>> ret = new ArrayList<>();
		for (Record vorgang : vorgaenge) {
			Map<String, String> map = new HashMap<>();
			map.put("id", vorgang.get("id") + "");
			for (int i = 0; i < selectCols.size(); i++) {
				String key = "value_" + selectCols.get(i).getId();
				map.put(key, (String) vorgang.get(key));
			}
			ret.add(map);
		}
		return ret;
	}

}
