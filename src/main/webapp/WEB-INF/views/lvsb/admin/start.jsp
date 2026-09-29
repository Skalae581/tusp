<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="lvsb.admin" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
</tusp:head>
<tusp:body>
	<tusp:panel heading="lvsb.admin" >
	<h4><spring:message code="lvsb.requests" /></h4>
	<tusp:table data="${lehrvertreter}" filter="column" cssclass="table" sortable="true">
		<tusp:column heading="" nofilter="true"><a href="<c:url value="/lvsb/admin/mail?id=${dataItem.id}" />">
		<c:choose>
			<c:when test="${dataItem.mailsent == true}"><span class="glyphicon glyphicon-envelope" style="color:green" data-toggle="tooltip" title="<spring:message code="lvsb.mailSentTrue" />"></span></c:when>
			<c:otherwise><span class="glyphicon glyphicon-envelope" data-toggle="tooltip" title="<spring:message code="lvsb.mail" />"></span></c:otherwise>
		</c:choose>
		</a></tusp:column>
		<tusp:column heading="" nofilter="true"><a href="<c:url value="/lvsb/admin/show?id=${dataItem.id}" />"><span class="glyphicon glyphicon-pencil" data-toggle="tooltip" title="<spring:message code="lvsb.edit" />"></span></a></tusp:column>
		<tusp:column heading="lvsb.status" property="complete">
			<c:choose>
				<c:when test="${dataItem.complete == true}">
					<spring:message code="lvsb.processed" />
				</c:when>	
				<c:otherwise>
					<spring:message code="lvsb.unprocessed" />
				</c:otherwise>
			</c:choose>
		</tusp:column>
		<tusp:column heading="lvsb.timestamp" property="timestamp_confirm"></tusp:column>
		<tusp:column heading="lvsb.employee" property="employee"></tusp:column>
		<tusp:column heading="lvsb.teacher_name" property="teacher_name"></tusp:column>
		<tusp:column heading="lvsb.teacher_email" property="teacher_email"></tusp:column>
		<tusp:column heading="lvsb.teacher_account" property="teacher_account"></tusp:column>
		<tusp:column heading="lvsb.rep_surname" property="rep_surname"></tusp:column>
		<tusp:column heading="lvsb.rep_firstname" property="rep_firstname"></tusp:column>
		<tusp:column heading="lvsb.rep_dateOfBirth" property="rep_dateofbirth"></tusp:column>
		<tusp:column heading="lvsb.rep_email" property="rep_email"></tusp:column>
		<tusp:column heading="lvsb.rep_account" property="rep_account"></tusp:column>
		<tusp:column heading="lvsb.institute" property="institute"></tusp:column>
		<tusp:column heading="lvsb.applicant" property="applicant"></tusp:column>
		<tusp:column heading="lvsb.processor" property="processor"></tusp:column>
		<tusp:column heading="lvsb.timestamp_process" property="timestamp_process"></tusp:column>
	</tusp:table>

    </tusp:panel>
</tusp:body>
</tusp:html>