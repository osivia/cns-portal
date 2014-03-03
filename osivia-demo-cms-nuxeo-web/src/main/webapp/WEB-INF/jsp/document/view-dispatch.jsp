<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page isELIgnored="false" %>


<c:set var="type" value="${fn:toLowerCase(doc.type)}" />

<c:choose>
    <c:when test='${"article" == type}'>
        <jsp:include page="view-annonce.jsp" />
    </c:when>
    
    <c:when test='${"contextuallink" == type}'>
        <jsp:include page="view-contextuallink.jsp" />
    </c:when>
    
    <c:when test='${"file" == type}'>
        <jsp:include page="view-file.jsp" />
    </c:when>
    
    <c:when test='${"note" == type}'>
        <jsp:include page="view-note.jsp" />
    </c:when>
    
    <c:when test='${"blogpost" == type}'>
        <jsp:include page="view-blog-post.jsp" />
    </c:when>
        
    <c:otherwise>
        <jsp:include page="view-default.jsp" />
    </c:otherwise>
</c:choose>
