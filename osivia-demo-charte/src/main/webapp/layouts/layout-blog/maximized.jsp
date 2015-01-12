<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body>
    <jsp:include page="../includes/header.jsp" />
    
    <div id="page-content" class="container">
        <!-- Blog header -->
        <div class="row blog-header">
            <div class="col-sm-2 hidden-xs">
                <p:region regionName="blog_logo" />
            </div>
            
            <div class="col-sm-7">
                <p:region regionName="blog_title" />
            </div>
            
            <div class="col-sm-3 hidden-xs">
                <p:region regionName="blog_tools" />
            </div>
        </div>
      
        <!-- Notifications -->
        <p:region regionName="notifications" />
      
        <div class="row">
            <div class="col-sm-4">
                <p:region regionName="col1" />
            </div>
        
            <div class="col-sm-8">
                 <div class="media">
                    <div class="media-body hidden-xs">
                        <!-- Breadcrumb -->
                        <p:region regionName="breadcrumb" />
                    </div>

                    <div class="media-right simple-menubar">
                        <!-- Menubar -->
                        <p:region regionName="menubar" />
                    </div>
                </div>
                 
                <!-- Content auxiliary navbar -->
                <jsp:include page="../includes/content-auxiliary-navbar.jsp" />
    
                <p:region regionName="maximized" />
            </div>
        </div>
    </div>
    
    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
