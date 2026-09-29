package de.tuhh.tusp.app.vorabcheck.controllers;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.utils.InMemoryResource;

@Controller
@RequestMapping(value = "/ampelsystem")
@SessionAttributes("ampelsystem")
@AccessConfig(path = "/ampelsystem/**", access = { Rollen.ADMIN, Rollen.APP_ADMIN, Rollen.MITARBEITER })

public class AmpelsystemController {

	static Logger logger = LogManager.getLogger(AmpelsystemController.class);

	@Autowired
	private BewVervollstController bewVervollstController;

	/**
	 * Ruft das Formular zum Auswählen / Hochladen auf
	 *
	 * @return Hochladen-Formular
	 */
	@RequestMapping(value = "/vervollstTest", method = RequestMethod.GET)
	public String vervollstTest() {

		return "vorabcheck/admin/vervollstForm";
	}

	@RequestMapping(value = "/vervollstTest", method = RequestMethod.POST)
	public void vervollstTestPost(@RequestParam("file") MultipartFile file, HttpServletResponse response,
			ModelMap model) {

		try {
			InMemoryResource inFile = new InMemoryResource(file.getName());
			IOUtils.copy(file.getInputStream(), inFile.getOutputStream());
			Resource outFile = bewVervollstController.generatePdf(inFile);

			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + outFile + ".pdf");
			IOUtils.copy(outFile.getInputStream(), response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("DownloadError", e);
		}
	}
}
