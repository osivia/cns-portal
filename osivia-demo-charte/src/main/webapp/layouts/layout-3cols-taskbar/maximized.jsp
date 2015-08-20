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
                        
                        <div class="taskbar taskbar-default">
                            <div class="taskbar-affix">
                                <p:region regionName="col1" />
                            </div>
                        </div>
                    </div>
                    
                    <div>
                        <!-- Back -->
                        <p:region regionName="back" />
                    
                        <p:region regionName="maximized" />
                    </div>
                </div>
                
                <p:region regionName="bottom" />
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
