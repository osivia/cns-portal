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
        <div id="banner">
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
            <section class="container">
                <!-- Notifications -->
                <p:region regionName="notifications" />
                
                <div class="row">
                    <!-- Menu -->
                    <div id="drawer">
                        <div class="col-sm-4">
                            <p:region regionName="col1" regionID="menu" />
                        </div>
                    </div>
                    
                    <!-- Content -->
                    <div class="col-sm-8">
                        <!-- Breadcrumb -->
                        <p:region regionName="breadcrumb" />
                        
                        <p:region regionName="maximized" />
                    </div>
                </div>                                   
            </section>
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
