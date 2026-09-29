<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tusp:html>
<tusp:head>
	<title><spring:message code="appname"/></title>
</tusp:head>
<tusp:body>

    <c:url var="post_url" value="/global/admin/config/edit/?prefix=${prefix}&id=${config.id}" />
    <tusp:panel heading="global.admin.config.form_heading">
    	<h1>${config.key}</h1>
    	<p><tusp:out key="${config.key}" /></p>
    
        <form:form method="post" action="${post_url}" id="form">
            <tusp:form>
                <tusp:formRow label="global.admin.config.key">
                	<input type="text" value="${config.key}" class="form-control" <c:if test="${config.id > 0}">disabled="disabled"</c:if> name="key"/>
                </tusp:formRow>
                <tusp:formRow label="global.admin.config.value" fieldname="value">
                	<c:choose>
                		<c:when test="${isPassword}">
                			<c:set var="type" value="password" />
                		</c:when>
                		<c:otherwise>
                			<c:set var="type" value="text" />
                		</c:otherwise>
                	</c:choose>
                	
                	<input type="${type}" value="${config.value}" class="form-control" name="value" autofocus/>
                </tusp:formRow>
                <tusp:formRow label="" fieldname="">
                	<spring:message code="btn.save" var="search_txt"/>
                    <input type="submit" value="${search_txt}" class="btn btn-default" />
                    <a href="<c:url value="/global/admin/config/list?prefix=${prefix}" />"  class="btn btn-default" >
						<tusp:out key="btn.back"/>
					</a>
				</tusp:formRow>
            </tusp:form>
            <a href="<c:url value="/global/admin/config/delete?prefix=${prefix}&id=${config.id}" />" class="btn btn-danger"><tusp:out key="btn.delete"/></a>
        </form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>