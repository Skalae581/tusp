package de.tuhh.tusp.webservice.sync.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.i18n.LocaleContextHolder;

import de.tuhh.tusp.webservice.sync.dao.AcademicdegreeDao;
import de.tuhh.tusp.webservice.sync.pojo.Academicdegree;
import de.tuhh.tusp.webservice.sync.pojo.Person;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@RunWith(MockitoJUnitRunner.class)
public class AcademicdegreeServiceImplTest {

	@InjectMocks
	private AcademicdegreeServiceImpl service;

	@Mock
	private AcademicdegreeDao dao;

	@Mock
	private WebserviceFactory factory;

	@Test
	public void academicdegreeAsString_degreeNull() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);

		Person p = new Person();
		p.setAcademicdegreeId(null);
		p.setAcademicdegreeSuffix("Suffix");

		String result = service.academicdegreeAsString(p);
		assertNull(result);
	}

	@Test
	public void academicdegreeAsString_deutsch() {

		LocaleContextHolder.setLocale(Locale.GERMANY);

		Person p = new Person();
		p.setAcademicdegreeId(5L);
		p.setAcademicdegreeSuffix("Suffix");

		String result = service.academicdegreeAsString(p);
		assertEquals("Prof. DE Suffix", result);

		result = service.academicdegreeAsString(p, Locale.GERMANY);
		assertEquals("Prof. DE Suffix", result);
	}

	@Test
	public void academicdegreeAsString_english() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);

		Person p = new Person();
		p.setAcademicdegreeId(5L);
		p.setAcademicdegreeSuffix("Suffix");

		String result = service.academicdegreeAsString(p);
		assertEquals("Prof. EN Suffix", result);

		result = service.academicdegreeAsString(p, Locale.ENGLISH);
		assertEquals("Prof. EN Suffix", result);
	}

	@Test
	public void academicdegreeAsString_personNull() {

		String result = service.academicdegreeAsString(null);
		assertNull(result);
	}

	@Test
	public void academicdegreeAsString_sufficNull() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);

		Person p = new Person();
		p.setAcademicdegreeId(5L);
		p.setAcademicdegreeSuffix(null);

		String result = service.academicdegreeAsString(p);
		assertEquals("Prof. EN", result);
	}

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		Academicdegree test = new Academicdegree();
		test.setId(5L);
		test.setDefaulttextDe("Prof. DE");
		test.setDefaulttextEn("Prof. EN");
		when(dao.fetchById(5L)).thenAnswer(i -> test);
	}

	@Test
	public void fetchByString_findsBestMatch() throws Exception {

		Academicdegree d1 = new Academicdegree();
		d1.setUniquename("Prof.");

		Academicdegree d2 = new Academicdegree();
		d2.setUniquename("Dr.");

		Academicdegree d3 = new Academicdegree();
		d3.setUniquename("Prof. Dr.");

		Academicdegree d4 = new Academicdegree();
		d4.setUniquename("Hamster");

		when(dao.findAll()).thenAnswer(i -> Arrays.asList(d1, d2, d3, d4));

		assertEquals(d3, service.fetchByString("Prof. Dr. habil."));

	}

	@Test
	public void fetchByString_findsIdByDefaulttextDe() throws Exception {

		Academicdegree degree = fetchByString_prepareTestData();
		degree.setDefaulttextDe("Prof.");

		assertEquals(degree, service.fetchByString("Prof. habil."));

	}

	@Test
	public void fetchByString_findsIdByDefaulttextEn() throws Exception {

		Academicdegree degree = fetchByString_prepareTestData();
		degree.setDefaulttextEn("Prof.");

		assertEquals(degree, service.fetchByString("Prof. habil."));

	}

	@Test
	public void fetchByString_findsIdByLongtextDe() throws Exception {

		Academicdegree degree = fetchByString_prepareTestData();
		degree.setLongtextDe("Prof.");

		assertEquals(degree, service.fetchByString("Prof. habil."));

	}

	@Test
	public void fetchByString_findsIdByLongtextEn() throws Exception {

		Academicdegree degree = fetchByString_prepareTestData();
		degree.setLongtextEn("Prof.");

		assertEquals(degree, service.fetchByString("Prof. habil."));

	}

	@Test
	public void fetchByString_findsIdByShorttextDe() throws Exception {

		Academicdegree degree = fetchByString_prepareTestData();
		degree.setShorttextDe("Prof.");

		assertEquals(degree, service.fetchByString("Prof. habil."));

	}

	@Test
	public void fetchByString_findsIdByShorttextEn() throws Exception {

		Academicdegree degree = fetchByString_prepareTestData();
		degree.setShorttextEn("Prof.");

		assertEquals(degree, service.fetchByString("Prof. habil."));

	}

	@Test
	public void fetchByString_findsIdByUniquename() throws Exception {

		Academicdegree degree = fetchByString_prepareTestData();
		degree.setUniquename("Prof.");

		assertEquals(degree, service.fetchByString("Prof. habil."));

	}

	private Academicdegree fetchByString_prepareTestData() {

		Academicdegree degree = new Academicdegree();

		when(dao.findAll()).thenAnswer(i -> Arrays.asList(new Academicdegree(), degree, new Academicdegree()));

		return degree;
	}

	@Test
	public void fetchByString_withNull() throws Exception {

		assertNull(service.fetchByString(null));
		assertNull(service.fetchByString(""));

	}

	@Test
	public void fetchSuffixFromString_extractsSuffix() throws Exception {

		Academicdegree degree = new Academicdegree();
		degree.setDefaulttextDe("Prof.");
		assertEquals("habil.", service.fetchSuffixFromString("Prof. habil.", degree));

		degree = new Academicdegree();
		degree.setDefaulttextEn("Prof.");
		assertEquals("habil.", service.fetchSuffixFromString("Prof. habil.", degree));

		degree = new Academicdegree();
		degree.setShorttextDe("Prof.");
		assertEquals("habil.", service.fetchSuffixFromString("Prof. habil.", degree));

		degree = new Academicdegree();
		degree.setShorttextEn("Prof.");
		assertEquals("habil.", service.fetchSuffixFromString("Prof. habil.", degree));

		degree = new Academicdegree();
		degree.setLongtextDe("Prof.");
		assertEquals("habil.", service.fetchSuffixFromString("Prof. habil.", degree));

		degree = new Academicdegree();
		degree.setLongtextEn("Prof.");
		assertEquals("habil.", service.fetchSuffixFromString("Prof. habil.", degree));

		degree = new Academicdegree();
		degree.setUniquename("Prof.");
		assertEquals("habil.", service.fetchSuffixFromString("Prof. habil.", degree));
	}

	@Test
	public void fetchSuffixFromString_withNulls() throws Exception {

		assertNull(service.fetchSuffixFromString(null, null));
		assertNull(service.fetchSuffixFromString(null, new Academicdegree()));
		assertEquals("", service.fetchSuffixFromString("", new Academicdegree()));
		assertEquals("test", service.fetchSuffixFromString("test", null));
	}

	@Test
	public void orderNumTest() {

		int num = service.orderNum();
		assertTrue(num > 0);
	}

	@Test
	public void setAcademicdegree_findsIdByDefaulttextDe() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);
		degree.setDefaulttextDe("Prof.");

		service.setAcademicdegree(p, "Prof.");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("", p.getAcademicdegreeSuffix());

		service.setAcademicdegree(p, "Prof. Testing");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("Testing", p.getAcademicdegreeSuffix());
	}

	@Test
	public void setAcademicdegree_findsIdByDefaulttextEn() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);
		degree.setDefaulttextEn("Prof.");

		service.setAcademicdegree(p, "Prof.");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("", p.getAcademicdegreeSuffix());

		service.setAcademicdegree(p, "Prof. Testing");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("Testing", p.getAcademicdegreeSuffix());
	}

	@Test
	public void setAcademicdegree_findsIdByLongtextDe() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);
		degree.setLongtextDe("Prof.");

		service.setAcademicdegree(p, "Prof.");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("", p.getAcademicdegreeSuffix());

		service.setAcademicdegree(p, "Prof. Testing");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("Testing", p.getAcademicdegreeSuffix());
	}

	@Test
	public void setAcademicdegree_findsIdByLongtextEn() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);
		degree.setLongtextEn("Prof.");

		service.setAcademicdegree(p, "Prof.");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("", p.getAcademicdegreeSuffix());

		service.setAcademicdegree(p, "Prof. Testing");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("Testing", p.getAcademicdegreeSuffix());
	}

	@Test
	public void setAcademicdegree_findsIdByShorttextDe() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);
		degree.setShorttextDe("Prof.");

		service.setAcademicdegree(p, "Prof.");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("", p.getAcademicdegreeSuffix());

		service.setAcademicdegree(p, "Prof. Testing");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("Testing", p.getAcademicdegreeSuffix());
	}

	@Test
	public void setAcademicdegree_findsIdByShorttextEn() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);
		degree.setShorttextEn("Prof.");

		service.setAcademicdegree(p, "Prof.");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("", p.getAcademicdegreeSuffix());

		service.setAcademicdegree(p, "Prof. Testing");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("Testing", p.getAcademicdegreeSuffix());
	}

	@Test
	public void setAcademicdegree_findsIdByUniquename() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);
		degree.setUniquename("Prof.");

		service.setAcademicdegree(p, "Prof.");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("", p.getAcademicdegreeSuffix());

		service.setAcademicdegree(p, "Prof. Testing");
		assertEquals(Long.valueOf(1), p.getAcademicdegreeId());
		assertEquals("Testing", p.getAcademicdegreeSuffix());
	}

	@Test(expected = RuntimeException.class)
	public void setAcademicdegree_findsNoneById() {

		Academicdegree degree = setAcademicdegree_prepareTestData();
		Person p = new Person();

		degree.setId(1L);

		service.setAcademicdegree(p, "Prof.");
	}

	@Test
	public void setAcademicdegree_nullSafe() {

		service.setAcademicdegree(null, "");
		// hoffentlich keine Exception, ansonsten passiert hier nix

		Person p = new Person();
		service.setAcademicdegree(p, null);
		assertNull(p.getAcademicdegreeId());
		assertNull(p.getAcademicdegreeSuffix());
	}

	private Academicdegree setAcademicdegree_prepareTestData() {

		Academicdegree degree = new Academicdegree();

		when(dao.findAll()).thenAnswer(i -> Arrays.asList(degree));

		return degree;
	}
}
