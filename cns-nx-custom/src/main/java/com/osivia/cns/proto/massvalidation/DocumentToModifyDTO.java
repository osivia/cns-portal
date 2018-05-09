package com.osivia.cns.proto.massvalidation;

import org.nuxeo.ecm.core.api.DocumentModel;

/**
 * DTO pour validation en masse
 * @author Loïc Billon
 *
 */
public class DocumentToModifyDTO {
	
	/** Etat brouillon validé ou en cours de validation */
	public enum DocumentToModifyState{
		AVALIABLE, VALIDATED, IN_WORKFLOW;
	}

	private DocumentModel doc;
	
	/** checkbox application des modifications */
	private Boolean applyModifications = true;
	
	private DocumentToModifyState avaliable;
	
	private String parentTitle;

	public DocumentModel getDoc() {
		return doc;
	}

	public void setDoc(DocumentModel doc) {
		this.doc = doc;
	}

	public Boolean getApplyModifications() {
		return applyModifications;
	}

	public void setApplyModifications(Boolean applyModifications) {
		this.applyModifications = applyModifications;
	}

	

	public DocumentToModifyState getAvaliable() {
		return avaliable;
	}

	public void setAvaliable(DocumentToModifyState avaliable) {
		this.avaliable = avaliable;
	}

	public String getParentTitle() {
		return parentTitle;
	}

	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}

	
	
	
}
