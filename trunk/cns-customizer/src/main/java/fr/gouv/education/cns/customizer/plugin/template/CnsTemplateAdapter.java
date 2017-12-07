package fr.gouv.education.cns.customizer.plugin.template;

import org.osivia.portal.api.theming.TemplateAdapter;

/**
 * CNS template adapter.
 * 
 * @author Cédric Krommenhoek
 * @see TemplateAdapter
 */
public class CnsTemplateAdapter implements TemplateAdapter {

    /**
     * Constructor.
     */
    public CnsTemplateAdapter() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String adapt(String spacePath, String path, String spaceTemplate, String targetTemplate) {
        String template;

        if ("/default/templates/workspace/search".equals(targetTemplate) && "/cns/workspaces/Transverse".equals(spacePath)) {
            template = "/default/templates/workspace/search-transverse";
        } else {
            template = null;
        }

        return template;
    }

}
