<%@ taglib uri="portal-layout" prefix="p" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>


<!-- Toolbar -->
<p:region regionName="toolbar" />

<header class="container-fluid hidden-xs">
    <!-- Banner -->
    <div class="banner clearfix">
        <!-- Logo -->
        <div class="logo">
            <p:region regionName="logo" />
        </div>

        <!-- Recherche -->
        <div class="search">
            <p:region regionName="search" />
        </div>
    </div>

    <!-- Onglets -->
    <p:region regionName="tabs" />
</header>
