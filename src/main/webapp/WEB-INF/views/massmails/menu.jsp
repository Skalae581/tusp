<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasAnyRole('MASSENMAIL')">
    <tusp:menuItem href="${pageContext.servletContext.contextPath}/massmails/" label="massmails.menu">
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/massmails/" label="massmails.home" />
		<tusp:menuItem href="${pageContext.servletContext.contextPath}/massmails/verteiler" label="massmails.verteiler_inhalt" />
    </tusp:menuItem>
</sec:authorize>