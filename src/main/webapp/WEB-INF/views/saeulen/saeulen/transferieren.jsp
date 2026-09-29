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




	<form:form method="post" action="${post_url}" modelAttribute="Konfigurationseigenschaft">
		
		<div>
		       	<ul class="list-inline">
					<li>
						<a href="<c:url value="/saeulen/saeulen/" />"
						 	class="btn btn-link"> <span><i class="glyphicon glyphicon-arrow-left">&nbsp;</i></span>Zurück zur Startseite</a>
					</li>
					<li>
					  	<input type="submit" value="Transfer Speichern" class="btn btn-outline-info" />		
					</li>
		          </ul>
			</div>
			<tusp:panel heading="Saeule transferieren">
			
		
			 <tusp:form>
                <div class="row">
              
                
                    <div class="col-sm-6" style="padding:5px;margin-bottom:5px;"> 
                      <tusp:panel   heading="Transferieren">
                       
                        	 <!-- 	<tusp:panel heading="Mit Quelle Auswahl"> -->
	                       			<c:forEach items="${konfeingenschaftMitQuelle}" var="konfeingenschaftItem">
	                       				<ul id="" class="grundList">
											<li>
												<input type="checkbox" name="konfeigenschaft" value="${konfeingenschaftItem.id}" class="quelleChecbox" />
												<label>${konfeingenschaftItem.bezeichnung}</label>
											</li>
								
										</ul>
									</c:forEach>
									
									<!-- Drop Down Quelle  -->
										<select id="konfquelle" name="konfigurationsquelle" class="form-control filterSelect">
											
											<c:forEach items="${konfigurationsquellen}" var="konfigurationsquellenItem">
											
											
												<c:if test="${konfigurationsquellenItem.bezeichnung == 'TUHH-dev'}">
													<option selected
														value="${konfigurationsquellenItem.id}">${konfigurationsquellenItem.bezeichnung}
													</option>
												</c:if>
												<option
													value="${konfigurationsquellenItem.id}">${konfigurationsquellenItem.bezeichnung}
												</option>
											</c:forEach>
										</select>
									
									
							<!-- </tusp:panel> -->	
								<br>
								
                       			<c:forEach items="${konfeingenschaft}" var="konfeingenschaftItem">
									<ul id="" class="grundList">
										<li>
									
											<input type="checkbox" name="restKonfeigenschaft" id="${konfeingenschaftItem.bezeichnung}" value="${konfeingenschaftItem.id}" />
											<label>${konfeingenschaftItem.bezeichnung}</label>
										</li>
								
									</ul>
								</c:forEach>
  

						</tusp:panel>
                  	</div>
                  	
                  	
                  	
                  	<div class="col-sm-3"> 
                  		<tusp:panel heading="Konfigurationstransfer">
							<input type="hidden" name="zielSaeuleId"  value="${saeuleObj.id}" />	
							
							<label>Datum</label>
							<input type="text" name="datum" readonly value="${datumHeute}" placeholder="" />
							
							<label>Benutzer</label>
							<input type="text" name="saeule_user_id" value="" placeholder="" />
							
							<label>Begründung</label>
							<textarea name="begruendung" cols="50" rows="10"></textarea>
		               		
		               		
		               		
		               </tusp:panel>
                  	</div>
                  	
                  	<div class="col-sm-3"> 
                  		<tusp:panel heading="Wer soll zustimmen?">
                  			
							<c:forEach items="${users}" var="user">
								
								<ul class="userList">
									<li>
										<input type="checkbox" name="zustimmung_user"
										 value="${user.id}">
										  <span> ${user.name}  ${user.nachname} [ ${user.benutzername} ] </span>
									</li>
								
								</ul>
								
								
						
							</c:forEach>
		                   
		               
		               </tusp:panel>
                  	</div>
                  	
                
                  	
                  	
                  </div>

                

            </tusp:form>
		
		
	</tusp:panel>	
           
    </form:form>

	

	
</tusp:body>
</tusp:html>