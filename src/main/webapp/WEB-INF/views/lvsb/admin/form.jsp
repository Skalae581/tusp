<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="lvsb.admin" /></title>
</tusp:head>
<tusp:body>
    <c:url var="post_url" value="/lvsb/admin/show?id=${object.id}" />
    <tusp:panel heading="lvsb.form">
        <form:form method="post" action="${post_url}" modelAttribute="object" id="form">
        	<input name="id" type="hidden" value="${object.id}" />
            <tusp:form>
            	<tusp:formRow label="lvsb.status" fieldname="complete">
                	<form:select path="complete" id="complete" class="form-control">
                		<form:option value="true"><spring:message code="lvsb.processed" /></form:option>
                		<form:option value="false"><spring:message code="lvsb.unprocessed" /></form:option>
                	</form:select>
                </tusp:formRow>
                <tusp:formRow label="lvsb.teacher_name" fieldname="teacher_name">
                    <p class="form-control" readonly="true">${object.teacher_name}</p>
                </tusp:formRow>
                <tusp:formRow label="lvsb.teacher_email" fieldname="teacher_email">
                    <p class="form-control" readonly="true">${object.teacher_email}</p>
                </tusp:formRow>
                <tusp:formRow label="lvsb.teacher_account" fieldname="teacher_account">
                    <p class="form-control" readonly="true">${object.teacher_account}</p>
                </tusp:formRow>
                 <tusp:formRow label="lvsb.employee" fieldname="employee">
	                 <c:choose>
					  <c:when test="${object.employee==true}">
					    <p class="form-control" readonly="true"><spring:message code="lvsb.yes" /></p>
					  </c:when>
					   <c:when test="${object.employee==false}">
					   	<p class="form-control" readonly="true"><spring:message code="lvsb.no" /></p>
					  </c:when>
					</c:choose>
                </tusp:formRow>
                <c:if test="${object.reason!=null}">
                <tusp:formRow label="lvsb.reason" fieldname="reason">
                	<textarea class="form-control" readonly="true">${object.reason}</textarea>
                </tusp:formRow>
                </c:if>
                <tusp:formRow label="lvsb.rep_surname" fieldname="rep_surname">
                    <p class="form-control" readonly="true">${object.rep_surname}</p>
                </tusp:formRow>
                 <tusp:formRow label="lvsb.rep_firstname" fieldname="rep_firstname">
                    <p class="form-control" readonly="true">${object.rep_firstname}</p>
                </tusp:formRow>
                <tusp:formRow label="lvsb.rep_dateOfBirth" fieldname="rep_dateofbirth">
                    <p class="form-control" readonly="true">${object.rep_dateofbirth}</p>
                </tusp:formRow>
                <tusp:formRow label="lvsb.rep_email" fieldname="rep_email">
                    <p class="form-control" readonly="true">${object.rep_email}</p>
                </tusp:formRow>
                 <tusp:formRow label="lvsb.rep_account" fieldname="rep_account">
                    <p class="form-control" readonly="true">${object.rep_account}</p>
                </tusp:formRow>

				<tusp:formRow label="lvsb.process" fieldname="t">
                    <p class="form-control" readonly="true">${object.timestamp_process} (${object.processor})</p>
                </tusp:formRow>
                
                <spring:message code="btn.save" var="submit_txt" />
                <tusp:formRow label="" fieldname="">	
                    <input type="submit" value="${submit_txt}" class="btn btn-primary" />
                    
                    <a href="<c:url value="/lvsb/admin/" />" class="btn btn-default"><spring:message code="btn.back" /></a>
                </tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>
        
   	<tusp:panel heading="lvsb.ldap">
        <c:forEach items="${ldapResult}" var="entry">
        	<p>${entry.key}: ${entry.value}</p>
        </c:forEach>
    </tusp:panel>
</tusp:body>
</tusp:html>