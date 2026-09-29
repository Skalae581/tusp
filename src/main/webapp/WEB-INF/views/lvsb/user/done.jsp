<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="lvsb.title" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
</tusp:head>
<tusp:body vollebreite="false">
    <tusp:panel heading="lvsb.header">
        <p>
            <spring:message code="lvsb.done" />
        </p>
		<br>
    	<c:url var="post_backToBegin" value="/lvsb/start" />
    	<form:form method="post" id="lehrvertreter">        
	    	<spring:message code="btn.backToBegin" var="submit_backToBegin" />
	        <input type="submit" formaction="${post_backToBegin}" value="${submit_backToBegin}" class="btn btn-primary"/>
        </form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>