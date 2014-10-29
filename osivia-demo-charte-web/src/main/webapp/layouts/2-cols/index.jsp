<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body>
    <jsp:include page="../includes/header.jsp" />
    
    <div id="page-content" class="container">
        <!-- Notifications -->
        <p:region regionName="notifications" />

        <p:region regionName="top" cms="true" />

        <div class="row">
            <div class="col-sm-6">
                <p:region regionName="col1-top" cms="true" />
                <p:region regionName="col1-bottom" cms="true" />
            </div>
            
            <div class="col-sm-6">
                <p:region regionName="col2-top" cms="true" />
                <p:region regionName="col2-bottom" cms="true" />
            </div>
        </div>
        
        <p:region regionName="bottom" cms="true" />
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
