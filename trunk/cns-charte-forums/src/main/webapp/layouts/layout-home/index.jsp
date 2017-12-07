<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body>
    <jsp:include page="../includes/header.jsp" />
    
    <main>
        <div class="container">
            <!-- Simple menubar -->
            <div class="simple-menubar">
                <p:region regionName="menubar" />
            </div>
        
            <!-- Drawer -->
            <div id="drawer">
                <p:region regionName="drawer-toolbar" />
            </div>
            
            <p:region regionName="col1" />
        </div>
    </main>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
