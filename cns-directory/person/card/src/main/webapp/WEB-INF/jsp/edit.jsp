<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>

<%@ page contentType="text/html" isELIgnored="false" %>


<portlet:actionURL name="save" var="saveUrl" copyCurrentRenderParameters="true" />


<form:form action="${saveUrl}" method="post" modelAttribute="editionForm" enctype="multipart/form-data" cssClass="form-horizontal" role="form">
    <fieldset>
        <legend><op:translate key="PERSON_CARD_EDITION_LEGEND" /></legend>
    
        <!-- Avatar -->
        <%@ include file="edition/avatar.jspf" %>
        
        <!-- Title- -->
        <%@ include file="edition/title.jspf" %>
        
        <!-- First name -->
        <%@ include file="edition/first-name.jspf" %>
        
        <!-- Last name -->
        <%@ include file="edition/last-name.jspf" %>
        
        <!-- CNS occupation -->
        <%@ include file="edition/occupation.jspf" %>
        
        <!-- CNS entity -->
        <%@ include file="edition/entity.jspf" %>
        
        <!-- CNS administrative entity -->
        <%@ include file="edition/administrative-entity.jspf" %>
        
        <!-- Phone -->
        <%@ include file="edition/phone.jspf" %>
        
        <!-- Mobile phone -->
        <%@ include file="edition/mobile-phone.jspf" %>
        
        <!-- Mail -->
        <%@ include file="edition/mail.jspf" %>
        
        <!-- CNS generic mail -->
        <%@ include file="edition/generic-mail.jspf" %>
        
        <!-- CNS referer -->
        <%@ include file="edition/referer.jspf" %>
        
        <!-- Buttons -->
        <%@ include file="edition/buttons.jspf" %>
    </fieldset>
</form:form>
