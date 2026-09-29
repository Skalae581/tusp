package de.tuhh.tusp.config;

import java.io.IOException;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import de.tuhh.tusp.utils.CustomViewResolver;
import de.tuhh.tusp.utils.MessageContainer;
import de.tuhh.tusp.utils.TuspMessageSource;

/**
 *
 * @author rztjj
 *
 *         Web-Konfiguration Klasse für das gesamte Projekt.
 *
 */

@Configuration
public class ApplicationWebConfig extends WebMvcConfigurerAdapter {

	/**
	 * Sprach-Parameter (lang=de oder lang=en)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		registry.addInterceptor(interceptor);
	}

	/**
	 * Resourcen css, js usw.
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * Diese Methode sucht alle jsp Views
	 *
	 * @return jsp resolver Objekt
	 */
	@Bean
	public ViewResolver genericViewResolver() {

		CustomViewResolver resolver = new CustomViewResolver();
		resolver.setViewClass(JstlView.class);

		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(0);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	/**
	 * Falls die Requests zu keinem Servlet weitergeleitet werden können, wird der
	 * Spring-Dispatcher alle Requests an das Default-Servlet wieterleiten
	 */
	/*
	 * @Override public void
	 * configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	 *
	 * // Bloß nicht, da wir dann keine 404 Seite mehr erzeugen können!!
	 *
	 * configurer.enable();
	 *
	 * }
	 */

	/**
	 *
	 * @return einen Cookie (die Locale-Eingeschaften speichert) falls keiner
	 *         gefunden. Default Sprache ist 'de'
	 */
	@Bean
	public LocaleResolver localeResolver() {

		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("de"));
		resolver.setCookieName("TuSPLanguageCookie");
		resolver.setCookieMaxAge(604800);// eine Woche in Sekunden
		return resolver;
	}

	/**
	 * MessageContainer Bean
	 *
	 * @return MessageContainer Klasse sehe(utils/MessageContainer.java)
	 */
	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public MessageContainer messageContainer() {

		return new MessageContainer();
	}

	/**
	 * Diese Methode gibt ein Bean der überschriebenen MessageSource-Klasse zurück.
	 * Hier werden alle Sprach-Properties Dateien eingetragen (Sehe:
	 * config/TuspMessageSourse.java)
	 *
	 * @return neue TuspMessageSource-Instanz
	 */
	@Bean
	public MessageSource messageSource() {

		TuspMessageSource messageSource = new TuspMessageSource(new Locale("de"));
		messageSource.setBasenames(messageSource.scanForBasenames());
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public MultipartResolver multipartResolver() throws IOException {

		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		return multipartResolver;
	}

	/**
	 * Views-Properties aufrufen für pdfView. Dadurch wird die PdfBuilder-Klasse in
	 * de.tuhh.tusp.app.vorabcheck.utils aufgerufen, welche dann für den View-Namen
	 * "pdfView" die PDF-Ansicht generiert
	 *
	 * HowTo: PdfBuilder.java in eigenen (utils-)Ordner der Anwendung kopieren und
	 * anpassen, dort wird die PDF-Ansicht aufgebaut, nutzt die Klasse
	 * de.tuhh.tusp.utils.AbstractITextPdfView.java
	 *
	 * @return ResourceBundleViewResolver
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public ResourceBundleViewResolver pdfViewResolver() {

		ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();

		resolver.setBasename("views");
		resolver.setOrder(0);
		return resolver;
	}
}