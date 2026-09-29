<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()"> <!--TODO rollen prüfen -->
    <tusp:menuItem href="${pageContext.servletContext.contextPath}/precheck/?lang=en" label="precheck.menu">
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/precheck/?lang=en" label="precheck.home" />
    	<tusp:menuItem href="${pageContext.servletContext.contextPath}/precheck/bearbeiter/" label="precheck.interessenten"/>
   		<tusp:menuItem href="${pageContext.servletContext.contextPath}/precheck/admin/studiengang/list" label="precheck.studiengaenge"/>
    </tusp:menuItem>
</sec:authorize>


