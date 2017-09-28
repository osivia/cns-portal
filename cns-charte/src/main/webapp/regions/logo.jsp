<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>


<c:set var="logoAlt"><op:translate key="LOGO_ALT"/> </c:set>


<c:choose>
	<c:when test="${not empty requestScope['osivia.toolbar.mySpaceURL']}">
		<c:set var="logoURL" value="${requestScope['osivia.toolbar.mySpaceURL']}" />
	</c:when>
	<c:otherwise>
		<c:set var="logoURL" value="#" />
	</c:otherwise>
</c:choose>


<div class="pull-left">
    <h1 class="h3">
        <a href="${logoURL}">
            <img src="${pageContext.request.contextPath}/img/logo_MENESR.png" alt="${logoAlt}">
            <span><op:translate key="PORTAL_TITLE"/></span>
        </a>
    </h1>
</div>
