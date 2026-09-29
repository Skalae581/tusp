<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vorabcheck.show.titel" /> ${vorabInteressent.stgName}</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
    <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_ects.js"></script>
</tusp:head>
<tusp:body>

    <tusp:panel heading="vorabcheck.show.titel">
			<h4><spring:message code="vorabcheck.studiengang" />: ${stgName}</h4>
			<br>
			<p><spring:message code="vorabcheck.prozent" />: ${prozentsatz}</p>			
			<table class="ectsTable">
				<tr class="ectsHeader">
				<td><spring:message code="vorabcheck.ects_vorabcheck.bereich" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.anford" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.zusatz" /></td>
				<td><spring:message code="vorabcheck.ects_vorabcheck.voraus" /></td>			
				</tr>
				
				<c:forEach var="i" items="${bereich}" >
					<c:if test="${i.stgId==stgId}">
						<tr class="ectsBereich" id="ectsBereich${i.id}"> 
							<td id="${i.id}">${i.name}</td>
							<td></td>
							<td class="ectsZusatz_${i.id}">${i.zusatz}</td>
							<td class="ectsPunkte"><input type="hidden" name="bereichPunkte_${i.id}" value="${i.punkte}"/>${i.punkte}</td>							 
						</tr>
						<c:forEach var="j" items="${fach}" >
							<c:if test="${i.id==j.bereichId}">
							<tr id="ectsFach${j.id}">
								<td></td>
								<td name="fachId" value="${j.id}" id="${j.id}">${j.name}</td>
								<td>${j.zusatz}</td>
								<td class="ectsPunkte">${j.punkte}</td> 
							</tr>
							</c:if>
						</c:forEach>
					</c:if>
				</c:forEach>
			</table>
			<div id="kleingedruckt"><spring:message code="vorabcheck.ects_vorabcheck.kleingedruckt" /></div>
		<br>
		<c:if test="${!empty extrakz || extrakz == '0'}">
			<div>
			<p class="extra">${extraTitel}</p>
				<c:forEach var="x" items="${leistungen}" >
					<c:if test="${x.stgId==stgId}">
						<p id="${x.id}">${x.name}</p>						 
					</c:if>
				</c:forEach>
			</div>
		</c:if>
		<br>
     	<a href="<c:url value="/vorabcheck/admin/studiengaenge" />" class="btn btn-default"><spring:message code="btn.back" /></a>
    </tusp:panel>

</tusp:body>
</tusp:html>