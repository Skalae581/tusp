package de.tuhh.tusp.utils;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.UpdatableRecord;
import org.jooq.impl.TableImpl;
import org.jooq.impl.TableRecordImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author rztjm Abstrakte Klasse die Basis-Methoden (CRUD) definiert. Es
 *         verwendet jooq um die Statements typsicher mit Objekten gestalten zu
 *         können
 * @param <T> Eine beliebige Pojo-Klasse
 */
@SuppressWarnings("rawtypes")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	private static Logger logger = LogManager.getLogger(BaseDao.class);

	protected final Class<T> typeParameterClass;
	protected final TableImpl<?> table;
	protected final Field idColumn;
	protected final boolean idColumnIsAutoincrement;

	@Autowired
	protected DSLContext context;

	/**
	 * Setzt die wichtigsten Parameter der Klasse
	 *
	 * @param typeParameterClass POJO-Klasse
	 * @param table              DB-Tabelle die gemappt werden soll
	 * @param idColumn           Primär-Schlüssel
	 *
	 */
	public BaseDaoImpl(Class<T> typeParameterClass, TableImpl<?> table, Field idColumn) {

		this(typeParameterClass, table, idColumn, true);
	}

	/**
	 * Setzt die wichtigsten Parameter der Klasse
	 *
	 * @param typeParameterClass      POJO-Klasse
	 * @param table                   DB-Tabelle die gemappt werden soll
	 * @param idColumn                Primär-Schlüssel
	 * @param idColumnIsAutoincrement Gibt an, ob die ID von der Datenbank bei einem
	 *                                Insert erzeugt werden soll oder nicht
	 */
	public BaseDaoImpl(Class<T> typeParameterClass, TableImpl<?> table, Field idColumn,
			boolean idColumnIsAutoincrement) {

		this.typeParameterClass = typeParameterClass;
		this.table = table;
		this.idColumn = idColumn;
		this.idColumnIsAutoincrement = idColumnIsAutoincrement;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int delete(Long id) {

		return context.delete(table).where(idColumn.equal(id)).execute();
	}

	@Override
	public void deleteAll() {

		context.delete(table).execute();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T fetchById(Long id) {

		T result = context.select().from(table).where(idColumn.equal(id)).fetchAnyInto(typeParameterClass);
		if (result == null) {
			logger.debug("RESULT FROM: fetchById(id=" + id + "); RESULT: " + result);
			throw new NoSuchElementException("No object with ID " + id + " found!");
		}
		String resultMsg = result.toString();
		if (resultMsg.length() > 1024) {
			resultMsg = resultMsg.substring(0, 1024) + "...";
		}
		logger.debug("RESULT FROM: fetchById(id=" + id + "); RESULT: " + resultMsg);

		return result;
	}

	@Override
	public List<T> findAll() {

		return context.selectFrom(table).fetchInto(typeParameterClass);
	}

	@Override
	public int getCount() {

		return context.fetchCount(context.selectFrom(table));
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public Long insert(T object) {

		TableRecordImpl record = (TableRecordImpl) context.newRecord(table, object);
		Field<?>[] fields = record.fields();

		if (idColumnIsAutoincrement) {
			fields = (Field<?>[]) ArrayUtils.removeElement(fields, idColumn);
		}

		try {
			record.insert(fields);
		} catch (Exception e) {
			logger.error("Insert faild for Object: " + object, e);
			throw e;
		}

		Object id = record.getValue(idColumn);

		if (id instanceof Integer) {
			return ((Integer) id).longValue();
		}
		return (Long) id;
	}

	@Override
	public void update(T object) {

		update(object, context);
	}

	/**
	 * Update Methode kann mit verschiedenen Contexten aufgerufen werden
	 *
	 * @param object
	 * @param ctx
	 */
	protected void update(T object, DSLContext ctx) {

		try {
			UpdatableRecord<?> record = (UpdatableRecord<?>) context.newRecord(table, object);
			record.update();
		} catch (ClassCastException e) {
			String message = "Object vom Typ " + object.getClass().getSimpleName()
					+ " ist nicht Updatable. Vermutlich fehlt der Primary-Key in der Datenbanktabelle!";
			logger.error(message, e);
			throw new RuntimeException(message, e);
		} catch (Exception e) {
			logger.error("Update faild for Object: " + object, e);
			throw e;
		}
	}
}
