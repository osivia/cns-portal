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
            
            <p:region regionName="maximized" />
        </div>
    </main>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
