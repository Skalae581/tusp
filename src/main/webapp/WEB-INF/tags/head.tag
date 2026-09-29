<%@ tag language="java" pageEncoding="UTF-8" description="Surrounding HTML-Tag and Doctype"  %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="titleCode" required="false" type="java.lang.String" description="Code mit dem der Seitentitel geladen werden kann" %>

<head data-locale="${pageContext.response.locale}" data-contextPath="${pageContext.servletContext.contextPath}">

	<meta charset="UTF-8">
	
	<link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
	
	<!-- JQUERY -->
	<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
	
	<script src="${pageContext.servletContext.contextPath}/resources/js/i18n.js"></script>
	
	<!-- BOOTSTRAP -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.7/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.7/css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="${pageContext.servletContext.contextPath}/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	
	<!-- JQUERY Validate --><!-- See documentation: https://jqueryvalidation.org/documentation/ -->
	<script src="${pageContext.servletContext.contextPath}/resources/js/jquery.validate.min.js"></script>
	
	<!-- JQUERY Ui -->
	<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/jquery-ui.min.css">
	
	<script src="${pageContext.servletContext.contextPath}/resources/js/table.js"></script>
	
	<script src="${pageContext.servletContext.contextPath}/resources/js/global.js"></script>
	
	<c:if test="${!empty titleCode}">
		<title><tusp:out key="${titleCode}" /></title> 
	</c:if>
	
	<jsp:doBody />
</head>