<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="fame.admin.workflow_list_title" /></title>
	<jsp:include page="styles.jsp" />
</tusp:head>
<tusp:body>
	<div class="row">
		<div class="col-md-2">
			<jsp:include page="include_menu.jsp" />
		</div>
		<div class="col-md-10">
			<c:forEach items="${userWorkFlowsDetails}" var="wf">
	
				<c:if test="${wf.id == activeWorkflowId}">
					<div class="panel panel-default">
						<div class="panel-heading">${wf.name}</div>
						<div class="panel-body">
							<c:forEach items="${wf.statuses}" var="s">
								<a href="<c:url value="/fame/user/status?statusId=${s.id}&instanceId=${wf.id}" />" class="btn btn-default  ${s.numVorgaenge == 0 ? "understate" : ""}">
									${s.name}&nbsp;<span class="badge ${s.numVorgaenge > 10 ? "highlight" : ""}">${s.numVorgaenge}</span>
								</a>
							</c:forEach>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</div>

</tusp:body>
</tusp:html>