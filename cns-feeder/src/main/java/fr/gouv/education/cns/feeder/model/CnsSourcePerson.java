package fr.gouv.education.cns.feeder.model;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.stereotype.Component;

/**
 * CNS source person
 * 
 * @author Cédric Krommenhoek
 * @see Serializable
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entry(objectClasses = "comptetec")
public final class CnsSourcePerson implements Serializable {

    /** Default serial version identifier. */
    private static final long serialVersionUID = 1L;


    /** DN. */
    @Id
    private Name dn;

    /** UID. */
    @Attribute
    private String uid;

    /** CN. */
    @Attribute
    private String cn;

    /** SN. */
    @Attribute
    private String sn;

    /** Given name. */
    @Attribute
    private String givenName;

    /** Email. */
    @Attribute
    private String mail;

    /** Entity. */
    @Attribute(name = "ou")
    private String entity;


    /**
     * Constructor.
     */
    public CnsSourcePerson() {
        super();
    }


    /**
     * Getter for dn.
     * 
     * @return the dn
     */
    public Name getDn() {
        return dn;
    }

    /**
     * Setter for dn.
     * 
     * @param dn the dn to set
     */
    public void setDn(Name dn) {
        this.dn = dn;
    }

    /**
     * Getter for uid.
     * 
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Setter for uid.
     * 
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Getter for cn.
     * 
     * @return the cn
     */
    public String getCn() {
        return cn;
    }

    /**
     * Setter for cn.
     * 
     * @param cn the cn to set
     */
    public void setCn(String cn) {
        this.cn = cn;
    }

    /**
     * Getter for sn.
     * 
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * Setter for sn.
     * 
     * @param sn the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * Getter for givenName.
     * 
     * @return the givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Setter for givenName.
     * 
     * @param givenName the givenName to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Getter for mail.
     * 
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Setter for mail.
     * 
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Getter for entity.
     * 
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * Setter for entity.
     * 
     * @param entity the entity to set
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

}
