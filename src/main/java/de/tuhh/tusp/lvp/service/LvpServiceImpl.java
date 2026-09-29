package de.tuhh.tusp.lvp.service;

import java.io.IOException;
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
import de.tuhh.tusp.lvp.dao.LvpColumns;
import de.tuhh.tusp.lvp.dao.LvpDao;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;

@Component
public class LvpServiceImpl implements LvpService {

	private final static String LOCAL_TEST_PATH = "/home/rztjj/Documents/TU Service Portal_files/monitore/lvp.csv";

	private static Logger logger = LogManager.getLogger(LvpServiceImpl.class);

	private static final String SERVER = "de.tuhh.tusp.lvp.autauschServer";
	private static final String FOLDER = "de.tuhh.tusp.lvp.autauschFolder";
	private static final String USER = "de.tuhh.tusp.lvp.autauschUser";
	private static final String PORT = "de.tuhh.tusp.lvp.autauschPort";
	@Autowired
	private LvpDao lvpDao;
	@Autowired
	private ConfigService config;
	@Autowired
	private SshUtils sshUtils;

	@Override
	public void exportLvp(MessageCollector mc, Date date, JobLogger jobLogger)
			throws ServiceFault_Exception, de.his.ws.keyvalueservice.ServiceFault_Exception,
			de.his.ws.appointmentseriesservice.ServiceFault_Exception, de.his.ws.facilityservice.ServiceFault_Exception,
			de.his.ws.termservice.ServiceFault_Exception, ParseException, JSchException {

		List<Map<LvpColumns, String>> lvp;
		try {
			lvp = lvpDao.getLvpDaten(mc, jobLogger);
			logger.debug("LVP- Veranstaltungen gefunden " + lvp.size());
			jobLogger.debug("LVP- Veranstaltungen gefunden " + lvp.size());
		} catch (ServiceFault e) {
			logger.error("Konnte Lvpdaten nicht abrufen", e);
			jobLogger.error("Konnte Lvpdaten nicht abrufen", e);
			return;
		}

		// Schreibe CSV
		InMemoryResource resource = new InMemoryResource("lvp.csv");

		WritableResource res = new FileSystemResource(LOCAL_TEST_PATH); // TEST

		try {
			lvpDao.writeLvp(lvp, resource);
			// lvpDao.writeLvp(lvp, res); // LOKAL TEST

			String server = config.get(SERVER, "marketing1.v.tu-harburg.de", true);
			String path = config.get(FOLDER, "/srv/websites/cis/", true);

			SshSession session = sshUtils.getSession(config.get(USER, "cis", true), server, config.get(PORT, 22, true));
			session.copyToRemote(resource, path);

			logger.debug("lvp.csv wurde erfolgreich an " + server + " übertragen");
			jobLogger.debug("lvp.csv wurde erfolgreich an " + server + " übertragen");

		} catch (IOException e) {
			logger.error("Konnte Datei lvp.csv nicht exportieren", e);
			jobLogger.error("Konnte Datei lvp.csv nicht exportieren", e);
		}

	}

}
