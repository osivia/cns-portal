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
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.Constants;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSHandlerProperties;
import org.osivia.portal.core.cms.CMSItemType;
import org.osivia.portal.core.cms.CMSPublicationInfos;
import org.osivia.portal.core.cms.CMSServiceCtx;

import fr.toutatice.portail.cms.nuxeo.portlets.customizer.helpers.NavigationItemAdapter;

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
     * Get search schema.
     *
     * @return search schema
     */
    public static String getSearchSchema() {
        return "dublincore, common, file, uid, toutatice";
    }


    @Override
    public NavigationItemAdapter getNavigationItemAdapter() {
        if (this.navigationItemAdapter == null) {
            this.navigationItemAdapter = new CustomNavigationItemAdapter(this.getPortletCtx(), this, this.getCmsService());
        }

        return this.navigationItemAdapter;
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

        if ("Agenda".equals(doc.getType())) {
            return this.getCalendarPlayer(ctx);
        }

        if ("VEVENT".equals(doc.getType())) {
            return this.getEventPlayer(ctx);
        }

        if ("PictureBook".equals(doc.getType())) {
            return this.getCMSPictureBookPlayer(ctx);
        }

        CMSHandlerProperties player = super.getCMSPlayer(ctx);
        player.getWindowProperties().put("osivia.cms.hideMetaDatas", "1");
        return player;
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
     * @param cmsContext CMS context
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
     * Get calendar player.
     *
     * @param ctx
     * @return calendar player
     */
    private CMSHandlerProperties getCalendarPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_URI, doc.getPath());
        windowProperties.put("osivia.title", doc.getTitle());
        windowProperties.put("osivia.hideTitle", "1");
        windowProperties.put("osivia.ajaxLink", "0");
        windowProperties.put("osivia.cms.hideMetaDatas", "1");
        windowProperties.put("osivia.calendar.cmsPath", "${contentPath}");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("osivia-services-calendar-instance");

        return linkProps;
    }


    /**
     * Get the event player.
     *
     * @param ctx
     * @return Event player
     */
    private CMSHandlerProperties getEventPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put(Constants.WINDOW_PROP_URI, doc.getPath());
        windowProperties.put("osivia.title", doc.getTitle());
        windowProperties.put("osivia.hideTitle", "1");
        windowProperties.put("osivia.ajaxLink", "0");
        windowProperties.put("osivia.cms.hideMetaDatas", "1");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("osivia-services-calendar-event-instance");

        return linkProps;
    }


    /**
     * Get PictureBook player.
     *
     * @param ctx
     * @return PictureBook player
     * @throws Exception
     */
    private CMSHandlerProperties getCMSPictureBookPlayer(CMSServiceCtx ctx) throws Exception {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();

        windowProperties.put("osivia.title", doc.getTitle());
        windowProperties.put("osivia.cms.scope", ctx.getScope());
        windowProperties.put("osivia.cms.uri", doc.getPath());
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("theme.dyna.partial_refresh_enabled", "false");
        windowProperties.put("osivia.cms.displayLiveVersion", ctx.getDisplayLiveVersion());
        windowProperties.put("osivia.cms.style", CMSCustomizer.STYLE_PICTUREBOOK);
        windowProperties.put("osivia.nuxeoRequest", this.createFolderRequest(ctx, false));
        windowProperties.put("osivia.cms.pageSize", "10");
        windowProperties.put("osivia.cms.pageSizeMax", "20");
        windowProperties.put("osivia.cms.maxItems", "100");

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-portail-cms-nuxeo-viewListPortletInstance");

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


    private Map<String, CMSItemType> customCMSItemTypes;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, CMSItemType> getCMSItemTypes() {

        if (this.customCMSItemTypes == null) {

            this.customCMSItemTypes = new LinkedHashMap<String, CMSItemType>();
            this.customCMSItemTypes.putAll(super.getCMSItemTypes());

            List<CMSItemType> customizedTypes = this.getCustomizedCMSItemTypes();
            for (CMSItemType customizedType : customizedTypes) {
                this.customCMSItemTypes.put(customizedType.getName(), customizedType);
            }
        }

        return this.customCMSItemTypes;
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
        customizedTypes.add(new CMSItemType("WikiBook", true, true, true, true, true, Arrays.asList("WikiSection"), null));
        // Blog post
        customizedTypes.add(new CMSItemType("WikiSection", true, true, true, true, true, Arrays.asList("WikiSection"), null));
        // Forum
        customizedTypes.add(new CMSItemType("Forum", true, true, false, true, true, Arrays.asList("Thread"), null));
        // Forum thread
        customizedTypes.add(new CMSItemType("Thread", false, false, false, true, true, new ArrayList<String>(0), null));
        // Calendar
        customizedTypes.add(new CMSItemType("Agenda", false, true, false, false, true,  Arrays.asList("VEVENT"), null));
        // Events
        customizedTypes.add(new CMSItemType("VEVENT", false, false, false, false, true, new ArrayList<String>(0), null));
        // Picture book
        customizedTypes.add(new CMSItemType("PictureBook", true, true, true, false, true, Arrays.asList("Picture"), null));
        //Picture
        customizedTypes.add(new CMSItemType("Picture", false, false, false, false, true, new ArrayList<String>(0), null));

        return customizedTypes;
    }

}
