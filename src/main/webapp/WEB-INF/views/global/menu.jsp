<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STU_SENIOR_MANAGER')">
	<tusp:menuItem href="${pageContext.servletContext.contextPath}/" label="admin.menu">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/admin/logs/" label="admin.logs" />
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/admin/" label="admin.config" />
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/admin/userprefs/" label="admin.userprefs" />
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/admin/jobs" label="admin.jobs" />
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/admin/createJob/" label="admin.createjob" />
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/admin/semesterbereinigung/" label="admin.semesterbereinigung" />
		</sec:authorize>
			
		<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STU_SENIOR_MANAGER')">
			<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/gebuehren/gebuehrendaten/" label="admin.gebuehrendaten" />
				<tusp:menuItem href="${pageContext.servletContext.contextPath}/global/gewerbelehrer/" label="admin.gewerbeleherdaten" />
				<tusp:menuItem href="${pageContext.servletContext.contextPath}/tsstudent/" label="admin.tsstudent" />
		</sec:authorize>
		

		
		
	</tusp:menuItem>
</sec:authorize>

