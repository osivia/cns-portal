package com.osivia.cns.proto.ldap.entite;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value="personneCns")
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class PersonneCNS {

	private String uid;
	
	private String cn;
	
	private String sn;
	
	private String givenName;
	
	private String displayName;
	
	private String mail;
	
	private String entite;

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
	
	
	
}
