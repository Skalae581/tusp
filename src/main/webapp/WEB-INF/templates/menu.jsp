<%@page import="org.apache.logging.log4j.Logger"%>
<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tusp" uri="/WEB-INF/tusp_taglib.tld"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-default center" role="navigation">

    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#sidebar-navbar-collapse">
            <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <span class="visible-xs navbar-brand">TUSP</span>
    </div>
    <div class="navbar-collapse collapse" id="sidebar-navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/" />"><spring:message code="menu.start" /></a></li>
            <%
			/*
			* Suche und lade alle menu.jsp Files im view-Bereich
			*/
			Logger logger = LogManager.getLogger("menu.jsp");
			String path = "/WEB-INF/views/";
			File dir = new File(getServletContext().getRealPath(path));
			for(File file : dir.listFiles()) {
				if(!file.isDirectory()) {
					continue;
				}
				
				File menujsp = new File(getServletContext().getRealPath(path) +"/"+ file.getName() + "/menu.jsp");
				if(!menujsp.exists()) {
					logger.debug("Konnte Menü für " + file.getName() + " nicht laden. Datei "+menujsp.getAbsolutePath()+" existiert nicht.");
					continue;
				}
			
				pageContext.include(path + file.getName() + "/menu.jsp");
			}
            %>
        </ul>
    </div>
</nav>
