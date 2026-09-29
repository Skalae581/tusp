<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

	
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
	<title><spring:message code="psv.import.name" /></title>
	<script src="${pageContext.servletContext.contextPath}/resources/js/psv/psv.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/moment.min.js"></script>
    <link href="${pageContext.servletContext.contextPath}/resources/css/psv/psv.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/font-awesome-4.7.0/css/font-awesome.min.css">
	
</tusp:head>
<tusp:body>
	
	
	
	<h1><spring:message code="appname"/></h1>
	
   <%--   <c:url var="post_url" value="/psv/" /> --%>
	
	<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" id="ldapTab" href="#ldap">LDAP-Import</a></li>
    <li><a data-toggle="tab" href="#unihh" id="unihhTab">Uni-HH Import</a></li>
    <li><a data-toggle="tab" href="#mobon" id="mobonTab">Mobility-Online Import</a></li>
    <li><a data-toggle="tab" href="#sync" id="syncTab">Sync</a></li>
  </ul>

  <div class="tab-content">
  
    <div id="ldap" class="tab-pane fade in active">
      <h3>LDAP-Import</h3>
      
      
      <tusp:panel heading="Import" >
    

	 			<div class="col col-lg-4  form-control _ldapStatisticsOutput " id="ldapStatisticsOutput">
	 			
	 			
	 			</div> 
		 			    	
		   

                <spring:message code="btn.import" var="import_txt" />
                <tusp:formRow label="" fieldname="">

                    <input type="button" value="Start LDAP Import" class="btn btn-default" id="ldapImportBtnStart"  
                               <c:if test="${ldapRunning == true}">
                               	 	disabled 	                             
                                </c:if>  
          
                     /> 
                      <input type="button" value="Stop LDAP Import" class="btn btn-default" id="ldapImportBtnStop"  
                               <c:if test="${ldapRunning == false}">
                               	 	disabled 	                             
                                </c:if> 
          
                     /> 
                     <!-- Animation -->
                       
                       
		                       <span id ="ldap_animation">
		                       		<i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
										<span class="">Daten werden importiert...</span>                                  
		                       </span>

                    <div class="form-group">		                   		
                    	<div class="form-control _ldapConsole" id="ldapConsole"></div>                 		
					</div>

                    <input type="hidden" name="thread_ldap" id="thread_ldap" value="${ldapRunning}">   
                 
                </tusp:formRow> 
          
                
    </tusp:panel>
      
      
      
    </div>
    <div id="unihh" class="tab-pane fade">
      <h3>Uni-HH Import</h3>
     <tusp:panel heading="Import" >
    

	 			<div class="col col-lg-4  form-control _unihhStatisticsOutput " id="unihhStatisticsOutput">
	 			
	 			
	 			</div> 
		 			    	
		   

                <spring:message code="btn.import" var="import_txt" />
                <tusp:formRow label="" fieldname="">

                    <input type="button" value="Start UNI-HH Import" class="btn btn-default" id="unihhImportBtnStart"  
                               <c:if test="${unihhRunning == true}">
                               	 	disabled 	                             
                                </c:if>  
          
                     /> 
                      <input type="button" value="Stop UNI-HH Import" class="btn btn-default" id="unihhImportBtnStop"  
                               <c:if test="${unihhRunning == false}">
                               	 	disabled 	                             
                                </c:if> 
          
                     /> 
                     <!-- Animation -->
                       
                       
		                       <span id ="unihh_animation">
	                       
                              	  <i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
									<span class="">Daten werden importiert...</span>                       
	                                
		                       </span>

                    <div class="form-group">		                   		
                    	<div class="form-control _unihhConsole" id="unihhConsole"></div>                 		
					</div>

                    <input type="hidden" name="thread_unihh" id="thread_unihh" value="${unihhRunning}">   
                 
                </tusp:formRow> 
          
                
    </tusp:panel>
    </div>
    <div id="mobon" class="tab-pane fade">
      <h3>Mobility-Online Import</h3>
      <tusp:panel heading="Import" >
    

	 			<div class="col col-lg-4  form-control _mobonStatisticsOutput " id="mobonStatisticsOutput">
	 			
	 			
	 			</div> 
		 			    	
		   

                <spring:message code="btn.import" var="import_txt" />
                <tusp:formRow label="" fieldname="">

                    <input type="button" value="Start Mobility-Online Import" class="btn btn-default" id="mobonImportBtnStart"  
                               <c:if test="${running == true}">
                               	 	disabled 	                             
                                </c:if>  
          
                     /> 
                      <input type="button" value="Stop Mobility-Online Import" class="btn btn-default" id="mobonImportBtnStop"  
                               <c:if test="${running == false}">
                               	 	disabled 	                             
                                </c:if> 
          
                     /> 
                     <!-- Animation -->
                       
                       
		                       <span id ="mobon_animation">
		                       		<i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
										<span class="">Daten werden importiert...</span>                                  
		                       </span>

                    <div class="form-group">		                   		
                    	<div class="form-control _mobonConsole" id="mobonConsole"></div>                 		
					</div>

                    <input type="hidden" name="thread_mobon" id="thread_mobon" value="${running}">   
                 
                </tusp:formRow> 
          
                
    </tusp:panel>
    </div>
    <div id="sync" class="tab-pane fade">
      <h3>Sync</h3>
     <tusp:panel heading="Import" >
    

	 			<div class="col col-lg-4  form-control _statisticsOutput " id="statisticsOutput">
	 			
	 			
	 			</div> 
		 			    	
		   

                <spring:message code="btn.import" var="import_txt" />
                <tusp:formRow label="" fieldname="">

                    <input type="button" value="Start Sync" class="btn btn-default" id="syncImportBtnStart"  
                               <c:if test="${running == true}">
                               	 	disabled 	                             
                                </c:if>  
          
                     /> 
                      <input type="button" value="Stop Sync" class="btn btn-default" id="syncImportBtnStop"  
                               <c:if test="${running == false}">
                               	 	disabled 	                             
                                </c:if> 
          
                     /> 
                     <!-- Animation -->
                       
                       
		                       <span id ="sync_animation">
		                       		<i class="fa fa-cog fa-spin fa-2x fa-fw"></i>
										<span class="">Daten werden importiert...</span>                                  
		                       </span>

                    <div class="form-group">		                   		
                    	<div class="form-control _syncConsole" id="syncConsole"></div>                 		
					</div>

                    <input type="hidden" name="thread_sync" id="thread_sync" value="${running}">   
                 
                </tusp:formRow> 
          
                
    </tusp:panel>
    </div>
  </div>

	
	
	
	
	
	
	
      
      
      
    
	
	
	
	
	
</tusp:body>
</tusp:html>