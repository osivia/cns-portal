<%@page import="java.util.List"%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.osivia.portal.api.urls.IPortalUrlFactory"%>
<%@ page import="java.util.Iterator" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.osivia.portal.api.theming.UserPage" %>
<%@ page import="org.osivia.portal.api.theming.UserPortal" %>
<%@ page import="org.osivia.portal.api.theming.Breadcrumb"%>
<%@page import="org.osivia.portal.api.theming.BreadcrumbItem"%>
<%@page import="org.osivia.portal.api.context.PortalControllerContext"%>
<%@page import="org.osivia.portal.api.Constants"%>

<%
UserPortal userPortal = (UserPortal) request.getAttribute(Constants.ATTR_USER_PORTAL);
Object currentPageId = request.getAttribute(Constants.ATTR_PAGE_ID);

//Get current tab

int currentTab = -1;
int iTab = 0;
Iterator<UserPage> tabPages = userPortal.getUserPages().iterator();
while (tabPages.hasNext())	{
	UserPage userPage = tabPages.next();
	if (userPage.getId().equals( currentPageId))
		currentTab = iTab;
	
	iTab ++;	
}

%>
<ul class="nav">
<%

int indexPage = 0;
int nbPages = userPortal.getUserPages().size();
Iterator<UserPage> pages = userPortal.getUserPages().iterator();

while (pages.hasNext()){  
	UserPage userPage = pages.next();
	if(!"accueil".equalsIgnoreCase(userPage.getName())
			&& !"templates".equalsIgnoreCase(userPage.getName())){
		String className = "";
		if(indexPage == 0){
			className = "first";
		}
		if(indexPage == nbPages){
			className = "last";
		}
		if(indexPage == currentTab){
			className += " current";
		}
	
%>
	<li class="<%= className %>">
		<a href="<%= userPage.getUrl() %>"><span><%= userPage.getName() %></span></a>
		<% if (userPage.getChildren().size() > 0){ %>
			 <ul class="subMenuHidden" onmouseover="this.class='subMenuShow'">
	         <%
	            for (Iterator<UserPage> j = userPage.getChildren().iterator(); j.hasNext();){
	            	UserPage userSubPage = j.next();
	         %>
	         <li><a href="<%= userSubPage.getUrl() %>"><span><%= userSubPage.getName() %></span>
	         </a></li>
	         <%
            }
         %>
      </ul>
		<% } %>
	</li>
                           
<% 		
	}
	indexPage++;  
 } 
%>
</ul>
