package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.pojo.InputType;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class InputTypeDaoImpl extends BaseDaoImpl<InputType> implements InputTypeDao {

	private static Map<InputTypeEnum, Long> cache = new HashMap<>();

	public InputTypeDaoImpl() {
		super(InputType.class, FAME_INPUT_TYPE, FAME_INPUT_TYPE.ID);
	}

	@Override
	public InputType fetchByName(String string) {

		return context.select().from(table).where(FAME_INPUT_TYPE.NAME.equal(string)).fetchAnyInto(InputType.class);
	}

	@Override
	public InputType fetchByUniqueName(InputTypeEnum uniquename) {

		return context	.select().from(table).where(FAME_INPUT_TYPE.UNIQ_NAME.equal(uniquename))
						.fetchAnyInto(InputType.class);
	}

	@Override
	public Long getId(InputTypeEnum uniquename) {

		if (!cache.containsKey(uniquename)) {
			cache.put(uniquename, fetchByUniqueName(uniquename).getId());
		}
		return cache.get(uniquename);
	}
}
