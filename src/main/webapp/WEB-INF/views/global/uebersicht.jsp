<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
</tusp:head>
<tusp:body>
	<div class="container">
		<div class="row">
			<div class="col-md">
				<h1>Konfiguration</h1>
			</div>
			<div class="col-md">
				<h4>Allgemein</h4>
				<a href="<c:url value="/global/admin/config/list" />" class="btn btn-success">Gesamt</a>
				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.webservice" />" class="btn btn-default">Webservices</a>
				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.hio.db" />" class="btn btn-default">HIS DB</a>
			</div>
<!-- 			<div class="col-md"> -->
<!-- 				<h4>APP</h4> -->
<!-- 			</div> -->
			<div class="col-md">
				<h4>STU</h4>
				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.psv.mobon" />" class="btn btn-default">Mobility Online</a>
				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.psv.tmabs" />" class="btn btn-default">Techno Bachelor</a>
				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.stu.sptim" />" class="btn btn-default">SPTIM</a>
				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.stu.gdm" />" class="btn btn-default">Gebührendaten</a>
				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.psv.unihh" />" class="btn btn-default">UniHH-Import</a>
			</div>
<!-- 			<div class="col-md"> -->
<!-- 				<h4>EXA</h4> -->
<%-- 				<a href="<c:url value="/global/admin/config/list?prefix=de.tuhh.tusp.utils.ldap" />" class="btn btn-default">LDAP</a> --%>
<!-- 			</div> -->
			<div class="col-md">
				<h1>SSH</h1>
				<a href="<c:url value="/global/admin/knownhost/list" />" class="btn btn-default">KnownHosts</a>
				<a href="<c:url value="/global/admin/identity/list" />" class="btn btn-default">RSA-Keys</a>
				<a href="<c:url value="/global/admin/connection/test" />" class="btn btn-default">Verbindung testen</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md">
				<h1>Startkonfiguration</h1>
				<table>
					<c:forEach items="${properties}" var="entry">
						<tr>
							<td>${entry.key}</td>
							<td>${entry.value}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="row">
			<h2>Build: ${version}</h2>
		</div>
	</div>
</tusp:body>
</tusp:html>