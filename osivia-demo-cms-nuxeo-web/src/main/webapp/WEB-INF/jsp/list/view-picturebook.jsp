<%@ page contentType="text/plain; charset=UTF-8"%>

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Vector"%>

<%@page import="org.osivia.portal.api.urls.Link"%>
<%@page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>
<%@page import="fr.toutatice.portail.cms.nuxeo.portlets.bridge.Formater"%>


<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.ResourceURL"%>
<%@page import="org.nuxeo.ecm.automation.client.model.Document"%>
<%@page import="org.nuxeo.ecm.automation.client.model.PropertyMap"%>
<%@page import="org.nuxeo.ecm.automation.client.model.PropertyList"%>
<%@page import="fr.toutatice.portail.cms.nuxeo.api.VocabularyHelper"%>
<%@page import="fr.toutatice.portail.cms.nuxeo.portlets.customizer.CMSCustomizer"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>


<portlet:defineObjects />
<%!
String getLabelsNewFashionMonovalue(NuxeoController ctx, Document doc, String ns, String voc)
{
	String label = "";
	String key = doc.getProperties().getString(ns); 

	if(key != null)
	{
		int i = 0;
		int v = 0;
		Vector vocVector = new Vector();
		
		try{
			StringTokenizer stvoc = new StringTokenizer(voc, "/"); //disciplines_parent/disciplines
			while(stvoc.hasMoreElements())
			{
				vocVector.addElement(stvoc.nextToken()); // = VocabularyHelper.getVocabularyLabel(ctx, voc, stvoc.nextToken());
			}
			
			StringTokenizer st = new StringTokenizer(key, "/");
			String label2 = "";
			String label1 = "";
			v = 0;
			while(st.hasMoreElements())
			{
				label2 = VocabularyHelper.getVocabularyLabel(ctx, (String)vocVector.get(v), st.nextToken());
				label1 += label2; 
				if(st.hasMoreElements()) label1 += "/";
				v++;
			}
			label += label1;
		}
		catch(Exception e){e.printStackTrace();}
	}

	return label;
}
%>
<%
NuxeoController ctx = (NuxeoController) renderRequest.getAttribute("ctx")	;
Document doc = (Document) renderRequest.getAttribute("doc");
int parite = (Integer) renderRequest.getAttribute("parite");
Link link = ctx.getLink(doc);
if( doc.getType().equalsIgnoreCase("ContextualLink") )
	link = ctx.getLink(doc,"detailedView");
String url = link.getUrl();

String target = Formater.formatTarget(link);
String srcVignette = "";

if(!doc.getType().equalsIgnoreCase("picture"))
{
	%>
	 <img src="/toutatice-portail-cms-nuxeo/img/workspace.gif"><a href="<%=link.getUrl()%>"><%=doc.getTitle()%></a>
	<%
}
else
{
	PropertyList props = (PropertyList) doc.getProperties().get("picture:views");
	
	if(!props.isEmpty())
		srcVignette = Formater.formatPictureLink(link, doc, "extra", doc.getTitle(), ctx.createPictureLink(doc.getPath(), "Thumbnail"));
	else
		srcVignette = "<div class=\"vignette-vide\"> </div>"; 	
	
	String date = "";
	date = Formater.formatDate( doc);
	String label = getLabelsNewFashionMonovalue(ctx, doc, "acr:publisher", "sourcesOrganisationnelles/sourcesOrganisationnelles_L2");
	if (label == null)
	{
		label = "Académie de Rennes";
	}
	else if (label.trim().length() == 0)
	{
		label = "Académie de Rennes";
	}
	else if (label.equalsIgnoreCase("null"))
	{
		label = "Académie de Rennes";
	}
	
	String creator = Formater.formatCreator(doc);
	
	String icon = Formater.formatNuxeoIcon(doc);
	icon = "<img class=\"icon-droite\" src=\""+renderRequest.getContextPath()+icon+"\">";
	icon = "";
	%>
	<li class="picture-item">
		<%=srcVignette%>
		<!--
		<%= Formater.formatLink(link, doc) %>
		
		<div class="info-detail">
			par <%=creator%> | source <%=label%> | publié le <%=date%>
		</div>
		<p class="description"><%= Formater.formatDescription(doc)%></p>
		-->
		<div class="separateur"></div>
	</li>
<%
}%>

