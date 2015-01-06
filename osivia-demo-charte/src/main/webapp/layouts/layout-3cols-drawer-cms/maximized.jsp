<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <jsp:include page="../includes/head.jsp" />
</head>


<body class="with-drawer">
    <jsp:include page="../includes/header.jsp" />
    
    <div class="wrapper-outer">
        <div class="wrapper-inner">
            <div id="page-content" class="container">
                <jsp:include page="../includes/content-top.jsp" />
        
                <div class="row">
                    <div id="drawer">
                        <div class="col-sm-4 col-lg-3">
                            <p:region regionName="col1" />
                            <p:region regionName="col1-cms" cms="true" />
                        </div>
                    </div>
                    
                    <div class="col-sm-8 col-lg-9">
                        <p:region regionName="maximized" />
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>

</html>
