<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title><spring:message code="precheck.studiengang_liste.title" /></title>
    
</tusp:head>
<tusp:body>

    <tusp:panel heading="precheck.studiengang_liste.title">
		<tusp:table  data="${list}" >
            <tusp:column property="defaulttext_de" heading="precheck.studiengang" />
            <tusp:column property="aktiv" heading="precheck.aktiv" href="precheck/admin/studiengang/list/change?id=[courseofstudy_id]">
            	<a href="<c:url value="/precheck/admin/studiengang/list/change?id=${dataItem.courseofstudy_id}" />">
            		<c:choose>
					   <c:when test="${empty dataItem.aktiv or dataItem.aktiv != 1}">
					      Aktivieren
					   </c:when>
					   <c:otherwise>
					      Deaktivieren
					   </c:otherwise>
					</c:choose>
            	</a>
            </tusp:column>
        </tusp:table>
    </tusp:panel>

</tusp:body>
</tusp:html>