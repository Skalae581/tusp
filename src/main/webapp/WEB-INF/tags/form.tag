<%@ tag language="java" pageEncoding="UTF-8" description="erstellt ein Form"  %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
jspContext.setAttribute("form_contains_pflicht", false, PageContext.REQUEST_SCOPE);
%>

<div class='form-horizontal'>

	<jsp:doBody />
	
	<c:if test="${form_contains_pflicht}">
		<span><spring:message code="form.pflichtfeld" /></span>
	</c:if>

</div>

<%
jspContext.setAttribute("form_contains_pflicht", false, PageContext.REQUEST_SCOPE);
%>