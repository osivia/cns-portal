<%@ page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@ page import="java.util.Locale"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.Document"%>
<%@ page import="org.osivia.portal.demo.customizer.Formatter"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page isELIgnored="false" %>


<%
// Nuxeo controller
NuxeoController nuxeoController = (NuxeoController) request.getAttribute("ctx");
// Current Nuxeo document
Document document = (Document) request.getAttribute("doc");

// Locale
Locale locale = request.getLocale();

// Date
pageContext.setAttribute("date", Formatter.formatDate(document, locale, false));
// Titre
pageContext.setAttribute("titre", document.getTitle());
// Lien du titre
pageContext.setAttribute("lien", nuxeoController.getLink(document).getUrl());
// Description
pageContext.setAttribute("description", document.getString("dc:description"));
// Contenu
pageContext.setAttribute("contenu", document.getString("webp:content"));

%>


<li class="blog-post">
    <div class="blog-post-date">${date}</div>
    <div class="blog-post-title"><a href="${lien}">${titre}</a></div>
    <div class="blog-post-description">${description}</div>
    <div class="blog-post-content">${contenu}</div>        
</li>
