package de.tuhh.tusp.global.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.psv.unihh.service.GewFileOutputService;
import de.tuhh.tusp.stu.gdm.pojo.History;
import de.tuhh.tusp.stu.gdm.service.HistoryService;
import de.tuhh.tusp.utils.MessageContainer;

/**
 * Stellt Methoden für die globale Administration bereit
 */
@Controller
@RequestMapping(value = "/global/gewlehrer/")
@AccessConfig(path = "/global/gewlehrer/**", access = { Rollen.ADMIN, Rollen.STU_SENIOR_MANAGER })
public class GlobalGewerbelehrerController {

	static Logger logger = LogManager.getLogger(GlobalGewerbelehrerController.class);

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private GewFileOutputService gewFileService;

	@Autowired
	private HistoryService gewHistoryService;

	@RequestMapping(value = "/gewerbelehrer/{fileName}", method = RequestMethod.GET)
	public void downloadGewerbelehrerFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {

		try {
			Resource resource = gewFileService.getFileByName(fileName);
			logger.debug("Liefere Resource aus" + resource);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"");
			org.apache.commons.io.IOUtils.copy(resource.getInputStream(), response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			String msg = "Konnte Gewerbelehrer-Datei nicht in OutputStream schreiben.";
			messageContainer.getErrors().add(msg);
			logger.error(msg, ex);
			throw new RuntimeException(ex);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/gewerbelehrer")
	public String showDownloadGewerbelehrerFiles(ModelMap model) {

		Map<String, List<History>> history = new HashMap<>();
		List<String> gewerbelehrerFiles = new ArrayList<>();
		try {
			for (Resource resource : gewFileService.listLokaleDateien()) {
				gewerbelehrerFiles.add(resource.getFilename());
				history.put(resource.getFilename(), gewHistoryService.findHistoryByFilename(resource.getFilename()));
				logger.debug("File: " + resource.getFilename());
			}
		} catch (IOException e) {
			String msg = "Konnte Gewerbelehrer-Datei nicht laden.";
			messageContainer.getErrors().add(msg);
			logger.error(msg, e);
		}

		model.put("gewFiles", gewerbelehrerFiles);
		model.put("gewFilesHistory", history);

		return "global/gewerbelehrer";
	}

}
