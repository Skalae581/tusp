<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
</tusp:head>
<tusp:body>

	<ul>
		<c:forEach items="${gebuehrenFiles}" var="fileName">
			<li>
				<a href="./${fileName}">${fileName}</a>
				<ul>
					<c:forEach items="${gebuehrenFilesHistory[fileName]}" var="history">
						<li><fmt:formatDate value="${history.zeit}" pattern="dd.MM.yyyy HH:mm:ss" /> runtergeladen von ${history.username}</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
	
</tusp:body>
</tusp:html>