package de.tuhh.tusp.app.fame.dao;

import static org.jooq.generated.Tables.FAME_FILE;
import static org.jooq.generated.Tables.FAME_INPUT;
import static org.jooq.generated.Tables.FAME_INPUT_BERECHTIGUNG;
import static org.jooq.generated.Tables.FAME_ROLLEN;
import static org.jooq.generated.Tables.FAME_VALUE;
import static org.jooq.generated.Tables.FAME_WORKFLOW_INSTANCE;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.Table;
import org.jooq.impl.SQLDataType;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.fame.enums.InputRechteEnum;
import de.tuhh.tusp.app.fame.enums.converter.InputRechteEnumConverter;
import de.tuhh.tusp.app.fame.pojo.File;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFile;
import de.tuhh.tusp.app.fame.pojo.extended.ExtendedFileMitRecht;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class FileDaoImpl extends BaseDaoImpl<File> implements FileDao {

	private static Logger logger = LogManager.getLogger(FileDaoImpl.class);

	/**
	 * Construct
	 */
	public FileDaoImpl() {
		super(File.class, FAME_FILE, FAME_FILE.ID);
	}

	@Override
	public ExtendedFile fetchByValueId(Long valueId) {

		return context.select(FAME_FILE.fields()).select(FAME_VALUE.EINGABE.as("filename"))
				.select(FAME_VALUE.VORGANG_ID)

				.from(FAME_FILE)

				.leftJoin(FAME_VALUE).on(FAME_FILE.VALUE_ID.eq(FAME_VALUE.ID))

				.where(FAME_FILE.VALUE_ID.eq(valueId))

				.fetchAnyInto(ExtendedFile.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ExtendedFileMitRecht fetchByValueId(Long valueId, List<String> rollen) {

		// Subselect für die Berechtigungen aufbauen
		SelectConditionStep<Record> subselectBuilder = context.select(FAME_INPUT_BERECHTIGUNG.RECHT.max().as("recht"))
				.select(FAME_INPUT_BERECHTIGUNG.INPUT_ID)

				.from(FAME_INPUT_BERECHTIGUNG)

				.leftJoin(FAME_ROLLEN).on(FAME_INPUT_BERECHTIGUNG.ROLLE_ID.eq(FAME_ROLLEN.ID))

				.leftJoin(FAME_INPUT).on(FAME_INPUT_BERECHTIGUNG.INPUT_ID.eq(FAME_INPUT.ID))

				.leftJoin(FAME_WORKFLOW_INSTANCE).on(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.eq(FAME_INPUT.WORKFLOW_ID))

				.where(" false ");

		// Die Rollen als OR anhängen
		for (String rolle : rollen) {
			String roleName = rolle;
			if (rolle.charAt(rolle.length() - 1) == ')') {
				roleName = rolle.substring(0, rolle.indexOf('('));
				String roleParam = rolle.substring(rolle.indexOf('(') + 1, rolle.length() - 1);
				subselectBuilder
						.or(FAME_ROLLEN.NAME.eq(roleName).and(FAME_WORKFLOW_INSTANCE.RECHTE_PARAMETER.eq(roleParam)));
			} else {
				subselectBuilder.or(FAME_ROLLEN.NAME.eq(roleName));
			}
		}
		Table<Record> subselect = subselectBuilder.groupBy(FAME_INPUT_BERECHTIGUNG.INPUT_ID).asTable();

		DataType<InputRechteEnum> rechteType = SQLDataType.VARCHAR.asConvertedDataType(new InputRechteEnumConverter());

		return context.select(FAME_FILE.fields()).select(FAME_VALUE.EINGABE.as("filename"))
				.select(FAME_VALUE.VORGANG_ID).select(subselect.field("recht", rechteType))

				.from(FAME_FILE)

				.leftJoin(FAME_VALUE).on(FAME_FILE.VALUE_ID.eq(FAME_VALUE.ID))

				.leftJoin(subselect).on(FAME_VALUE.INPUT_ID.equal((Field<Long>) subselect.field("input_id")))

				.where(FAME_FILE.VALUE_ID.eq(valueId))

				.fetchAnyInto(ExtendedFileMitRecht.class);
	}

	/**
	 * Es wird nur ein Update gemacht, wenn auch wirklich das Objekt anders ist.
	 * Sonst soll es abgefangen werden, damit die Tabelle sich nicht aufbläht.
	 */
	@Override
	public void update(File object) {

		if (object == null) {
			return;
		}

		File old;
		try {
			old = fetchById(object.getId());
		} catch (NoSuchElementException e) {
			return;
		}

		// Wenn old == null wird eh nichts geupdated
		if (old == null || object.equals(old)) {
			logger.debug("Datei unverändert: " + object.getId());
			return;
		}

		logger.debug("Datei aktualisiert: " + object.getId());
		super.update(object);
	}

}
