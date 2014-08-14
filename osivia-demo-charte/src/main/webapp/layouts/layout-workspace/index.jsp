<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>

<html>

<head>
    <title>OSIVIA Démo</title>
    
    <meta charset="UTF-8">
    <meta content="text">
    <meta http-equiv="default-style" content="main_css">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    
    <p:headerContent />
    <p:theme themeName="osivia-demo-charte" />
</head>

<body class="with-drawer">
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
        
    <div class="wrapper-outer">
        <div class="wrapper-inner">
            <div id="page-content" class="container">
                <!-- Notifications -->
                <p:region regionName="notifications" />
            
                <div class="row">
                    <!-- Menu -->
                    <div id="drawer">
                        <div class="col-sm-3">
                            <p:region regionName="menu" />
                        </div>
                    </div>
            
                    <!-- Content -->
                    <div class="col-sm-9">
                        <!-- Breadcrumb -->
                        <p:region regionName="breadcrumb" />
                        
                        <!-- Top -->
                        <p:region regionName="top" />
                        
                        <div class="row">
                            <div class="col-sm-8">
                                <p:region regionName="center" />
                            </div>
                            
                            <div class="col-sm-4 hidden-xs">
                                <p:region regionName="tools" />
                            </div>                    
                        </div>
                    </div>
                </div>
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
