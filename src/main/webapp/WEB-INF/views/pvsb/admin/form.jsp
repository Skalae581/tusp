<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="pvsb.admin" /></title>
</tusp:head>
<tusp:body>
    <c:url var="post_url" value="/pvsb/admin/show?id=${object.id}" />
    <tusp:panel heading="pvsb.form">
        <form:form method="post" action="${post_url}" modelAttribute="object" id="form">
        	<input name="id" type="hidden" value="${object.id}" />
            <tusp:form>
            	<tusp:formRow label="pvsb.status" fieldname="complete">
                	<form:select path="complete" id="complete" class="form-control">
                		<form:option value="true"><spring:message code="pvsb.processed" /></form:option>
                		<form:option value="false"><spring:message code="pvsb.unprocessed" /></form:option>
                	</form:select>
                </tusp:formRow>
                <tusp:formRow label="pvsb.examiner_name" fieldname="examiner_name">
                    <p class="form-control" readonly="true">${object.examiner_name}</p>
                </tusp:formRow>
                <tusp:formRow label="pvsb.examiner_email" fieldname="examiner_email">
                    <p class="form-control" readonly="true">${object.examiner_email}</p>
                </tusp:formRow>
                <tusp:formRow label="pvsb.examiner_account" fieldname="examiner_account">
                    <p class="form-control" readonly="true">${object.examiner_account}</p>
                </tusp:formRow>
                <tusp:formRow label="pvsb.rep_surname" fieldname="rep_surname">
                    <p class="form-control" readonly="true">${object.rep_surname}</p>
                </tusp:formRow>
                 <tusp:formRow label="pvsb.rep_firstname" fieldname="rep_firstname">
                    <p class="form-control" readonly="true">${object.rep_firstname}</p>
                </tusp:formRow>
                <tusp:formRow label="pvsb.rep_dateOfBirth" fieldname="rep_dateofbirth">
                    <p class="form-control" readonly="true">${object.rep_dateofbirth}</p>
                </tusp:formRow>
                <tusp:formRow label="pvsb.rep_email" fieldname="rep_email">
                    <p class="form-control" readonly="true">${object.rep_email}</p>
                </tusp:formRow>
                 <tusp:formRow label="pvsb.rep_account" fieldname="rep_account">
                    <p class="form-control" readonly="true">${object.rep_account}</p>
                </tusp:formRow>

				<tusp:formRow label="pvsb.process" fieldname="t">
                    <p class="form-control" readonly="true">${object.timestamp_process} (${object.processor})</p>
                </tusp:formRow>
                
                <spring:message code="btn.save" var="submit_txt" />
                <tusp:formRow label="" fieldname="">	
                    <input type="submit" value="${submit_txt}" class="btn btn-primary" />
                    
                    <a href="<c:url value="/pvsb/admin/" />" class="btn btn-default"><spring:message code="btn.back" /></a>
                </tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>
        
   	<tusp:panel heading="psvb.ldap">
        <c:forEach items="${ldapResult}" var="entry">
        	<p>${entry.key}: ${entry.value}</p>
        </c:forEach>
    </tusp:panel>
</tusp:body>
</tusp:html>