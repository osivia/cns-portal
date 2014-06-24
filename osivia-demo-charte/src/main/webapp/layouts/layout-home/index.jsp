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
        
        
        <!-- Navigation toolbar -->
        
<!--         <div id="navigation2" class="visible-xs"> -->
<!--             <ol class="breadcrumb"> -->
<!--                 <li><a href="#">Rubrique 2</a></li> -->
<!--                 <li><a href="#">Page 2</a></li> -->
<!--                 <li><a href="#">Page 2.1</a></li> -->
<!--                 <li class="active">Page 2.1.3</li> -->
<!--                 <li> -->
<!--                     <button type="button" class="btn btn-default btn-sm" data-toggle="menu" data-parent="#navigation2" data-target="#navigation2-menu"> -->
<!--                         <span class="glyphicon glyphicon-plus"></span> -->
<!--                         <span class="caret"></span> -->
<!--                     </button> -->
<!--                 </li> -->
<!--             </ol> -->
            
<!--             <div id="navigation2-menu" class="menu hidden"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.1</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.2</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.3</a> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
        
        
<!--         <hr> -->
        
<!--         <div id="navigation" class="osivia-navigation row visible-xs"> -->
<!--             <div class="btn-toolbar" role="toolbar"> -->
<!--                 <div class="btn-group btn-group-lg"> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navigation" data-target="#dln1"> -->
<!--                         <span>Rubrique 2</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navigation" data-target="#dln2"> -->
<!--                         <span>Page 2</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navigation" data-target="#dln3"> -->
<!--                         <span>Page 2.1</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                     <a class="btn btn-primary" href="#" data-toggle="menu" data-parent="#navigation" data-target="#dln4"> -->
<!--                         <span>Page 2.1.3</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navigation" data-target="#dln5"> -->
<!--                         <span>--</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                 </div> -->
<!--             </div> -->
        
<!--             <div id="dln1" class="menu hidden"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Rubrique 1</a> -->
<!--                     <a class="list-group-item active" href="#">Rubrique 2</a> -->
<!--                     <a class="list-group-item" href="#">Rubrique 3</a> -->
<!--                     <a class="list-group-item" href="#">Rubrique 4</a> -->
<!--                     <a class="list-group-item" href="#">Rubrique 5</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="dln2" class="menu hidden"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Page 1</a> -->
<!--                     <a class="list-group-item active" href="#">Page 2</a> -->
<!--                     <a class="list-group-item" href="#">Page 3</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="dln3" class="menu hidden"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item active" href="#">Page 2.1</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.2</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.3</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.4</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="dln4" class="menu hidden"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.1</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.2</a> -->
<!--                     <a class="list-group-item active" href="#">Page 2.1.3</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="dln5" class="menu hidden"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.1</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.2</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.3</a> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
        
        
<!--         <hr> -->
        

<!--         <div id="navmenu" class="menu-collapse-toolbar"> -->
<!--             <div class="btn-toolbar" role="toolbar"> -->
<!--                 <div class="btn-group"> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navmenu" data-target="#n1"> -->
<!--                         <span>Rubrique 2</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navmenu" data-target="#n2"> -->
<!--                         <span>Page 2</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navmenu" data-target="#n3"> -->
<!--                         <span>Page 2.1</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navmenu" data-target="#n4"> -->
<!--                         <span>Page 2.1.3</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                 </div> -->
<!--                 <div class="btn-group"> -->
<!--                     <a class="btn btn-default" href="#" data-toggle="menu" data-parent="#navmenu" data-target="#n5"> -->
<!--                         <span>--Sélectionner--</span> -->
<!--                         <span class="caret"></span> -->
<!--                     </a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="n1" class="menu-collapse collapse"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Rubrique 1</a> -->
<!--                     <a class="list-group-item active" href="#">Rubrique 2</a> -->
<!--                     <a class="list-group-item" href="#">Rubrique 3</a> -->
<!--                     <a class="list-group-item" href="#">Rubrique 4</a> -->
<!--                     <a class="list-group-item" href="#">Rubrique 5</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="n2" class="panel-collapse collapse"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Page 1</a> -->
<!--                     <a class="list-group-item active" href="#">Page 2</a> -->
<!--                     <a class="list-group-item" href="#">Page 3</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="n3" class="panel-collapse collapse"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item active" href="#">Page 2.1</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.2</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.3</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.4</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="n4" class="panel-collapse collapse"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.1</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.2</a> -->
<!--                     <a class="list-group-item active" href="#">Page 2.1.3</a> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div id="n5" class="panel-collapse collapse"> -->
<!--                 <div class="list-group"> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.1</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.2</a> -->
<!--                     <a class="list-group-item" href="#">Page 2.1.3.3</a> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
        
        
<!--         <hr> -->
                
