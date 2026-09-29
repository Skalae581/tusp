package de.tuhh.tusp.webservice.wsoutput.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.his.ws.courseofstudyservice.CourseOfStudyService;
import de.his.ws.courseofstudyservice.CourseOfStudyServicePortType;
import de.his.ws.courseofstudyservice.SearchedCos;
import de.his.ws.courseofstudyservice.SearchedCosType;
import de.his.ws.courseofstudyservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.sync.dao.DegreeDao;
import de.tuhh.tusp.webservice.sync.dao.SubjectDao;
import de.tuhh.tusp.webservice.sync.pojo.Degree;
import de.tuhh.tusp.webservice.sync.pojo.Subject;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;
import de.tuhh.tusp.webservice.wsoutput.service.TuHHStgService;

@RunWith(MockitoJUnitRunner.class)
public class HisCourseOfStudyHelperDaoImplTest {

	private static final Long subjectLid = 25L;
	private static final Long degreeLid = 23L;

	@InjectMocks
	private HisCourseOfStudyHelperDaoImpl dao;

	@Mock
	private WebserviceFactory webserviceFactory;

	@Mock
	private CourseOfStudyServicePortType cosService;

	@Mock
	private DegreeDao degreeDao;

	@Mock
	private SubjectDao subjectDao;

	@Mock
	private ConfigService configService;

	@Mock
	private TuHHStgService stgService;

	private Subject subject;
	private Degree degree;

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);

		subject = new Subject();
		subject.setLid(subjectLid);
		when(subjectDao.fetchByUniquename(null)).thenAnswer(i -> subject);

		degree = new Degree();
		degree.setLid(degreeLid);
		when(degreeDao.fetchByUniquename(null)).thenAnswer(i -> degree);

		when(webserviceFactory.getWebservicePort(CourseOfStudyService.class)).thenAnswer(i -> cosService);
	}

	@Test
	public void findUniquenameByUniHHAstat_withNull() throws Exception {

		assertNull(dao.findUniquenameByUniHHAstat(null));
	}

	@Test
	public void findUniquenameByUniHHAstat_withSuccess() throws Exception {

		when(stgService.getStgByAstatUni("12345")).thenAnswer(i -> "stgwert");
		Subject subject = new Subject();
		subject.setLid(5L);
		when(subjectDao.fetchByUniquename("stgwert")).thenAnswer(i -> subject);

		SearchedCosType answer = new SearchedCosType();

		SearchedCos cos1 = new SearchedCos();
		cos1.setUniquename("84|004|CHN|ohne|H|2013|-|P|V|1|");
		answer.getSearchedCos().add(cos1);

		when(cosService.findCourseOfStudy("", "", "5", "", "", "")).thenAnswer(i -> answer);

		assertEquals(cos1.getUniquename(), dao.findUniquenameByUniHHAstat("12345"));
	}

	@Test
	public void findUniquenameByUniHHAstat_withUndefinedSubject() throws Exception {

		assertNull(dao.findUniquenameByUniHHAstat("12345"));
	}

	@Test
	public void findUniquenameByUniHHAstat_withUndefinedSubject2() throws Exception {

		when(stgService.getStgByAstatUni("12345")).thenAnswer(i -> "stgwert");

		assertNull(dao.findUniquenameByUniHHAstat("12345"));
	}

	@Test
	public void findUniqunameBySubjectLid_withEmptyAnswer() throws Exception {

		when(cosService.findCourseOfStudy("", "", "5", "", "", "")).thenAnswer(i -> new SearchedCosType());

		assertNull(dao.findUniqunameBySubjectLid(5L));
	}

	@Test
	public void findUniqunameBySubjectLid_withNull() throws Exception {

		assertNull(dao.findUniqunameBySubjectLid(null));
	}

	@Test(expected = NumberFormatException.class)
	public void findUniqunameBySubjectLid_withNumberFormatException() throws Exception {

		SearchedCosType answer = new SearchedCosType();

		SearchedCos cos1 = new SearchedCos();
		cos1.setUniquename("84|004|CHN|ohne|H|PO-Version 2013|-|P|V|1|");
		answer.getSearchedCos().add(cos1);

		when(cosService.findCourseOfStudy("", "", "5", "", "", "")).thenAnswer(i -> answer);

		assertNull(dao.findUniqunameBySubjectLid(5L));
	}

	@Test(expected = ServiceFault.class)
	public void findUniqunameBySubjectLid_withServiceFault() throws Exception {

		when(cosService.findCourseOfStudy("", "", "5", "", "", "")).thenThrow(new ServiceFault_Exception(null, null));

		dao.findUniqunameBySubjectLid(5L);
	}

	@Test
	public void findUniqunameBySubjectLid_withSuccess() throws Exception {

		SearchedCosType answer = new SearchedCosType();

		SearchedCos cos1 = new SearchedCos();
		cos1.setUniquename("84|004|CHN|ohne|H|2013|-|P|V|1|");
		answer.getSearchedCos().add(cos1);

		SearchedCos cos2 = new SearchedCos();
		cos2.setUniquename("84|004|CHN|ohne|H|2017|-|P|V|1|");
		answer.getSearchedCos().add(cos2);

		SearchedCos cos3 = new SearchedCos();
		cos3.setUniquename("84|004|CHN|ohne|H|2014|-|P|V|1|");
		answer.getSearchedCos().add(cos3);

		SearchedCos cos4 = new SearchedCos();
		cos4.setUniquename("84|004|CHN|ohne|H|2010|-|P|V|1|");
		answer.getSearchedCos().add(cos4);

		when(cosService.findCourseOfStudy("", "", "5", "", "", "")).thenAnswer(i -> answer);

		assertEquals(cos2.getUniquename(), dao.findUniqunameBySubjectLid(5L));
	}

	@Test(expected = IllegalStateException.class)
	public void findUniqunameForMobilityOnline_withEmptyAnswer() throws Exception {

		when(cosService.findCourseOfStudy(	"", "", subjectLid + "", degreeLid + "", "",
											"")).thenAnswer(i -> new SearchedCosType());

		dao.findUniquenameForMobilityOnline();
	}

	@Test(expected = ServiceFault.class)
	public void findUniqunameForMobilityOnline_withServiceFault() throws Exception {

		when(cosService.findCourseOfStudy(	"", "", subjectLid + "", degreeLid + "", "",
											"")).thenThrow(new ServiceFault_Exception(null, null));

		dao.findUniquenameForMobilityOnline();
	}

	@Test
	public void findUniqunameForMobilityOnline_withSuccess() throws Exception {

		SearchedCosType answer = new SearchedCosType();

		SearchedCos cos1 = new SearchedCos();
		cos1.setUniquename("84|004|CHN|ohne|H|2013|-|P|V|1|");
		answer.getSearchedCos().add(cos1);

		SearchedCos cos2 = new SearchedCos();
		cos2.setUniquename("84|004|CHN|ohne|H|2017|-|P|V|1|");
		answer.getSearchedCos().add(cos2);

		SearchedCos cos3 = new SearchedCos();
		cos3.setUniquename("84|004|CHN|ohne|H|2014|-|P|V|1|");
		answer.getSearchedCos().add(cos3);

		SearchedCos cos4 = new SearchedCos();
		cos4.setUniquename("84|004|CHN|ohne|H|2010|-|P|V|1|");
		answer.getSearchedCos().add(cos4);

		when(cosService.findCourseOfStudy("", "", subjectLid + "", degreeLid + "", "", "")).thenAnswer(i -> answer);

		assertEquals(cos2.getUniquename(), dao.findUniquenameForMobilityOnline());
	}

}
