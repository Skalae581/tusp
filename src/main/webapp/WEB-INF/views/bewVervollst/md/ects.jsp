<%@page contentType="text/html;encoding=UTF-8;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vervollst.browser.title" /> ${bewerber.stgName}</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
    <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_ects.js"></script>
</tusp:head>
<tusp:body>
    <tusp:panel heading="vervollst.title">
    <!-- TODO: bei Null-Templates wie Materialwissenschaft keinen Text anzeigen? -->
	    <c:url var="post_url" value="/bewVervollst/md/downloadPdf" />
		<form:form method="post" action="${post_url}" modelAttribute="ectsForm" id="ects">
			<table class="ectsPunkte">
				<c:if test="${!empty bewerber.codenummer }">
				<tr><td><spring:message code="vorabcheck.codeNummer" />: </td> <td>${bewerber.codenummer}</td></tr>
				</c:if>
				<tr><td><spring:message code="vorabcheck.studiengang" />: </td> <td>${bewerber.stgName}</td></tr>
			</table>
			<br>
			<p><spring:message code="vervollst.partOneOfInfo" arguments="${bewerber.stgName}" argumentSeparator=";"/></p>
			<br>
			<div class="vervollstBeispiel">
		    	<p>Beispiel:</p>
		    	<p>In Modul XY wurden 5 ECTS vergeben, dabei wurde zum Großteil "Höhere Analysis und Formenkalkül" und zum kleinen Teil "Informatik" gelehrt.</p>
		    	<table>
		    		<tr>
		    		<td>Richtige Eintragung: </td>
		    		<td>"Höhere Analysis und Formenkalkül"	</td>
		    		<td>= 4 ECTS</td>
		    		</tr>
		    		<tr>
		    		<td></td>
		    		<td>"Informatik" </td>
		    		<td>= 1 ECTS</td>
		    		</tr>
		    		<tr><td>&nbsp;</td></tr>
		    		<tr>
		    		<td>Falsche Eintragung: </td>
		    		<td>"Höhere Analysis und Formenkalkül"	</td>
		    		<td>= 5 ECTS</td>
		    		</tr>
		    		<tr>
		    		<td></td>
		    		<td>"Informatik" </td>
		    		<td>= 5 ECTS</td>
		    		</tr>
	    		</table>
	    	</div>
	    	<br>
	    	<p><spring:message code="vervollst.partTwoOfInfo" /></p>
	    	<p><spring:message code="vervollst.partThreeOfInfo" /></p>
	    	<p><b><spring:message code="vervollst.partFourOfInfo" /></b></p>
	    	<ol>
	    		<li><spring:message code="vervollst.partFourOfInfo.firstNote" /></li>
	    		<li><spring:message code="vervollst.partFourOfInfo.secondNote" /></li>
	    		<li><spring:message code="vervollst.partFourOfInfo.thirdNote" /></li>
	    		<li><spring:message code="vervollst.partFourOfInfo.fourthNote" /></li>
	    		<li><spring:message code="vervollst.partFourOfInfo.fifthNote" /></li>
		    	<c:if test="${!empty extrakz}">
		    		<li><spring:message code="vervollst.partFourOfInfo.sixthNote" /></li>
		    	</c:if>
	    	</ol>
			<table class="ectsTable">
				<tr class="ectsHeader">
				<td><spring:message code="vorabcheck.ects_vorabcheck.bereich" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.anford" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.zusatz" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.voraus" /></td>
				<td><spring:message code="vervollst.ects.vorstud" /></td>
				<td><spring:message code="vervollst.ects.erwerb" /></td>
				<td><spring:message code="vervollst.ects.modulhandbuch" /></td>
				<td><spring:message code="vervollst.ects.nachweis" /></td>
				</tr>

				<c:set var="x" value="0" scope="page"/>
				<c:forEach var="i" items="${bereich}">
					<c:if test="${i.stg_id==bewerber.stgId}">
						<tr class="ectsBereich" id="ectsBereich${i.id}"> 
							<td id="${i.id}">${i.name}</td>
							<td></td>
							<form:input path="ects[${x}].fachId" type="hidden" value="" />
							<form:input path="ects[${x}].bereichId" type="hidden" value="${i.id}" />
							<td class="ectsZusatz_${i.id}">${i.zusatz}</td>
							<td class="ectsPunkte"><form:input path="ects[${x}].bereichPunkte" name="bereichPunkte_${i.id}" type="hidden" value="${i.punkte}" />${i.punkte}</td>
							<td class="ectsPunkte" size="2"><form:input path="ects[${x}].gesamtPunkte" name="bereichSumme_${i.id}" id="bereichSumme_${i.id}" readonly="true" value="" /></td> 
							<td class="ectsPunkte" size="2"><input name="bereichErwerb_${i.id}" id="bereichErwerb_${i.id}" readonly value="" /></td>
							<td class="ectsPunkte" size="2"><input name="bereichModul_${i.id}" id="bereichModul_${i.id}" readonly value="" /></td>
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
								<td>${j.zusatz}</td>
								<td class="ectsPunkte">${j.punkte}</td>
								
								<c:choose>
									<c:when test="${!empty ects}">
										<c:forEach var="k" items="${ects}">
											<c:if test="${j.id==k.fachId}">
												<td class="ectsPunkte"><form:input path="ects[${x}].punkte" name="fachInput_${j.id}" id="fachInput_${j.id}" class="bereich_${i.id}" type="text" size="2" value="${k.punkte}"/><p id="error_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehler" /></p><p id="errorDecimal_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerDec" /></p><p id="errorNegative_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerNeg" /></p></td>
												<td class="ectsPunkte"><form:textarea path="ects[${x}].erwerb" name="fachErwerb_${j.id}" id="fachErwerb_${j.id}" value="${k.erwerb}"/></td>
												<td class="ectsPunkte"><form:textarea path="ects[${x}].modul" name="fachModul_${j.id}" id="fachModul_${j.id}" value="${k.modul}"/></td>
												<td class="ectsPunkte"><form:textarea path="ects[${x}].nachweis" name="fachNachweis_${j.id}" id="fachNachweis_${j.id}" value="${k.nachweis}"/></td>
												<c:set var="x" value="${x + 1}" scope="page"/>
											</c:if>
										</c:forEach> 
									</c:when>
									
									<c:otherwise>
										<td class="ectsPunkte"><form:input path="ects[${x}].punkte" name="fachInput_${j.id}" id="fachInput_${j.id}" class="bereich_${i.id}" type="text" size="2"/><p id="error_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehler" /></p><p id="errorDecimal_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerDec" /></p><p id="errorNegative_fachInput_${j.id}"><spring:message code="vorabcheck.ects_vorabcheck.fehlerNeg" /></p></td>
										<td class="ectsPunkte"><form:textarea path="ects[${x}].erwerb" name="fachErwerb_${j.id}" id="fachErwerb_${j.id}"/></td>
										<td class="ectsPunkte"><form:textarea path="ects[${x}].modul" name="fachModul_${j.id}" id="fachModul_${j.id}"/></td>
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
			<div id="kleingedruckt"><spring:message code="vorabcheck.ects_vorabcheck.kleingedruckt" /></div>
		<br>
		<c:if test="${!empty extrakz}">
			<p><b>${extraTitel}: </b></p>
			<table class="ectsTable">
			<tr class="extra">
				<td><spring:message code="vervollst.lectures" /></td>
				<td><spring:message code="vervollst.erbracht" /></td>
				<td><spring:message code="vervollst.ausstehend" /></td>
			</tr>
				<c:forEach var="x" items="${leistungen}">
					<c:if test="${x.stgId==stgId}">
					<tr>
						<form:input path="extra[${x.id}].id" type="hidden" value="${x.id}" />
						<form:input path="extra[${x.id}].stgId" type="hidden" value="${x.stgId}" />
						<td><form:input path="extra[${x.id}].name" type="hidden" value="${x.name}" /><p>${x.name}</p></td>
						<td class="ectsPunkte"><form:input path="extra[${x.id}].zusatzErbracht" type="text" value="" id="extra" size="100" /></td>
						<td class="ectsPunkte"><form:input path="extra[${x.id}].zusatzAusstehend" type="text" value="" id="extra" size="100" /></td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
			<p><spring:message code="vorabcheck.vervollst.stern" /></p>
		</c:if>
		<br>
		<p><b><spring:message code="vervollst.annotation" />: </b></p>
		<textarea name="anmerk"></textarea>
		<input name="stgId" type="hidden" value="${stgId}" />
		<input name="stgName" type="hidden" value="${stgName}" />
		<br>
     	<spring:message code="btn.pdf" var="submit_txt" />
        <input type="submit" value="${submit_txt}" class="btn btn-default" />
		</form:form>
    </tusp:panel>

</tusp:body>
</tusp:html>