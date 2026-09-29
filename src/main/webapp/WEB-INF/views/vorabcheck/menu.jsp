<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
    <tusp:menuItem href="${pageContext.servletContext.contextPath}/vorabcheck/" label="vorabcheck.menu">
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/vorabcheck/" label="vorabcheck.perform" />
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/bewVervollst/" label="vervollst.admin.title"/>
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/bewVervollst/aos/" label="vorabcheck.admin.aos"/>
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/ampelsystem/vervollstTest/" label="vorabcheck.admin.ampelsystem"/>
    	<tusp:menuItem href="${pageContext.servletContext.contextPath}/vorabcheck/admin/studiengaenge/" label="vorabcheck.admin.titel"/>
    </tusp:menuItem>
</sec:authorize>


