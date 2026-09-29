<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>

<tusp:html>
<tusp:head>
    <title><spring:message code="appname"/></title>
</tusp:head>
<tusp:body vollebreite="false">

	<c:url var="post_url" value="/tsstudent/edit/?id=${student.id}" />
	<tusp:panel heading="Sonderstudent hinzufügen">
		
        <form:form method="post" action="${post_url}" id="form">
            <tusp:form>
                <tusp:formRow label="Mtknr:" fieldname="mtknr">
                	<input type="number" value="${student.mtknr}" class="form-control" autofocus="autofocus" name="mtknr" />
                </tusp:formRow>
                <tusp:formRow label="Start-Datum" fieldname="from">
               		 <input type="date" value="${student.startdate}" class="form-control" name="from" />
                </tusp:formRow>
                <tusp:formRow label="End-Datum" fieldname="to">
               		 <input type="date" value="${student.enddate}" class="form-control" name="to" />
                </tusp:formRow>

                <tusp:formRow label="" fieldname="">
                	<spring:message code="btn.save" var="search_txt"/>
                    <input type="submit" value="${search_txt}" class="btn btn-default" />
                    <a href="<c:url value="/tsstudent/" />"  class="btn btn-default" >
						<tusp:out key="btn.back"/>
					</a>
				</tusp:formRow>
            </tusp:form>
            <a href="<c:url value="/tsstudent/delete?id=${student.id}" />" class="btn btn-danger"><tusp:out key="btn.delete"/></a>
        </form:form>

	</tusp:panel>

</tusp:body>
</tusp:html>