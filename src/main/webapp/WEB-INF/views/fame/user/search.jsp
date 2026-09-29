<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title><spring:message code="fame.user.searchresult_title" /></title>
    
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
								<input type="text" name="search" class="form-control" placeholder="Suchwort" value="${word}"/>
						    </div>
						    <div class="col-md-3">
								<input type="submit" class="btn btn-default" value="<tusp:out key="btn.search" />"/>
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">Ergebnisse</div>
				<div class="panel-body">
					<tusp:table data="${ergebnisse}" headerfix="true" sortable="true" filter="column" >
						<tusp:column heading="" headingStatic="true" nofilter="true" style="width: 100px">
							<input type='hidden' class='vorgangId' value="${dataItem.vorgang.id}" />
							<a 
								href="<c:url value="/fame/user/vorgang?id=${dataItem.vorgang.id}" />" 
								class="btn btn-default" 
								title="<tusp:out key="fame.user.vorgang_ansehen" />">
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
							</a>
							<a 
								href="<c:url value="/fame/user/vorgang?id=${dataItem.vorgang.id}&edit=true" />" 
								class="btn btn-default" 
								title="<tusp:out key="fame.user.vorgang_bearbeiten" />">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</a>
						</tusp:column>
						<tusp:column heading="fame.search.vorkommen" headingStatic="false" style="width: 250px; word-wrap: break-word;">
							${dataItem.inputName}: ${dataItem.value.eingabe}
						</tusp:column>
						<tusp:column heading="fame.search.details" headingStatic="false">
							<c:forEach items="${dataItem.description}" var="feld">
								${feld.inputName}: ${feld.eingabe}<br>
							</c:forEach>
						</tusp:column>
					</tusp:table>
				</div>
			</div>
		</div>
	</div>

</tusp:body>
</tusp:html>