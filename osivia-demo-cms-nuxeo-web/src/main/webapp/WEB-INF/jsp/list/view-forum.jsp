<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib uri="toutatice" prefix="ttc" %>

<%@ page isELIgnored="false" %>


<ul class="list-group">
    <c:forEach var="document" items="${documents}">
        <!-- Document properties -->
        <c:set var="url"><ttc:documentLink document="${document}" /></c:set>
        <c:set var="vignetteURL"><ttc:getImageURL document="${document}" property="ttc:vignette" /></c:set>
        <c:set var="description" value="${document.properties['dc:description']}" />
        <c:set var="lastContributor" value="${document.properties['dc:lastContributor']}" />
        <c:set var="modificationDate" value="${document.properties['dc:modified']}" />
        <c:set var="nbAnswers" value="${document.properties['ttcth:nbComments']}" />
    
    
        <li class="list-group-item list-group-item-linked">
            <a href="${url}" class="list-group-item">
                <div class="media">
                    <div class="media-left">
	                    <h3 class="h3 text-center media-heading">${nbAnswers}</h3> 
	                    <p class="small"><is:getProperty key="ANSWERS" /></p>
                    </div>
                    <c:if test="${not empty vignetteURL}">
                        <span class="media-left">
                            <img src="${vignetteURL}" alt="" class="media-object">
                        </span>
                    </c:if>
                    
                    <div class="media-body">
                        <h3 class="h4 media-heading">${document.title}</h3>
                        <p>${description}</p>
	                        <div class="small panel-body">
	                           <span><is:getProperty key="LAST_ANSWER" /></span>
	                           <span><is:getProperty key="DATE_ARTICLE_PREFIX" /></span>
                               <span><fmt:formatDate value="${modificationDate}" type="date" dateStyle="long" /></span>
			                   <span><is:getProperty key="BY" /></span>
			                   <span><ttc:user name="${lastContributor}" linkable="false" /></span>
	                        </div>
                    </div>
                </div>
            </a>
        </li>
    </c:forEach>
</ul>
