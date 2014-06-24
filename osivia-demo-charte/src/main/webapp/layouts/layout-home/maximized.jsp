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

        <!-- Top -->
        <p:region regionName="top" />

        <p:region regionName="maximized" />
    </section>
    
    <!-- Footer -->
    <footer></footer>
    
    <!-- AJAX scripts -->
    <p:region regionName="AJAXScripts" />
    <!-- AJAX footer -->
    <p:region regionName="AJAXFooter" />    
    <!-- Page settings -->
    <p:region regionName="pageSettings" />
    
</body>

</html>
