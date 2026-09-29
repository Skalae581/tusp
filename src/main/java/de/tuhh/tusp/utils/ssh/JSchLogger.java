package de.tuhh.tusp.utils.ssh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jcraft.jsch.JSch;

public class JSchLogger implements com.jcraft.jsch.Logger {

	private static final Logger logger = LogManager.getLogger(JSch.class);

	@Override
	public boolean isEnabled(int pLevel) {

		return true; // here, all levels enabled
	}

	@Override
	public void log(int level, String message) {

		logger.debug(message);
	}
}
