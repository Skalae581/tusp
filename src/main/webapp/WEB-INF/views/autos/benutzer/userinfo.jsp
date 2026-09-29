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
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/autos/benutzer/overviewpage" />

<tusp:panel heading="Person Info">

	<div class="row">
		<div class="col-sm-9">
			<form:form method="POST" action="${post_url}" modelAttribute="avlUser" id="userForm">
				<tusp:form>
					<tusp:formRow label="avlUser.vorname" fieldname="vorname">
		                <form:input path="vorname" id="vorname" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="avlUser.nachname" fieldname="nachname">
		                <form:input path="nachname" id="nachname" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="avlUser.tel" fieldname="tel">
		                <form:input path="tel" id="tel" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="avlUser.email" fieldname="email">
		                <form:input path="email" id="email" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="avlUser.institut" fieldname="institut">
		                <form:input path="institut" id="institut" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="avlUser.fuererschein" fieldname="fuererschein">
		                <form:input path="fuererschein" id="fuererschein" class="form-control" />
		            </tusp:formRow>
		            <tusp:formRow label="avlUser.gueltigkeit" fieldname="gueltigkeit">
		                <form:input path="gueltigkeit" id="gueltigkeit" class="form-control" />
		            </tusp:formRow>
		                <tusp:formRow label="" fieldname="">
		                
		                
		                    <a href="<c:url value="/autos/benutzer/buchung" />" 
		                    		  class="btn btn-default">
		                             <spring:message code="Zurück" /></a> 
		                    
		                    
		                    <input type="submit" value="Weiter" class="btn btn-default" />
		                </tusp:formRow>      
				</tusp:form>
			</form:form> 
		</div>
		
	</div>
		
</tusp:panel>
	
	
</tusp:body>
</tusp:html>