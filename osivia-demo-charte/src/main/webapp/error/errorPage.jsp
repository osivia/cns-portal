<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib prefix="p" uri="portal-layout" %>

<%
    String httpError = request.getParameter("httpCode");
    if (httpError != null) {
        try {
            int httpCode = Integer.parseInt(httpError);
            response.setStatus(httpCode);
        } catch (Exception e) {

        }
    }
    String msg = "Une erreur est survenue";

    if ("404".equals(httpError))
        msg = "Erreur 404 - Ressource non trouvée";
    if ("403".equals(httpError))
        msg = "Erreur 403 - Accès interdit";
    if ("401".equals(httpError))
        msg = "Erreur 401 - Accès non authorisé";
%>


<c:set var="urlPortal" value="/portal/auth" />


<html>

<head>
<title>OSIVIA Portal</title>

<meta charset="UTF-8">
<meta content="text">
<meta http-equiv="default-style" content="main_css">

<link rel="stylesheet" href="/osivia-portal-custom-web-assets/common-css/common.css" type="text/css">
<link rel="stylesheet" href="/osivia-portal-custom-web-assets/common-css/toolbar.css" type="text/css">
<link rel="stylesheet" href="/osivia-portal-custom-web-assets/common-css/tabs.css" type="text/css">
<link rel="stylesheet" id="main_css" href="/osivia-demo-charte/style/osivia-demo.css" type="text/css" />
<link rel="shortcut icon" type="image/x-icon" href="/osivia-demo-charte/style/images/favicon/favicon.ico">
</head>

<body>   
    <!-- Barre d'outils -->
    <div id="toolbar">
        <div class="toolbar-content"><p>&nbsp;</p></div>
    </div>    

    <header>
        <!-- Bannière -->
        <div id="banner">
            <!-- Logo -->
            <div id="logo">
                <a class="osivia" href="/portal" title="OSIVIA"></a>
                <div class="barre"></div>
            </div>            
        </div>        
        
        <!-- Onglets -->
        <nav>
            <div id="tabs">
                <ul>
                    <li class="current"><a>Erreur</a></li>
                </ul>
            </div>
        </nav>
    </header>        
    
    <section>
        <p><%=msg%></p>
    </section>
    
    <!-- Footer -->
    <footer>
        <div id="footer"></div>
    </footer>

</body>

</html>
