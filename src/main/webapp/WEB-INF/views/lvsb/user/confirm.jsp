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
            <spring:message code="lvsb.confirm" />
        </p>
		<br>
    	 <c:if test="${lehrvertreter.reason==null && rolle==true}">
        	<p id="hinweis" style="color:red;"><spring:message code="lvsb.reasonText" /></p>
        </c:if>
    	<c:url var="post_send" value="/lvsb/done" />
    	<c:url var="post_back" value="/lvsb/start" />
 	    <form:form method="post" modelAttribute="lehrvertreter" id="lehrvertreter">
            <tusp:form>        
            	<tusp:formRow label="lvsb.teacher_data"></tusp:formRow>          
            	<tusp:formRow label="lvsb.name" fieldname="teacher_name"><p class="form-control" readonly="true">${lehrvertreter.teacher_name}</p></tusp:formRow>
                <tusp:formRow label="lvsb.email" fieldname="teacher_email"><p class="form-control" readonly="true">${lehrvertreter.teacher_email}</p></tusp:formRow>              
                <tusp:formRow label="lvsb.account" fieldname="teacher_account"><p class="form-control" readonly="true">${lehrvertreter.teacher_account}</p></tusp:formRow>
                <c:if test="${lehrvertreter.reason != null}">
                	<tusp:formRow label="lvsb.reason" fieldname="reason"><textarea class="form-control" readonly="true">${lehrvertreter.reason}</textarea></tusp:formRow>
                </c:if>
                <tusp:formRow label="lvsb.rep_data"></tusp:formRow>   			
				<tusp:formRow label="lvsb.surname" fieldname="rep_surname"><p class="form-control" readonly="true">${lehrvertreter.rep_surname}</p></tusp:formRow>	
				<tusp:formRow label="lvsb.firstname" fieldname="rep_firstname"><p class="form-control" readonly="true">${lehrvertreter.rep_firstname}</p></tusp:formRow>				
				<tusp:formRow label="lvsb.dateOfBirth" fieldname="rep_dateofbirth"><p class="form-control" readonly="true">${lehrvertreter.rep_dateofbirth}</p></tusp:formRow>
				<tusp:formRow label="lvsb.email" fieldname="rep_email"><p class="form-control" readonly="true">${lehrvertreter.rep_email}</p></tusp:formRow>	
				<tusp:formRow label="lvsb.account" fieldname="rep_account"><p class="form-control" readonly="true">${lehrvertreter.rep_account}</p></tusp:formRow>	
				<tusp:formRow label="lvsb.institute" fieldname="institute"><p class="form-control" readonly="true">${lehrvertreter.institute}</p></tusp:formRow>
                
                <spring:message code="btn.back" var="submit_back" />
                <spring:message code="btn.send" var="submit_send" />
                <tusp:formRow label="" fieldname=""><spring:message code="lvsb.buttonLegal" /></tusp:formRow>
                <tusp:formRow label="" fieldname="">
                	<input type="submit" formaction="${post_back}" value="${submit_back}" class="btn btn-warning"/>
                    <input type="submit" formaction="${post_send}" value="${submit_send}" class="btn btn-success"/>
                </tusp:formRow>
            </tusp:form>
        </form:form>

			
    </tusp:panel>

</tusp:body>
</tusp:html>