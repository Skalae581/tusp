<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<tusp:html>
	<tusp:head>
	    <title>${title}</title>
	</tusp:head>

	<tusp:body>
	
        <h1>${title}</h1>
        <c:choose>
        	<c:when test="${message.getClass().name.contains('List')}">
        		<c:forEach items="${message}" var="msg">
        			<p>${msg}</p>
        		</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<p>${message}</p>
        	</c:otherwise>
        </c:choose>
        
        <c:if test="${errorHandler != null}">
        	<dl>
        		<dt><spring:message code="error.id"/></dt>
        		<dd>${errorHandler.errorId}</dd>
        		<dt><spring:message code="error.server"/></dt>
        		<dd>${errorHandler.server}</dd>
        		<dt><spring:message code="error.date"/></dt>
        		<dd>${errorHandler.time}</dd>
        	</dl>
        	
        	<a href="${errorHandler.mailLink}" class="btn btn-default"><spring:message code="error.send" /></a>
        	
        </c:if>
        
		<c:if test="${back_url}">
	    	<a href="<c:url value="${back_url}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
	    </c:if>
	    <a href="<c:url value="/" />" class="btn btn-default"><spring:message code="btn.start" /></a>
	    
	</tusp:body>
 	

    
</tusp:html>   