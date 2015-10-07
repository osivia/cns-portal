<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body>
    <jsp:include page="../includes/header.jsp" />
    
    <div class="wrapper-outer">
        <div class="wrapper-inner">
            <div id="page-content" class="container-fluid">
                <!-- Content navbar -->
                <jsp:include page="../includes/content-navbar.jsp" />
                
                <!-- Notifications -->
                <p:region regionName="notifications" />

                <p:region regionName="top" />
                <p:region regionName="top-cms" cms="true" />
                
                <div class="row">
                    <!-- Drawer -->
                    <div id="drawer">
                        <p:region regionName="drawer-toolbar" />
                        
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
                
                <p:region regionName="bottom" />
                <p:region regionName="bottom-cms" cms="true" />
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
