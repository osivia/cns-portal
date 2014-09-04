<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>


<html>

<head>
    <p:region regionName="header-metadata" />
    
    <meta charset="UTF-8">
    <meta content="text">
    <meta http-equiv="default-style" content="main_css">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    
    <p:headerContent />
    <p:theme themeName="osivia-demo-charte" />    
</head>


<body>
    <!-- Barre d'outils -->
    <p:region regionName="toolbar" />

    <header class="container">
        <!-- Bannière -->
        <div id="banner" class="clearfix">
            <!-- Logo -->
            <p:region regionName="logo" />

            <!-- Recherche -->
            <p:region regionName="search" />
        </div>

        <!-- Onglets -->
        <p:region regionName="tabs" />
    </header>
    
    <div id="page-content" class="container">
        <!-- Notifications -->
        <p:region regionName="notifications" />

        <!-- Main content -->
        <div class="row">
            <!-- Top -->
            <div class="col-xs-12">
                <p:region regionName="top" />
            </div>
    
            <div class="col-sm-3 col-lg-4">
                <p:region regionName="left" />
            </div>
            
            <div class="col-sm-6 col-lg-5">
                <p:region regionName="right" />
            </div>
            
            <div class="col-sm-3">
                <p:region regionName="tools" />
            </div>
        </div>
    </div>
    
    <!-- Footer -->
    <footer class="container"></footer>
    
    <!-- AJAX scripts -->
    <p:region regionName="AJAXScripts" />    
    <!-- AJAX footer -->
    <p:region regionName="AJAXFooter" />
    <!-- Page settings -->
    <p:region regionName="pageSettings" />
    
</body>

</html>
