<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.ResourceURL"%>

<%@page import="org.nuxeo.ecm.automation.client.model.Document"%>
<%@page import="org.nuxeo.ecm.automation.client.model.PropertyMap"%>
<%@page import="org.nuxeo.ecm.automation.client.model.PropertyList"%>

<%@page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@page import="fr.toutatice.portail.cms.nuxeo.portlets.bridge.Formater"%>
<%@page import="fr.toutatice.portail.cms.nuxeo.portlets.customizer.CMSCustomizer"%>

<%@page import="org.osivia.portal.api.urls.Link"%>
<%@page import="org.osivia.portal.api.menubar.MenubarItem"%>

<portlet:defineObjects />

<%
NuxeoController ctx = (NuxeoController) renderRequest.getAttribute("ctx");
Document doc = (Document) renderRequest.getAttribute("doc");

List<MenubarItem> menuBar = (List<MenubarItem>) request.getAttribute("osivia.menuBar");
menuBar.add(new MenubarItem("DOWNLOAD", "Télécharger...", 20, ctx.createPictureLink(doc.getPath(), "Original"), null, "portlet-menuitem portlet-menuitem-download", ""));

String srcVignette = "";
PropertyList props = (PropertyList) doc.getProperties().get("picture:views");
Link link = ctx.getLink(doc);

if(!props.isEmpty())
	srcVignette = Formater.formatPictureLink(link, doc, "extra", doc.getTitle(), ctx.createPictureLink(doc.getPath(), "Medium"));
else
	srcVignette = "<div class=\"vignette-vide\"> </div>"; 	
%>
	
<div class="nuxeo-docview-normal-view">
	<div class="nuxeo-docview-description">
		<%=srcVignette%>
	</div>
</div>