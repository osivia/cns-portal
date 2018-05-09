package com.osivia.cns.proto.massvalidation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.impl.DocumentModelListImpl;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.EventProducer;
import org.nuxeo.ecm.core.event.impl.EventContextImpl;
import org.nuxeo.ecm.platform.routing.api.DocumentRoutingService;
import org.nuxeo.ecm.platform.ui.web.api.NavigationContext;
import org.nuxeo.runtime.api.Framework;

import com.osivia.cns.proto.constants.CnsConstants;
import com.osivia.cns.proto.constants.ExtendedSeamPrecedence;
import com.osivia.cns.proto.massvalidation.DocumentToModifyDTO.DocumentToModifyState;

/**
 * Gestion des traitements de masse, couche UI
 * @author Loïc Billon
 *
 */
@Name("massValidation")
@Scope(ScopeType.CONVERSATION)
@Install(precedence = ExtendedSeamPrecedence.CUSTOM)
public class MassValidationBean {


	private static final String MASS_VALIDATION_EVENT = "massValidationEvent";
	
	private static final String PV_MASS_VALIDATION_CHOOSE = "mass_validation";
	private static final String PV_MASS_VALIDATION = "mass_validation_2";
    private static final String PV_MASS_VALIDATION_CONFIRM = "mass_validation_3";

	private static final String PV_MASS_VAL_CHOOSE_DOCS = "mass_validation_choose_docs";
	

	@In(create = true, required = false)
    protected transient CoreSession documentManager;

    /** nagivation context for nuxeo queries */
    @In(create = true)
    protected transient NavigationContext navigationContext;
	
	
	private Map<String,Serializable> propertiesToModify;
	
	public Integer getNbDocToModify() {
		
		Integer nbDocToModify = 0;
		for(DocumentToModifyDTO d : docsToModify) {
			if(d.getApplyModifications()) {
				nbDocToModify++;
			}
		}
		
		return nbDocToModify;
	}
	
	private Boolean withValidation = null;

	private List<DocumentToModifyDTO> docsToModify = new ArrayList<DocumentToModifyDTO>();;
	

	public List<DocumentToModifyDTO> getDocsToModify() {
		return docsToModify;
	}

	public void setDocsToModify(List<DocumentToModifyDTO> docsToModify) {
		this.docsToModify = docsToModify;
	}

	public Boolean getWithValidation() {
		return withValidation;
	}

	public void setWithValidation(Boolean withValidation) {
		this.withValidation = withValidation;
	}

	/**
	 * Ecran 1 : choix validation
	 * @return vue JSF
	 */
	public String goToChooseValidation() {
		return PV_MASS_VALIDATION_CHOOSE;
	}
	
	/**
	 * Ecran 2 : choix documents
	 * @return vue JSF
	 */
	public String goToChooseDocs() {
		
		docsToModify.clear();
		
		DocumentModel currentFolder = navigationContext.getCurrentDocument();
		
        DocumentModelList docs = documentManager.query("SELECT * FROM Document WHERE ecm:path STARTSWITH '"+currentFolder.getPathAsString()+"' AND ecm:mixinType <> 'Folderish'"
        		+ " AND ecm:isVersion = 0 AND ecm:mixinType != 'HiddenInNavigation' AND ecm:currentLifeCycleState != 'deleted'  ORDER BY ecm:path");
        
                
        DocumentRoutingService routingService = Framework.getService(DocumentRoutingService.class);
        
        for(DocumentModel child : docs) {
        	
        	DocumentToModifyDTO d = new DocumentToModifyDTO();
        	
        	d.setDoc(child);
        	if(!child.getCurrentLifeCycleState().equals("project")) {
        		d.setAvaliable(DocumentToModifyState.VALIDATED);
        		d.setApplyModifications(false);
        	}
        	else if (routingService.getDocumentRoutesForAttachedDocument(documentManager, child.getId()).size() > 0) {
        		d.setAvaliable(DocumentToModifyState.IN_WORKFLOW);
        		d.setApplyModifications(false);
        	}
        	else {
        		d.setAvaliable(DocumentToModifyState.AVALIABLE);
        	}
        	
        	DocumentModel parent = documentManager.getDocument(child.getParentRef());
        	String parentTitle = "";
        	
        	while(parent != null && (!parent.getId().equals(currentFolder.getId()))) {
        		parentTitle = parent.getTitle() + " >> " + parentTitle;
        		
        		parent = documentManager.getDocument(parent.getParentRef());
        	}
        	
        	d.setParentTitle(parentTitle);
        	
        	docsToModify.add(d);
        }
        
     
       	return PV_MASS_VAL_CHOOSE_DOCS;
	}
	
	/**
	 * Ecran 3 : choix méta données
	 * @return vue JSF
	 */
	public String goToStartValidation() {
		return PV_MASS_VALIDATION;
	}
	
	/**
	 * Retour écran 2
	 * @return
	 */
	public String backToChoose() {
		
       	return PV_MASS_VALIDATION_CHOOSE;
	}
	
	/**
	 * Ecran 4 : écran confirmation
	 * @return vue JSF
	 */
	public String validate() {

		DocumentModel doc = navigationContext.getCurrentDocument();
		
		propertiesToModify = new HashMap<String, Serializable>();
		
		propertiesToModify.put(CnsConstants.CNS_CYCLE_VIE ,doc.getPropertyValue(CnsConstants.CNS_CYCLE_VIE));
		propertiesToModify.put(CnsConstants.CNS_SUBJECTS,doc.getPropertyValue(CnsConstants.CNS_SUBJECTS));
		propertiesToModify.put(CnsConstants.CNS_NATURE,doc.getPropertyValue(CnsConstants.CNS_NATURE));
		propertiesToModify.put(CnsConstants.CNS_S_INFO_ASSOCIE,doc.getPropertyValue(CnsConstants.CNS_S_INFO_ASSOCIE));
		propertiesToModify.put(CnsConstants.CNS_SOURCE,doc.getPropertyValue(CnsConstants.CNS_SOURCE));
		
	
		return PV_MASS_VALIDATION_CONFIRM;
	}
	
	/**
	 * Ecran 4 : Traitement en cours
	 * @return vue JSF
	 */
	public String confirmValidate() {
		
		EventProducer eventProducer;
		try {
		    eventProducer = Framework.getService(EventProducer.class);
		} catch (Exception e) {
		    return PV_MASS_VALIDATION_CONFIRM;
		}
		 
		DocumentModelList listToModify = new DocumentModelListImpl();
		for(DocumentToModifyDTO d : docsToModify) {
			if(d.getApplyModifications()) {
				listToModify.add(d.getDoc());
			}
		}
		
		EventContext ctx = new EventContextImpl(documentManager, documentManager.getPrincipal(), listToModify);
		ctx.setProperties(propertiesToModify);
		ctx.setProperty(MassValidationListener.WITH_VALIDATION, withValidation);
		 
		Event event = ctx.newEvent(MASS_VALIDATION_EVENT);
		try {
		    eventProducer.fireEvent(event);
		} catch (ClientException e) {
		    return PV_MASS_VALIDATION_CONFIRM;
		}
		
		return "done";
	}
}
