<%@ tag language="java" pageEncoding="UTF-8" description="Erzeugt ein Panel"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ attribute name="type" required="false" rtexprvalue="true" type="java.lang.String" description="Bootstrap Typ [default,danger, warn, success, info]" %>
<%@ attribute name="heading" required="true" rtexprvalue="true" type="java.lang.String" description="Der Key zur Überschrift" %>
<%@ attribute name="hiddenWhen" required="false" rtexprvalue="true" type="java.lang.Boolean" description="Bedingung unter dieses Panel ausgeblendet werden soll" %>
<%@ attribute name="clazz" required="false" rtexprvalue="true" type="java.lang.String" description="CSS-Klasse" %>
<%@ attribute name="headingStatic" required="false" type="java.lang.Boolean" description="Wenn true wird das 'heading' nicht aus einer Language-Datei ersetzt." %>
<c:set var="type" value="${empty type ? 'default' : type}" />
<c:if test="${not hiddenWhen}">
	<div class='panel panel-${type} ${clazz}'>
		<div class='panel-heading'>
			<c:choose>
				<c:when test="${headingStatic}">
					${heading}
				</c:when>
				<c:otherwise>
					<tusp:out key="${heading}"/>
				</c:otherwise>
			</c:choose>
		</div>
		<div class='panel-body'>
			<jsp:doBody />
		</div>
	</div>
</c:if>