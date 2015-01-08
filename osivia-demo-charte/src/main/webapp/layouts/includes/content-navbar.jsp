<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib uri="portal-layout" prefix="p" %>


<div class="content-navbar">
    <!-- Breadcrumb -->
    <div class="content-navbar-breadcrumb">
        <p:region regionName="breadcrumb" />
    </div>
    
    <!-- Actions -->
    <div class="content-navbar-actions">
        <p:region regionName="content-navbar-actions" />
    </div>
</div>
