<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="internationalization" prefix="is"%>
<%@ taglib uri="toutatice" prefix="ttc"%>

<%@ page isELIgnored="false"%>


<div class="tiles">
    <ul class="list-unstyled">
        <c:forEach var="document" items="${documents}">
            <!-- Document properties -->
            <ttc:documentLink document="${document}" var="link" />
            <c:remove var="target" />
            <c:if test="${link.external}">
                <c:set var="target" value="_blank" />
            </c:if>
            <c:set var="color" value="${document.properties['zoom:color']}" />
            <c:set var="style" value="${document.properties['zoom:style']}" />
            <c:set var="description" value="${document.properties['dc:description']}" />
            
        
            <li>
                <div class="col-xs-6 col-sm-12 col-lg-6">
                    <a href="${link.url}" target="${target}" class="thumbnail tile tile-${color}">
                        <span class="text-middle">
                            <i class="glyphicons ${style}"></i>
                            <span>${description}</span>
                        </span>
                    </a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
