package com.osivia.cns.proto.workflows;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.platform.task.Task;
import org.nuxeo.ecm.webapp.action.MainTabsActions;
import org.nuxeo.runtime.api.Framework;

import com.osivia.cns.proto.constants.CnsConstants;
import com.osivia.cns.proto.constants.ExtendedSeamPrecedence;

import fr.toutatice.ecm.platform.service.portalviews.adapter.WidgetsAdapterService;
import fr.toutatice.ecm.workflows.integration.beans.IntegrationRoutingTaskActionsBean;
import fr.toutatice.ecm.workflows.integration.constants.WorkflowsConstants;


/**
 * @author David Chevrier
 *
 */
@Scope(CONVERSATION)
@Name("routingTaskActions")
@Install(precedence = ExtendedSeamPrecedence.CUSTOM)
public class CnsRoutingTaskActionsBean extends IntegrationRoutingTaskActionsBean {

    private static final long serialVersionUID = -4585041875109709783L;

    @Override
    public String endTask(Task task) throws ClientException {
        String view = MainTabsActions.DEFAULT_VIEW;

        String wfName = super.getCurrentWorkflowName(task);

        if (WorkflowsConstants.VALIDATION_WORKFLOWS.contains(wfName) || CnsConstants.CNS_VALIDATION_WF.equals(wfName)) {

            String clickedButton = getClickedButton();

            if (WorkflowsConstants.CONTINUE_VALIDATION_ACTIONS.get(0).equals(clickedButton) || CnsConstants.CNS_VALIDATION_BUTTON.equals(clickedButton)) {
                saveMetaData(task);
            }

            view = super.endTask(task);

            WidgetsAdapterService aSrv = (WidgetsAdapterService) Framework.getService(WidgetsAdapterService.class);
            if (aSrv.isInPortalViewContext()) {
                if (CnsConstants.CNS_VALIDATION_BUTTON.equals(clickedButton) || CnsConstants.CNS_CANCEL_BUTTON.equals(clickedButton)) {
                    view = WorkflowsConstants.PV_WORKFLOW_ACTION_DONE;
                }
            }

        } else {
            // no-op. only forward processing to mother class
            view = super.endTask(task);
        }

        return view;
    }

    /**
     * Save required metadata of task
     * on document associated to validation workflow.
     * 
     * @param task
     */
    private void saveMetaData(Task task) {

        Map<String, Serializable> formVariables = this.getFormVariables(task);
        /* Only one document associated with task */
        String docId = task.getTargetDocumentsIds().get(0);

        DocumentModel document = documentManager.getDocument(new IdRef(docId));

        Set<Entry<String, String>> metaDataSet = CnsConstants.METADATA.entrySet();

        for (Entry<String, String> metaData : metaDataSet) {
            document.setPropertyValue(metaData.getValue(), formVariables.get(metaData.getKey()));
        }

        documentManager.saveDocument(document);

    }

    /**
     * @return true if task form fields must be required.
     */
    public boolean isTaskFormRequired() {
        String clikedButton = getClickedButton();
        return WorkflowsConstants.CONTINUE_VALIDATION_ACTIONS.get(0).equals(clikedButton) || CnsConstants.CNS_VALIDATION_BUTTON.equals(clikedButton);
    }

}
