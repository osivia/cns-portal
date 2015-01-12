<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body>
    <jsp:include page="../includes/header.jsp" />
    
    <div id="page-content" class="container">
        <!-- Content navbar -->
        <jsp:include page="../includes/content-navbar.jsp" />
        
        <!-- Notifications -->
        <p:region regionName="notifications" />

        <!-- Content auxiliary navbar -->
        <jsp:include page="../includes/content-auxiliary-navbar.jsp" />

        <p:region regionName="col1" />
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
