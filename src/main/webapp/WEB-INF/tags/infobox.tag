<%@tag import="org.springframework.context.MessageSource"%>
<%@tag import="org.springframework.web.context.WebApplicationContext"%>
<%@tag import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@tag import="java.util.Locale"%>
<%@tag import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@tag language="java" pageEncoding="UTF-8" description="Tag um localized Strings auszugeben"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="messages" required="false" type="java.util.List" description="Liste mit Nachrichten, die angezeigt werden sollen" %>
<%@ attribute name="message" required="false" type="java.lang.String" description="Nachricht die angezeigt werden kann" %>
<%@ attribute name="clazz" required="true" type="java.lang.String" description="CSS-Klasse" %>

<c:if test="${!empty messages}">
	<div class='alert ${pageScope.clazz}' role='alert'>
			<ul>
				<c:forEach items="${pageScope.messages}" var="msg">
					<li><c:out value="${msg}" /></li>
				</c:forEach>
			</ul>
	</div>
</c:if>


<c:if test="${not empty pageScope.message}">
	<div class='alert ${pageScope.clazz}' role='alert'>
		<c:out value="${pageScope.message}" />
	</div>
</c:if>