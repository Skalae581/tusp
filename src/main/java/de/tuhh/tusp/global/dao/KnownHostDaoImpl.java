package de.tuhh.tusp.global.dao;

import static org.jooq.generated.Tables.*;

import java.util.List;

import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.pojo.KnownHost;
import de.tuhh.tusp.utils.BaseDaoImpl;

@Component
public class KnownHostDaoImpl extends BaseDaoImpl<KnownHost> implements KnownHostDao {

	/**
	 * Erstellt eien Instanz
	 */
	public KnownHostDaoImpl() {
		super(KnownHost.class, GLOBAL_KNOWN_HOST, GLOBAL_KNOWN_HOST.ID);
	}

	@Override
	public List<KnownHost> findValid() {

		return context.select().from(table).where(GLOBAL_KNOWN_HOST.VALID.equal(true)).fetchInto(KnownHost.class);
	}

}
