<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="logoAlt"><op:translate key="LOGO_ALT" /></c:set>


<html>

<head>
    <title><op:translate key="ERROR" /> - <op:translate key="BRAND" /></title>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    
    <link rel="icon" href="${contextPath}/img/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/proto-cns.min.css" />
    <link rel="stylesheet" href="${contextPath}/css/tiles.min.css" />
    <link rel="stylesheet" href="${contextPath}/css/theme-default.min.css" />
    <link rel="stylesheet" href="/osivia-portal-custom-web-assets/css/osivia.min.css">
    <link rel="stylesheet" href="/osivia-portal-custom-web-assets/components/glyphicons/css/glyphicons-halflings.css">
</head>


<body>
    <!-- Toolbar -->
    <div class="toolbar">
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <!-- Brand -->
                    <a href="/" class="navbar-brand hidden-xs"><op:translate key="BRAND" /></a>
                </div>
            </div>
        </div>
    </div>
    
    <header class="container-fluid hidden-xs">
        <!-- Banner -->
        <div class="banner clearfix">
            <!-- Logo -->
            <div class="logo">
                <div class="pull-left">
                    <h1 class="h3">
                        <a href="/">
                            <img src="${contextPath}/img/logo.png" alt="${logoAlt}">
                            <span><op:translate key="PORTAL_TITLE" /></span>
                        </a>
                    </h1>
                </div>
            </div>
        </div>
    </header>
        

    <div id="page-content" class="container-fluid">
        <div class="page-header">
            <h2 class="h3 text-danger text-center">
                <i class="halflings halflings-exclamation-sign"></i>
                
                <c:choose>
                    <c:when test="${(param['httpCode'] eq 401) || (param['httpCode'] eq 403) || (param['httpCode'] eq 404)}">
                        <span><op:translate key="ERROR_${param['httpCode']}" /></span>
                    </c:when>
    
                    <c:otherwise>
                        <span><op:translate key="ERROR_GENERIC_MESSAGE" /></span>
                    </c:otherwise>
                </c:choose>
            </h2>
        </div>
        
        <p class="text-center">
            <a href="javascript:history.back()" class="btn btn-default">
                <i class="halflings halflings-arrow-left"></i>
                <span>Retour &agrave; la page pr&eacute;c&eacute;dente</span>
            </a>
        </p>
        
        <p class="text-center visible-xs">
            <a href="/" class="btn btn-default">
                <i class="halflings halflings-home"></i>
                <span>Accueil</span>
            </a>
        </p>
    </div>
</body>

</html>
