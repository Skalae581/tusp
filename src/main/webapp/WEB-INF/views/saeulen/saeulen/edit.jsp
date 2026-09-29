
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

	<c:url var="post_url" value="/saeulen/saeulen/editPost" />
	<form:form method="post" action="${post_url}" modelAttribute="saeule">

		<div>
		       	<ul class="list-inline">
					<li>
						<a href="<c:url value="/saeulen/saeulen/" />"
						 	class="btn btn-link"> <span><i class="glyphicon glyphicon-arrow-left">&nbsp;</i></span>Zurück zur Startseite</a>
					</li>
					<li>
					  	<input type="submit" value="Speichern" class="btn btn-outline-primary" />		
					</li>
		          </ul>
		</div>

		<tusp:panel heading="Daten Bearbeiten">

                <div class="row">

	                <div class="col-sm-2"> </div>
	                    <div class="col-sm-8"> 
	                       <tusp:panel heading="Quelle Information">
	      
				                 <input type="hidden" name="id"  value="${saeule.id}" />
	       						 <label>Bezeichnung</label>
				                	<input type="text" name="bezeichnung"  value="${saeule.bezeichnung}"  placeholder=""  disabled="disabled"/>
					             <label>Hotfix Nr.</label>
				                	<input type="text" name="hisoneHotfixNr"  value="${saeule.hisoneHotfixNr}"  placeholder="" />		            
					            <label>Hotfix Datum</label>
				                	<input type="text" name="hisoneHotfixDatum"  value="${saeule.hisoneHotfixDatum}" placeholder="" />
					            <label>HIO DB Quelle</label>
				                	<input type="text" name="hiodbQuelle"  value="${saeule.hiodbQuelle}" placeholder="" />
					             <label>Hotfix Datum</label>
				                	<input type="text" name="hiodbDatum"  value="${saeule.hiodbDatum}" placeholder="" />
				     			    <label>Hotfix Datum</label>
				                	<input type="text" name="hiodbDatum"  value="${saeule.hiodbDatum}" placeholder="" />
	
							</tusp:panel>
	                  	</div>
	                  	<div class="col-sm-2"> </div>
                  	
                  </div>



		</tusp:panel>
	
	</form:form>
</tusp:body>
</tusp:html>
