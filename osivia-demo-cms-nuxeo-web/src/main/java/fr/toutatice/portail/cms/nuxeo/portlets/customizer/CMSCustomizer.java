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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.Documents;
import org.osivia.portal.api.Constants;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSHandlerProperties;
import org.osivia.portal.core.cms.CMSItemType;
import org.osivia.portal.core.cms.CMSPublicationInfos;
import org.osivia.portal.core.cms.CMSServiceCtx;

import fr.toutatice.portail.cms.nuxeo.api.NuxeoQueryFilter;
import fr.toutatice.portail.cms.nuxeo.portlets.list.DocumentQueryCommand;

/**
 * CMS customizer.
 * 
 * @author Cédric Krommenhoek
 * @see DefaultCMSCustomizer
 */
public class CMSCustomizer extends DefaultCMSCustomizer {

    /** "tuile" schemas. */
    public static final String SCHEMAS_TUILE = "dublincore, toutatice, zoom";
    /** "tuile" list template. */
    public static final String STYLE_TUILE = "tuile";
    /** "picturebook" schemas. */
    public static final String SCHEMAS_PICTUREBOOK = "dublincore, common, toutatice, note, files, acaren, webcontainer, file, picture";
    /** "picturebook" list template. */
    public static final String STYLE_PICTUREBOOK = "picturebook";
    /** "blog" schemas. */
    public static final String SCHEMAS_BLOG = "dublincore, common, toutatice, webpage";
    /** "blog" list template. */
    public static final String STYLE_BLOG = "blog";
    /** "workspace" schemas. */
    public static final String SCHEMAS_WORKSPACE = "dublincore";
    /** "workspace" list template. */
    public static final String STYLE_WORKSPACE = "workspace";
    /** "slider" schemas. */
    public static final String SCHEMAS_SLIDER = "dublincore, common, toutatice, annonce, note";
    /** "slider" list template. */
    public static final String STYLE_SLIDER = "slider";
    /** "forum" schemas. */
    public static final String SCHEMAS_FORUM = "dublincore, common, toutatice";
    /** "forum" list template. */
    public static final String STYLE_FORUM = "forum";


    /**
     * Constructor.
     * 
     * @param ctx portlet context
     */
    public CMSCustomizer(PortletContext ctx) {
        super(ctx);
    }


    /**
     * Get search schema.
     * 
     * @return search schema
     */
    public static String getSearchSchema() {
        return "dublincore, common, file, uid";
    }


    /**
     * Get list of list templates.
     * 
     * @return list of list templates
     */
    public static List<ListTemplate> getListTemplates() {
        List<ListTemplate> templates = DefaultCMSCustomizer.getListTemplates();
        templates.add(new ListTemplate(STYLE_TUILE, "Tuile [visuel, description]", SCHEMAS_TUILE));
        templates.add(new ListTemplate(STYLE_PICTUREBOOK, "Livre d'images", SCHEMAS_PICTUREBOOK));
        templates.add(new ListTemplate(STYLE_BLOG, "Blog", SCHEMAS_BLOG));
        templates.add(new ListTemplate(STYLE_FORUM, "Forum", SCHEMAS_FORUM));
        templates.add(new ListTemplate(STYLE_WORKSPACE, "Workspace", SCHEMAS_WORKSPACE));
        templates.add(new ListTemplate(STYLE_SLIDER, "Carrousel", SCHEMAS_SLIDER));
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
        windowProperties.put("osivia.cms.hideMetaDatas", "1");
        windowProperties.put(Constants.WINDOW_PROP_URI, doc.getPath());
        windowProperties.put("osivia.cms.publishPathAlreadyConverted", "1");
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("theme.dyna.partial_refresh_enabled", "false");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-portail-cms-nuxeo-viewDocumentPortletInstance");

        return linkProps;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> parseCMSURL(CMSServiceCtx cmsCtx, String requestPath, Map<String, String> requestParameters) throws Exception {
        Map<String, String> cmsCommandProperties = new HashMap<String, String>();

        if (requestPath.startsWith("/purl/")) {
            // URL de la forme: /purl/url
            String[] ident = requestPath.split("/");

            String clause = " ecm:primaryType = 'WikiSection' and webc:url = '" + ident[2] + "'";
            String filteredClause = NuxeoQueryFilter.addPublicationFilter(clause, false);

            String savedScope = cmsCtx.getScope();
            cmsCtx.setScope("superuser_context");
            try {
                Documents documents = (Documents) this.getCmsService().executeNuxeoCommand(cmsCtx, new DocumentQueryCommand(filteredClause));

                if (documents.size() != 1) {
                    throw new CMSException(CMSException.ERROR_NOTFOUND);
                } else {
                    cmsCommandProperties.put("cmsPath", documents.get(0).getPath());
                }
            } finally {
                cmsCtx.setScope(savedScope);
            }
        } else {
            cmsCommandProperties = super.parseCMSURL(cmsCtx, requestPath, requestParameters);
        }

        return cmsCommandProperties;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CMSHandlerProperties getCMSPlayer(CMSServiceCtx ctx) throws Exception {
        Document doc = (Document) ctx.getDoc();
        if ("WikiBook".equals(doc.getType()) || "WikiSection".equals(doc.getType())) {
            return this.getWikiPlayer(ctx);
        }

        if ("FaqFolder".equals(doc.getType()) || "Question".equals(doc.getType())) {
            return this.getFaqPlayer(ctx);
        }

        if ("BlogPost".equals(doc.getType())) {
            return this.getCMSMinimalPlayer(ctx);
        }

        if ("Forum".equals(doc.getType())) {
            return this.getForumPlayer(ctx);
        }

        if ("Thread".equals(doc.getType())) {
            return this.getForumThreadPlayer(ctx);
        }

        return super.getCMSPlayer(ctx);
    }


    /**
     * Get Wiki player.
     * 
     * @param ctx CMS service context
     * @return Wiki player
     */
    private CMSHandlerProperties getWikiPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_URI, doc.getPath());
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("osivia.ajaxLink", "1");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("osivia-services-wiki-wikiPortletInstance");

        return linkProps;
    }


