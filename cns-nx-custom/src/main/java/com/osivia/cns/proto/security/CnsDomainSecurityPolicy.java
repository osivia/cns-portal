package com.osivia.cns.proto.security;

import java.security.Principal;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.DocumentException;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.core.api.security.ACP;
import org.nuxeo.ecm.core.api.security.Access;
import org.nuxeo.ecm.core.model.Document;
import org.nuxeo.ecm.core.query.sql.model.Operator;
import org.nuxeo.ecm.core.query.sql.model.Predicate;
import org.nuxeo.ecm.core.query.sql.model.Reference;
import org.nuxeo.ecm.core.query.sql.model.SQLQuery;
import org.nuxeo.ecm.core.query.sql.model.SQLQuery.Transformer;
import org.nuxeo.ecm.core.query.sql.model.StringLiteral;
import org.nuxeo.ecm.core.query.sql.model.WhereClause;
import org.nuxeo.ecm.core.security.AbstractSecurityPolicy;
import org.nuxeo.runtime.api.Framework;

/**
 * Domain restriction security policy
 * 
 * @author Dorian Licois
 *
 */
public class CnsDomainSecurityPolicy extends AbstractSecurityPolicy {

	private static final Log log = LogFactory.getLog(CnsDomainSecurityPolicy.class);
	
    private static final String RESTRICTED_DOMAIN_NAME = Framework.getProperty("cns.domainSecurity.restrictedDomain", "ged-cns");

    private static final String ALLOWED_PROFILE = Framework.getProperty("cns.domainSecurity.allowedProfiles", "CNS_commun-cns");

    private static final Transformer CNS_RESTRICTED_DOMAIN_TRANSFORMER = new CnsDomainSecurityTransformer();

    /**
	 * {@inheritDoc}
	 */
    @Override
    public Access checkPermission(Document doc, ACP mergedAcp, Principal principal, String permission, String[] resolvedPermissions,
            String[] additionalPrincipals) {

        Access access = Access.UNKNOWN;

        try {
            if (doPolicyApply(doc)) {
                access = restrictToProfile(doc, principal, additionalPrincipals);
            }
        } catch (DocumentException e) {
            log.error("Failed to evaluate the policy, error: " + e.getMessage());
        }


        return access;
    }
	
	
    /**
     * Deny any permission to users not
     * 
     * @param doc
     * @param principal
     * @param additionalPrincipals
     * @return
     */
    private Access restrictToProfile(Document doc, Principal principal, String[] additionalPrincipals) {

        String[] allPrincipals = principal != null ? (String[]) ArrayUtils.add(additionalPrincipals, principal.getName()) : additionalPrincipals;

        if (hasAllowedProfile(allPrincipals)) {
            return Access.UNKNOWN;
        } else {
            return Access.DENY;
        }
    }


    /**
     * Checks if any of the profiles are allowed
     * 
     * @param allPrincipals
     * @return
     */
    private boolean hasAllowedProfile(String[] allPrincipals) {
        for (String principal : allPrincipals) {
            if (StringUtils.equals(principal, ALLOWED_PROFILE)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Policy is applied if document is part of restricted domain.
     * 
     * @param doc
     * @return
     * @throws DocumentException
     */
    private static boolean doPolicyApply(Document doc) throws DocumentException {
        if (docIsInRestrictedDomain(doc)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Checks if doc is in a restricted domain
     * 
     * @param doc
     * @return
     * @throws DocumentException
     */
    private static boolean docIsInRestrictedDomain(Document doc) throws DocumentException {
        
        String domainName = null;

        String path = doc.getPath();
        path = StringUtils.removeStart(path, "/");
        if (StringUtils.isNotBlank(path)) {
            domainName = StringUtils.split(path, '/')[0];
        }
        if(StringUtils.equals(domainName, RESTRICTED_DOMAIN_NAME)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isExpressibleInQuery() {
        return true;
    }

    @Override
    public SQLQuery.Transformer getQueryTransformer() {
        return CNS_RESTRICTED_DOMAIN_TRANSFORMER;
    }

    /**
     * query transformer with restricted domain
     * 
     * @author Dorian Licois
     *
     */
    public static class CnsDomainSecurityTransformer implements SQLQuery.Transformer {
        
        /** serialVersionUID */
        private static final long serialVersionUID = 1L;
        
        private static final String TTC_DOMAINID = "ttc:domainID";

        private static final Predicate NOT_IN_DOMAIN = new Predicate(new Reference(TTC_DOMAINID), Operator.NOTEQ, new StringLiteral(RESTRICTED_DOMAIN_NAME));

        private boolean hasAllowedProfile(List<String> allGroups) {
            for (String principal : allGroups) {
                if (StringUtils.equals(principal, ALLOWED_PROFILE)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public SQLQuery transform(Principal principal, SQLQuery query) {
            
            NuxeoPrincipal nuxeoPrincipal = (NuxeoPrincipal) principal;
            if (!nuxeoPrincipal.isAdministrator() && !StringUtils.equals(principal.getName(), "system")) {
                List<String> allGroups = nuxeoPrincipal.getAllGroups();
                // if restricted profile
                if (!hasAllowedProfile(allGroups)) {
                    // apply NOT_IN_DOMAIN restriction
                    WhereClause where = query.where;
                    Predicate predicate;
                    if (where == null || where.predicate == null) {
                        predicate = NOT_IN_DOMAIN;
                    } else {
                        predicate = new Predicate(NOT_IN_DOMAIN, Operator.AND, where.predicate);
                    }
                    query = new SQLQuery(query.select, query.from, new WhereClause(predicate), query.groupBy, query.having, query.orderBy, query.limit,
                            query.offset);
                }
            }
            return query;
        }

    }
}
