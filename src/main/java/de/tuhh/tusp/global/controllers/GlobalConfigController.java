package de.tuhh.tusp.global.controllers;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.MessageContainer;

/**
 * Stellt Methoden für die globale Administration der Configuration bereit
 */
@Controller
@RequestMapping(value = "/global/admin/config")
@AccessConfig(path = "/global/admin/config/**", access = { Rollen.ADMIN })
public class GlobalConfigController {

	static Logger logger = LogManager.getLogger(GlobalConfigController.class);

	private static final String HIDDEN_PWD = "**********";

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private ConfigService configService;

	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public String configDelete(ModelMap model, String prefix, Long id) {

		try {
			if (configService.delete(id) > 0) {
				messageContainer.addSuccessKey("success.delete");
			}
			return configList(model, prefix);
		} catch (Exception e) {
			messageContainer.addSuccessKey(e.getMessage());
			return configEdit(model, prefix, id);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/edit")
	public String configEdit(ModelMap model, String prefix, Long id) {

		// Bei Passwörtern darf das PWD nicht gezeigt werden!!
		Config c = configService.fetchById(id);
		if (isPassword(c)) {
			c.setValue(HIDDEN_PWD);
			model.put("isPassword", true);
		}

		model.put("prefix", prefix);
		model.put("config", c);

		return "global/admin/config/edit";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/edit")
	public String configEditPost(ModelMap model, String prefix, Long id, String value, String key) {

		// XSS-Filter escaped, dass muss rückgängig!
		value = StringEscapeUtils.unescapeHtml(value);

		// Passworter nur aktualisieren, wenn sie geändert wurden
		if (HIDDEN_PWD.equals(value)) {
			return configEdit(model, prefix, id);
		}

		try {
			if (id == null) {
				Config config = new Config();
				config.setKey(key.trim());
				config.setValue(value);
				id = configService.insert(config);
				messageContainer.addSuccessKey("success.insert");
			} else {
				Config config = configService.fetchById(id);
				config.setValue(value);
				configService.update(config);
				messageContainer.addSuccessKey("success.update");
			}
		} catch (Exception e) {
			messageContainer.addErrorKey("backend.message", e.getMessage());
		}

		return configEdit(model, prefix, id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public String configList(ModelMap model, @RequestParam(name = "prefix", required = false) String prefix) {

		prefix = prefix == null ? "" : prefix;

		List<Config> configs = configService.findByKeyPrefix(prefix);
		for (Config config : configs) {
			if (isPassword(config)) {
				config.setValue(HIDDEN_PWD);
			}
		}

		model.put("prefix", prefix);
		model.put("configs", configs);

		return "global/admin/config/list";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/new")
	public String configNew(ModelMap model, String prefix) {

		model.put("prefix", prefix);
		model.put("config", new Config());

		return "global/admin/config/edit";
	}

	private boolean isPassword(Config c) {

		String key = c.getKey().toLowerCase();
		return key.contains("pwd") || key.contains("passwor");

	}
}
