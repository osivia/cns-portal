package fr.gouv.education.cns.feeder.dao;

import java.util.List;

import javax.naming.Name;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.Rdn;

import org.osivia.directory.v2.MappingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Repository;

import fr.gouv.education.cns.feeder.model.CnsSourcePerson;

/**
 * CNS source person DAO.
 * 
 * @author Cédric Krommenhoek
 */
@Repository
public class CnsSourcePersonDao {

    /** CNS source LDAP template. */
    @Autowired
    @Qualifier("cnsLdapTemplate")
    private LdapTemplate cnsLdapTemplate;
    
    /** CNS source person sample. */
    @Autowired
    private CnsSourcePerson sample;


    /** Base DN. */
    private final Name baseDn;

    /** Application. */
    @Autowired
    protected ApplicationContext context;
    
    /**
     * Constructor.
     */
    public CnsSourcePersonDao() {
        super();
        this.baseDn = LdapNameBuilder.newInstance(System.getProperty("ldapSource.base")).build();
    }

    
    /**
     * Get CNS person.
     * 
     * @param uid CNS person UID.
     * @return CNS person
     */
    public List<CnsSourcePerson> findPersonByUid(String uid) {
    	
    	CnsSourcePerson person = context.getBean(this.sample.getClass());
    	person.setUid(uid);
    	
    	OrFilter filter = MappingHelper.generateOrFilter(person);
    	
    	SearchControls searchControls = new SearchControls();
    	searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		return (List<CnsSourcePerson>) this.cnsLdapTemplate.find(getBaseDn(), filter, searchControls , this.sample.getClass());
    }

    /**
     * Getter for baseDn.
     * 
     * @return the baseDn
     */
    public Name getBaseDn() {
        return baseDn;
    }

}
