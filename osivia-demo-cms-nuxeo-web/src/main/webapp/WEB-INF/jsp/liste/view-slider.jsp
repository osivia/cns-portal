<%@ page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@ page import="org.nuxeo.ecm.automation.client.model.Document"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
// Extrait
pageContext.setAttribute("extrait", document.getProperties().getString("dc:description"));
// Image
if ((document.getProperties().getMap("annonce:image") != null) && (document.getProperties().getMap("annonce:image").getString("data") != null)) {
    pageContext.setAttribute("image", nuxeoController.createFileLink(document, "annonce:image"));
}

%>


<portlet:defineObjects />


<li class="slide">
    <c:if test="${not empty image}">
        <img src="${image}" />
    </c:if>

    <a href="${link.url}" class="title">${title}</a>
    <div u="caption" class="caption" t="L" style="position: absolute; top: 100px; left: 80px; width: 320px; height: 30px;">${extrait}</div>

    <script type="text/javascript">
    loadSlide();
    </script>
</li>
