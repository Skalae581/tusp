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
import de.tuhh.tusp.stu.tuhhstgs.pojo.StgHistory;
import de.tuhh.tusp.stu.tuhhstgs.service.StgFileOutputService;
import de.tuhh.tusp.stu.tuhhstgs.service.StgHistoryService;
import de.tuhh.tusp.utils.MessageContainer;

@Controller
@RequestMapping(value = "/global/stgs")
@AccessConfig(path = "/global/stgs/**", access = { Rollen.ADMIN, Rollen.STU_SENIOR_MANAGER })
public class GlobalStgsController {

	static Logger logger = LogManager.getLogger(GlobalStgsController.class);

	@Autowired
	private MessageContainer messageContainer;
	@Autowired
	private StgFileOutputService stgFileService;
	@Autowired
	private StgHistoryService stgHistoryService;

	@RequestMapping(value = "/stgsdaten/{fileName}", method = RequestMethod.GET)
	public void downloadGebuehrenFile(@PathVariable("fileName") String fileName, HttpServletResponse response) {

		try {
			Resource resource = stgFileService.getFileByName(fileName);
			logger.debug("Liefere Resource aus" + resource);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + resource.getFilename() + "\"");
			org.apache.commons.io.IOUtils.copy(resource.getInputStream(), response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			String msg = "Konnte Zahlungsdatei nicht in OutputStream schreiben.";
			messageContainer.getErrors().add(msg);
			logger.error(msg, ex);
			throw new RuntimeException(ex);
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/stgsdaten")
	public String showDownloadStgFiles(ModelMap model) {

		Map<String, List<StgHistory>> history = new HashMap<>();
		List<String> stgsFiles = new ArrayList<>();

		try {
			for (Resource resource : stgFileService.listLokaleDateien()) {
				stgsFiles.add(resource.getFilename());
				stgHistoryService.findHistoryByFilename(resource.getFilename());
				history.put(resource.getFilename(), stgHistoryService.findHistoryByFilename(resource.getFilename()));

			}
		} catch (IOException e) {
			String msg = "Konnte Studiengänge nicht laden.";
			messageContainer.getErrors().add(msg);
			logger.error(msg, e);
		}

		model.put("stgsFiles", stgsFiles);
		model.put("stgsFilesHistory", history);

		return "global/stgs/stgs";

	}

}
