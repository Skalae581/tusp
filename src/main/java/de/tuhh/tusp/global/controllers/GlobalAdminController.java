package de.tuhh.tusp.global.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import javax.security.auth.login.AccountException;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import de.his.ws.accountservice.AccountInfo;
import de.tuhh.tusp.app.SemesterCleanable;
import de.tuhh.tusp.app.SemesterCleanable.NotImplementedException;
import de.tuhh.tusp.config.AccessConfig;
import de.tuhh.tusp.global.Rollen;
import de.tuhh.tusp.global.pojo.Config;
import de.tuhh.tusp.global.pojo.Job;
import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.global.service.JobService;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.MessageContainer;
import de.tuhh.tusp.utils.schedule.Schedulable;
import de.tuhh.tusp.webservice.exception.ServiceFault;
import de.tuhh.tusp.webservice.wsoutput.dao.HisAccountHelperDao;

/**
 * Stellt Methoden für die globale Administration bereit
 */
@Controller
@RequestMapping(value = "/global/admin")
@AccessConfig(path = "/global/admin/**", access = { Rollen.ADMIN })
public class GlobalAdminController {

	private static Logger logger = LogManager.getLogger(GlobalAdminController.class);

	@Autowired
	private HisAccountHelperDao accountDao;

	@Autowired
	private MessageContainer messageContainer;

	@Autowired
	private JobService jobService;

