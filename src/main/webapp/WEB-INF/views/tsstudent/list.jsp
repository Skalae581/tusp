<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
</tusp:head>
<tusp:body>

	
	<a 
		href="<c:url value="/tsstudent/new" />" 
		class="btn btn-default" 
		title="<tusp:out key="btn.new" />">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
	</a>
	<tusp:table data="${students}" filter="column" sortable="true">
		 <tusp:column heading="Sonder Studenten">
		<a 
			href="<c:url value="/tsstudent/edit?id=${dataItem.id}" />" 
			class="btn btn-default" 
			title="<tusp:out key="btn.edit" />">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		</a>
		</tusp:column>
		<tusp:column heading="Matrikelnummer">
			${dataItem.mtknr}
		</tusp:column>
		<tusp:column heading="Startdate">
			${dataItem.startdate}
		</tusp:column>
			<tusp:column heading="Enddate">
			${dataItem.enddate}
		</tusp:column>
	</tusp:table>

</tusp:body>
</tusp:html>