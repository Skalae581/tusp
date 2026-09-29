<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vervollst.aos.menu" /></title>
     <script type="text/javascript">
	  var txt = new Array();
	  txt['jqueryvalidate.required'] = "<spring:message code='jqueryvalidate.aos.required' />";
	</script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_start.js"></script>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
    
</tusp:head>
<tusp:body vollebreite="false">
    <tusp:panel heading="vervollst.aos.title">
		<br>
    	<c:url var="post_url" value="/bewVervollst/aos/ects" />
        <form:form method="post" action="${post_url}" modelAttribute="bewerber" id="bewerber" class="vorabForm">
            <tusp:form>       
				
				<tusp:formRow label="vervollst.aos.program" fieldname="stg">						
						<form:select id="stg${i.id}" class="form-control" path="stgId" required="required">
							<form:option value=""><spring:message code="vervollst.aos.please_select" /></form:option>
							<c:forEach var="i" items="${stg}" >
							<c:if test="${i.aktiv_vervollst == 1 and i.aos and i.standardkz != 'N'}">	
								<form:option value="${i.id}">${i.shorttext_de}</form:option> 
							</c:if>
							</c:forEach> 
						</form:select>
				</tusp:formRow>
				
				<!-- value of stgName is set via vorabcheck_start.js -->
				<form:hidden path="stgName"/>
				
                <spring:message code="vervollst.aos.btnNext" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                </tusp:formRow>
            </tusp:form>
        </form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>