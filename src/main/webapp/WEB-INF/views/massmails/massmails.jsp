<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>


<tusp:html>
<tusp:head>
    <title><spring:message code="Mail Verteiler" /></title>
    <link href="${pageContext.servletContext.contextPath}/resources/css/massmails/massmails.css" rel="stylesheet"/>
    <link href="${pageContext.servletContext.contextPath}/resources/css/global.css" rel="stylesheet"/>
  	<script src="${pageContext.servletContext.contextPath}/resources/js/ckeditor5/build/ckeditor.js"></script>
  	<script src="${pageContext.servletContext.contextPath}/resources/js/jquery.blockUI.js"></script> 
    <script src="${pageContext.servletContext.contextPath}/resources/js/massmails/massmails.js"></script> 
    
</tusp:head>
<tusp:body>
	<tusp:panel heading="Mail Verteiler" >
	
		<c:url var="post_url" value="/massmails/sendMail/" />
		
		<form:form action="${post_url}" method="post" id="massmails" modelAttribute="mailProtokoll">
			
			<fieldset style="width: 1200px;">
				 <tusp:form>
				 
					<div class="row">
					
						
						
						<c:forEach items="${verteiler}" var="v">
								<tusp:formRow label="${v.name } ${v.sem } ${v.year == 0 ? '' : v.year}" fieldname="reciever" label_static="true">
							 		 <form:checkbox path="receiver" value="${v.name }###${v.semester}" cssClass="massmails_checkbox"/>
							 		 <span class="rec">(${v.count } Empfänger)</span>
							 	</tusp:formRow>
			 			</c:forEach> 
			 			
					  
					</div>
				 	
				 	
				 	<div class="row">
					 	<tusp:formRow label="E-Mail an mich" fieldname="reciever" label_static="true">
					 		 <form:checkbox path="receiver" value="mich" checked="checked" disabled="true" cssClass="massmails_checkbox"/>
					 	</tusp:formRow>
					 	<!-- Betreff -->
						 <tusp:formRow label="Betreff (*)" fieldname="subject" label_static="true">
			                    <form:input path="subject" id="subject" class="form-control" />
			             </tusp:formRow>
			             <!-- Reply To -->
						 <tusp:formRow label="Reply To" fieldname="replyto" label_static="true">
						 <!-- <i class="fa fa-search"> Some content</i> -->
						 		<p id="helper_text">Reply To ist die Adresse an die die Antworten gesendet werden, 
						 		wenn ein Empfänger auf Antworten klickt. (default servicedesk+tune@tuhh.de)</p>
			                    <form:input path="replyto" id="replyto" class="form-control" />
			             </tusp:formRow>
			                  <!-- Anhang Button -->
			            <tusp:formRow label="" fieldname="" label_static="true">
			            	<button id="attachment">Anhang</button>
			            </tusp:formRow>
			             <!-- CKEDITOR 5 -->
			             <tusp:formRow label="" fieldname="body" label_static="true">
				        	<form:textarea path="body" id="editor" name="textarea" value="${body}" />
			             </tusp:formRow>
			             <br>

						<div id="dialog" title="Dialog box">
							<p>
							Es können keine Dateien an die E-Mail angehängt werden. 
							Stattdessen ist es möglich Dateien zu verlinken. Verwenden das Symbol 
							<svg class="ck ck-icon ck-button__icon" viewBox="0 0 20 20"><path d="m11.077 15 .991-1.416a.75.75 0 1 1 1.229.86l-1.148 1.64a.748.748 0 0 1-.217.206 5.251 5.251 0 0 1-8.503-5.955.741.741 0 0 1 .12-.274l1.147-1.639a.75.75 0 1 1 1.228.86L4.933 10.7l.006.003a3.75 3.75 0 0 0 6.132 4.294l.006.004zm5.494-5.335a.748.748 0 0 1-.12.274l-1.147 1.639a.75.75 0 1 1-1.228-.86l.86-1.23a3.75 3.75 0 0 0-6.144-4.301l-.86 1.229a.75.75 0 0 1-1.229-.86l1.148-1.64a.748.748 0 0 1 .217-.206 5.251 5.251 0 0 1 8.503 5.955zm-4.563-2.532a.75.75 0 0 1 .184 1.045l-3.155 4.505a.75.75 0 1 1-1.229-.86l3.155-4.506a.75.75 0 0 1 1.045-.184z"></path></svg>
							oberhalb des Texteingabefeldes und kopieren dann den Link in das Feld.
							</p>
							
							<p>
							Es können Dateien verlinkt werden, die auf der TU Homepage öffentlich sind oder es können Dateien aus der Cloud verlinkt werden.
							</p>
							<p>
							
							Um Dateien aus der Cloud zu verlinken gehen sie auf <a href='https://cloud.tuhh.de' target='_blank' >cloud.tuhh.de</a>
							
							Dort kann mit dem + (Plus) eine neue Datei hochgeladen werden. Danach kann diese mit dem 
							
							<img src='data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGhlaWdodD0iMTYiIHdpZHRoPSIxNiIgdmVyc2lvbj0iMS4xIiB2aWV3Qm94PSIwIDAgMTYgMTYiPjxjaXJjbGUgY3g9IjMuNSIgY3k9IjgiIHI9IjIuNSIgZmlsbD0iIzAwMCIvPjxjaXJjbGUgY3k9IjEyLjUiIGN4PSIxMi41IiByPSIyLjUiIGZpbGw9IiMwMDAiLz48Y2lyY2xlIGN4PSIxMi41IiBjeT0iMy41IiByPSIyLjUiIGZpbGw9IiMwMDAiLz48cGF0aCBkPSJtMy41IDggOSA0LjVtLTktNC41IDktNC41IiBzdHJva2U9IiMwMDAiIHN0cm9rZS13aWR0aD0iMiIgZmlsbD0ibm9uZSIvPjwvc3ZnPgo=' />
						
							Symbol geteilt werden. Hier kann bei "Link Teilen" mit + (Plus) ein neuer Freigabelink erstellt werden. Dieser kann dann wie oben beschrieben in den Mailtext eingefügt werden.
							</p>
						</div>
	             	</div>
		           
	             </tusp:form>
	             
	             <tusp:formRow label="" fieldname="submit" label_static="true">
	             		<input type="submit" value="E-Mail Senden"  id="btn" class="mailSubmit" />
	             </tusp:formRow>
					
				
			</fieldset>
	
			

	</form:form>
		
           
          
    </tusp:panel>
</tusp:body>
</tusp:html>