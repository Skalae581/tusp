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

    <tusp:panel heading="vorabcheck.result_vorabcheck.titel">
	  	<p><spring:message code="vorabcheck.result_vorabcheck.textVorher" />
	  	<c:choose>
		  <c:when test="${result=='gruen'}">
		    <spring:message code="vorabcheck.result_vorabcheck.textGruen" />
		  </c:when>
		  <c:when test="${result=='gelb'}">
		    <spring:message code="vorabcheck.result_vorabcheck.textOrange" />
		  </c:when>
		   <c:when test="${result=='rot'}">
		    <spring:message code="vorabcheck.result_vorabcheck.textRot" />
		  </c:when>
		</c:choose>
		<spring:message code="vorabcheck.result_vorabcheck.textNachher" />
	  	</p>
	  	<p><spring:message code="vorabcheck.result_vorabcheck.textAbschluss" /></p>
	  	
	  	<spring:message code="btn.backToBegin" var="submit_txt" />
	    <input type="text" value="${submit_txt}" class="btn btn-default" onclick="closeWindow('/tusp/vorabcheck/');"/>
	  	
    </tusp:panel>

</tusp:body>
</tusp:html>