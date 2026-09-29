package de.tuhh.tusp.utils.ssh;

import com.jcraft.jsch.JSchException;

public interface SshUtils {

	/**
	 * Stellt eine SSH-Verbindung zum entfernten Server her. Nimmt dafür die KnownHosts aus der Config.
	 *
	 * @param user
	 * @param host
	 * @param port
	 * @throws JSchException
	 *             Wenn die Verbindung fehlschlägt
	 * @return Die Session
	 */
	SshSession getSession(String user, String host, int port) throws JSchException;

}
