<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>
<%@ taglib uri="http://www.toutatice.fr/jsp/taglib/toutatice" prefix="ttc"%>

<%@ page isELIgnored="false"%>


<c:set var="vignetteURL"><ttc:pictureLink document="${document}" property="ttc:vignette" /></c:set>
<c:set var="author" value="${document.properties['dc:creator']}" />

<c:set var="keywords" value="${document.properties['ttc:keywords']}"></c:set>

<c:set var="date" value="${document.properties['dc:modified']}" />
<c:if test="${empty date}">
    <c:set var="date" value="${document.properties['dc:created']}" />
</c:if>

<c:set var="cycle_vie" value="${document.properties['cns:cycle_vie']}" />
<c:set var="metier" value="${document.properties['dc:subjects']}" />
<c:set var="nature" value="${document.properties['dc:nature']}" />
<c:set var="s_info_associe" value="${document.properties['cns:s_info_associe']}" />
<c:set var="entite_cns" value="${document.properties['dc:source']}" />


<hr>

<div class="metadata">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="halflings halflings-tags"></i>
                <span><op:translate key="METADATA" /></span>
            </h3>
        </div>
    
        <div class="panel-body">
            <div class="media">
                <!-- Vignette -->
                <c:if test="${not empty vignetteURL}">
                    <div class="media-left">
                        <img src="${vignetteURL}" alt="" class="img-responsive">
                    </div>
                </c:if>
                
                <div class="media-body">
                    <dl class="dl-horizontal">
                        <!-- Author -->
                        <dt><op:translate key="AUTHOR" /></dt>
                        <dd><ttc:user name="${author}"/></dd>
                        
                        <!-- Publication date -->
                        <dt><op:translate key="DOCUMENT_PUBLICATION_DATE" /></dt>
                        <dd><fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="short" /></dd>
                        
                        <!-- Keywords -->
                        <dt><op:translate key="DOCUMENT_KEYWORDS" /></dt>
                        <dd>
                            <c:forEach items="${keywords}" var="keyword">
                                <span class="label label-default">${keyword}</span>
                            </c:forEach>
                        </dd>
                        
                        <!-- Document state -->
                        <dt><op:translate key="DOCUMENT_STATE" /></dt>
                        <dd>
                            <c:if test="${document.document.state eq 'project'}">
                                <span class="label label-info"><op:translate key="DOCUMENT_STATE_PROJECT" /></span>
                            </c:if>
                            <c:if test="${document.document.state eq 'approved'}">
                                <span class="label label-success"><op:translate key="DOCUMENT_STATE_APPROVED" /></span>
                            </c:if>
                        </dd>
                        <!-- Remote publication spaces -->
                	    <c:set var="doc" value="${document}" scope="request" />
                        <jsp:include page="metadata-remote-sections.jsp" />
                    </dl>
                     
                    <hr>
                    
                    <dl class="dl-horizontal">
                        <!-- Cycle de vie -->
                        <dt><op:translate key="META_CYCLE_VIE" /></dt>
                        <dd><ttc:vocabularyLabel name="[CNS] Cycle de vie" key="${cycle_vie}"/></dd>
                        
                        <!-- Métiers -->
                        <dt><op:translate key="META_METIER" /></dt>
                        <dd><ttc:vocabularyLabel name="[CNS] Metier" key="${metier}"/></dd>
                        
                        <!-- Nature -->
                        <dt><op:translate key="META_NATURE" /></dt>
                        <dd><ttc:vocabularyLabel name="[CNS] Nature" key="${nature}"/></dd>
                        
                        <!-- SI cible -->
                        <dt><op:translate key="META_SI" /></dt>
                        <dd><ttc:vocabularyLabel name="[CNS] SI" key="${s_info_associe}"/></dd>
                        
                        <!-- Entité source -->
                        <dt><op:translate key="META_ENTITE" /></dt>
                        <dd><ttc:vocabularyLabel name="[CNS] Entité" key="${entite_cns}"/></dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>
