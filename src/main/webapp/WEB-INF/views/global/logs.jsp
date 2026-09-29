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

	<ul>
		<c:forEach items="${files}" var="path">
			<li><a href="?path=${path }">${path }</a></li>
		</c:forEach>
	</ul>

	<pre><c:out value="${content }" /></pre>
	
</tusp:body>
</tusp:html>