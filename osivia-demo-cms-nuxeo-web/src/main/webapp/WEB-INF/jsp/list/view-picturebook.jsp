<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib uri="toutatice" prefix="ttc" %>

<%@ page isELIgnored="false" %>


<c:set var="downloadLabel"><is:getProperty key="DOWNLOAD" /></c:set>


<div class="picture-book">
    <div class="row">
        <c:forEach var="document" items="${documents}">
            <!-- Document properties -->
            <c:set var="url"><ttc:documentLink document="${document}" /></c:set>
            <c:set var="pictureURL"><ttc:documentLink document="${document}" picture="true" /></c:set>
            <c:set var="thumbnailURL"><ttc:documentLink document="${document}" picture="true" displayContext="Medium" /></c:set>
            <c:set var="description" value="${document.properties['dc:description']}" />
            <c:set var="fileSize" value="${document.properties['file:content']['length']}" />
            
        
            <div class="col-xs-6 col-sm-4 col-md-3">
                <div class="picture">
                    <a href="${pictureURL}" class="thumbnail fancybox no-ajax-link" rel="gallery" data-title="${document.title}" data-download="${downloadLabel}">
                        <img src="${thumbnailURL}" alt="${description}">
                    </a>
                </div>

                <p class="text-center">
                    <a href="${url}" class="no-ajax-link">${document.title}</a>
                    <span>(<ttc:formatFileSize size="${fileSize}" />)</span>
                </p>
            </div>
        </c:forEach>
    </div>
</div>