<!--         <div class="btn-toolbar" role="toolbar"> -->
<!--             <div class="btn-group"> -->
<!--                 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> -->
<!--                     <span>Rubrique 2</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </button> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Rubrique 1</a></li> -->
<!--                     <li class="active"><a class="active" href="#">Rubrique 2</a></li> -->
<!--                     <li><a href="#">Rubrique 3</a></li> -->
<!--                     <li><a href="#">Rubrique 4</a></li> -->
<!--                     <li><a href="#">Rubrique 5</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
            
<!--             <div class="btn-group"> -->
<!--                 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> -->
<!--                     <span>Page 1</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </button> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li class="active"><a class="active" href="#">Page 1</a></li> -->
<!--                     <li><a href="#">Page 2</a></li> -->
<!--                     <li><a href="#">Page 3</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
            
<!--             <div class="btn-group"> -->
<!--                 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> -->
<!--                     <span>Page 1.2</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </button> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Page 1.1</a></li> -->
<!--                     <li class="active"><a class="active" href="#">Page 1.2</a></li> -->
<!--                     <li><a href="#">Page 1.3</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
            
<!--             <div class="btn-group"> -->
<!--                 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> -->
<!--                     <span>Page 1.2.3</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </button> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Page 1.2.1</a></li> -->
<!--                     <li><a href="#">Page 1.2.2</a></li> -->
<!--                     <li class="active"><a class="active" href="#">Page 1.2.3</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
            
<!--             <div class="btn-group"> -->
<!--                 <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"> -->
<!--                     <span>Page 1.2.3.1</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </button> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li class="active"><a class="active" href="#">Page 1.2.3.1</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.2</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.3</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
            
<!--             <div class="btn-group"> -->
<!--                 <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> -->
<!--                     <span>-- Sélectionner --</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </button> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Page 1.2.3.1.1</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.1.2</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.1.3</a></li> -->
<!--                 </ul> -->
<!--             </div> -->
<!--         </div> -->
        
<!--         <hr> -->
        
<!--         <ul class="nav nav-pills"> -->
<!--             <li class="dropdown"> -->
<!--                 <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                     <span>Rubrique 2</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </a> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Rubrique 1</a></li> -->
<!--                     <li class="active"><a class="active" href="#">Rubrique 2</a></li> -->
<!--                     <li><a href="#">Rubrique 3</a></li> -->
<!--                     <li><a href="#">Rubrique 4</a></li> -->
<!--                     <li><a href="#">Rubrique 5</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
            
<!--             <li class="dropdown"> -->
<!--                 <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                     <span>Page 1</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </a> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li class="active"><a class="active" href="#">Page 1</a></li> -->
<!--                     <li><a href="#">Page 2</a></li> -->
<!--                     <li><a href="#">Page 3</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
            
<!--             <li class="dropdown"> -->
<!--                 <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                     <span>Page 1.2</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </a> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Page 1.1</a></li> -->
<!--                     <li class="active"><a class="active" href="#">Page 1.2</a></li> -->
<!--                     <li><a href="#">Page 1.3</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
            
<!--             <li class="dropdown"> -->
<!--                 <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                     <span>Page 1.2.3</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </a> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Page 1.2.1</a></li> -->
<!--                     <li><a href="#">Page 1.2.2</a></li> -->
<!--                     <li class="active"><a class="active" href="#">Page 1.2.3</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
            
<!--             <li class="dropdown active"> -->
<!--                 <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                     <span>Page 1.2.3.1</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </a> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li class="active"><a class="active" href="#">Page 1.2.3.1</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.2</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.3</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
            
