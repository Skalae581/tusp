<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="lvsb.title" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
</tusp:head>
<tusp:body vollebreite="false">
    <tusp:panel heading="lvsb.header">
        <p>
            <spring:message code="lvsb.description" />
		<br>
            <spring:message code="lvsb.info" />
        </p>
        <c:if test="${rolle==true}">
        	<p id="hinweis" style="color:red;"><spring:message code="lvsb.reasonText" /></p>
        </c:if>
		<br>
		<spring:message code="placeholder.name" var="placeholderName" />
		<spring:message code="placeholder.surname" var="placeholderSurname" />
		<spring:message code="placeholder.firstname" var="placeholderFirstname" />
		<spring:message code="placeholder.dateOfBirth" var="placeholderDateOfBirth" />
		<spring:message code="placeholder.email" var="placeholderEmail" />
		<spring:message code="placeholder.account" var="placeholderAccount" />
		<spring:message code="placeholder.institute" var="placeholderInstitute" />
		
    	<c:url var="post_url" value="/lvsb/confirm" />
        <form:form method="post" action="${post_url}" modelAttribute="lehrvertreter" id="lehrvertreter">
        	
            <tusp:form>    
            <tusp:formRow label="lvsb.teacher_data"></tusp:formRow>    
                
                <tusp:formRow label="lvsb.account" fieldname="teacher_account" pflicht="true">	
						<form:input id="teacher_account" class="form-control" path="teacher_account" required="required" value="${lehrvertreter.teacher_account}" placeholder="${placeholderAccount}" />				
				</tusp:formRow>
                   
            	<tusp:formRow label="lvsb.name" fieldname="teacher_name">	
						<form:input id="teacher_name" class="form-control" path="teacher_name" value="${lehrvertreter.teacher_name}" placeholder="${placeholderName}" />				
				</tusp:formRow>
				 
                <tusp:formRow label="lvsb.email" fieldname="teacher_email" pflicht="true">
                    	<form:input id="teacher_email" class="form-control" type="email" required="required" path="teacher_email" value="${lehrvertreter.teacher_email}" placeholder="${placeholderEmail}" />
                </tusp:formRow>

                <input id="rolle" path="employee" type="hidden" value="${rolle}" />
                
                <c:if test="${rolle==true}">
                	<tusp:formRow label="lvsb.reason" fieldname="reason" pflicht="true">
			      		<form:textarea class="form-control" path="reason" rows="15" cols="100" name="reason" required="required" />
			      	</tusp:formRow> 
                </c:if>
			
				<tusp:formRow label="lvsb.rep_data"></tusp:formRow>
			
				<tusp:formRow label="lvsb.surname" fieldname="rep_surname" pflicht="true">	
						<form:input id="rep_surname" class="form-control" path="rep_surname" required="required" value="${lehrvertreter.rep_surname}" placeholder="${placeholderSurname}" />				
				</tusp:formRow>
				
				<tusp:formRow label="lvsb.firstname" fieldname="rep_firstname" pflicht="true">	
						<form:input id="rep_firstname" class="form-control" path="rep_firstname" required="required" value="${lehrvertreter.rep_firstname}" placeholder="${placeholderFirstname}" />				
				</tusp:formRow>
				
				<tusp:formRow label="lvsb.dateOfBirth" fieldname="rep_dateofbirth">	
						<form:input id="rep_dateofbirth" class="form-control" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}" path="rep_dateofbirth" value="${lehrvertreter.rep_dateofbirth}" placeholder="${placeholderDateOfBirth}" />				
				</tusp:formRow>
				
				<tusp:formRow label="lvsb.email" fieldname="rep_email" pflicht="true">	
						<form:input id="rep_email" class="form-control" path="rep_email" required="required" value="${lehrvertreter.rep_email}" placeholder="${placeholderEmail}" />				
				</tusp:formRow>
				
				<tusp:formRow label="lvsb.account" fieldname="rep_account">	
						<form:input id="rep_account" class="form-control" path="rep_account" value="${lehrvertreter.rep_account}" placeholder="${placeholderAccount}" />				
				</tusp:formRow>
				
				<tusp:formRow label="lvsb.institute" fieldname="institute">	
						<form:input id="institute" class="form-control" path="institute" value="${lehrvertreter.institute}" placeholder="${placeholderInstitute}" />				
				</tusp:formRow>
				
                <spring:message code="btn.next" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-primary" />
                </tusp:formRow>
            </tusp:form>
        </form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>