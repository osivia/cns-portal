<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="internationalization" prefix="is" %>


<c:set var="appsLogout" value="${requestScope['osivia.demo.customizer.applications']}" />
<c:set var="searchPlaceholder"><is:getProperty key="SEARCH_PLACEHOLDER" /></c:set>
<c:set var="logoutMessage"><is:getProperty key="LOGOUT_MESSAGE" /></c:set>


<script type="text/javascript">
var $JQry = jQuery.noConflict();


// Popover
$JQry("#logout-popover").popover({
    container : "body"
});


function callLogout() {
    var $popover = $JQry("#logout-popover");
    
    // Disabled dropdown menu
    var $menu = $popover.parent();
    $menu.addClass("disabled");
    $menu.find("[data-toggle=dropdown]").addClass("disabled");
    
    // Popover
    $popover.popover("show");
    
    var delog = "";

    <c:forEach var="app" items="${appsLogout}" varStatus="status" >
        delog += "<img src='${app}' />";
    </c:forEach>

    $("disconnection").update(delog);
    window.setTimeout("portalLogout()", 3000)
}

function portalLogout() {
    document.location = "${requestScope['osivia.toolbar.signOutURL']}";
}

</script>


<div class="toolbar">
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
                <button type="button" class="btn navbar-btn visible-xs pull-right" data-toggle="collapse" data-target="#toolbar-content">
                    <i class="glyphicons halflings cog"></i>
                </button>
    
                <!-- Search -->
                <form class="navbar-form visible-xs" onsubmit="return onsubmitGlobalSearch(this);" method="post" role="search">
                    <div class="form-group">
                        <label class="sr-only" for="search-toolbar-input">Search</label>
                        <div class="input-group">
                            <input id="search-toolbar-input" type="text" name="keywords" class="form-control" placeholder="${searchPlaceholder}" />
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">
                                    <i class="glyphicons halflings search"></i>
                                </button>
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
                                    <span><is:getProperty key="LOGIN" /></span>
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
                            <li>
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img class="avatar" src="${requestScope['osivia.toolbar.person'].avatar.url}" />
                                    <span>${requestScope['osivia.toolbar.person'].displayName}</span>
                                    <span class="caret"></span>
                                </a>
                                
                                <div id="logout-popover" data-content="${logoutMessage}" data-placement="bottom"></div>
                                
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation">
                                        <a role="menuitem" href="${requestScope['osivia.toolbar.myprofile']}">
                                            <i class="glyphicons halflings user"></i>
                                            <span><is:getProperty key="MY_PROFILE" /></span>
                                        </a>
                                    </li>
                                    <li role="presentation">
                                        <a role="menuitem" href="#" onclick="callLogout()">
                                            <i class="glyphicons halflings log_out"></i>
                                            <span><is:getProperty key="LOGOUT" /></span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
    
                            <!-- Refresh -->
                            <li>
                                <a href="${requestScope['osivia.toolbar.refreshPageURL']}" class="navbar-link">
                                    <i class="glyphicons halflings refresh"></i>
                                    <span><is:getProperty key="REFRESH_PAGE" /></span>
                                </a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>


<!-- Technical div for applications disconnection -->
<div id="disconnection" class="hidden"></div>
