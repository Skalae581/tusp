package de.tuhh.tusp.global.dao;

import static org.jooq.generated.Tables.GLOBAL_JOBS;
import static org.jooq.impl.DSL.coalesce;
import static org.jooq.impl.DSL.concat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.TableField;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.schedule.Status;

@Component
public class JobDaoImpl extends BaseDaoImpl<Job> implements JobDao {

	@Value("${url}")
	private String url;

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	@Value("${driverClass}")
	private String driverClassName;

	@Value("${dialect}")
	private String dialect;

	/**
	 * Construct
	 */
	public JobDaoImpl() {

		super(Job.class, GLOBAL_JOBS, GLOBAL_JOBS.ID);
	}

	@Override
	public void appendLog(Long jobId, String message) {

		context	.update(table).set(GLOBAL_JOBS.LOG, concat(coalesce(GLOBAL_JOBS.LOG, ""), message))
				.where(GLOBAL_JOBS.ID.eq(jobId)).execute();
	}

	@Override
	public Job fetchByName(String name) {

		return context.select().from(table).where(GLOBAL_JOBS.NAME.equal(name)).fetchAnyInto(Job.class);
	}

	@Override
	public List<Job> findByStatus(Status status) {

		return context	.select().from(GLOBAL_JOBS).where(GLOBAL_JOBS.STATUS.eq(status.toString()))
						.orderBy(GLOBAL_JOBS.ID).fetchInto(Job.class);

	}

	@Override
	public List<Job> findByTimespan(Date start, Date ende) {

		LocalDateTime ts = CalendarUtils.toLocalDateTime(start);
		LocalDateTime te = CalendarUtils.toLocalDateTime(ende);

		return context	.select().from(table).where(GLOBAL_JOBS.START.between(ts).and(te))
						.or(GLOBAL_JOBS.ENDE.between(ts).and(te)).orderBy(GLOBAL_JOBS.ID, GLOBAL_JOBS.NAME)
						.fetchInto(Job.class);
	}

	@Override
	public void setRunning(Job job) {

		SQLDialect d = null;
		if ("postgres".equals(dialect)) {
			d = SQLDialect.POSTGRES;
		}
		else if ("h2".equals(dialect)) {
			d = SQLDialect.H2;
		}
		else if ("mariadb".equals(dialect)) {
			d = SQLDialect.MARIADB;
		}
		else if ("mysql".equals(dialect)) {
			d = SQLDialect.MYSQL;
		}
		else if ("sqlite".equals(dialect)) {
			d = SQLDialect.SQLITE;
		}
		else if ("derby".equals(dialect)) {
			d = SQLDialect.DERBY;
		}

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			DSLContext create = DSL.using(conn, d);
			update(job, create);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Aktualisiert alles Spalten außer des Logs
	 */
	@Override
	public void update(Job job) {

		UpdatableRecord<?> record = (UpdatableRecord<?>) context.newRecord(table, job);
		List<TableField<?, ?>> fields = new ArrayList<>();
		for (Field<?> field : table.fields()) {
			fields.add((TableField<?, ?>) field);
		}
		fields.remove(GLOBAL_JOBS.LOG);
		record.update(fields.toArray(new TableField<?, ?>[fields.size()]));
	}
}
