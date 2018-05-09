/**
 * 
 */
package com.osivia.cns.proto.document;

import static org.jboss.seam.ScopeType.CONVERSATION;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.userworkspace.constants.UserWorkspaceConstants;
import org.nuxeo.ecm.webapp.edit.lock.LockActions;
import org.nuxeo.runtime.api.Framework;
import org.opentoutatice.ecm.attached.images.bean.OttcDocumentActionsBean;

import com.osivia.cns.proto.constants.CnsConstants;
import com.osivia.cns.proto.constants.ExtendedSeamPrecedence;


/**
 * @author david
 *
 */
// TODO: to put in opentoutatice-web later
@Name("documentActions")
@Scope(CONVERSATION)
@Install(precedence = ExtendedSeamPrecedence.CUSTOM + 100)
public class CnsDocumentActionsBean extends OttcDocumentActionsBean {

    private static final long serialVersionUID = 1804328063431792756L;
    
    /** Public domain's name. */
    private static final String PUBLIC_DOMAIN_NAME = "/" + Framework.getProperty("cns.public.domain", CnsConstants.FORUMS_DOMAIN_NAME);

    @In(create = true)
    private LockActions lockActions;
    
    /**
     * @param doc
     * @return true if document is in a UserWorkspace.
     */
    public boolean isInUserWorkspace(DocumentModel doc){
        return StringUtils.contains(doc.getPathAsString(), UserWorkspaceConstants.USERS_PERSONAL_WORKSPACES_ROOT);
    }
    
    /**
     * Appliquer un verrou au document après enregistrement
     * @return
     * @throws ClientException
     */
    public String saveAndLockDocument(String viewId) throws ClientException {
    	saveDocument();
    	
    	lockActions.lockCurrentDocument();
    	
        return viewId;
    }
    
    /**
     * Appliquer un verrou au document après enregistrement
     * @return
     * @throws ClientException
     */
    public String updateAndLockDocument(String viewId) throws ClientException {
    	updateCurrentDocument();
    	
    	lockActions.lockCurrentDocument();
    	
        return viewId;
    }
    
    /**
     * Test si le document en création est folderish
     * @return
     */
    public boolean getCanLockChangeableDocument() {
    	DocumentModel changeableDocument = navigationContext.getChangeableDocument();
		return !(changeableDocument.hasFacet("Folderish"));
    	
    }

    /**
     * Do not display CNS metatdata in public Domain.
     */
    public boolean displayMetadata() {
        // Check if current document is in public domain
        String currentDomainPath = this.navigationContext.getCurrentDomainPath();
        return !StringUtils.startsWith(currentDomainPath, PUBLIC_DOMAIN_NAME);
    }

}
