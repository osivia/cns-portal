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
                
                <div class="clearfix">
                    <!-- Drawer -->
                    <div id="drawer" class="taskbar-container">
                        <p:region regionName="drawer-toolbar" />
                        
                        <div class="taskbar taskbar-default closed">
                            <div class="taskbar-affix">
                                <p:region regionName="taskbar" />
                            </div>
                        </div>
                    </div>
                    
                    <div>
                        <!-- Back -->
                        <p:region regionName="back" />
                    
                        <div class="row">
                            <div class="col-sm-4 col-lg-3">
                                <p:region regionName="col1" />
                            </div>
                            
                            <div class="col-sm-8 col-lg-9">
                                <p:region regionName="col2" />
                            
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p:region regionName="col21" />
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <p:region regionName="col22" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <p:region regionName="bottom" />
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
