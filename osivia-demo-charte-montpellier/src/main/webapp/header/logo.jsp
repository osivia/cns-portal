<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="internationalization" prefix="is" %>


<c:set var="logoAlt"><is:getProperty key="LOGO_ALT" /></c:set>


<h1>
    <a href="${requestScope['osivia.home.url']}">
        <img src="${pageContext.request.contextPath}/images/logo-ac-montpellier.css" alt="${logoAlt}">
    </a>
</h1>
