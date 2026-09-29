<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>


<tusp:html>
<tusp:head>
    <title><spring:message code="fame.admin.input_list_title" /></title>
    
</tusp:head>
<tusp:body>
    <c:url var="post_url" value="/fame/admin/input/show?id=${object.id}" />
    <tusp:panel heading="fame.admin.workflowinstance_form">
        <form:form method="post" action="${post_url}" modelAttribute="object" id="form">
        	<input name="id" type="hidden" value="${object.id}" />
            <tusp:form>
                <tusp:formRow label="fame.input.name" fieldname="name">
                    <form:input path="name" id="name" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.input.beschreibung" fieldname="beschreibung">
                    <form:input path="beschreibung" id="beschreibung" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.input.workflowId" fieldname="workflowId">
                	<input type="hidden" name="workflowId" value="${object.workflowId}" />
                    <form:select path="workflowId"  id="workflowId" class="form-control" disabled="true" >
                    	<form:options items="${workflows}" itemLabel="name" itemValue="id" />
                    </form:select>
                </tusp:formRow>
                <tusp:formRow label="fame.input.inputTypeId" fieldname="inputTypeId">
                    <form:select path="inputTypeId"  id="inputTypeId" class="form-control" >
                    	<form:options items="${types}" itemLabel="name" itemValue="id" />
                    </form:select>
                </tusp:formRow>
                <tusp:formRow label="fame.input.options" fieldname="options">
                    <form:textarea path="options" id="options" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.input.position" fieldname="position">
                    <form:input path="position" id="position" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.input.tablePos" fieldname="tablePos">
                    <form:input path="tablePos" id="tablePos" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.input.pflicht" fieldname="pflicht">
                    <form:input path="pflicht" id="pflicht" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.input.regex" fieldname="regex">
                    <form:input path="regex" id="regex" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="fame.input.connectorMapping" fieldname="connectorMapping">
                	<p>Es können weitere im Applicantconnector Feldnamen hinzugefügt werden für dynamische Bewerbungsbestandteile. Hierzu in der Konfig de.tuhh.tusp.app.fame.applicantservice.bestandteile.EINDEUTIGERBEZEICHNER = feldname setzen. Dies geschieht aber auch automatisch bei jedem Import.</p>
                    <form:select path="connectorMapping" id="connectorMapping" class="form-control" >
                    	<option />
                    	<form:options items="${connectorFields}" />
                    </form:select>
                </tusp:formRow>
                <tusp:formRow label="fame.input.kommentar" fieldname="kommentar">
                    <form:input path="kommentar" id="kommentar" class="form-control" />
                </tusp:formRow>
                <input type="hidden" name="hidden" value="0" />

                <spring:message code="btn.save" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                    
                	<a href="<c:url value="/fame/admin/overview?id=${object.workflowId}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
                	
					<c:if test="${not empty object.id}">
                		<a href="<c:url value="/fame/admin/input/delete?id=${object.id}" />" class="btn btn-danger"><spring:message code="btn.delete" /></a>
                		<a href="<c:url value="/fame/admin/inputberechtigung/list?id=${object.workflowId}&empf=${object.id}" />" class="btn btn-default"><spring:message code="fame.admin.edit.inputBerechtigung" /></a>
                	</c:if>
                </tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>
</tusp:body>
</tusp:html>