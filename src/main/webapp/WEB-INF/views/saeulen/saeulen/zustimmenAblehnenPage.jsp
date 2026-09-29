
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
<c:url var="post_url" value="/saeulen/saeulen/zustimn/" />

<tusp:panel heading="Ablehnung einer Aktualisierung">
	
	
	
	<c:choose>
		  <c:when test="${hatAbgelehnt == false }">
		  
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
								      							dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus. 
								      							
								      						</p>
														</tusp:panel>
								                  	</div>
							                  	</div>
							                  	<div class="row">
								                  	<div class="col-sm-12">
								                  	
								                  	 	<tusp:panel heading="Welche Aktualisierung und wer hat es bantragt?">
								                  	 	
								                  	 		
								                  	 		<!-- wird für POST gebraucht -->
										             		<input type="hidden" name="aktualisierungUID"  value="${aktualisierungUID}" />	
										         			<input type="hidden" name="currentUser"  value="${currentUser}" />	
				 				             		
										             		
										             		
										                	<label>Ziel Quelle</label>
										                	<input type="text" name="bezeichnung"  value="${aufAktualisieren.bezeichnung}" disabled="disabled" />		            
								                  	 		<label>Von wo?</label>		
								      						<input type="text" name="bezeichnung"  value="${vonWoAktualisieren.bezeichnung}" disabled="disabled" />		            		      							
								      						<label>Name:</label> 	
							      							
							      							<ul class="list-inline">
							      								<li><input type="text" name="bezeichnung"  value="${user.benutzername}" disabled="disabled" /></li>
							      								<li></li>
							      								<li></li>
							      								<li><input type="text" name="bezeichnung"  value="${user.name} ${user.nachname}" disabled="disabled" /></li>
							      							</ul>
				
								      						<label>Wann soll aktualisiert werden?</label>	
								      						<input type="text" name="bezeichnung"  value="${saeuleaktualisierung.datum}" disabled="disabled" />	
								      							
								                  	 		<p><label>Warum?</label></p>					   	 	 							
								      						<textarea name="begruendung" disabled="disabled" cols="90" rows="5">${saeuleaktualisierung.begruendung }</textarea>
				
														</tusp:panel>
								                  	</div>
						                  		</div>
					                    </div>
					                    <div class="col-sm-5"> 
					                       <tusp:panel heading="Grund für die Ablehnung">
					      										
												<label>Datum</label>
												<input type="text" name="datum" value="${datumHeute}"  disabled="disabled"/>
												
												<label>Benutzer</label>
												<input type="text" name="currentUser" value="${currentUser}"  disabled="disabled"/>
												
												<label>Begründung</label><br>
												<textarea id="begruendungZustimmAblehnung" name="begruendung" cols="75" rows="15"></textarea>
								               
												     
											</tusp:panel>
												
											<div>
											
				
				                   				<ul class="list-inline">
				                   					<li>
														<a href="<c:url value="/saeulen/saeulen/" />" class="btn btn-default">Abbrechen</a>
													</li>
				                   					<li>
					                   					<input type="submit" value="Ich kann jetzt nicht zustimmen!" class="btn btn-primary" id="zustimmenAblehnenBtn"/>				           
				                   					</li>
				                   				</ul>
				                   				
				                   				
							                  
												
											
											</div>				
											  
				
					                  	</div>
					                  	
					                  	<div class="col-sm-1"> </div>
				  	
					               </div>	
				                </div>
				
				               
				
				
				            </tusp:form>
				            
				
				    </form:form>

		  </c:when>
		  
		  <c:otherwise>
		    Sie haben bereits abgelehnt!! Für die Zustimmung HIER klicken: TODO Zustimmungs URL
		    
		  </c:otherwise>
	</c:choose>
 
	
	

	</tusp:panel>
	
	
</tusp:body>
</tusp:html>
