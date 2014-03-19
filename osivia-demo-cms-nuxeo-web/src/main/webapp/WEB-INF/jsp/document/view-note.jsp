<%@page import="org.nuxeo.ecm.automation.client.model.Document"%>

<%@ page isELIgnored="false" %>


<%
// Current Nuxeo document
Document document = (Document) request.getAttribute("doc");


// Description
pageContext.setAttribute("description", document.getString("dc:description"));
// Note content
pageContext.setAttribute("note", document.getString("note:note"));

%>


<div class="nuxeo-docview-note">
    <p class="description">${description}</p>
    <p>${note}</p>
</div>
