<%@page import="org.osivia.portal.api.urls.Link"%>
<%@page import="org.osivia.portal.api.urls.IPortalUrlFactory"%>
<%@ page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.Document"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="internationalization" prefix="is" %>


<%@ page isELIgnored="false" %>


<%
// Nuxeo controller
NuxeoController nuxeoController = (NuxeoController) request.getAttribute("ctx");
// Nuxeo document
Document document = (Document) request.getAttribute("doc");

// Title
pageContext.setAttribute("title", document.getTitle());
// Link

String contentUrl = nuxeoController.getPortalUrlFactory().getPermaLink(nuxeoController.getPortalCtx(), null,null, document.getPath(), IPortalUrlFactory.PERM_LINK_TYPE_CMS);
Link link = new Link(contentUrl, true);

pageContext.setAttribute("link",link );



// Vignette
if ((document.getProperties().getMap("ttc:vignette") != null) && (document.getProperties().getMap("ttc:vignette").getString("data") != null)) {
    
	String baseUrl = "http://" + request.getServerName();
	if( request.getServerPort() != 80)
	    baseUrl +=  ":" + request.getServerPort();
	
    pageContext.setAttribute("vignette",baseUrl + nuxeoController.createFileLink(document, "ttc:vignette"));
}

%>



<c:if test="${link.external}">
    <c:set var="target" value="_blank" />
</c:if>


<tr><td>

    <a href="${link.url}" target="${target}">
        <!-- Vignette -->
        <c:if test="${not empty vignette}">
            <img src="${vignette}" alt="" class="vignette pull-left" />
        </c:if>
     ${title} </a> <br/>
</td></tr>