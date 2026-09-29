package de.tuhh.tusp.app.precheck.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import de.tuhh.tusp.app.precheck.service.InteressentService;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;

@Controller
@RequestMapping(value = "/precheck/verifier/")
@AccessConfig(path = "/precheck/verifier/", access = { Rollen.PUBLIC })
public class VerifierController {

	@Autowired
	private InteressentService interessentService;

	/**
	 * Handler für den Test ob ein Code gültig ist
	 *
	 * @param response
	 * @param request
	 * @return Y wenn gültig, sonst N
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String verify(HttpServletRequest request) {

		String code = request.getParameter("verification");
		boolean exists = interessentService.getCodeIfExists(code);

		if (exists == true) {
			return "Y";
		} else {
			return "N";
		}

	}
}
