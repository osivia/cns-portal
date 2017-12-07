<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>
<%@ taglib uri="http://www.toutatice.fr/jsp/taglib/toutatice" prefix="ttc"%>

<%@ page isELIgnored="false" %>


<ul class="list-group blog-post">
    <c:forEach var="document" items="${documents}" varStatus="status">
        <!-- Document properties -->

        <!-- Link -->
        <ttc:documentLink document="${document}" var="link" />

        <!-- Date -->
        <c:set var="date" value="${document.properties['dc:issued']}" />
        <c:if test="${empty date}">
            <c:set var="date" value="${document.properties['dc:modified']}" />
        </c:if>
        <c:if test="${empty date}">
            <c:set var="date" value="${document.properties['dc:created']}" />
        </c:if>
        
        <!-- Description -->
        <c:set var="description" value="${document.properties['dc:description']}" />
        
        <!-- Content -->
        <c:set var="content"><ttc:transform document="${document}" property="webp:content" /></c:set>
        
        <!-- Author -->
        <c:set var="author" value="${document.properties['dc:creator']}" />



        <li class="list-group-item">
            <article class="blog-post">
                <!-- Title -->
                <h3 class="list-group-item-heading">
                    <ttc:title document="${document}" icon="true" />
    
                    <!-- Date -->
                    <small>&nbsp;<fmt:formatDate value="${date}" type="date" dateStyle="long" /></small>
                </h3>
                
                <!-- Description -->
                <c:if test="${not empty description}">
                    <blockquote>
                        <p>${description}</p>
                    </blockquote>
                </c:if>
                
                <!-- Content -->
                <div class="content">
                    <div>${content}</div>
                    
                    <div class="gradient"></div>
                </div>
                
                <hr>
                
                <div class="clearfix">
                    <div class="pull-left">
                        <small class="text-muted">
                            <span>publié par</span>
                            <ttc:user name="${author}" />
                            <span>le</span>
                            <fmt:formatDate value="${date}" type="date" dateStyle="long" />
                            <span>à</span>
                            <fmt:formatDate value="${date}" type="time" timeStyle="short" />
                        </small>
                    </div>
                    
                    <div class="pull-right">
                        <a href="${link.url}" class="btn btn-link btn-xs"
                            <c:if test="${link.external}">target="_blank"</c:if>
                        >
                            <span><op:translate key="LIST_TEMPLATE_BLOG_SITE_READ_MORE" /></span>
                            <i class="halflings halflings-play-circle"></i>
                        </a>
                    </div>
                </div>
            </article>
        </li>
    </c:forEach>
</ul>
