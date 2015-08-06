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
 */
package fr.toutatice.portail.cms.nuxeo.portlets.customizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.Constants;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSHandlerProperties;
import org.osivia.portal.core.cms.CMSItemType;
import org.osivia.portal.core.cms.CMSServiceCtx;

import fr.toutatice.portail.cms.nuxeo.api.domain.ListTemplate;
import fr.toutatice.portail.cms.nuxeo.portlets.customizer.helpers.NavigationItemAdapter;

/**
 * CMS customizer.
 *
 * @author Cédric Krommenhoek
 * @see DefaultCMSCustomizer
 */
public class CMSCustomizer extends DefaultCMSCustomizer {

    /** Search schemas. */
    public static final String SCHEMAS_SEARCH = "dublincore, common, file, uid, toutatice";
    /** Zoom schemas. */
    public static final String SCHEMAS_ZOOM = "dublincore, toutatice, zoom";
    /** Picturebook schemas. */
    public static final String SCHEMAS_PICTUREBOOK = "dublincore, common, toutatice, note, files, acaren, webcontainer, file, picture";
    /** Annonce schemas. */
    public static final String SCHEMAS_ANNONCE = "dublincore, common, toutatice, annonce, note";

    /** Tiles list template. */
    public static final String STYLE_TILE = "tuile";
    /** Picturebook list template. */
    public static final String STYLE_PICTUREBOOK = "picturebook";
    /** Workspace list template. */
    public static final String STYLE_WORKSPACE = "workspace";


    /** Navigation item adapter. */
    public NavigationItemAdapter navigationItemAdapter;


    /**
     * Constructor.
     *
     * @param ctx portlet context
     */
    public CMSCustomizer(PortletContext ctx) {
        super(ctx);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public NavigationItemAdapter getNavigationItemAdapter() {
        if (this.navigationItemAdapter == null) {
            this.navigationItemAdapter = new CustomNavigationItemAdapter(this.getPortletCtx(), this, this.getCmsService());
        }

        return this.navigationItemAdapter;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ListTemplate> initListTemplates(Locale locale) {
        List<ListTemplate> templates = super.initListTemplates(locale);

        // Bundle
        Bundle bundle = this.getBundleFactory().getBundle(locale);

        // Tiles
        templates.add(new ListTemplate(STYLE_TILE, bundle.getString("LIST_TEMPLATE_TILES"), SCHEMAS_ZOOM));
        // Workspace
        templates.add(new ListTemplate(STYLE_WORKSPACE, bundle.getString("LIST_TEMPLATE_WORKSPACE"), DEFAULT_SCHEMAS));

        return templates;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CMSHandlerProperties getCMSDefaultPlayer(CMSServiceCtx ctx) throws CMSException {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_VERSION, ctx.getDisplayLiveVersion());
        //windowProperties.put("osivia.cms.hideMetaDatas", "1");
        windowProperties.put(Constants.WINDOW_PROP_URI, doc.getPath());
        windowProperties.put("osivia.cms.publishPathAlreadyConverted", "1");
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("theme.dyna.partial_refresh_enabled", "false");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-portail-cms-nuxeo-viewDocumentPortletInstance");

        return linkProps;
    }

}
