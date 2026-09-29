<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title>${workflow.name} - ${status.name}</title>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/fame/gruppenaktionen.js"></script>
</tusp:head>
<tusp:body>

	<div class="row">
		<div class="col-md-2">
			<jsp:include page="include_menu.jsp" />
		</div>
		<div class="col-md-10">
		    <tusp:panel heading="${workflow.name} - ${status.name}" headingStatic="true">
				<jsp:include page="include_vorgaenge.jsp" />
			</tusp:panel>
		</div>
	</div>

	<div style="display: none" id="moveForm" class="btn-group" role="group">
		<div class="btn-group" role="group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<spring:message code="btn.move" /><span class="caret"></span>
			</button>
			<ul class="dropdown-menu">
				<c:forEach items="${stati}" var="status">
					<c:url value="/fame/user/move?to=${status.id}&amp;instanceId=${instanceId}&amp;" var="link" />
					<li><a data-href="${link}" href="#">${status.name}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>

</tusp:body>
</tusp:html>