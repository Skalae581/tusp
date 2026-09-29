<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<tusp:html>
	<tusp:head>
	    <title><spring:message code="accessdenied.title" /></title>
	</tusp:head>

	<tusp:body>
	
        <h1><spring:message code="accessdenied.title" /></h1>
		<p><strong>${user}</strong><spring:message code="accessdenied.message" /></p>
	    <a href="<c:url value="/" />"><spring:message code="btn.start" /></a>
	
	</tusp:body>
 	

    
</tusp:html>   