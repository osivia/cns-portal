package fr.gouv.education.cns.customizer.plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.osivia.portal.api.cms.DocumentType;
import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.api.internationalization.IBundleFactory;
import org.osivia.portal.api.internationalization.IInternationalizationService;
import org.osivia.portal.api.locator.Locator;
import org.osivia.portal.api.menubar.MenubarModule;
import org.osivia.portal.api.taskbar.TaskbarFactory;
import org.osivia.portal.api.taskbar.TaskbarItem;
import org.osivia.portal.api.taskbar.TaskbarItemExecutor;
import org.osivia.portal.api.taskbar.TaskbarItems;
import org.osivia.portal.api.theming.TemplateAdapter;

import fr.gouv.education.cns.customizer.plugin.menubar.CnsMenubarModule;
import fr.gouv.education.cns.customizer.plugin.taskbar.CnsForumExecutor;
import fr.gouv.education.cns.customizer.plugin.template.CnsTemplateAdapter;
import fr.toutatice.portail.cms.nuxeo.api.domain.AbstractPluginPortlet;
import fr.toutatice.portail.cms.nuxeo.api.domain.ListTemplate;

/**
 * CNS plugin.
 * 
 * @author Cédric Krommenhoek
 * @see AbstractPluginPortlet
 */
public class CnsPlugin extends AbstractPluginPortlet {

    /** Plugin name. */
    private static final String PLUGIN_NAME = "cns.plugin";


    /** Internationalization bundle factory. */
    private final IBundleFactory bundleFactory;


    /**
     * Constructor.
     */
    public CnsPlugin() {
        super();

        // Internationalization bundle factory
        IInternationalizationService internationalizationService = Locator.findMBean(IInternationalizationService.class,
                IInternationalizationService.MBEAN_NAME);
        this.bundleFactory = internationalizationService.getBundleFactory(this.getClass().getClassLoader());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPluginName() {
        return PLUGIN_NAME;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void customizeCMSProperties(CustomizationContext context) {
        // Document types
        this.customizeDocumentTypes(context);
        // List templates
        this.customizeListTemplates(context);
        // Menubar
        this.customizeMenubar(context);
        // Taskbar items
        this.customizeTaskbarItems(context);
        // Template adapters
        this.customizeTemplateAdapters(context);
    }


    /**
     * Customize document types.
     * 
     * @param context customization context
     */
    private void customizeDocumentTypes(CustomizationContext context) {
        // Document types
        Map<String, DocumentType> types = this.getDocTypes(context);

        // Blog
        DocumentType blog = DocumentType.createRoot("BlogSite");
        blog.addSubtypes("BlogPost");
        blog.setIcon("glyphicons glyphicons-blog");
        blog.setOrdered(false);
        blog.setEditable(true);
        blog.setTemplate("/default/templates/blogSite");
        types.put(blog.getName(), blog);

        // Blog post
        DocumentType blogPost = DocumentType.createLeaf("BlogPost");
        blogPost.setIcon("glyphicons glyphicons-blog");
        blogPost.setForceContextualization(true);
        blogPost.setEditable(true);
        types.put(blogPost.getName(), blogPost);

        // Remove pad
        types.remove("ToutaticePad");
    }


    /**
     * Customize list templates.
     * 
     * @param context customization context
     */
    private void customizeListTemplates(CustomizationContext context) {
        // Internationalization bundle
        Bundle bundle = this.bundleFactory.getBundle(context.getLocale());
        
        // List templates
        Map<String, ListTemplate> templates = this.getListTemplates(context);


        // Workspace member requests
        ListTemplate workspaceMemberRequests = templates.get("workspace-member-requests");

        if (workspaceMemberRequests != null) {
            // Workspace member requests tiles
            ListTemplate workspaceMemberRequestsTiles = new ListTemplate("workspace-member-requests-tiles",
                    bundle.getString("LIST_TEMPLATE_WORKSPACE_MEMBER_REQUESTS_TILES"), workspaceMemberRequests.getSchemas());
            workspaceMemberRequestsTiles.setModule(workspaceMemberRequests.getModule());
            templates.put(workspaceMemberRequestsTiles.getKey(), workspaceMemberRequestsTiles);
        }

        // Workspace tiles
        ListTemplate workspaceTiles = new ListTemplate("workspace-tiles", bundle.getString("LIST_TEMPLATE_WORKSPACE_TILES"), "dublincore, common, toutatice");
        templates.put(workspaceTiles.getKey(), workspaceTiles);

        // Blog site
        ListTemplate blogSite = new ListTemplate("blog-site", bundle.getString("LIST_TEMPLATE_BLOG_SITE"), "dublincore, common, toutatice, webpage");
        templates.put(blogSite.getKey(), blogSite);
    }


    /**
     * Customize menubar.
     * 
     * @param context customization context
     */
    private void customizeMenubar(CustomizationContext context) {
        // Menubar modules
        List<MenubarModule> modules = this.getMenubarModules(context);

        // Customized menubar module
        MenubarModule module = new CnsMenubarModule();
        modules.add(module);
    }


    /**
     * Customize taskbar items.
     *
     * @param context customization context
     */
    private void customizeTaskbarItems(CustomizationContext context) {
        // Taskbar items
        TaskbarItems items = this.getTaskbarItems(context);
        // Factory
        TaskbarFactory factory = this.getTaskbarService().getFactory();

        // Default taskbar item identifiers
        List<String> defaultIdentifiers = Arrays.asList(new String[]{"DOCUMENTS", "FORUM"});
        
        for (TaskbarItem item : items.getAll()) {
            if (defaultIdentifiers.contains(item.getId())) {
                if (!item.isDefault()) {
                    factory.preset(item, true, null);
                }

                if ("FORUM".equals(item.getId())) {
                    // Taskbar item executor
                    TaskbarItemExecutor executor = new CnsForumExecutor();
                    factory.setExecutor(item, executor);
                }
            } else if (item.isDefault()) {
                factory.preset(item, false, null);
            }
        }
    }


    /**
     * Customize template adapters.
     * 
     * @param context customization context
     */
    private void customizeTemplateAdapters(CustomizationContext context) {
        // Template adapters
        List<TemplateAdapter> adapters = this.getTemplateAdapters(context);

        // Customized template adapter
        TemplateAdapter adapter = new CnsTemplateAdapter();
        adapters.add(adapter);
    }

}
