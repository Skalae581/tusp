<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title><spring:message code="fame.user.searchresult_title" /></title>
    
</tusp:head>
<tusp:body>

	<div class="row">
		<div class="col-md-2">
			<jsp:include page="include_menu.jsp" />
		</div>
		<div class="col-md-10">
			<spring:message var="search" code="fame.user.search"/>
		    <tusp:panel heading="${search}">
				<jsp:include page="include_vorgaenge.jsp" />
			</tusp:panel>
		</div>
	</div>

</tusp:body>
</tusp:html>