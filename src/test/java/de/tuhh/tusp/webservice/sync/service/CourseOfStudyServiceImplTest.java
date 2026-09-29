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
import org.springframework.context.ApplicationEventPublisher;

import de.his.ws.courseofstudyservice.CourseOfStudyService;
import de.his.ws.courseofstudyservice.CourseOfStudyServicePortType;
import de.his.ws.courseofstudyservice.SearchedCos;
import de.his.ws.courseofstudyservice.SearchedCosType;
import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.webservice.sync.dao.CourseOfStudyDao;
import de.tuhh.tusp.webservice.sync.main.event.StudiengangRefreshEvent;
import de.tuhh.tusp.webservice.sync.pojo.CourseOfStudy;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class CourseOfStudyServiceImplTest {

	@InjectMocks
	private CourseOfStudyServiceImpl service;

	@Mock
	private CourseOfStudyDao dao;

	@Mock
	private WebserviceFactory factory;

	@Mock
	private CourseOfStudyServicePortType courseOfStudyService;

	@Mock
	private ApplicationEventPublisher publisher;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);
		when(factory.getWebservicePort(CourseOfStudyService.class)).thenAnswer(i -> courseOfStudyService);
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

	@Test
	public void sync_allNulls() throws ServiceFault_Exception {

		// find vorbereiten
		SearchedCos searchCos = new SearchedCos();
		searchCos.setCourseofstudyId(15L);
		searchCos.setCourseofstudyLid(16L);
		SearchedCosType list = new SearchedCosType();
		list.getSearchedCos().add(searchCos);
		when(courseOfStudyService.findCourseOfStudy(any(), any(), any(), any(), any(), any())).then(i -> list);

		// de Vorbereiten
		de.his.ws.courseofstudyservice.CourseOfStudy hisCos = new de.his.ws.courseofstudyservice.CourseOfStudy();
		hisCos.setId(searchCos.getCourseofstudyId());
		hisCos.setLid(searchCos.getCourseofstudyLid());
		when(courseOfStudyService.getCourseOfStudyById(15L)).thenAnswer(i -> hisCos);

		// Dao liefert leere Liste
		when(dao.findAll()).thenAnswer(i -> new ArrayList<>());

		ArgumentCaptor<CourseOfStudy> argumentCaptor = ArgumentCaptor.forClass(CourseOfStudy.class);
		service.sync();
		verify(dao, times(1)).insert(argumentCaptor.capture());
		CourseOfStudy courseOfStudy = argumentCaptor.getValue();

		assertEquals(Long.valueOf(15L), courseOfStudy.getId());
		assertEquals(Long.valueOf(16L), courseOfStudy.getLid());
		assertEquals(searchCos.getUniquename(), courseOfStudy.getUniquename());

		assertEquals(hisCos.getDefaulttext(), courseOfStudy.getDefaulttextDe());
		assertEquals(hisCos.getShorttext(), courseOfStudy.getShorttextDe());
		assertEquals(hisCos.getLongtext(), courseOfStudy.getLongtextDe());

		// TODO en
		assertEquals(hisCos.getDefaulttext(), courseOfStudy.getDefaulttextEn());
		assertEquals(hisCos.getShorttext(), courseOfStudy.getShorttextEn());
		assertEquals(hisCos.getLongtext(), courseOfStudy.getLongtextEn());

		verify(publisher, times(1)).publishEvent(any(StudiengangRefreshEvent.class));
	}

	@Test
	public void syncTest() throws ServiceFault_Exception {

		// find vorbereiten
		SearchedCos searchCos = new SearchedCos();
		searchCos.setCourseofstudyId(15L);
		searchCos.setCourseofstudyLid(16L);
		searchCos.setDefaulttext("setDefaulttext");
		searchCos.setUniquename("setUniquename");
		SearchedCosType list = new SearchedCosType();
		list.getSearchedCos().add(searchCos);
		when(courseOfStudyService.findCourseOfStudy(any(), any(), any(), any(), any(), any())).then(i -> list);

		// de Vorbereiten
		de.his.ws.courseofstudyservice.CourseOfStudy hisCos = new de.his.ws.courseofstudyservice.CourseOfStudy();
		hisCos.setId(searchCos.getCourseofstudyId());
		hisCos.setLid(searchCos.getCourseofstudyLid());
		hisCos.setUniquename(searchCos.getUniquename());
		hisCos.setDefaulttext("setDefaulttext");
		hisCos.setShorttext("setShorttext");
		hisCos.setLongtext("setLongtext");
		when(courseOfStudyService.getCourseOfStudyById(15L)).thenAnswer(i -> hisCos);

		// Dao liefert leere Liste
		when(dao.findAll()).thenAnswer(i -> new ArrayList<>());

		ArgumentCaptor<CourseOfStudy> argumentCaptor = ArgumentCaptor.forClass(CourseOfStudy.class);
		service.sync();
		verify(dao, times(1)).insert(argumentCaptor.capture());
		CourseOfStudy courseOfStudy = argumentCaptor.getValue();

		assertEquals(Long.valueOf(15L), courseOfStudy.getId());
		assertEquals(Long.valueOf(16L), courseOfStudy.getLid());
		assertEquals(searchCos.getUniquename(), courseOfStudy.getUniquename());

		assertEquals(hisCos.getDefaulttext(), courseOfStudy.getDefaulttextDe());
		assertEquals(hisCos.getShorttext(), courseOfStudy.getShorttextDe());
		assertEquals(hisCos.getLongtext(), courseOfStudy.getLongtextDe());

		// TODO en
		assertEquals(hisCos.getDefaulttext(), courseOfStudy.getDefaulttextEn());
		assertEquals(hisCos.getShorttext(), courseOfStudy.getShorttextEn());
		assertEquals(hisCos.getLongtext(), courseOfStudy.getLongtextEn());

		verify(publisher, times(1)).publishEvent(any(StudiengangRefreshEvent.class));;
	}

	@Test(expected = RuntimeException.class)
	public void syncTest_withServiceFault() throws ServiceFault_Exception {

		when(courseOfStudyService.findCourseOfStudy(any(), any(), any(), any(), any(),
													any())).thenThrow(new ServiceFault_Exception(null, null));

		service.sync();
	}
}
