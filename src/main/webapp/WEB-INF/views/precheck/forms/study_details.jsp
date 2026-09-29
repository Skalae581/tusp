<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>




<tusp:html>
<tusp:head>
    <title><spring:message code="precheck.study_details.title" /></title>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/precheck/precheck.css" />
     <script src="${pageContext.servletContext.contextPath}/resources/js/precheck/precheck.js"></script>
    
</tusp:head>
<tusp:body vollebreite="false">

    <c:url var="post_url" value="/precheck/overview_page" />

    <tusp:panel heading="precheck.study_details.title">
        <form:form method="post" action="${post_url}" modelAttribute="interessent" id="form2">
            <tusp:form>

                <tusp:formRow label="precheck.uni" fieldname="uni">
                    <form:input path="uni" id="uni" class="form-control" />
                </tusp:formRow>
                

                <tusp:formRow label="precheck.studienland" fieldname="studienland">
                 	<form:select path="studienland" id="studienland"  class="form-control" >
                 	
	                 	<option>
	                 		Please Select
	                 	</option>
                 	
                 		<form:options items="${countries}"></form:options>
                 		
                 </form:select>
                </tusp:formRow>
                
                 <tusp:formRow label="Kind of Institution" fieldname="institution">
                 	<form:select name="institution" path="institution" id="additional" class="form-control" >
                 	</form:select>
                 </tusp:formRow>
                <tusp:formRow label="precheck.abschluss" fieldname="abschluss">
                    <form:input path="abschluss" id="abschluss" class="form-control" />
                </tusp:formRow>
                
                <tusp:formRow label="precheck.abgeschlossenesstd" fieldname="abgeschlossenesstd">
                    <form:input path="abgeschlossenesstd" id="abgeschlossenesstd" class="form-control" />
                </tusp:formRow>
<%--                 <tusp:formRow label="precheck.abschluss.ekl" fieldname="abgeschlossenesstd"  />
 --%>                
                
                
                <tusp:formRow label="precheck.stdDauer" fieldname="stdDauer">
                    <form:input path="stdDauer" id="stdDauer" class="form-control" />
                </tusp:formRow>
                <tusp:formRow label="precheck.abschlussJahr" fieldname="abschlussJahr">
                    <form:input path="abschlussJahr" id="abschlussJahr" class="form-control" />
                </tusp:formRow>
                
                <tusp:formRow label="precheck.minNote" fieldname="minNote">
                    <form:input path="minNote" id="minNote" class="form-control" />
                </tusp:formRow>
<%--                 <tusp:formRow label="precheck.minNote.ekl" fieldname="minNote" />
 --%>                 
                 
                <tusp:formRow label="precheck.maxNote" fieldname="maxNote">
                    <form:input path="maxNote" id="maxNote" class="form-control" />
                </tusp:formRow>
<%--                  <tusp:formRow label="precheck.maxNote.ekl" fieldname="maxNote" />
 --%>                 
                <tusp:formRow label="precheck.erreichteNote" fieldname="erreichteNote">
                    <form:input path="erreichteNote" id="erreichteNote" class="form-control" />
                </tusp:formRow>
<%--                  <tusp:formRow label="precheck.erreichteNote.ekl" fieldname="erreichteNote" />
 --%>                         
               <tusp:formRow label="precheck.stgTuhh" fieldname="stgTuhh">
                 	<form:select path="stgTuhh" id="stgTuhh"  class="form-control" >
                 	
	                 	<option>
	                 		Please Select
	                 	</option>
                 	
                 		<form:options items="${studiengaenge}"></form:options>
                 		
                 </form:select>
                </tusp:formRow>

                <spring:message code="precheck.btn.next" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <a href="<c:url value="/precheck/personal_details" />" class="btn btn-default"><spring:message code="precheck.btn.back" /></a>
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                </tusp:formRow>
            </tusp:form>
        </form:form>

        

    </tusp:panel>

</tusp:body>
</tusp:html>