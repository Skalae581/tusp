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

	
	<a 
		href="<c:url value="/global/admin/config/new?prefix=${prefix}" />" 
		class="btn btn-default" 
		title="<tusp:out key="btn.new" />">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
	</a>
	<tusp:table data="${configs}" filter="column" sortable="true">
		<tusp:column heading="">
		<a 
			href="<c:url value="/global/admin/config/edit?id=${dataItem.id}&prefix=${prefix}" />" 
			class="btn btn-default" 
			title="<tusp:out key="btn.edit" />">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		</a>
		</tusp:column>
		<tusp:column heading="global.admin.config.key">
			${dataItem.key}
		</tusp:column>
		<tusp:column heading="global.admin.config.value">
			<c:choose>
				<c:when test="${fn:length(dataItem.value) ge 75}">${fn:substring(dataItem.value, 0, 75)}...</c:when>
				<c:otherwise>${dataItem.value}</c:otherwise>
			</c:choose>
		</tusp:column>
	</tusp:table>

</tusp:body>
</tusp:html>