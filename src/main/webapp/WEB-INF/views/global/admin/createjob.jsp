<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title><spring:message code="global.createjob.title" /></title>
    
</tusp:head>
<tusp:body>

   	<spring:message code="global.createjob.bestaetigen" var="bestaetigen" />
    <tusp:panel heading="global.createjob.title">
	    <div class="container">
	    	<c:forEach items="${classes}" var="clazz">
	    		<div class="row">
	    			<form method="post" action="">
		    			<div class="col-md-4">
		    				${clazz.key}
		    			</div>
		    			<div class="col-md-6"> 
			    			 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    				<input type="hidden" name=clazz value="${clazz.key}" class="form-control"/>
			    				<input type="text" name="name" value="${clazz.value}" class="form-control"/>
		    			</div>
		    			<div class="col-md-2">
			    				<input type="submit" name="submit" value="${bestaetigen}" class="btn btn-default"/>
		    			</div>
	    			</form>
	    		</div>
	    	</c:forEach>
	    </div>
    </tusp:panel>
</tusp:body>
</tusp:html>