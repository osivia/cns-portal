<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="brand"><op:translate key="BRAND" /></c:set>


<html>

<head>
    <title><op:translate key="ERROR" /> - ${brand}</title>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="default-style" content="CNS">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    
    <link rel="stylesheet" href="/osivia-portal-custom-web-assets/css/osivia.min.css">
    <link rel="stylesheet" href="/osivia-portal-custom-web-assets/css/glyphicons.min.css">
    <link rel="icon" href="${contextPath}/img/favicon.png" />
    <link rel="stylesheet" href="${contextPath}/css/cns-forums.min.css" title="CNS" />
</head>


<body>
    <!-- Toolbar -->
    <div class="toolbar">
        <div class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <!-- Brand -->
                    <a href="/" class="navbar-brand hidden-xs">${brand}</a>
                </div>
            </div>
        </div>
    </div>

    <header class="hidden-xs">
        <div class="container">
            <!-- Title -->
            <div class="jumbotron">
                <p class="text-center">
                    <img src="${contextPath}/img/logo-men.png" alt="Logo du Minit&egrave;re de l'&Eacute;ducation Nationale">
                </p>

                <h1 class="text-center">Forums du CNS</h1>
            </div>
        </div>
    </header>

    <main>
        <div class="container">
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
                    <span><op:translate key="HOME" /></span>
                </a>
            </p>
        </div>
    </main>
</body>

</html>
