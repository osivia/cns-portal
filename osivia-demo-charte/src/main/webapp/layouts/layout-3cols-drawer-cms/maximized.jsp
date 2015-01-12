<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body class="with-drawer">
    <jsp:include page="../includes/header.jsp" />
    
    <div class="wrapper-outer">
        <div class="wrapper-inner">
            <div id="page-content" class="container">
                <!-- Content navbar -->
                <jsp:include page="../includes/content-navbar.jsp" />
                
                <!-- Notifications -->
                <p:region regionName="notifications" />
        
                <div class="row">
                    <div id="drawer">
                        <div class="col-sm-4 col-lg-3">
                            <p:region regionName="col1" />
                            <p:region regionName="col1-cms" cms="true" />
                        </div>
                    </div>
                    
                    <div class="col-sm-8 col-lg-9">
                        <!-- Content auxiliary navbar -->
                        <jsp:include page="../includes/content-auxiliary-navbar.jsp" />
                    
                        <p:region regionName="maximized" />
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
