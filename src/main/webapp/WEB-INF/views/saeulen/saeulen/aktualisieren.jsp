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
 	<title><spring:message code="saeulen.title" /></title>  
	    <script src="${pageContext.servletContext.contextPath}/resources/js/saeulen/saeulen.js"></script>
	    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/saeulen/saeulen.css" />
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/saeulen/saeulen/aktualisierungPost" />



	<form:form method="post" action="${post_url}" modelAttribute="saeule">

			<div>
		       	<ul class="list-inline">
					<li>
						<a href="<c:url value="/saeulen/saeulen/" />"
						 	class="btn btn-link"> <span><i class="glyphicon glyphicon-arrow-left">&nbsp;</i></span>Zurück zur Startseite</a>
					</li>
					<li>
					  	<input type="submit" value="Aktualisierung Speichern" class="btn btn-outline-info" />		
					</li>
		          </ul>
			</div>
			
			<tusp:panel heading="Saeule aktualisieren">
		
            <div class="row">
                
                    <div class="col-sm-4"> 
                       <tusp:panel heading="Quelle Information">
      						
			                	<!-- <input type="hidden" name="zielSaeuleId"  value="${saeule.id}" /> --><input type="hidden" name="zielSaeuleId"  value="${saeule.id}" />	
       						<label>Ziel</label>
			                	<input type="text" name="bezeichnung"  value="${saeule.bezeichnung}" disabled="disabled" placeholder="" />		            
				             <label>Hotfix Nr.</label>
			                	<input type="text" name="hisoneHotfixNr"  value="${saeule.hisoneHotfixNr}" disabled="disabled" placeholder="" />		            
				            <label>Hotfix Datum</label>
			                	<input type="text" name="hisoneHotfixDatum"  value="${saeule.hisoneHotfixDatum}" disabled="disabled" placeholder="" />
				            <label>HIO DB Quelle</label>
			                	<input type="text" name="hiodbQuelle"  value="${saeule.hiodbQuelle}" disabled="disabled" placeholder="" />
				             <label>DB Datum</label>
			                	<input type="text" name="hiodbDatum"  value="${saeule.hiodbDatum}" disabled="disabled" placeholder="" />
			       			       			
			       
						</tusp:panel>
                  	</div>
                  	
                  	
                  	
                  	<div class="col-sm-4"> 
                  		<tusp:panel heading="Aktualisierung">

							<label>Von wo soll aktualisiert werden?</label>
							<select id="quellSaeule" name="quellSaeule"
								class="form-control filterSelect">
								<option value="0">Auswahl der Quelle</option>
								<c:forEach items="${quellSaeule}" var="quellSaeuleItem">
									<option
										value="${quellSaeuleItem.id}">${quellSaeuleItem.bezeichnung}
									</option>
								</c:forEach>
							</select>
							
							<br>
							<br>

							<label>Datum</label>
							<input type="text" readonly name="datum" value="${datumHeute}" placeholder="" />
							
							
							<label>Benutzer</label>
							<input type="text" name="saeule_user_id" value="" placeholder="" />
							
							<label>Begründung</label>
							<textarea name="begruendung" cols="50" rows="10"></textarea>
		               
		               </tusp:panel>
                  	</div>
                  	
                  	<div class="col-sm-4"> 
                  		<tusp:panel heading="Wer soll zustimmen?">
                  			<label>Personen</label>
							<c:forEach items="${users}" var="user">
								
								<ul class="userList">
									<li>
										<input type="checkbox" name="zustimmung_user"
										 value="${user.id }">
										 &nbsp; <span> ${user.name}  ${user.nachname} [ ${user.benutzername} ] </span>
									</li>
								
								</ul>
								
								
						
							</c:forEach>
		                   
		               
		               </tusp:panel>
                  	</div>
 	
                  </div>		
				</tusp:panel>
			</form:form>
		
	
</tusp:body>
</tusp:html>
