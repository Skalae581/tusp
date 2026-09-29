package de.tuhh.tusp.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import de.tuhh.tusp.global.service.ConfigService;

/**
 * Überprüft die Laufzeit von Anfragen. Klasse wird als Filter vor jedem Request
 * ausgeführt.
 *
 * @author rztjm
 *
 */
public final class StopWatchFilter implements Filter {

	private static final String STOPWATCHFILTER_ENABLE_KEY = "de.tuhh.tusp.utils.StopWatchFilter_enabled";

	private static Logger logger = LogManager.getLogger(StopWatchFilter.class);

	private WebApplicationContext context;
	private Boolean enabled;

	/**
	 * Construct
	 *
	 * @param context
	 */
	public StopWatchFilter(WebApplicationContext context) {
		this.context = context;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (enabled == null) {
			ConfigService config = context.getBean(ConfigService.class);
			enabled = "true".equals(config.get(STOPWATCHFILTER_ENABLE_KEY, "false", true));
		}

		if (!enabled) {
			chain.doFilter(request, response);
			return;
		}

		StopWatch sw = new StopWatch();
		sw.start();
		chain.doFilter(request, response);
		sw.stop();

		Long time = sw.getTime(TimeUnit.MILLISECONDS);
		String message = "Ausführungszeit für " + ((HttpServletRequest) request).getRequestURL().toString() + " war "
				+ time + "ms";

		// Zeiten sind relativ willkürlich gewählt. Unter 300ms sollte aber
		// immer gut sein. Ab 600ms ist es definitiv
		// nerfig :)

		if (time < 300) {
			logger.debug(message);
		} else if (time < 600) {
			logger.info(message);
		} else {
			logger.warn(message);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}
}