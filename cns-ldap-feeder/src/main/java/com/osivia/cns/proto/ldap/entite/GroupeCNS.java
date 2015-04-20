package com.osivia.cns.proto.ldap.entite;

import java.util.List;

public class GroupeCNS {

	private String cn;
	
	private List<String> listeMembres;
	
	private List<String> listeGestionnaires;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public List<String> getListeMembres() {
		return listeMembres;
	}

	public void setListeMembres(List<String> listeMembres) {
		this.listeMembres = listeMembres;
	}

	public List<String> getListeGestionnaires() {
		return listeGestionnaires;
	}

	public void setListeGestionnaires(List<String> listeGestionnaires) {
		this.listeGestionnaires = listeGestionnaires;
	}
	
	
}
