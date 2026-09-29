
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

<tusp:panel heading="Aktualisierung bestellt">

			
                <div class="row">
                
                	<div class="col-sm-12">
                	
                	<div class="col-sm-4"> </div>
                	
                	
	                	<div class="col-sm-4"> 
		                	<div class="row">
				                <div class="col-sm-12"> 
				                      					
				      						<p>
				      							Die Aktualisierung ist in der Bearbeitung!
				      							Die Emails mit den Zustimmungslinks wurden verschickt!
				      						</p>
										
				                  	</div>
			                  	</div>
			                  
	                    </div>
	                    
	                    <div class="col-sm-4"> </div>
	                  
	               </div>	
                </div>


					<div class="col-sm-12">
						
						<div class="col-sm-4"> </div>
                		
	                	<div class="col-sm-4"> 
		                	<div class="row">
				                <div class="col-sm-12"> 
				                       <a href="<c:url value="/saeulen/saeulen/" />" class="btn btn-default">OK</a>
				                  	</div>
			                  	</div>
			                  
	                    </div>
	                    <div class="col-sm-4"> </div>
	                   
	                    
	                  
	               </div>

	</tusp:panel>
	
	
</tusp:body>
</tusp:html>
