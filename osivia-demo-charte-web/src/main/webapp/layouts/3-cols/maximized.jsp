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

                <!-- Back -->
                <p:region regionName="back" />
                
                <p:region regionName="top" />
                
                <div class="row">
                    <!-- Drawer -->
                    <div id="drawer">
                        <p:region regionName="drawer-toolbar" />
                        
                        <div class="col-sm-4 col-lg-3">
                            <p:region regionName="col1-top" />
                            <p:region regionName="menu" />
                            <p:region regionName="col1-bottom" />
                        </div>
                    </div>
                    
                    <div class="col-sm-8 col-lg-9">
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
