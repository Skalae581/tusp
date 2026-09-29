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

	<tusp:table data="${entries}" filter="column" sortable="true">
		<tusp:column heading="Nachname" headingStatic="true">
			${dataItem.nachname}
		</tusp:column>
		<tusp:column heading="Vorname" headingStatic="true">
			${dataItem.vorname}
		</tusp:column>
		<tusp:column heading="Titel" headingStatic="true">
			${dataItem.titel}
		</tusp:column>
		<tusp:column heading="Anrede" headingStatic="true">
			${dataItem.anrede}
		</tusp:column>
		<tusp:column heading="Mailadresse" headingStatic="true">
			${dataItem.email}
		</tusp:column>
		<tusp:column heading="Semester" headingStatic="true">
			${dataItem.semester}
		</tusp:column>
		<tusp:column heading="Verteiler" headingStatic="true">
			${dataItem.verteilername}
		</tusp:column>
	</tusp:table>

</tusp:body>
</tusp:html>