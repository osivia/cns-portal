<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="internationalization" prefix="is" %>


<c:set var="userPages" value="${requestScope['osivia.userPortal'].userPages}" />


<div class="dynamic-pages">
    <c:forEach var="userPage" items="${userPages}">
        <c:if test="${not empty userPage.closePageUrl}">
            <c:if test="${userPage.id == requestScope['osivia.currentPageId']}">
                <c:set var="className" value="current" />
            </c:if>
                
            <div class="dynamic-page ${className}">
                <a href="${userPage.url}">${userPage.name}</a>
                <a class="close" href="${userPage.closePageUrl}" title="<is:getProperty key="CLOSE" />"></a>
            </div>
            
            <c:remove var="className" />
        </c:if>
    </c:forEach>
</div>


<ul class="nav">
    <c:forEach var="userPage" items="${userPages}" varStatus="status" >
        <c:if test="${empty userPage.closePageUrl}">
            <c:if test="${'templates' != fn:toLowerCase(userPage.name)}">            
                <c:if test="${status.count == 1}">
                    <c:set var="className" value="first" />
                </c:if>
                <c:if test="${status.count == fn:length(userPages)}">
                    <c:set var="className" value="last" />
                </c:if>
                <c:if test="${userPage.id == requestScope['osivia.currentPageId']}">
                    <c:set var="className" value="${className} current" />
                </c:if>
                
                <li class="${className}">
                    <a href="${userPage.url}">${userPage.name}</a>
                    
                    <c:if test="${not empty userPage.children}">
                        <ul class="sub-menu">
                            <c:forEach var="child" items="${userPage.children}">
                                <li>
                                    <a href="${child.url}">${child.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
                
                <c:remove var="className" />
            </c:if>
        </c:if>
    </c:forEach>

    <!-- Prevent empty tabs bar -->
    <li>&nbsp;</li>
</ul>
