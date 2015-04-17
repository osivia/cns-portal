<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib uri="toutatice" prefix="ttc" %>

<%@ page isELIgnored="false" %>


<!-- jQuery File Upload -->
<script type="text/javascript" src="/toutatice-portail-cms-nuxeo/components/jquery-file-upload-9.8.1/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="/toutatice-portail-cms-nuxeo/components/jquery-file-upload-9.8.1/js/jquery.fileupload-process.js"></script>
<script type="text/javascript" src="/toutatice-portail-cms-nuxeo/components/jquery-file-upload-9.8.1/js/jquery.fileupload-ui.js"></script>
<script type="text/javascript" src="/toutatice-portail-cms-nuxeo/components/jquery-file-upload-9.8.1/js/jquery.fileupload-validate.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/picturebook-dnd.js"></script>


<portlet:renderURL var="refreshURL" />

<portlet:actionURL name="drop" var="dropActionURL" />

<portlet:actionURL name="fileUpload" var="fileUploadActionURL">
    <portlet:param name="parentId" value="${parentId}" />
</portlet:actionURL>


<c:set var="downloadLabel"><is:getProperty key="DOWNLOAD" /></c:set>


<div class="picturebook" data-dropurl="${dropActionURL}" data-refreshurl="${refreshURL}">
    <div
        <c:if test="${editable}">class="drop-zone"</c:if>
    >
        <div class="row">
            <c:forEach var="document" items="${documents}" varStatus="status">
                <!-- Document properties -->
                
                <!-- URL -->
                <c:set var="url"><ttc:documentLink document="${document}" /></c:set>
                
                <!-- Picture -->
                <c:set var="pictureURL"><ttc:documentLink document="${document}" picture="true" /></c:set>
                
                <!-- Thumbnail -->
                <c:set var="thumbnailURL"><ttc:documentLink document="${document}" picture="true" displayContext="Medium" /></c:set>
                
                <!-- Description -->
                <c:set var="description" value="${document.properties['dc:description']}" />
                
                <!-- File size -->
                <c:set var="fileSize" value="${document.properties['common:size']}" />
                
                
            
                <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                    <div class="picture">
                        <a href="${pictureURL}" class="thumbnail fancybox draggable" rel="gallery" data-title="${document.title}" data-download="${downloadLabel}" data-id="${document.id}" data-type="${document.type.name}">
                            <img src="${thumbnailURL}" alt="${description}">
                        </a>
                    </div>
    
                    <p class="text-center">
                        <a href="${url}">${document.title}</a>
                        
                        <c:if test="${not empty fileSize}">
                            <span>(<ttc:formatFileSize size="${fileSize}" />)</span>
                        </c:if>
                    </p>
                </div>
                
                
                <!-- Responsive column reset -->
                <c:choose>
                    <c:when test="${status.count % 6 == 0}">
                        <div class="clearfix visible-xs visible-sm visible-lg"></div>
                    </c:when>
                    
                    <c:when test="${status.count % 4 == 0}">
                        <div class="clearfix visible-xs visible-md"></div>
                    </c:when>
                    
                    <c:when test="${status.count % 3 == 0}">
                        <div class="clearfix visible-sm"></div>
                    </c:when>
                    
                    <c:when test="${status.count % 2 == 0}">
                        <div class="clearfix visible-xs"></div>
                    </c:when>
                </c:choose>
            </c:forEach>
        </div>
        
        
        <!-- File upload -->
        <c:if test="${editable}">
            <form action="${fileUploadActionURL}" method="post" enctype="multipart/form-data" class="file-upload ajax-link" role="form">
                <input type="file" name="files[]" class="hidden" multiple="multiple">
            
                <div class="panel panel-default hidden">
                    <div class="panel-body">
                        <div class="form-group fileupload-buttonbar">
                            <button type="submit" class="btn btn-primary start">
                                <i class="halflings halflings-upload"></i>
                                <span><is:getProperty key="FILE_BROWSER_START_UPLOAD" /></span>
                            </button>
                            
                            <button type="reset" class="btn btn-default cancel">
                                <i class="halflings halflings-ban-circle"></i>
                                <span><is:getProperty key="CANCEL" /></span>
                            </button>
                        </div>
                            
                        <div class="form-group">
                            <div class="progress">
                                <div class="progress-bar" role="progressbar"></div>
                            </div>
                        </div>
                    </div>
                    
                    <ul class="file-upload-list list-group files"></ul>
                </div>
            </form>
            
            <div class="file-upload-shadowbox jumbotron bg-info-hover">
                <div class="text-center">
                    <p><is:getProperty key="FILE_BROWSER_DROP_ZONE_MESSAGE" /></p>
                    <p class="h1"><i class="glyphicons glyphicons-inbox"></i></p>
                </div>
            </div>
        </c:if>
    </div>
</div>
