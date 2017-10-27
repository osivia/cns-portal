package fr.gouv.education.cns.feeder.dao;

import javax.naming.Name;
import javax.naming.ldap.Rdn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.LdapTemplate;
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


    /**
     * Constructor.
     */
    public CnsSourcePersonDao() {
        super();
        this.baseDn = LdapNameBuilder.newInstance(System.getProperty("ldapSource.base")).add("ou=personnes").build();
    }


    /**
     * Get CNS person.
     * 
     * @param uid CNS person UID.
     * @return CNS person
     */
    public CnsSourcePerson getPerson(String uid) {
        Name dn = LdapNameBuilder.newInstance(this.getBaseDn()).add("uid=" + Rdn.escapeValue(uid)).build();
        return this.cnsLdapTemplate.findByDn(dn, this.sample.getClass());
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
