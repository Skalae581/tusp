<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<tusp:html>
<tusp:head>
    <title><spring:message code="precheck.interessenten_liste.title" /></title>  
    <script src="${pageContext.servletContext.contextPath}/resources/js/precheck/precheck.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/precheck/precheck.css" />
    <style>
    .content {
    	margin: 5px;
    }
    </style>
</tusp:head>
<tusp:body>
 
 
	
	

	<div id="dialog-confirm"></div>
	<tusp:panel heading="Interessenten-Daten Bearbeiten">
		
		<div class="row">
			<div class="col-md-2">
				<form:form action="downloadCSV" method="post" id="downloadCSV">
					<fieldset style="width: 400px;">
						
						
						<div class="input-group">
						 
						  
						  <button id="submitId" type="submit" class="btn btn-defoult">
							  <span class="glyphicon glyphicon-arrow-down">
							 	 </span>&nbsp;&nbsp;Pre-Check Auswertung.csv herunterladen&nbsp;&nbsp;
							  		 <span class="glyphicon glyphicon-arrow-down"></span>
							</button>
						</div>
						 
					</fieldset>
				</form:form>
			</div>
		</div>
		<hr>
		
	
	
		<c:url var="post_url" value="/precheck/bearbeiter/" />
		<c:url  var="post_edit_url" value="/edit"/>
		<form:form method="post"  class="submit" action="${post_url}">
		
			<div class="row">
				<div class="col-md-2">
				 <select id="countryList" name="countryList" 
				 	class="form-control filterSelect">
						<option value="0">Alle Länder</option>
							<c:forEach items="${countries}" var="country">
								<option ${country.id eq filter.countryValue ? 'selected':''}
									value="${country.id}">${country.defaulttextDe}</option>
							</c:forEach>
						<option value="Other">Other</option>
					</select>
				</div>
				<div class="col-md-2">
					<select id="statusList" name="statusList"
						class="form-control filterSelect">
						<option value="0">Alle Stati</option>
						<c:forEach items="${statuses}" var="status">
							<option ${status.id eq filter.statusValue ? 'selected':''}
								value="${status.id}">${status.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<select id="studyList" name="studyList"
						class="form-control filterSelect">
						<option value="0">Alle Studiengänge</option>
						<c:forEach items="${studies}" var="study">
							<option
								${study.courseofstudyId eq filter.studyValue ? 'selected':''}
								value="${study.courseofstudyId}">${study.shorttext}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<select id="doneList" name="doneList"
						class="form-control filterSelect">
						<option value="b">Alle Einträge</option>
						<option ${'y' eq filter.doneValue ? 'selected':''} value="y">
							Bearbeitet</option>
						<option ${'n' eq filter.doneValue ? 'selected':''} value="n">
							Nicht Bearbeitet</option>
					</select>
				</div>
				<div class="col-md-2">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Vor-, Nachname oder Mail"  name="search" value="${filter.search}">
						<span class="input-group-btn">
							<button class="btn btn-default" id="search" type="button">Go!</button>
						</span>
					</div>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-2">
					<select id="order" name="order" class="form-control filterSelect" >
						<option value="bewdat">Sortieren nach Bewerbungsdatum</option>
						<option ${'nachname' eq filter.order ? 'selected':''} value="nachname">Nachname</option>
						<option ${'abschluss' eq filter.order ? 'selected':''} value="abschluss">Abschluss</option>
						<option ${'abgeschlstg' eq filter.order ? 'selected':''} value="abgeschlstg">Abgeschlosener Stg</option>
						<option ${'denote' eq filter.order ? 'selected':''} value="denote">Note DE</option>
					</select>
				</div>
				<div class="col-md-2">
					<select name="elementList" id="counterValues" class="form-control filterSelect">
						<option selected="${selectedElements}" value="200">200
							Eintr&auml;ge</option>
						<option class="op" ${'100' == selectedElements ? 'selected':''}
							value="100">100 Eintr&auml;ge</option>
						<option class="op" ${'15' == selectedElements ? 'selected':''}
							value="15">15 Eintr&auml;ge</option>
					</select>
				</div>
				
				<div class="col-md-8">
					
				    <div class="col-md-5">
						<input ${'true' eq filter.doubles ? 'checked':''} ${'true' eq filter.doublesMail ? 'disabled':''} type="checkbox" id="doubles" class="checkbox" name="doubles">										 
	  					&nbsp;<label for="doubles">Duplikate (Name, Nachname, GebDat)</label>
					</div>
			    	<div class="col-md-7">
						<input ${'true' eq filter.doublesMail ? 'checked':''} ${'true' eq filter.doubles ? 'disabled':''} type="checkbox" id="doubles_mail" class="checkbox" name="doubles_mail">										 
	  					&nbsp;<label for="doubles_mail">Duplikate (Email)</label>
					</div>
			    	

				</div>
				
			
			</div>
			
			<hr class="separator">
			
	
			
	</form:form>

	<div class="row">
	

			<div class="col-md-2">
				<div id="pagingWrapper">
					<table id="pagingTable">
						<tr>
							<c:if test="${currentPage != 1}">
								<td id="prevPageBtt"><a class="btn btn-warning"
									href="/tusp/precheck/bearbeiter/?page=${currentPage - 1}&elem=${selectedElements}">
									&lt;-
								</a></td>
							</c:if>
							<td>
								<table id="centralBttPagingTable">
									<tr>
										<c:forEach begin="1" end="${noOfPages}" var="i">
											<c:choose>
												<c:when test="${currentPage eq i}">
													<td>
														<div>
															<a class="btn btn-default firstPageBtn" id="button_selected">
																${i} </a>
														</div>
													</td>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when
															test="${currentPage > 7 && currentPage < noOfPages-1}">
															<c:if
																test="${i eq currentPage -1 || i eq currentPage +1}">
																<td id="">
																	<div>...</div>
																</td>
															</c:if>
														</c:when>
														<c:otherwise>
															<c:if test="${i eq 7 && noOfPages > 7}">
																<td id="">
																	<div>...</div>
																</td>
															</c:if>
															<c:if test="${currentPage eq 7 && i == 8 }">
																<td id="">
																	<div>...</div>
																</td>
															</c:if>
														</c:otherwise>
													</c:choose>
													<c:if test="${i <= 6 }">
														<td id="allPagesBtt">
															<div>
																<a class="btn btn-primary pages"
																	href="/tusp/precheck/bearbeiter/?page=${i}&elem=${selectedElements}">
																	${i} </a>
															</div>
														</td>
													</c:if>
													<c:if test="${i == noOfPages && noOfPages >= 7 }">
														<td id="allPagesBtt">
															<div>
																<a class="btn btn-primary pages"
																	href="/tusp/precheck/bearbeiter/?page=${i}&elem=${selectedElements}">
																	${i} </a>
															</div>
														</td>
													</c:if>
												</c:otherwise>
											</c:choose>
										</c:forEach>

									</tr>
								</table>
							</td>
							<c:if test="${currentPage lt noOfPages}">
								<td id="nextPageBtt"><a class="btn btn-warning"
									href="/tusp/precheck/bearbeiter/?page=${currentPage + 1}&elem=${selectedElements}">
									-&gt;
								</a></td>
							</c:if>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="col-md-1"></div>
			
		
			
			<div class="col-md-3">
				<label class="form-check-label" for="twinCheckbox" id="twinLabel">Datensätze:
					${counter} von Gesamt: ${counterAll} </label>	
			</div>
			
		</div>
	
	
		
	<hr>

		<tusp:table data="${data}" filter = "column" cssclass="table">
	       
	        	<c:set var="value" value="${dataItem.id}"/>
	        	
	            <tusp:column heading="precheck.nachname" >
	            	<c:choose>
	            		<c:when test="${dataItem.nachname != null && dataItem.nachname.length() > 30}">
	            			<span title="${dataItem.nachname}">${dataItem.nachname.substring(0,30)}...</span>
	            		</c:when>
	            		<c:otherwise>
	            			${dataItem.nachname}
	            		</c:otherwise>
	            	</c:choose>
	            </tusp:column>
	            <tusp:column heading="precheck.vorname" >
	            	<c:choose>
	            		<c:when test="${dataItem.vorname != null && dataItem.vorname.length() > 30}">
	            			<span title="${dataItem.vorname}">${dataItem.vorname.substring(0,30)}...</span>
	            		</c:when>
	            		<c:otherwise>
	            			${dataItem.vorname}
	            		</c:otherwise>
	            	</c:choose>
	            </tusp:column>
	            <tusp:column property="email" heading="precheck.email" />
	            <tusp:column heading="precheck.abschluss_bearbeiter" >
	            	<c:choose>
	            		<c:when test="${dataItem.abschluss != null && dataItem.abschluss.length() > 30}">
	            			<span title="${dataItem.abschluss}">${dataItem.abschluss.substring(0,30)}...</span>
	            		</c:when>
	            		<c:otherwise>
	            			${dataItem.abschluss}
	            		</c:otherwise>
	            	</c:choose>
	            </tusp:column>
	            <tusp:column heading="precheck.abgeschlstd_und_uni" >
	            	<c:choose>
	            		<c:when test="${dataItem.abgeschlossenesstd != null && dataItem.abgeschlossenesstd.length() > 30}">
	            			<span title="${dataItem.abgeschlossenesstd}">${dataItem.abgeschlossenesstd.substring(0,30)}...</span>
	            		</c:when>
	            		<c:otherwise>
	            			${dataItem.abgeschlossenesstd} 
	            		</c:otherwise>
	            	</c:choose>
	            	/<br />
	            	<c:choose>
	            		<c:when test="${dataItem.uni != null && dataItem.uni.length() > 30}">
	            			<span title="${dataItem.uni}">${dataItem.uni.substring(0,30)}...</span>
	            		</c:when>
	            		<c:otherwise>
	            			${dataItem.uni}
	            		</c:otherwise>
	            	</c:choose>
	            </tusp:column>
	
	            <tusp:column property="stdDauer" heading="precheck.stdDauer_head" title="Dauer des Studiums in Jahren" />
	            <tusp:column  heading="precheck.note_zusammengefasst" >
	            		<fmt:formatNumber type="number" maxIntegerDigits="4" maxFractionDigits="2" groupingUsed="false" value="${dataItem.minNote}"/>/
	            		<fmt:formatNumber type="number" maxIntegerDigits="4" maxFractionDigits="2" groupingUsed="false" value="${dataItem.maxNote}"/>/
	            		<fmt:formatNumber type="number" maxIntegerDigits="4" maxFractionDigits="2" groupingUsed="false" value="${dataItem.erreichteNote}"/>
				</tusp:column>
	            <tusp:column heading="precheck.deutscheNote">
	            		<fmt:formatNumber type="number"
	  					maxIntegerDigits="2" value="${dataItem.tuhhNote}"/>
	            </tusp:column>
	            <tusp:column property="stg_tuhh" heading="precheck.studiengangTUHH" />
	            <tusp:column property="studyCountry" heading="precheck.studienland"/> 
	            <tusp:column property="bewerbungsdatum" heading="precheck.bewerbungsdatum" />
	            <tusp:column property="bearbeitet" heading="precheck.bearbeitet_head">
	            	<c:choose>
	            		<c:when test="${dataItem.bearbeitet == 'y'}">
	            			<span> <img class="checkedImg" src="${pageContext.servletContext.contextPath}/resources/css/precheck/check.png"> </span>
	            		</c:when>
	            	</c:choose>
	            </tusp:column>
	            <tusp:column property="statusName" heading="precheck.status">
	            <select style="font-family:'FontAwesome', Arial;" class="form-control statusValue" id="statusValue${dataItem.id}" name="statusValue" 
	            	 ${not empty dataItem.bearbeitungsdatum ? 'disabled' : ''}  >
						<c:choose>
							<c:when test="${dataItem.status == 8}">										
								<option 
									${dataItem.status == 8 ? 'selected' : '' }
									value="8">Sonderfall TIE
								</option>
							</c:when>
							<c:otherwise>
								<option
									${dataItem.status == 1 ? 'selected' :''}
									value="1">akzeptiert
								</option>
								<option
									${dataItem.status == 2 ? 'selected' :''}
									value="2">unbekannt
								</option>
								<option 
									${dataItem.status == 3 ? 'selected' : '' }
									value="3">fraglich
								</option>
								<option 
									${dataItem.status == 4 ? 'selected' : '' }
									value="4">abgelehnt wg. Note
								</option>
								<option 
									${dataItem.status == 5 ? 'selected' : '' }
									value="5">abgelehnt wg. Stg.
								</option>
								<option 
									${dataItem.status == 6? 'selected' : '' }
									value="6">abgelehnt(Nt./Stg./Hs.)
								</option>
							</c:otherwise>
						</c:choose>
					</select>
					
	             </tusp:column>
	             
	             <tusp:column property="actions" heading="precheck.bearbeiter.actions">
	             	<div class="nowrap">
		            	<a class="btn btn-info btn-sm" title="Bearbeiten" href="<c:url value="/precheck/bearbeiter/edit?id=${dataItem.id}" />">
		            		<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
		            	</a>

			            	<c:if test="${empty dataItem.bearbeitungsdatum}">        
			            		<button class="btn btn-warning mailSend btn-sm"  id="sendMail${dataItem.id}" 
			            			 title="Status speichern und Mail versenden">          			 	            		
				            		<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>&nbsp;&nbsp;E-Mail 
			            		</button>
			            	</c:if>

		            	<c:if test="${not empty dataItem.bearbeitungsdatum}">
		    				<a class="btn btn-sm btn-info anschalten" title="Bearbeitung wieder aktivieren" href="<c:url value="/precheck/bearbeiter/anschalten?id=${dataItem.id}" />">
		            			<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>&nbsp;&nbsp;Einschalten
		            		</a>
						</c:if>
		            	<a class="btn btn-sm btn-danger loeschen" title="Löschen" href="<c:url value="/precheck/bearbeiter/delete?id=${dataItem.id}" />">
		            		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp;&nbsp;Löschen
		            	</a>
	            	</div>
	            </tusp:column>
	             
	              
	             
	        </tusp:table>
	        
	        <div class="row">
	        
	        	<div class="col-md-2">
				  <div id="pagingWrapper">
					<table id="pagingTable">
						<tr>
							<c:if test="${currentPage != 1}">
								<td id="prevPageBtt"><a class="btn btn-warning"
									href="/tusp/precheck/bearbeiter/?page=${currentPage - 1}&elem=${selectedElements}">
									&lt;-
								</a></td>
							</c:if>
							<td>
								<table id="centralBttPagingTable">
									<tr>
										<c:forEach begin="1" end="${noOfPages}" var="i">
											<c:choose>
												<c:when test="${currentPage eq i}">
													<td>
														<div>
															<a class="btn btn-default" id="button_selected">
																${i} </a>
														</div>
													</td>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when
															test="${currentPage > 7 && currentPage < noOfPages-1}">
															<c:if
																test="${i eq currentPage -1 || i eq currentPage +1}">
																<td id="">
																	<div>...</div>
																</td>
															</c:if>
														</c:when>
														<c:otherwise>
															<c:if test="${i eq 7 && noOfPages > 7}">
																<td id="">
																	<div>...</div>
																</td>
															</c:if>
															<c:if test="${currentPage eq 7 && i == 8 }">
																<td id="">
																	<div>...</div>
																</td>
															</c:if>
														</c:otherwise>
													</c:choose>
													<c:if test="${i <= 6 }">
														<td id="allPagesBtt">
															<div>
																<a class="btn btn-primary"
																	href="/tusp/precheck/bearbeiter/?page=${i}&elem=${selectedElements}">
																	${i} </a>
															</div>
														</td>
													</c:if>
													<c:if test="${i == noOfPages && noOfPages >= 7 }">
														<td id="allPagesBtt">
															<div>
																<a class="btn btn-primary"
																	href="/tusp/precheck/bearbeiter/?page=${i}&elem=${selectedElements}">
																	${i} </a>
															</div>
														</td>
													</c:if>
												</c:otherwise>
											</c:choose>
										</c:forEach>

									</tr>
								</table>
							</td>
							<c:if test="${currentPage lt noOfPages}">
								<td id="nextPageBtt"><a class="btn btn-warning"
									href="/tusp/precheck/bearbeiter/?page=${currentPage + 1}&elem=${selectedElements}">
									-&gt;
								</a></td>
							</c:if>
						</tr>
					</table>
				</div>
			</div>
        </div>
        
      </tusp:panel>
        
        
</tusp:body>
</tusp:html>