<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
	<style>
	.col1 {
/* 		word-break: break-all; */
	}
	.col5 {
		word-break: keep-all;
	}
	</style>
</tusp:head>
<tusp:body>

	<form method="post" action="">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div class="row">
			<div class="col-md-4">
				<input class="form-control" type="date" placeholder="Von" name="start" value="${start}" />
			</div>
			<div class="col-md-4">
				<input class="form-control" type="date" placeholder="Bis" name="ende" value="${ende}" />
			</div>
			<div class="col-md-4">
				<input class="form-control" type="submit"/>
			</div>
		</div>
	</form>
	<tusp:table data="${jobs}">
		<tusp:column heading="global.jobs.id" property="id" href="global/admin/job?id=[id]" />
		<tusp:column heading="global.jobs.name" property="name" />
		<tusp:column heading="global.jobs.status">
			<c:choose>
				<c:when test="${dataItem.status == 'Pending'}">
					<span class="label label-info">${dataItem.status}</span>
				</c:when>
				<c:when test="${dataItem.status == 'Done'}">
					<span class="label label-success">${dataItem.status}</span>
				</c:when>
				<c:when test="${dataItem.status == 'Running'}">
					<span class="label label-warning">${dataItem.status}</span>
				</c:when>
				<c:otherwise>
					<span class="label label-danger">${dataItem.status}</span>
				</c:otherwise>
			</c:choose>
		</tusp:column>
		<tusp:column heading="global.jobs.start" property="start" />
		<tusp:column heading="global.jobs.ende" property="ende" />
		<tusp:column heading="global.jobs.server" property="server" />
	</tusp:table>
	
</tusp:body>
</tusp:html>