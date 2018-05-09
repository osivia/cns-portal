package com.osivia.cns.proto.listeners;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.event.DocumentEventTypes;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.EventListener;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;
import org.nuxeo.ecm.platform.routing.api.DocumentRoutingConstants;
import org.nuxeo.ecm.platform.routing.core.impl.GraphNode;
import org.nuxeo.ecm.platform.routing.core.impl.GraphRoute;
import org.nuxeo.ecm.platform.routing.web.RoutingTaskActionsBean;
import org.nuxeo.ecm.platform.task.Task;
import org.nuxeo.ecm.platform.ui.web.util.SeamComponentCallHelper;

import com.osivia.cns.proto.constants.CnsConstants;

import fr.toutatice.ecm.workflows.integration.constants.WorkflowsConstants;


/**
 * @author David Chevrier.
 *
 */
public class CnsFillMetadaTaskListener implements EventListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleEvent(Event event) throws ClientException {

        EventContext ctx = event.getContext();
        CoreSession session = ctx.getCoreSession();
        String eventName = event.getName();

        if (event.getContext() instanceof DocumentEventContext) {

            DocumentEventContext docCtx = (DocumentEventContext) event.getContext();
            DocumentModel document = docCtx.getSourceDocument();

            if (DocumentEventTypes.BEFORE_DOC_UPDATE.equals(eventName)) {

                if (WorkflowsConstants.TASKDOC_TYPE.equals(document.getType())) {

                        Task task = document.getAdapter(Task.class);
                        String taskName = task.getName();

                        Map<String, Serializable> formVariables = getFormVariables(task);

                        if (WorkflowsConstants.CHOOSE_PARTICIPANTS_TASKS.contains(taskName) || CnsConstants.CNS_VALIDATION_TASK.equals(taskName)) {

                            /* Only one document associated with task */
                            String docId = task.getTargetDocumentsIds().get(0);
                            DocumentModel currentDoc = session.getDocument(new IdRef(docId));

                            Set<Entry<String, String>> metaDataSet = CnsConstants.METADATA.entrySet();

                            for (Entry<String, String> metaData : metaDataSet) {
                                formVariables.put(metaData.getKey(), currentDoc.getPropertyValue(metaData.getValue()));
                            }

                            fillFormVariables(session, task, formVariables);
                        }
                }
            }

        } 

    }

    /**
     * @param task
     * @return Form variables of given task.
     */
    private Map<String, Serializable> getFormVariables(Task task) {
        
        RoutingTaskActionsBean rtActions = (RoutingTaskActionsBean) SeamComponentCallHelper.getSeamComponentByName("routingTaskActions");
        Map<String, Serializable> formVariables = rtActions.getFormVariables(task);
        
        return formVariables;
    }

    /**
     * Save form variables of task on graph node.
     */
    private void fillFormVariables(CoreSession session, Task task, Map<String, Serializable> formVariables) {
        getGraphNode(session, task).setVariables(formVariables);
    }

    /**
     * @param session
     * @param task
     * @return GraphNode corresponding to task.
     */
    private GraphNode getGraphNode(CoreSession session, Task task) {
        final String routeDocId = task.getVariable(DocumentRoutingConstants.TASK_ROUTE_INSTANCE_DOCUMENT_ID_KEY);
        final String nodeId = task.getVariable(DocumentRoutingConstants.TASK_NODE_ID_KEY);

        DocumentModel doc = session.getDocument(new IdRef(routeDocId));
        GraphRoute route = doc.getAdapter(GraphRoute.class);
        GraphNode node = route.getNode(nodeId);
        return node;
    }

}
