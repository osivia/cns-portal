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

<body>
    <!-- Barre d'outils -->
    <p:region regionName="toolbar" />

    <header class="container-fluid">
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
    
    <section class="container-fluid">
        <!-- Notifications -->
        <p:region regionName="notifications" />
    
        <!-- Blog header -->
        <div class="row blog-header">
            <div class="col-sm-2 hidden-xs">
                <p:region regionName="blog_logo" />
            </div>
            
            <div class="col-sm-7">
                <p:region regionName="blog_title" />
            </div>
            
            <div class="col-sm-3 hidden-xs">
                <p:region regionName="blog_tools" />
            </div>
        </div>
      
        <div class="row">
            <!-- Content -->
            <div class="col-sm-8 col-sm-push-4">
                <!-- Breadcrumb -->
                <p:region regionName="breadcrumb" />   
    
                <p:region regionName="maximized" />
            </div>
        
            <!-- Menu -->
            <div class="col-sm-4 col-sm-pull-8">
                <p:region regionName="col1" regionID="menu" />
            </div>
        </div>
    </section>
    
    <!-- Footer -->
    <footer class="container-fluid"></footer>
    
    <!-- AJAX scripts -->
    <p:region regionName="AJAXScripts" />
    <!-- AJAX footer -->
    <p:region regionName="AJAXFooter" />    
    <!-- Page settings -->
    <p:region regionName="pageSettings" />
    
</body>

</html>
