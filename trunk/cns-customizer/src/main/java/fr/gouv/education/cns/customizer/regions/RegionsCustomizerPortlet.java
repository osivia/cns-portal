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
package fr.gouv.education.cns.customizer.regions;

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

    /** Customizer name. */
    private static final String CUSTOMIZER_NAME = "cns.customizer.regions.name";
    /** Customization modules repository attribute name. */
    private static final String ATTRIBUTE_CUSTOMIZATION_MODULES_REPOSITORY = "CustomizationModulesRepository";

    /** Charte CNS context path init parameter name. */
    private static final String CHARTE_CNS_CONTEXT = "customizer.regions.cns.context";
    /** "osivia-demo-charte-montpellier" context path init parameter name. */
    private static final String CHARTE_CNS_FORUMS_CONTEXT = "customizer.regions.cns.forums.context";


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
    @Override
    public void customize(CustomizationContext context) {
        String charteCns = this.getInitParameter(CHARTE_CNS_CONTEXT);
        String charteCnsForums = this.getInitParameter(CHARTE_CNS_FORUMS_CONTEXT);


        Map<String, Object> attributes = context.getAttributes();
        String contextPath = (String) attributes.get(IRenderedRegions.CUSTOMIZER_ATTRIBUTE_THEME_CONTEXT_PATH);
        IRenderedRegions renderedRegion = (IRenderedRegions) attributes.get(IRenderedRegions.CUSTOMIZER_ATTRIBUTE_RENDERED_REGIONS);

        if (StringUtils.equals(contextPath, charteCns) || StringUtils.equals(contextPath, charteCnsForums)) {
            // Customize regions
            if (StringUtils.equals(contextPath, charteCns)) {
                renderedRegion.customizeRenderedRegion("logo", "/regions/logo.jsp");
                renderedRegion.customizeRenderedRegion("search", "/regions/search.jsp");
            } else if (StringUtils.equals(contextPath, charteCnsForums)) {
                renderedRegion.customizeRenderedRegion("toolbar", "/regions/toolbar.jsp", charteCnsForums);
                renderedRegion.removeRenderedRegion("search");
            }
            renderedRegion.removeRenderedRegion("footer");
        }
    }

}
