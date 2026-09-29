<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
</tusp:head>
<tusp:body>

	<p><spring:message code="userinfo.user"/><c:out value="${user}" /></p>
	
	<p><spring:message code="userinfo.rollen"/></p>
	<ul>
		<c:forEach items="${rollen}" var="rolle">
			<li><c:out value="${rolle}" /></li>
		</c:forEach>
	</ul>
	
	
	<p><spring:message code="userinfo.displayName"/> ${displayName}</p>
	<p><spring:message code="userinfo.email"/> ${email}</p>
	<p><spring:message code="userinfo.server"/> ${server}</p>
</tusp:body>
</tusp:html>