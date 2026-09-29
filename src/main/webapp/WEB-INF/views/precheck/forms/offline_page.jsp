<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    
    <script src="${pageContext.servletContext.contextPath}/resources/js/global.js"></script>
	<title><spring:message code="precheck.offline_page.title" /></title>
	
</tusp:head>
<tusp:body vollebreite="false">

	<tusp:panel heading="">
		
		<p>
			<spring:message code="precheck.offline_page.txt" />
		</p>
		
		<%-- <a href="<c:url value="/precheck/" />" class="btn btn-default"><spring:message code="precheck.btn.restart" /></a> --%>

		<!-- <button class="btn btn-primary" id ="coolerCloseBtnId" >Back to the beginning</button> -->
	</tusp:panel>

</tusp:body>
</tusp:html>