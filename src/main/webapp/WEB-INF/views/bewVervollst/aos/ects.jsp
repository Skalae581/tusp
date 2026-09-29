<%@page contentType="text/html;encoding=UTF-8;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vervollst.aos.menu" /> ${bewerber.stgName}</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
    <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_ects.js"></script>
</tusp:head>
<tusp:body>
    <tusp:panel heading="vervollst.aos.title">
	    <c:url var="post_url" value="/bewVervollst/aos/downloadPdf" />
		<form:form method="post" action="${post_url}" modelAttribute="ectsForm" id="ects">
			<table class="ectsPunkte">
				<tr><td><spring:message code="vervollst.aos.program" />: </td> <td>${bewerber.stgName}</td></tr>
			</table>
			<br>
	    	<c:forEach var="info" items="${infotext}">
	    	${info}<br>
	    	</c:forEach>
	    	<br>
			<table class="ectsTable">
				<tr class="ectsHeader">
				<td><spring:message code="vervollst.aos.field" /></td>
				<td><spring:message code="vervollst.aos.requirements" /></td>
				<td><spring:message code="vervollst.aos.requiredUnits" /></td>
				<td><spring:message code="vervollst.aos.unitsAchieved" /></td>
				<td><spring:message code="vervollst.aos.explanation" /></td>
				</tr>

				<c:set var="x" value="0" scope="page"/>
				<c:forEach var="i" items="${bereich}">
					<c:if test="${i.stg_id==bewerber.stgId}">
						<tr class="ectsBereich" id="ectsBereich${i.id}"> 
							<td id="${i.id}">${i.name}</td>
							<td></td>
							<form:input path="ects[${x}].fachId" type="hidden" value="" />
							<form:input path="ects[${x}].bereichId" type="hidden" value="${i.id}" />
							<td class="ectsPunkte"><form:input path="ects[${x}].bereichPunkte" name="bereichPunkte_${i.id}" type="hidden" value="${i.punkte}" />${i.punkte}</td>
							<td class="ectsPunkte" size="2"><form:input path="ects[${x}].gesamtPunkte" name="bereichSumme_${i.id}" id="bereichSumme_${i.id}" readonly="true" value="" /></td> 
							<td class="ectsPunkte" size="2"><input name="bereichNachweis_${i.id}" id="bereichNachweis_${i.id}" readonly value="" /></td>
						</tr>
						<c:set var="x" value="${x + 1}" scope="page"/>
						<c:forEach var="j" items="${fach}">
							<c:if test="${i.id==j.bereich_id}">
							<tr id="ectsFach${j.id}">
								<td></td>
								<td id="${j.id}">${j.name}</td>
								<form:input path="ects[${x}].fachId" type="hidden" value="${j.id}" />
								<form:input path="ects[${x}].bereichId" type="hidden" value="${j.bereich_id}" />
								<td class="ectsPunkte">${j.punkte}</td>
								
								<c:choose>
									<c:when test="${!empty ects}">
										<c:forEach var="k" items="${ects}">
											<c:if test="${j.id==k.fachId}">
												<td class="ectsPunkte"><form:input path="ects[${x}].punkte" name="fachInput_${j.id}" id="fachInput_${j.id}" class="bereich_${i.id}" type="text" size="2" value="${k.punkte}"/><p id="error_fachInput_${j.id}"><spring:message code="vervollst.aos.error" /></p><p id="errorDecimal_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerDec" /></p><p id="errorNegative_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerNeg" /></p></td>
												<td class="ectsPunkte"><form:textarea path="ects[${x}].nachweis" name="fachNachweis_${j.id}" id="fachNachweis_${j.id}" value="${k.nachweis}"/></td>
												<c:set var="x" value="${x + 1}" scope="page"/>
											</c:if>
										</c:forEach> 
									</c:when>
									
									<c:otherwise>
										<td class="ectsPunkte"><form:input path="ects[${x}].punkte" name="fachInput_${j.id}" id="fachInput_${j.id}" class="bereich_${i.id}" type="text" size="2"/><p id="error_fachInput_${j.id}"><spring:message code="vervollst.aos.error" /></p><p id="errorDecimal_fachInput_${j.id}"><spring:message code="vervollst.aos.errorDec" /></p><p id="errorNegative_fachInput_${j.id}"><spring:message code="vervollst.aos.errorNeg" /></p></td>
										<td class="ectsPunkte"><form:textarea path="ects[${x}].nachweis" name="fachNachweis_${j.id}" id="fachNachweis_${j.id}"/></td>
										<c:set var="x" value="${x + 1}" scope="page"/>
									</c:otherwise>
									
								</c:choose>
							</tr>
							</c:if>
						</c:forEach>
						
					</c:if>
				</c:forEach>
			</table>
			<br>
			<table class="ectsTable" id="units">
			<tr class="unitTable">
				<td><spring:message code="vervollst.aos.unittype" /></td>
				<td class="ectsPunkte"><input name="unittype" id="units" type="text" size="10"/></td>
			</tr>
			<tr class="unitTable">
				<td><spring:message code="vervollst.aos.total" /></td>
				<td class="ectsPunkte"><input name="unittotal" id="units" type="text" size="10"/></td>
			</tr>
			<tr class="unitTable">
				<td><spring:message code="vervollst.aos.duration" /></td>
				<td class="ectsPunkte"><input name="unitduration" id="units" type="text" size="10"/></td>
			</tr>
			</table>
		<br>
		<p><b><spring:message code="vervollst.aos.supplInfo" />: </b></p>
		<textarea name="anmerk"></textarea>
		<input name="stgId" type="hidden" value="${stgId}" />
		<input name="stgName" type="hidden" value="${stgName}" />
		<br>
     	<spring:message code="vervollst.aos.btnPdf" var="submit_txt" />
        <input type="submit" value="${submit_txt}" class="btn btn-default" />
		</form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>