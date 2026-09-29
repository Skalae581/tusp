package de.tuhh.tusp.utils.ssh;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSchException;

import de.tuhh.tusp.global.pojo.Identity;
import de.tuhh.tusp.global.pojo.KnownHost;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.IdentityService;
import de.tuhh.tusp.global.service.KnownHostService;

/**
 *
 * Interessantes Beispiel:
 *
 * @see https://medium.com/@ldclakmal/scp-with-java-b7b7dbcdbc85
 *
 * @author rztjm
 *
 */
@Component
public class SshUtilsImpl implements SshUtils {

	private static Logger logger = LogManager.getLogger(SshUtilsImpl.class);

	@Autowired
	private KnownHostService knownHostService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private ConfigService config;

	@Override
	public SshSession getSession(String user, String host, int port) throws JSchException {

		if ("localhost".equals(host) || "127.0.0.1".equals(host) || "::1".equals(host)) {
			return new LocalhostSession();
		}

		List<KnownHost> knownHosts = knownHostService.findValid();

		List<Identity> identities = identityService.findValid();

		String passphrase = config.get("tusp.transfer.hash", null);

		try {
			return new SshSessionImpl(user, host, port, knownHosts, identities, passphrase);
		} catch (JSchException e) {
			logger.error("SSH Session konnte nicht erstellt werden", e);
			if (e.getMessage().contains("reject HostKey")) {
				throw new JSchException("Für host " + host
						+ " ist kein Hostkey konfiguriert. Lege einen Eintrag über die Weboberfläche an.", e);
			}
			throw e;
		}
	}
}
