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
                <!-- Global navbar -->
                <jsp:include page="../includes/content-navbar.jsp" />
                
                <!-- Notifications -->
                <p:region regionName="notifications" />

                <p:region regionName="top" />
                <p:region regionName="top-cms" cms="true" />
        
                <div class="row">
                    <div id="drawer">
                        <div class="col-sm-4 col-lg-3">
                            <p:region regionName="col1" />
                            <p:region regionName="col1-cms" cms="true" />
                        </div>
                    </div>
                    
                    <div class="col-sm-8 col-lg-9">
                        <p:region regionName="col2" />
                        <p:region regionName="col2-cms" cms="true" />
                    
                        <div class="row">
                            <div class="col-sm-6">
                                <p:region regionName="col21" />
                                <p:region regionName="col21-cms" cms="true" />
                            </div>
                            
                            <div class="col-sm-6">
                                <p:region regionName="col22" />
                                <p:region regionName="col22-cms" cms="true" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
