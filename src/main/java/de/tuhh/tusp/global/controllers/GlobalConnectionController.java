package de.tuhh.tusp.global.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcraft.jsch.JSchException;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.MessageContainer;
import de.tuhh.tusp.utils.XssFilter;
import de.tuhh.tusp.utils.ssh.SshSession;
import de.tuhh.tusp.utils.ssh.SshUtils;

@Controller
@RequestMapping(value = "/global/admin/connection")
@AccessConfig(path = "/global/admin/connection/**", access = { Rollen.ADMIN })
public class GlobalConnectionController {

	private static Logger logger = LogManager.getLogger(GlobalConnectionController.class);

	@Autowired
	private SshUtils sshUtils;

	@Autowired
	private MessageContainer messages;

	@Autowired
	private ConfigService config;

	protected GlobalConnectionController() {
		XssFilter.disableFor("/global/admin/connection/test");
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testPost(ModelMap model, String user, String host, String port, String dir, String cmd) {

		model.addAttribute("user", user == null ? "" : user);
		model.addAttribute("port", port == null ? "22" : port);
		model.addAttribute("host", host == null ? "" : host);

		boolean secured = config.get("de.tuhh.tusp.unsecure_ssh_testing_allowed", "false").equals("false");

		if (secured) {
			dir = cmd = "Disabled";
		}
		// beide Funktionen sollten nicht dauerhaft in der GUI sein!
		model.addAttribute("dir", dir == null ? "" : dir);
		model.addAttribute("cmd", cmd == null ? "" : cmd);

		if (host != null) {
			try {
				SshSession session = sshUtils.getSession(user, host, Integer.parseInt(port));
				messages.addSuccessKey("global.connection.ok");

				if (!secured && !dir.isEmpty()) {
					try {
						List<String> files = session.listRemoteFiles(dir);
						model.addAttribute("files", files);
					} catch (Exception e) {
						messages.addErrorKey("global.connection.list.fail", e.getMessage());
						logger.error("SSH-List-Dir fehlgeschlagen", e);
					}
				}

				if (!secured && !cmd.isEmpty()) {
					try {
						String data = session.sendCommand(cmd);
						model.addAttribute("cmdOutput", data);
					} catch (Exception e) {
						messages.addErrorKey("global.connection.run.fail", e.getMessage());
						logger.error("SSH-Run-CMD fehlgeschlagen", e);
					}
				}
			} catch (NumberFormatException | JSchException e) {
				messages.addErrorKey("global.connection.fail", e.getMessage());
				logger.error("SSH-Test fehlgeschlagen", e);
			}
		}

		return "/global/admin/connection/test";
	}
}
