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

// Visuel
PropertyMap visuelProperties = properties.getMap("zoom:image");
if ((visuelProperties != null) && (visuelProperties.getString("data") != null)) {
    pageContext.setAttribute("visuel", nuxeoController.createFileLink(document, "zoom:image"));
    pageContext.setAttribute("style", properties.getString("zoom:style"));
}

// Chemin de la cible
String path = properties.getString("zoom:path");
if (path != null) {
    if (path.startsWith("/")) {
        pageContext.setAttribute("url", "window.location.href='" + nuxeoController.getCMSLinkByPath(path, null).getUrl() + "'");
    } else {
        pageContext.setAttribute("url", "window.open('" + path + "', '_blank')");
    }
}

// Couleur
pageContext.setAttribute("couleur", properties.getString("zoom:color"));
%>


<portlet:defineObjects />


<li>
    <div class="tuile ${couleur}" onclick="${url}">
        <div class="contenu-tuile">
            <c:choose>
                <c:when test="${'banniere' == style}">
                    <!-- Visuel en bannière -->
                    <div class="banner" style="background-image: url('${visuel}')">
                        <div>
                            <!-- Description -->
                            <p>${description}</p>
                        </div>
                    </div>
                </c:when>
                
                <c:when test="${'vignette' == style}">
                    <!-- Visuel en vignette -->
                    <c:if test="${not empty visuel}">
                        <div class="thumbnail" style="background-image: url('${visuel}')"></div>
                    </c:if>
                    
                    <!-- Description -->
                    <div class="description">${description}</div>
                </c:when>
                
                <c:when test="${'arriere-plan' == style}">
                    <!-- Visuel en arrière plan -->
                    <div class="background" style="background-image: url('${visuel}')">
                        <div>
                            <!-- Description -->                         
                            <p>${description}</p>
                        </div>
                    </div>
                </c:when>
                
                <c:otherwise>                    
                    <div class="normal" style="background-image: url('${visuel}')">
                        <div>
                            <!-- Description -->                         
                            <p>${description}</p>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</li>
