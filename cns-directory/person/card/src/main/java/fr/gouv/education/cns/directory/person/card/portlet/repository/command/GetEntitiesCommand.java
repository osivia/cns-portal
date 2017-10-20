package fr.gouv.education.cns.directory.person.card.portlet.repository.command;

import org.nuxeo.ecm.automation.client.OperationRequest;
import org.nuxeo.ecm.automation.client.Session;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.toutatice.portail.cms.nuxeo.api.INuxeoCommand;

/**
 * Get entities command.
 * 
 * @author Cédric Krommenhoek
 * @see INuxeoCommand
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetEntitiesCommand implements INuxeoCommand {

    /**
     * Constructor.
     */
    public GetEntitiesCommand() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Object execute(Session nuxeoSession) throws Exception {
        OperationRequest request = nuxeoSession.newRequest("Document.GetVocabularies");
        request.setHeader(org.nuxeo.ecm.automation.client.Constants.HEADER_NX_SCHEMAS, "*");
        request.set("vocabularies", "[CNS] Entité");
        return request.execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return this.getClass().getSimpleName();
    }

}
