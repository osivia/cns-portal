<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="internationalization" prefix="is" %>

<%@ page isELIgnored="false" %>


<!-- Link name -->
<div class="form-group">
    <label for="path" class="control-label col-sm-3"><is:getProperty key="FRAGMENT_TEST_PATH" /></label>
    <div class="col-sm-9">
        <input id="link-name" type="text" name="path" value="${path}" class="form-control" />
    </div>
</div>

