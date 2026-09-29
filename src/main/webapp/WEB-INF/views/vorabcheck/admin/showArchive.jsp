<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tusp:html>
<tusp:head>
    <title><spring:message code="vorabcheck.showArchives.titel" /> ${vorabInteressent.stgName}</title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
    <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_ects.js"></script>
</tusp:head>
<tusp:body>
	<c:choose>
	<c:when test="${size > 1}">
		<c:choose>
			<c:when test="${sessionIsSet}">
				<tusp:panel heading="vorabcheck.showArchive.titel" >
					<h4><spring:message code="vorabcheck.studiengang" />: ${stgName}</h4>
					<p><spring:message code="vorabcheck.bearbeiter" />: ${bearbeiter}</p>	
					<p><spring:message code="vorabcheck.grund" />: ${grund}</p>
					<p><spring:message code="vorabcheck.datum" />: ${datum}</p>
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
								<c:if test="${i.stgId==stgId && i.sessionId == sessionId}">
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
					<br>
					<c:choose>
					<c:when test="${sessionIsSet}">
			     		<a href="<c:url value="/vorabcheck/admin/showArchive?stgId=${originStgId}&shorttext=${stgName}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
			     	</c:when>
			     	<c:otherwise>
			     		<a href="<c:url value="/vorabcheck/admin/studiengaenge" />" class="btn btn-default"><spring:message code="btn.back" /></a>
			     	</c:otherwise>
			     	</c:choose>
			    	</tusp:panel>
			</c:when>
			<c:otherwise>
			<tusp:panel heading="vorabcheck.showArchives.titel" >
				<h4><spring:message code="vorabcheck.studiengang" />: ${stgName}</h4>
				<br>		
				<tusp:table data="${stg}">
				  <tusp:column property="nummer" heading="vorabcheck.nummer">
	 	            	${dataCount}
		            </tusp:column>
		            
		             <tusp:column property="datum" heading="vorabcheck.datum">
		             	<fmt:formatDate pattern="dd-MM-yyyy" value="${dataItem.zeitstempel}" />
		            </tusp:column>
		            
		            <tusp:column property="aktion" heading="vorabcheck.aktion">
		            	<a href="<c:url value="/vorabcheck/admin/showArchive?stgId=${dataItem.id}&shorttext=${stgName}&sessionId=${dataItem.session_id}" />"><spring:message code="vorabcheck.stg.archive" /></a>
		            </tusp:column>
		            
		            <tusp:column property="bearbeiter" heading="vorabcheck.bearbeiter">
		            	${dataItem.bearbeiter}
		            </tusp:column>
		            
		             <tusp:column property="grund" heading="vorabcheck.grund">
		            	${dataItem.grund}
		            </tusp:column>    
		        </tusp:table>			
				<br>
		     	<a href="<c:url value="/vorabcheck/admin/studiengaenge" />" class="btn btn-default"><spring:message code="btn.back" /></a>
		    </tusp:panel>
			</c:otherwise>
		</c:choose>
	   </c:when>
				
		<c:otherwise>
		<c:choose>
		<%-- wird eigentlich nicht benötigt, da Null-Templates nicht archiviert zu werden brauchen --%>
			<c:when test="${standardkz == 'N'}">
				<tusp:panel heading="vorabcheck.showArchive.titel" >
					<h4><spring:message code="vorabcheck.studiengang" />: ${stgName}</h4>
					<p><spring:message code="vorabcheck.bearbeiter" />: ${bearbeiter}</p>	
					<p><spring:message code="vorabcheck.grund" />: ${grund}</p>
					<p><spring:message code="vorabcheck.datum" />: ${datum}</p>
					<p><spring:message code="vorabcheck.stg.null" /></p>	
					
					<br>
					<c:choose>
					<c:when test="${sessionIsSet}">
			     		<a href="<c:url value="/vorabcheck/admin/showArchive?stgId=${originStgId}&shorttext=${stgName}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
			     	</c:when>
			     	<c:otherwise>
			     		<a href="<c:url value="/vorabcheck/admin/studiengaenge" />" class="btn btn-default"><spring:message code="btn.back" /></a>
			     	</c:otherwise>
			     	</c:choose>
			    </tusp:panel>
			</c:when>
		
			<c:otherwise>
				<tusp:panel heading="vorabcheck.showArchive.titel" >
				<h4><spring:message code="vorabcheck.studiengang" />: ${stgName}</h4>
				<p><spring:message code="vorabcheck.bearbeiter" />: ${bearbeiter}</p>	
				<p><spring:message code="vorabcheck.grund" />: ${grund}</p>
				<p><spring:message code="vorabcheck.datum" />: ${datum}</p>
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
							<c:if test="${i.stgId==stgId && i.sessionId == sessionId}">
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
					
				<br>
				<c:choose>
				<c:when test="${sessionIsSet}">
		     		<a href="<c:url value="/vorabcheck/admin/showArchive?stgId=${originStgId}&shorttext=${stgName}" />" class="btn btn-default"><spring:message code="btn.back" /></a>
		     	</c:when>
		     	<c:otherwise>
		     		<a href="<c:url value="/vorabcheck/admin/studiengaenge" />" class="btn btn-default"><spring:message code="btn.back" /></a>
		     	</c:otherwise>
		     	</c:choose>
		    	</tusp:panel>
		    </c:otherwise>
	    </c:choose>
    </c:otherwise>
    </c:choose>

</tusp:body>
</tusp:html>