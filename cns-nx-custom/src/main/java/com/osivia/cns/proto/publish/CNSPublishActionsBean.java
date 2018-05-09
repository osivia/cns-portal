/**
 * 
 */
package com.osivia.cns.proto.publish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.Lock;
import org.nuxeo.ecm.platform.publisher.api.PublicationNode;
import org.nuxeo.ecm.platform.publisher.api.PublicationTree;
import org.nuxeo.ecm.platform.publisher.api.PublicationTreeNotAvailable;
import org.nuxeo.ecm.platform.publisher.api.PublishedDocument;

import com.osivia.cns.proto.constants.ExtendedSeamPrecedence;

import fr.toutatice.ecm.platform.core.constants.ToutaticeNuxeoStudioConst;
import fr.toutatice.ecm.platform.web.publication.ToutaticePublishActionsBean;


/**
 * @author david
 *
 */
@Name("publishActions")
@Scope(ScopeType.CONVERSATION)
@Install(precedence = ExtendedSeamPrecedence.CUSTOM)
public class CNSPublishActionsBean extends ToutaticePublishActionsBean {

    private static final long serialVersionUID = -4846952536986036993L;

    private static final Log log = LogFactory.getLog(CNSPublishActionsBean.class);

    /**
	 * GR: 
	 * - can not published non approved document
	 * - can publish a document even if there is a lock.
	 */
	@Override
	public boolean canPublishTo(PublicationNode publicationNode)
			throws ClientException {
		boolean canPublish = super.canPublishTo(publicationNode);
		DocumentModel doc = navigationContext.getCurrentDocument();

		// canPublish can be false if lock on document is present.
		if (!canPublish) {
			Lock lock = documentManager.getLockInfo(doc.getRef());
			// We authorize check of publication even if there is a lock.
			canPublish = lock != null ? true : false;
		}

		if (canPublish
				&& ToutaticeNuxeoStudioConst.CST_DOC_STATE_APPROVED.equals(doc
						.getCurrentLifeCycleState())) {

			PublicationTree tree = getCurrentPublicationTreeForPublishing();
			canPublish = tree != null ? tree.canPublishTo(publicationNode)
					: false;

		}

		return canPublish;
	}


    /**
     * We can republish only if current document
     * is approved.
     */
    @Override
    public boolean canRepublish(PublishedDocument publishedDocument) throws ClientException {
        DocumentModel doc = navigationContext.getCurrentDocument();
        // Is doc approved
        if (ToutaticeNuxeoStudioConst.CST_DOC_STATE_APPROVED.equals(doc.getCurrentLifeCycleState())) {

            if (!canUnpublish(publishedDocument)) {
                return false;
            }
            // version label is different, what means it is a previous version
            if (!publishedDocument.getSourceVersionLabel().equals(doc.getVersionLabel())) {
                return true;
            }
            // in case it is the same version, we have to check if the current
            // document has been modified since last publishing
            if (doc.isDirty()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Type of tree changed (ToutaticeRootSectionsPublicationTree to CNSRootSectionsPublicationTree)
     * to be coherent with publisher-task-contrib.xml.
     */
    @Override
    protected List<String> filterEmptyTrees(Collection<String> trees) throws PublicationTreeNotAvailable, ClientException {
        List<String> filteredTrees = new ArrayList<String>();

        for (String tree : trees) {
            try {
                PublicationTree pTree = publisherService.getPublicationTree(tree, documentManager, null, navigationContext.getCurrentDocument());
                if (pTree != null) {
                    if (pTree.getTreeType().equals("CNSRootSectionsPublicationTree")) {
                        if (pTree.getChildrenNodes().size() > 0) {
                            filteredTrees.add(tree);
                        }
                    } else {
                        filteredTrees.add(tree);
                    }
                }
            } catch (PublicationTreeNotAvailable e) {
                log.warn("Publication tree " + tree + " is not available : check config");
                log.debug("Publication tree " + tree + " is not available : root cause is ", e);
            }
        }
        return filteredTrees;
    }


}
