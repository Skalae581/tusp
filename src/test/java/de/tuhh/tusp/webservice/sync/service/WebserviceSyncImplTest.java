package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.utils.BaseDao;
import de.tuhh.tusp.webservice.sync.pojo.BasePojo;
import lombok.Data;
import lombok.NonNull;

@RunWith(MockitoJUnitRunner.class)
public class WebserviceSyncImplTest {

	@Data
	class Dummy implements BasePojo {

		@NonNull
		private Long id;
		@NonNull
		private Boolean valid;
		@NonNull
		private String property;

		@Override
		public Boolean isValid() {

			return getValid();
		}
	}

	class SyncService extends WebserviceSyncImpl {

		@Override
		protected void insertOrUpdateData(	List<BasePojo> serviceData, BaseDao<?> dao, List<BasePojo> cacheList,
											String daoClass) {

			super.insertOrUpdateData(serviceData, dao, cacheList, daoClass);
		}

		@Override
		protected void invalidateExpiredData(	List<BasePojo> serviceData, BaseDao<?> dao, List<BasePojo> cacheList,
												String daoClass) {

			super.invalidateExpiredData(serviceData, dao, cacheList, daoClass);
		}

		@Override
		public int orderNum() {

			return 4;
		}

		@Override
		public void sync() {}
	}

	@Mock
	private BaseDao<Dummy> dao;

	private SyncService service = new SyncService();

	private ArrayList<BasePojo> serviceData;

	private ArrayList<BasePojo> cacheList;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		serviceData = new ArrayList<>();
		serviceData.add(new Dummy(21L, true, "bleibt gleich"));
		serviceData.add(new Dummy(25L, true, "wird erzeugt"));
		serviceData.add(new Dummy(24L, true, "wird aktualisiert"));
		serviceData.add(new Dummy(22L, true, "muss valid werden"));

		cacheList = new ArrayList<>();
		cacheList.add(new Dummy(21L, true, "bleibt gleich"));
		cacheList.add(new Dummy(24L, true, "muss aktualisiert werden"));
		cacheList.add(new Dummy(23L, true, "muss invalid werden"));
		cacheList.add(new Dummy(22L, false, "muss valid werden"));
	}

	@Test
	public void insertOrUpdateTest() {

		service.insertOrUpdateData(serviceData, dao, cacheList, "Dummy");

		ArgumentCaptor<Dummy> insertCaptor = ArgumentCaptor.forClass(Dummy.class);
		verify(dao, times(1)).insert(insertCaptor.capture());
		assertEquals(Long.valueOf(25L), insertCaptor.getValue().getId());
		assertTrue(insertCaptor.getValue().isValid());
		assertEquals("wird erzeugt", insertCaptor.getValue().getProperty());

		ArgumentCaptor<Dummy> updateCaptor = ArgumentCaptor.forClass(Dummy.class);
		verify(dao, times(2)).update(updateCaptor.capture());
		assertEquals(Long.valueOf(24L), updateCaptor.getAllValues().get(0).getId());
		assertTrue(updateCaptor.getAllValues().get(0).isValid());
		assertEquals("wird aktualisiert", updateCaptor.getAllValues().get(0).getProperty());

		assertEquals(Long.valueOf(22L), updateCaptor.getAllValues().get(1).getId());
		assertTrue(updateCaptor.getAllValues().get(1).isValid());
		assertEquals("muss valid werden", updateCaptor.getAllValues().get(1).getProperty());

	}

	@Test
	public void invalidateExpiredDataTest() {

		service.invalidateExpiredData(serviceData, dao, cacheList, "Dummy");

		ArgumentCaptor<Dummy> updateCaptor = ArgumentCaptor.forClass(Dummy.class);
		verify(dao, times(1)).update(updateCaptor.capture());
		assertEquals(Long.valueOf(23L), updateCaptor.getAllValues().get(0).getId());
		assertFalse(updateCaptor.getAllValues().get(0).isValid());
		assertEquals("muss invalid werden", updateCaptor.getAllValues().get(0).getProperty());
	}
}
