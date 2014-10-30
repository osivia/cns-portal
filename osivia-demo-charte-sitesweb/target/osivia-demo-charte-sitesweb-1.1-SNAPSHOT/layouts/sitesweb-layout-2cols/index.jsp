<%@ taglib prefix="p" uri="portal-layout"%>
<!DOCTYPE html>

<html>
<head>
    <p:region regionName='header-metadata' />
    
    <p:headerContent />
    <p:theme themeName="osivia-demo-charte" />
</head>

<body>
    <!-- Barre d'outils -->
    <p:region regionName="toolbar" />
    
    <header class="container">
        <p:region regionName="headBand" />
    </header>
    
    <section class="container">
        <!-- Notifications -->
        <p:region regionName="notifications" />
    
        <div class="row">
            <!-- Navigation -->
            <div class="col-xs-3 col-sm-2">
                <p:region regionName="colNav" />
            </div>
            
            <div class="col-xs-9 col-sm-10">
                <div class="row hidden-xs">
                    <div class="col-sm-8">
                        <!-- Breadcrumb -->
                        <p:region regionName="breadcrumb" />
                    </div>
                    
                    <div class="col-sm-4">
                        <!-- Recherche -->
                        <p:region regionName="search" />
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-xs-12 col-sm-8">
                        <!-- Title -->
                        <p:region regionName="blocTitre"/>
                        <!-- Center content -->
                        <p:region regionName="colMilieu" cms="true"/>
                    </div>
                </div>    
            </div>
        </div>
    </section>
    
    <footer class="container">
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
