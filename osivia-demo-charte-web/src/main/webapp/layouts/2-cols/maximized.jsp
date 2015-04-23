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
            <div id="page-content" class="container">
                <div class="clearfix">
                    <div class="pull-left">
                        <p:region regionName="breadcrumb" />
                    </div>
                    
                    <div class="simple-menubar">
                        <p:region regionName="menubar" />
                    </div>
                </div>
                
                <!-- Notifications -->
                <p:region regionName="notifications" />

                <!-- Drawer -->
                <div id="drawer">
                    <p:region regionName="drawer-toolbar" />
                </div>
                
                <!-- Back -->
                <p:region regionName="back" />
                
                <p:region regionName="top" />
                
                <p:region regionName="maximized" />
                
                <p:region regionName="bottom" />
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
