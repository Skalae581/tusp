<%@tag import="java.util.Locale"%>
<%@tag import="java.util.HashMap"%>
<%@tag import="javax.servlet.jsp.tagext.JspFragment"%>
<%@tag import="de.tuhh.tusp.utils.PropertyAccessUtils"%>
<%@tag import="de.tuhh.tusp.utils.CalendarUtils"%>
<%@tag import="java.util.Date"%>
<%@tag import="java.util.regex.Matcher"%>
<%@tag import="java.util.regex.Pattern"%>
<%@tag import="java.lang.reflect.Method"%>
<%@tag import="java.util.ArrayList"%>
<%@ tag language="java" pageEncoding="UTF-8" description="erstellt eine Tabellen-Spalte"  %>

<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ attribute name="data" required="true" type="java.util.Collection" description="Collection of data used to populate the table." %>
<%@ attribute name="cssclass" required="false" type="java.lang.String" description="CSS-Classen der Tabelle durch space getrennt z.B.: table-striped table-bordered table-hover table-condensed" %>
<%@ attribute name="addUrl" required="false" type="java.lang.String" description="URL zum Hinzufügen von neuen Elementen" %>
<%@ attribute name="headerfix" required="false" type="java.lang.Boolean" description="Soll der Header der Tabelle immer oben stehen" %>
<%@ attribute name="sortable" required="false" type="java.lang.Boolean" description="Ist die Tabelle sortierbar?" %>
<%@ attribute name="filter" required="false" type="java.lang.String" description="Suchfeld für die Tabelle. column => Über jeder Spalte ist ein Eingabefeld; single => es gibt nur ein Eingabefeld das in allen Spalten sucht" %>

<c:url var="baseUrl" value="/" scope="page"/>


<%
String baseUrl = (String)jspContext.getAttribute("baseUrl");
ArrayList<HashMap<String, Object>> tableColumns = new ArrayList<HashMap<String, Object>>();
jspContext.setAttribute("table_columns", tableColumns, PageContext.REQUEST_SCOPE);
%>

<jsp:doBody />
<div class="table-container">
	<div class="table-menu">
		<c:if test="${not empty addUrl}">
			<a href="${baseUrl}${addUrl}" class="btn btn-default"><tusp:out key="btn.add"/></a>
		</c:if>
	</div>
	<div class="table-responsive">
		<div><%-- Wird für die Größenbeschränkung der Tabelle gebraucht --%>
		<table class="table ${cssclass} ${headerfix ? ' headerfix' : ''} ${sortable ? ' sortable' : ''} filter-${filter}">
			
		 
			<tr class="thead">
				<%-- Iteration over Columns --%>
				<c:forEach items="${table_columns}" var="text" varStatus="loop">
					<th class="col${loop.index} ${text.nofilter ? ' nofilter' : ''}" style="${text.style}" >
						<c:choose>
							<c:when test="${text.headingStatic}">
								${text.heading}
							</c:when>
							<c:otherwise>
								<tusp:out key="${text.heading}"/>
							</c:otherwise>
						</c:choose>
					</th>
				</c:forEach>
			</tr>
			<%-- Iteration over Rows --%>
			<c:forEach items="${data}" var="dataItem2" varStatus="status" >
			<c:set var="rowindex" value="${status.index}" />
			<c:set var="rowcount" value="${status.count}" />
			<tr class="tdata">
				<%-- Iteration over Columns --%>
				<c:forEach begin="0" end="${fn:length(table_columns) - 1}" var="i" step="1">
					
						<%
						// Aktuelle Umgebung suchen
						int i = (int)jspContext.getAttribute("i");
						Object item = jspContext.getAttribute("dataItem2");
						HashMap<String, Object> columDefinition = (HashMap<String, Object>)tableColumns.get(i);
						
						// Wenn Child-Element gefunden
						if(columDefinition.get("jspBody") != null) {
							jspContext.setAttribute("dataItem", item, PageContext.REQUEST_SCOPE);
							jspContext.setAttribute("dataIndex", jspContext.getAttribute("rowindex"), PageContext.REQUEST_SCOPE);
							jspContext.setAttribute("dataCount", jspContext.getAttribute("rowcount"), PageContext.REQUEST_SCOPE);
							jspContext.getOut().print("<td class='col"+i+"' style='"+columDefinition.get("style")+"'>");
							((JspFragment)columDefinition.get("jspBody")).invoke(jspContext.getOut());
							jspContext.getOut().print("</td>");
						} else {
							// Sonst aus Properties erzeugen
							
							// Eigenschaften der Spalte laden
							String property = (String)columDefinition.get("property");
							String href = (String)columDefinition.get("href");
							String title = (String)columDefinition.get("title");

							// Anzeige-Wert laden
							Object value = PropertyAccessUtils.getProperty(item, property);
							
							String label = "";
							String data = "";
							if(value == null) {
								label = "";
								data  = "";
							} else if(value instanceof Date && !CalendarUtils.isMidnight((Date)value)) {
								label = CalendarUtils.format((Date)value, CalendarUtils.DATETIMEFORMAT_GERMAN);
								data = CalendarUtils.format((Date)value, CalendarUtils.DATETIMEFORMAT_ENGLISH);
							} else if(value instanceof Date) {
								label = CalendarUtils.dateToDatumsString((Date)value);
								data = CalendarUtils.dateToDatumsString((Date)value, Locale.ENGLISH);
							} else {
								label = value.toString();
								data = label;
							}
							
							// Link anzeigen?
							if(href != null) {
								try {
									Matcher matcher = Pattern.compile("\\[.+?\\]").matcher(href);
									while(matcher.find()) {
										String match = matcher.group();
										String prop = match.substring(1, match.length() - 1);
										Object retVal = PropertyAccessUtils.getProperty(item, prop);
										href = href.replace(match, retVal != null ? retVal.toString() : "");
									}
									label = String.format("<a href='%s%s'>%s</a>", baseUrl, href, label);
								} catch (Exception e) {
									System.out.println(e.getClass().getCanonicalName().toString() + ": " + e.getMessage());
								}
							}

							jspContext.getOut().print("<td title='"+title+"' class='col"+i+"' data-value='"+data+"' style='"+columDefinition.get("style")+"'>");
							jspContext.getOut().print(label);
							jspContext.getOut().print("</td>");
						}
						%>
					
				</c:forEach>
			</tr>
			</c:forEach>
			
		 
			
	</table>
			
			<c:if test="${empty data}">
				<p><tusp:out key="table.empty"/></p>
			</c:if>
		</div>
	</div>
</div>