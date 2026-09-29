<%@ tag language="java" pageEncoding="UTF-8" description="Erzeugt eine Formularzeile"  %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="label" required="false" rtexprvalue="true" type="java.lang.String" description="Key des Label-Strings" %>
<%@ attribute name="label_static" required="false" rtexprvalue="true" type="java.lang.Boolean" description="der Wert true deaktiviert das Übersetzten des label-Attributes" %>
<%@ attribute name="fieldname" required="false" rtexprvalue="true" type="java.lang.String" description="Name des Eingabefeldes" %>
<%@ attribute name="pflicht" required="false" rtexprvalue="true" type="java.lang.String" description="Ist Pflichtfeld" %>


<c:set var="labelProperty" value="${empty label ? \"\" : label}" />
<c:set var="fieldnameProperty" value="${empty fieldname ? \"\" : fieldname}" />


<c:choose>
	<c:when test="${label_static}">
		<spring:message text="${label}" var="labelText"></spring:message>
	</c:when>
	<c:when test="${\"\".equals(label)}">
		<spring:message text="" var="labelText"></spring:message>
	</c:when>
	<c:otherwise>
		<spring:message code="${label}" var="labelText"></spring:message>
	</c:otherwise>
</c:choose>



<c:if test="${\"true\".equals(pflicht) || \"1\".equals(pflicht) || \"ja\".equals(pflicht) }">
	<%
	// Dem Formular mitteilen, dass es Pflichtfelder enthält
	jspContext.setAttribute("form_contains_pflicht", true, PageContext.REQUEST_SCOPE);
	%>
</c:if>

<div class='form-group'>

	<c:if test="${\"\".equals(fieldnameProperty) }">
		<span class='col-sm-3 control-label' >
			${labelText}
			<c:if test="${\"true\".equals(pflicht) || \"1\".equals(pflicht) || \"ja\".equals(pflicht) }">*</c:if>
		</span>
	</c:if>
	<c:if test="${!\"\".equals(fieldnameProperty) }">
		<label class='col-sm-3 control-label' for='${fieldnameProperty}'>
			${labelText}
			<c:if test="${\"true\".equals(pflicht) || \"1\".equals(pflicht) || \"ja\".equals(pflicht) }">*</c:if>
		</label>
	</c:if>
	<div class='col-sm-9'>
		<jsp:doBody />
	</div>
</div>