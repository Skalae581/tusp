package de.tuhh.tusp.psv.unihh.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.InMemoryResource;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;

@Component
public class CSVDownloadImpl implements CSVDownload {

	private static Logger logger = LogManager.getLogger(CSVDownloadImpl.class);

	@Autowired
	private SshUtils sshUtils;

	@Autowired
	private ConfigService config;

	@Override
	public Map<String, Resource> download() throws JSchException, SftpException, IOException, RuntimeException {

		String sftHost = config.get("de.tuhh.tusp.psv.unihh.sftphost", "", true);
		String sftpUser = config.get("de.tuhh.tusp.psv.unihh.sftpuser", "", true);
		int sftpPort = config.get("de.tuhh.tusp.psv.unihh.sftpport", 22, true);
		String sftpWorkDir = config.get("de.tuhh.tusp.psv.unihh.sftpworkingdir", "", true);
		String[] fileNames = config.get("de.tuhh.tusp.psv.unihh.ssh_copy_files",
				"TUHH_Technomathe_StudienDaten.csv;TUHH_Studien_Daten.csv;TUHH_Studierenden_Daten.csv;TUHH_Technomathe_StudierendenDaten.csv",
				true).split(";");

		if (sftHost.isEmpty() || sftpUser.isEmpty() || sftpWorkDir.isEmpty()) {
			throw new RuntimeException("Der UHH Import ist nicht richtig Konfiguriert: de.tuhh.tusp.psv.unihh.*");
		}

		Map<String, Resource> result = new HashMap<>();
		SshSession ssh = sshUtils.getSession(sftpUser, sftHost, sftpPort);
		for (String remoteFile : fileNames) {
			logger.debug("Datei : [" + remoteFile + "] wird heruntergeladen...");
			WritableResource outFile = new InMemoryResource(remoteFile);
			ssh.copyFromRemote(outFile, sftpWorkDir, remoteFile);
			result.put(remoteFile, outFile);
		}
		return result;
	}

}
