package de.tuhh.tusp.stu.sptim.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class SyscallUtilsImpl implements SyscallUtils {

	@Getter
	public class CmdFaildException extends RuntimeException {

		private static final long serialVersionUID = -76955866717577575L;
		String cmd;
		int exitVal;
		String output;

		/**
		 * Construct
		 *
		 * @param cmd
		 * @param exitVal
		 * @param output
		 */
		public CmdFaildException(String cmd, int exitVal, String output) {
			this.cmd = cmd;
			this.exitVal = exitVal;
			this.output = output;
		}

	}

	private static Logger logger = LogManager.getLogger(SyscallUtils.class);

	@Override
	public String runCmd(File workdir, String... cmd) throws IOException, InterruptedException, CmdFaildException {

		ProcessBuilder processBuilder = new ProcessBuilder();

		logger.debug("Run cmd: " + Arrays.toString(cmd) + " in " + workdir);
		processBuilder.command(cmd);
		processBuilder.directory(workdir);

		Process process = processBuilder.start();

		StringBuilder output = new StringBuilder();
		String line;

		output.append("Stderr:\n");
		BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		while ((line = err.readLine()) != null) {
			output.append(line + "\n");
		}

		output.append("Stdout:\n");
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		while ((line = reader.readLine()) != null) {
			output.append(line + "\n");
		}

		int exitVal = process.waitFor();
		logger.debug("Exit Code " + exitVal + " for " + Arrays.toString(cmd));
		logger.debug(output.toString());
		if (exitVal != 0) {
			throw new CmdFaildException(Arrays.toString(cmd), exitVal, output.toString());
		}

		return output.toString();
	}
}
