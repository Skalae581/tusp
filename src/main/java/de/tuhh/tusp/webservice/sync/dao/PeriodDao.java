package de.tuhh.tusp.webservice.sync.dao;

import java.util.Date;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.Period;

public interface PeriodDao extends BaseDao<Period> {

	/**
	 * Holt eine Semesterzeitraum zu einem Datum
	 *
	 * @param date
	 * @return die Period
	 */
	Period findSemesterzeitraumForDate(Date date);
}
