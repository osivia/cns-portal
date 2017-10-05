<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>
<%@ taglib uri="http://www.toutatice.fr/jsp/taglib/toutatice" prefix="ttc" %>

<%@ page isELIgnored="false" %>


<div class="row">
    <c:forEach var="document" items="${documents}" varStatus="status">
        <c:set var="vignetteUrl"><ttc:pictureLink document="${document}" property="ttc:vignette" /></c:set>
        <c:set var="description" value="${document.properties['dc:description']}" />
        <c:set var="workspaceType" value="${document.properties['workspaceType']}" />
        <c:set var="memberStatus" value="${document.properties['memberStatus']}" />
        
        <portlet:actionURL name="createRequest" var="createRequestUrl">
            <portlet:param name="id" value="${document.properties['webc:url']}" />
        </portlet:actionURL>
        
        
        <div class="col-sm-6 col-md-4 col-lg-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <!-- Vignette -->
                    <c:if test="${not empty vignetteUrl}">
                        <p>
                            <img src="${vignetteUrl}" alt="" class="media-object">
                        </p>
                    </c:if>
                    
                    <!-- Title -->
                    <h3 class="h4">
                        <ttc:title document="${document}" linkable="${(workspaceType.id eq 'PUBLIC')}" />
                    </h3>
                    
                    <!-- Type -->
                    <c:if test="${not empty workspaceType}">
                        <p>
                            <span class="label label-${workspaceType.color}">
                                <i class="${workspaceType.icon}"></i>
                                <span><op:translate key="LIST_TEMPLATE_${workspaceType.key}" /></span>
                            </span>
                        </p>
                    </c:if>
                    
                    <!-- Action -->
                    <div>
                        <c:choose>
                            <c:when test="${empty memberStatus}">
                                <p>
                                    <a href="${createRequestUrl}" class="btn btn-default btn-sm">
                                        <span><op:translate key="LIST_TEMPLATE_WORKSPACE_MEMBER_REQUESTS_CREATION" /></span>
                                    </a>
                                </p>
                            </c:when>
                            
                            <c:otherwise>
                                <p class="text-${memberStatus.color}">
                                    <i class="${memberStatus.icon}"></i>
                                    <span><op:translate key="${memberStatus.key}" /></span>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    
                    <!-- Description -->
                    <c:if test="${not empty description}">
                        <p class="text-pre-wrap">${description}</p>
                    </c:if>
                </div>
            </div>
        </div>
   </c:forEach>
</div>


<c:if test="${empty documents}">
    <p class="text-center">
        <span class="text-muted"><op:translate key="LIST_NO_ITEMS" /></span>
    </p>
</c:if>
