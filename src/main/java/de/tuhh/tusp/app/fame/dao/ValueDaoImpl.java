package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class ValueDaoImpl extends BaseDaoImpl<Value> implements ValueDao {

	/**
	 * Constructor
	 */
	public ValueDaoImpl() {
		super(Value.class, FAME_VALUE, FAME_VALUE.ID);
	}

	@Override
	public void deleteByInput(Long id) {

		context.delete(table).where(FAME_VALUE.INPUT_ID.eq(id)).execute();
	}

	@Override
	public Value fetchByVorgangAndInput(Long vorgangId, Long inputId) {

		return context	.select().from(table)

						.where(FAME_VALUE.INPUT_ID.eq(inputId)).and(FAME_VALUE.VORGANG_ID.eq(vorgangId))

						.fetchAnyInto(Value.class);
	}

	@Override
	public List<Value> fetchByVorgangAndInputType(Long vorgangId, InputTypeEnum inputType) {

		return context	.select(FAME_VALUE.ID, FAME_VALUE.EINGABE)
						.select(FAME_INPUT.ID.as("input_id"), DSL.inline(vorgangId).as("vorgang_id"))
						.from(FAME_INPUT_TYPE)

						.leftJoin(FAME_INPUT).on(FAME_INPUT.INPUT_TYPE_ID.eq(FAME_INPUT_TYPE.ID))

						.leftJoin(FAME_VALUE).on(FAME_INPUT.ID.eq(FAME_VALUE.INPUT_ID))
						.and(FAME_VALUE.VORGANG_ID.eq(vorgangId))

						.where(FAME_INPUT_TYPE.UNIQ_NAME.eq(inputType)).and(FAME_INPUT.ID.isNotNull())

						.fetchInto(Value.class);
	}

}
