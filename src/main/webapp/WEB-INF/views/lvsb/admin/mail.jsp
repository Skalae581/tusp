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
	<spring:message code="lvsb.mail.subject" var="subject" />
	<spring:message code="lvsb.mail.body" arguments="${lehrvertreterTeaName}; ${lehrvertreterRepName}" argumentSeparator=";" var="body" />
    <c:url var="post_url" value="/lvsb/admin/done" />
    <tusp:panel heading="lvsb.mail">
        <form:form method="post" action="${post_url}" modelAttribute="mail" id="form">
        	<input name="id" type="hidden" value="${lehrvertreterId}" />
            <tusp:form>
                <tusp:formRow label="lvsb.teacher_email" fieldname="to">
                    <form:input path="to" value="${mail.to}" size="30" />
                </tusp:formRow>
                <tusp:formRow label="lvsb.rep_email" fieldname="cc">
                    <form:input path="cc" value="${mail.cc}" size="30" />
                </tusp:formRow>
                <tusp:formRow label="lvsb.subject" fieldname="subject">
					<form:input path="subject" value="${subject}" size="50" />
                </tusp:formRow>
                <tusp:formRow label="lvsb.body" fieldname="body">
                    <textarea rows="15" cols="100" name="body">${body}</textarea>
                </tusp:formRow>
                
                <spring:message code="btn.send" var="submit_txt" />
                <tusp:formRow label="" fieldname="">	
                    <input type="submit" value="${submit_txt}" class="btn btn-primary" />
                    
                    <a href="<c:url value="/lvsb/admin/" />" class="btn btn-default"><spring:message code="btn.back" /></a>
                </tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>
</tusp:body>
</tusp:html>