<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>



<tusp:html>
<tusp:head>
    <title><spring:message code="precheck.personal_details.title" /></title>
   	  <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/precheck/precheck.css" />
     <script src="${pageContext.servletContext.contextPath}/resources/js/precheck/precheck.js"></script>
</tusp:head>
<tusp:body vollebreite="false">

    <c:url var="post_url" value="/precheck/study_details" />
    <tusp:panel heading="precheck.personal_details.title">
        <form:form method="post" action="${post_url}" modelAttribute="interessent" id="form1">
            <tusp:form>

                <tusp:formRow label="precheck.nachname" fieldname="nachname">
                    <form:input path="nachname" id="nachname" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="precheck.vorname" fieldname="vorname">
                    <form:input path="vorname" id="vorname" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="precheck.geschlecht" fieldname="geschlecht">
                    <form:select path="geschlecht" id="geschlecht" items="${genders}" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="precheck.geburtsdatum" fieldname="geburtsdatum">
                    <form:input path="geburtsdatum" id="geburtsdatum"  type="date" class="form-control"/>
                </tusp:formRow>
                <tusp:formRow label="precheck.geburtsland" fieldname="geburtsland">
                 	<form:select path="geburtsland" id="geburtsland"  class="form-control" >
                 	
	                 	<option>
	                 		Please Select
	                 	</option>
                 	
                 		<form:options items="${countries}"></form:options>
                 		
                 	</form:select>
                </tusp:formRow>
                <tusp:formRow label="precheck.stadt" fieldname="stadt">
                    <form:input path="stadt" id="stadt" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="precheck.email" fieldname="email">
                    <form:input path="email" id="email" class="form-control" />
                </tusp:formRow>


                <spring:message code="precheck.btn.next" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                </tusp:formRow>
            </tusp:form>
        </form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>