<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vorabcheck.admin.titel" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
</tusp:head>
<tusp:body>

    <tusp:panel heading="vorabcheck.admin.titel">
		<tusp:table data="${stgMD}">
            <tusp:column property="shorttext" heading="vorabcheck.studiengang_md">
            	<p>${dataItem.shorttext_de}</p>
            </tusp:column>
            
            <c:set var="bereichStgIds" value="${bereichStgIds}" />
            <c:set var="archivOriginIds" value="${archivOriginIds}" />
            
            <tusp:column property="ects" heading="vorabcheck.ects" href="vorabcheck/admin/change?stgId=${dataItem.id}">
            	<c:choose>
					<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
						<c:if test="${empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1}">
					    <a href="<c:url value="/vorabcheck/admin/modify?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.modify" /></a>
						 |
						 </c:if>
						<a class="ectsShow" href="<c:url value="/vorabcheck/admin/show?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />" ><spring:message code="vorabcheck.stg.show" /></a>
					</c:when>
					
					<c:otherwise>
						<a class="ectsCreate" href="<c:url value="/vorabcheck/admin/insert?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.create" /></a>
					</c:otherwise>
				</c:choose>
            </tusp:column>

             <tusp:column property="aktion_vorabcheck" heading="vorabcheck.aktion_vorabcheck" href="vorabcheck/admin/change?stgId=${dataItem.id}&action=vorab">
            	<c:choose>
	            	<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vorab" />">
	            		<c:choose>
							<c:when test="${(empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1) && (empty dataItem.standardkz or dataItem.standardkz == null)}">
							    <spring:message code="vorabcheck.stg.activate" />
							</c:when>
							<c:when test="${(empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1) && (!empty dataItem.standardkz or dataItem.standardkz != null)}">
							<%-- normalerweise sollte dies hier nicht angezeigt werden, d.h. wenn es Daten in Bereich gibt, dann soll Standardkz automatisch null gesetzt sein --%>
							</c:when>
							<c:otherwise>
								<spring:message code="vorabcheck.stg.deactivate" />
							</c:otherwise>
						</c:choose>
						</a>
					</c:when>
					
					<c:otherwise>
						<c:choose>
							<c:when test="${empty dataItem.standardkz or dataItem.standardkz == null}">
								<a class="ectsNull" href="<c:url value="/vorabcheck/admin/nullify?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.nullify" /></a>
							</c:when>
							
							<c:otherwise>
								<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vorab" />">
				            		<c:choose>
										<c:when test="${empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1}">
										    <spring:message code="vorabcheck.stg.activate" />
										</c:when>
										<c:otherwise>
											<spring:message code="vorabcheck.stg.deactivate" />
										</c:otherwise>
									</c:choose>
								</a>
								<spring:message code="vorabcheck.stg.null" /> 
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
            </tusp:column>
            
            <tusp:column property="aktion_vervollst" heading="vorabcheck.aktion_vervollst" href="vorabcheck/admin/change?stgId=${dataItem.id}&action=vervollst">
            	<c:choose>
	            	<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vervollst" />">
	            		<c:choose>
							<c:when test="${(empty dataItem.aktiv_vervollst or dataItem.aktiv_vervollst != 1) && (empty dataItem.standardkz or dataItem.standardkz == null)}">
							    <spring:message code="vorabcheck.stg.activate" />
							</c:when>
							<c:when test="${(empty dataItem.aktiv_vervollst or dataItem.aktiv_vervollst != 1) && (!empty dataItem.standardkz or dataItem.standardkz != null)}">
							<%-- normalerweise sollte dies hier nicht angezeigt werden, d.h. wenn es Daten in Bereich gibt, dann soll Standardkz automatisch null gesetzt sein --%>
							</c:when>
							<c:otherwise>
								<spring:message code="vorabcheck.stg.deactivate" />
							</c:otherwise>
						</c:choose>
						</a>
					</c:when>
					
					<c:otherwise>
						<c:choose>
							<c:when test="${empty dataItem.standardkz or dataItem.standardkz == null}">
								<a class="ectsNull" href="<c:url value="/vorabcheck/admin/nullify?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.nullify" /></a>
							</c:when>
							
							<c:otherwise>
								<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vervollst" />">
				            		<c:choose>
										<c:when test="${empty dataItem.aktiv_vervollst or dataItem.aktiv_vervollst != 1}">
										    <spring:message code="vorabcheck.stg.activate" />
										</c:when>
										<c:otherwise>
											<spring:message code="vorabcheck.stg.deactivate" />
										</c:otherwise>
									</c:choose>
								</a>
								<spring:message code="vorabcheck.stg.null" /> 
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
            </tusp:column>
            
