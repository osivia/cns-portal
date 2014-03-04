package fr.toutatice.portail.cms.nuxeo.portlets.customizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletContext;

import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.Documents;
import org.osivia.portal.core.cms.CMSException;
import org.osivia.portal.core.cms.CMSHandlerProperties;
import org.osivia.portal.core.cms.CMSItemType;
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
        windowProperties.put("osivia.cms.displayLiveVersion", ctx.getDisplayLiveVersion());
        windowProperties.put("osivia.cms.hideMetaDatas", "1");
        windowProperties.put("osivia.cms.uri", doc.getPath());
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
            return this.getCMSFaqPlayer(ctx);
        }

        if ("BlogPost".equals(doc.getType())) {
            return this.getCMSMinimalPlayer(ctx);
        }

        return super.getCMSPlayer(ctx);
    }


    /**
     * Get Wiki player.
     * 
     * @param ctx CMS service context
     * @return Wiki player
     */
    public CMSHandlerProperties getWikiPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put("osivia.cms.uri", doc.getPath());
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
    public CMSHandlerProperties getCMSFaqPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put("osivia.cms.uri", doc.getPath());
        windowProperties.put("osivia.ajaxLink", "1");
        windowProperties.put("osivia.hideDecorators", "1");
        windowProperties.put("osivia.cms.displayLiveVersion", ctx.getDisplayLiveVersion());

        CMSHandlerProperties linkProps = new CMSHandlerProperties();
        linkProps.setWindowProperties(windowProperties);
        linkProps.setPortletInstance("toutatice-faq-portletInstance");

        return linkProps;
    }


    /**
     * Get minimal player.
     * 
     * @param ctx CMS service context
     * @return minimal player
     */
    public CMSHandlerProperties getCMSMinimalPlayer(CMSServiceCtx ctx) {
        Document doc = (Document) ctx.getDoc();

        Map<String, String> windowProperties = new HashMap<String, String>();
        windowProperties.put("osivia.cms.scope", ctx.getScope());
        windowProperties.put("osivia.cms.uri", doc.getPath());
        windowProperties.put("osivia.hideTitle", "1");
        windowProperties.put("osivia.ajaxLink", "1");
        windowProperties.put("osivia.cms.hideMetaDatas", "1");
        windowProperties.put("osivia.cms.displayLiveVersion", ctx.getDisplayLiveVersion());

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

        return customizedTypes;
    }

}
