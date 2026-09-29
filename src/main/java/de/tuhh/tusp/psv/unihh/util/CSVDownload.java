package de.tuhh.tusp.psv.unihh.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public interface CSVDownload {

	/**
	 * Läd die CSV-Dateien der UHH herunter
	 *
	 * @return Map der Dateien mit den Dateinamen als Key
	 * @throws JSchException
	 *             Wenn die Verbindung Probleme macht
	 * @throws SftpException
	 *             Wenn die Übertragung Probleme macht
	 * @throws IOException
	 *             Wenn das Speichern Probleme macht
	 * @throws RuntimeException
	 *             kp
	 */
	Map<String, Resource> download() throws JSchException, SftpException, IOException, RuntimeException;
}