	@Autowired
	private ConfigService config;

	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/createJob", method = RequestMethod.GET)
	public String createJob(ModelMap model) {

		Map<String, String> classes = new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				return o1.compareTo(o2);
			}
		});

		String[] beansNames = context.getBeanNamesForType(Schedulable.class);
		for (String className : beansNames) {
			Schedulable bean = (Schedulable) context.getBean(className);
			classes.put(bean.getClass().getName(), bean.suggestName());
		}

		model.addAttribute("classes", classes);

		return "global/admin/createjob";
	}

	@RequestMapping(value = "/createJob", method = RequestMethod.POST)
	public ModelAndView createJob(ModelMap model, String clazz, String name) {

		Job job = jobService.registerJob(clazz, name);
		messageContainer.addSuccessKey("global.job.angelegt", name);

		return new ModelAndView("redirect:../job?id=" + job.getId());
	}

	@RequestMapping(value = "/semesterbereinigung", method = RequestMethod.GET)
	public String semesterBereinigung(ModelMap model) {

		List<String> classes = new ArrayList<>();
		List<String> semester = new ArrayList<>();

		for (String className : context.getBeanNamesForType(SemesterCleanable.class)) {
			SemesterCleanable bean = (SemesterCleanable) context.getBean(className);
			classes.add(bean.getClass().getName());
			try {
				for (String sem : bean.getSemester()) {
					if (!semester.contains(sem)) {
						semester.add(sem);
					}
				}
			} catch (NotImplementedException e) {
				// Ist ok
				logger.debug(className + " implementiert getSemester() nicht");
			}
		}

		model.addAttribute("semester", semester);
		model.addAttribute("classes", classes);

		return "global/admin/semesterbereinigung";
	}

	@RequestMapping(value = "/semesterbereinigung", method = RequestMethod.POST)
	public String semesterBereinigung(ModelMap model, String semester) {

		for (String className : context.getBeanNamesForType(SemesterCleanable.class)) {
			SemesterCleanable bean = (SemesterCleanable) context.getBean(className);
			try {
				Integer number = bean.cleanSemeser(semester);
				messageContainer.addSuccessKey("global.semesterbereinigung.success", bean.getClass().getName(), number);
			} catch (Exception e) {
				messageContainer.addErrorKey("global.semesterbereinigung.error", className, e.getMessage());
				logger.error("Semesterbereinigung ist fehlgeschlagen", e);
			}
		}

		return semesterBereinigung(model);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/job")
	public String showJob(ModelMap model, Long id) {

		model.put("job", jobService.fetchById(id));

		return "global/job";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/jobs")
	public String showJobs(ModelMap model) {

		int days = 86400000 * 2;
		Date now = new Date();
		Date start = new Date(now.getTime() - days);

		model.put("start", CalendarUtils.dateToDatumsString(start, Locale.ENGLISH));
		model.put("ende", CalendarUtils.dateToDatumsString(now, Locale.ENGLISH));
		model.put("jobs", Lists.reverse(jobService.findByTimespan(start, now)));

		return "global/jobs";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/jobs")
	public String showJobsPost(ModelMap model, String start, String ende) {

		Date st = new Date();
		try {
			st = CalendarUtils.stringToDate(start + " 00:00:00");
		} catch (ParseException e) {
			messageContainer.addErrorKey("Start nicht parsbar");
		}
		Date en = new Date();
		try {
			en = CalendarUtils.stringToDate(ende + " 23:59:59");
		} catch (ParseException e) {
			messageContainer.addErrorKey("Ende nicht parsbar");
		}

		model.put("start", CalendarUtils.dateToDatumsString(st, Locale.ENGLISH));
		model.put("ende", CalendarUtils.dateToDatumsString(en, Locale.ENGLISH));
		model.put("jobs", Lists.reverse(jobService.findByTimespan(st, en)));

		return "global/jobs";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logs")
	public String showLog(ModelMap model, String path) {

		ArrayList<String> logfiles = new ArrayList<>();

		// Altes Code
//		LoggerContext logContext = LogManager.getContext(false);
//
//		Map<String, Appender> map = logContext.getConfiguration().getRootLogger().getAppenders();
//
//		for (Map.Entry<String, Appender> entry : map.entrySet()) {
//			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//
//			Appender app = entry.getValue();
//			if (app instanceof FileAppender) {
//				logfiles.add(((FileAppender) app).getFile());
//			}
//		}

		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		RollingFileAppender techical = (RollingFileAppender) config.getAppender("ROLLING_FILE");
		File logFile = new File(techical.getFileName());
		logfiles.add(logFile.getPath());

		model.put("files", logfiles);

		if (path != null) {
			File file = new File(path);

			if (!file.exists()) {
				model.put("content", "Logfile existiert nicht.");
			} else if (!file.canRead()) {
				model.put("content", "Logfile ist nicht lesbar.");
			} else {
				InputStream in = null;
				try {
					in = new FileInputStream(file);
					model.put("content", IOUtils.toString(in));
				} catch (Exception ex) {

					model.put("content", ex.getMessage());
				} finally {
					IOUtils.closeQuietly(in);
				}
			}
		}

		return "global/logs";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String uebersicht(ModelMap model) throws IOException {

		// Properties laden
		List<SimpleEntry<String, String>> properties = new ArrayList<>();
		for (Entry<Object, Object> entry : config.getPropertiesFromConfigfile().entrySet()) {
			String key = (String) entry.getKey();
			if (key.toLowerCase().contains("password")) {
				properties.add(new SimpleEntry<>(key, "*****"));
			} else {
				properties.add(new SimpleEntry<>(key, (String) entry.getValue()));
			}
		}
		properties.sort((a, b) -> a.getKey().compareTo(b.getKey()));
		model.addAttribute("properties", properties);

		// Version laden
		Resource version = new ClassPathResource("version.txt");
		if (!version.exists()) {
			model.addAttribute("version", "unbekant");
		} else {
			String result = IOUtils.toString(version.getInputStream());
			model.addAttribute("version", result);
		}

		return "global/uebersicht";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/userprefs")
	public String userprefs(ModelMap model, @RequestParam(name = "username", required = false) String username) {

		if (username == null) {
			return "global/admin/userprefs";
		}

		try {
			AccountInfo account = accountDao.fetchAccountByUsername(username);
			if (account == null) {
				throw new AccountException();
			}
			model.put("username", username);
			model.put("account", account);

			model.put("configRoles", config.findByKeyPrefix(ConfigService.ROLES_KEY + username));
			model.put("userPrefs", config.findByKeyPrefix(ConfigService.USERPREFS_KEY + username));

			model.put("userPrefsPrefix", ConfigService.USERPREFS_KEY + username);
			model.put("newRolesKey", ConfigService.ROLES_KEY + username + "." + UUID.randomUUID().toString());
		} catch (ServiceFault e) {
			String msg = "Konnte Account " + username + " nicht laden";
			logger.error(msg, e);
			messageContainer.addErrorKey(msg);
		} catch (AccountException e) {
			messageContainer.addErrorKey("Es existiert Kein Benutzer " + username);
		}

		return "global/admin/userprefs";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/userprefs/delete")
	public String userprefsDelete(ModelMap model, String username, Long id) {

		config.delete(id);

		messageContainer.addSuccessKey("success.delete");

		return userprefs(model, username);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/userprefs/save")
	public String userprefsSave(ModelMap model, String username, Long id, String value, String key) {

		if (id == 0) {
			Config c = new Config();
			c.setKey(key);
			c.setValue(value);
			config.insert(c);
		} else {
			Config c = config.fetchById(id);
			c.setValue(value);
			config.update(c);
		}

		messageContainer.addSuccessKey("success.update");

		return userprefs(model, username);
	}
}
