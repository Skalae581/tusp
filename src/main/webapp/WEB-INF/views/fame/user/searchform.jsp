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
		    <c:url var="post_url" value="/fame/user/searchresult?workflowId=${workflowId}" />
		    <tusp:panel heading="${search}">
		        <form:form method="post" action="${post_url}" modelAttribute="vorgang" id="form">
			    	<tusp:form>
						<c:forEach items="${inputs}" var="input">
							<tusp:formRow label_static="true" label="${input.name}">
								<input class="form-control" name="input_${input.id}" value="" />
							</tusp:formRow>
						</c:forEach>
						<tusp:formRow label="">
			                <spring:message code="btn.search" var="submit_txt" />
	                    	<input type="submit" value="${submit_txt}" class="btn btn-default" />
	                   	</tusp:formRow>
					</tusp:form>
				</form:form>
			</tusp:panel>
		</div>
	</div>

</tusp:body>
</tusp:html>