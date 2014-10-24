<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="internationalization" prefix="is"%>
<%@ taglib uri="toutatice" prefix="ttc"%>

<%@ page isELIgnored="false"%>


<table class="table">
    <tbody>
        <c:forEach var="document" items="${documents}">
            <!-- Document properties -->
            <ttc:documentLink document="${document}" var="link" />
            <c:if test="${link.external}">
                <c:set var="target" value="_blank" />
            </c:if>
            <c:set var="vignetteURL"><ttc:getImageURL document="${document}" property="ttc:vignette" /></c:set>
        
            
            <tr>
                <td>
                    <a href="${link.url}" target="${target}">
                        <!-- Vignette -->
                        <c:if test="${not empty vignetteURL}">
                            <img src="${vignetteURL}" alt="" class="pull-left" />
                        </c:if>
                        
                        <!-- Title -->
                        <span class="h3">${document.title}</span>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
