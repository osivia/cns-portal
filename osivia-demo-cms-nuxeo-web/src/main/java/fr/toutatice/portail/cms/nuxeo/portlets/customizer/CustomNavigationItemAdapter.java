package fr.toutatice.portail.cms.nuxeo.portlets.customizer;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;

import fr.toutatice.portail.cms.nuxeo.portlets.customizer.helpers.NavigationItemAdapter;

/**
 * Custom navigation item adapter.
 * 
 * @author Cédric Krommenhoek
 * @see NavigationItemAdapter
 */
public class CustomNavigationItemAdapter extends NavigationItemAdapter {

    /**
     * Constructor.
     * 
     * @param portletCtx portlet context
     * @param customizer default CMS customizer
     * @param cmsService CMS service
     */
    public CustomNavigationItemAdapter(PortletContext portletCtx, DefaultCMSCustomizer customizer,
            fr.toutatice.portail.cms.nuxeo.portlets.service.CMSService cmsService) {
        super(portletCtx, customizer, cmsService);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isNavigable(Document doc) {
        if ((doc.getType().equals("WikiBook")) || (doc.getType().equals("WikiSection"))) {
            return true;
        }
        return super.isNavigable(doc);
    }

}
