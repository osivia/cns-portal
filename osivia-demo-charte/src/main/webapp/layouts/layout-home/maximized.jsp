<!DOCTYPE html>
<%@ taglib prefix="p" uri="portal-layout"%>

<html>

<head>
    <title>OSIVIA Démo</title>
    
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
        <!-- Bannière -->
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
        <!-- Notifications -->
        <p:region regionName="notifications" />

        <!-- Content -->
        <div class="content">
            <p:region regionName="tools" />
            <p:region regionName="maximized" />
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
