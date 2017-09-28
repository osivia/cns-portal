<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>


<c:set var="title"><op:translate key="SEARCH_TITLE" /></c:set>
<c:set var="placeholder"><op:translate key="SEARCH_PLACEHOLDER" /></c:set>
<c:set var="directoryTooltip"><op:translate key="DIRECTORY_TITLE" /></c:set>


<c:if test="${not empty requestScope['osivia.toolbar.principal']}">
    <div class="pull-right hidden-xs">
        <form action="${requestScope['osivia.search.url']}" method="get" class="form-inline" role="search">
            <input type="hidden" name="action" value="advancedSearch">
        
            <div class="form-group">
                <label class="sr-only" for="search-input"><op:translate key="SEARCH" /></label>
                <div class="input-group input-group-sm">
                    <input id="search-input" type="text" name="search" class="form-control" placeholder="${placeholder}">
                    <span class="input-group-btn">
                        <button type="submit" class="btn btn-default" title="${title}" data-toggle="tooltip" data-placement="bottom">
                            <i class="halflings halflings-search"></i>
                        </button>
                    </span>
                </div>
            </div>
            
            <a class="btn btn-default btn-sm" href="${requestScope['osivia.search.directoryURL']}" title="${directoryTooltip}" data-toggle="tooltip" data-placement="bottom">
                <i class="glyphicons glyphicons-address-book"></i>
                <span><op:translate key="DIRECTORY"/> </span>
            </a>
        </form>
    </div>
</c:if>
