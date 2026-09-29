package de.tuhh.tusp.webservice.wsoutput.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.webservice.wsoutput.dao.TuHHStgDao;

@SuppressWarnings("unused")
@RunWith(MockitoJUnitRunner.class)
public class TuhhStgServiceImplTest {

	@InjectMocks
	private TuHHStgServiceImpl service;

	@Mock
	private TuHHStgDao dao;

	@Test
	public void getByAstatUniTest() {

		service.getStgByAstatUni("Test");

		verify(dao).getStgByAstatUni("Test");
	}
}
