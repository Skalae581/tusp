<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>





<tusp:html>
<tusp:head>
    <title>${vorgang.connectorKey} - <spring:message code="fame.user.vorgang_title" /></title>
    <style>
    .fame-vorgang-menu {
		margin-bottom: 0.5em;
	}
	</style>
	<script type="text/javascript" src="<c:url value="/fame/user/workflowValidation?id=${workflowId}" />"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$('textarea').keyup(function(e){
			var elem = document.getElementById(e.target.id);
			if(elem.scrollHeight > elem.offsetHeight) {
				elem.style.height = (elem.scrollHeight + 1) + "px"
			}
		});
		$('textarea').keyup();
		$('textarea').css('overflow', 'hidden');
	})
	</script>
</tusp:head>
<tusp:body>

	<c:if test="${vorgang.warn != null && vorgang.warn != ''}">
		<div class="alert alert-danger">${vorgang.warn}</div>
	</c:if>

	<div class="row">
		<div class="col-md-2">
			<jsp:include page="include_menu.jsp" />
		</div>
		<div class="col-md-10">

			<div class="fame-vorgang-menu">
	           	<c:if test="${not readonly}">
	           		<a 	href="<c:url value="/fame/user/vorgang?id=${vorgang.id}&done=true" />"  
	           		class="btn btn-default">
	           			<spring:message code="fame.user.vorgang_freigeben" />
	           		</a>
	           		<a 	href="<c:url value="/fame/user/vorgang?id=${vorgang.id}&none=true" />"  
	           		class="btn btn-default">
	           			<spring:message code="fame.user.keine_weitere_bearbeitung" />
	           		</a>
	           	</c:if>
	           	<c:if test="${readonly}">
	           		<a 	href="<c:url value="/fame/user/vorgang?id=${vorgang.id}&edit=true" />"  
	           		class="btn btn-default">
	           			<spring:message code="fame.user.vorgang_bearbeiten" />
	           		</a>
	           	</c:if>
	           	
	           	<c:choose>
	           		<c:when test="${\"me\".equals(gesperrt_durch)}">
           				<span id="gesperrt_durch"><spring:message code="fame.user.gesperrt_durch" />
	           				<spring:message code="fame.user.gesperrt_durch_mich" />
	           			</span>
	           		</c:when>
	           		<c:when test="${\"none\".equals(gesperrt_durch)}">
	           			<spring:message code="fame.user.gesperrt_durch_none" />
	           		</c:when>
		           	<c:when test="${not empty gesperrt_durch}">
           				<span id="gesperrt_durch"><spring:message code="fame.user.gesperrt_durch" />
		           			${gesperrt_durch}
	           			</span>
		           	</c:when>
	           	</c:choose>
	           	
           		<a 	href="<c:url value="/fame/user/vorgang?id=${vorgang.id}&start" />"  
           		class="btn btn-default">
           			<spring:message code="fame.user.vorgang_aktualisieren" />
           		</a>
            </div>
		                	
		    <c:url var="post_url" value="/fame/user/vorgang?id=${vorgang.id}" />
		    <tusp:panel heading="fame.user.vorgang_title">
		    	
		        <form:form method="post" action="${post_url}&${_csrf.parameterName}=${_csrf.token}" 
		        	modelAttribute="vorgang" id="form" enctype="multipart/form-data">
		            <tusp:form>
						<c:forEach items="${inputs}" var="input">
							<c:choose>
							
								<%-- Keine Rechte zu lesen! --%>
								<c:when test="${input.recht.name eq 'NONE'}"></c:when>
							
								<%-- Soll nicht gezeigt werden --%>
								<c:when test="${input.position lt 0}"></c:when>
								
								<c:when test="${'FILE_NAME' eq input.inputTypeUniqName}">
					                <tusp:formRow label="${input.name}" label_static="true">
					                	<input class="form-control fame-control" value="${input.eingabe}" readonly="readonly"/>
					                	<c:if test="${input.valueId != null}">
						                	<a 	href="<c:url value="/fame/user/file?id=${input.valueId}&action=download&instanceId=${vorgang.workflowInstanceId}&vorgangId=${vorgang.id}" />"  
						                		class="btn btn-default"
						                		title="<spring:message code="btn.download" />">
						                		<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
						                	</a>
						                	<c:if test="${input.mimeType eq 'application/pdf'}">
							                	<a 	href="<c:url value="/fame/user/file?id=${input.valueId}&action=preview&instanceId=${vorgang.workflowInstanceId}&vorgangId=${vorgang.id}" />"  
							                		class="btn btn-default"
							                		title="<spring:message code="btn.preview" />">
							                		<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
							                	</a>
						                	</c:if>
					                	</c:if>
					                	<c:if test="${!readonly && !(input.recht.name ne 'WRITE')}">
					                		<c:if test="${input.valueId != null}">
							                	<a 	href="<c:url value="/fame/user/file?id=${input.valueId}&action=delete&instanceId=${vorgang.workflowInstanceId}&vorgangId=${vorgang.id}" />"  
							                		class="btn btn-default"
							                		title="<spring:message code="btn.delete" />">
							                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
							                	</a>
						                	</c:if>
						                	<input 
						                		style="display:inline-block"
						                		type="file" 
						                    	name="value_${input.id}"  
						                    	id="value_${input.id}" 
						                    	title="${input.beschreibung}"
						                    	${readonly || (input.recht.name ne 'WRITE') ? 'readonly=\"readonly\"' : ''}
						                    	value="${input.eingabe}"
						                    	class="fame-control" />
					                	</c:if>
					                </tusp:formRow>
								</c:when>
							
								<c:when test="${'CONNECTOR_TEXT' eq input.inputTypeUniqName}">
					                <tusp:formRow label="${input.name}" label_static="true">
					                	<input class="form-control fame-control" value="${input.eingabe}" readonly="readonly"/>
					                </tusp:formRow>
								</c:when>
								
								<c:when test="${'CONNECTOR_FILE_NAME' eq input.inputTypeUniqName}">
					                <tusp:formRow label="${input.name}" label_static="true">
					                	<input class="form-control fame-control" value="${input.eingabe}" readonly="readonly"/>
					                	
					                	<c:if test="${input.valueId != null}">
						                	<a 	href="<c:url value="/fame/user/file?id=${input.valueId}&action=download&instanceId=${vorgang.workflowInstanceId}&vorgangId=${vorgang.id}" />"  
						                		class="btn btn-default"
						                		title="<spring:message code="btn.download" />">
						                		<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>
						                	</a>
						                	<a 	href="<c:url value="/fame/user/file?id=${input.valueId}&action=preview&instanceId=${vorgang.workflowInstanceId}&vorgangId=${vorgang.id}" />"  
						                		class="btn btn-default"
						                		title="<spring:message code="btn.preview" />">
						                		<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
						                	</a>
					                	</c:if>
					                </tusp:formRow>
								</c:when>
								
								<c:when test="${'TEXTAREA' eq input.inputTypeUniqName}">
					                <tusp:formRow label="${input.name}" label_static="true">
					                	<textarea 
					                    	name="value_${input.id}"  
					                    	id="value_${input.id}" 
					                    	title="${input.beschreibung}" 
					                    	${readonly || (input.recht.name ne 'WRITE') ? 'readonly=\"readonly\"' : ''}
					                    	class="form-control fame-control">${input.eingabe}</textarea>
					                </tusp:formRow>
								</c:when>
								
								<c:when test="${'SELECT' eq input.inputTypeUniqName}">
					                <tusp:formRow label="${input.name}" label_static="true">
					                	<select 
					                    	name="value_${input.id}"  
					                    	id="value_${input.id}" 
					                    	title="${input.beschreibung}" 
					                    	${readonly || (input.recht.name ne 'WRITE') ? 'disabled=\"disabled\"' : ''}
					                    	class="form-control fame-control">
					                    	<c:set var="optionsName">options${input.id}</c:set>
					                    	<c:forEach items="${requestScope[optionsName]}" var="opt">
					                    		<option 
					                    			value="${opt.id}" 
					                    			${opt.disabled ? 'readonly=\"readonly\"' : ''}
					                    			${opt.id == input.eingabe ? 'selected=\"selected\"' : ''}>${opt.name}</option>
					                    	</c:forEach>
					                    	</select>
					                </tusp:formRow>
								</c:when>
								
								<c:when test="${'CHECKBOX' eq input.inputTypeUniqName}">
					                <tusp:formRow label="${input.name}" label_static="true">
					                	<input 
					                		type="checkbox" 
					                    	name="value_${input.id}"  
					                    	id="value_${input.id}" 
					                    	title="${input.beschreibung}"
					                    	${readonly || (input.recht.name ne 'WRITE') ? 'readonly=\"readonly\"' : ''}
					                    	value="1"
					                    	${input.eingabe == 1 ? 'checked=\"checked\"' : ''}
					                    	class="fame-control" />
					                </tusp:formRow>
								</c:when>
								
								<c:when test="${'RADIOBUTTON' eq input.inputTypeUniqName}">
					                <tusp:formRow label="${input.name}" label_static="true">
				                    	<c:set var="optionsName">options${input.id}</c:set>
				                    	<c:forEach items="${requestScope[optionsName]}" var="opt">
					                    	<div class="radio">
					                    		<label>
								                	<input 
								                		type="radio" 
								                    	name="value_${input.id}"  
								                    	id="value_${input.id}_id_${opt.id}" 
								                    	title="${input.beschreibung}"
					                    				${readonly || (input.recht.name ne 'WRITE') ? 'readonly=\"readonly\"' : ''}
								                    	value="${opt.id}"
								                    	${input.eingabe == opt.id ? 'checked=\"checked\"' : ''}
						                    			${opt.disabled ? 'readonly=\"readonly\"' : ''}
								                    	class="fame-control" />${opt.name}
							                    </label>
						                    </div>
				                    	</c:forEach>
					                </tusp:formRow>
								</c:when>
								
								<c:otherwise>
					                <tusp:formRow label="${input.name}" fieldname="value_${input.id}" pflicht="${input.pflicht}" label_static="true">
					                    <input 
					                    	value="${input.eingabe}" 
					                    	name="value_${input.id}"  
					                    	id="value_${input.id}" 
					                    	title="${input.beschreibung}" 
					                    	${readonly || (input.recht.name ne 'WRITE') ? 'readonly=\"readonly\"' : ''}
					                    	class="form-control fame-control" />
					                </tusp:formRow>
								</c:otherwise>
								
								
							</c:choose>
						</c:forEach>
						
		                <tusp:formRow label="fame.user.status" fieldname="statusId">
		                	<form:select 
		                		path="statusId" 
		                		items="${statuses}" 
		                		itemLabel="name" 
		                		itemValue="id"
		                		disabled="${readonly}"
		                		class="form-control fame-control"
					            />
		                </tusp:formRow>
		
		                <spring:message code="btn.save" var="submit_txt" />
		                <tusp:formRow label="" fieldname="">
		                	<c:if test="${not readonly}">
		                    	<input type="submit" value="${submit_txt}" class="btn btn-default" />
		                    </c:if>
		                    
		                	<a 	href="<c:url value="/fame/user/status?statusId=${vorgang.statusId}&instanceId=${vorgang.workflowInstanceId}" />"  
		                		class="btn btn-default">
		                		<spring:message code="btn.back" />
		                	</a>
		                	
		                </tusp:formRow>
		                
		            </tusp:form>
		        </form:form>
		    </tusp:panel>
		    <tusp:panel heading="fame.user.log">
		    	<tusp:table data="${log}">
		    		<tusp:column heading="fame.user.log_subject" property="subject"></tusp:column>
		    		<tusp:column heading="fame.user.log_user" property="user"></tusp:column>
		    		<tusp:column heading="fame.user.log_time" property="changeTime"></tusp:column>
		    	</tusp:table>
		    </tusp:panel>
		</div>
	</div>

</tusp:body>
</tusp:html>