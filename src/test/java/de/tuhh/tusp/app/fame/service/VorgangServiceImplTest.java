package de.tuhh.tusp.app.fame.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.InputTypeDao;
import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.VorgangDao;
import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchergebnis;
import de.tuhh.tusp.app.fame.dao.VorgangDao.Suchparameter;
import de.tuhh.tusp.app.fame.dao.WorkflowInstanceDao;
import de.tuhh.tusp.app.fame.enums.InputTypeEnum;
import de.tuhh.tusp.app.fame.exception.StatusuebergangVerbotenException;
import de.tuhh.tusp.app.fame.exception.VorgangGesperrtException;
import de.tuhh.tusp.app.fame.pojo.Input;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Value;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.app.fame.pojo.WorkflowInstance;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;

@RunWith(MockitoJUnitRunner.class)
public class VorgangServiceImplTest {

	@InjectMocks
	private VorgangServiceImpl service;

	@Mock
	private VorgangDao vorgangDao;

	@Mock
	private AuthorityUtils authorityUtilsImpl;

	@Mock
	private ConfigService configService;

	@Mock
	private StatusBerechtigungDao statusBerechtigungDao;

	@Mock
	private InputBerechtigungDao inputBerechtigungDao;

	@Mock
	private InputTypeDao inputTypeDao;

	@Mock
	private WorkflowInstanceDao workflowInstanceDao;

	@Mock
	private StatusService statusService;

	@Mock
	private InputServiceImpl inputService;

	@Mock
	private LogService logService;

	@Captor
	private ArgumentCaptor<List<WorkflowInstance>> searchArgCaptor1;

	@Captor
	private ArgumentCaptor<Map<Long, String>> searchArgCaptor2;

	private List<String> rollenDesTestbenutzers = new ArrayList<>();

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		// ich bin user "test"
		Mockito.when(authorityUtilsImpl.getCurrentUserName()).thenAnswer(i -> "test");

		// ich habe die Rolle "Tester" und ""
		rollenDesTestbenutzers.add("Tester");
		rollenDesTestbenutzers.add("Debugger(TUSP)");
		Mockito.when(authorityUtilsImpl.getRoles()).thenAnswer(i -> rollenDesTestbenutzers);

		// fetchById => liefert new Vorgang()
		Mockito.when(vorgangDao.fetchById(null)).thenAnswer(i -> new Vorgang());

