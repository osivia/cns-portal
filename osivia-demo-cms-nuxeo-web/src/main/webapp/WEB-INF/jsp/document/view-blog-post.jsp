<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib uri="toutatice" prefix="ttc" %>

<%@ page isELIgnored="false"%>


<c:set var="date" value="${document.properties['dc:created']}" />
<c:set var="description" value="${document.properties['dc:description']}" />
<c:set var="content"><ttc:transform document="${document}" property="webp:content" /></c:set>


<article class="blog-post">
    <!-- Date -->
    <p><fmt:formatDate value="${date}" type="date" dateStyle="long" /></p>
    
    <!-- Title -->
    <h3>${document.title}</h3>
    
    <!-- Description -->
    <p class="lead">${description}</p>
    
    <!-- Content -->
    <div>${content}</div>
</article>
