package de.tuhh.tusp.psv.mobon;

//import java.util.GregorianCalendar;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import de.tuhh.tusp.global.service.JobLogger;
//import de.tuhh.tusp.psv.mobon.pojo.SopArgumentsHolder;
//import de.tuhh.tusp.psv.mobon.service.SopOutputService;
//import de.tuhh.tusp.psv.mobon.service.SopOutputServiceImpl;
//import de.tuhh.tusp.psv.mobon.util.SopJsonParser;
//import de.tuhh.tusp.utils.CalendarUtils;
//import de.tuhh.tusp.utils.MessageCollector;
//import de.tuhh.tusp.utils.MessageCollectorImpl;
//import de.tuhh.tusp.utils.schedule.Schedulable;

//@Component
public class MobonScheduler /* implements Schedulable */ {
//
//	public static final String MESSAGE_COLLECTOR_NAME = "Mobility-Online-Import";
//	private static Logger logger = Logger.getLogger(SopOutputServiceImpl.class);
//
//	@Autowired
//	private SopJsonParser jsonParser;
//
//	@Autowired
//	private SopOutputService sopService;
//
//	@Override
//	public void start(JobLogger job) throws Exception {
//
//		MessageCollector mc = MessageCollectorImpl.getInstance(MESSAGE_COLLECTOR_NAME, null);
//		try {
//
//			if (!job.getJob().getName().matches(".*;[SW]S \\d{4}(/\\d{2})?$")) {
//				String msg = "SOP-ImportJob muss am Ende des Namen das Semester enthalten (Beispiel 'SOP Job;SS 2018') "
//						+ job.getJob().getName();
//				throw new RuntimeException(msg);
//			}
//
//			String[] parts = job.getJob().getName().split(";");
//
//			SopArgumentsHolder args = jsonParser.readJsonFile();
//			args.setSemesterDescription(parts[1]);
//
//			sopService.importSopData(args);
//		}
//		catch (Exception e) {
//			logger.error(job.getJob().getName() + " Import fehlgeschlagen!", e);
//			mc.sendWarningsAndErrors(null);
//		}
//
//	}
//
//	@Override
//	public String suggestName() {
//
//		return "MobilityOnline " + CalendarUtils.sopFromHisSemester(CalendarUtils.getSemester(new GregorianCalendar()));
//	}
}
