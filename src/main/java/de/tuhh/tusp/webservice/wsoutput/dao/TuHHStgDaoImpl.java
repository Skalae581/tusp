package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.jooq.generated.Tables.*;

import org.jooq.Record1;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.utils.BaseDaoImpl;
import de.tuhh.tusp.webservice.wsoutput.pojo.TuHHStg;

@Component
public class TuHHStgDaoImpl extends BaseDaoImpl<TuHHStg> implements TuHHStgDao {

	/**
	 * Erstellt eine Instanz
	 */
	public TuHHStgDaoImpl() {
		super(TuHHStg.class, WEBSERVICE_GWLEHRER_STG, WEBSERVICE_GWLEHRER_STG.ID);
	}

	@Override
	public String getStgByAstatUni(String astatUni) {

		Record1<String> result = context.select(WEBSERVICE_GWLEHRER_STG.STG).from(WEBSERVICE_GWLEHRER_STG)
										.where(WEBSERVICE_GWLEHRER_STG.ASTAT_UNIHH.eq(astatUni)).fetchAny();

		if (result == null) {
			return null;
		}

		return result.value1();

	}

}
