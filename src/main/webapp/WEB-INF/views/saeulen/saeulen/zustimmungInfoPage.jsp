
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

<div>
       	<ul class="list-inline">
			<li>
				<a href="<c:url value="/saeulen/saeulen/" />" 
					class="btn btn-link"> <span><i class="glyphicon glyphicon-home">&nbsp;</i></span>Zurück zur Startseite</a>
			</li>
			<li>
				<a href="<c:url value="/saeulen/saeulen/aktualisierungsProtokoll?id=${zielSaeuleId}" />"
				 	class="btn btn-link"> <span><i class="glyphicon glyphicon-arrow-left">&nbsp;</i></span>Zurück zur Liste</a>
			</li>
          </ul>
</div>	

<tusp:panel heading="Zustimmungs-Prozess Info">
	

                <div class="row">
                	
                	<div class="col-sm-12">
                	
	                	<div class="col-sm-6"> 
		                	
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
	      										
								 <tusp:panel heading="Mitarbeiter für die Zustimmung">
				        
				        				<c:choose>
				        						<c:when test="${empty zustimmungList}">
				        							<p>Keine Miterbeiter für diese Aktualisierung</p>
				        						</c:when>
				        						
				        						<c:otherwise>
				        							 <c:forEach items="${zustimmungList}" var="zustimmungen" >  
													        <c:forEach var="entry" items="${zustimmungen}">
													        	<ul class="list-inline">
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
																  <li class="list-inline-item">
																  	${entry.key.name}
																  </li>
																  <li class="list-inline-item">
																  	${entry.key.nachname}
																  </li>
																  <li class="list-inline-item">
																  [	${entry.key.benutzername} ]
																  </li>
																 
																</ul>
					
															</c:forEach>
					  									</c:forEach>
					        						</c:otherwise>
				        					
				        					</c:choose>
								      
								     
							</tusp:panel>
								
										
							  

	                  	</div>
	                  	
	                  	<div class="col-sm-1"> </div>
  	
	               </div>	
                </div>
				
				               
			
	</tusp:panel>
	
	
</tusp:body>
</tusp:html>
