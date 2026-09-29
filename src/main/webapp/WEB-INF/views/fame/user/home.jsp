<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


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
		
			<div class="panel panel-default">
				<div class="panel-heading">Allgemeine Suche</div>
				<div class="panel-body">
					<c:url value="/fame/user/search2" var="formurl"/>
					<form method="get" action="${formurl}">
					  	<div class="row">
						    <div class="col-md-4">
								<input type="text" name="search" class="form-control" placeholder="Suchwort"/>
						    </div>
						    <div class="col-md-3">
								<input type="submit" class="btn btn-default" value="<tusp:out key="btn.search" />"/>
							</div>
						</div>
					</form>
				</div>
			</div>
		
			<sec:authorize access="hasAnyRole('ADMIN', 'ZULBEARB')">
				<div class="panel panel-default">
					<div class="panel-heading">Import nach Bewerbernummer</div>
					<div class="panel-body">
						<c:url value="/fame/user/search" var="formurl"/>
						<form method="get" action="${formurl}">
						  	<div class="row">
							    <div class="col-md-4">
									<input type="text" name="search" class="form-control" placeholder="Bewerbernummer"/>
							    </div>
							    <div class="col-md-3">
									<input type="submit" class="btn btn-default" value="<tusp:out key="fame.workflow.start_import" />"/>
								</div>
							</div>
						</form>
						<c:forEach items="${imported}" var="entry">
							<a href="<c:url value="/fame/user/vorgang?id=${entry.key}" />" class="btn btn-default">
								${entry.value}
							</a>
						</c:forEach>
					</div>
				</div>
			</sec:authorize>
		
			<c:forEach items="${userWorkFlowsDetails}" var="wf">
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
			</c:forEach>
		</div>
	</div>
</tusp:body>
</tusp:html>