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
 	<title><spring:message code="sauelen.title" /></title>  
	    <script src="${pageContext.servletContext.contextPath}/resources/js/saeulen/saeulen.js"></script>
	    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/saeulen/saeulen.css" />
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/saeulen/saeulen/aktualisieren"/>
<tusp:panel heading="Saeulen">
	<form:form method="POST" action="${post_url}" modelAttribute="saeule" id="saeuleForm">
		<tusp:form>
		  
			<div class="row">

				  <div class="col-sm-12"> 
				  
				  	<table id="example" class="table table-striped table-bordered" style="width:100%">
				 
				 			<thead>
				 			
				 			 <tr>
				 			  		<th class="actions">Bearbeiten</th> 
							        <th class="actions" scope="col">Säule Name</th>
							        <th class="actions" scope="col">SuperX Version</th>
							        <th class="actions" scope="col">HIO Datum</th>
							        <th class="actions" scope="col">HIO Hotfix Nr.</th>
							        <th class="actions" scope="col">HIO Hotfix Datum</th>
							        <th class="actions" scope="col">DB-Quelle</th>
							        <th class="actions" scope="col">DB-Quelle-Datum</th>
    							    <th class="actions" scope="col">SOSPOS Version</th>
    							    <th class="actions" scope="col">SOSPOS Datum</th>
							      	<th class="actions" class="actions">Aktualisieren / Reservieren / Konfig-Transfer</th>
							    </tr>	

				 			</thead>
							   
							    <c:forEach var="saeule" items="${saeulen}">
							        <tr>
							        
								        <td>
									        <a class="btn btn-warrning bearbeiten" title="bearbeiten" href="<c:url value="/saeulen/saeulen/edit?id=${saeule.id}" />">
			            									<span class="glyphicon glyphicon-edit"></span>
			            					</a>
		            					</td>
							            <td style="text-align: left">${saeule.bezeichnung}</td>
							            <td style="text-align: left">${saeule.hisoneSuperxVersion}</td>
							            <td style="text-align: left">${saeule.hisoneDatum}</td>
							            <td style="text-align: left">${saeule.hisoneHotfixNr}</td>
							            <td style="text-align: left">${saeule.hisoneHotfixDatum}</td>
							            <td style="text-align: left">${saeule.hiodbQuelle}</td>
							            <td style="text-align: left">${saeule.hiodbDatum}</td>
							            <td style="text-align: left">${saeule.sosposVersion}</td>
							        	<td style="text-align: left">${saeule.sosposDatum}</td>
							            
							            <td >
							            												
										 <div class="row">
              			
               									<div class="col-sm-12">
               									
	               									<ul class="list-inline">
	               																
	               										<li>
			               									<ul class="list-inline">
			               										<li>
															  		<a class="btn btn-info aktualisieren" title="Säule aktualisieren" href="<c:url value="/saeulen/saeulen/aktualisieren?id=${saeule.id}" />">
					            										<i class="glyphicon glyphicon-font"></i>
					            								 	</a>
						            							</li>	
						            							
						            							<li>
															  		<a class="btn btn-info aktualisierenProtokoll" title="Aktualisierungs-Protokoll" href="<c:url value="/saeulen/saeulen/aktualisierungsProtokoll?id=${saeule.id}" />">
					            										<i class="glyphicon glyphicon-option-vertical"></i>
					            								 	</a>
						            							</li>	
			               									</ul>
	               					
	               										</li>
	               												
													  	<li> 
													  		 <a class="btn btn-primary konfTransfer" title="Konfigurations-Transfer" href="<c:url value="/saeulen/saeulen/transfer?id=${saeule.id}" />">
				            									<i class="glyphicon glyphicon-transfer"></i>
				            							 	 </a>
				            							</li>
					            							
														  
													</ul>
													<ul class="list-inline">
													
														<li> 
														  	 <a class="btn btn-warning reservieren" title="Säule reservieren" href="<c:url value="/saeulen/saeulen/reservieren" />">
					            									<i class="glyphicon glyphicon-share"></i>
					            							 </a>
						            					</li>
													</ul>
												
												
               									
               									</div>
              									
              								</div>
											 	            								 
							            
							            </td>
							        </tr>
							    </c:forEach>
							</table>
				  
				  </div>
				 	
				 
 	   </div>
 	
 	 
 	
   </tusp:form>
</form:form>
</tusp:panel>
</tusp:body>
</tusp:html>
