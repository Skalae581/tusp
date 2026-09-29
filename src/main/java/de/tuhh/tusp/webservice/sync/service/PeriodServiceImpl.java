package de.tuhh.tusp.webservice.sync.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.his.ws.periodservice.PeriodInfo;
import de.his.ws.periodservice.PeriodInfosType;
import de.his.ws.periodservice.PeriodServicePortType;
import de.his.ws.periodservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.sync.dao.PeriodDao;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import de.tuhh.tusp.webservice.sync.pojo.Period;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Component

public class PeriodServiceImpl extends WebserviceSyncImpl implements PeriodService {

	private static final int ORDER_NUM = 3;

	@Autowired
	protected PeriodDao dao;

	@Autowired
	private WebserviceFactory webserviceFactory;

	@Override
	public Long getCurrentSemesterzeitraumId() {

		return dao.findSemesterzeitraumForDate(new Date()).getId();
	}

	@Override
	public Long getNextSemesterzeitraumId() {

		Date currentEnde = dao.findSemesterzeitraumForDate(new Date()).getEnddate();

		Calendar nextDayAfterEnddate = Calendar.getInstance();
		nextDayAfterEnddate.setTime(currentEnde);
		nextDayAfterEnddate.add(Calendar.DATE, 1);

		return dao.findSemesterzeitraumForDate(nextDayAfterEnddate.getTime()).getId();
	}

	@Override
	public int orderNum() {

		return ORDER_NUM;
	}

	@Override
	public void sync() {

		PeriodInfosType period;
		try {
			PeriodServicePortType port =
					webserviceFactory.getWebservicePort(de.his.ws.periodservice.PeriodService.class);
			period = port.findPeriod70("", "", "", "", "", "", "", "", "", "");
		}
		catch (ServiceFault_Exception e) {
			throw new RuntimeException("Konnte Periods nicht abrufen.", e);
		}

		List<BasePojo> serviceData = new ArrayList<>();
		for (PeriodInfo pInfo : period.getPeriodInfo()) {

			Period p = new Period();
			p.setId(pInfo.getId());
			p.setUniquename(pInfo.getUniquename());
			p.setStartdate(pInfo.getStartdate().toGregorianCalendar().getTime());
			p.setEnddate(pInfo.getEnddate().toGregorianCalendar().getTime());
			p.setValid(true);

			serviceData.add(p);
		}

		syncData(serviceData, dao);

	}

}
