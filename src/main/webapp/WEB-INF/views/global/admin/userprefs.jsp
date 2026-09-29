<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tusp:html>
<tusp:head>
	<title><spring:message code="global.admin.userlookup_form"/></title>
</tusp:head>
<tusp:body>

    <c:url var="post_url" value="/global/admin/userprefs/" />
    
    <tusp:panel heading="global.admin.userlookup_form">
        <form:form method="get" action="${post_url}" id="form">
            <tusp:form>
                <tusp:formRow label="global.admin.username" fieldname="username">
                	<input type="text" value="${username}" class="form-control form-controll-narrow" name="username" autofocus/>
                	<spring:message code="btn.search" var="search_txt"/>
                    <input type="submit" value="${search_txt}" class="btn btn-default" />
				</tusp:formRow>
                
            </tusp:form>
        </form:form>
    </tusp:panel>

	<c:if test="${not empty username}">
	    <tusp:panel heading="global.admin.userlookup_result">
			<h2><spring:message code="global.admin.user"/> <c:out value="${username}" /></h2>
			<ul>
				<li><spring:message code="global.admin.accoutid"/>: <c:out value="${account.id}" /></li>
				<li><spring:message code="global.admin.personid"/>: <c:out value="${account.personId}" /></li>
			</ul>
	    </tusp:panel>
	    
	    <tusp:panel heading="global.admin.userprefs">
			<tusp:table data="${userPrefs}" sortable="true">
				<tusp:column heading="global.admin.config.key">
					<a 
						href="${post_url}delete?id=${dataItem.id}&username=${username}" 
						class="btn btn-default" 
						title="<tusp:out key="btn.delete" />">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</a>
				</tusp:column>
				<tusp:column heading="global.admin.config.key">
					${dataItem.key}
				</tusp:column>
				<tusp:column heading="global.admin.config.value">
					<form:form method="post" action="${post_url}save?username=${username}">
						<input type="hidden" name="id" value="${dataItem.id}" />
						<input type="text" name="value" value="${dataItem.value}"  class="form-control form-controll-narrow" />
						<input type="submit" value="<tusp:out key="btn.save" />" class="form-control form-controll-narrow" />
					</form:form>
				</tusp:column>
			</tusp:table>
			<form:form method="post" action="${post_url}save?username=${username}" id="roles">
				<input name="id" value="0" type="hidden">
				<input name="key" value="" class="form-control form-controll-narrow" type="text" placeholder="key">
				<input name="value" value="" class="form-control form-controll-narrow" type="text" placeholder="value">
				<input value="<tusp:out key="btn.add" />" class="form-control form-controll-narrow" type="submit">
			</form:form>
			<span>Keys: ${userPrefsPrefix}.displayName, ${userPrefsPrefix}.email</span>
	    </tusp:panel>
	
	    <tusp:panel heading="global.admin.userroles">
			<tusp:table data="${configRoles}" sortable="true">
				<tusp:column heading="global.admin.config.key">
					<a 
						href="${post_url}delete?id=${dataItem.id}&username=${username}" 
						class="btn btn-default" 
						title="<tusp:out key="btn.delete" />">
						<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
					</a>
				</tusp:column>
				<tusp:column heading="global.admin.config.key">
					${dataItem.key}
				</tusp:column>
				<tusp:column heading="global.admin.config.value">
					<form:form method="post" action="${post_url}save?username=${username}">
						<input type="hidden" name="id" value="${dataItem.id}" />
						<input type="text" name="value" value="${dataItem.value}"  class="form-control form-controll-narrow" />
						<input type="submit" value="<tusp:out key="btn.save" />" class="form-control form-controll-narrow" />
					</form:form>
				</tusp:column>
			</tusp:table>
			<form:form method="post" action="${post_url}save?username=${username}" id="roles">
				<input name="id" value="0" type="hidden">
				<input name="key" value="${newRolesKey}" class="form-control form-controll-narrow" type="text" placeholder="key">
				<input name="value" value="" class="form-control form-controll-narrow" type="text" placeholder="value">
				<input value="<tusp:out key="btn.add" />" class="form-control form-controll-narrow" type="submit">
			</form:form>
	    </tusp:panel>
	
	</c:if>
</tusp:body>
</tusp:html>