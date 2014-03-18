<%@ page import="java.util.Locale"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.Document"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.PropertyMap"%>
<%@ page import="org.osivia.portal.demo.customizer.Formatter"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page isELIgnored="false" %>


<%
Document document = (Document) request.getAttribute("doc");
PropertyMap properties = document.getProperties();

// Locale
Locale locale = request.getLocale();

// Date
pageContext.setAttribute("date", Formatter.formatDate(document, locale, true));
// Titre
pageContext.setAttribute("titre", properties.getString("dc:title"));
// Description
pageContext.setAttribute("description", properties.getString("dc:description"));
// Contenu
pageContext.setAttribute("contenu", properties.getString("webp:content"));

%>


<div class="blog-post">
    <div class="blog-post-date">${date}</div>
    <div class="blog-post-title">${titre}</div>
    <div class="blog-post-description">${description}</div>
    <div class="blog-post-content">${contenu}</div>        
</div>
