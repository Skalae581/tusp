package de.tuhh.tusp.stu.studcredentials.alle.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.stu.studcredentials.alle.service.TuhhRaumeService;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.MessageCollectorImpl;

@Component
public class TuhhRaumeMain {

	@Autowired
	private TuhhRaumeService tuhhRaumeService;

	public void start(JobLogger jobLogger) throws Exception {
		MessageCollector mc = MessageCollectorImpl.getInstance("studcredentials", jobLogger);

		jobLogger.debug("Studenten-Credentials gestartet!");

		try {
			tuhhRaumeService.getList(jobLogger);
		} catch (Exception e) {
			mc.addErrorMessage(e.getMessage());
		}

		jobLogger.debug("Studenten-Credentials beendet!");

	}
}
