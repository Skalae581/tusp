<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">

	<sec:authorize access="hasAnyRole('PRUEFER') && !hasRole('ADMIN')">
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/pvsb/" label="pvsb.title"/>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ADMIN')">
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/pvsb/" label="pvsb.title">
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/pvsb/" label="pvsb.title" />
       		<li role="separator" class="divider"></li>
		    <tusp:menuItem href="${pageContext.servletContext.contextPath}/pvsb/admin/" label="pvsb.admin"/>
    	</tusp:menuItem>
	</sec:authorize>
</sec:authorize>


