<%@tag import="java.util.ArrayList"%>
<%@tag import="java.util.List"%>
<%@ tag language="java" pageEncoding="UTF-8" description="Erzeugt eine Formularzeile"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="href" required="true" rtexprvalue="true" type="java.lang.String" description="Der Link" %>
<%@ attribute name="label" required="true" rtexprvalue="true" type="java.lang.String" description="Der Key des AnzeigeStrings" %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<% 
// Vermerken ob es Children gibt
if(this.getJspBody() != null) {
	jspContext.setAttribute("hasChild", "true", PageContext.PAGE_SCOPE);
}
%>
<c:if test="${not empty hasChild}">
	<li class="dropdown">
		<a href="${href}" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" >
			<spring:message code="${label}" /><span class="caret"></span>
		</a>
		<ul class="dropdown-menu"><jsp:doBody /></ul>
	</li>
</c:if>
<c:if test="${empty hasChild}">
	<li>
		<a href="${href}"><spring:message code="${label}" /></a>
	</li>
</c:if>