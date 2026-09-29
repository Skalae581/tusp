package de.tuhh.tusp.utils.ssh;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.HostKey;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import de.tuhh.tusp.global.pojo.Identity;
import de.tuhh.tusp.global.pojo.KnownHost;

public class SshSessionImpl implements SshSession {

	private static Logger logger = LogManager.getLogger(SshSessionImpl.class);

	/**
	 *
	 * @param in
	 * @return 0 for success, 1 for error, 2 for fatal error, -1 sometimes ;)
	 * @throws IOException Wenn der Stream abbricht
	 */
	private static int checkAck(InputStream in) throws IOException {

		int b = in.read();
		// b may be 0 for success,
		// 1 for error,
		// 2 for fatal error,
		// -1
		if (b == 0) {
			return b;
		}
		if (b == -1) {
			return b;
		}

		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				logger.error(sb.toString());
			}
			if (b == 2) { // fatal error
				logger.error(sb.toString());
			}
		}
		return b;
	}

	private Session session;

	/**
	 * Erstellt eine SSH-Verbindung zum entfernten Server her
	 *
	 * @param user
	 * @param host
	 * @param port
	 * @param knownHosts
	 * @param identities
	 * @param passphrase Passwort zum entschlüsseln der Private Keys
	 * @throws JSchException Wenn die Verbindung fehlschlägt
	 * @throws IOException   Wenn der PrivateKey nicht erreichbar ist
	 */
	SshSessionImpl(String user, String host, int port, List<KnownHost> knownHosts, List<Identity> identities,
			String passphrase) throws JSchException {

		if (knownHosts.isEmpty()) {
			throw new JSchException("KnownHosts darf nicht leer sein!. Lege einen Eintrag über die Weboberfläche an.");
		}
		if (passphrase == null || passphrase.isEmpty()) {
			throw new JSchException(
					"Das Passwort für die privaten Schlüssel ist nicht gesetzt (System-Property 'tusp.transfer.hash')");
		}
		if (identities.isEmpty()) {
			throw new JSchException(
					"Identities dürfen nicht leer sein!. Lege einen Eintrag über die Weboberfläche an.");
		}

		JSch.setLogger(new JSchLogger());
		JSch jsch = new JSch();

		// Add Known Hosts
		for (KnownHost knownHost : knownHosts) {
			byte[] base64Bytes = knownHost.getHostKey().getBytes();
			byte[] rawBytes = Base64.getDecoder().decode(base64Bytes);

			logger.debug("KnownHost: " + knownHost.getHostname());
			HostKey hk = new HostKey(knownHost.getHostname(), rawBytes);
			jsch.getHostKeyRepository().add(hk, null);
		}

		// Add Identities
		for (Identity identity : identities) {
			logger.debug("Use Identity: " + identity.getName());
			jsch.addIdentity(identity.getName(), identity.getPrivateKey().replace("\r", "").getBytes(),
					identity.getPublicKey().replace("\r", "").getBytes(), passphrase.getBytes());
		}

		// Start Connection
		session = jsch.getSession(user, host, port);
		Hashtable<String, String> config = new Hashtable<>();
		config.put("StrictHostKeyChecking", "yes"); // niemals no!!
		config.put("server_host_key", "ecdsa-sha2-nistp256");
		session.setConfig(config);
		passphrase = passphrase == null ? "null" : passphrase;
		logger.debug("SSH Connect " + user + "@" + host + ":" + port);
		session.connect();
	}

	@Override
	public void close() {

		if (session == null) {
			return;
		}

		session.disconnect();
		session = null;
	}

	@Override
	public void copyFromRemote(WritableResource outfile, String remoteWorkingDir, String remoteFileName)
			throws SftpException, JSchException, IOException {

		if (session == null) {
			throw new ConnectionClosedException("Versuch auf einer geschlossenen Verbindung zu arbeiten.");
		}

		Channel channel = session.openChannel("sftp");
		channel.connect();
		logger.debug("Verbindung mit Remote Shell erfolgreich...");
		ChannelSftp channelSftp = (ChannelSftp) channel;
		channelSftp.cd(remoteWorkingDir);

		@SuppressWarnings("unchecked")
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(remoteFileName);

		if (list.size() != 1) {
			throw new RuntimeException("Es wurde nicht eine sondern " + list.size() + " Dateien gefunden.");
		}

		ChannelSftp.LsEntry entry = list.get(0);
		logger.debug("Datei : [" + entry.getFilename() + "] wird heruntergeladen...");
		byte[] buffer = new byte[1024];
		BufferedInputStream bis = new BufferedInputStream(channelSftp.get(entry.getFilename()));

		OutputStream os = outfile.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		int readCount;
		while ((readCount = bis.read(buffer)) > 0) {
			bos.write(buffer, 0, readCount);
		}
		bis.close();
		bos.close();

		channel.disconnect();

	}

	@Override
	public void copyToRemote(Resource inFile, String outFilePath) throws JSchException, IOException {

		if (session == null) {
			throw new ConnectionClosedException("Versuch auf einer geschlossenen Verbindung zu arbeiten.");
		}

		// exec 'scp -t rfile' remotely
		String command = "scp -p -t " + outFilePath;
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);

		// get I/O streams for remote scp
		InputStream in = channel.getInputStream();
		channel.connect();

		if (checkAck(in) != 0) {
			throw new ConnectionClosedException("Connction verloren!");
		}

		long modTime = inFile.lastModified() / 1000 + 9 * 3600;
		command = "T" + modTime + " 0";
		command += " " + modTime + " 0\n";
		OutputStream out = channel.getOutputStream();
		out.write(command.getBytes());
		out.flush();
		if (checkAck(in) != 0) {
			throw new ConnectionClosedException("Connction verloren!");
		}

		// send "C0644 filesize filename", where filename should not include '/'
		String from = inFile.getFilename();
		long filesize = inFile.contentLength();
		command = "C0644 " + filesize + " ";
		if (from.lastIndexOf('/') > 0) {
			command += from.substring(from.lastIndexOf('/') + 1);
		} else {
			command += from;
		}

		command += "\n";
		out.write(command.getBytes());
		out.flush();

		if (checkAck(in) != 0) {
			throw new ConnectionClosedException("Connction verloren!");
		}

		// send a content of inFile
		InputStream is = inFile.getInputStream();
		byte[] buf = new byte[1024];
		while (true) {
			int len = is.read(buf, 0, buf.length);
			if (len <= 0) {
				break;
			}
			out.write(buf, 0, len);
			out.flush();
		}

		// send '\0'
		buf[0] = 0;
		out.write(buf, 0, 1);
		out.flush();

		if (checkAck(in) != 0) {
			throw new ConnectionClosedException("Connction verloren!");
		}
		out.close();

		try {
			if (is != null) {
				is.close();
			}
		} catch (Exception ex) {
			logger.error("Konnte InputStream nicht schließen", ex);
		}

		channel.disconnect();
	}

	@Override
	public List<String> listRemoteFiles(String remoteWorkingDir) throws JSchException, SftpException {

		Channel channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp channelSftp = (ChannelSftp) channel;
		channelSftp.cd(remoteWorkingDir);
		logger.debug("Ordner " + remoteWorkingDir + " wird nach Dateien durchgesucht...");

		List<String> result = new ArrayList<>();
		@SuppressWarnings("unchecked")
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*");
		for (ChannelSftp.LsEntry entry : list) {
			result.add(entry.getFilename());
		}
		return result;
	}

	@Override
	public String sendCommand(String command) throws JSchException, IOException {

		StringBuilder outputBuffer = new StringBuilder();

		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);
		InputStream commandOutput = channel.getInputStream();
		channel.connect();
		int readByte = commandOutput.read();

		while (readByte != 0xffffffff) {
			outputBuffer.append((char) readByte);
			readByte = commandOutput.read();
		}

		channel.disconnect();
		return outputBuffer.toString();
	}

}
