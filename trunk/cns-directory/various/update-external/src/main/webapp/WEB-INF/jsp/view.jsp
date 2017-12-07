<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>

<%@ page contentType="text/html" isELIgnored="false" %>


<portlet:actionURL var="url" name="start" />


<c:if test="${updatedPersons.started}">
    <div class="alert alert-info"><op:translate key="MESSAGE_UPDATE" args="${fn:length(updatedPersons.list)}" /></div>
</c:if>


<c:if test="${not empty updatedPersons.list}">
    <ul class="list-unstyled">
        <c:forEach var="person" items="${updatedPersons.list}">
            <c:choose>
                <c:when test="${person.external}">
                    <c:set var="icon" value="glyphicons glyphicons-ok" />
                    <c:set var="color" value="success" />
                </c:when>
                
                <c:otherwise>
                    <c:set var="icon" value="glyphicons glyphicons-remove" />
                    <c:set var="color" value="danger" />
                </c:otherwise>
            </c:choose>
        
            <li>
                <p class="text-${color}">
                    <i class="${icon}"></i>
                    
                    <c:choose>
                        <c:when test="${empty person.displayName}">
                            <span>${person.uid}</span>
                        </c:when>
                        
                        <c:otherwise>
                            <span>${person.displayName}</span>
                            <small>(${person.uid})</small>
                        </c:otherwise>
                    </c:choose>
                </p>
            </li>
        </c:forEach>
    </ul>
</c:if>


<div>
    <a href="${url}" class="btn btn-default">
        <i class="glyphicons glyphicons-play"></i>
        <span><op:translate key="START_UPDATE" /></span>
    </a>
</div>
