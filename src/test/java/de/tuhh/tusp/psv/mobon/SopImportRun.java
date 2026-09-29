package de.tuhh.tusp.psv.mobon;

//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import de.tuhh.tusp.config.ApplicationRunTestConfig;
//import de.tuhh.tusp.global.pojo.Job;
//import de.tuhh.tusp.global.service.JobLogger;
//import de.tuhh.tusp.psv.PsvConfig;
//import de.tuhh.tusp.webservice.config.WebserviceWebConfig;
//
//@ContextConfiguration(classes = { ApplicationRunTestConfig.class, WebserviceWebConfig.class, PsvConfig.class })
//@RunWith(SpringJUnit4ClassRunner.class)
public class SopImportRun {
//
//	private static final Logger logger = Logger.getLogger(SopImportRun.class);
//
//	@Autowired
//	private MobonScheduler mobon;
//
//	@Test
//	public void run() throws Exception {
//
//		Job job = new Job();
//		job.setName("Testjob;SS 2018"); // Hier wird das Semester übergeben
//
//		mobon.start(new JobLogger() {
//
//			@Override
//			public void debug(String message) {
//
//				logger.debug(message);
//			}
//
//			@Override
//			public void error(String message) {
//
//				logger.error(message);
//			}
//
//			@Override
//			public void error(String message, Throwable e) {
//
//				logger.error(message, e);
//			}
//
//			@Override
//			public Job getJob() {
//
//				return job;
//			}
//
//			@Override
//			public String getText() {
//
//				return null;
//			}
//
//			@Override
//			public void info(String message) {
//
//				logger.info(message);
//			}
//
//			@Override
//			public void warn(String message) {
//
//				logger.warn(message);
//			}
//		});
//		logger.info("done");
//	}
}
