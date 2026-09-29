package de.tuhh.tusp.utils.ssh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.util.Streams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class LocalhostSession implements SshSession {

	private static Logger logger = LogManager.getLogger(LocalhostSession.class);

	@Override
	public void close() {
	}

	@Override
	public void copyFromRemote(WritableResource outfile, String remoteWorkingDir, String remoteFileName)
			throws SftpException, JSchException, IOException, RuntimeException {

		File file = new File(remoteWorkingDir + remoteFileName);
		logger.info("Read from file " + file.getAbsolutePath());
		FileInputStream fis = new FileInputStream(file);

		Streams.copy(fis, outfile.getOutputStream(), false);
	}

	@Override
	public void copyToRemote(Resource inFile, String outFilePath)
			throws ConnectionClosedException, JSchException, IOException {

		File file = new File(outFilePath + inFile.getFilename());
		logger.info("Write to file " + file.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(file);

		Streams.copy(inFile.getInputStream(), fos, true);
	}

	@Override
	public List<String> listRemoteFiles(String remoteWorkingDir) throws JSchException, SftpException {

		File file = new File(remoteWorkingDir);
		if (!file.isDirectory() || !file.exists()) {
			throw new RuntimeException(remoteWorkingDir + " is not a directory on localhost");
		}

		List<String> result = new ArrayList<>();

		for (File f : file.listFiles()) {
			result.add(f.getName());
		}

		return result;
	}

	@Override
	public String sendCommand(String command) throws JSchException, IOException {

		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("bash", "-c", command);

		try {

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			logger.debug("'" + command + "' endet mit Exitcode " + exitVal);

			return output.toString();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
