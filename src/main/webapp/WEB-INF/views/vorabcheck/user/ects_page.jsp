<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vorabcheck.menu" /> ${vorabInteressent.stgName}</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
    <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_ects.js"></script>
</tusp:head>
<tusp:body vollebreite="false">

    <tusp:panel heading="vorabcheck.info_vorabcheck.titel">
	    <c:url var="post_url" value="/vorabcheck/result_page" />
		<form:form method="post" action="${post_url}" modelAttribute="ectsForm" id="ects">
			<h3><spring:message code="vorabcheck.studiengang" /> ${vorabInteressent.stgName}</h3>
			<h4><spring:message code="vorabcheck.ects_vorabcheck.hinweisTitel" /></h4>
			<ol>
				<li><spring:message code="vorabcheck.ects_vorabcheck.hinweis1" /></li>
				<li><spring:message code="vorabcheck.ects_vorabcheck.hinweis2" /></li>
			</ol>
			<br>
			<table class="ectsTable">
				<tr class="ectsHeader">
				<td><spring:message code="vorabcheck.ects_vorabcheck.bereich" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.anford" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.zusatz" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.voraus" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.vorstud" /></td>
				</tr>
				
				<c:forEach var="i" items="${bereich}" varStatus="y">
					<c:if test="${i.stg_id==vorabInteressent.stgId}">					
						<tr class="ectsBereich" id="ectsBereich${i.id}"> 
							<td id="${i.id}">${i.name}</td>
							<td></td>
							<td class="ectsZusatz_${i.id}">${i.zusatz}</td>
							<c:choose>
								<c:when test="${fn:contains(i.zusatz, 'Gesamt')}" >
									<td class="ectsPunkte"><input type="hidden" name="bereichPunkte_${i.id}" value="${i.punkte}"/>${i.punkte}</td>
									<td class="ectsPunkte" size="2"><input name="bereichSumme_${i.id}" id="bereichSumme_${i.id}" readonly="true" value="" /></td>
								</c:when>
								<c:otherwise>
									<td class="ectsPunkte"><form:input path="ects[${y.index}].bereichPunkte" type="hidden" name="bereichPunkte_${i.id}" value="${i.punkte}"/>${i.punkte}</td>
									<td class="ectsPunkte" size="2"><form:input path="ects[${y.index}].gesamtPunkte" name="bereichSumme_${i.id}" id="bereichSumme_${i.id}" readonly="true" value="" /></td>
								</c:otherwise>
							</c:choose> 
						</tr>
						
						<c:forEach var="j" items="${fach}" varStatus="x">
							<c:if test="${i.id==j.bereich_id}">
							<tr id="ectsFach${j.id}">
								<td></td>
								<td name="fachId" value="${j.id}" id="${j.id}">${j.name}</td>
								<form:input path="ects[${x.index}].fachId" type="hidden" value="${j.id}" />
								<form:input path="ects[${x.index}].bereichId" type="hidden" value="${j.bereich_id}" />
								<td>${j.zusatz}</td>
								<td class="ectsPunkte">${j.punkte}</td>
								<td class="ectsPunkte"><form:input path="ects[${x.index}].punkte" name="fachInput_${j.id}" id="fachInput_${j.id}" class="bereich_${i.id}" type="text" size="2"/><p id="error_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehler" /></p><p id="errorDecimal_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerDec" /></p><p id="errorNegative_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerNeg" /></p></td> 
							</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</table>
			<div id="kleingedruckt"><spring:message code="vorabcheck.ects_vorabcheck.kleingedruckt" /></div>
		<br>
     	<spring:message code="btn.send" var="submit_txt" />
        <input type="submit" value="${submit_txt}" class="btn btn-default" />
		</form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>