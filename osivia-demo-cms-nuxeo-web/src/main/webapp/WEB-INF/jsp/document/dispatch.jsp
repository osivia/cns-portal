<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="toutatice" prefix="ttc" %>

<%@ page isELIgnored="false"%>


<c:set var="type" value="${document.type.name}" />



<c:choose>
    <c:when test="${'Annonce' eq type}">
        <ttc:custom-include page="view-annonce.jsp" />
    </c:when>
    
    <c:when test="${('File' eq type) or ('Audio' eq type) or ('Video' eq type)}">
        <ttc:custom-include page="view-file.jsp" />
    </c:when>
    
    <c:when test="${'Note' eq type}">
    	<ttc:custom-include page="view-note.jsp" />
    </c:when>
        
    <c:when test="${'BlogPost' eq type}">
        <ttc:custom-include page="view-blog-post.jsp" />
    </c:when>
    
    <c:when test="${'Picture' eq type}">
        <ttc:custom-include page="view-picture.jsp" />
    </c:when>
    
    <c:when test="${'VEVENT' eq type}">
        <ttc:custom-include page="view-calendar-event.jsp" />
    </c:when>    
        
    <c:otherwise>
        <ttc:custom-include page="view-default.jsp" />
    </c:otherwise>
</c:choose>
