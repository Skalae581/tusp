
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<tusp:html>
<tusp:head>
 	<title><spring:message code="saeulen.title" /></title>  
	    <script src="${pageContext.servletContext.contextPath}/resources/js/saeulen/saeulen.js"></script>
	    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/saeulen/saeulen.css" />
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/saeulen/saeulen/zustimmenPost" />

<tusp:panel heading="Zustimmung">

<c:choose>
  <c:when test="${hatZugestimmt == false}">
  
    <form:form method="post" action="${post_url}" modelAttribute="zustimmungen">
		
			 <tusp:form>
			
                <div class="row">
                
                	<div class="col-sm-12">
	                	<div class="col-sm-6"> 
		                	<div class="row">
				                <div class="col-sm-12"> 
				                      <tusp:panel heading="Info"> 						
				      						<p>
				      							Lorem ipsum dolor sit amet, consetetur sadipscing elitr, 
				      							sed diam nonumy eirmod tempor invidunt ut labore et dolore magna 
				      							aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo 
				      							dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus 
				      							est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur 
				      							sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore 
				      							magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores 
				      							et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor 
				      							sit amet.
				      						</p>
										</tusp:panel>
				                  	</div>
			                  	</div>
			                  	<div class="row">
				                  	<div class="col-sm-12">
				                  	
				                  	 	<tusp:panel heading="Welche Aktualisierung und wer hat es bantragt?">
				                  	 		<tusp:panel heading="Benutzername"> 		
				      							<p>${user.benutzername}</p>
				      						</tusp:panel>
				      						<tusp:panel heading="Name"> 		
				      							<p>${user.name} ${user.nachname}</p>
				      						</tusp:panel>
				                  	 		<tusp:panel heading="Warum?"> 						   	 	 						
				      							<p>${saeuleaktualisierung.begruendung }</p>
				      						</tusp:panel>
				      						<tusp:panel heading="Von wo?"> 	
				      							<p>${vonWoAktualisieren.bezeichnung }</p>
				      						</tusp:panel>	
				      						<tusp:panel heading="Wohin?"> 	
				      							<p>${aufAktualisieren.bezeichnung}</p>
				      						</tusp:panel>	
				      						
										</tusp:panel>
				                  	</div>
		                  		</div>
	                    </div>
	                    <div class="col-sm-6"> 
	                       <tusp:panel heading="Zustimmen">
	      										
	      						 <c:forEach items="${zustimmungList}" var="zustimmungen" >  
								        
								      <tusp:panel heading="Mitarbeiter für die Zustimmung">
								        
								        <c:forEach var="entry" items="${zustimmungen}">
								        	<ul class="list-inline">
											  <li class="list-inline-item">
											  	${entry.key.name}
											  </li>
											  <li class="list-inline-item">
											  	${entry.key.nachname}
											  </li>
											  <li class="list-inline-item">
											  [	${entry.key.benutzername} ]
											  </li>
											  <li class="list-inline-item">										  	
    											<input type="checkbox" name="zustimmung_user" value="${entry.value.id}" id="" 
													  <c:if test="${entry.value.zugestimmt == true}">
														  checked="checked" 
													  </c:if>
													  <c:if test="${currentBenutzer != entry.key.benutzername}">
														  disabled="disabled"
													  </c:if>
												/>
		
											  </li>
											</ul>

										</c:forEach>
										
									</tusp:panel>
								           
								  </c:forEach>
							</tusp:panel>
	                  	</div>
	               </div>	
                </div>

                <tusp:formRow label="" fieldname="">
                
                    <a href="<c:url value="/saeulen/saeulen/" />" class="btn btn-default">Abbrechen</a>
                    
                   	<input type="submit" value="Zustimmen!" class="btn btn-default" 
                   	 	<c:if test="${entry.value.zugestimmt == false}">
								disabled = "disabled"						   
						 </c:if>
						 
					/>
                 
                </tusp:formRow>


            </tusp:form>
            

    </form:form>
  </c:when>
  
  <c:otherwise>
     Sie haben bereits zugestimmt!!
  </c:otherwise>
</c:choose>

	

	

	</tusp:panel>
	
	
</tusp:body>
</tusp:html>
