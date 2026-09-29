<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>


<tusp:html>
<tusp:head>
    <title><spring:message code="precheck.container.start" /></title>
</tusp:head>
<tusp:body vollebreite="false">

    <tusp:panel heading="precheck.container.start">

        <p>
            <spring:message code="precheck.einleitung" />
        </p>

        <a href="<c:url value="/precheck/personal_details" />" class="btn btn-default"> 
            <spring:message code="precheck.btn.start" />
        </a>

    </tusp:panel>

</tusp:body>
</tusp:html>