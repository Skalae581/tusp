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
<c:url var="post_url" value="/saeulen/saeulen/startseite" />
	<tusp:panel heading="Ihre Daten">
		<form:form method="POST" action="${post_url}">
		<div class="container">
		<div class="row">
			<div class="col-md-4 card" id="dev2">		
							<div class="form-group">
						        <div class="thumbnail">
						          <!-- Body + Foorter -->
						          <div class="caption card-footer text-left">
						           
						            <!--  Obere Liste -->
						            <div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>Name:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.bezeichnung}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneSuperxVersion:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneSuperxVersion}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneDatum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneDatum}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li><i class="lighter text-center"></i>&nbsp;Weitere Informationen</li>
										</ul>
									</div>
									 <div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneHotfixNr:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneHotfixNr}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneHotfixDatum Datum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneHotfixDatum}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hiodbQuelle:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hiodbQuelle}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hiodbDatum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hiodbDatum}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>sosposVersion:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.sosposVersion}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>sosposDatum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.sosposDatum}</p></li>
										</ul>
									</div>
						          </div>
								</div>	
						      </div>
						  </div>
						  <div class="col-md-4 card" id="dev2">		
							<div class="form-group">
						        <div class="thumbnail">
						          <!-- Body + Foorter -->
						          <div class="caption card-footer text-left">
						           
						            <!--  Obere Liste -->
						            <div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>Name:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.bezeichnung}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneSuperxVersion:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneSuperxVersion}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneDatum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneDatum}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li><i class="lighter text-center"></i>&nbsp;Weitere Informationen</li>
										</ul>
									</div>
									 <div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneHotfixNr:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneHotfixNr}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hisoneHotfixDatum Datum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hisoneHotfixDatum}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hiodbQuelle:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hiodbQuelle}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>hiodbDatum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.hiodbDatum}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>sosposVersion:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.sosposVersion}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>sosposDatum:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.sosposDatum}</p></li>
										</ul>
									</div>
						          </div>
								</div>	
						      </div>
						  </div>
						  <div class="col-md-4 card">		
							<div class="form-group">
						        <div class="thumbnail">
						          <!-- Body + Foorter -->
						          <div class="caption card-footer text-left">
						           
						            <!--  Obere Liste -->
						            
									<div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>von:</p></li><li></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.von}</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>bis:</p></li>
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.bis}</p></li>
										</ul>
									</div>
									<div>
										<ul class="list-inline">
											<li><i class="lighter text-center"></i>&nbsp;Weitere Informationen</li>
										</ul>
									</div>
									 <div>
										<ul class="list-inline">
											<li class="list-inline-item"><i class="lighter text-center"></i> <p>${saeule.begruendung}:</p></li><li></li>
										</ul>
									</div>
						          </div>
								</div>	
						      </div>
						  </div>	
			</div>
		</div>
		<tusp:formRow label="" fieldname="">
		<a href="<c:url value="/saeule/saeulen/startseite" />" class="btn btn-default"><spring:message code="Zurück" /></a>
		<input type="submit" value="Sprichern" class="btn btn-default" />
		</tusp:formRow>
	</form:form>
</tusp:panel>
</tusp:body>
</tusp:html>
