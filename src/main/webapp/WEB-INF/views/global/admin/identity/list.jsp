<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title><spring:message code="global.identity.list_title" /></title>
    
</tusp:head>
<tusp:body>

    <tusp:panel heading="global.identity.list_title">
    
    	<div class="alert alert-info"><spring:message code="global.identity.get" /></div>
    
    	<a href="<c:url value="/global/admin/identity/show?id=0" />" class="btn btn-success"><spring:message code="btn.new" /></a>
    	
		<tusp:table data="${objects}">
            <tusp:column property="id" heading="global.identity.id" href="global/admin/identity/show?id=[id]"/>
            <tusp:column property="name" heading="global.identity.name" />
            <tusp:column property="valid" heading="global.identity.valid" />
        </tusp:table>
    </tusp:panel>

</tusp:body>
</tusp:html>