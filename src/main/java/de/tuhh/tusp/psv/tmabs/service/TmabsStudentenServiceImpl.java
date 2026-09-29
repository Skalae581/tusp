package de.tuhh.tusp.psv.tmabs.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.psv.tmabs.dao.TmabsColumns;
import de.tuhh.tusp.psv.tmabs.dao.TmabsStudentDao;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.MessageCollector;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;
import de.tuhh.tusp.webservice.exception.ServiceFault;

@Component
public class TmabsStudentenServiceImpl implements TmabsStudentenService {

	private static Logger logger = LogManager.getLogger(TmabsStudentenServiceImpl.class);

	@Autowired
	private TmabsStudentDao studentDao;
	@Autowired
	private SshUtils sshUtils;
	@Autowired
	private ConfigService config;

	@Override
	public void exportStudents(MessageCollector mc, Date date) {

		// Suche Studenten aus aus HIO
		// Wandle Datensätze in CSV-konforme Datensätze um
		List<Map<TmabsColumns, String>> studenten;

		try {
			studenten = studentDao.getStudentenDaten(mc);
		} catch (ServiceFault e) {
			mc.addErrorMessage("Konnte Studentendaten nicht abrufen: " + e.getMessage());
			logger.error("Konnte Studentendaten nicht abrufen", e);
			mc.sendWarningsAndErrors(null);
			return;
		}

		// Schreibe CSV
		InMemoryResource resource = new InMemoryResource("TMA_f_UniHH.csv");
		try {
			studentDao.writeStudenten(studenten, resource);

			String server = config.get(SERVER, "", true);
			String path = config.get(FOLDER, "/srv/ssh/stine-tuhh/Technomathematik/", true);

			SshSession session = sshUtils.getSession(config.get(USER, "", true), server, config.get(PORT, 22, true));
			session.copyToRemote(resource, path);
		} catch (IOException | JSchException e) {
			mc.addErrorMessage("Konnte Datei TMA_f_UniHH.csv nicht exportieren: " + e.getMessage());
			logger.error("Konnte Datei TMA_f_UniHH.csv nicht exportieren", e);
		}

	}

}
