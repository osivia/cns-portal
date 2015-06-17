<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="toutatice" prefix="ttc"%>

<%@ page isELIgnored="false"%>


<article class="calendar-event">
    <dl class="dl-horizontal">
        <!-- Date de début -->
        <c:set var="begin" value="${document.properties['vevent:dtstart']}" />
        <dt>D&eacute;but</dt>
        <dd>
            <span><fmt:formatDate value="${begin}" type="date" dateStyle="long" /></span>
            
            <c:if test="${not document.properties['vevent:allDay']}">
                <span>&agrave;</span>
                <span><fmt:formatDate value="${begin}" type="time" timeStyle="short" /></span>
            </c:if>
        </dd>
        
        <!-- Date de fin -->
        <c:set var="end" value="${document.properties['vevent:dtend']}" />
        <dt>Fin</dt>
        <dd>
            <span><fmt:formatDate value="${end}" type="date" dateStyle="long" /></span>
            
            <c:if test="${not document.properties['vevent:allDay']}">
                <span>&agrave;</span>
                <span><fmt:formatDate value="${end}" type="time" timeStyle="short" /></span>
            </c:if>
        </dd>
        
        <!-- Lieu -->
        <c:set var="location" value="${document.properties['vevent:location']}" />
        <c:if test="${not empty location}">
            <dt>Lieu</dt>
            <dd>${location}</dd>
        </c:if>
        
        <!-- Description -->
        <c:set var="description" value="${document.properties['dc:description']}" />
        <c:if test="${not empty description}">
            <dt>Description</dt>
            <dd>${description}</dd>
        </c:if>
        
        <!-- Note -->
        <c:set var="note"><ttc:transform document="${document}" property="note:note" /></c:set>
        <c:if test="${not empty note}">
            <dt>Objet</dt>
            <dd>${note}</dd>
        </c:if>
    </dl>
</article>
