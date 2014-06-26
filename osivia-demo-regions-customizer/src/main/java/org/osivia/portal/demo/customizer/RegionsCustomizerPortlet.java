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
package org.osivia.portal.demo.customizer;

import java.util.Arrays;
import java.util.Map;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;

import org.apache.commons.lang.StringUtils;
import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.customization.CustomizationModuleMetadatas;
import org.osivia.portal.api.customization.ICustomizationModule;
import org.osivia.portal.api.customization.ICustomizationModulesRepository;
import org.osivia.portal.api.theming.IRenderedRegions;

/**
 * Technical portlet for regions customization.
 *
 * @author Cédric Krommenhoek
 * @see GenericPortlet
 * @see ICustomizationModule
 */
public class RegionsCustomizerPortlet extends GenericPortlet implements ICustomizationModule {

    /** "osivia-demo-charte" context path init parameter name. */
    private static final String CHARTE_CONTEXT = "customizer.regions.demo.context";
    /** "osivia-demo-charte-sitesweb" context path init parameter name. */
    private static final String CHARTE_SITESWEB_CONTEXT = "customizer.regions.demo.sitesweb.context";
    /** "osivia-demo-charte-montpellier" context path init parameter name. */
    private static final String CHARTE_MONTPELLIER_CONTEXT = "customizer.regions.demo.montpellier.context";

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "osivia.site.customizer.regions.name";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";

    /** Customization modules repository. */
    private ICustomizationModulesRepository repository;
    /** Internationalization customization module metadatas. */
    private final CustomizationModuleMetadatas metadatas;


    /**
     * Constructor.
     */
    public RegionsCustomizerPortlet() {
        super();
        this.metadatas = this.generateMetadatas();
    }


    /**
     * Utility method used to generate attributes bundles customization module metadatas.
     *
     * @return metadatas
     */
    private CustomizationModuleMetadatas generateMetadatas() {
        CustomizationModuleMetadatas metadatas = new CustomizationModuleMetadatas();
        metadatas.setName(CUSTOMIZER_NAME);
        metadatas.setModule(this);
        metadatas.setCustomizationIDs(Arrays.asList(IRenderedRegions.CUSTOMIZER_ID));
        return metadatas;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws PortletException {
        super.init();
        this.repository = (ICustomizationModulesRepository) this.getPortletContext().getAttribute(ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY);
        this.repository.register(this.metadatas);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        super.destroy();
        this.repository.unregister(this.metadatas);
    }


    /**
     * {@inheritDoc}
     */
    public void customize(String customizationID, CustomizationContext context) {
        String charteEnt = this.getInitParameter(CHARTE_CONTEXT);
        String charteSitesWeb = this.getInitParameter(CHARTE_SITESWEB_CONTEXT);
        String charteMontpellier = this.getInitParameter(CHARTE_MONTPELLIER_CONTEXT);

        Map<String, Object> attributes = context.getAttributes();
        String contextPath = (String) attributes.get(IRenderedRegions.CUSTOMIZER_ATTRIBUTE_THEME_CONTEXT_PATH);
        if (StringUtils.equals(contextPath, charteEnt) || StringUtils.equals(contextPath, charteSitesWeb) || StringUtils.equals(contextPath, charteMontpellier)) {
            IRenderedRegions renderedRegion = (IRenderedRegions) attributes.get(IRenderedRegions.CUSTOMIZER_ATTRIBUTE_RENDERED_REGIONS);

            if (StringUtils.equals(contextPath, charteEnt)) {
                // Customize regions
                renderedRegion.customizeRenderedRegion("toolbar", "/header/toolbar.jsp");
                renderedRegion.customizeRenderedRegion("logo", "/header/logo.jsp");
                renderedRegion.customizeRenderedRegion("search", "/header/search.jsp");
                renderedRegion.removeRenderedRegion("footer");
            }

            if (StringUtils.equals(contextPath, charteSitesWeb)) {
                // Replace default toolbar
                renderedRegion.customizeRenderedRegion("toolbar", "/header/toolbar.jsp");
            }

            if (StringUtils.equals(contextPath, charteMontpellier)) {
                // Customize regions
                renderedRegion.customizeRenderedRegion("toolbar", "/header/toolbar.jsp");
                renderedRegion.customizeRenderedRegion("logo", "/header/logo.jsp");
                renderedRegion.customizeRenderedRegion("search", "/header/search.jsp");
                renderedRegion.removeRenderedRegion("footer");
            }
        }
    }

}
