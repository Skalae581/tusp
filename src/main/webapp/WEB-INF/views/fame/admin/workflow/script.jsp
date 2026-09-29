<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head titleCode="fame.admin.workflow_list_title">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/fame.css">
	<style>
		#inputValidationCode{
			height: 500px;
		}
		.small {
			font-size: small;
		}
	</style>
</tusp:head>
<tusp:body>

    <c:url var="post_url" value="/fame/admin/workflow/inputValidationScript?id=${workflow.id}" />
    <tusp:panel heading="fame.admin.workflow_form">
        <form:form method="post" action="${post_url}" modelAttribute="workflow" id="form">
        	<input name="id" type="hidden" value="${workflow.id}" />
            <tusp:form>
				<div class="row">
					<div class="col-md-6">
                   		<form:textarea path="inputValidationCode" id="inputValidationCode" class="form-control" />
	                	<spring:message code="btn.save" var="submit_txt" />
	                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
	                	<a href="<c:url value="/fame/admin/overview?id=${workflow.id}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
					</div>
					<div class="col-md-6 small">
						<h1>Dokmentation</h1>
						<p>Für die Implementierung der Validierung kann <strong>JavaScript</strong> Code verwendet werden. 
						Er muss aber so geschrieben sein, dass er im Browser und in der Nashorn-Engine laufen kann.
						Die Validierung wird bei jeder Änderung und beim Speichern ausgeführt.</p>
						<p>Für den Zugriff auf die Werte von Eingabefeldern gibt es die Funktion <strong>valueOf(String id)</strong>.
						Sie gibt den Wert des Eingebafelds mit der entsprechenden ID zurück.</p>
						<p>Mit der <strong>fail(String message)</strong> Funktion kann auf Fehler hingewiesen werden. 
						Clientseitig erscheint eine Warnung, Serverseitig wird die Verarbeitng abgebrochen.
						Es wird die Message angezeigt.</p>
						<p>Die Funktion <strong>allowOptions(String id, Array options)</strong> grenzt die Auswahl in Selects ein. 
						Alle nicht erlaubten Werte können nicht ausgewählt/abgespeichert werden.
						Wenn options === 'all' ist, sind alle Optionen erlaubt.</p>
						<p>Um zu testen ob ein Wert gesetzt ist kann <strong>empty(value)</strong> genutzt werden. 
						Sowohl null als auch '' und 0 geben true zurück.</p>
						<p>Mit <strong>getName(String id)</strong> kannst du den Namen eines Eingabefeldes abrufen.</p>
						<p>Mit <strong>setValue(String fieldId, String newValue)</strong> kannst du den Wert eines Feldes ändern.</p>
						<p>Mit <strong>log(String message)</strong> kannst du etwas Protokollieren.</p>
						<p><strong>Eingabefelder:</strong></p>
						<table class="table table-sm">
							<tr>
								<th>Name</th>
								<th>Typ</th>
								<th>ID</th>
							</tr>
							<c:forEach items="${inputs}" var="input">
								<tr>
									<td>${input.name}</td>
									<td>
										<c:forEach items="${inputTypes}" var="type">
											<c:if test="${input.inputTypeId == type.id}">${type.name}</c:if>
										</c:forEach>
									</td>
									<td>value_${input.id}</td>
								</tr>
							</c:forEach>
							<tr>
								<td>Status</td>
								<td>SELECT</td>
								<td>statusId</td>
							</tr>
						</table>
						
						<p><strong>Status:</strong></p>
						<table class="table table-sm">
							<tr>
								<th>Name</th>
								<th>ID</th>
							</tr>
							<c:forEach items="${stati}" var="status">
								<tr>
									<td>${status.name}</td>
									<td>status_${status.id}</td>
								</tr>
							</c:forEach>
						</table>
						
						
					</div>
				</div>
            </tusp:form>
        </form:form>
    </tusp:panel>
    
</tusp:body>
</tusp:html>