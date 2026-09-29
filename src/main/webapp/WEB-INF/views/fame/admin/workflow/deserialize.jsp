<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head titleCode="fame.admin.workflow_list_title">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/fame.css">
</tusp:head>
<tusp:body>

	<c:url var="post_url" value="/fame/admin/workflow/deserialize?${_csrf.parameterName}=${_csrf.token}" />
    <tusp:panel heading="fame.admin.workflow_form">
    
        <form method="post" action="${post_url}" id="form" enctype="multipart/form-data">
    		<input type="file" name="file" class="" />
    		<input type="submit" value="<spring:message code="btn.import" />" class="btn btn-default"/>
    	</form>
    	
   		<a href="<c:url value="/fame/admin/" />" class="btn btn-default"><spring:message code="btn.back" /></a>
    
    </tusp:panel>
    
</tusp:body>
</tusp:html>