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
	    <style>
	    .content {
	    	margin: 0px;
	    }
	    </style>
</tusp:head>
<tusp:body>
	<form:form method="POST" action="autos/userinfo" modelAttribute="auto">
		<tusp:form>
			<tusp:formRow label="autos.standort" fieldname="standort">
                <form:input path="standort" id="standort" class="form-control" />
            </tusp:formRow>
			<tusp:formRow label="autos.dieselOElektro" fieldname="dieselOElektro">
                <form:input path="dieselOElektro" id="dieselOElektro" class="form-control" />
            </tusp:formRow>
			<tusp:formRow label="autos.dieselTankkarteReserviert" fieldname="dieselTankkarteReserviert">
                <form:input path="dieselTankkarteReserviert" id="dieselTankkarteReserviert" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.reichweite" fieldname="reichweite">
                <form:input path="reichweite" id="reichweite" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.laenge" fieldname="laenge">
                <form:input path="laenge" id="laenge" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.breite" fieldname="breite">
                <form:input path="breite" id="breite" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.hoehe" fieldname="hoehe">
                <form:input path="hoehe" id="hoehe" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.ladevolumen" fieldname="ladevolumen">
                <form:input path="ladevolumen" id="ladevolumen" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.ladeflaeche" fieldname="ladeflaeche">
                <form:input path="ladeflaeche" id="ladeflaeche" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.ladeKante" fieldname="ladeKante">
                <form:input path="ladeKante" id="ladeKante" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.sitzplaetze" fieldname="sitzplaetze">
                <form:input path="sitzplaetze" id="sitzplaetze" class="form-control" />
            </tusp:formRow>
            <tusp:formRow label="autos.Anhaengerkopplung" fieldname="Anhaengerkopplung">
                <form:input path="Anhaengerkopplung" id="Anhaengerkopplung" class="form-control" />
            </tusp:formRow>         
		</tusp:form>
	</form:form>
</tusp:body>
</tusp:html>