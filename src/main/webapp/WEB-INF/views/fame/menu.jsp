<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authorize access="hasAnyRole('MITARBEITER', 'ZULBEARB', 'APP_ADMIN') && !hasRole('ADMIN')">
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/fame/user/" label="fame.menu" />
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ADMIN')">
    	<tusp:menuItem href="${pageContext.servletContext.contextPath}/fame/user/" label="fame.menu">
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/fame/user/" label="fame.home" />
       		<li role="separator" class="divider"></li>
		    <tusp:menuItem href="${pageContext.servletContext.contextPath}/fame/admin/" label="fame.admin"/>
    	</tusp:menuItem>
	</sec:authorize>
</sec:authorize>

