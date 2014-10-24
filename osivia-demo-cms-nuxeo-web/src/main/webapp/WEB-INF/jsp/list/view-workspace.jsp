<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="internationalization" prefix="is"%>
<%@ taglib uri="toutatice" prefix="ttc"%>

<%@ page isELIgnored="false"%>


<c:set var="workspaceLabel"><is:getProperty key="WORKSPACE" /></c:set>


<ul class="list-unstyled">
    <c:forEach var="document" items="${documents}">
        <!-- Document properties -->
        <c:set var="url"><ttc:documentLink document="${document}" /></c:set>


        <li>
            <p>
                <img alt="${workspaceLabel}" src="${pageContext.request.contextPath}/img/workspace.gif">
                <a href="${url}">${document.title}</a>
            </p>
        </li>        
    </c:forEach>
</ul>
