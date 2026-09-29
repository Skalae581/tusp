<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="pvsb.title" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
</tusp:head>
<tusp:body vollebreite="false">
    <tusp:panel heading="pvsb.header">
        <p>
            <spring:message code="pvsb.description" />
		<br>
            <spring:message code="pvsb.info" />
        </p>
		<br>
		<spring:message code="placeholder.name" var="placeholderName" />
		<spring:message code="placeholder.surname" var="placeholderSurname" />
		<spring:message code="placeholder.firstname" var="placeholderFirstname" />
		<spring:message code="placeholder.dateOfBirth" var="placeholderDateOfBirth" />
		<spring:message code="placeholder.email" var="placeholderEmail" />
		<spring:message code="placeholder.account" var="placeholderAccount" />
		<spring:message code="placeholder.institute" var="placeholderInstitute" />
		
    	<c:url var="post_url" value="/pvsb/confirm" />
        <form:form method="post" action="${post_url}" modelAttribute="pruefervertreter" id="pruefervertreter">
            <tusp:form>    
            <tusp:formRow label="pvsb.examiner_data"></tusp:formRow>    
                
                <tusp:formRow label="pvsb.account" fieldname="examiner_account" pflicht="true">	
						<form:input id="examiner_account" class="form-control" path="examiner_account" required="required" value="${pruefervertreter.examiner_account}" placeholder="${placeholderAccount}" />				
				</tusp:formRow>
                   
            	<tusp:formRow label="pvsb.name" fieldname="examiner_name">	
						<form:input id="examiner_name" class="form-control" path="examiner_name" value="${pruefervertreter.examiner_name}" placeholder="${placeholderName}" />				
				</tusp:formRow>
				 
                <tusp:formRow label="pvsb.email" fieldname="examiner_email">
                    	<form:input id="examiner_email" class="form-control" type="email" path="examiner_email" value="${pruefervertreter.examiner_email}" placeholder="${placeholderEmail}" />
                </tusp:formRow>
			
				<tusp:formRow label="pvsb.rep_data"></tusp:formRow>
			
				<tusp:formRow label="pvsb.surname" fieldname="rep_surname" pflicht="true">	
						<form:input id="rep_surname" class="form-control" path="rep_surname" required="required" value="${pruefervertreter.rep_surname}" placeholder="${placeholderSurname}" />				
				</tusp:formRow>
				
				<tusp:formRow label="pvsb.firstname" fieldname="rep_firstname" pflicht="true">	
						<form:input id="rep_firstname" class="form-control" path="rep_firstname" required="required" value="${pruefervertreter.rep_firstname}" placeholder="${placeholderFirstname}" />				
				</tusp:formRow>
				
				<tusp:formRow label="pvsb.dateOfBirth" fieldname="rep_dateofbirth">	
						<form:input id="rep_dateofbirth" class="form-control" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}" path="rep_dateofbirth" value="${pruefervertreter.rep_dateofbirth}" placeholder="${placeholderDateOfBirth}" />				
				</tusp:formRow>
				
				<tusp:formRow label="pvsb.email" fieldname="rep_email" pflicht="true">	
						<form:input id="rep_email" class="form-control" path="rep_email" required="required" value="${pruefervertreter.rep_email}" placeholder="${placeholderEmail}" />				
				</tusp:formRow>
				
				<tusp:formRow label="pvsb.account" fieldname="rep_account">	
						<form:input id="rep_account" class="form-control" path="rep_account" value="${pruefervertreter.rep_account}" placeholder="${placeholderAccount}" />				
				</tusp:formRow>
				
				<tusp:formRow label="pvsb.institute" fieldname="institute">	
						<form:input id="institute" class="form-control" path="institute" value="${pruefervertreter.institute}" placeholder="${placeholderInstitute}" />				
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