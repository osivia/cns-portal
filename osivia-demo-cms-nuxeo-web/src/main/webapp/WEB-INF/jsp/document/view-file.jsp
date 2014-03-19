<%@ page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@ page import="fr.toutatice.portail.cms.nuxeo.portlets.bridge.Formater"%>
<%@ page import="fr.toutatice.portail.cms.nuxeo.portlets.customizer.CMSCustomizer"%>
<%@ page import="java.util.List"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.Document"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.PropertyMap"%>
<%@ page import="org.osivia.portal.api.Constants"%>
<%@ page import="org.osivia.portal.api.menubar.MenubarItem"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page isELIgnored="false" %>


<%
// Nuxeo controller
NuxeoController nuxeoController = (NuxeoController) request.getAttribute("ctx");
// Current Nuxeo document
Document document = (Document) request.getAttribute("doc");

// URL
String url = nuxeoController.getLink(document, CMSCustomizer.TEMPLATE_DOWNLOAD).getUrl();
pageContext.setAttribute("url", url);

// File content
PropertyMap map = document.getProperties().getMap("file:content");
if ((map != null) && !map.isEmpty()) {
    // File name
    pageContext.setAttribute("filename", map.getString("name"));
    // File size
    String size = map.getString("length");
    if (size != null) {
        pageContext.setAttribute("fileSize", Math.floor(Double.parseDouble(size) / 1024));
    } else {
        pageContext.setAttribute("fileSize", "0");
    }
}

// Icon
pageContext.setAttribute("icon", Formater.formatNuxeoIcon(document));

// Description
pageContext.setAttribute("description", document.getString("dc:description"));


// Menubar customization
@SuppressWarnings("unchecked")
List<MenubarItem> menubar = (List<MenubarItem>) request.getAttribute(Constants.PORTLET_ATTR_MENU_BAR);
menubar.add(new MenubarItem(null, "Télécharger...", MenubarItem.ORDER_PORTLET_SPECIFIC, url, null, "portlet-menuitem download", null));

%>


<div class="nuxeo-docview-normal-view">
    <div class="nuxeo-docview-description">
        <p class="description">${description}</p>
    
        <c:if test="${not empty filename}">
            <p>
                <a href="${url}">
                    <img class="icon" src="${pageContext.request.contextPath}${icon}" alt="${filename}">
                    <span>${filename}</span>
                </a>
                <span>(${fileSize} Ko)</span>
            </p>
        </c:if>
    </div>
</div>
