<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
	
	
	<script src="${pageContext.servletContext.contextPath}/resources/pdf.js/pdf.js"></script>
	<style>
		canvas {
			-webkit-box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.75);
			-moz-box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.75);
			box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.75);
			margin: 20px;
		}
		#url, footer {
			display:none !important;
		}
	</style>
</tusp:head>
<tusp:body>

	<span id="url">${pageContext.servletContext.contextPath}/${url}</span>
	<div id="menu" class="form-inline">
	  <span id="page"><tusp:out key="label.page" />: <span id="page_num"></span> / <span id="page_count"></span></span>
	  <button id="prev" class="btn btn-default"><tusp:out key="btn.prev_page" /></button>
	  <button id="next" class="btn btn-default"><tusp:out key="btn.next_page" /></button>
	  <select id="scale" class="form-control form-control-inline">
	  	<option value="0.25">25%</option>
	  	<option value="0.5">50%</option>
	  	<option value="0.75">75%</option>
	  	<option value="0.9">90%</option>
	  	<option value="1.0" selected="selected">100%</option>
	  	<option value="1.5">150%</option>
	  	<option value="2.0">200%</option>
	  	<option value="2.5">250%</option>
	  	<option value="3.0">300%</option>
	  </select>
	  <button id="rotate-left" class="btn btn-default"><tusp:out key="btn.rotate_left" /></button>
	  <button id="rotate-right" class="btn btn-default"><tusp:out key="btn.rotate_right" /></button>
	  <c:if test="${not empty back}">
	  	<a href="${pageContext.servletContext.contextPath}/${back}" class="btn btn-default"><tusp:out key="btn.back" /></a>
	  </c:if>
	</div>
	
	<div id="wrapper">
		<canvas id="the-canvas" style="position: absolute"></canvas>
	</div>
	
	<script src="${pageContext.servletContext.contextPath}/resources/pdf.js/loader.js"></script>
	
</tusp:body>
</tusp:html>