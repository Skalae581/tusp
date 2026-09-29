<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<tusp:html>
<tusp:head>
	<title><spring:message code="autos.title" /></title>  
	<script src="${pageContext.servletContext.contextPath}/resources/js/autos/autos.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/autos/autos.css" />
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/autos/benutzer/lastpage" />
	<tusp:panel heading="Ihre Daten">
		<form:form method="POST" action="${post_url}">
		<div class="container">
		<div class="row">
			<div class="col-md-4 card"> 	
				<div class="form-group">
					<!-- Das Bild von Auto	 -->
					<div class="thumbnail">
						<div class="caption text-center">
							<div class="position-relative">
								<img src="${pageContext.servletContext.contextPath}/resources/img/auto03.png" style="width:100%;height:250px;" />
							</div>
						<h4 id="thumbnail-label"><i>${autoObj.name}</i></h4>
					<div class="thumbnail-description smaller">Auto Beschreibung ${autoObj.name}</div>
				</div>
				<!-- Body + Footer -->
				<div class="caption card-footer text-left">
					<!--  Obere Liste -->
					<div class="list-group">
						<i class="list-group-item list-group-item-info">${autoObj.name}</i>
						<i class="list-group-item list-group-item-info">${autoObj.dieselOElektro}</i>
					</div>
					<!-- Untere Inline Liste  hier alle weitere Elemene rein packen-->
						<div>
							<ul class="list-inline">
							<li><i class=" glyphicon glyphicon-wrench lighter text-center"></i>&nbsp;Eigenschaften</li>
							</ul>
						</div>
							<ul class="list-inline">
								<li class="list-inline-item"><i class="lighter text-center"></i> <p>${autoObj.breite}</p></li>
								<li></li>
								<li class="list-inline-item"><i class="lighter text-center"></i> <p>${autoObj.ladevolumen}</p></li>
								<li></li>
								<li class="list-inline-item"><i class="lighter text-center"></i> <p>${autoObj.dieselOElektro}</p> </li>
								<li class="list-inline-item"><i class="lighter text-center"></i> <p>${autoObj.reichweite}</p></li>
								<li></li>
								<li class="list-inline-item"><i class="lighter text-center"></i> <p>${autoObj.hoehe}</p></li>
								<li></li>
								<li class="list-inline-item"><i class="lighter text-center"></i> <p>${autoObj.anhaengerkopplung}</p> </li>
							</ul>   
						</div>
					</div>
				</div>	
			</div>
			<div class="container">
				<div class="col-md-4 card"> 
					<div class="thumbnail">		
						<div class="form-group">
							<div class="card-body">
								<h5 class="card-title">Ihre Daten</h5>
								<!-- Body + Foorter -->
								<div class="caption card-footer text-left">
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>von:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${buchungObj.von}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>bis:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${buchungObj.bis}</p></li>
										</ul>
									</div>
									<ul class="list-inline">
										<li class="list-inline-item"><i class="lighter text-center"></i> <p>Vorname:</p></li><li></li>
										<li class="list-inline-item"><i class="lighter text-center"></i> <p>${{userObj.vorname}}</p></li>
									</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>Nachname:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${userObj.nachname}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>Telefonnummer:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${userObj.tel}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>E-Mail:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${userObj.email}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>Institut:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${userObj.institut}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>Führerschein:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${userObj.fuererschein}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>Gültigkeit:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${userObj.gueltigkeit}</p></li>
										</ul> 
									</div>
								</div>
							</div>
						</div>
					</div>	
				</div>
			</div>
		</div>
		<tusp:formRow label="" fieldname="">
		<a href="<c:url value="/autos/benutzer/userinfo" />" class="btn btn-default"><spring:message code="Zurück" /></a>
		<input type="submit" value="Sprichern" class="btn btn-default" />
		</tusp:formRow>				  					
	</form:form>										
</tusp:panel>
</tusp:body>
</tusp:html>
