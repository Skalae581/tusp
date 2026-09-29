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

    <c:url var="post_url" value="/fame/admin/workflow/show?id=${object.id}" />
    <tusp:panel heading="fame.admin.workflow_form">
        <form:form method="post" action="${post_url}" modelAttribute="object" id="form">
        	<input name="id" type="hidden" value="${object.id}" />
            <tusp:form>
                <tusp:formRow label="fame.workflow.name" fieldname="name">
                    <form:input path="name" id="name" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.workflow.connector" fieldname="connector">
                    <form:select path="connector" id="connector" class="form-control" items="${connectors}"/>
                </tusp:formRow>
                <tusp:formRow label="fame.workflow.kommentar" fieldname="kommentar">
                    <form:input path="kommentar" id="kommentar" class="form-control" />
                </tusp:formRow>
<%--                 <tusp:formRow label="weiche.abschluss.hisDegreeId" fieldname="hisDegreeId"> --%>
<%--                  	<form:select path="hisDegreeId" id="hisDegreeId" items="${degrees}" class="form-control" /> --%>
<%--                 </tusp:formRow> --%>

                <spring:message code="btn.save" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                    
                	<a href="<c:url value="/fame/admin/overview?id=${object.id}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
                	<a href="<c:url value="/fame/admin/workflow/delete?id=${object.id}" />" class="btn btn-danger"><spring:message code="btn.delete" /></a>
                </tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>
    
</tusp:body>
</tusp:html>