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
 	<title><spring:message code="reservierung.title" /></title>  
	    <script src="${pageContext.servletContext.contextPath}/resources/js/saeulen/saeulen.js"></script>
	    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/saeulen/saeulen.css" />
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/saeulen/saeulen/endseiteReserv" />

<tusp:panel heading="Reservierung Zeitraum">

	<div class="row">
		<div class="col-sm-9">
			<form:form method="POST" action="${post_url}" modelAttribute="saeule" autocomplete="off">
				<tusp:form>
					<tusp:formRow label="reservierung.von" fieldname="von">
		                <form:input path="von" id="von" class="form-control" />
	                </tusp:formRow>
					<tusp:formRow label="reservierung.bis" fieldname="bis">
		                <form:input path="bis" id="bis" class="form-control" />
	                </tusp:formRow>
					<tusp:formRow label="reservierung.begruendung" fieldname="begruendung">
		                <form:input path="begruendung" id="begruendung" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="reservierung.saeule_id" fieldname="saeule_id">
		                <form:input path="saeule_id" id="saeule_id" class="form-control" />
	                </tusp:formRow>
		            <tusp:formRow label="" fieldname="">
		                <a href="<c:url value="/saeulen/startseite" />" class="btn btn-default"><spring:message code="Zurück" /></a>
		                <input type="submit" value="Weiter" class="btn btn-default" />
		            </tusp:formRow>
				</tusp:form>
			</form:form>
		</div>
	</div>
	</tusp:panel>
</tusp:body>
</tusp:html>
