<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="internationalization" prefix="is"%>
<%@ taglib uri="toutatice" prefix="ttc"%>

<%@ page isELIgnored="false"%>


<ul class="list-group">
    <c:forEach var="document" items="${documents}">
        <!-- Document properties -->
        <c:set var="url"><ttc:documentLink document="${document}" /></c:set>
        <c:set var="date" value="${document.properties['dc:modified']}" />
        <c:if test="${empty date}">
            <c:set var="date" value="${document.properties['dc:created']}" />
        </c:if>
        <c:set var="description" value="${document.properties['dc:description']}" />
        <c:set var="content"><ttc:transform document="${document}" property="webp:content" /></c:set>
    
    
        <li class="list-group-item blog-post">
            <!-- Date -->
            <p><fmt:formatDate value="${date}" dateStyle="long"/></p>
            
            <!-- Title -->
            <h3>
                <a href="${url}">${document.title}</a>
            </h3>
            
            <!-- Description -->
            <p class="lead">${description}</p>
            
            <!-- Content -->
            <div>${content}</div>      
        </li>
    </c:forEach>
</ul>
