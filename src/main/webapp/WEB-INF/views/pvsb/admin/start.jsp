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
<tusp:body>
	<tusp:panel heading="pvsb.admin" >
	<h4><spring:message code="pvsb.requests" /></h4>
	<tusp:table data="${pruefervertreter}" filter="column" cssclass="table" sortable="true">
		<tusp:column heading="" nofilter="true"><a href="<c:url value="/pvsb/admin/mail?id=${dataItem.id}" />">
		<c:choose>
			<c:when test="${dataItem.mailsent == true}"><span class="glyphicon glyphicon-envelope" style="color:green" data-toggle="tooltip" title="<spring:message code="pvsb.mailSentTrue" />"></span></c:when>
			<c:otherwise><span class="glyphicon glyphicon-envelope" data-toggle="tooltip" title="<spring:message code="pvsb.mail" />"></span></c:otherwise>
		</c:choose>
		</a></tusp:column>
		<tusp:column heading="" nofilter="true"><a href="<c:url value="/pvsb/admin/show?id=${dataItem.id}" />"><span class="glyphicon glyphicon-pencil" data-toggle="tooltip" title="<spring:message code="pvsb.edit" />"></span></a></tusp:column>
		<tusp:column heading="pvsb.status" property="complete">
			<c:choose>
				<c:when test="${dataItem.complete == true}">
					<spring:message code="pvsb.processed" />
				</c:when>	
				<c:otherwise>
					<spring:message code="pvsb.unprocessed" />
				</c:otherwise>
			</c:choose>
		</tusp:column>
		<tusp:column heading="pvsb.timestamp" property="timestamp_confirm"></tusp:column>
		<tusp:column heading="pvsb.examiner_name" property="examiner_name"></tusp:column>
		<tusp:column heading="pvsb.examiner_email" property="examiner_email"></tusp:column>
		<tusp:column heading="pvsb.examiner_account" property="examiner_account"></tusp:column>
		<tusp:column heading="pvsb.rep_surname" property="rep_surname"></tusp:column>
		<tusp:column heading="pvsb.rep_firstname" property="rep_firstname"></tusp:column>
		<tusp:column heading="pvsb.rep_dateOfBirth" property="rep_dateofbirth"></tusp:column>
		<tusp:column heading="pvsb.rep_email" property="rep_email"></tusp:column>
		<tusp:column heading="pvsb.rep_account" property="rep_account"></tusp:column>
		<tusp:column heading="pvsb.institute" property="institute"></tusp:column>
		<tusp:column heading="pvsb.applicant" property="applicant"></tusp:column>
		<tusp:column heading="pvsb.processor" property="processor"></tusp:column>
		<tusp:column heading="pvsb.timestamp_process" property="timestamp_process"></tusp:column>
	</tusp:table>

    </tusp:panel>
</tusp:body>
</tusp:html>