<%@page import="org.osivia.portal.api.Constants"%>
<%@page import="java.util.List"%>
<%@page import="org.osivia.portal.api.contexte.PortalControllerContext"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.osivia.portal.api.urls.IPortalUrlFactory"%>
<%@ page import="java.util.Iterator" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.osivia.portal.api.charte.UserPage" %>
<%@ page import="org.osivia.portal.api.charte.UserPortal" %>
<%@ page import="org.osivia.portal.api.charte.Breadcrumb"%>
<%@page import="org.osivia.portal.api.charte.BreadcrumbItem"%>

<%
UserPortal userPortal = (UserPortal) request.getAttribute(Constants.ATTR_USER_PORTAL);
Object currentPageId = request.getAttribute(Constants.ATTR_PAGE_ID);
  
%>



<div id="dynamicTab">
</div>



<ul id="hiddenTabs">
   <%
   Iterator<UserPage> hiddenPages = userPortal.getUserPages().iterator();
   
   while (hiddenPages.hasNext())
      {
	   UserPage userPage = hiddenPages.next();
	   
	   if( userPage.getClosePageUrl() == null)	{

    %>
   <li  onmouseover="this.className='hoverOn'"
                                                                    onmouseout="this.className='hoverOff'"><!-- <div class="maxWidth">--><a
      href="<%= userPage.getUrl() %>&firstTab=0"><span><%= userPage.getName() %></span>
      
      <%
      if (userPage.getChildren().size() == 0)
      {
   %></a><!-- </div> -->
   
   <%
   if( userPage.getClosePageUrl() != null)	{ %>
   
   <div class="close"> <a href="<%=  userPage.getClosePageUrl()%>"></a></div>
   
   <% } %>

      <%
      }
      else
      {
      %>
      <!--[if IE 7]><!--></div></a><!--<![endif]-->
      <!--[if lte IE 6]>&nbsp;&nbsp;&nbsp;<table><tr><td><![endif]-->
      <ul>
         <%
            for (Iterator<UserPage> j = userPage.getChildren().iterator(); j.hasNext();)
            {
            	UserPage userSubPage = j.next();
         %>
         <li><a href="<%= userSubPage.getUrl() %>&firstTab=0"><span><%= userSubPage.getName() %></span>
         </a></li>
         <%
            }
         %>
      </ul>
      <!--[if lte IE 6]></td></tr></table></div></a><![endif]-->
      <%
         }
      %>
   </li>
   <%
	     }
      }

   %>
</ul>






<%

//Get first tab

Integer firstTab = (Integer) request.getAttribute(Constants.ATTR_FIRST_TAB);

if( firstTab == null)
	firstTab = new Integer(0);

// To avoid first tab displaying in menu tabs, i.e. "Accueil" tab for Osivia site (by convention, ordered index = 0)
if(Integer.valueOf(0).equals(firstTab)){
	firstTab = 1;
}

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
//Boolean added to initTab exclude or not first page, i.e. "Accueil" tab for Osivia site (by convention, ordered index = 0)
%>

<script type="text/javascript">
	initTab( <%=firstTab.intValue()%>, <%=currentTab %>, 8 , true)	;
</script> 

