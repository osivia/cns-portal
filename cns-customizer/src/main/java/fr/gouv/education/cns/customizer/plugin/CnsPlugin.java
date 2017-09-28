package fr.gouv.education.cns.customizer.plugin;

import java.util.Arrays;
import java.util.List;

import org.osivia.portal.api.customization.CustomizationContext;
import org.osivia.portal.api.menubar.MenubarModule;
import org.osivia.portal.api.taskbar.TaskbarFactory;
import org.osivia.portal.api.taskbar.TaskbarItem;
import org.osivia.portal.api.taskbar.TaskbarItems;

import fr.gouv.education.cns.customizer.plugin.menubar.CnsMenubarModule;
import fr.toutatice.portail.cms.nuxeo.api.domain.AbstractPluginPortlet;

/**
 * CNS plugin.
 * 
 * @author Cédric Krommenhoek
 * @see AbstractPluginPortlet
 */
public class CnsPlugin extends AbstractPluginPortlet {

    /** Plugin name. */
    private static final String PLUGIN_NAME = "cns.plugin";


    /**
     * Constructor.
     */
    public CnsPlugin() {
        super();
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
        // Menubar
        this.customizeMenubar(context);
        // Taskbar items
        this.customizeTaskbarItems(context);
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
            } else if (item.isDefault()) {
                factory.preset(item, false, null);
            }
        }
    }

}
