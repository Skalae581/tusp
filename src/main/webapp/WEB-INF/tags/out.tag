<%@tag import="org.springframework.context.MessageSource"%>
<%@tag import="org.springframework.web.context.WebApplicationContext"%>
<%@tag import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@tag import="java.util.Locale"%>
<%@tag import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@tag language="java" pageEncoding="UTF-8" description="Tag um localized Strings auszugeben"  %>

<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="key" required="true" type="java.lang.String" description="Code mit dem der Seitentitel geladen werden kann" %>
<%
	// Hier wurde bewusst kein Zeilenumbruch gemacht, da diese ausgegeben werden und dann der HTML-Code schwer lesbar wird

	WebApplicationContext context = 
	RequestContextUtils.findWebApplicationContext(request);
	Locale locale = LocaleContextHolder.getLocale();
	MessageSource ms = context.getBean(MessageSource.class);
	String message = ms.getMessage(key, null, locale);
	jspContext.getOut().print(message);
%>