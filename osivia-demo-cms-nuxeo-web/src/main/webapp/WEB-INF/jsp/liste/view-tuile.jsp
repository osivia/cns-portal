<%@ page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.PropertyMap"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.Document"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page isELIgnored="false" %>


<%
NuxeoController nuxeoController = (NuxeoController) request.getAttribute("ctx");

Document document = (Document) request.getAttribute("doc");
PropertyMap properties = document.getProperties();


// Description
pageContext.setAttribute("description", properties.getString("dc:description"));

// Style
pageContext.setAttribute("style", properties.getString("zoom:style"));

// Visuel
PropertyMap visuelProperties = properties.getMap("zoom:image");
if ((visuelProperties != null) && (visuelProperties.getString("data") != null)) {
    pageContext.setAttribute("visuel", nuxeoController.createFileLink(document, "zoom:image"));
    
}

// Chemin de la cible
String path = properties.getString("zoom:path");
if (path != null) {
    if (path.startsWith("/")) {
        pageContext.setAttribute("url", nuxeoController.getCMSLinkByPath(path, null).getUrl());
        pageContext.setAttribute("target", null);
    } else {
        pageContext.setAttribute("url", path);
        pageContext.setAttribute("target", "_blank");
    }
}

// Couleur
pageContext.setAttribute("couleur", properties.getString("zoom:color"));
%>


<portlet:defineObjects />



<li>
    <div class="col-xs-6 col-sm-12 col-lg-6">
        <a href="${url}" target="${target}" class="thumbnail tile tile-${couleur}">
            <span class="text-middle">
                <i class="glyphicons ${style}"></i>
                <span>${description}</span>
            </span>
        </a>
    </div>
</li>
