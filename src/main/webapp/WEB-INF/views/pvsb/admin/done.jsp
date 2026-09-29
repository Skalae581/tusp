<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="pvsb.admin" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
</tusp:head>
<tusp:body vollebreite="false">
    <tusp:panel heading="pvsb.header">
        <p>
            <spring:message code="pvsb.mailSent" />
        </p>
		<br>
        <a href="<c:url value="/pvsb/admin/" />" class="btn btn-primary"><spring:message code="btn.back" /></a>
    </tusp:panel>

</tusp:body>
</tusp:html>