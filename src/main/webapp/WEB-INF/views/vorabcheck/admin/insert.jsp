<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="vorabcheck.admin.titelNeu" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/vorabcheck.css" rel="stylesheet"/>
     <script src="${pageContext.servletContext.contextPath}/resources/js/vorabcheck_insert.js"></script>
</tusp:head>
<tusp:body>
	<tusp:panel heading="vorabcheck.insert.titel" >
	<h4><spring:message code="vorabcheck.studiengang" />: ${stgName}</h4>
	<%-- <br>
	<input type="hidden" id="prozentsatz" value="${prozentsatz}">
	<c:url var="standard_url" value="/vorabcheck/admin/updateProzent?stgId=${stgId}&shorttext=${stgName}" />
	<form:form method="post" action="${standard_url}" id="prozentsatz" name="prozent">
		<spring:message code="vorabcheck.prozent" />:
		<input type="hidden" name="id" value="${stgId}"/>
		<select class="selectProzent" name="prozentsatz"></select>
			
		<spring:message code="btn.save" var="submit_txt" />
		<input type="submit" value="${submit_txt}" class="btn btn-default" />
	</form:form> --%>
	<br>
  		<c:forEach var="i" items="${bereiche}">
			<c:if test="${i.stgId==stgId}">
			<div class="createModifyECTS">
			<c:url var="updateBereich_url" value="/vorabcheck/admin/update?bereichId=${i.id}&stgId=${stgId}&shorttext=${stgName}" />
			<input type="hidden" id="zusatz_${i.id}" value="${i.zusatz}">
			<form:form method="post" action="${updateBereich_url}" id="bereich" name="bereich">
				<p class="ectsBereich"><spring:message code="vorabcheck.bereich" />: 
				<input type="hidden" name="id" value="${i.id}"/>
				<input type="hidden" name="stgId" value="${i.stgId}"/>
				<input name="name" value="${i.name}" />
				<select class="selectedZusatz_${i.id}" name="zusatz" data-toggle="tooltip" data-placement="right" title="<spring:message code="vorabcheck.adminInfo"/>">
					<option value=""></option>
					<option value="<spring:message code="vorabcheck.gesamtVertiefung" />" ${j.zusatz == "Gesamt (da nur eine Vertiefung gefordert)" ? 'selected="selected"' : ''}><spring:message code="vorabcheck.gesamtVertiefung" /></option>
					<option value="<spring:message code="vorabcheck.kenntnisse" />" ${j.zusatz == "Kenntnisse in mindestens einem Anwendungsbereich erforderlic" ? 'selected="selected"' : ''}><spring:message code="vorabcheck.kenntnisse" /></option>
					<option value="<spring:message code="vorabcheck.gesamt" />" ${j.zusatz == "Gesamt" ? 'selected="selected"' : ''}><spring:message code="vorabcheck.gesamt" /></option>
				</select>
				<input name="punkte" value="${i.punkte}" size="2"/>
					
				<spring:message code="btn.update" var="update_txt" />
		        <input type="submit" value="${update_txt}" class="btn btn-default" />
		        <a href="<c:url value="/vorabcheck/admin/delete?bereichId=${i.id}&stgId=${stgId}&shorttext=${stgName}" />" onclick="return confirm('Sind Sie sicher, dass Sie den Bereich &quot;${i.name}&quot; löschen möchten?');" class="btn btn-danger"><spring:message code="btn.delete" /></a>
				</p>
			</form:form>
				
				<c:forEach var="j" items="${faecher}" >
					<c:if test="${j.bereichId==i.id}">
					<c:url var="updateFach_url" value="/vorabcheck/admin/update?fachId=${j.id}&stgId=${stgId}&shorttext=${stgName}" />
					<form:form method="post" action="${updateFach_url}" id="bereich" name="bereich">
						<p class="fach"><spring:message code="vorabcheck.fach" />:
						<input type="hidden" name="id" value="${j.id}"/>
						<input type="hidden" name="bereichId" value="${j.bereichId}"/>
						<input name="name" value="${j.name}" />
						<input name="zusatz" value="${j.zusatz}" />
						<input name="punkte" value="${j.punkte}" size="2"/>
												
						<spring:message code="btn.update" var="update_txt" />
		        		<input type="submit" value="${update_txt}" class="btn btn-default" />
		        		<a href="<c:url value="/vorabcheck/admin/delete?fachId=${j.id}&stgId=${stgId}&shorttext=${stgName}" />" onclick="return confirm('Sind Sie sicher, dass Sie das Fach &quot;${j.name}&quot; löschen möchten?');" class="btn btn-danger"><spring:message code="btn.delete" /></a>
						</p>
						</form:form>
					</c:if>
				</c:forEach>
				
				<c:url var="post_url" value="/vorabcheck/admin/insertFach?stgId=${stgId}&shorttext=${stgName}" />
		    	<form:form method="post" action="${post_url}" id="fach" name="fach">
		    		<p class="fach ectsNeu"><spring:message code="vorabcheck.fachNeu" />:
					<input name="bereichId" type="hidden" value="${i.id}" />
					<input name="name" type="text" value="" />
					<input name="zusatz" value="${j.zusatz}" />
					<input name="punkte" type="text" value="" size="2" />
					
		    		<spring:message code="btn.save" var="submit_txt" />
		        	<input type="submit" value="${submit_txt}" class="btn btn-default" />
		        	</p>
		        </form:form>
		      </div>
			</c:if>
		</c:forEach>
		<c:url var="post_url" value="/vorabcheck/admin/insertBereich?stgId=${stgId}&shorttext=${stgName}" />
    	<form:form method="post" action="${post_url}" id="bereich" name="bereich">
    		<p class="ectsNeu"><spring:message code="vorabcheck.bereichNeu" />:
			<input name="stgId" type="hidden" value="${stgId}" />
			<input name="name" type="text" value="" />
			<select class="selectZusatz" name="zusatz" data-toggle="tooltip" data-placement="right" title="Für spezielle Bögen gilt Folgendes: Falls wie bei IWI nur die Punktezahl aus einer der darunterstehenden Fachrichtungen gezählt werden soll, bitte &quot;Gesamt (da nur eine Vertiefung gefordert)&quot; wählen. Falls wie beim LIM nur eine Punktezahl jeweils aus den untenstehenden Bereichen gezählt werden soll, bitte &quot;Kenntnisse in mindestens einem Anwendungsbereich erforderlich&quot; auswählen und zusätzlich jeweils &quot;Gesamt&quot; bei den entsprechenden Bereichen.">
				<option value=""></option>
				<option value="<spring:message code="vorabcheck.gesamtVertiefung" />"><spring:message code="vorabcheck.gesamtVertiefung" /></option>
				<option value="<spring:message code="vorabcheck.kenntnisse" />"><spring:message code="vorabcheck.kenntnisse" /></option>
				<option value="<spring:message code="vorabcheck.gesamt" />"><spring:message code="vorabcheck.gesamt" /></option>
			</select>
			<input name="punkte" type="text" value="" size="2" />
			
    		<spring:message code="btn.save" var="submit_txt" />
        	<input type="submit" value="${submit_txt}" class="btn btn-default" />
        	</p>
        </form:form>
        
        <hr>
        <p class="extra"><spring:message code="vorabcheck.extra" /></p>
        <c:choose>
        <c:when test="${!empty extraTitel}">
	        <c:url var="updateTitel_url" value="/vorabcheck/admin/update?extraTitel=y&stgId=${stgId}&shorttext=${stgName}" />
	        <form:form method="post" action="${updateTitel_url}" id="extra" name="extra">
				<spring:message code="vorabcheck.extra.titel" />: <input name="titel" type="text" value="${extraTitel}" />
										
				<spring:message code="btn.update" var="update_txt" />
	       		<input type="submit" value="${update_txt}" class="btn btn-default" />
	       		<!-- delete Titel nur anzeigen, wenn keine Leistungen dazu vorhanden?! Oder dann alles löschen -->
	       		<a href="<c:url value="/vorabcheck/admin/delete?extraTitel=y&stgId=${stgId}&shorttext=${stgName}" />" onclick="return confirm('Sind Sie sicher, dass Sie den Titel &quot;${extraTitel}&quot; löschen möchten?');" class="btn btn-danger"><spring:message code="btn.delete" /></a>
			</form:form>
		</c:when>
		<c:otherwise>
			<c:url var="insertTitel_url" value="/vorabcheck/admin/insertExtraTitel?stgId=${stgId}&shorttext=${stgName}" />
	        <form:form method="post" action="${insertTitel_url}" id="extra" name="extra">
				<spring:message code="vorabcheck.extra.titel" />: <input name="titel" type="text" value="${extraTitel}" />
										
				<spring:message code="btn.save" var="save_txt" />
	       		<input type="submit" value="${save_txt}" class="btn btn-default" />
			</form:form>
		</c:otherwise>
		</c:choose>
			
        <c:forEach var="x" items="${leistungen}">
			<c:if test="${x.stgId==stgId}">
			<c:url var="updateExtra_url" value="/vorabcheck/admin/update?extraId=${x.id}&stgId=${stgId}&shorttext=${stgName}" />
			<form:form method="post" action="${updateExtra_url}" id="extra" name="extra">
				<input type="hidden" name="id" value="${x.id}"/>
				<input name="stgId" type="hidden" value="${stgId}" />
				<spring:message code="vorabcheck.extra.leistung" />: <input name="name" type="text" value="${x.name}" />
										
				<spring:message code="btn.update" var="update_txt" />
        		<input type="submit" value="${update_txt}" class="btn btn-default" />
        		<a href="<c:url value="/vorabcheck/admin/delete?extraId=${x.id}&stgId=${stgId}&shorttext=${stgName}" />" onclick="return confirm('Sind Sie sicher, dass Sie die Leistung &quot;${x.name}&quot; löschen möchten?');" class="btn btn-danger"><spring:message code="btn.delete" /></a>
			</form:form>
			</c:if>
		</c:forEach>
        
        <c:url var="post_url" value="/vorabcheck/admin/insertExtra?stgId=${stgId}&shorttext=${stgName}" />
        <form:form method="post" action="${post_url}" id="extra" name="extra">
    		<p class="extra"><spring:message code="vorabcheck.extraNeu" />:
			<input name="stgId" type="hidden" value="${stgId}" />
			<input name="name" type="text" value="" />
			
    		<spring:message code="btn.save" var="submit_txt" />
        	<input type="submit" value="${submit_txt}" class="btn btn-default" />
        	</p>
        </form:form>
        <br>
        <a href="<c:url value="/vorabcheck/admin/studiengaenge" />" class="btn btn-default"><spring:message code="btn.back" /></a>
    </tusp:panel>
</tusp:body>
</tusp:html>