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

import de.his.ws.orgunitservice.OrgUnitChildren;
import de.his.ws.orgunitservice.OrgUnitService;
import de.his.ws.orgunitservice.OrgUnitServicePortType;
import de.his.ws.orgunitservice.SearchedOrgunit60;
import de.his.ws.orgunitservice.SearchedOrgunitsType;
import de.his.ws.orgunitservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.sync.dao.OrgUnitDao;
import de.tuhh.tusp.webservice.sync.pojo.OrgUnit;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class OrgUnitServiceImplTest {

	@InjectMocks
	private OrgUnitServiceImpl service;

	@Mock
	private OrgUnitDao dao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private OrgUnitServicePortType orgunitService;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
		when(factory.getWebservicePort(OrgUnitService.class)).thenAnswer(i -> orgunitService);
	}

	@Test
	public void fetchByShorttextDe() throws Exception {

		service.fetchByShorttextDe("test");
		verify(dao).fetchByShorttextDe("test");
	}

	@Test
	public void fetchByUniquename() throws Exception {

		service.fetchByUniquename("test");
		verify(dao).fetchByUniquename("test");
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

	@Test
	public void syncTest() throws ServiceFault_Exception {

		SearchedOrgunit60 searchedOu = new SearchedOrgunit60();
		searchedOu.setDefaulttext("setDefaulttext");
		searchedOu.setId(2L);
		searchedOu.setLid(4L);
		searchedOu.setParentLid(5L);
		searchedOu.setUniquename("setUniquename");
		SearchedOrgunitsType searched = new SearchedOrgunitsType();
		searched.getSearchedOrgunit().add(searchedOu);

		when(orgunitService.findOrgUnit61(	any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
											any())).thenAnswer(i -> searched);

		OrgUnitChildren de = new OrgUnitChildren();
		de.setDisplaytext("setDisplaytext");
		de.setShorttext("setShorttext");
		de.setLongtext("setLongtext");
		when(orgunitService.readOrgunitById(2L)).thenAnswer(i -> de);

		when(dao.findAll()).thenAnswer(i -> new ArrayList<>());

		ArgumentCaptor<OrgUnit> argumentCaptor = ArgumentCaptor.forClass(OrgUnit.class);
		service.sync();
		verify(dao, times(1)).insert(argumentCaptor.capture());
		OrgUnit orgUnit = argumentCaptor.getValue();

		assertEquals(Long.valueOf(2L), orgUnit.getId());
		assertEquals(searchedOu.getUniquename(), orgUnit.getUniquename());
		assertEquals(de.getParentId(), orgUnit.getParentId());
		assertEquals(de.getDisplaytext(), orgUnit.getDisplaytextDe());
		assertEquals(de.getShorttext(), orgUnit.getShorttextDe());
		assertEquals(de.getLongtext(), orgUnit.getLongtextDe());
		assertEquals(de.getType(), orgUnit.getType());
	}

	@Test(expected = RuntimeException.class)
	public void syncTest_withServiceFault() throws ServiceFault_Exception {

		when(orgunitService.findOrgUnit61(	any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any(),
											any())).thenThrow(new ServiceFault_Exception(null, null));

		service.sync();
	}
}
