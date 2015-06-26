package com.osivia.cns.proto.ldap.dao;

import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import com.osivia.cns.proto.ldap.entite.PersonneCNS;

@Repository
@Scope("singleton")
public class PersonneCNSDao {
	
	protected static final Log logger = LogFactory.getLog(PersonneCNSDao.class);
	
	@Autowired
	private ApplicationContext context;
	

	@Autowired
	@Qualifier("ldapTemplateLecture")
	private LdapTemplate ldapTemplateLecture;
	
	private String uid ="";
	
	private String cn ="";
	
	private String sn ="";
	
	private String givenName ="";
	
	private String displayName ="";
	
	private String mail ="";
	
	private String entite ="";

	private String categorie ="";
	private String classObjet ="";
	private String BASE_DN="";	

	
	//Classe interne permettant de récupérer un objet Personne depuis l'annuaire
	private class PersonAttributMapper implements AttributesMapper {
		@SuppressWarnings("rawtypes")
		public Object mapFromAttributes(Attributes attrs)
				throws javax.naming.NamingException {
			
			//instanciation d'un objet Person va Spring (ne pas utiliser le constructeur par défaut !)
			PersonneCNS p = (PersonneCNS) context.getBean("personneCns");
			
			Attribute attr = attrs.get(uid);
			if (attr != null)
			{
				p.setUid(attr.get().toString());
			}
			else {logger.error("Erreur d'accès à l'annuaire : recherche de personne");}
			
			attr = attrs.get(givenName);
			if (attr != null) {
				p.setGivenName(attr.get().toString());
			}
			else { 
				p.setGivenName("");}
			
			attr = attrs.get(displayName);
			if (attr != null) {
				p.setDisplayName(attr.get().toString());
			}
			else { 
				p.setDisplayName("");}

			attr = attrs.get(cn);
			if (attr != null)
			{
				p.setCn(attr.get().toString());
			}
			else { 
				p.setCn("");}
			
			attr = attrs.get(entite);
			if (attr != null)
			{
				p.setEntite(attr.get().toString());
			}
			else { 
				p.setEntite("");}			
			
			attr = attrs.get(sn);
			if (attr != null)
			{
				p.setSn(attr.get().toString());
			}
			else { 
				p.setSn("");}
			
	
			
			attr = attrs.get(mail);
			if (attr != null) {
				p.setMail(attr.get().toString());
			}
			else { 
				p.setMail("");}

		
			return p;
		}
	}
	
	/**
	 * Construit le DN d'une personne à partir de son id
	 * @param uid identifiant de la personne dont on veut le DN
	 * @return DN de la personne (sans la base de l'annuaire LDAP, défini dans le fichier properties)
	 */
	private Name buildDn(String uid) {
		
		DistinguishedName dn = new DistinguishedName();
		dn.add("ou", categorie);
		dn.add(this.uid, uid);
		return dn;
	}
	
	
	public PersonneCNS findByPrimaryKey(String uid){
		
		logger.debug("findByPrimaryKey/" + uid);

		PersonneCNS person = null;
		if(!uid.trim().isEmpty()){
			
			Name dn = buildDn(uid);
			
			try {
				PersonAttributMapper personAttributMapper = new PersonAttributMapper();
				person = (PersonneCNS) ldapTemplateLecture.lookup(dn, personAttributMapper);
			} 
			catch (NameNotFoundException e) {
				logger.warn("Recherche d'une personne dans l'annuaire : l'identifiant n'existe pas " + uid);
			}

		}
		return person;
	}
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	

	/**
	 * @return the entite
	 */
	public String getEntite() {
		return entite;
	}


	/**
	 * @param entite the entite to set
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public String getBASE_DN() {
		return BASE_DN;
	}


	public void setBASE_DN(String bASE_DN) {
		this.BASE_DN = bASE_DN;
	}


	public String getClassObjet() {
		return classObjet;
	}


	public void setClassObjet(String classObjet) {
		this.classObjet = classObjet;
	}
	
	
		
}