    /**
     * Get FAQ player.
     * 
     * @param ctx CMS service context
     * @return FAQ player
     */
    private CMSHandlerProperties getFaqPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_URI, doc.getPath());
        windowProperties.put("osivia.ajaxLink", "1");
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put(Constants.WINDOW_PROP_VERSION, ctx.getDisplayLiveVersion());

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-faq-portletInstance");

        return linkProps;
    }


    /**
     * Get forum player.
     * 
     * @param ctx CMS context
     * @return CMS forum player
     * @throws CMSException
     */
    private CMSHandlerProperties getForumPlayer(CMSServiceCtx ctx) throws CMSException {
        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put("osivia.nuxeoRequest", this.createForumPlayerRequest(ctx));
        windowProperties.put("osivia.cms.style", CMSCustomizer.STYLE_FORUM);
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("theme.dyna.partial_refresh_enabled", "false");
        windowProperties.put(Constants.WINDOW_PROP_SCOPE, ctx.getScope());
        windowProperties.put("osivia.hideTitle", "1");
        windowProperties.put("osivia.ajaxLink", "1");
        windowProperties.put(Constants.WINDOW_PROP_VERSION, ctx.getDisplayLiveVersion());

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-portail-cms-nuxeo-viewListPortletInstance");

        return linkProps;
    }

    /**
     * Utility method used to create forum player request.
     * 
     * @param cmsContext CMS context
     * @return request
     * @throws CMSException
     */
    private String createForumPlayerRequest(CMSServiceCtx cmsContext) throws CMSException {
        // Document
        Document document = (Document) cmsContext.getDoc();
        // Publication infos
        CMSPublicationInfos pubInfos = this.getCmsService().getPublicationInfos(cmsContext, document.getPath());

        StringBuilder request = new StringBuilder();
        request.append("ecm:parentId = '").append(pubInfos.getLiveId()).append("' ");
        request.append("AND ecm:primaryType = 'Thread' ");
        request.append("ORDER BY dc:modified DESC ");
        return request.toString();
    }


    /**
     * Get forum thread player.
     * 
     * @param ctx CMS context
     * @return forum thread player
     */
    private CMSHandlerProperties getForumThreadPlayer(CMSServiceCtx cmsContext) {
        Document document = (Document) cmsContext.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_URI, document.getPath());
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("osivia.ajaxLink", "1");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("osivia-services-forum-portletInstance");

        return linkProps;
    }


    /**
     * Get minimal player.
     * 
     * @param ctx CMS service context
     * @return minimal player
     */
    private CMSHandlerProperties getCMSMinimalPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_SCOPE, ctx.getScope());
        windowProperties.put(Constants.WINDOW_PROP_URI, doc.getPath());
        windowProperties.put("osivia.hideTitle", "1");
        windowProperties.put("osivia.ajaxLink", "1");
        windowProperties.put("osivia.cms.hideMetaDatas", "1");
        windowProperties.put(Constants.WINDOW_PROP_VERSION, ctx.getDisplayLiveVersion());

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-portail-cms-nuxeo-viewDocumentPortletInstance");

        return linkProps;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, CMSItemType> getCMSItemTypes() {
        Map<String, CMSItemType> cmsItemTypes = super.getCMSItemTypes();

        List<CMSItemType> customizedTypes = this.getCustomizedCMSItemTypes();
        for (CMSItemType customizedType : customizedTypes) {
            cmsItemTypes.put(customizedType.getName(), customizedType);
        }

        return cmsItemTypes;
    }


    /**
     * Get customized CMS item types.
     * 
     * @return customized CMS item types
     */
    private List<CMSItemType> getCustomizedCMSItemTypes() {
        List<CMSItemType> customizedTypes = new ArrayList<CMSItemType>();

        // FAQ folder
        customizedTypes.add(new CMSItemType("FaqFolder", true, false, false, false, true, Arrays.asList("Question"), null));
        // FAQ question
        customizedTypes.add(new CMSItemType("Question", false, false, false, false, true, new ArrayList<String>(0), null));
        // Blog site
        customizedTypes.add(new CMSItemType("BlogSite", true, false, false, true, true, Arrays.asList("BlogPost"), "/default/templates/blogSite"));
        // Blog post
        customizedTypes.add(new CMSItemType("BlogPost", false, false, false, true, true, new ArrayList<String>(0), null));
        // Wiki book
        customizedTypes.add(new CMSItemType("WikiBook", true, true, true, false, true, Arrays.asList("WikiSection"), null));
        // Blog post
        customizedTypes.add(new CMSItemType("WikiSection", true, true, true, false, true, Arrays.asList("WikiSection"), null));
        // Forum
        customizedTypes.add(new CMSItemType("Forum", true, true, false, true, true, Arrays.asList("Thread"), "/default/templates/forum"));
        // Forum thread
        customizedTypes.add(new CMSItemType("Thread", false, false, false, true, true, new ArrayList<String>(0), null));

        return customizedTypes;
    }

}
