<%@page import="java.util.List"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>

<%@page import="org.osivia.portal.api.Constants"%>
<%@page import="org.osivia.portal.api.theming.Breadcrumb"%>
<%@page import="org.osivia.portal.api.theming.BreadcrumbItem"%>

<%
Breadcrumb breadcrumb = (Breadcrumb) request.getAttribute(Constants.ATTR_BREADCRUMB);
%>


<ul >
<%
if( breadcrumb != null)	{
	for (BreadcrumbItem bi : breadcrumb.getChilds())	{
%>
		<li>
			<a href="<%= bi.getUrl() %>" ><%= bi.getName() %> </a>
		</li>
<%
	}
}
%>

</ul>

