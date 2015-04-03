<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="internationalization" prefix="is" %>


<html>

<head>
    <title><is:getProperty key="ERROR" /> - <is:getProperty key="BRAND" /></title>

	
	
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	
	<meta name="author" content="admin">
	<meta name="keywords" content="Error">
	<meta name="description" content="Error">
	<meta name="application-name" content="OSIVIA">
		
	<meta http-equiv="default-style" content="OSIVIA">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	
	<script type='text/javascript' src='/osivia-portal-custom-web-assets/components/jquery/jquery-1.8.3.min.js'></script><script type='text/javascript' src='/osivia-portal-custom-web-assets/js/jquery-integration.js'></script><script type='text/javascript' src='/osivia-portal-custom-web-assets/components/jquery-ui/jquery-ui-1.11.3.min.js'></script><link rel='stylesheet' href='/osivia-portal-custom-web-assets/components/jquery-ui/jquery-ui-1.11.3.min.css'><link rel="stylesheet" href="/toutatice-portail-cms-nuxeo/css/publish-menu.min.css"/>
	<script type="text/javascript" src="/toutatice-portail-cms-nuxeo/js/jquery-ui-dnd.js"></script>
	<script type="text/javascript" src="/toutatice-portail-cms-nuxeo/components/jquery-file-upload-9.8.1/js/jquery.fileupload.js"></script>
	<link type="text/css" rel="stylesheet" href="/osivia-services-calendar/css/calendar.css"/>
	<script src='/osivia-portal-custom-web-assets/js/postmessage.js'></script><script src='/osivia-portal-custom-web-assets/js/print/print.js'></script><link rel='stylesheet' href='/osivia-portal-custom-web-assets/css/generated/bootstrap/bootstrap.min.css' title='Bootstrap'><link rel='stylesheet' href='/osivia-portal-custom-web-assets/css/generated/osivia/osivia.min.css'><script src='/osivia-portal-custom-web-assets/components/bootstrap/js/bootstrap.min.js'></script><script src='/osivia-portal-custom-web-assets/js/bootstrap-integration.js'></script><link rel='stylesheet' href='/osivia-portal-custom-web-assets/components/fancybox/jquery.fancybox.css'><script src='/osivia-portal-custom-web-assets/components/fancybox/jquery.fancybox.js'></script><script src='/osivia-portal-custom-web-assets/components/fancybox/jquery.fancybox.pack.js'></script><script src='/osivia-portal-custom-web-assets/js/fancybox-integration.js'></script><script src='/osivia-portal-custom-web-assets/components/fancytree/jquery.fancytree-all-2.8.0.min.js'></script><script src='/osivia-portal-custom-web-assets/js/fancytree-integration.js'></script><script src='/osivia-portal-custom-web-assets/components/jstree/jquery.jstree.js'></script><script src='/osivia-portal-custom-web-assets/js/jstree-integration.js'></script><link rel='stylesheet' href='/osivia-portal-custom-web-assets/components/glyphicons/css/glyphicons.css'><link rel='stylesheet' href='/osivia-portal-custom-web-assets/components/glyphicons/css/glyphicons-halflings.css'><link rel='stylesheet' href='/osivia-portal-custom-web-assets/components/glyphicons/css/glyphicons-filetypes.css'><link rel='stylesheet' href='/osivia-portal-custom-web-assets/components/glyphicons/css/glyphicons-social.css'><link rel='stylesheet' href='/osivia-portal-custom-web-assets/css/glyphicons-1.8.css'><noscript><link rel='stylesheet' href='/osivia-portal-custom-web-assets/css/noscript.css'></noscript><script type='text/javascript' src='/portal-ajax/dyna/prototype.js'></script><script type='text/javascript' src='/portal-ajax/dyna/prototype-bootstrap-workaround.js'></script><script type='text/javascript' src='/portal-ajax/dyna/effects.js'></script><script type='text/javascript' src='/portal-ajax/dyna/dragdrop.js'></script><script type='text/javascript' src='/portal-ajax/dyna/dyna.js'></script>
	<link rel="icon" href="/osivia-demo-charte-cns/img/favicon.png" />
	<link rel="stylesheet" href="/osivia-demo-charte-cns/css/proto-cns.min.css" title="OSIVIA" />
	<link rel="stylesheet" href="/osivia-demo-charte-cns/css/tiles.min.css" />
	<link rel="stylesheet" href="/osivia-demo-charte-cns/css/menubar-publi.min.css" />
</head>


<body>
    <!-- Toolbar -->
    <div class="toolbar">
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <!-- Brand -->
                    <a class="navbar-brand hidden-xs" href="/"><is:getProperty key="BRAND" /></a>
                </div>
            </div>
        </div>
    </div>


    <!-- Header -->
    <header class="container">
        <!-- Banner -->
        <div class="banner clearfix">
            <!-- Logo -->
            <div class="pull-left">
                <h1>
                    <a href="/">
                        <img src="/osivia-demo-charte/img/logo.png" alt="OSIVIA">
                    </a>
                </h1>
            </div>
            
            <div class="header-line"></div>
        </div>
    
        <!-- Navigation -->
        <nav role="navigation">
            <h2 class="hidden"><is:getProperty key="TABS_TITLE" /></h2>
                
            <ul class="nav nav-osivia">
                <!-- Home -->
                <li role="presentation">
                    <div class="text-center clearfix">
                        <a href="/">
                            <i class="glyphicons halflings home"></i>
                        </a>
                    </div>
                </li>
                
                <!-- Error page -->
                <li class="active" role="presentation">
                    <div class="text-center clearfix">
                        <a href="#"><is:getProperty key="ERROR" /></a>
                    </div>
                </li>
            </ul>
        </nav>
    </header>
    
    
    <!-- Page content -->
    <div id="page-content" class="container">
        <div class="alert alert-danger" role="alert">
            <i class="glyphicons halflings exclamation-sign"></i>
            
            <c:choose>
                <c:when test="${(param['httpCode'] eq 401) || (param['httpCode'] eq 403) || (param['httpCode'] eq 404)}">
                    <span><is:getProperty key="ERROR_${param['httpCode']}" /></span>
                </c:when>

                <c:otherwise>
                    <span><is:getProperty key="ERROR_GENERIC_MESSAGE" /></span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>


    <!-- Footer -->
    <footer class="container"></footer>
</body>

</html>