<!--             <li class="dropdown"> -->
<!--                 <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                     <span>-- Sélectionner --</span> -->
<!--                     <span class="caret"></span> -->
<!--                 </a> -->
<!--                 <ul class="dropdown-menu" role="menu"> -->
<!--                     <li><a href="#">Page 1.2.3.1.1</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.1.2</a></li> -->
<!--                     <li><a href="#">Page 1.2.3.1.3</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
<!--         </ul> -->
        
<!--         <hr> -->
        
    </header>
    
    <section class="container-fluid">
        <!-- Notifications -->
        <p:region regionName="notifications" />


<!--         <div class="row row-offcanvas row-offcanvas-left"> -->
<!--             Sidebar -->
<!--             <div class="col-sm-3 sidebar-offcanvas" role="navigation"> -->
<!--                 <ul class="nav nav-pills nav-stacked"> -->
<!--                     <li class="dropdown"> -->
<!--                         <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                             <span>Rubrique 2</span> -->
<!--                             <span class="caret"></span> -->
<!--                         </a> -->
<!--                         <ul class="dropdown-menu" role="menu"> -->
<!--                             <li><a href="#">Rubrique 1</a></li> -->
<!--                             <li class="active"><a class="active" href="#">Rubrique 2</a></li> -->
<!--                             <li><a href="#">Rubrique 3</a></li> -->
<!--                             <li><a href="#">Rubrique 4</a></li> -->
<!--                             <li><a href="#">Rubrique 5</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    
<!--                     <li class="dropdown"> -->
<!--                         <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                             <span>Page 1</span> -->
<!--                             <span class="caret"></span> -->
<!--                         </a> -->
<!--                         <ul class="dropdown-menu" role="menu"> -->
<!--                             <li class="active"><a class="active" href="#">Page 1</a></li> -->
<!--                             <li><a href="#">Page 2</a></li> -->
<!--                             <li><a href="#">Page 3</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    
<!--                     <li class="dropdown"> -->
<!--                         <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                             <span>Page 1.2</span> -->
<!--                             <span class="caret"></span> -->
<!--                         </a> -->
<!--                         <ul class="dropdown-menu" role="menu"> -->
<!--                             <li><a href="#">Page 1.1</a></li> -->
<!--                             <li class="active"><a class="active" href="#">Page 1.2</a></li> -->
<!--                             <li><a href="#">Page 1.3</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    
<!--                     <li class="dropdown"> -->
<!--                         <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                             <span>Page 1.2.3</span> -->
<!--                             <span class="caret"></span> -->
<!--                         </a> -->
<!--                         <ul class="dropdown-menu" role="menu"> -->
<!--                             <li><a href="#">Page 1.2.1</a></li> -->
<!--                             <li><a href="#">Page 1.2.2</a></li> -->
<!--                             <li class="active"><a class="active" href="#">Page 1.2.3</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    
<!--                     <li class="dropdown active"> -->
<!--                         <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                             <span>Page 1.2.3.1</span> -->
<!--                             <span class="caret"></span> -->
<!--                         </a> -->
<!--                         <ul class="dropdown-menu" role="menu"> -->
<!--                             <li class="active"><a class="active" href="#">Page 1.2.3.1</a></li> -->
<!--                             <li><a href="#">Page 1.2.3.2</a></li> -->
<!--                             <li><a href="#">Page 1.2.3.3</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    
<!--                     <li class="dropdown"> -->
<!--                         <a class="dropdown-toggle" data-toggle="dropdown" href="#"> -->
<!--                             <span>-- Sélectionner --</span> -->
<!--                             <span class="caret"></span> -->
<!--                         </a> -->
<!--                         <ul class="dropdown-menu" role="menu"> -->
<!--                             <li><a href="#">Page 1.2.3.1.1</a></li> -->
<!--                             <li><a href="#">Page 1.2.3.1.2</a></li> -->
<!--                             <li><a href="#">Page 1.2.3.1.3</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </div> -->
            
            <!-- Main content -->
            <div class="row">
                <div class="col-xs-12 col-sm-9">
                    <!-- Top -->
                    <p:region regionName="top" />
            
                    <div class="row">
                        <div class="col-sm-5">
                            <p:region regionName="left" />
                        </div>
                        
                        <div class="col-sm-5">
                            <p:region regionName="right" />
                        </div>
                        
                        <div class="col-sm-2">
                            <p:region regionName="tools" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
