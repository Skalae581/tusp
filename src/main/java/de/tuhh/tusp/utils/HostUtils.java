package de.tuhh.tusp.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HostUtils {

	private static Logger logger = LogManager.getLogger(HostUtils.class);

	public static String getServername() {

		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			logger.error("Konnte Servernamen nicht abfragen.", e);
			return "Unbekannter Server";
		}
	}
}
