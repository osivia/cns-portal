<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="internationalization" prefix="is" %>
<%@ taglib uri="toutatice" prefix="ttc" %>

<%@ page isELIgnored="false"%>


<c:set var="vignetteURL"><ttc:getImageURL document="${document}" property="ttc:vignette" /></c:set>
<c:set var="author" value="${document.properties['dc:creator']}" />

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
                    <!-- Author -->
                    <p>
                        <strong><is:getProperty key="AUTHOR" /></strong>
                        <span> : </span>
                        <ttc:user name="${author}"/>
                    </p>
                    
                    <!-- Publication date -->
                    <p>
                        <strong><is:getProperty key="DOCUMENT_PUBLICATION_DATE" /></strong>
                        <span> : </span>
                        <span><fmt:formatDate value="${date}" type="both" dateStyle="full" timeStyle="short" /></span>
                    </p>

                    <p>
                        <strong><is:getProperty key="DOCUMENT_STATE" /></strong>
                        <span> :  </span>
                       	<c:if test="${document.document.state == 'project'}">
							<span class="label label-info"><is:getProperty key="DOCUMENT_STATE_PROJECT" /></span>
                       	</c:if>
                       	<c:if test="${document.document.state == 'approved'}">
                       		<span class="label label-success"><is:getProperty key="DOCUMENT_STATE_APPROVED" /></span>
                       	</c:if>
                        
                       
                        
                    </p>

                     
                    <hr />
                    
                    <!-- Meta données CNS -->
                    <!--  Cycle de vie-->
                    <p>
                        <strong><is:getProperty key="META_CYCLE_VIE" /></strong>
                        <span> : </span>
                        <span><ttc:vocabularyLabel name="cycle_vie" key="${cycle_vie}"/></span>
                    </p>
                    
                    <!--  Métiers-->
                    <p>
                        <strong><is:getProperty key="META_METIER" /></strong>
                        <span> : </span>
                        <span><ttc:vocabularyLabel name="metier" key="${metier}"/></span>
                    </p>                    
                    <!--  Nature-->
                    <p>
                        <strong><is:getProperty key="META_NATURE" /></strong>
                        <span> : </span>
                        <span><ttc:vocabularyLabel name="nature" key="${nature}"/></span>
                    </p>
                    <!--  SI cible-->
                    <p>
                        <strong><is:getProperty key="META_SI" /></strong>
                        <span> : </span>
                        <span><ttc:vocabularyLabel name="s_info_associe" key="${s_info_associe}"/></span>
                    </p>
                    <!--  Entite source--> 
                    <p>
                        <strong><is:getProperty key="META_ENTITE" /></strong>
                        <span> : </span>
                        <span><ttc:vocabularyLabel name="entite_cns" key="${entite_cns}"/></span>
                    </p>
                                        
                </div>
            </div>
        </div>
    </div>
</div>
