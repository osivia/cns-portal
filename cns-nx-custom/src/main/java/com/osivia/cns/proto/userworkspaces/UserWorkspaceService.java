package com.osivia.cns.proto.userworkspaces;

import java.security.Principal;

import org.nuxeo.drive.service.NuxeoDriveManager;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.api.security.ACE;
import org.nuxeo.ecm.core.api.security.ACL;
import org.nuxeo.ecm.core.api.security.ACP;
import org.nuxeo.ecm.core.api.security.SecurityConstants;
import org.nuxeo.ecm.core.api.security.impl.ACLImpl;
import org.nuxeo.ecm.core.api.security.impl.ACPImpl;
import org.nuxeo.ecm.platform.usermanager.NuxeoPrincipalImpl;
import org.nuxeo.ecm.platform.userworkspace.core.service.DefaultUserWorkspaceServiceImpl;
import org.nuxeo.runtime.api.Framework;

public class UserWorkspaceService extends DefaultUserWorkspaceServiceImpl {

	/**
	 * 
	 */
	private static final String TTC_SHOW_IN_MENU = "ttc:showInMenu";
	/**
	 * 
	 */
	private static final String DC_TITLE = "dc:title";
	/**
	 * 
	 */
	private static final long serialVersionUID = 4621010585970187579L;

	
	@Override
	protected DocumentModel doCreateUserWorkspace(
			CoreSession unrestrictedSession, PathRef wsRef,
			Principal principal, String userName) throws ClientException {
		DocumentModel userWorkspace = super.doCreateUserWorkspace(unrestrictedSession, wsRef, principal,
				userName);
		
		
		
		// Paramétrage affichage portail
		userWorkspace.setProperty("toutatice", "pageTemplate", "/templates/userWorkspace");
		userWorkspace.setProperty("toutatice", "tabOrder", "100");
		userWorkspace.setProperty("toutatice", "isPreloadedOnLogin", true);
		userWorkspace.setPropertyValue(DC_TITLE, "Mon espace");
		
		unrestrictedSession.saveDocument(userWorkspace);
		
		// Initialisation Mes documents
		DocumentModel mesDocs = unrestrictedSession.createDocumentModel(userWorkspace.getPathAsString(), "mes-documents", "Folder");
		mesDocs.setPropertyValue(DC_TITLE, "Mes documents");
		mesDocs.setPropertyValue(TTC_SHOW_IN_MENU, Boolean.TRUE);
				
		unrestrictedSession.createDocument(mesDocs);

		
		// Initialisation Documents synchronisés
		DocumentModel docsSynchro = unrestrictedSession.createDocumentModel(userWorkspace.getPathAsString(), "docs-synchro", "Folder");
		docsSynchro.setPropertyValue(DC_TITLE, "Documents synchronisés");
		docsSynchro.setPropertyValue(TTC_SHOW_IN_MENU, Boolean.TRUE);
				
		unrestrictedSession.createDocument(docsSynchro);
	
		NuxeoPrincipalImpl nuxeoPrincipalImpl = new NuxeoPrincipalImpl(userName);
		NuxeoDriveManager drive = Framework.getService(NuxeoDriveManager.class);
		drive.registerSynchronizationRoot(nuxeoPrincipalImpl,docsSynchro,unrestrictedSession);

		
		// Initialisation Mes liens
		DocumentModel mesLiens = unrestrictedSession.createDocumentModel(userWorkspace.getPathAsString(), "mes-liens", "DocumentUrlContainer");
		mesLiens.setPropertyValue(DC_TITLE, "Mes liens");
		mesLiens.setPropertyValue(TTC_SHOW_IN_MENU, Boolean.TRUE);
				
		unrestrictedSession.createDocument(mesLiens);
		
		// Initialisation blog
		DocumentModel monBlog = unrestrictedSession.createDocumentModel(userWorkspace.getPathAsString(), "mon-blog", "BlogSite");
		monBlog.setPropertyValue(DC_TITLE, "Le blog de " + buildUserWorkspaceTitle(principal, userName));
				
		unrestrictedSession.createDocument(monBlog);
		setFoldersACL(monBlog,userName);
		
		// Initialisation du dossier public
		DocumentModel publicFolder = unrestrictedSession.createDocumentModel(userWorkspace.getPathAsString(), "public", "Folder");
		publicFolder.setPropertyValue(DC_TITLE, "Public");
		publicFolder.setPropertyValue(TTC_SHOW_IN_MENU, Boolean.TRUE);
		
		unrestrictedSession.createDocument(publicFolder);
		setFoldersACL(publicFolder,userName);
		
		return userWorkspace;
	}

    protected void setFoldersACL( DocumentModel doc, String userName) throws ClientException {
    	
//    	UserService userService = Framework.getService(UserService.class);
//    	String defaultGroup = userService.getUserManager().getDefaultGroup();
    	
        ACP acp = new ACPImpl();
//        ACE grantMembersRead = new ACE(defaultGroup, SecurityConstants.READ,
//                true);
        ACE grantMembersRead = new ACE(SecurityConstants.MEMBERS, SecurityConstants.READ,
                true);
        ACE grantEverything = new ACE(userName, SecurityConstants.EVERYTHING,
                true);
        ACL acl = new ACLImpl();
        acl.setACEs(new ACE[] { grantMembersRead, grantEverything });
        acp.addACL(acl);
        doc.setACP(acp, true);
    }
}
