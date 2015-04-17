<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page isELIgnored="false"%>


<c:choose>
    <c:when test="${('osivia-demo-charte-cns' eq theme) or ('osivia-demo-charte-cns-wks' eq theme) or ('osivia-demo-charte-cns-userwks' eq theme)}">
        <jsp:include page="metadata-cns.jsp" />
    </c:when>
    
    <c:otherwise>
        <jsp:include page="metadata-default.jsp" />
    </c:otherwise>
</c:choose>
