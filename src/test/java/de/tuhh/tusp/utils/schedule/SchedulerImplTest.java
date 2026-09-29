package de.tuhh.tusp.utils.schedule;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.global.service.JobService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MailUtils;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerImplTest {

	class ExSched implements Schedulable {

		public boolean isStarted = false;

		@Override
		public void start(JobLogger job) throws Exception {

			throw new RuntimeException("Exception wird für Test benötigt");
		}

		@Override
		public String suggestName() {

			return "test1";
		}

	}

	class Sched implements Schedulable {

		public boolean isStarted = false;

		@Override
		public void start(JobLogger job) throws Exception {

			isStarted = true;
		}

		@Override
		public String suggestName() {

			return "test2";
		}

	}

	@Mock
	private JobService jobService;

	@Mock
	private ConfigService configService;

	@Mock
	private CalendarUtils calendar;

	@Mock
	private ApplicationContext ctx;

	@Mock
	private MailUtils mail;

	@InjectMocks
	private SchedulerImpl scheduler = new SchedulerImpl();

	@Before
	public void before() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void scheduleBeanNotFoundTest() throws Exception {

		Job job1 = new Job();
		job1.setClassName(Sched.class.getName());
		when(jobService.findPendingJobs()).then(x -> Arrays.asList(job1));
		when(ctx.getBean(Sched.class)).thenThrow(new BeansException("Test") {

			private static final long serialVersionUID = 1L;
		});

		scheduler.schedule();

		verify(jobService, times(1)).startJob(job1);
		verify(jobService, times(1)).beendeJob(job1, Status.Fail);
	}

	@Test
	public void scheduleClassNotFoundTest() throws Exception {

		Job job1 = new Job();
		job1.setClassName("test");
		when(jobService.findPendingJobs()).then(x -> Arrays.asList(job1));

		scheduler.schedule();

		verify(jobService, times(1)).startJob(job1);
		verify(jobService, times(1)).beendeJob(job1, Status.Fail);
	}

	@Test
	public void scheduleRegistriertJob() throws Exception {

		Class<? extends Schedulable> clazz = Sched.class;
		String name = "testName";

		scheduler.schedule(clazz, name);

		verify(jobService, times(1)).registerJob(clazz.getName(), name);;
	}

	@Test
	public void scheduleSendMailWhenExceptionInJob() throws Exception {

		Job job1 = new Job();
		job1.setClassName(Sched.class.getName());
		when(jobService.findPendingJobs()).then(x -> Arrays.asList(job1));
		when(ctx.getBean(Sched.class)).then(x -> new ExSched());

		scheduler.schedule();

		verify(mail, times(1)).sendPlainTextMail(	any(String.class), any(String.class), any(String.class),
													any(String.class));
		verify(jobService, times(1)).beendeJob(job1, Status.Fail);
	}

	@Test
	public void scheduleSkipsRunningJobs() throws Exception {

		Job job1 = new Job();
		job1.setClassName(Sched.class.getName());
		job1.setStatus(Status.Running);

		Sched sched = new Sched();
		Job job2 = new Job();
		job2.setClassName(Sched.class.getName());

		doThrow(JobInUseException.class).when(jobService).startJob(job1);
		when(jobService.findPendingJobs()).then(x -> Arrays.asList(job1, job2));
		when(ctx.getBean(Sched.class)).then(x -> sched);

		scheduler.schedule();

		verify(jobService, times(1)).beendeJob(job2, Status.Done);
		verify(jobService, times(1)).startJob(job1);
		verify(jobService, times(0)).beendeJob(job1, Status.Done);
		assertTrue(sched.isStarted);
	}

	@Test
	public void scheduleSuccess() throws Exception {

		Sched sched = new Sched();
		Job job2 = new Job();
		Job job1 = new Job();
		job1.setClassName(Sched.class.getName());
		when(jobService.findPendingJobs()).then(x -> Arrays.asList(job1, job2));
		when(ctx.getBean(Sched.class)).then(x -> sched);

		scheduler.schedule();

		verify(jobService, times(1)).beendeJob(job1, Status.Done);
		verify(jobService, times(0)).startJob(job2);
		assertTrue(sched.isStarted);
	}
}
