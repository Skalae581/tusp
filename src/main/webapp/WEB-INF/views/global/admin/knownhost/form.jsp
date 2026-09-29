<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>



<tusp:html>
<tusp:head>
    <title><spring:message code="global.knownhost.change_title" /></title>
</tusp:head>
<tusp:body>

    <c:url var="post_url" value="/global/admin/knownhost/show?id=${object.id}" />
    <tusp:panel heading="global.knownhost.change_title">
        <form:form method="post" action="${post_url}" modelAttribute="object" id="form">
            <tusp:form>
                <tusp:formRow label="global.knownhost.hostname" fieldname="hostname">
                    <form:input path="hostname" id="hostname" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="global.knownhost.hostKey" fieldname="hostKey">
                    <form:textarea path="hostKey" id="hostKey" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="global.knownhost.valid" fieldname="valid">
                	<form:select path="valid" id="valid" class="form-control">
                		<form:option value="true"><spring:message code="btn.yes" /></form:option>
                		<form:option value="false"><spring:message code="btn.no" /></form:option>
                	</form:select>
                </tusp:formRow>

                <spring:message code="btn.save" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                    
                	<a href="<c:url value="/global/admin/knownhost/list" />" class="btn btn-default"><spring:message code="btn.back" /></a>
                	<a href="<c:url value="/global/admin/knownhost/delete?id=${object.id}" />" class="btn btn-danger"><spring:message code="btn.delete" /></a>
                </tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>
