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
<c:url var="post_url" value="/saeulen/saeulen/transferierungPost" />

<tusp:panel heading="Konfigurationstransfer">


<!-- ModelAttribute? -->
	<form:form method="post" action="${post_url}" modelAttribute="saeule">
		
		
		
			 <tusp:form>
                <div class="row">
                
                    <div class="col-sm-4"> 
                       <tusp:panel heading="Quelle Information">
      						
			                	<input type="hidden" name="zielSaeuleId"  value="${saeule.id}" />	
       						<label>Quelle:</label>
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

							<label>Von Wo Soll Aktualisiert Werden?</label>
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
							<input type="text" name="datum" value="" placeholder="" />
							
							<label>Benutzer</label>
							<input type="text" name="saeule_user_id" value="" placeholder="" />
							
							<label>Begründung</label>
							<textarea name="begruendung" cols="50" rows="10"></textarea>
		               
		               </tusp:panel>
                  	</div>
                  	
                  	<div class="col-sm-4"> 
                  		<tusp:panel heading="Wer soll zustimmen?">
                  			
							<c:forEach items="${users}" var="user">
								
								<ul>
									<li>
										<input type="checkbox" name="zustimmung_user"
										 value="${user.id }">
										  <span> ${user.name}  ${user.nachname} [ ${user.benutzername} ] </span>
									</li>
								
								</ul>
								
								
						
							</c:forEach>
		                   
		               
		               </tusp:panel>
                  	</div>
                  	
                
                  	
                  	
                  </div>

                <spring:message code="precheck.btn.submit.edit" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                
                    <a href="<c:url value="/saeulen/saeulen" />" class="btn btn-default">Zurück zur Startseite</a>
                   	<input type="submit" value="Aktualisierung Speichern" class="btn btn-default" />
                 
                </tusp:formRow>


            </tusp:form>
		
		
		
           
    </form:form>

	

	</tusp:panel>
</tusp:body>
</tusp:html>
