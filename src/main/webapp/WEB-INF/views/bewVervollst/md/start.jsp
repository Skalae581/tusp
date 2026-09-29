<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vervollst.browser.title" /></title>
    <script type="text/javascript">
	  var txt = new Array();
	  txt['jqueryvalidate.required'] = "<spring:message code='jqueryvalidate.required' />";
	  txt['jqueryvalidate.number'] = "<spring:message code='jqueryvalidate.number' />";
	  txt['jqueryvalidate.minlength2'] = "<spring:message code='jqueryvalidate.minlength2' />";
	</script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_start.js"></script>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
    
</tusp:head>
<tusp:body vollebreite="false">
    <tusp:panel heading="vervollst.title">
		<br>
    	<c:url var="post_url" value="/bewVervollst/md/ects" />
        <form:form method="post" action="${post_url}" modelAttribute="bewerber" id="bewerber" class="vorabForm">
            <tusp:form>
                         
                <c:if test="${redirect == 'codeNull'}">
	                <p>
	            		<spring:message code="vorabcheck.vervollst.codeHinweis" />
	        		</p>
        		</c:if>
        		<c:if test="${redirect == 'stgNull'}">
        			<p>
	            		<spring:message code="vorabcheck.vervollst.stgHinweis" />
	        		</p>
        		</c:if>
                
				
				<tusp:formRow label="vorabcheck.studiengang" fieldname="stg">						
						<form:select id="stg${i.id}" class="form-control" path="stgId" required="required">
							<form:option value=""><spring:message code="vorabcheck.please_select" /></form:option>
							<c:forEach var="i" items="${stg}" >
							<c:if test="${i.aktiv_vervollst == 1 and not i.aos and i.standardkz != 'N'}">	
								<form:option value="${i.id}">${i.shorttext_de}</form:option> 
							</c:if>
							</c:forEach> 
						</form:select>
				</tusp:formRow>
				
				<!-- value of stgName is set via vorabcheck_start.js -->
				<form:hidden path="stgName"/>
				
                <spring:message code="btn.next" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                </tusp:formRow>
            </tusp:form>
        </form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>
