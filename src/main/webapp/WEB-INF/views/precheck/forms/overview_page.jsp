<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>




<tusp:html>
<tusp:head>

    <title><spring:message code="precheck.overview_page.title" /></title>
</tusp:head>
<tusp:body vollebreite="false">

    <c:url var="post_url" value="/precheck/end_page" />
    <tusp:panel heading="precheck.overview_page.title">
        <form:form method="post" action="${post_url}" modelAttribute="interessent" id="form3">
            <tusp:form>
                <div class="row">
                    <div class="col-sm-6"> <!-- Personendetails -->
                        <dl>
                            <dt><spring:message code="precheck.vorname" /></dt>
                            <dd>${interessent.vorname}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.nachname" /></dt>
                            <dd>${interessent.nachname}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.geschlecht" /></dt>
                            <%-- <dd>${interessent.geschlecht}</dd> --%>
                            <dd>${gender}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.geburtsdatum" /></dt>
                            <dd>${gebDatum}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.geburtsland" /></dt>
                            <dd>${gland}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.stadt" /></dt>
                            <dd>${interessent.stadt}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.email" /></dt>
                            <dd>${interessent.email}</dd>
                        </dl>
                    </div>
                    <div class="col-sm-6"> <!-- Studiumsdetails -->
                        <dl>
                            <dt><spring:message code="precheck.uni_edit" /></dt>
                            <dd>${interessent.uni}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.studienland" /></dt>
                                     
                           	 <c:choose>        
						         <c:when test = "${stgland eq 'China (VR)' or stgland eq 'Indien'}">
						           <dd>${stgland} | Institution:  ${institution}</dd>
						         </c:when>
						         
						         <c:otherwise>
						            <dd>${stgland}</dd>
						         </c:otherwise>
						      </c:choose>
                             
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.abschluss_bearbeiter" /></dt>
                            <dd>${interessent.abschluss}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.abgeschlossenesstd" /></dt>
                            <dd>${interessent.abgeschlossenesstd}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.stdDauer" /></dt>
                            <dd>${interessent.stdDauer}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.abschlussJahr" /></dt>
                            <dd>${interessent.abschlussJahr}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.minimaleNote_bearbeiter" /></dt>
                            <dd>${interessent.minNote}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.maximaleNote_bearbeiter" /></dt>
                            <dd>${interessent.maxNote}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.abschlussNote_bearbeiter" /></dt>
                            <dd>${interessent.erreichteNote}</dd>
                        </dl>
                        
                        <dl>
                            <dt><spring:message code="precheck.stgTuhh" /></dt>
                            <%-- <dd>${interessent.stgTuhh}</dd> --%>
                             <dd>${stgTuhh}</dd>
                        </dl>
                    </div>
                </div>

                <spring:message code="precheck.btn.submit" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <a href="<c:url value="/precheck/study_details" />" class="btn btn-default"><spring:message code="precheck.btn.back" /></a>
                    <input type="submit" value="${submit_txt}" class="btn btn-default" />
                </tusp:formRow>


            </tusp:form>
        </form:form>

    </tusp:panel>

</tusp:body>
</tusp:html>