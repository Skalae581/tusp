<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<a href="<c:url value="/fame/user/" />" class="btn-m btn-default"><strong><tusp:out key="fame.home" /></strong></a>
<br />
<c:forEach var="w" items="${userWorkFlowsDetails}">
	<a href="<c:url value="/fame/user/workflow?id=${w.id}" />" class="btn-s btn-default">
		${w.name}&nbsp;
		<c:if test="${w.id != activeWorkflowId}">
			<span class="badge">${w.numVorgaenge}</span>
		</c:if>
	</a>
	<br />
	<c:if test="${w.id == activeWorkflowId}">
		<c:forEach var="s" items="${w.statuses}">
			&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/fame/user/status?statusId=${s.id}&instanceId=${w.id}" />" class="btn-s btn-default">${s.name}&nbsp;<span class="badge">${s.numVorgaenge}</span></a>
			<br />
		</c:forEach>
		&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value="/fame/user/searchform?workflowId=${w.workflowId}" />" class="btn-s btn-default"><tusp:out key="fame.user.menu_search"/></a>
		<br />
	</c:if>
</c:forEach>
