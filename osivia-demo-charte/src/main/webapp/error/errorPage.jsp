<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>


<html>

<head>
    <title><op:translate key="ERROR" /> - <op:translate key="BRAND" /></title>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="default-style" content="OSIVIA">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    
    <link rel="stylesheet" href="/osivia-portal-custom-web-assets/css/generated/osivia/osivia.min.css">
    <link rel="stylesheet" href="/osivia-portal-custom-web-assets/css/glyphicons.css">
    <link rel="stylesheet" href="/osivia-portal-custom-web-assets/css/noscript.css">
    <link rel="stylesheet" href="/osivia-demo-charte/css/osivia-demo.min.css" title="OSIVIA">
    <link rel="icon" href="/osivia-demo-charte/img/osivia.ico" />
</head>


<body>
    <!-- Toolbar -->
    <div class="toolbar">
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <!-- Brand -->
                    <a class="navbar-brand hidden-xs" href="/"><op:translate key="BRAND" /></a>
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
            <h2 class="hidden"><op:translate key="TABS_TITLE" /></h2>
                
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
                        <a href="#"><op:translate key="ERROR" /></a>
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
                    <span><op:translate key="ERROR_${param['httpCode']}" /></span>
                </c:when>

                <c:otherwise>
                    <span><op:translate key="ERROR_GENERIC_MESSAGE" /></span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>


    <!-- Footer -->
    <footer class="container"></footer>
</body>

</html>
