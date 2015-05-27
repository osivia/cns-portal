<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib uri="toutatice" prefix="ttc" %>

<%@ page isELIgnored="false"%>


<c:set var="vignetteURL"><ttc:getImageURL document="${document}" property="ttc:vignette" /></c:set>
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
                <span><is:getProperty key="METADATA" /></span>
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
                        <dt><is:getProperty key="AUTHOR" /></dt>
                        <dd><ttc:user name="${author}"/></dd>
                        
                        <!-- Publication date -->
                        <dt><is:getProperty key="DOCUMENT_PUBLICATION_DATE" /></dt>
                        <dd><fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="short" /></dd>
                        
                        <!-- Keywords -->
                        <dt><is:getProperty key="DOCUMENT_KEYWORDS" /></dt>
                        <dd>
                            <c:forEach items="${keywords}" var="keyword">
                                <span class="label label-default">${keyword}</span>
                            </c:forEach>
                        </dd>
                        
                        <!-- Document state -->
                        <dt><is:getProperty key="DOCUMENT_STATE" /></dt>
                        <dd>
                            <c:if test="${document.document.state eq 'project'}">
                                <span class="label label-info"><is:getProperty key="DOCUMENT_STATE_PROJECT" /></span>
                            </c:if>
                            <c:if test="${document.document.state eq 'approved'}">
                                <span class="label label-success"><is:getProperty key="DOCUMENT_STATE_APPROVED" /></span>
                            </c:if>
                        </dd>
                    </dl>
                     
                    <hr>
                    
                    <dl class="dl-horizontal">
                        <!-- Cycle de vie -->
                        <dt><is:getProperty key="META_CYCLE_VIE" /></dt>
                        <dd><ttc:vocabularyLabel name="cycle_vie" key="${cycle_vie}"/></dd>
                        
                        <!-- Métiers -->
                        <dt><is:getProperty key="META_METIER" /></dt>
                        <dd><ttc:vocabularyLabel name="metier" key="${metier}"/></dd>
                        
                        <!-- Nature -->
                        <dt><is:getProperty key="META_NATURE" /></dt>
                        <dd><ttc:vocabularyLabel name="nature" key="${nature}"/></dd>
                        
                        <!-- SI cible -->
                        <dt><is:getProperty key="META_SI" /></dt>
                        <dd><ttc:vocabularyLabel name="s_info_associe" key="${s_info_associe}"/></dd>
                        
                        <!-- Entité source -->
                        <dt><is:getProperty key="META_ENTITE" /></dt>
                        <dd><ttc:vocabularyLabel name="entite_cns" key="${entite_cns}"/></dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>
