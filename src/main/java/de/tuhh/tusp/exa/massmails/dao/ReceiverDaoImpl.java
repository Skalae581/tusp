package de.tuhh.tusp.exa.massmails.dao;

import static org.jooq.impl.DSL.choose;
import static org.jooq.impl.DSL.count;

import java.util.List;

import org.jooq.generated.Tables;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.exa.massmails.pojo.Receiver;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class ReceiverDaoImpl extends BaseDaoImpl<Receiver> implements ReceiverDao {

	public ReceiverDaoImpl() {

		super(Receiver.class, Tables.RECEIVER, Tables.RECEIVER.ID);
	}

	@Override
	public List<Receiver> findAllByName(String name) {

		return context.select(Tables.RECEIVER.fields()).from(Tables.RECEIVER)
				.where(Tables.RECEIVER.VERTEILERNAME.eq(name)).fetchInto(Receiver.class);
	}

	@Override
	public List<Receiver> findAllByVerteilername(String name, Long semester) {

		return context.select(Tables.RECEIVER.fields()).from(Tables.RECEIVER)
				.where(Tables.RECEIVER.VERTEILERNAME.eq(name))
				.and(Tables.RECEIVER.SEMESTER.eq(semester).or(semester == 0l)).fetchInto(Receiver.class);
	}

	@Override
	public List<Verteiler> getVerteiler() {

		return context.select(Tables.RECEIVER.VERTEILERNAME.as("name"),

				Tables.RECEIVER.SEMESTER.as("semester"),

				Tables.RECEIVER.SEMESTER.div(10).as("year"),

				choose(Tables.RECEIVER.SEMESTER.mod(10)).when(1l, "SoSe").when(2l, "WiSe").otherwise("").as("sem"),

				count())

				.from(Tables.RECEIVER)

				.groupBy(Tables.RECEIVER.VERTEILERNAME, Tables.RECEIVER.SEMESTER)

				.orderBy(Tables.RECEIVER.SEMESTER, Tables.RECEIVER.VERTEILERNAME)

				.fetchInto(Verteiler.class);
	}
}
