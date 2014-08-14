<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>

<html>

<head>
<title>OSIVIA D�mo</title>

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
        <!-- Banni�re -->
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
                        <div class="col-sm-4">
                            <p:region regionName="col1" regionID="menu" />
                        </div>
                    </div>
        
                    <!-- Content -->
                    <div class="col-sm-8">
                        <!-- Breadcrumb -->
                        <p:region regionName="breadcrumb" />
        
                        <!-- Top -->
                        <p:region regionName="top" />
        
                        <div class="row">
                            <div class="col-sm-6">
                                <p:region regionName="col2" />
                            </div>
        
                            <div class="col-sm-6">
                                <p:region regionName="col3" />
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
