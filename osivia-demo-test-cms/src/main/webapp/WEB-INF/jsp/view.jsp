

<%@page import="org.nuxeo.ecm.automation.client.model.Documents"%>
<%@ page contentType="text/plain; charset=UTF-8"%>


<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="javax.portlet.PortletURL"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.ResourceURL"%>
<%@page import="org.nuxeo.ecm.automation.client.model.Document"%>
<%@page import="org.nuxeo.ecm.automation.client.model.PropertyList"%>
<%@page import="fr.toutatice.portail.cms.nuxeo.api.NuxeoController"%>


<portlet:defineObjects />


<%
String note = (String) renderRequest.getAttribute("note");
String closePopupURL = (String) renderRequest.getAttribute("closePopup");
String openPopupURL = (String) renderRequest.getAttribute("openPopup");
%>

			
<div class="nuxeo-test">		
	<div class="nuxeo-docview-note">			
		<%= note %>
	</div>
	
	 <div class="nuxeo-docview-close">            
        <a href="<%= closePopupURL %>">close</a>
    </div>
    
         <div class="nuxeo-docview-open">            
        <a class="fancyframe_refresh " href="<%= openPopupURL %>">open</a>
    </div>
    
    
    
    <form method="post" action="<portlet:actionURL/>">
        <input type="submit" name="close"  value="close popup"> <br/>
    </form>
    

    
    
    
    

</div>


