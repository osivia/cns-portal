<%@ taglib uri="internationalization" prefix="is" %>


<div>
    <p class="lead text-danger">
        <i class="glyphicons halflings exclamation-sign"></i>
        <span><is:getProperty key="ERROR_GENERIC_MESSAGE" /></span>
    </p>
    
    <p>${err}</p>
</div>