<%--             <tusp:column property="delete" heading="" href="vorabcheck/admin/change?stgId=${dataItem.id}"> --%>
<%--             	<c:choose> --%>
<%-- 	            	<c:when test="${fn:contains(bereichStgIds, dataItem.id)}"> --%>
<%-- 	            	TODO in Service: vorher prüfen, ob aktiv == 1, dann archivieren, sonst: ob schon mal aktiv gewesen, ebenso archivieren, sonst nicht --%>
<%-- 	            		<a href="<c:url value="/vorabcheck/admin/archiveDelete?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />" onclick="return confirm('Sind Sie sicher, dass Sie den kompletten Bogen für ${dataItem.shorttext_de} löschen möchten? Wenn der Bogen aktiv gesetzt ist, wird dieser vor dem Löschen archiviert.');" class="btn btn-danger"><spring:message code="btn.delete" /></a> --%>
<%-- 					</c:when> --%>
<%-- 					<c:otherwise> --%>
						
<%-- 					</c:otherwise> --%>
<%-- 				</c:choose> --%>
<%--             </tusp:column> --%>

			<tusp:column property="archiveDelete" heading="" href="vorabcheck/admin/archiveDelete?stgId=${dataItem.id}">
            	<c:choose>
	            	<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/archiveDelete?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />" onclick="return confirm('Sind Sie sicher, dass Sie den kompletten Bogen für ${dataItem.shorttext_de} archivieren möchten?');" class="btn btn-danger"><spring:message code="vorabcheck.archive" /></a>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
            </tusp:column>
            
             <tusp:column property="archiv" heading="" href="vorabcheck/admin/showArchive?stgId=${dataItem.id}">
            	<c:choose>
	            	<c:when test="${fn:contains(archivOriginIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/showArchive?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}"/>" class="btn btn-info"><spring:message code="vorabcheck.stg.archive" /></a>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
            </tusp:column>
            
        </tusp:table>
        
        <tusp:table data="${stgAOS}">
            <tusp:column property="shorttext" heading="vorabcheck.studiengang_aos">
            	<p>${dataItem.shorttext_de}</p>
            </tusp:column>
            
            <c:set var="bereichStgIds" value="${bereichStgIds}" />
            <c:set var="archivOriginIds" value="${archivOriginIds}" />
            
            <tusp:column property="ects" heading="vorabcheck.ects" href="vorabcheck/admin/change?stgId=${dataItem.id}">
            	<c:choose>
					<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
						<c:if test="${(empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1) and (empty dataItem.aktiv_vervollst or dataItem.aktiv_vervollst != 1)}">
					    <a href="<c:url value="/vorabcheck/admin/modify?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.modify" /></a>
						 |
						 </c:if>
						<a class="ectsShow" href="<c:url value="/vorabcheck/admin/show?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />" ><spring:message code="vorabcheck.stg.show" /></a>
					</c:when>
					
					<c:otherwise>
						<a class="ectsCreate" href="<c:url value="/vorabcheck/admin/insert?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.create" /></a>
					</c:otherwise>
				</c:choose>
            </tusp:column>

             <tusp:column property="aktion_vorabcheck" heading="vorabcheck.aktion_vorabcheck" href="vorabcheck/admin/change?stgId=${dataItem.id}&action=vorab">
            	<c:choose>
	            	<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vorab" />">
	            		<c:choose>
							<c:when test="${(empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1) && (empty dataItem.standardkz or dataItem.standardkz == null)}">
							    <spring:message code="vorabcheck.stg.activate" />
							</c:when>
							<c:when test="${(empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1) && (!empty dataItem.standardkz or dataItem.standardkz != null)}">
							<%-- normalerweise sollte dies hier nicht angezeigt werden, d.h. wenn es Daten in Bereich gibt, dann soll Standardkz automatisch null gesetzt sein --%>
							</c:when>
							<c:otherwise>
								<spring:message code="vorabcheck.stg.deactivate" />
							</c:otherwise>
						</c:choose>
						</a>
					</c:when>
					
					<c:otherwise>
						<c:choose>
							<c:when test="${empty dataItem.standardkz or dataItem.standardkz == null}">
								<a class="ectsNull" href="<c:url value="/vorabcheck/admin/nullify?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.nullify" /></a>
							</c:when>
							
							<c:otherwise>
								<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vorab" />">
				            		<c:choose>
										<c:when test="${empty dataItem.aktiv_vorab or dataItem.aktiv_vorab != 1}">
										    <spring:message code="vorabcheck.stg.activate" />
										</c:when>
										<c:otherwise>
											<spring:message code="vorabcheck.stg.deactivate" />
										</c:otherwise>
									</c:choose>
								</a>
								<spring:message code="vorabcheck.stg.null" /> 
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
            </tusp:column>
            
            <tusp:column property="aktion_vervollst" heading="vorabcheck.aktion_vervollst" href="vorabcheck/admin/change?stgId=${dataItem.id}&action=vervollst">
            	<c:choose>
	            	<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vervollst" />">
	            		<c:choose>
							<c:when test="${(empty dataItem.aktiv_vervollst or dataItem.aktiv_vervollst != 1) && (empty dataItem.standardkz or dataItem.standardkz == null)}">
							    <spring:message code="vorabcheck.stg.activate" />
							</c:when>
							<c:when test="${(empty dataItem.aktiv_vervollst or dataItem.aktiv_vervollst != 1) && (!empty dataItem.standardkz or dataItem.standardkz != null)}">
							<%-- normalerweise sollte dies hier nicht angezeigt werden, d.h. wenn es Daten in Bereich gibt, dann soll Standardkz automatisch null gesetzt sein --%>
							</c:when>
							<c:otherwise>
								<spring:message code="vorabcheck.stg.deactivate" />
							</c:otherwise>
						</c:choose>
						</a>
					</c:when>
					
					<c:otherwise>
						<c:choose>
							<c:when test="${empty dataItem.standardkz or dataItem.standardkz == null}">
								<a class="ectsNull" href="<c:url value="/vorabcheck/admin/nullify?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />"><spring:message code="vorabcheck.stg.nullify" /></a>
							</c:when>
							
							<c:otherwise>
								<a href="<c:url value="/vorabcheck/admin/change?stgId=${dataItem.id}&action=vervollst" />">
				            		<c:choose>
										<c:when test="${empty dataItem.aktiv_vervollst or dataItem.aktiv_vervollst != 1}">
										    <spring:message code="vorabcheck.stg.activate" />
										</c:when>
										<c:otherwise>
											<spring:message code="vorabcheck.stg.deactivate" />
										</c:otherwise>
									</c:choose>
								</a>
								<spring:message code="vorabcheck.stg.null" /> 
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
            </tusp:column>

			<tusp:column property="archiveDelete" heading="" href="vorabcheck/admin/archiveDelete?stgId=${dataItem.id}">
            	<c:choose>
	            	<c:when test="${fn:contains(bereichStgIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/archiveDelete?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}" />" onclick="return confirm('Sind Sie sicher, dass Sie den kompletten Bogen für ${dataItem.shorttext_de} archivieren möchten?');" class="btn btn-danger"><spring:message code="vorabcheck.archive" /></a>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
            </tusp:column>
            
             <tusp:column property="archiv" heading="" href="vorabcheck/admin/showArchive?stgId=${dataItem.id}">
            	<c:choose>
	            	<c:when test="${fn:contains(archivOriginIds, dataItem.id)}">
	            		<a href="<c:url value="/vorabcheck/admin/showArchive?stgId=${dataItem.id}&shorttext=${dataItem.shorttext_de}"/>" class="btn btn-info"><spring:message code="vorabcheck.stg.archive" /></a>
					</c:when>
					<c:otherwise>
						<p>&nbsp;</p>
					</c:otherwise>
				</c:choose>
            </tusp:column>
            
        </tusp:table>
    </tusp:panel>

</tusp:body>
</tusp:html>