		// fetchById => liefert new Vorgang()
		Mockito.when(configService.get("de.tuhh.tusp.fame.vorgang.lock_time", 30, true)).thenAnswer(i -> 30);
	}

	@Test
	public void inBearbeitungBeiTest() {

		// Vorgang ist nicht gesperrt
		Vorgang vorgang = new Vorgang();
		assertNull(service.inBearbeitungBei(vorgang));

		// Ohne Zeit bleibt die Sperre wirkungslos
		vorgang.setGesperrtDurch("some body");
		assertNull(service.inBearbeitungBei(vorgang));

		// Sperrung durch "some body"
		vorgang.setGesperrtDatum(new Date());
		vorgang.setGesperrtDurch("some body");
		assertEquals("some body", service.inBearbeitungBei(vorgang));

		// Sperrung durch "test"
		vorgang.setGesperrtDatum(new Date());
		vorgang.setGesperrtDurch("test");
		assertEquals("me", service.inBearbeitungBei(vorgang));

		// Keine weitere Bearbeitung
		vorgang.setGesperrtDatum(null);
		vorgang.setGesperrtDurch("none");
		assertEquals("none", service.inBearbeitungBei(vorgang));

		// Sperrung veraltet
		vorgang.setGesperrtDatum(new Date(1)); // => 1970
		vorgang.setGesperrtDurch("test");
		assertEquals(null, service.inBearbeitungBei(vorgang));
	}

	@Test
	public void inBearbeitungNehmenTest() throws VorgangGesperrtException {

		Vorgang vorgang = new Vorgang();

		// Wird der Vorgang richtig gesperrt?
		service.inBearbeitungNehmen(vorgang);
		Long timeDiff = new Date().getTime() - vorgang.getGesperrtDatum().getTime();
		assertEquals(authorityUtilsImpl.getCurrentUserName(), vorgang.getGesperrtDurch());
		assertTrue(Math.abs(timeDiff) < 1000);
		Mockito.verify(vorgangDao, Mockito.times(1)).update(vorgang); // einmal Daten persistiert

		// Von jemand anderem gesperrt
		vorgang.setGesperrtDurch("some body");
		try {
			service.inBearbeitungNehmen(vorgang);
			fail("Gesperrter Vorgang konnte in Bearbeitung genommen werden.");
		}
		catch (VorgangGesperrtException e) {}

		// Keine weitere Bearbeitung sollte kein Hiindernis sein
		vorgang.setGesperrtDurch("none");
		service.inBearbeitungNehmen(vorgang);
	}

	@Test
	public void keineWeitereBearbeitungTest() throws VorgangGesperrtException {

		// Vorgang kann gesperrt werden
		Vorgang vorgang = new Vorgang();
		service.keineWeitereBearbeitung(vorgang);
		assertEquals("none", vorgang.getGesperrtDurch());

		// Wenn ich Bearbeiter bin klappt's auch
		vorgang.setGesperrtDurch(authorityUtilsImpl.getCurrentUserName());
		vorgang.setGesperrtDatum(new Date());
		service.keineWeitereBearbeitung(vorgang);
		assertEquals("none", vorgang.getGesperrtDurch());

		// Wenn jemand anderes gesperrt hat => Exception
		vorgang.setGesperrtDurch("some body");
		vorgang.setGesperrtDatum(new Date());
		try {
			service.keineWeitereBearbeitung(vorgang);
			fail("Exception fehlt");
		}
		catch (VorgangGesperrtException e) {}
	}

	@Test
	public void search_filtersWorkflowInstancesWithPermission() throws Exception {

		when(authorityUtilsImpl.getRoles()).then(i -> Arrays.asList("test"));

		WorkflowInstance wi1 = new WorkflowInstance();
		wi1.setId(1L);
		wi1.setWorkflowId(5L);
		WorkflowInstance wi2 = new WorkflowInstance();
		wi2.setId(2L);
		wi2.setWorkflowId(6L);
		when(workflowInstanceDao.findByRoles(Arrays.asList("test"))).thenAnswer(i -> Arrays.asList(wi1, wi2));

		Map<Long, String> params = new HashMap<>();

		service.search(5L, params);

		verify(vorgangDao).search(searchArgCaptor1.capture(), searchArgCaptor2.capture());

		assertSame(params, searchArgCaptor2.getValue());
		assertEquals(1, searchArgCaptor1.getValue().size());
		assertTrue(searchArgCaptor1.getValue().contains(wi1));
		// assertTrue(searchArgCaptor1.getValue().contains(wi2));
	}

	@Test
	public void search_filtertNichtBerechtigteTrefferAus() throws Exception {

		List<Suchergebnis> treffer = new ArrayList<>();

		Suchergebnis e1 = new Suchergebnis();
		e1.setVorgang(new Vorgang());
		e1.setValue(new Value());
		e1.getValue().setInputId(1L);
		treffer.add(e1);

		Suchergebnis e2 = new Suchergebnis();
		e2.setVorgang(new Vorgang());
		e2.setValue(new Value());
		e2.getValue().setInputId(2L);
		treffer.add(e2);

		when(vorgangDao.search(any())).then(x -> treffer);
		when(inputBerechtigungDao.canRead(1L, null, null, rollenDesTestbenutzers)).then(x -> true);
		when(inputBerechtigungDao.canRead(2L, null, null, rollenDesTestbenutzers)).then(x -> false);

		List<Suchergebnis> result = service.search("");

		assertTrue(result.contains(e1));
		assertFalse(result.contains(e2));
	}

	@Test
	public void serach_erstelltCorrrecteSuchparameterForTypeDate() throws Exception {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArgumentCaptor<List<Suchparameter>> cap = ArgumentCaptor.forClass((Class) List.class);

		when(inputTypeDao.getId(InputTypeEnum.DATE)).then(i -> 1L);

		List<Input> inputs = new ArrayList<>();
		Input i1 = new Input();
		i1.setInputTypeId(1L);
		inputs.add(i1);
		when(inputService.findAll()).then(i -> inputs);

		// Schwachsinnszahl
		service.search("10");
		verify(vorgangDao, times(1)).search(cap.capture());
		assertTrue(cap.getValue().isEmpty());

		// Schwachsinnswort
		service.search("test");
		verify(vorgangDao, times(2)).search(cap.capture());
		assertTrue(cap.getValue().isEmpty());

		// englisches Datum mit Uhrzeit
		service.search("2019-06-04 10:33:55");
		verify(vorgangDao, times(3)).search(cap.capture());
		assertEquals("2019-06-04 10:33:55", cap.getValue().get(0).getSearchword());

		// englisches Datum
		service.search("2019-06-04");
		verify(vorgangDao, times(4)).search(cap.capture());
		assertEquals("2019-06-04", cap.getValue().get(0).getSearchword());

		// Uhrzeit sekundengenau
		service.search("10:33:55");
		verify(vorgangDao, times(5)).search(cap.capture());
		assertEquals("10:33:55", cap.getValue().get(0).getSearchword());

		// Uhrzeit minutengenau
		service.search("10:33:55");
		verify(vorgangDao, times(6)).search(cap.capture());
		assertEquals("10:33:55", cap.getValue().get(0).getSearchword());

		// deutsches Datum mit Uhrzeit
		service.search("04.06.2019 10:33:55");
		verify(vorgangDao, times(7)).search(cap.capture());
		assertEquals("2019-06-04 10:33:55", cap.getValue().get(0).getSearchword());

		// deutsches Datum mit Uhrzeit
		service.search("04.06.2019 10:33");
		verify(vorgangDao, times(8)).search(cap.capture());
		assertEquals("2019-06-04 10:33", cap.getValue().get(0).getSearchword());

		// deutsches Datum
		service.search("04.06.2019");
		verify(vorgangDao, times(9)).search(cap.capture());
		assertEquals("2019-06-04", cap.getValue().get(0).getSearchword());
	}

	@Test
	public void serach_erstelltCorrrecteSuchparameterForTypeSelect() throws Exception {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArgumentCaptor<List<Suchparameter>> cap = ArgumentCaptor.forClass((Class) List.class);

		when(inputTypeDao.getId(InputTypeEnum.SELECT)).then(i -> 1L);

		List<Input> inputs = new ArrayList<>();

		Input i1 = new Input();
		i1.setInputTypeId(1L);
		i1.setOptions("0;Nein\n1;Ja");
		inputs.add(i1);

		when(inputService.findAll()).then(i -> inputs);
		when(inputService.parseOptions(i1)).thenCallRealMethod();

		String suchwort = "ei";
		service.search(suchwort);

		verify(vorgangDao, times(1)).search(cap.capture());
		List<Suchparameter> params = cap.getValue();
		assertEquals(i1, params.get(0).getInput());
		// Nein enthält "ei" und wird umgewandelt in 0
		assertEquals("0", params.get(0).getSearchword());
	}

	@Test
	public void serach_erstelltCorrrecteSuchparameterForTypeText() throws Exception {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArgumentCaptor<List<Suchparameter>> cap = ArgumentCaptor.forClass((Class) List.class);

		when(inputTypeDao.getId(InputTypeEnum.TEXT)).then(i -> 1L);

		List<Input> inputs = new ArrayList<>();

		Input i1 = new Input();
		i1.setInputTypeId(1L);
		inputs.add(i1);

		when(inputService.findAll()).then(i -> inputs);

		String suchwort = "suchwort";
		service.search(suchwort);

		verify(vorgangDao, times(1)).search(cap.capture());
		List<Suchparameter> params = cap.getValue();
		assertEquals(i1, params.get(0).getInput());
		assertEquals(suchwort, params.get(0).getSearchword());
	}

	@Test
	public void statusUebergangTest() throws StatusuebergangVerbotenException {

		Long fromId = 5L;
		Long erlaubterStatus = 8L;
		Long wiId = 3L; // WorkflowInstance

		Vorgang vorgang = new Vorgang();
		vorgang.setStatusId(fromId);
		vorgang.setWorkflowInstanceId(wiId);

		// Eingabe für die Methode Mocken
		Status s1 = new Status();
		s1.setId(erlaubterStatus);

		Status s2 = new Status();
		s2.setId(1000L);
		when(statusService.findByVorgangAndRoles(	vorgang,
													authorityUtilsImpl.getRoles())).thenAnswer(i -> Arrays.asList(	s1,
																													s2));

		// Bleibt beim alten
		service.statusUebergang(vorgang, vorgang.getStatusId());
		verify(logService, times(0)).logStatusChange(any(Status.class), any(Status.class), any(Long.class));

		// erlaubter Statusübergang
		service.statusUebergang(vorgang, erlaubterStatus);
		verify(logService, times(1)).logStatusChange(any(Status.class), any(Status.class), any(Long.class));

		// nicht erlaubter Übergang
		try {
			service.statusUebergang(vorgang, -5L);
			fail("Exception erwartet");
		}
		catch (StatusuebergangVerbotenException e) {}

	}

	@Test
	public void zurBearbeitungFreigebenTest() throws VorgangGesperrtException {

		// ist schon frei sollte; kein Problem sein
		Vorgang vorgang = new Vorgang();
		service.zurBearbeitungFreigeben(vorgang);

		// In Bearbeitung bei mir
		vorgang.setGesperrtDatum(new Date());
		vorgang.setGesperrtDurch(authorityUtilsImpl.getCurrentUserName());
		service.zurBearbeitungFreigeben(vorgang);

		// Gesperrt von anderem => Exception
		vorgang.setGesperrtDurch("some body");
		vorgang.setGesperrtDatum(new Date());
		try {
			service.zurBearbeitungFreigeben(vorgang);
			fail("Exception erwartet");
		}
		catch (VorgangGesperrtException e) {}
	}
}
