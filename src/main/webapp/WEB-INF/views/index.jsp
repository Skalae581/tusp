<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
	
	<style type="text/css">
 		.img-wrapper { 
 			display: block; 
 			width: 100px; 
 			height: 100px; 
 			margin: 0 auto;
 			vertical-align:middle;
 		} 
		
 		.img-wrapper img { 
 		    width: 100%; 
 		    height: auto; 
 		} 
		
 		.tile a {
 			height: 200px;
 			width: 200px;
 			padding: 25px;
 			display: block;
 			border: 3px solid #000099;
 			color: black;
 			text-align: center;
 		}
		
 		.tile a:hover {
 			text-decoration: none;
 			-webkit-box-shadow: 0px 0px 10px 4px rgba(0,0,0,0.65);
			-moz-box-shadow: 0px 0px 10px 4px rgba(0,0,0,0.65);
			box-shadow: 0px 0px 10px 4px rgba(0,0,0,0.65);
 		}
 		
		.tile {
 			margin-bottom: 25px;
		}
		#tuhh {
		    width: 150%;
		    margin-left: -25%;
		    margin-top: 10%;
		}
		#tune {
		    width: 120%;
		    margin-left: -10%;
		    margin-top: 10%;
		}
		
		.container{
			width: 100%;
		}
	
	</style>
</tusp:head>
<tusp:body vollebreite="false">



	<h1 class="text-center"><spring:message code="appname"/></h1>
    <p class="text-center"><spring:message code="index.willkommen"/></p>
    
    
    
    <div class="container">
    	<div class="row">
		    <div class="tile col-md-3 col-md-offset-3">
		    	<a href="https://www.tuhh.de/tuhh/startseite.html">
		    		<span class="img-wrapper"><img src="https://intranet.tuhh.de/img/logo/web_rgb_de.svg" alt="TUHH" id="tuhh"/></span>
		    		<span>TUHH</span>
		    	</a>
		    </div>
		    <div class="tile col-md-3 col-md-offset-">
		    	<a href="https://www.tuhh.de/rzt/">
		    		<span class="img-wrapper"><img src="https://www.tuhh.de/t3resources/rzt/layout01INSTITUTE/Img/inst_logo.svg" alt="Rechenzentrum" /></span>
		    		<span>Rechenzentrum</span>
		    	</a>
		    </div>
	    </div>
	    
    	<div class="row">
		    <div class="tile col-md-3 col-md-offset-3">
<!-- 		    	<a href="https://tune.tuhh.de"> -->
		    	<a href="https://tune.tuhh.de">
		    		<span class="img-wrapper"><img src="https://tune.tuhh.de/HISinOne/images/tuhh/logo/inst_logo_tune.png" alt="TUNE" id="tune" /></span>
		    		<span>TUNE (Campusmanagement)</span>
		    	</a>
		    </div>
		    <div class="tile col-md-3 col-md-offset-">
		    	<a href="https://www.tuhh.de/sls/service-fuer-lehre-und-studium.html">
		    		<span class="img-wrapper">
		    			<img src="https://www.tuhh.de/t3resources/sls/layout01INSTITUTE/Img/inst_logo.svg" alt="SLS" />
		    		</span>
		    		<span>Servicebereich Lehre und Studium</span>
		    	</a>
		    </div>
	    </div>
    </div>
	
	
	
	<%--
	<p><spring:message code="index.angemeldet_als"/><strong>${user}</strong></p> 
	
	<p>
    	<spring:message code="index.rollen"/>
    	<c:forEach items="${authorities}" var="auth" >
    		
    		<c:out value="| ${auth} |"></c:out> 
    		
    	</c:forEach>
	</p> 
	--%>
	
</tusp:body>
</tusp:html>