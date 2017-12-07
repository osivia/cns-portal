<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.osivia.org/jsp/taglib/osivia-portal" prefix="op" %>

<%@ page isELIgnored="false" %>


<!-- Link name -->
<div class="form-group">
    <label for="path" class="control-label col-sm-3"><op:translate key="FRAGMENT_TEST_PATH" /></label>
    <div class="col-sm-9">
        <input id="link-name" type="text" name="path" value="${path}" class="form-control" />
    </div>
</div>

