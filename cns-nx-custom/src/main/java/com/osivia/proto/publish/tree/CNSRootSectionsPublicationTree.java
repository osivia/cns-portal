/**
 * 
 */
package com.osivia.proto.publish.tree;

import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.platform.publisher.api.PublicationNode;

import fr.toutatice.ecm.platform.web.publication.tree.ToutaticeRootSectionsPublicationTree;


/**
 * @author david
 *
 */
public class CNSRootSectionsPublicationTree extends ToutaticeRootSectionsPublicationTree {

    private static final long serialVersionUID = 4152775341393551865L;
    
    private static final String SECTION_ROOT_TYPE = "SectionRoot";
    
    /* FIXME: Fork to exclude SectionRoot publication  and publication with workflow*/
    @Override
    public boolean canPublishTo(PublicationNode publicationNode) throws ClientException {
        if (publicationNode == null || publicationNode.getParent() == null) {
            // we can't publish in the root node
            return false;
        }
        DocumentRef docRef = new PathRef(publicationNode.getPath());
        boolean canAsk = coreSession.hasPermission(docRef, CAN_ASK_FOR_PUBLISHING);
        boolean canWrite = coreSession.hasPermission(docRef, "Write");
        DocumentModel document = coreSession.getDocument(docRef);
        boolean notSectionRoot = !SECTION_ROOT_TYPE.equals(document.getType());
        return canAsk && canWrite && notSectionRoot;
    }

}
