<%@tag import="de.tuhh.tusp.utils.PropertyAccessUtils"%>
<%@ tag language="java" pageEncoding="UTF-8" description="erstellt ein Select"  %>

<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="items" required="true" type="java.util.Collection" description="Die Options" %>
<%@ attribute name="value" required="false" type="java.lang.String" description="Der Wert, der ausgewählt ist" %>
<%@ attribute name="name" required="false" type="java.lang.String" description="Name des Feldes" %>
<%@ attribute name="keyCol" required="true" type="java.lang.String" description="Property der Options, die zur Anzeige genommen werden soll" %>
<%@ attribute name="valCol" required="true" type="java.lang.String" description="Property der Options, die als ID genommen werden soll" %>
<%@ attribute name="id" required="false" type="java.lang.String" description="ID" %>
<%@ attribute name="clazz" required="false" type="java.lang.String" description="CSS-Klassen" %>
<%@ attribute name="form" required="false" type="java.lang.String" description="HTML-Form zu dem das Element gehört" %>
<%@ attribute name="emptyKey" required="false" type="java.lang.String" description="ID, die bei leerem Defaultelement genommen werden soll (wird ohne emptyVal ignoriert)" %>
<%@ attribute name="emptyVal" required="false" type="java.lang.String" description="Value, der bei leerem Defaultelement genommen werden soll" %>

<%-- <jsp:doBody /> --%>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<c:choose>
	<c:when test="${empty items}">
		<tusp:out key="select.empty_options"/>
	</c:when>
	<c:otherwise>
		<select class="${clazz}" id="${id}" name="${name}" form="${form}">
			<c:if test="${emptyVal != null}">
				<option value="${emptyKey}"><tusp:out key="${emptyVal}" /></option>
			</c:if>
		
			<% String valCol = (String)jspContext.getAttribute("valCol"); %>
			<% String keyCol = (String)jspContext.getAttribute("keyCol"); %>
			<c:forEach items="${items}" var="item" ><%
				 
				Object item = jspContext.getAttribute("item");
				Object val = PropertyAccessUtils.getProperty(item, keyCol);
				String value;
				if(val == null) {
					value = "";
				} else if(val instanceof String) {
					value = (String)val;
				} else {
					value = val.toString();
				}
				
				
				%><option 
				value="<%jspContext.getOut().print(value);%>" 
				<% if(value.equals(jspContext.getAttribute("value")))
					{
						jspContext.getOut().print("selected=\"selected\"");
					}
				%>
				><%jspContext.getOut().print(PropertyAccessUtils.getProperty(item, valCol));%></option>
			</c:forEach>
		</select>
	</c:otherwise>
</c:choose>