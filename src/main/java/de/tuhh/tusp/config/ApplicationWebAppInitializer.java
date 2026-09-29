package de.tuhh.tusp.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import de.tuhh.tusp.utils.StopWatchFilter;
import de.tuhh.tusp.utils.XssFilter;
import de.tuhh.tusp.webservice.config.WebserviceWebConfig;

/**
 *
 * @author rztjj Default Spring Konfigurationsklasse die Root- und Servlet Konfigurationsklassen definiert sowie Servlet
 *         Mappings
 */
public class ApplicationWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private WebApplicationContext context;

	@Override
	protected WebApplicationContext createRootApplicationContext() {

		context = super.createRootApplicationContext();
		return context;
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {

		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

	/**
	 * Jede neue Konfigurationsklasse muss hier eingetragen werden
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] { ApplicationRootConfig.class, DatabaseConfig.class, WebserviceWebConfig.class,
				SecurityConfiguration.class };
	}

	/**
	 * Jede Web-Config Klasse muss hier eingetragen werden
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] { ApplicationWebConfig.class };

	}

	/**
	 * Hier können Filter eingetragen werden, die vor jedem Request serverseitig ausgeführt werden sollen
	 */
	@Override
	protected Filter[] getServletFilters() {

		return new Filter[] { new StopWatchFilter(context), new XssFilter(context) };
	}

	/**
	 * Bei Tusp ist '/' Root-Mapping
	 */
	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}
}