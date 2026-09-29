package de.tuhh.tusp.app.fame.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import de.tuhh.tusp.app.fame.dao.InputBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao;
import de.tuhh.tusp.app.fame.dao.StatusBerechtigungDao.ExtendedStatusBerechtigung;
import de.tuhh.tusp.app.fame.dao.StatusDao;
import de.tuhh.tusp.app.fame.enums.FameConstants;
import de.tuhh.tusp.app.fame.pojo.Status;
import de.tuhh.tusp.app.fame.pojo.Vorgang;
import de.tuhh.tusp.utils.AuthorityUtils;

/**
 * Tests für StatusServiceImpl
 *
 * @author rztjm
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class StatusServiceImplTest {

	@Mock
	private StatusDao statusDao;

	@Mock
	private StatusBerechtigungDao statusBerechtigungDao;

	@Mock
	private InputBerechtigungDao inputBerechtigungDao;

	@Mock
	private AuthorityUtils authorityUtils;

	@InjectMocks
	private StatusServiceImpl service;

	private List<ExtendedStatusBerechtigung> berechtigungsMock;
	private Vorgang vorgang;
	private List<String> roles;

	@Before
	public void before() {

		List<String> userRoles = new ArrayList<>();
		userRoles.add("Tester");
		when(authorityUtils.getRoles()).thenAnswer(i -> userRoles);

		berechtigungsMock = new ArrayList<>();
		when(statusBerechtigungDao.findByRollenAndWorkflowInstance(any(), any())).thenAnswer(i -> berechtigungsMock);

		vorgang = new Vorgang();
		vorgang.setStatusId(5L);

		roles = new ArrayList<>();
		roles.add("Tester");
		roles.add("Spaßvogel");

		when(statusDao.findByIds(any())).thenAnswer(new Answer<List<Status>>() {

			@Override
			public List<Status> answer(InvocationOnMock invocation) throws Throwable {

				List<Status> stati = new ArrayList<>();
				@SuppressWarnings("unchecked")
				List<Long> ids = (List<Long>) invocation.getArguments()[0];
				for (int i = 0; i < ids.size(); i++) {
					Status status = new Status();
					status.setId(ids.get(i));
					stati.add(status);
				}
				return stati;
			}
		});

	}

	@Test
	public void deleteTest() {

		service.delete(3L);
		verify(statusDao, times(1)).delete(3L);
		verify(inputBerechtigungDao, times(1)).deleteByStatus(3L);
		verify(statusBerechtigungDao, times(1)).deleteByStatus(3L);
	}

	@Test
	public void fetchByIdTest() {

		service.fetchById(19L);
		verify(statusDao).fetchById(19L);
	}

	@Test
	public void fetchFirstStatusByWorkflow_multipleStatus() {

		List<Status> status = new ArrayList<>();

		Status s1 = new Status();
		s1.setId(16L);
		status.add(s1);

		Status s2 = new Status();
		s2.setId(17L);
		status.add(s2);

		when(statusDao.findByWorkflow(13L, false)).thenAnswer(i -> status);

		Status result = service.fetchFirstStatusByWorkflow(13L);

		assertEquals(16, result.getId().intValue());
	}

	@Test
	public void fetchFirstStatusByWorkflow_noStatus() {

		List<Status> status = new ArrayList<>();
		when(statusDao.findByWorkflow(13L, false)).thenAnswer(i -> status);

		Status result = service.fetchFirstStatusByWorkflow(13L);

		assertNull(result);
	}

	@Test
	public void findAllTest() {

		service.findAll();
		verify(statusDao).findAll();
	}

	@Test
	public void findByVorgangAndRoles_ohneRechte() {

		// Ohne irgendwelche Extrarechte darf nur der bisherige Status gewählt werden
		vorgang.setStatusId(5L);

		List<Status> result = service.findByVorgangAndRoles(vorgang, roles);
		assertEquals(1, result.size());
		assertEquals(5, result.get(0).getId().intValue());
	}

	@Test
	public void findByVorgangAndRoles_rechteFuerALL() {

		// Recht 2 => ALL gegeben
		ExtendedStatusBerechtigung b = new ExtendedStatusBerechtigung();
		b.setStatusToName(FameConstants.ALL_STATUS);
		b.setStatusFromName(FameConstants.ALL_STATUS);
		b.setStatusToHidden(1);
		berechtigungsMock.add(b);

		vorgang.setWorkflowInstanceId(14L);

		service.findByVorgangAndRoles(vorgang, roles);

		verify(statusDao, times(1)).findByWorkflowInstance(14L, false);
	}

	@Test
	public void findByVorgangAndRoles_speziellesRecht() {

		// Recht zu einem Speziellen Status zu wechseln
		ExtendedStatusBerechtigung b = new ExtendedStatusBerechtigung();
		b.setStatusToName("egal");
		b.setStatusToId(16L);
		b.setStatusFromName("egaler");
		b.setStatusFromId(vorgang.getStatusId());
		b.setStatusToHidden(0);
		berechtigungsMock.add(b);

		List<Status> result = service.findByVorgangAndRoles(vorgang, roles);
		assertEquals(2, result.size());
		assertEquals(5, result.get(0).getId().intValue());
		assertEquals(16, result.get(1).getId().intValue());

	}

	@Test
	public void findByVorgangAndRoles_vonUeberallZuEinem() {

		// Plus Recht von überall in s1 zu wechseln
		ExtendedStatusBerechtigung b = new ExtendedStatusBerechtigung();
		b.setStatusToName("egal");
		b.setStatusToId(17L);
		b.setStatusFromName(FameConstants.ALL_STATUS);
		b.setStatusToHidden(0);
		berechtigungsMock.add(b);

		List<Status> result = service.findByVorgangAndRoles(vorgang, roles);
		assertEquals(2, result.size());
		assertEquals(5, result.get(0).getId().intValue());
		assertEquals(17, result.get(1).getId().intValue());
	}

	@Test
	public void findByWorkflowTest() {

		service.findByWorkflow(15L, true);
		verify(statusDao).findByWorkflow(15L, true);
	}

	@Test
	public void insertTest() {

		Status status = new Status();
		service.insert(status);
		verify(statusDao).insert(status);
	}

	@Test
	public void updateTest() {

		Status status = new Status();
		service.update(status);
		verify(statusDao).update(status);
	}
}
