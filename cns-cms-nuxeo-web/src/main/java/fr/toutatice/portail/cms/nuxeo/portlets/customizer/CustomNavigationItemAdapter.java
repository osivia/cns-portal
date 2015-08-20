/*
 * (C) Copyright 2014 OSIVIA (http://www.osivia.com)
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
 */
package fr.toutatice.portail.cms.nuxeo.portlets.customizer;

import java.util.Map;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.core.cms.CMSItem;

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


    @Override
    protected String getDefaultPageTemplate(Document doc) {
        if ("WebSite".equals(doc.getType()))
            return "/default/templates/webSitepublish";
        else if ("BlogSite".equals(doc.getType()))
            return "/default/templates/blogSite";
        else if ("Workspace".equals(doc.getType()))
            return "/default/templates/workspace";

        return "/default/templates/portalSitePublish";

    }

    public void adaptPublishSpaceNavigationItem(CMSItem publishSpaceNavigationItem, CMSItem publishSpaceItem) {
        super.adaptPublishSpaceNavigationItem(publishSpaceNavigationItem, publishSpaceItem);
        Document doc = (Document) publishSpaceNavigationItem.getNativeItem();
        Map<String, String> properties = publishSpaceNavigationItem.getProperties();
        
        if("Workspace".equals(doc.getType()))   {
            if (doc.getPath().contains("/UserWorkspaces/")) {
                    properties.put("defaultTemplate", "1");
                    properties.put("pageTemplate", "/default/templates/userWorkspace");

            }
        }
    }
    
}
