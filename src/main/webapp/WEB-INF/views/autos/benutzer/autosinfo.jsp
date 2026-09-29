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
<c:url var="post_url" value="/autos/benutzer/buchung" />

<tusp:panel heading="Autoseingenschaften">


	<form:form method="POST" action="${post_url}" modelAttribute="autos" id="autoForm">

		<tusp:form>
		
		  <div class="container">  
			<div class="row">		
				 <fieldset>
	                 <c:forEach items="${autos}" var="auto">
	                 
	 					<div class="col-md-4 card"> 	
	 										
							<div class="form-group">
								  	
								<!-- Das Bild von Auto	 -->
						        <div class="thumbnail">
						          <div class="caption text-center">
						            <div class="position-relative">
						              <img src="${pageContext.servletContext.contextPath}/resources/img/auto03.png" style="width:100%;height:250px;" />
						            </div>
						            <h4 id="thumbnail-label"><i>${auto.name}</i></h4>
						            <div class="thumbnail-description smaller">AUto Beschreibung ${auto.name}</div>
						          </div>
		          
						          <!-- Body + Foorter -->
						          <div class="caption card-footer text-left">
						           
						            <!--  Obere Liste -->
						            <div class="list-group">
									  <i class="list-group-item list-group-item-info">${auto.name}</i>
									  <i class="list-group-item list-group-item-info">${auto.dieselOElektro}</i>
									</div>
									
									<!-- Untere Inline Liste  hier alle weitere Elemene rein packen-->
									<div>
										<ul class="list-inline">
											<li><i class=" glyphicon glyphicon-wrench lighter text-center"></i>&nbsp;Eigenschaften</li>
										</ul>
									</div>
									 <ul class="list-inline">
						              <li class="list-inline-item"><i class="lighter text-center"></i> <p>${auto.breite}</p></li>
						              <li></li>
						              <li class="list-inline-item"><i class="lighter text-center"></i> <p>${auto.ladevolumen}</p></li>
						              <li></li>
						              <li class="list-inline-item"><i class="lighter text-center"></i> <p>${auto.dieselOElektro}</p> </li>
									  <li class="list-inline-item"><i class="lighter text-center"></i> <p>${auto.reichweite}</p></li>
						              <li></li>
						              <li class="list-inline-item"><i class="lighter text-center"></i> <p>${auto.hoehe}</p></li>
						              <li></li>
						              <li class="list-inline-item"><i class="lighter text-center"></i> <p>${auto.anhaengerkopplung}</p> </li>
						             </ul> 
						             
						             <!-- Radio Button  style sehe auto.css-->
						             <div class="caption card-footer text-right">
								             <div class="radio">
								             
									            
									            <%--  <c:choose>
												  <c:when test="${auto.id == 1}">
									             		<input id="autoSelect${auto.name}" name="autoSelect" value="${auto.name}" type="radio" checked>
												  </c:when>
												  <c:otherwise>
														<input id="autoSelect${auto.name}" name="autoSelect" value="${auto.name}" type="radio">

												  </c:otherwise>
												</c:choose> --%>
												
												
												
												<tusp:formRow label="" fieldname="id">
													<input id="autoSelect${auto.name}" class="radioButton" name="autoSelect"
													 value="${auto.name}" type="radio">
													
													 
<%-- 			<c:if test="${auto.id == autoId}">checked</c:if> --%>										
												</tusp:formRow>
							  					
									 		</div>	
						             </div>
           
						          </div>
								</div>	
						      </div>
						  </div>	
						   							  
	  		  		</c:forEach>  
	  		  		
	  		  									
														
	    	</fieldset>
	    	
	    	
	    	
 
 	   </div>
 	   
 	   
 	   	<div class="caption card-footer text-right">
	    	
		    	<tusp:formRow label="" fieldname="">
	                  <input type="submit" value="Weiter"  class="btn btn-outline-light btn-lg firstPageWeiterButton" />
	   			</tusp:formRow>
	    	
	    	</div>	
 	</div>
  	
	   	 	
   </tusp:form>
</form:form>

	</tusp:panel>
</tusp:body>
</tusp:html>