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
<c:url var="get_url" value="/saeulen/saeulen/zustimmungInfo"/>


<div>
      <ul class="list-inline">

		<li>
			<a href="<c:url value="/saeulen/saeulen/" />" 
				class="btn btn-link"> <span><i class="glyphicon glyphicon-home">&nbsp;</i></span>Zurück zur Startseite</a>
		</li>
		
       </ul>
</div>	
<tusp:panel heading='Saeule-Protokoll für die <b>${protokollList[0].zielSaeule}</b>'>
	<form:form method="GET" action="${get_url}" modelAttribute="protokollList" id="aktualisierungsProtokollTable">
		<tusp:form>
				 
			<div class="row">

				  <div class="col-sm-12"> 
				  
				  	 
				  	<table id="example" class="table table-striped table-bordered" style="width:100%">
				 
				 			<thead>
				 			
				 			 <tr>
							        <th class="actions" scope="col">BEANTRAGT</th>
							        <th class="actions" scope="col">VON SÄULE</th>
							        <th class="actions" scope="col">UID</th>
							        <th class="actions" scope="col">DATUM</th>
							     	<th class="actions" scope="col">ABGESCHLOSSEN</th>
							     
							      	<th style="text-align: center" class="actions" class="actions">Details Ansehen</th>
							    </tr>	

				 			</thead>
							   
						    <c:forEach var="protokoll" items="${protokollList}">			    	
						        <tr>
						            <td style="text-align: left">${protokoll.user}</td>
						         	<td style="text-align: left">${protokoll.vonSaeule}</td>
						            <td style="text-align: left">${protokoll.uid}</td>
   							        <td style="text-align: left">${protokoll.datum}</td>
   							        <td>
    							        <c:choose>
    							        	<c:when test="${protokoll.abgeschlossen == false}">
    							        		<p style="color: red">Nicht Abgeschlossen</p>
    							        	</c:when>
    							        	<c:otherwise>
    							        		<p style="color: blue">Abgeschlossen</p>
    							        	</c:otherwise>
    							        </c:choose>
 										</td>
						            <td style="text-align: center">
									  	 <a class="btn btn-link zustimmInfo" title="Aktualisierungs / Zustimmungs-Details" 
									  			 href='<c:url value="/saeulen/saeulen/zustimmungInfo?aktualisierungUID=${protokoll.uid}&zielSaeuleId=${zielSaeuleId}"></c:url>'> 
            									<i class="glyphicon glyphicon-align-justify"> Details</i>
            							 </a>
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
