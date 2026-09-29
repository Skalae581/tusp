<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>


<tusp:html>
<tusp:head>
    <title><spring:message code="Mail Verteiler" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/massmails/massmails.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
  	<script src="${pageContext.servletContext.contextPath}/resources/js/ckeditor5/ckeditor.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/massmails/massmails.js"></script> 
</tusp:head>
<tusp:body>
		
	 <div class="col-md-12 ">	
	
		
		 <div class="row">
			   <div class="col-md-12 ">		
			  	<div>
			  		<h2>Fertig!</h2>
			  	</div>
			  	
			  	<br>
				<hr>
			  </div>
		  </div>
		  
		  <div class="row">
		  		 <div class="col-md-2 ">	
		  			<a href="<c:url value="/massmails/" />" class="btn btn-default">Beenden</a>
		  		</div>
		  		 <div class="col-md-3 ">
		  		 </div>
		   		<div class="col-md-3 ">	
		   
		   			
					<form action="downloadEmailCSV" method="get" id="downloadEmailCSV">
			  			<button id="submitId" type="submit" class="btn btn-defoult">
					  		 <span class="glyphicon glyphicon-arrow-down">
					 	 	 </span>&nbsp;&nbsp;Zusammenfassung herunterladen&nbsp;&nbsp;
					  		 <span class="glyphicon glyphicon-arrow-down"></span>
						 </button>
						<input type="hidden" name="uid" value="<c:out value="${uid}"></c:out>">
 				 </form>
				</div>				
		  </div>	
			
		  <div class="row"> 		
			   <div class="col-md-12 ">		
			   <div>
			  		<h3>Zusammenfassung:</h3>
			  	</div>
			  	<div>
			  		
			  		<ul class="mailErrorList list-inline">
			  			<li><p class="text-primary" style="font-size:18px;">Erforlgreich: <span class="text-warning" style="font-size:20px;">${successes}</span> </p><li>
						<li><p class="text-primary" style="font-size:18px;">Keine Email-Adresse gefunden: <span class="text-warning" style="font-size:20px;">${mailsNotFound}</span></p><li>
			  			<c:if test="${ rolePrueferExpiredCount > 0}">
			  				<li><p class="text-primary" style="font-size:18px;">Rolle Prüfer Abgelaufen: <span class="text-warning" style="font-size:20px;">${rolePrueferExpiredCount}</span></p><li>
			  			</c:if>
						<c:if test="${ rolePrueferVertreterExpiredCount > 0}">
			  				<li><p class="text-primary" style="font-size:18px;">Rolle Prüfer-Vertreter Abgelaufen: <span class="text-warning" style="font-size:20px;">${rolePrueferVertreterExpiredCount}</span></p><li>
			  			</c:if>
						<c:if test="${ roleLehrenderExpiredCount > 0}">
			  				<li><p class="text-primary" style="font-size:18px;">Rolle Lehrperson Abgelaufen: <span class="text-warning" style="font-size:20px;">${roleLehrenderExpiredCount}</span></p><li>
			  			</c:if>
						<c:if test="${ roleLehrenderVertreterExpiredCount > 0}">
			  				<li><p class="text-primary" style="font-size:18px;">Rolle Lehrperson-Vertreter Abgelaufen: <span class="text-warning" style="font-size:20px;">${roleLehrenderVertreterExpiredCount}</span></p><li>
			  			</c:if>
			  			<c:if test="${ roleLehrenderVertreterExpiredCount == 0 && 
			  						 roleLehrenderExpiredCount == 0 &&
			  						 rolePrueferVertreterExpiredCount == 0 && 
			  						 rolePrueferExpiredCount == 0}">
			  				<li><p class="text-primary" style="font-size:18px;">Rollen Abgelaufen: <span class="text-warning" style="font-size:20px;">${0}</span></p><li>
			  			</c:if>
			  			
			  			<li><p class="text-primary" style="font-size:18px;">Abgelaufene Accounts: <span class="text-warning" style="font-size:20px;">${accountsExpired}</span></p><li>	
			  			<li><p class="text-primary" style="font-size:18px;">Ausnahme Fehler (Meistens Account-Passwort nicht gesetzt!): <span class="text-warning" style="font-size:20px;">${errors}</span></p><li>
			  					  		
			  		</ul>
			  	</div>
			  	<br>
				<hr>
			  </div>
		  </div>
		  
		   <div class="row">
			   <div class="col-md-12 ">		
			  	<div>
			  		
			  		<h4>
			  		E-Mail-Zustellprotokoll. 
			  		Dieses Protokoll zeigt aktuelle Fehler und Warnungen für den ausgewählten Verteiler.
			  		 Sie können diese Informationen an RZ53 melden!
			  		</h4>
			  	</div>
			  	<br>
				<hr>
			  </div>
		  </div>
		
		
		  <div class="row">
		  
				  <div class="col-md-12">
				  
					  <div class="col-md-2">
					  	 
					      <h4>Erfolgreich:</h4>
					      	<br>
				       		<hr>
					      		<c:choose>
							       <c:when test="${empty successList}">
							      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
							      </c:when>
							      <c:otherwise>
							      	 <c:forEach var="success" items="${successList}">
							        	<p class="text-success"><c:out value="${success.vorname}  ${success.nachname}"/></p>
							   		 </c:forEach>
							      </c:otherwise>
						      </c:choose>
							  
					    </div>	
				   		 <div class="col-md-2">
						      <h4>E-Mail Adresse fehlt:</h4>
						      <br>
						      <hr>
						      <c:if test = "${!empty mailErrorList}">
						     
							      <c:choose>
								       <c:when test="${empty mailErrorList}">
								      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
								      </c:when>
								      <c:otherwise>
								      	  <c:forEach var="mailsError" items="${mailErrorList}">
									      		 <p class="text-danger"><c:out value="${mailsError.vorname}  ${mailsError.nachname}"/></p>
									  	  </c:forEach>
								      </c:otherwise>
							      </c:choose>
						      		
						      </c:if>
								
				    		</div>
				  	
						    
					<c:if test="${!empty rolePrueferExpiredList}">		    
						    <div class="col-md-1">
						      <h4>Prüfer-Rolle Abgelaufen:</h4>
						      
						       <hr>
						      		<c:choose>
								       <c:when test="${empty rolePrueferExpiredList}">
								      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
								      </c:when>
								      <c:otherwise>
								      	 <c:forEach var="rolePrueferExpired" items="${rolePrueferExpiredList}">
								        	<p class="text-info"><c:out value="${rolePrueferExpired.vorname}  ${rolePrueferExpired.nachname}"/></p>
								   		 </c:forEach>
								      </c:otherwise>
							      </c:choose>
								  
						    </div>
				    </c:if>
				    <c:if test="${!empty rolePrueferVertereterExpiredList}">
				    	
				    	 <div class="col-md-1">
					       <h4>Prüfer-Vertreter-Rolle Abgelaufen:</h4>
					       
					       <hr>
					       <c:choose>
							       <c:when test="${empty rolePrueferVertereterExpiredList}">
							      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
							      </c:when>
							      <c:otherwise>
							      	  <c:forEach var="rolePrueferVertereterExpired" items="${rolePrueferVertereterExpiredList}">
							        <p class="text-info"><c:out value="${rolePrueferVertereterExpired.vorname}  ${rolePrueferVertereterExpired.nachname}"/></p>
							   </c:forEach>
							      </c:otherwise>
						      </c:choose>
							
					    </div>
				    </c:if>
				    
				    <c:if test="${!empty roleLehrenderExpiredList}">
				    
						    <div class="col-md-1">
						       <h4>Lehrperson-Rolle Abgelaufen:</h4>
						       
						       <hr>
						       <c:choose>
								       <c:when test="${empty roleLehrenderExpiredList}">
								      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
								      </c:when>
								      <c:otherwise>
								      	  <c:forEach var="roleLehrenderExpired" items="${roleLehrenderExpiredList}">
								        <p class="text-info"><c:out value="${roleLehrenderExpired.vorname}  ${roleLehrenderExpired.nachname}"/></p>
								   </c:forEach>
								      </c:otherwise>
							   </c:choose>
								
						    </div>
				    
				    </c:if>
				    <c:if test="${!empty roleLehrenderVertereterExpiredList}">
				    
						    <div class="col-md-1">
						       <h4>Lehrperson-Rolle Abgelaufen:</h4>
						       
						       <hr>
						       <c:choose>
								       <c:when test="${empty roleLehrenderVertereterExpiredList}">
								      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
								      </c:when>
								      <c:otherwise>
								      	  <c:forEach var="roleLehrenderVertereterExpired" items="${roleLehrenderVertereterExpiredList}">
								        <p class="text-info"><c:out value="${roleLehrenderVertereterExpired.vorname}  ${roleLehrenderVertereterExpired.nachname}"/></p>
								   </c:forEach>
								      </c:otherwise>
							   </c:choose>
								
						    </div>
				    
				    </c:if>

				   
					<div class="col-md-1"></div>
					 <div class="col-md-1">
				       <h4>Alle-Accounts-Abgelaufen:</h4>
				       <br>
				       <hr>
				       <c:choose>
						       <c:when test="${empty accountsExpiredList}">
						      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
						      </c:when>
						      <c:otherwise>
						      	   <c:forEach var="accountsExpired" items="${accountsExpiredList}">
						        		<p class="text-primary"><c:out value="${accountsExpired.vorname}  ${accountsExpired.nachname} "/></p>
						   			</c:forEach>
						      </c:otherwise>
					      </c:choose>
						
					 </div>
					 <div class="col-md-1"></div>
					 <div class="col-md-1">
				       <h4>Ausnahme Fehler:</h4>
				       <br>
				       <hr>
				        <c:choose>
						       <c:when test="${empty exceptionList}">
						      		<p class="text-primary"><c:out value="Keine Personen vorhanden!"/></p>
						      </c:when>
						      <c:otherwise>
						      	   <c:forEach var="exception" items="${exceptionList}">
						        <p class="text-danger"><c:out value="${exception.vorname}  ${exception.nachname}"/></p>
						   </c:forEach>
						      </c:otherwise>
					      </c:choose>
						
					 </div>
				  </div>
					
				  
				  </div>
			  
			   		 
			  
			  
			</div>
		
	
		
           
</tusp:body>
</tusp:html>