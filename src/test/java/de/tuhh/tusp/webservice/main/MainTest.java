package de.tuhh.tusp.webservice.main;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.webservice.sync.main.Main;
import de.tuhh.tusp.webservice.sync.service.WebserviceSync;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

	class Sync implements WebserviceSync {

		int order;

		public Sync(int order) {
			this.order = order;
		}

		@Override
		public int orderNum() {

			return order;
		}

		@Override
		public void sync() {

			invocations.add(this);

			if (order == 3) {
				throw new RuntimeException("Es geht immer was schief!");
			}
		}

	}

	List<Sync> invocations = new ArrayList<>();

	@InjectMocks
	private Main main;

	@Mock
	private ApplicationContext context;

	@Before
	public void before() {

		MockitoAnnotations.initMocks(this);

		Map<String, WebserviceSync> beans = new HashMap<>();
		beans.put("a", new Sync(4));
		beans.put("b", new Sync(2));
		beans.put("c", new Sync(1));
		beans.put("d", new Sync(3));

		when(context.getBeansOfType(any())).thenAnswer(i -> beans);
	}

	@Test
	public void startTest() {

		main.start(Mockito.mock(JobLogger.class));
		assertEquals(1, invocations.get(0).orderNum());
		assertEquals(2, invocations.get(1).orderNum());
		assertEquals(3, invocations.get(2).orderNum());
		assertEquals(4, invocations.get(3).orderNum());
	}

	@Test
	public void trigger_isScheduled() throws NoSuchMethodException, SecurityException {

		Scheduled annotation = Main.class.getMethod("trigger").getAnnotation(Scheduled.class);
		assertNotNull(annotation);
	}
}
