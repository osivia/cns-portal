<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="internationalization" prefix="is" %>


<c:set var="logoAlt"><is:getProperty key="LOGO_ALT" /></c:set>


<div class="pull-left">
    <h1 class="h3">
        <a href="${requestScope['osivia.home.url']}">
            <img src="${pageContext.request.contextPath}/img/logo.png" alt="${logoAlt}">
            <span><is:getProperty key="PORTAL_TITLE" /></span>
        </a>
    </h1>
</div>
