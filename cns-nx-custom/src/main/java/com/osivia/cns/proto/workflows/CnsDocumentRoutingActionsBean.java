/*
 * (C) Copyright 2014 Académie de Rennes (http://www.ac-rennes.fr/), OSIVIA (http://www.osivia.com) and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * 
 * Contributors:
 * dchevrier
 */
package com.osivia.cns.proto.workflows;

import static org.jboss.seam.ScopeType.CONVERSATION;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;

import com.osivia.cns.proto.constants.CnsConstants;

import fr.toutatice.ecm.workflows.integration.beans.IntegrationDocumentRoutingActionsBean;
import com.osivia.cns.proto.constants.ExtendedSeamPrecedence;
import fr.toutatice.ecm.workflows.integration.constants.WorkflowsConstants;

/**
 * @author David Chevrier
 *
 */
@Scope(CONVERSATION)
@Name("routingActions")
@Install(precedence = ExtendedSeamPrecedence.CUSTOM)
public class CnsDocumentRoutingActionsBean extends IntegrationDocumentRoutingActionsBean {

    private static final long serialVersionUID = 6571225517930521105L;
    
    /**
     * Starts Direct Validation workflow.
     * 
     * @return view of current task
     * @throws ClientException
     */
    public String startDirectValidationWorkflow() throws ClientException {
        DocumentModel onlineWf = getDirectValidationWorkflowModel();
        super.startWorkflow(onlineWf, StringUtils.EMPTY);
        return WorkflowsConstants.PV_CURRENT_TASK;
    }
    
    /**
     * @return model of Direct Validation workflow.
     * @throws ClientException
     */
    public DocumentModel getDirectValidationWorkflowModel() throws ClientException {
        String id = getDocumentRoutingService().getRouteModelDocIdWithId(documentManager, CnsConstants.CNS_VALIDATION_WF);
        return getRouteModel(id);
    }
    
    
}
