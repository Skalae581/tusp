package de.tuhh.tusp.lvp.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;

import de.his.ws.courseinterfaceservice.ServiceFault_Exception;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobLogger;
import de.tuhh.tusp.lvp.dao.TermineColumns;
import de.tuhh.tusp.lvp.dao.TermineDao;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;

@Component
public class TermineServiceImpl implements TermineService {

	private final static String LOCAL_TEST_PATH = "/home/rztjj/Documents/TU Service Portal_files/monitore/termine.csv";

	private static Logger logger = LogManager.getLogger(TermineServiceImpl.class);

	private static final String SERVER = "de.tuhh.tusp.lvp.autauschServer";
	private static final String FOLDER = "de.tuhh.tusp.lvp.autauschFolder";
	private static final String USER = "de.tuhh.tusp.lvp.autauschUser";
	private static final String PORT = "de.tuhh.tusp.lvp.autauschPort";
	@Autowired
	private TermineDao termineDao;
	@Autowired
	private ConfigService config;
	@Autowired
	private SshUtils sshUtils;

	@Override
	public void exportTermine(MessageCollector mc, Date date, JobLogger jobLogger)
			throws ServiceFault_Exception, de.his.ws.keyvalueservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.termservice.ServiceFault_Exception, ParseException, JSchException {

		List<Map<TermineColumns, String>> termine;
		try {
			termine = termineDao.getTermineDaten(jobLogger);
			logger.debug("LVP- Termine gefunden " + termine.size());
			jobLogger.debug("LVP- Termine gefunden " + termine.size());

		} catch (ServiceFault e) {
			// mc.addErrorMessage("Konnte Terminedaten nicht abrufen: " + e.getMessage());
			logger.error("Konnte Terminedaten nicht abrufen", e);
			jobLogger.error("Konnte Terminedaten nicht abrufen", e);
			// mc.sendWarningsAndErrors(null);
			return;
		}

		// Schreibe CSV
		InMemoryResource resource = new InMemoryResource("termine.csv");

		WritableResource res = new FileSystemResource(LOCAL_TEST_PATH);

		try {
			termineDao.writeTermine(termine, resource);
			// termineDao.writeTermine(termine, res); // LOKAL TEST

			String server = config.get(SERVER, "marketing1.v.tu-harburg.de", true);
			String path = config.get(FOLDER, "/srv/websites/cis/", true);

			SshSession session = sshUtils.getSession(config.get(USER, "cis", true), server, config.get(PORT, 22, true));
			session.copyToRemote(resource, path);

			logger.debug("termine.csv wurde erfolgreich an " + server + " übertragen");
			jobLogger.debug("termine.csv wurde erfolgreich an " + server + " übertragen");

		} catch (Exception e) {
			jobLogger.error("Konnte Datei termine.csv nicht exportieren: " + e.getMessage());
			logger.error("Konnte Datei termine.csv nicht exportieren", e);
		}

	}

}
