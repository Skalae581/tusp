<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<tusp:html>
<tusp:head>
	<title><spring:message code="login.title" /></title>
</tusp:head>

<tusp:body>

	<spring:message code="login.username_placeholder"
		var="username_placeholder" />
	<spring:message code="login.password_placeholder"
		var="password_placeholder" />
	<spring:message code="login.submit" var="submit_label" />


	<div class="container login">
		<div class="row">
			<div class="col-md-12">
				<c:if test="${message != null}">
					<div class="alert alert-danger">
						<spring:message code="login.${message}" />
					</div>
				</c:if>
			</div>
		</div>
	
		<div class="row">
			<div class="col-md-offset-4 col-md-4 center">
				<a href="<c:url value="/shibstart"/>" class="btn btn-primary"> <spring:message
						code="menu.login_shib" />
				</a>
				<a href="<c:url value="/"/>" class="btn btn-primary"> <spring:message
						code="menu.start" />
				</a>
			</div>
		</div>
	
		<c:if test="${form}">
			<div class="row">
				<div class="col-md-offset-4 col-md-4">
					<c:url var="loginUrl" value="/login" />
					<form:form method="post" action="${loginUrl}" modelAttribute="user">
						<tusp:formRow label="login.username" fieldname="benutzername">
							<form:input path="benutzername" name="benutzername"
								class="form-control" placeholder="${username_placeholder}" />
						</tusp:formRow>
						<tusp:formRow label="login.password" fieldname="passwort">
							<form:input type="password" path="passwort" name="passwort"
								class="form-control" placeholder="${password_placeholder}" />
						</tusp:formRow>
						<tusp:formRow label="" fieldname="">
							<form:hidden path="id" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<input type="submit" value="${submit_label}" class="form-control" />
						</tusp:formRow>
					</form:form>
				</div>
			</div>
		</c:if>
	</div>

</tusp:body>

</tusp:html>



