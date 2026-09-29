package de.tuhh.tusp.app.fame.connector.applicantservice;

import static org.jooq.generated.Tables.FAME_FILE;
import static org.jooq.generated.Tables.FAME_LOG;
import static org.jooq.generated.Tables.FAME_VALUE;
import static org.jooq.generated.Tables.FAME_VORGANG;
import static org.jooq.generated.Tables.FAME_WORKFLOW;
import static org.jooq.generated.Tables.FAME_WORKFLOW_INSTANCE;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.app.SemesterCleanable;

@Component
public class ApplicantSemesterBereinigungImpl implements SemesterCleanable {

	@Autowired
	protected DSLContext context;

	@Override
	public Integer cleanSemeser(String semester) throws Exception {

		semester = semester.replace("FAME-", "");

		SelectConditionStep<Record1<Long>> vorgangIds =
				context	.select(FAME_VORGANG.ID).from(FAME_VORGANG)
						.where(FAME_VORGANG.CONNECTOR_KEY.like("%-%-" + semester));

		SelectConditionStep<Record1<Long>> valueIds =
				context.select(FAME_VALUE.ID).from(FAME_VALUE).where(FAME_VALUE.VORGANG_ID.in(vorgangIds));

		// Log löschen
		context.delete(FAME_LOG).where(FAME_LOG.VORGANG_ID.in(vorgangIds)).execute();

		// Files löschen
		context.delete(FAME_FILE).where(FAME_FILE.VALUE_ID.in(valueIds)).execute();

		// VALUES löschen
		context.delete(FAME_VALUE).where(FAME_VALUE.VORGANG_ID.in(vorgangIds)).execute();

		// Vorgänge löschen
		return context.delete(FAME_VORGANG).where(FAME_VORGANG.CONNECTOR_KEY.like("%-%-" + semester)).execute();
	}

	@Override
	public List<String> getSemester() throws NotImplementedException {

		List<String> sems = new ArrayList<>();

		Result<Record2<Long, String>> result = null;
		result = context.select(FAME_VORGANG.ID, FAME_VORGANG.CONNECTOR_KEY)

						.from(FAME_VORGANG)

						.join(FAME_WORKFLOW_INSTANCE)
						.on(FAME_WORKFLOW_INSTANCE.ID.equal(FAME_VORGANG.WORKFLOW_INSTANCE_ID))

						.join(FAME_WORKFLOW).on(FAME_WORKFLOW_INSTANCE.WORKFLOW_ID.equal(FAME_WORKFLOW.ID))

						.where(FAME_VORGANG.CONNECTOR_KEY.like("%-%-%semester 20__/__"))

						.and(FAME_WORKFLOW.CONNECTOR.equal(ApplicantConnectorImpl.class.getName()))

						.fetch();

		for (Record2<Long, String> vorgang : result) {
			String key = (String) vorgang.get(1);
			String[] parts = key.split("-");

			if (parts.length != 3) {
				throw new RuntimeException("Erwarte Muster \\d+-.*-20\\d\\d/\\d\\d habe aber:" + key);
			}

			String sem = "FAME-" + parts[2];

			if (!sems.contains(sem)) {
				sems.add(sem);
			}
		}

		return sems;
	}

}
