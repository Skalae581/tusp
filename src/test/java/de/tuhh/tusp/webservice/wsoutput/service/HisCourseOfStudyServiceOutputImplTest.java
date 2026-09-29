package de.tuhh.tusp.webservice.wsoutput.service;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.webservice.wsoutput.dao.HisCourseOfStudyHelperDao;

@RunWith(MockitoJUnitRunner.class)

public class HisCourseOfStudyServiceOutputImplTest {

	@InjectMocks
	private HisCourseOfStudyServiceOutputImpl service;

	@Mock
	private HisCourseOfStudyHelperDao dao;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void findUniquenameByUniHHAstatTest() throws Exception {

		service.findUniquenameByUniHHAstat("test");
		verify(dao).findUniquenameByUniHHAstat("test");
	}

	@Test
	public void findUniquenameForMobilityOnlineTest() throws Exception {

		service.findUniquenameForMobilityOnline();
		verify(dao).findUniquenameForMobilityOnline();
	}
}
