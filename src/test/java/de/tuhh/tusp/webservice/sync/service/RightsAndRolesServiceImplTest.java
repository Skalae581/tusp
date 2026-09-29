package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.*;
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

import de.his.ws.rightsandrolesservice.RightsAndRolesServicePortType;
import de.his.ws.rightsandrolesservice.SearchedRole;
import de.his.ws.rightsandrolesservice.SearchedRolesType;
import de.his.ws.rightsandrolesservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class RightsAndRolesServiceImplTest {

	@InjectMocks
	private RightsAndRolesServiceImpl service;

	@Mock
	private RightsAndRolesDao dao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private RightsAndRolesServicePortType cosService;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
		when(factory.getWebservicePort(de.his.ws.rightsandrolesservice.RightsAndRolesService.class)).thenAnswer(i -> cosService);
	}

	@Test
	public void fetchByUniquenameTest() throws Exception {

		service.fetchByUniquename("test");
		verify(dao).fetchByUniqename("test");
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

	@Test
	public void syncTest() throws de.his.ws.rightsandrolesservice.ServiceFault_Exception {

		SearchedRole searchedRole = new SearchedRole();
		searchedRole.setDefaulttext("setDefaulttext");
		searchedRole.setLongtext("setLongtext");
		searchedRole.setShorttext("setShorttext");
		searchedRole.setUniquename("setUniquename");
		searchedRole.setRoleId(12L);
		// searchedRole.setValidFrom(CalendarUtils.stringToXMLGregorianCalendar("01.01.1980",
		// CalendarUtils.DATEFORMAT_GERMAN));
		// searchedRole.setValidTo(CalendarUtils.stringToXMLGregorianCalendar("01.01.1990",
		// CalendarUtils.DATEFORMAT_GERMAN));
		SearchedRolesType list = new SearchedRolesType();
		list.getSearchedRole().add(searchedRole);
		when(cosService.findRoles60("", "", "", "", "")).then(i -> list);

		when(dao.findAll()).thenAnswer(i -> new ArrayList<>());

		ArgumentCaptor<RightsAndRoles> argumentCaptor = ArgumentCaptor.forClass(RightsAndRoles.class);
		service.sync();
		verify(dao, times(1)).insert(argumentCaptor.capture());
		RightsAndRoles rar = argumentCaptor.getValue();

		assertEquals(Long.valueOf(12L), rar.getId());
		assertEquals(searchedRole.getShorttext(), rar.getShorttext());
		assertEquals(searchedRole.getDefaulttext(), rar.getDefaulttext());
		assertEquals(searchedRole.getLongtext(), rar.getLongtext());
		assertEquals(searchedRole.getUniquename(), rar.getUniquename());
	}

	@Test(expected = RuntimeException.class)
	public void syncTest_withServiceFault() throws Exception {

		when(cosService.findRoles60("", "", "", "", "")).thenThrow(new ServiceFault_Exception(null, null));

		service.sync();
	}
}
