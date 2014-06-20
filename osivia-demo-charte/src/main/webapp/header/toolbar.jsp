<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


<div class="toolbar-content">
    <div class="toolbar-fixed-content">
        <c:choose>
            <c:when test="${empty requestScope['osivia.toolbar.principal']}">
                <!-- User bar -->
                <c:out value="${requestScope['osivia.toolbar.userContent']}" escapeXml="false" />
                
            </c:when>
    
            <c:otherwise>
                <!-- Administration -->
                <c:out value="${requestScope['osivia.toolbar.administrationContent']}" escapeXml="false" />
                
                <!-- User bar -->
                <c:out value="${requestScope['osivia.toolbar.userContent']}" escapeXml="false" />
        			
                <!-- Refresh page -->
                <a id="refresh-page" href="${requestScope['osivia.toolbar.refreshPageURL']}" title='<is:getProperty key="REFRESH_PAGE" />'>&nbsp;</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div id="deco" style="display:inline"></div>

<div id="msglogout">
	<p><is:getProperty key="LOGOUT_MESSAGE" /></p>
</div>
