<%@ page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.Document"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@ page isELIgnored="false" %>


<%
// Nuxeo controller
NuxeoController nuxeoController = (NuxeoController) request.getAttribute("ctx");
// Current Nuxeo document
Document document = (Document) request.getAttribute("doc");
// Current Nuxeo document link
pageContext.setAttribute("link", nuxeoController.getLink(document));
// Current Nuxeo document title
pageContext.setAttribute("title", document.getProperties().getString("dc:title"));
%>


<portlet:defineObjects />


<li>
    <p>
        <img alt="Workspace" src="${pageContext.request.contextPath}/images/workspace.gif">
        <a href="${link.url}">${title}</a>
    </p>
</li>
