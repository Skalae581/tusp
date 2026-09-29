<%@tag import="de.tuhh.tusp.utils.MessageContainer"%>
<%@tag import="org.springframework.web.context.WebApplicationContext"%>
<%@tag import="org.springframework.web.servlet.support.RequestContextUtils"%>

<%@ tag language="java" pageEncoding="UTF-8" description="integrert das Body-Tag und das Menü und den Seitentitel"  %>

<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="vollebreite" required="false" type="java.lang.Boolean" description="Gibt an ob die Seite die volle Breite nehmen darf (Mitarbeiteransicht)" %>
<c:set var="vollebreite" value="${(empty vollebreite) ? true : vollebreite}" />

<body class="${vollebreite ? '' : 'narrow'}">
	<div class='container-fluid pagewrap'>
		<jsp:include page="/WEB-INF/templates/headline.jsp"></jsp:include>
		
		<main class="content tusp-body">

			<noscript>
				<spring:message code="noscript.message" var="noscript"/>
				<tusp:infobox message="${noscript}" clazz="alert-info" />
			</noscript>
			
			<%
				WebApplicationContext context = 
				RequestContextUtils.findWebApplicationContext(request);
				MessageContainer mc = context.getBean(MessageContainer.class);
				jspContext.setAttribute("getErrors", mc.getErrors());
				jspContext.setAttribute("getWarnings", mc.getWarnings());
				jspContext.setAttribute("getSuccesses", mc.getSuccesses());
				jspContext.setAttribute("getInfos", mc.getInfos());
			%>
			
			
			<tusp:infobox messages="${getErrors}" clazz="alert-danger" />
			<tusp:infobox messages="${getWarnings}" clazz="alert-warning" />
			<tusp:infobox messages="${getSuccesses}" clazz="alert-success" />
			<tusp:infobox messages="${getInfos}" clazz="alert-info" />
			
			<jsp:doBody />
		</main>
		
	</div>
	
	<jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
</body>