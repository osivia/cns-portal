package fr.gouv.education.cns.customizer.plugin.taskbar;

import java.util.Arrays;

import org.nuxeo.ecm.automation.client.Session;
import org.nuxeo.ecm.automation.client.adapters.DocumentSecurityService;
import org.nuxeo.ecm.automation.client.adapters.DocumentService;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.DocumentPermissions;
import org.osivia.directory.v2.model.CollabProfile;
import org.osivia.directory.v2.model.ext.WorkspaceRole;
import org.osivia.portal.api.taskbar.TaskbarTask;

import fr.toutatice.portail.cms.nuxeo.api.INuxeoCommand;

/**
 * Update forum taskbar task Nuxeo command.
 * 
 * @author Cédric Krommenhoek
 * @see INuxeoCommand
 */
public class UpdateForumTaskCommand implements INuxeoCommand {

    /** Taskbar task. */
    private final TaskbarTask task;
    /** Workspace members profile. */
    private final CollabProfile profile;


    /**
     * Constructor.
     * 
     * @param task taskbar task
     * @param profile workspace members profile
     */
    public UpdateForumTaskCommand(TaskbarTask task, CollabProfile profile) {
        super();
        this.task = task;
        this.profile = profile;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute(Session nuxeoSession) throws Exception {
        // Document service
        DocumentService documentService = nuxeoSession.getAdapter(DocumentService.class);
        // Document security service
        DocumentSecurityService securityService = nuxeoSession.getAdapter(DocumentSecurityService.class);

        // Taskbar task document
        Document document = documentService.getDocument(this.task.getPath());

        // Document permissions
        DocumentPermissions permissions = new DocumentPermissions();
        permissions.setPermissions(this.profile.getCn(), Arrays.asList(WorkspaceRole.CONTRIBUTOR.getPermissions()));

        // Add permissions
        securityService.addPermissions(document, permissions, DocumentSecurityService.LOCAL_ACL, false);

        return null;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return null;
    }

}
