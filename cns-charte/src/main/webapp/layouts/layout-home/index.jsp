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

            <!-- Drawer -->
            <div id="drawer">
                <p:region regionName="drawer-toolbar" />
            </div>
            
            <div class="row">
                <div class="col-sm-6">
                    <p:region regionName="col1" />
                </div>
                
                <div class="col-sm-6">
                    <p:region regionName="col2" />
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
