<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title><spring:message code="global.job.title" /> ${job.name}</title>
    <meta http-equiv="refresh" content="30">
</tusp:head>
<tusp:body>
    <tusp:panel heading="${job.name}" headingStatic="true"> 
	    <div class="container">
    		<div class="row">
    			<div class="col-md-4">
   					<spring:message code="global.job.id"/>
    			</div>
    			<div class="col-md-8"> 
					${job.id} 
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-4">
   					<spring:message code="global.job.name"/>
    			</div>
    			<div class="col-md-8"> 
					${job.name} 
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-4">
   					<spring:message code="global.job.bean"/>
    			</div>
    			<div class="col-md-8"> 
					${job.className} 
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-4">
   					<spring:message code="global.job.start"/>
    			</div>
    			<div class="col-md-8"> 
					${job.start} 
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-4">
   					<spring:message code="global.job.ende"/>
    			</div>
    			<div class="col-md-8"> 
					${job.ende} 
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-4">
   					<spring:message code="global.job.status"/>
    			</div>
    			<div class="col-md-8"> 
					<c:choose>
						<c:when test="${job.status == 'Pending'}">
							<span class="label label-info">${job.status}</span>
						</c:when>
						<c:when test="${job.status == 'Done'}">
							<span class="label label-success">${job.status}</span>
						</c:when>
						<c:when test="${job.status == 'Running'}">
							<span class="label label-warning">${job.status}</span>
						</c:when>
						<c:otherwise>
							<span class="label label-danger">${job.status}</span>
						</c:otherwise>
					</c:choose>
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-4">
   					<spring:message code="global.job.server"/>
    			</div>
    			<div class="col-md-8"> 
					${job.server} 
    			</div>
    		</div>
    		<div class="row">
    			<div class="col-md-12"> 
    				<pre> 
    					${job.log} 
    				</pre>
    			</div>
    		</div>
	    </div>
    </tusp:panel>
    <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/global/admin/jobs"><spring:message code="admin.jobs" /></a>
</tusp:body>
</tusp:html>