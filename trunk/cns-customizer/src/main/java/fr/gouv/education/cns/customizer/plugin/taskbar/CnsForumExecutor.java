package fr.gouv.education.cns.customizer.plugin.taskbar;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.directory.v2.model.CollabProfile;
import org.osivia.directory.v2.model.ext.WorkspaceGroupType;
import org.osivia.directory.v2.service.WorkspaceService;
import org.osivia.portal.api.PortalException;
import org.osivia.portal.api.cache.services.CacheInfo;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.directory.v2.DirServiceFactory;
import org.osivia.portal.api.taskbar.TaskbarItemExecutor;
import org.osivia.portal.api.taskbar.TaskbarTask;

import fr.toutatice.portail.cms.nuxeo.api.INuxeoCommand;
import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.cms.NuxeoDocumentContext;
import fr.toutatice.portail.cms.nuxeo.api.services.NuxeoCommandContext;

/**
 * CNS forum taskbar item executor.
 * 
 * @author Cédric Krommenhoek
 * @see TaskbarItemExecutor
 */
public class CnsForumExecutor implements TaskbarItemExecutor {

    /** Workspace service. */
    private final WorkspaceService workspaceService;


    /**
     * Constructor.
     */
    public CnsForumExecutor() {
        super();

        // Workspace service
        this.workspaceService = DirServiceFactory.getService(WorkspaceService.class);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void invoke(PortalControllerContext portalControllerContext, TaskbarTask task) throws PortalException {
        // Nuxeo controller
        NuxeoController nuxeoController = new NuxeoController(portalControllerContext);
        nuxeoController.setAuthType(NuxeoCommandContext.AUTH_TYPE_SUPERUSER);
        nuxeoController.setCacheType(CacheInfo.CACHE_SCOPE_NONE);

        // Workspace path
        String workspacePath = StringUtils.substringBeforeLast(task.getPath(), "/");
        // Workspace document context
        NuxeoDocumentContext workspaceDocumentContext = nuxeoController.getDocumentContext(workspacePath);
        // Workspace document
        Document workspace = workspaceDocumentContext.getDocument();
        // Workspace identifier
        String workspaceId = workspace.getString("webc:url");

        // Workspace members profile search criteria
        CollabProfile criteria = this.workspaceService.getEmptyProfile();
        criteria.setWorkspaceId(workspaceId);
        criteria.setType(WorkspaceGroupType.space_group);
        // Workspace members profile search results
        List<CollabProfile> profiles = this.workspaceService.findByCriteria(criteria);
        if (CollectionUtils.isEmpty(profiles) || (profiles.size() > 1)) {
            throw new PortalException("Unable to find space group for workspace '" + workspaceId + "'");
        }
        // Workspace members profile
        CollabProfile profile = profiles.get(0);

        // Nuxeo command
        INuxeoCommand command = new UpdateForumTaskCommand(task, profile);
        nuxeoController.executeNuxeoCommand(command);
    }

}
