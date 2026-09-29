<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>


<tusp:html>
<tusp:head>
    <title><spring:message code="fame.admin.workflowinstance_list_title" /></title>
    
</tusp:head>
<tusp:body>
    <c:url var="post_url" value="/fame/admin/workflowinstance/show?id=${object.id}" />
    <tusp:panel heading="fame.admin.workflowinstance_form">
        <form:form method="post" action="${post_url}" modelAttribute="object" id="form">
        	<input name="id" type="hidden" value="${object.id}" />
            <tusp:form>
                <tusp:formRow label="fame.workflowinstance.name" fieldname="name">
                    <form:input path="name" id="name" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.workflowinstance.parameter" fieldname="parameter">
                    <form:input path="parameter" id="parameter" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.workflowinstance.rechteParameter" fieldname="rechteParameter">
                    <form:input path="rechteParameter" id="rechteParameter" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.workflowinstance.workflowId" fieldname="workflowId">
                	<input type="hidden" name="workflowId" value="${object.workflowId}" />
                    <form:select path="workflowId"  id="workflowId" class="form-control" disabled="true" >
                    	<form:options items="${workflows}" itemLabel="name" itemValue="id" />
                    </form:select>
                </tusp:formRow>
                <tusp:formRow label="fame.workflowinstance.kommentar" fieldname="kommentar">
                    <form:input path="kommentar" id="kommentar" class="form-control" />
                </tusp:formRow>

                <spring:message code="btn.save" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                    
                	<a href="<c:url value="/fame/admin/overview?id=${object.workflowId}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
                	<a href="<c:url value="/fame/admin/workflowinstance/delete?id=${object.id}" />" class="btn btn-danger"><spring:message code="btn.delete" /></a>
                </tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>
</tusp:body>
</tusp:html>