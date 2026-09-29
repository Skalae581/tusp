package de.tuhh.tusp.utils.ssh;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.tuhh.tusp.config.ApplicationIntegrationTestConfig;
import de.tuhh.tusp.global.pojo.Identity;
import de.tuhh.tusp.global.pojo.KnownHost;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.IdentityService;
import de.tuhh.tusp.global.service.KnownHostService;
import de.tuhh.tusp.testutils.TestResource;

@ContextConfiguration(classes = { ApplicationIntegrationTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Component
public class SshSessionImplIT {

	@Autowired
	private ConfigService config;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private KnownHostService knownHostService;

	@Test
	public void copyToRemoteAndBack() throws Exception {

		//
		String user = config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.user", "");
		String host = config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.remoteHosts", "");
		int port = config.get("de.tuhh.tusp.stu.gdm.service.fileOutput.port", 22);

		if (host.contains(",")) {
			// in diesem Test nur den ersten Server nutzen!
			host = host.substring(0, host.indexOf(','));
		}

		String outPath = "/tmp/test.txt";
		byte[] testData = "Hallo Welt!".getBytes();
		Resource inFile = new TestResource(testData, "test.txt", 0);

		List<KnownHost> knownHosts = knownHostService.findValid();
		List<Identity> identities = identityService.findValid();
		String passphrase = config.get("tusp.transfer.hash");

		// Datei schreiben
		SshSessionImpl ssh = new SshSessionImpl(user, host, port, knownHosts, identities, passphrase);
		ssh.copyToRemote(inFile, outPath);
		ssh.close();

		// Wieder lesen
		TestResource out = new TestResource();
		ssh = new SshSessionImpl(user, host, port, knownHosts, identities, passphrase);
		ssh.copyFromRemote(out, "/tmp/", "test.txt");

		assertEquals(new String(testData), new String(out.getBytes()));
	}
}
