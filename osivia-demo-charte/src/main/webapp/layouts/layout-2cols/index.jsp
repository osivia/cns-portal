<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>

<html>

<head>
    <title>OSIVIA D�mo</title>
    
    <meta charset="UTF-8">
    <meta content="text">
    <meta http-equiv="default-style" content="main_css">
    
    <p:headerContent />
    <p:theme themeName="osivia-demo-charte" />
</head>

<body>

    <!-- Barre d'outils -->
    <p:region regionName="toolbar" />

    <header>
        <!-- Banni�re -->
        <div id="banner">
            <!-- Logo -->
            <p:region regionName="logo" />

            <!-- Recherche -->
            <p:region regionName="search" />
        </div>

        <!-- Onglets -->
        <nav>
            <p:region regionName="tabs" />
        </nav>
    </header>
    
    <section>
        <!-- Menu -->
        <p:region regionName="col1" regionID="menu" />
    
        <div class="content with-menu">
            <!-- Notifications -->
            <p:region regionName="notifications" />
        
            <!-- Breadcrumb -->
            <p:region regionName="breadcrumb" />   

            <!-- Layout -->
            <p:region regionName="col2" />
        </div>                                    
    </section>
    
    <!-- Footer -->
    <footer>
        <p:region regionName="footer" />
    </footer>
    
    <!-- AJAX scripts -->
    <p:region regionName="AJAXScripts" />
    <!-- AJAX footer -->
    <p:region regionName="AJAXFooter" />    
    <!-- Page settings -->
    <p:region regionName="pageSettings" />
    
</body>

</html>
