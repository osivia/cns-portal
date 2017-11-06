<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.toutatice.fr/jsp/taglib/toutatice" prefix="ttc"%>

<%@ page isELIgnored="false"%>


<!-- Date -->
<c:set var="date" value="${document.properties['dc:issued']}" />
<c:if test="${empty date}">
    <c:set var="date" value="${document.properties['dc:modified']}" />
</c:if>
<c:if test="${empty date}">
    <c:set var="date" value="${document.properties['dc:created']}" />
</c:if>

<!-- Description -->
<c:set var="description" value="${document.properties['dc:description']}" />

<!-- Content -->
<c:set var="content"><ttc:transform document="${document}" property="webp:content" /></c:set>


<article class="blog-post">
    <!-- Title -->
    <h3>
        <span>${document.title}</span>
    </h3>
    
    <!-- Date -->
    <p class="text-muted">
        <span><fmt:formatDate value="${date}" type="date" dateStyle="long" /></span>
    </p>
    
    <!-- Description -->
    <c:if test="${not empty description}">
        <blockquote>
            <p>${description}</p>
        </blockquote>
    </c:if>
    
    <!-- Content -->
    <div class="clearfix">${content}</div>
</article>
