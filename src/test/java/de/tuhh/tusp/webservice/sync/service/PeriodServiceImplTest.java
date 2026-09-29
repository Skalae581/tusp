package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.periodservice.PeriodInfo;
import de.his.ws.periodservice.PeriodInfosType;
import de.his.ws.periodservice.PeriodService;
import de.his.ws.periodservice.PeriodServicePortType;
import de.his.ws.periodservice.ServiceFault_Exception;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.webservice.sync.dao.PeriodDao;
import de.tuhh.tusp.webservice.sync.pojo.Period;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class PeriodServiceImplTest {

	@InjectMocks
	private PeriodServiceImpl service;

	@Mock
	private PeriodDao dao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private PeriodServicePortType periodService;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
		when(factory.getWebservicePort(PeriodService.class)).thenAnswer(i -> periodService);
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

	@Test
	public void syncTest() throws ServiceFault_Exception {

		PeriodInfo hisPeriod = new PeriodInfo();
		hisPeriod.setUniquename("setUniquename");
		hisPeriod.setId(3L);
		hisPeriod.setStartdate(CalendarUtils.stringToXMLGregorianCalendar(	"01.01.1970",
																			CalendarUtils.DATEFORMAT_GERMAN));
		hisPeriod.setEnddate(CalendarUtils.stringToXMLGregorianCalendar("21.12.2012", CalendarUtils.DATEFORMAT_GERMAN)); // end
																															// of
																															// the
																															// word
		PeriodInfosType periods = new PeriodInfosType();
		periods.getPeriodInfo().add(hisPeriod);
		when(periodService.findPeriod70(any(), any(), any(), any(), any(), any(), any(), any(), any(),
										any())).thenAnswer(i -> periods);

		when(dao.findAll()).thenAnswer(i -> new ArrayList<>());

		ArgumentCaptor<Period> argumentCaptor = ArgumentCaptor.forClass(Period.class);
		service.sync();
		verify(dao, times(1)).insert(argumentCaptor.capture());
		Period period = argumentCaptor.getValue();

		assertEquals(Long.valueOf(3L), period.getId());
		assertEquals(hisPeriod.getUniquename(), period.getUniquename());
		assertEquals("Thu Jan 01 00:00:00 CET 1970", period.getStartdate().toString());
		assertEquals("Fri Dec 21 00:00:00 CET 2012", period.getEnddate().toString());
	}

	@Test(expected = RuntimeException.class)
	public void syncTest_withServiceFault() throws ServiceFault_Exception {

		when(periodService.findPeriod70(any(), any(), any(), any(), any(), any(), any(), any(), any(),
										any())).thenThrow(new ServiceFault_Exception(null, null));

		service.sync();
	}
}
