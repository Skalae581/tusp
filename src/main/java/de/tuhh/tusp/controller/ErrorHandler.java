package de.tuhh.tusp.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.UriUtils;

import de.tuhh.tusp.global.service.ConfigService;
import de.tuhh.tusp.utils.AuthorityUtils;
import de.tuhh.tusp.utils.CalendarUtils;
import de.tuhh.tusp.utils.HostUtils;

public class ErrorHandler {

	private static Logger logger = LogManager.getLogger(ErrorHandler.class);

	private Exception exception;
	private MessageSource messageSource;
	private ConfigService config;
	private AuthorityUtils authorityUtils;
	private Long errorId;
	private Locale locale;
	private String userAgent;
	private String queryString;

	/**
	 * Construct
	 */
	public ErrorHandler(Exception e, ApplicationContext context, HttpServletRequest request) {
		this.exception = e;
		this.messageSource = context.getBean(MessageSource.class);
		this.config = context.getBean(ConfigService.class);
		this.locale = LocaleContextHolder.getLocale();
		this.errorId = new Date().getTime();
		this.userAgent = request.getHeader("user-agent");
		this.authorityUtils = context.getBean(AuthorityUtils.class);
		this.queryString = request.getRequestURL().toString();
		if (request.getQueryString() != null) {
			this.queryString += "?" + request.getQueryString();
		}

		logger.error("Unbehandelter Fehler mit ID " + this.errorId, e);

		logger.info(queryString);
		logger.info(userAgent);
		logger.info(mapToString(request.getParameterMap()));
	}

	public long getErrorId() {

		return this.errorId;
	}

	public String getMailLink() throws UnsupportedEncodingException {

		StringBuilder body = new StringBuilder();
		body.append("Hallo Servicedesk,\n");
		body.append(messageSource.getMessage("error.question.trying", null, locale) + "\n");
		body.append(messageSource.getMessage("error.question.expacting", null, locale) + "\n");
		body.append(messageSource.getMessage("error.question.actual", new String[] { getTitle() }, locale) + "\n");
		body.append("\n");
		body.append(messageSource.getMessage("error.question.tecnical_information", null, locale) + "\n");
		body.append("Server: " + getServer() + "\n");
		body.append("ID: " + errorId + "\n");
		body.append("Browser: " + userAgent + "\n");
		body.append("URL: " + queryString + "\n");
		if (authorityUtils.getCurrentUserName() != null) {
			body.append("Username: " + authorityUtils.getCurrentUserName() + "\n");
		}
		body.append("\n");
		body.append(messageSource.getMessage("error.question.additional", null, locale) + "\n");
		body.append("\n");
		body.append(messageSource.getMessage("error.question.greetings", null, locale) + "\n");

		StringBuilder sb = new StringBuilder();
		sb.append("mailto:" + getMailto() + "?");
		sb.append("subject=" + UriUtils.encodeQueryParam(getTitle(), "UTF-8") + "&");
		sb.append("body=" + UriUtils.encodeQueryParam(body.toString(), "UTF-8"));

		return sb.toString();
	}

	public String getMailto() {

		return config.get("de.tuhh.tusp.service_address", "servicedesk+tune@tuhh.de", true);
	}

	public String getMessage() {

		if (this.exception instanceof NoHandlerFoundException) {
			return messageSource.getMessage("error.404.message", null, locale);
		}
		if (this.exception instanceof org.jooq.exception.DataAccessException) {
			Throwable rootCause = this.exception;
			while (rootCause.getCause() != null) {
				rootCause = rootCause.getCause();
			}

			if (rootCause instanceof java.net.ConnectException
					|| rootCause instanceof java.net.SocketTimeoutException) {
				return messageSource.getMessage("error.500.database_unreachable", null, locale);
			} else {
				return messageSource.getMessage("error.500.database_error", null, locale);
			}
		}

		return messageSource.getMessage("error.500.message", null, locale);
	}

	public String getServer() {

		return HostUtils.getServername();
	}

	public String getTime() {

		return CalendarUtils.format(new Date(this.getErrorId()), CalendarUtils.DATETIMEFORMAT_GERMAN);
	}

	public String getTitle() {

		if (this.exception instanceof NoHandlerFoundException) {
			return messageSource.getMessage("error.404.title", null, locale);
		}
		return messageSource.getMessage("error.500.title", null, locale);
	}

	private String mapToString(Map<String, String[]> map) {

		StringBuilder sb = new StringBuilder();
		sb.append("Map(");
		for (String key : map.keySet()) {
			sb.append(key);
			sb.append("=");
			String[] val = map.get(key);
			sb.append("[");
			for (Object item : val) {
				sb.append(item);
				sb.append(", ");
			}
			if (((Object[]) val).length > 0) {
				sb.delete(sb.length() - 2, sb.length());
			}
			sb.append("]");

			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(")");
		return sb.toString();
	}
}
