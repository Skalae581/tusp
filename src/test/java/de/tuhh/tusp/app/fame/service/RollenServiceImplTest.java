package de.tuhh.tusp.app.fame.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.RollenDao;
import de.tuhh.tusp.app.fame.pojo.Rollen;
import de.tuhh.tusp.global.dao.ConfigDao;
import de.tuhh.tusp.webservice.sync.dao.RightsAndRolesDao;
import de.tuhh.tusp.webservice.sync.pojo.RightsAndRoles;

@RunWith(MockitoJUnitRunner.class)
public class RollenServiceImplTest {

	@InjectMocks
	private RollenServiceImpl service;

	@Mock
	private RollenDao dao;

	@Mock
	private RightsAndRolesDao rightsDao;

	@Mock
	private ConfigDao config;

	@Test
	public void aktualisiereTest() {

		Rollen r1 = new Rollen();
		r1.setId(5L);
		r1.setName("ROLE_SOLL_ERHALTEN_BLEIBEN");

		Rollen r2 = new Rollen();
		r2.setId(6L);
		r2.setName("soll_gelöscht_werden");

		List<Rollen> fameAll = new ArrayList<>();
		fameAll.add(r1);
		fameAll.add(r2);

		when(dao.findAll()).thenAnswer(a -> fameAll);

		RightsAndRoles rar1 = new RightsAndRoles();
		rar1.setRoleId(5L);
		rar1.setUniquename("soll_erhalten_bleiben");

		RightsAndRoles rar2 = new RightsAndRoles();
		rar2.setRoleId(2L);
		rar2.setUniquename("soll_erstellt_werden");

		List<RightsAndRoles> globalAll = new ArrayList<>();
		globalAll.add(rar1);
		globalAll.add(rar2);

		when(rightsDao.findAll()).thenAnswer(a -> globalAll);

		service.aktualisiere();

		ArgumentCaptor<Long> deleteCaptor = ArgumentCaptor.forClass(Long.class);
		verify(dao, times(1)).delete(deleteCaptor.capture());
		assertEquals(Long.valueOf(6L), deleteCaptor.getValue());

		ArgumentCaptor<Rollen> insertCaptor = ArgumentCaptor.forClass(Rollen.class);
		verify(dao, times(1)).insert(insertCaptor.capture());
		assertEquals("ROLE_SOLL_ERSTELLT_WERDEN", insertCaptor.getValue().getName());
	}

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void findAllTest() {

		List<Rollen> list = new ArrayList<>();
		list.add(new Rollen());
		when(dao.findAll()).thenAnswer(i -> list);
		List<Rollen> result = service.findAll();
		verify(dao, times(1)).findAll();
		assertEquals(list, result);
	}
}
