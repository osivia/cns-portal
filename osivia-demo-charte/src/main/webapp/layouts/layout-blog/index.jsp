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

        <div class="row">
            <div class="col-sm-4">
                <p:region regionName="col1" />
            </div>
        
            <div class="col-sm-8">
                <jsp:include page="../includes/content-top.jsp" />
    
                <p:region regionName="col2" />
            </div>
        </div>
    </div>
    
    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
