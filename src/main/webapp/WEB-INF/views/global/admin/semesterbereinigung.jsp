<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title><spring:message code="global.semesterbereinigung.title" /></title>
    
	<script type="text/javascript" src="<c:url value="/resources/js/semesterbereinigung.js" />"></script>
</tusp:head>
<tusp:body>

    <tusp:panel heading="global.semesterbereinigung.title">
    	<div class="alert alert-warning" role="alert"><tusp:out key="global.semesterbereinigung.info" /></div>
	    <ul>
	    	<spring:message code="global.semesterbereinigung.bestaetigen" var="bestaetigen" />
	    	<c:forEach items="${semester}" var="sem">
	    		<li>
	    			${sem}<button class="do btn btn-default"><tusp:out key="global.semesterbereinigung.bereinigen" /></button>
	    			<form method="post" action="" style="display:none">
	    			 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	    				<input type="hidden" name="semester" value="${sem}" />
	    				<input type="submit" name="submit" value="${bestaetigen}" class="btn btn-danger"/>
	    				<button class="cancel btn btn-default"><tusp:out key="global.semesterbereinigung.abbrechen"></tusp:out></button>
	    			</form>
	    		</li>
	    	</c:forEach>
	    </ul>
    </tusp:panel>
</tusp:body>
</tusp:html>