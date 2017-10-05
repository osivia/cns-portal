<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>
<%@ taglib uri="http://www.toutatice.fr/jsp/taglib/toutatice" prefix="ttc" %>


<c:if test="${not fn:startsWith(document.path, '/forums')}">
    <!-- Cycle de vie -->
    <dt><op:translate key="META_CYCLE_VIE" /></dt>
    <dd><ttc:vocabularyLabel name="[CNS] Cycle de vie" key="${document.properties['cns:cycle_vie']}"/></dd>
    
    <!-- Métiers -->
    <dt><op:translate key="META_METIER" /></dt>
    <dd><ttc:vocabularyLabel name="[CNS] Metier" key="${document.properties['dc:subjects']}"/></dd>
    
    <!-- Nature -->
    <dt><op:translate key="META_NATURE" /></dt>
    <dd><ttc:vocabularyLabel name="[CNS] Nature" key="${document.properties['dc:nature']}"/></dd>
    
    <!-- SI cible -->
    <dt><op:translate key="META_SI" /></dt>
    <dd><ttc:vocabularyLabel name="[CNS] SI" key="${document.properties['cns:s_info_associe']}"/></dd>
    
    <!-- Entité source -->
    <dt><op:translate key="META_ENTITE" /></dt>
    <dd><ttc:vocabularyLabel name="[CNS] Entité" key="${document.properties['dc:source']}"/></dd>
</c:if>
