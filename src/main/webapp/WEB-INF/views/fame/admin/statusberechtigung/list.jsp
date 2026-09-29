<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<tusp:html>
<tusp:head titleCode="fame.admin.workflow_list_title">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/fame.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/fame/statusberechtigung.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-combobox.js"></script>
	<style>
	.cell {font-size: 10px;}
	</style>
</tusp:head>
<tusp:body>

	<table class="table" id="statusberechtigung" data-workflowid="${workflow.id}">
		<tr>
			<td>Von \ Nach</td>
			<c:forEach items="${stati}" var="status">
				<td class="status ${status.id == empf ? 'empf' : ''}" data-id="${status.id}">${status.name}</td>
			</c:forEach>
		</tr>
		<c:forEach items="${stati}" var="statusFrom">
			<tr class="${statusFrom.id == empf ? 'empf' : ''}">
				<td class="statusFrom" data-id="${statusFrom.id}">${statusFrom.name}</td>
				<c:forEach items="${stati}" var="statusTo">
					<c:choose>
						<c:when test="${statusFrom.id == statusTo.id && statusFrom.hidden == 1}">
							<td class="${statusTo.id == empf ? 'empf' : ''}">-</td>
						</c:when>
						<c:when test="${statusFrom.id == statusTo.id}">
							<td class="${statusTo.id == empf ? 'empf' : ''}">Alle</td>
						</c:when>
						<c:otherwise>
							<td class="statusFrom${statusFrom.id} statusTo${statusTo.id} cell ${statusTo.id == empf ? 'empf' : ''}" id="td${statusTo.id}-${statusFrom.id}">
								<span class="glyphicon glyphicon-plus add" aria-hidden="true" data-statusToId="${statusTo.id}" data-statusFromId="${statusFrom.id}"></span>
							</td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	
	
   	<a href="<c:url value="/fame/admin/overview?id=${workflow.id}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
					
	
	<div id="addForm" style="display:none">
		<tusp:select id="rolle" items="${rollen}" keyCol="id" valCol="name" clazz="combobox form-control input-xs"></tusp:select><br />
		<button class='btn btn-default btn-xs'><tusp:out key="btn.add"/></button>
	</div>
</tusp:body>
</tusp:html>
