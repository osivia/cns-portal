<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="internationalization" prefix="is" %>


<%@ page isELIgnored="false" %>


<portlet:defineObjects />


<table>
      <c:forEach var="doc" items="${docs}" varStatus="status">
           <c:set var="doc" value="${doc}" scope="request" />
           <c:set var="status" value="${status}" scope="request" />
           <jsp:include page="view-${fn:toLowerCase(style)}-newsletter.jsp" />
       </c:forEach>
</table>
