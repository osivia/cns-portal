<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="internationalization" prefix="is" %>


<c:set var="appsLogout" value="${requestScope['osivia.demo.customizer.applications']}" />


<script type="text/javascript">
function callLogout() {
	$JQry('#msglogout').show();
	
	var delog = '';
	
	<c:forEach var="app" items="${appsLogout}" varStatus="status" >
		delog = delog + "<img src=\"${app}\" />" ;
	</c:forEach>
	
	$('deco').update(delog);
	window.setTimeout("portalLogout()",3000)
}

function portalLogout() {
	document.location="${requestScope['osivia.toolbar.signOutURL']}";
}

</script>



<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <!-- Toggle drawer menu button -->
            <button type="button" class="drawer-toggle-btn btn navbar-btn pull-left" data-toggle="drawer">
                <i class="glyphicons show_lines"></i>
                <span class="text">${requestScope['osivia.currentPageName']}</span>
            </button>
            
            <!-- Toggle navigation menu button -->
            <button type="button" class="btn navbar-btn visible-xs pull-left" data-toggle="tabs-menu">
                <i class="glyphicons halflings th"></i>
            </button>
        
            <!-- Brand -->
            <a class="navbar-brand hidden-xs" href="${requestScope['osivia.home.url']}"><is:getProperty key="BRAND" /></a>
          
            <!-- Toggle toolbar menu button -->
            <button type="button" class="btn navbar-btn visible-xs" data-toggle="collapse" data-target="#toolbar-content">
                <i class="glyphicons halflings cog"></i>
            </button>

            <!-- Search -->
            <form class="navbar-form visible-xs" onsubmit="return onsubmitGlobalSearch(this);" method="post" role="search">
                <div class="form-group">
                    <label class="sr-only" for="search-toolbar-input">Search</label>
                    <div class="input-group">
                        <input id="search-toolbar-input" type="text" name="keywords" class="form-control" placeholder='<is:getProperty key="SEARCH_PLACEHOLDER" />'>
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-default"><i class="glyphicons halflings search"></i></button>
                        </span>
                    </div>
                </div>
            </form>
        </div>

        <div id="toolbar-content" class="collapse navbar-collapse">
            <c:choose>
                <c:when test="${empty requestScope['osivia.toolbar.principal']}">
                    <ul class="nav navbar-nav navbar-right offline">
                        <!-- Login -->
                        <li>
                            <a href="${requestScope['osivia.toolbar.loginURL']}" class="navbar-link">
                                <i class="glyphicons halflings log_in"></i>
                                <is:getProperty key="LOGIN" />
                            </a>
                        </li>
                    </ul>
                </c:when>
        
                <c:otherwise>
                    <!-- Administration -->
                    <c:out value="${requestScope['osivia.toolbar.administrationContent']}" escapeXml="false" />
 
                    <!-- User links -->
                    <ul class="nav navbar-nav navbar-right">
                        <!-- User bar -->
						<li><a class="dropdown-toggle" href="#"
							data-toggle="dropdown"> <img class="avatar"
								src="<c:out value="${requestScope['osivia.toolbar.person'].avatar.url}"/>">
								<span><c:out value="${requestScope['osivia.toolbar.person'].displayName}"/></span> <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li role="presentation"><a role="menuitem"
									href="<c:out value="${requestScope['osivia.toolbar.myprofile']}"/>">
										<i class="glyphicons halflings user"></i> <span><is:getProperty key="MY_PROFILE" /></span>
								</a></li>
								<li role="presentation"><a role="menuitem"
									href="#" onclick="callLogout();">
										<i class="glyphicons halflings log_out"></i> <span><is:getProperty key="LOGOUT" /></span>
								</a></li>
							</ul></li>

						<li><a
							href="${requestScope['osivia.toolbar.refreshPageURL']}"
							class="navbar-link"> <i class="glyphicons halflings refresh"></i>
								<is:getProperty key="REFRESH_PAGE" />
						</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>



<div id="deco"></div>

<div id="msglogout">
	<p><is:getProperty key="LOGOUT_MESSAGE" /></p>
</div>
