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
            <spring:message code="pvsb.confirm" />
        </p>
		<br>
    	
    	<c:url var="post_send" value="/pvsb/done" />
    	<c:url var="post_back" value="/pvsb/start" />
 	    <form:form method="post" modelAttribute="pruefervertreter" id="pruefervertreter">
            <tusp:form>        
            	<tusp:formRow label="pvsb.examiner_data"></tusp:formRow>          
            	<tusp:formRow label="pvsb.name" fieldname="examiner_name"><p class="form-control" readonly="true">${pruefervertreter.examiner_name}</p></tusp:formRow>
                <tusp:formRow label="pvsb.email" fieldname="examiner_email"><p class="form-control" readonly="true">${pruefervertreter.examiner_email}</p></tusp:formRow>              
                <tusp:formRow label="pvsb.account" fieldname="examiner_account"><p class="form-control" readonly="true">${pruefervertreter.examiner_account}</p></tusp:formRow>
                <tusp:formRow label="pvsb.rep_data"></tusp:formRow>   			
				<tusp:formRow label="pvsb.surname" fieldname="rep_surname"><p class="form-control" readonly="true">${pruefervertreter.rep_surname}</p></tusp:formRow>	
				<tusp:formRow label="pvsb.firstname" fieldname="rep_firstname"><p class="form-control" readonly="true">${pruefervertreter.rep_firstname}</p></tusp:formRow>				
				<tusp:formRow label="pvsb.dateOfBirth" fieldname="rep_dateofbirth"><p class="form-control" readonly="true">${pruefervertreter.rep_dateofbirth}</p></tusp:formRow>
				<tusp:formRow label="pvsb.email" fieldname="rep_email"><p class="form-control" readonly="true">${pruefervertreter.rep_email}</p></tusp:formRow>	
				<tusp:formRow label="pvsb.account" fieldname="rep_account"><p class="form-control" readonly="true">${pruefervertreter.rep_account}</p></tusp:formRow>	
				<tusp:formRow label="pvsb.institute" fieldname="institute"><p class="form-control" readonly="true">${pruefervertreter.institute}</p></tusp:formRow>
                
                <spring:message code="btn.back" var="submit_back" />
                <spring:message code="btn.send" var="submit_send" />
                <tusp:formRow label="" fieldname=""><spring:message code="pvsb.buttonLegal" /></tusp:formRow>
                <tusp:formRow label="" fieldname="">
                	<input type="submit" formaction="${post_back}" value="${submit_back}" class="btn btn-warning"/>
                    <input type="submit" formaction="${post_send}" value="${submit_send}" class="btn btn-success"/>
                </tusp:formRow>
            </tusp:form>
        </form:form>

			
    </tusp:panel>

</tusp:body>
</tusp:html>