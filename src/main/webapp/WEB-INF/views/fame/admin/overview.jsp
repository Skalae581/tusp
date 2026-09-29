<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<tusp:html>
<tusp:head titleCode="fame.admin.workflow_list_title">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/fame.css">
</tusp:head>
<tusp:body>

<div class="well well-sm">${workflow.name}</div>
	
 	<div class="">
	   	<div class="row equalheight" id="tables">
			
	   		<div class="col-md-3">
				<tusp:table data="${inputs}" >
					<tusp:column heading='fame.admin.input_list_title'>
						<a href="<c:url value="/fame/admin/input/show?id=${dataItem.id}" />" 
							class="btn btn-default width100"
							title="<spring:message code="btn.edit" />">
							${dataItem.name} (${dataItem.position}|${dataItem.tablePos})
						</a>
					</tusp:column>
				</tusp:table>
		    	<a href="<c:url value="/fame/admin/input/add?workflowId=${workflow.id}" />" class="btn btn-info width100"><spring:message code="btn.add" /></a>
		    </div>

	   		<div class="col-md-3">
				<tusp:table data="${stati}" >
					<tusp:column heading='fame.admin.status_list_title'>
						<a href="<c:url value="/fame/admin/status/show?id=${dataItem.id}" />" 
							class="btn btn-default width100"
							title="<spring:message code="btn.edit" />">
							${dataItem.name} (${dataItem.position})
						</a>
					</tusp:column>
				</tusp:table>
		    	<a href="<c:url value="/fame/admin/status/add?workflowId=${workflow.id}" />" class="btn btn-info width100"><spring:message code="btn.add" /></a>
			</div>
			
	   		<div class="col-md-3">
				<tusp:table data="${instances}" >
					<tusp:column heading='fame.admin.workflowinstance_list_title'>
						<a href="<c:url value="/fame/admin/workflowinstance/show?id=${dataItem.id}" />" 
							class="btn btn-default width100"
							title="<spring:message code="btn.edit" />">
							${dataItem.name}
						</a>
					</tusp:column>
				</tusp:table>
		    	<a href="<c:url value="/fame/admin/workflowinstance/add?workflowId=${workflow.id}" />" class="btn btn-info width100"><spring:message code="btn.add" /></a>
			</div>
			
	   		<div class="col-md-3">
	   			<table class="table  filter-">
					<tbody><tr class="thead">
						<th class="col0"><spring:message code="fame.sonstiges" /></th>
					</tr>
					<tr class="tdata"><td class="col0">
						<a href="<c:url value="/fame/admin/inputberechtigung/list?id=${workflow.id}" />" class="btn btn-success width100">
			   				<tusp:out key="fame.admin.edit.inputBerechtigung" />
			   			</a>
					</td></tr>
					<tr class="tdata"><td class="col0">
						<a href="<c:url value="/fame/admin/statusberechtigung/list?id=${workflow.id}" />" class="btn btn-success width100">
			   				<tusp:out key="fame.admin.edit.statusBerechtigung" />
			   			</a>
					</td></tr>
					<tr class="tdata"><td class="col0">
						<a href="<c:url value="/fame/admin/workflow/show?id=${workflow.id}" />" class="btn btn-success width100">
			   				<tusp:out key="fame.admin.edit.workflow" />
			   			</a>
					</td></tr>
					<tr class="tdata"><td class="col0">
						<a href="<c:url value="/fame/admin/workflow/inputValidationScript?id=${workflow.id}" />" class="btn btn-success width100">
			   				<tusp:out key="fame.admin.edit.inputValidationScript" />
			   			</a>
					</td></tr>
					<tr class="tdata"><td class="col0">
						<a href="<c:url value="/fame/admin/workflow/serialize?id=${workflow.id}" />" class="btn btn-success width100">
			   				<tusp:out key="fame.admin.edit.srialize" />
			   			</a>
					</td></tr>
					</tbody>
				</table>
			</div>
	    
	    </div>
    </div>

</tusp:body>
</tusp:html>