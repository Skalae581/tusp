<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>



<tusp:html>
<tusp:head>
    <title><spring:message code="global.connection.test_title" /></title>
</tusp:head>
<tusp:body>

    <tusp:panel heading="global.connection.test_title">
    	<c:url var="post_url" value="/global/admin/connection/test" />
    	<form method="get" action="${post_url}">
           <tusp:form>
               <tusp:formRow label="global.connection.host" fieldname="host">
                   <input type="text" name="host" id="host" class="form-control"  value="${host}"/>
               </tusp:formRow>
               <tusp:formRow label="global.connection.port" fieldname="port">
                   <input type="text" name="port" id="port" class="form-control"  value="${port}"/>
               </tusp:formRow>
               <tusp:formRow label="global.connection.user" fieldname="user">
                   <input type="text" name="user" id="user" class="form-control" value="${user}" />
               </tusp:formRow>
               <tusp:formRow label="global.connection.dir" fieldname="dir">
                   <input type="text" name="dir" id="dir" class="form-control" value="${dir}" />
               </tusp:formRow>
               <tusp:formRow label="global.connection.cmd" fieldname="cmd">
                   <input type="text" name="cmd" id="cmd" class="form-control" value="${cmd}" />
               </tusp:formRow>

               <spring:message code="btn.run" var="submit_txt" />
               <tusp:formRow label="" fieldname="">
                   <input type="submit" value="${submit_txt}" class="btn btn-default" />
               </tusp:formRow>
           </tusp:form>
        </form>
    </tusp:panel>
    
    <tusp:panel heading="global.connection.test_title" hiddenWhen="${empty files}">
		<ul>
			<c:forEach items="${files}" var="file">
				<li>${file}</li>
			</c:forEach>
		</ul>
    </tusp:panel>
    
    <tusp:panel heading="global.connection.test_title" hiddenWhen="${empty cmdOutput}">
		<pre>${cmdOutput}</pre>
    </tusp:panel>
    
</tusp:body>
</tusp:html>
