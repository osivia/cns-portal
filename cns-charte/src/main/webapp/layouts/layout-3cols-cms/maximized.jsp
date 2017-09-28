<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body>
    <jsp:include page="../includes/header.jsp" />
    
    <main>
        <div class="container-fluid">
            <!-- Content navbar -->
            <jsp:include page="../includes/content-navbar.jsp" />
            
            <div class="row">
                <!-- Drawer -->
                <div id="drawer">
                    <p:region regionName="drawer-toolbar" />
                    
                    <div class="col-sm-4 col-lg-3">
                        <p:region regionName="col1" />
                        <p:region regionName="col1-cms" />
                    </div>
                </div>
                
                <div class="col-sm-8 col-lg-9">
                    <p:region regionName="maximized" />
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
