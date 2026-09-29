<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>


<tusp:html>
<tusp:head titleCode="vorabcheck.admin.ampelsystem">
</tusp:head>
<tusp:body>

	<c:url var="post_url" value="/ampelsystem/vervollstTest?${_csrf.parameterName}=${_csrf.token}" />
    <tusp:panel heading="vorabcheck.admin.ampelsystem">
    
    	<c:if test="error"><p class="label label-danger">${error}</p></c:if>
    
        <form method="post" action="${post_url}" id="form" enctype="multipart/form-data">
    		<input type="file" name="file" class="" />
    		<input type="submit" value="<spring:message code="btn.new" />" class="btn btn-default"/>
    	</form>
    
    </tusp:panel>
    
</tusp:body>
</tusp:html>