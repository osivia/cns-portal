/******************************************************************************
 * JBoss, a division of Red Hat *
 * Copyright 2009, Red Hat Middleware, LLC, and individual *
 * contributors as indicated by the @authors tag. See the *
 * copyright.txt in the distribution for a full listing of *
 * individual contributors. *
 * *
 * This is free software; you can redistribute it and/or modify it *
 * under the terms of the GNU Lesser General Public License as *
 * published by the Free Software Foundation; either version 2.1 of *
 * the License, or (at your option) any later version. *
 * *
 * This software is distributed in the hope that it will be useful, *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU *
 * Lesser General Public License for more details. *
 * *
 * You should have received a copy of the GNU Lesser General Public *
 * License along with this software; if not, write to the Free *
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA *
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org. *
 ******************************************************************************/

package org.osivia.portal.demo;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.LocaleUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.portal.theme.render.AbstractObjectRenderer;
import org.jboss.portal.theme.render.RenderException;
import org.jboss.portal.theme.render.RendererContext;
import org.jboss.portal.theme.render.renderer.ActionRendererContext;
import org.jboss.portal.theme.render.renderer.DecorationRenderer;
import org.jboss.portal.theme.render.renderer.DecorationRendererContext;
import org.osivia.portal.api.internationalization.IInternationalizationService;
import org.osivia.portal.api.locator.Locator;
import org.osivia.portal.core.constants.InternalConstants;
import org.osivia.portal.core.page.PageProperties;

/**
 * Implementation of a decoration renderer, based on table tags.
 * 
 * @author <a href="mailto:mholzner@novell.com>Martin Holzner</a>
 * @version $LastChangedRevision: 12912 $, $LastChangedDate: 2009-02-28 11:37:59 -0500 (Sat, 28 Feb 2009) $
 * @see AbstractObjectRenderer
 * @see TableDecorationRenderer
 */
public class TableDecorationRenderer extends AbstractObjectRenderer implements DecorationRenderer {

    /** Internationalization service. */
    private final IInternationalizationService internationalizationService;


    /**
     * Default constructor.
     */
    public TableDecorationRenderer() {
        super();

        this.internationalizationService = Locator.findMBean(IInternationalizationService.class, IInternationalizationService.MBEAN_NAME);
    }


    /**
     * {@inheritDoc}
     */
    public void render(RendererContext rendererContext, DecorationRendererContext drc) throws RenderException {
        PrintWriter markup = rendererContext.getWriter();

        PageProperties properties = PageProperties.getProperties();


        // Render title
        this.renderTitle(properties, markup, drc);


    }


    /**
     * Render portlet title.
     * 
     * @param properties page properties
     * @param markup markup
     * @param drc decoration renderer context
     */
    private void renderTitle(PageProperties properties, PrintWriter markup, DecorationRendererContext drc) {
        // Current window identifier
        String currentWindowId = properties.getCurrentWindowId();
        // Title value
        String title = properties.getWindowProperty(currentWindowId, "osivia.title");
        if (title == null) {
            title = drc.getTitle();
        }
        markup.print(title);


    }


}
