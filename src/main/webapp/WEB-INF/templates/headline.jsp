<%@page import="de.tuhh.tusp.global.service.ConfigService"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
boolean showLangSwitch = request.getMethod().toLowerCase().equals("get");
pageContext.setAttribute("showLangSwitch", showLangSwitch, PageContext.PAGE_SCOPE);

if(showLangSwitch) {
	StringBuilder sb = new StringBuilder();
	java.util.Enumeration<String> attrs =  request.getParameterNames();
	while(attrs.hasMoreElements()) {
	    String attr = attrs.nextElement();
	    
	    if("lang".equals(attr)) continue;
	    
	    sb.append("&");
	    sb.append(attr);
	    sb.append("=");
	    sb.append(request.getParameter(attr));
	}
	
    pageContext.setAttribute("getDe", "?lang=de"+sb.toString(), PageContext.PAGE_SCOPE);
    pageContext.setAttribute("getEn", "?lang=en"+sb.toString(), PageContext.PAGE_SCOPE);
     
}
	
%>

<header>
	
	<div class='row'>
		<div class='col-md-6 header_logo'>
			<div class='logo'>
				<a href="<c:url value="/" />">
					<img src="https://tune.tuhh.de/tusp/resources/img/tusp_logo1.svg" 
						alt="TU Service Portal" 
						title="TU Service Portal" 
						class="logoPortal"
					>
				</a>
			</div>
		</div>
		<div class='col-md-6 header_menu'>
			<!-- Language switch -->
			
			<p class="languages">
				&nbsp;
				<c:if test="${showLangSwitch}">
					<a href="${getDe}">DE</a>|<a href="${getEn}">EN</a>
				</c:if>
			</p>
			
			<sec:authorize access="!isAuthenticated()">
				<%-- <a href="<c:url value="/login" />"><spring:message code="menu.login_local" /></a>
				<br> --%>
				<p><a href="<c:url value="/shibstart" />"><spring:message code="menu.login_shib" /></a></p>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<p><a href="<c:url value="/logout" />"><spring:message code="menu.logout" /></a></p>
				<p><spring:message code="headline.angemeldet_als"/><a href="<c:url value="/userinfo" />"><strong><sec:authentication property="name"/></strong></a></p>
			</sec:authorize>
		</div>
	</div>
		
	<jsp:include page="/WEB-INF/templates/menu.jsp"></jsp:include>
</header>