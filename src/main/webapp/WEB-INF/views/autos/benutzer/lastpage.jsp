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
 	<title><spring:message code="autos.title" /></title>  
	    <script src="${pageContext.servletContext.contextPath}/resources/js/autos/autos.js"></script>
	    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/autos/autos.css" />
	   
</tusp:head>
<tusp:body>
<c:url var="post_url" value="/autos/" />
	<form:form method="GET" action="${post_url}">
		<tusp:form>
			    
			   <p>Erfolg!</p>
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="Zurück" class="btn btn-default" />
                </tusp:formRow>
			    
			    
		</tusp:form>
	</form:form> 
	
</tusp:body>
</tusp:html>