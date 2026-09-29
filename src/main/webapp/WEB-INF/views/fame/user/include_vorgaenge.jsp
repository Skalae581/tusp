<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:table data="${vorgaenge}" headerfix="true" sortable="true" filter="single" >
	<tusp:column heading="" headingStatic="true">
		<input type='hidden' class='vorgangId' value="${dataItem.id}" />
		<a 
			href="<c:url value="/fame/user/vorgang?id=${dataItem.id}" />" 
			class="btn btn-default" 
			title="<tusp:out key="fame.user.vorgang_ansehen" />">
			<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
		</a>
		<a 
			href="<c:url value="/fame/user/vorgang?id=${dataItem.id}&edit=true" />" 
			class="btn btn-default" 
			title="<tusp:out key="fame.user.vorgang_bearbeiten" />">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		</a>
	</tusp:column>
	<c:forEach items="${inputs}" var="input" >
		<tusp:column heading="${input.name}" property="value_${input.id}" headingStatic="true"/>
	</c:forEach>
</tusp:table>