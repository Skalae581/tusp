<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<tusp:html>
<tusp:head titleCode="fame.admin.workflow_list_title">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/fame.css">
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/fame/inputberechtigung.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui-combobox.js"></script>
	<style>
	.cell {font-size: 10px;}
	</style>
</tusp:head>
<tusp:body>

	<table class="table" id="inputberechtigung" data-workflowid="${workflow.id}">
		<tr>
			<td>Feld \ Status</td>
			<c:forEach items="${stati}" var="status">
				<td class="status" data-id="${status.id}">${status.name}</td>
			</c:forEach>
		</tr>
		<c:forEach items="${inputs}" var="input">
			<tr class="${input.id == empf ? 'empf' : ''}">
				<td class="status" data-id="${input.id}">${input.name}</td>
				<c:forEach items="${stati}" var="status">
					<td class="status${status.id} input${input.id} cell" id="td${status.id}-${input.id}">
						<span class="glyphicon glyphicon-plus add" aria-hidden="true" data-statusId="${status.id}" data-inputId="${input.id}"></span>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	
   	<a href="<c:url value="/fame/admin/overview?id=${workflow.id}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
					
	
	<div id="addForm" style="display:none">
		<tusp:select id="rolle" items="${rollen}" keyCol="id" valCol="name" clazz="combobox form-control input-xs"></tusp:select><br />
		<tusp:select id="rechte" items="${rechte}" keyCol="name" valCol="name" clazz="form-control input-xs"></tusp:select><br />
		<button class='btn btn-default btn-xs'><tusp:out key="btn.add"/></button>
	</div>
</tusp:body>
</tusp:html>
