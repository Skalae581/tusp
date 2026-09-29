<%@tag import="java.util.HashMap"%>
<%@tag import="javax.servlet.jsp.tagext.JspFragment"%>
<%@tag import="javax.servlet.jsp.tagext.TagSupport"%>
<%@tag import="java.util.ArrayList"%>
<%@ tag language="java" pageEncoding="UTF-8" description="erstellt eine Tabelle"  %>
<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="heading" required="true" type="java.lang.String" description="Key der Überschrift" %>
<%@ attribute name="property" required="false" type="java.lang.String" description="Property, die in dieser spalte ausgegeben werden soll" %>
<%@ attribute name="href" required="false" type="java.lang.String" description="Link-Fragment" %>
<%@ attribute name="title" required="false" type="java.lang.String" description="Title Attribut" %>
<%@ attribute name="style" required="false" type="java.lang.String" description="CSS style füt das th und td" %>
<%@ attribute name="nofilter" required="false" type="java.lang.Boolean" description="Wenn Header-Filter, dann kann der Filter für einzelne Spalten ausgeblendet werden" %>
<%@ attribute name="headingStatic" required="false" type="java.lang.Boolean" description="Wenn true wird das 'heading' nicht aus einer Language-Datei ersetzt." %>

<%
@SuppressWarnings("unchecked")
ArrayList<HashMap<String, Object>> tableColumns = (ArrayList<HashMap<String, Object>>)jspContext.getAttribute("table_columns", PageContext.REQUEST_SCOPE);

if(tableColumns != null) {
	HashMap<String, Object> thisColumn = new HashMap<String, Object>();
	thisColumn.put("jspBody", getJspBody());
	thisColumn.put("heading", this.heading);
	thisColumn.put("property", this.property);
	thisColumn.put("href", this.href);
	thisColumn.put("title", this.title);
	thisColumn.put("style", this.style);
	thisColumn.put("nofilter", this.nofilter);
	thisColumn.put("headingStatic", this.headingStatic);
	tableColumns.add(thisColumn);
}
%>