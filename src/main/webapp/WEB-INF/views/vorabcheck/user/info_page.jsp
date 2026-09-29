<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
     <title><spring:message code="vorabcheck.menu" /> ${vorabInteressent.stgName}</title>
     <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
</tusp:head>
<tusp:body vollebreite="false">

    <tusp:panel heading="vorabcheck.info_vorabcheck.titel">

    <c:choose>
		<c:when test="${!empty standardkz or standardkz == 'N'}">
	 		<p><spring:message code="vorabcheck.ects.null" /></p>
		</c:when>
		<c:otherwise>

	    <c:url var="post_url" value="/vorabcheck/ects_page" />
	    <form:form method="post" action="${post_url}" modelAttribute="vorabInteressent" id="vorabInteressent">
	    	<h4><spring:message code="vorabcheck.info_vorabcheck.titel1" /></h4>
	    	<p><spring:message code="vorabcheck.info_vorabcheck.einl1" arguments="${vorabInteressent.stgName}" argumentSeparator=";" /></p>
	    	<br>
	    	<h4><spring:message code="vorabcheck.info_vorabcheck.titel2" /></h4>
	    	<p><spring:message code="vorabcheck.info_vorabcheck.einl2" arguments="${vorabInteressent.stgName}" argumentSeparator=";"/></p>
	    	<br>
	    	<div class="vorabcheckBeispiel">
		    	<p>Beispiel:</p>
		    	<p>In Modul XY wurden 5 ECTS vergeben, dabei wurde zum Großteil "Höhere Analysis und Formenkalkül" und zum kleinen Teil "Informatik" gelehrt.</p>
		    	<table>
		    		<tr>
		    		<td>Richtige Eintragung:</td>
		    		<td>"Höhere Analysis und Formenkalkül"	</td>
		    		<td>= 4 ECTS</td>
		    		</tr>
		    		<tr>
		    		<td></td>
		    		<td>"Informatik"</td>
		    		<td>= 1 ECTS</td>
		    		</tr>
		    		<tr><td>&nbsp;</td></tr>
		    		<tr>
		    		<td>Falsche Eintragung:</td>
		    		<td>"Höhere Analysis und Formenkalkül"	</td>
		    		<td>= 5 ECTS</td>
		    		</tr>
		    		<tr>
		    		<td></td>
		    		<td>"Informatik"</td>
		    		<td>= 5 ECTS</td>
		    		</tr>
	    		</table>
	    	</div>
	    	<br>
	    	<p><spring:message code="vorabcheck.info_vorabcheck.einl3" /></p>
	    	<br>
	    	<p><spring:message code="vorabcheck.info_vorabcheck.schlusswort" arguments="${vorabInteressent.stgName}" argumentSeparator=";"/></p>
	    	<br>
	    	<spring:message code="btn.next" var="submit_txt" />
	        <input type="submit" value="${submit_txt}" class="btn btn-default" />
		</form:form>
		</c:otherwise>
		</c:choose>
    </tusp:panel>

</tusp:body>
</tusp:html>