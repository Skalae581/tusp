package de.tuhh.tusp.utils.ssh;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public interface SshSession {

	/**
	 * Schließt die Verbindung
	 */
	void close();

	/**
	 * Kopiert eine Datei vom entfernten zum lokalen System
	 *
	 * @param outfile
	 *            Ressource in die geschrieben werden soll
	 * @param remoteWorkingDir
	 *            Das Verzeichnis in dem die Datei auf dem entfernten Server liegt
	 * @param remoteFileName
	 *            Der Dateiname der zu kopierenden Datei
	 * @throws SftpException
	 *             wenn eine der Remote-Funktionen nicht funktioniert
	 * @throws JSchException
	 *             wenn es Probleme mit der Verbindung gibt
	 * @throws IOException
	 *             wenn die Streams Probleme machen
	 * @throws RuntimeException
	 *             wenn 0 oder > 1 Dateien gefunden werden
	 */
	void copyFromRemote(WritableResource outfile, String remoteWorkingDir,
						String remoteFileName) throws SftpException, JSchException, IOException, RuntimeException;

	/**
	 * Kopiert eine Datei vom lokalen zum entfernten Rechner
	 *
	 * @param inFile
	 * @param outFolder
	 *            vollständiger Pfad auf dem entfernten Rechner
	 * @throws ConnectionClosedException
	 *             Wenn die Verbindung bereits geschlossen ist oder während der Übertragung abbricht
	 * @throws JSchException
	 *             Bei Problemen im Channel-Aufbau
	 * @throws IOException
	 *             Bei IO Problemen der Streams
	 */
	void copyToRemote(Resource inFile, String outFolder) throws ConnectionClosedException, JSchException, IOException;

	/**
	 * Fragt die Dateien eines Verzeichnisses ab.
	 *
	 * @param remoteWorkingDir
	 * @return Liste der Dateinamen
	 * @throws JSchException
	 *             Wenn es Probleme mit der Verbindung gibt
	 * @throws SftpException
	 *             Wenn es Problemen mit dem Abfragen gibt
	 */
	List<String> listRemoteFiles(String remoteWorkingDir) throws JSchException, SftpException;

	/**
	 * Fürht einen Befehl remote aus
	 * 
	 * @param command
	 * @return Die Ausgaben des Befehls
	 * @throws JSchException
	 *             Bei Problemen im Channel-Aufbau
	 * @throws IOException
	 *             Bei IO Problemen der Streams
	 */
	String sendCommand(String command) throws JSchException, IOException;
}
