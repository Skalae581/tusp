<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<tusp:html>
<tusp:head>
 	<title><spring:message code="autos.title" /></title>  
	    <script src="${pageContext.servletContext.contextPath}/resources/js/autos/autos.js"></script>
	    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/autos/autos.css" />
	    
	   	<script src="${pageContext.servletContext.contextPath}/resources/js/autos/main.js"></script>
	    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/autos/main.css" />
	    
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/autos/benutzer/userinfo" />

<tusp:panel heading="Buchung Zeitraum">

	<div class="row">
		<div class="col-sm-9">
			<form:form method="POST" action="${post_url}" modelAttribute="buchung" autocomplete="off">
				<tusp:form>
					<tusp:formRow label="buchung.von" fieldname="von">
		                <form:input path="von" id="von" class="form-control" />
		            </tusp:formRow>
					<tusp:formRow label="buchung.bis" fieldname="bis">
		                <form:input path="bis" id="bis" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="" fieldname="">
		                <a href="<c:url value="/autos/benutzer/autosinfo" />" class="btn btn-default"><spring:message code="Zurück" /></a>
		                <input type="submit" value="Weiter" class="btn btn-default" />
		            </tusp:formRow>
				</tusp:form>
			</form:form>
			
			<input type="hidden" id="jsonData" name="jsonData" value='<c:out value="${jsonData}"></c:out>'/>
		</div>
	
	</div>
	<p>Auto ID: &nbsp;${auto.id} </p>
	<p>Buchungen: &nbsp; ${buchungen} </p>
	<p>JsonData: &nbsp; ${jsonData} </p>
	
	
	
	
	<div id='calendar'></div>
	</tusp:panel>


	
	
</tusp:body>
</tusp:html>