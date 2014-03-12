<%@ page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
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
// Document link
pageContext.setAttribute("link", nuxeoController.getLink(document));
// Document title
pageContext.setAttribute("title", document.getTitle());
// Document description
pageContext.setAttribute("description", document.getString("dc:description"));
// Document vignette
if ((document.getProperties().getMap("ttc:vignette") != null) && (document.getProperties().getMap("ttc:vignette").getString("data") != null)) {
    pageContext.setAttribute("vignette", nuxeoController.createFileLink(document, "ttc:vignette"));
}
// Document update date
pageContext.setAttribute("date", Formatter.formatDate(document, request.getLocale(), true));
%>


<li class="thread">
    <c:if test="${not empty vignette}">
        <a href="${link.url}"><img class="thread-vignette" src="${vignette}" /></a>
    </c:if>

    <div class="thread-content">
        <div class="thread-title"><a href="${link.url}">${title}</a></div>
        <div class="thread-description">${description}</div>
        <div class="thread-date">Dernière modification : ${date}</div>
    </div>
</li>
