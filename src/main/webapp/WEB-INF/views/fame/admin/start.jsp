<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<tusp:html>
<tusp:head titleCode="fame.admin.workflow_list_title">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/fame.css">
</tusp:head>
<tusp:body>

    <tusp:panel heading="fame.admin.workflow_list_title">
		<tusp:table data="${objects}" >
			<tusp:column heading='fame.workflow.name'>
				<a href="<c:url value="/fame/admin/overview?id=${dataItem.id}" />" 
					class="btn btn-default"
					title="<spring:message code="btn.edit" />">
					${dataItem.name}
				</a>
			</tusp:column>
		</tusp:table>
    	<a href="<c:url value="/fame/admin/workflow/show?id=0" />" class="btn btn-info"><spring:message code="btn.add" /></a>
    	<a href="<c:url value="/fame/admin/workflow/deserialize" />" class="btn btn-info"><spring:message code="btn.import" /></a>
    </tusp:panel>

</tusp:body>
</tusp:html>