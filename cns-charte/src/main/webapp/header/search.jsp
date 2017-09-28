<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>

<c:if test="${not empty requestScope['osivia.toolbar.principal']}">
	
	<script type="text/javascript">
	
	function onsubmitGlobalSearch(form) {
	   var searchUrl = "${requestScope['osivia.search.url']}";
	   
	   searchUrl = searchUrl.replace("__REPLACE_KEYWORDS__", form.keywords.value);
	   form.action = searchUrl;
	}
	
	</script>
	
	
	<c:set var="searchTitle"><op:translate key="SEARCH_TITLE"/></c:set>
	<c:set var="searchPlaceholder"><op:translate key="SEARCH_PLACEHOLDER"/> </c:set>
	
	
	<div class="pull-right hidden-xs">
	    <form class="form-inline" onsubmit="return onsubmitGlobalSearch(this);" method="post" role="search">
	        <div class="form-group">
	            <label class="sr-only" for="search-input"><op:translate key="SEARCH"/> </label>
	            <div class="input-group input-group-sm">
	                <input id="search-input" type="text" name="keywords" class="form-control" placeholder="${searchPlaceholder}">
	                <span class="input-group-btn">
	                    <button type="submit" class="btn btn-default" title="${searchTitle}" data-toggle="tooltip" data-placement="bottom">
	                        <i class="halflings halflings-search"></i>
	                    </button>
	                </span>
	            </div>
	        </div>
	        
	        <a class="btn btn-default btn-sm" href="${requestScope['osivia.search.directoryURL']}" title="<op:translate key="DIRECTORY_TITLE" />" data-toggle="tooltip" data-placement="bottom">
	        	<i class="glyphicons glyphicons-address-book"></i>
	    		<span><op:translate key="DIRECTORY"/></span>
	    	</a>
	    </form>
	</div>
</c:if>
