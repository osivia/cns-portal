package com.osivia.cns.proto.massvalidation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.model.PropertyNotFoundException;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventBundle;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.PostCommitEventListener;
import fr.toutatice.ecm.platform.core.helper.ToutaticeOperationHelper;
import fr.toutatice.ecm.platform.core.helper.ToutaticeSilentProcessRunnerHelper;
import fr.toutatice.ecm.platform.core.utils.exception.ToutaticeException;

/**
 * Gestion des traitements de masse, couche traitement asynchrone
 * @author Loïc Billon
 *
 */
public class MassValidationListener implements PostCommitEventListener {

	/** Param de contexte pour activer la validation */
	public static final String WITH_VALIDATION = "withValidation";
	
	
	private static final Log log = LogFactory
			.getLog(MassValidationListener.class);

	
	private class MassValidationExecutor extends ToutaticeSilentProcessRunnerHelper {

		private Event event;

		public MassValidationExecutor(CoreSession session, Event event) {
			super(session);
			
			this.event = event;
			
		}

		@Override
		public void run() throws ClientException {
			

			EventContext ctx = event.getContext();
			
			Object rawlist = ctx.getArguments()[0];
	        DocumentModelList listToSave = (DocumentModelList) rawlist;
			
	        log.info("Application des metadonnées ["+event.getTime()+"]");
	        
	        // Calcul des métadonnées à appliquer
	        Map<String, Serializable> propertiesToChange = new HashMap<String, Serializable>();
	        
	        for(Map.Entry<String, Serializable> property : ctx.getProperties().entrySet()) {
	        	
	        	// Application des propriétés de document (avec :), et étant valorisées.
	        	if(property.getKey().contains(":") && property.getValue() != null) {
	        		
	        		if(property.getValue() instanceof String) {
		        		if(StringUtils.isNotBlank(property.getValue().toString())) {
		        			propertiesToChange.put(property.getKey(), property.getValue().toString());
		        		}
	        		}
	        		else {
	        			propertiesToChange.put(property.getKey(), property.getValue());
	        		}
	        		log.info(property.getKey() + "->"+property.getValue());
	        	}
	        }
	
	        CoreSession coreSession = ctx.getCoreSession();

	        for(DocumentModel child : listToSave) {
	        	
	        	try {
	        		
	        		// Application des méta données
	                for(Map.Entry<String, Serializable> property : propertiesToChange.entrySet()) {
	                	
	                	child.setPropertyValue(property.getKey(), property.getValue());
	                	
	                }
	                
	            	log.info("Traitement de "+child.getPathAsString());

	        	}
	        	catch(PropertyNotFoundException pnfe) {
	        		log.error("Traitement de "+child.getPathAsString()+" "+pnfe.getMessage() );
	        	}
	        	
	        }
	        
	        
	        // Application en masse
	        DocumentModel[] t = new DocumentModel[listToSave.size()];
	        coreSession.saveDocuments(listToSave.toArray(t));
	        
	        log.info("Fin de l'appliquation des metadonnées ["+event.getTime()+"]");
	        
	        if(ctx.getProperty(WITH_VALIDATION).equals(Boolean.TRUE)) {
	        	
	        	log.info("Validation en masse ["+event.getTime()+"]");
	        	
	        	for(DocumentModel docToSave : listToSave) {

		        	try {
						ToutaticeOperationHelper.runOperationChain( docToSave.getCoreSession(), "validateDocument", docToSave);
					} catch (ToutaticeException e) {
						log.error("Traitement de "+docToSave.getPathAsString()+" "+e.getMessage() );
					}
		        }
	        	log.info("Fin de la validation en masse ["+event.getTime()+"]");
	        	
	        }
		}
		
	}
	
	
	
	
	
	@Override
	public void handleEvent(EventBundle events) throws ClientException {
		
		for(Event event : events) {
		
			MassValidationExecutor executor = new MassValidationExecutor(event.getContext().getCoreSession(), event);
			executor.silentRun(true);
		}
	}


}
