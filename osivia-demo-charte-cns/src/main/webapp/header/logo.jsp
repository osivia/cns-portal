<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="internationalization" prefix="is" %>


<c:set var="logoAlt"><is:getProperty key="LOGO_ALT" /></c:set>


<div class="pull-left">
    <h1>
        <a href="${requestScope['osivia.home.url']}">
            <img src="${pageContext.request.contextPath}/img/logo_MENESR_324357.jpg" alt="${logoAlt}">
        </a>
        <small><is:getProperty key="PORTAL_TITLE" /></small>
    </h1>
 </div>
