<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>





<tusp:html>
<tusp:head>
    <title><spring:message code="precheck.edit.title" /></title>
     <script src="${pageContext.servletContext.contextPath}/resources/js/precheck/precheck_edit.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/precheck/precheck.css" />
    <style>
    .content {
    	margin: 0px;
    }
    </style>
</tusp:head>
<tusp:body>


	<div id="dialog-confirm"></div>
	

   <tusp:panel heading="precheck.edit.title">
    	
     <tusp:panel heading="precheck.overview_page.title">
        <form:form method="post" action="${post_url}" modelAttribute="interessent">
            <tusp:form>
                <div class="row">
                    <div class="col-sm-4"> <!-- Personendetails -->
                        <dl>
                            <dt><spring:message code="precheck.vorname"/></dt>
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
                    <div class="col-sm-4"> <!-- Studiumsdetails -->
                        <dl>
                            <dt><spring:message code="precheck.uni_edit" /></dt>
                            <dd>${interessent.uni}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.studienland" /></dt>
                            <dd>${stgland}</dd>
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
                            <dt><spring:message code="precheck.status" /></dt>
                            <dd>
                            
                            	 	<select style="font-family:'FontAwesome', Arial;" 
                            	 	class="form-control input-small statusValue" name="statusValue" id="statusValueEdit" >
									
											<c:choose>
												<c:when test="${interessent.status == 8}">										
													<option 
														${interessent.status == 8 ? 'selected' : '' }
														value="8">Sonderfall TIE
													</option>
												</c:when>
												<c:otherwise>
													<option
														${interessent.status == 1 ? 'selected' :''}
														value="1">akzeptiert
													</option>
													<option
														${interessent.status == 2 ? 'selected' :''}
														value="2">unbekannt
													</option>
													<option 
														${interessent.status == 3 ? 'selected' : '' }
														value="3">fraglich
													</option>
													<option 
														${interessent.status == 4 ? 'selected' : '' }
														value="4">abgelehnt wg. Note
													</option>
													<option 
														${interessent.status == 5 ? 'selected' : '' }
														value="5">abgelehnt wg. Stg.
													</option>
													<option 
														${interessent.status == 6? 'selected' : '' }
														value="6">abgelehnt(Nt./Stg./Hs.)
													</option>									
												</c:otherwise>
												
											</c:choose>
												
											
										</select>
                            
 
                            </dd>
                        </dl>
                      
                       
                    </div>
                    
                    <div class="col-sm-4"> <!-- Studiumsdetails 2 -->
                    
                    		  <dl>
                            <dt><spring:message code="precheck.abschlussJahr" /></dt>
                            <dd>${interessent.abschlussJahr}</dd>
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.minimaleNote_bearbeiter" /></dt>
                            
                            <dd><fmt:formatNumber type="number"
  							maxIntegerDigits="5" value="${interessent.minNote}"/></dd>
                           
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.maximaleNote_bearbeiter" /></dt>
                             <dd><fmt:formatNumber type="number"
  							maxIntegerDigits="5" value="${interessent.maxNote}"/></dd>
                           
                        </dl>
                        <dl>
                            <dt><spring:message code="precheck.abschlussNote_bearbeiter" /></dt>
                             <dd><fmt:formatNumber type="number"
  							maxIntegerDigits="5" value="${interessent.erreichteNote}"/></dd>
                           
                        </dl>
                        <dl>
                        	<dt><spring:message code="precheck.deutscheNote" /></dt>
                        	<dd>
			            		<fmt:formatNumber type="number" maxIntegerDigits="2" value="${interessent.tuhhNote}"/>
		  					</dd>
                        </dl>
                       	<c:choose>
					         <c:when test = "${interessent.kommentar ne null && interessent.kommentar ne ''}">
					             <dl>
	                            	<dt><spring:message code="precheck.kommentar" /></dt>                          
	                            	<dd>${interessent.kommentar}</dd>
                        		</dl>
					         </c:when>    
					     </c:choose>
                        <dl>
                            <dt><spring:message code="precheck.stgTuhh" /></dt>
                            <%-- <dd>${interessent.stgTuhh}</dd> --%>
                             <dd>${stgTuhh}</dd>
                        </dl>
                    </div>
                </div>

                <spring:message code="precheck.btn.submit.edit" var="submit_txt" />
                <tusp:formRow label="" fieldname="">
                    <a href="<c:url value="/precheck/bearbeiter/" />" class="btn btn-default"><spring:message code="precheck.btn.back" /></a>
                    
                    <c:if test="${empty interessent.bearbeitungsdatum}">
            			<input type="submit" value="${submit_txt}" class="btn btn-default" id="editBtn"/> 
            		</c:if>   
                </tusp:formRow>


            </tusp:form>
        </form:form>

    </tusp:panel>
    	
    </tusp:panel>

</tusp:body>
</tusp:html>
