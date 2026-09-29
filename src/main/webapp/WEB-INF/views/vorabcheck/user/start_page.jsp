<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vorabcheck.menu" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
</tusp:head>
<tusp:body vollebreite="false">
    <tusp:panel heading="vorabcheck.start">
        <p>
            <spring:message code="vorabcheck.einleitung" />
        </p>
		<br>
		
    	<c:url var="post_url" value="/vorabcheck/info_page" />
        <form:form method="post" action="${post_url}" modelAttribute="vorabInteressent" id="vorabInteressent">
            <tusp:form>				
				<tusp:formRow label="vorabcheck.studiengang" fieldname="stg">					
						<form:select id="stg${i.id}" class="form-control" path="stgId" required="required" onchange="this.form.submit();">
							<form:option value=""><spring:message code="vorabcheck.please_select" /></form:option>
							<c:forEach var="i" items="${stg}" >
							<c:if test="${i.aktiv_vorab == 1}">	
								<form:option value="${i.id}">${i.shorttext_de}</form:option> 
							</c:if>
							</c:forEach> 
						</form:select>
				
				</tusp:formRow>
				
                <spring:message code="btn.next" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                </tusp:formRow>
            </tusp:form>
        </form:form>

    </tusp:panel>

</tusp:body>
</tusp:html>