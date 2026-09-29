package de.tuhh.tusp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.pojo.User;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.HostUtils;
import de.tuhh.tusp.utils.auth.ShibbolethTooken;
import de.tuhh.tusp.webservice.utils.WebserviceFactory;

@Controller
public class ApplicationHomeController {

	private static Logger logger = LogManager.getLogger(ApplicationHomeController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ConfigService configService;

	@Autowired
	private WebserviceFactory factory;

	@Autowired
	private AuthorityUtils authUtils;

	@RequestMapping(value = "/accessDenied", method = { RequestMethod.GET, RequestMethod.POST })
	public String accessDeniedPage(ModelMap model) {

		model.addAttribute("user", authUtils.getCurrentUserName());
		return "accessDenied";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@ModelAttribute("user") User user, ModelMap model, String message) {

		// Authentifizierte Nutzer brauchen kein Loginform
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!"anonymousUser".equals(auth.getName())) {
			return "redirect:/";
		}

		model.addAttribute("message", message);
		model.addAttribute("form", "true".equals(configService.get("de.tuhh.tusp.enable_loginform", "false", true)));

		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Boolean isShibboleth = null;
		if (auth != null) {
			isShibboleth = auth.getCredentials() instanceof ShibbolethTooken;
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		Locale locale = LocaleContextHolder.getLocale();

		if (isShibboleth == null) {
			request.setAttribute("title", messageSource.getMessage("logout.not_loggedin.title", null, locale));
			request.setAttribute("message", messageSource.getMessage("logout.not_loggedin.message", null, locale));
		} else if (isShibboleth) {
			request.setAttribute("title", messageSource.getMessage("logout.shibboleth.title", null, locale));
			request.setAttribute("message", messageSource.getMessage("logout.shibboleth.message", null, locale));
		} else {
			request.setAttribute("title", messageSource.getMessage("logout.done.title", null, locale));
			request.setAttribute("message", messageSource.getMessage("logout.done.message", null, locale));
		}

		return "error";
	}

	@RequestMapping(value = "/shib", method = RequestMethod.GET)
	public String shibHomePage() {

		return index();
	}

	@RequestMapping(value = "/shibstart", method = RequestMethod.GET)
	public String shibstartPage(HttpServletRequest request, HttpServletResponse response) {

		String localhost = request.getServerName();

		return "redirect:https://" + localhost + "/Shibboleth.sso/Login?target=https%3A%2F%2F" + localhost
				+ "/tusp/shib";
	}

	/**
	 * Controller Mappting, dass vom Spong überwacht wird. Der StatusCode 500
	 * signalisiert für Spong ein Problem.
	 *
	 * @param response
	 * @param applicationContext
	 * @param userService
	 * @return view name
	 */
	@AccessConfig(access = Rollen.PUBLIC, path = "/spong")
	@RequestMapping(value = "/spong", method = RequestMethod.HEAD)
	public String spong(HttpServletResponse response) {

		try {
			configService.testConnection();
			response.addHeader("Datenbank", "OK");
		} catch (Exception e) {
			logger.error("Spong Test fehlgeschlagen", e);
			response.addHeader("Datenbank", e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		Reflections ref = new Reflections("de.his.ws", new TypeAnnotationsScanner(), new SubTypesScanner());
		for (Class<?> cl : ref.getTypesAnnotatedWith(WebServiceClient.class)) {
			try {
				factory.getNewWebservicePort(cl);
				response.addHeader(cl.getSimpleName(), "OK");
			} catch (Exception e) {
				logger.error("Spong Test fehlgeschlagen", e);
				response.addHeader(cl.getSimpleName(), e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}

		return "index";
	}

	@AccessConfig(path = "/userinfo", access = { Rollen.PUBLIC })
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public String userinfo(ModelMap model) {

		String username = authUtils.getCurrentUserName();
		String displayName = configService.get(ConfigService.USERPREFS_KEY + username + ".displayName", username, true);
		String email = configService.get(ConfigService.USERPREFS_KEY + username + ".email", "", true);

		model.addAttribute("user", username);
		model.addAttribute("rollen", authUtils.getUserAuthorities());
		model.addAttribute("displayName", displayName);
		model.addAttribute("email", email);
		model.addAttribute("server", HostUtils.getServername());

		return "global/user_info";
	}
}