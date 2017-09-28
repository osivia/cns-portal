package fr.gouv.education.cns.customizer.plugin.menubar;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.PortalException;
import org.osivia.portal.api.cms.DocumentContext;
import org.osivia.portal.api.cms.DocumentType;
import org.osivia.portal.api.cms.Permissions;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.internationalization.Bundle;
import org.osivia.portal.api.internationalization.IBundleFactory;
import org.osivia.portal.api.internationalization.IInternationalizationService;
import org.osivia.portal.api.locator.Locator;
import org.osivia.portal.api.menubar.IMenubarService;
import org.osivia.portal.api.menubar.MenubarDropdown;
import org.osivia.portal.api.menubar.MenubarItem;
import org.osivia.portal.api.menubar.MenubarModule;
import org.osivia.portal.api.urls.IPortalUrlFactory;
import org.osivia.portal.core.cms.CMSServiceCtx;
import org.osivia.portal.core.cms.ICMSService;
import org.osivia.portal.core.cms.ICMSServiceLocator;

import fr.toutatice.portail.cms.nuxeo.api.cms.NuxeoDocumentContext;
import fr.toutatice.portail.cms.nuxeo.api.services.NuxeoConnectionProperties;

/**
 * Customized menubar module.
 * 
 * @author Cédric Krommenhoek
 * @see MenubarModule
 */
public class CnsMenubarModule implements MenubarModule {

    /** Remote publishing menubar item identifier. */
    private static final String REMOTE_PUBLISHING_ID = "REMOTE_PUBLISHING_URL";
    /** Approved document state. */
    private static final String APPROVED_STATE = "approved";


    /** Menubar service. */
    private final IMenubarService menubarService;
    /** Portal URL factory. */
    private final IPortalUrlFactory portalUrlFactory;
    /** CMS service locator. */
    private final ICMSServiceLocator cmsServiceLocator;
    /** Internationalization bundle factory. */
    private final IBundleFactory bundleFactory;


    /**
     * Constructor.
     */
    public CnsMenubarModule() {
        super();

        // Menubar service
        this.menubarService = Locator.findMBean(IMenubarService.class, IMenubarService.MBEAN_NAME);
        // Portal URL factory
        this.portalUrlFactory = Locator.findMBean(IPortalUrlFactory.class, IPortalUrlFactory.MBEAN_NAME);
        // CMS service locator
        this.cmsServiceLocator = Locator.findMBean(ICMSServiceLocator.class, ICMSServiceLocator.MBEAN_NAME);
        // Internationalization bundle factory
        IInternationalizationService internationalizationService = Locator.findMBean(IInternationalizationService.class,
                IInternationalizationService.MBEAN_NAME);
        this.bundleFactory = internationalizationService.getBundleFactory(this.getClass().getClassLoader());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void customizeSpace(PortalControllerContext portalControllerContext, List<MenubarItem> menubar, DocumentContext spaceDocumentContext)
            throws PortalException {
        // Do nothing
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void customizeDocument(PortalControllerContext portalControllerContext, List<MenubarItem> menubar, DocumentContext documentContext)
            throws PortalException {
        for (MenubarItem item : menubar) {
            if (REMOTE_PUBLISHING_ID.equals(item.getId())) {
                // Customize remote publishing menubar item
                this.customizeRemotePublishing(documentContext, item);
            }
        }

        // Add mass validation menubar item
        this.addMassValidation(portalControllerContext, menubar, documentContext);
    }


    /**
     * Customize remote publishing menubar item.
     * 
     * @param documentContext document context
     * @param item menubar item
     */
    private void customizeRemotePublishing(DocumentContext documentContext, MenubarItem item) {
        // Visible menubar item indicator
        boolean visible;

        if (documentContext == null) {
            visible = false;
        } else if (StringUtils.startsWith(documentContext.getPath(), "/forums")) {
            visible = false;
        } else if (documentContext instanceof NuxeoDocumentContext) {
            // Nuxeo document context
            NuxeoDocumentContext nuxeoDocumentContext = (NuxeoDocumentContext) documentContext;
            // Nuxeo document
            Document document = nuxeoDocumentContext.getDocument();
            
            visible = APPROVED_STATE.equals(document.getState());
        } else {
            visible = false;
        }
        
        // Update menubar item
        item.setVisible(visible);
    }


    /**
     * Add mass validation menubar item.
     * 
     * @param portalControllerContext portal controller context
     * @param menubar menubar
     * @param documentContext document context
     */
    private void addMassValidation(PortalControllerContext portalControllerContext, List<MenubarItem> menubar, DocumentContext documentContext) {
        if ((documentContext != null) && !StringUtils.startsWith(documentContext.getPath(), "/forums")) {
            // Document type
            DocumentType documentType = documentContext.getDocumentType();

            if ((documentType != null) && "Folder".equals(documentType.getName())) {
                // Permissions
                Permissions permissions = documentContext.getPermissions();

                if (permissions.isManageable()) {
                    // CMS service
                    ICMSService cmsService = this.cmsServiceLocator.getCMSService();
                    // CMS context
                    CMSServiceCtx cmsContext = new CMSServiceCtx();
                    cmsContext.setPortalControllerContext(portalControllerContext);

                    // Internationalization bundle
                    Bundle bundle = this.bundleFactory.getBundle(portalControllerContext.getRequest().getLocale());

                    // Menubar item URL
                    StringBuilder url = new StringBuilder();
                    url.append(NuxeoConnectionProperties.getPublicBaseUri().toString());
                    url.append("/nxpath/default");
                    url.append(documentContext.getPath());
                    url.append("@mass_validation?fromUrl=");
                    url.append(this.portalUrlFactory.getBasePortalUrl(portalControllerContext));
                    // Callback URL
                    String callbackUrl = this.portalUrlFactory.getCMSUrl(portalControllerContext, null, "_NEWID_", null, null, "_LIVE_", null, null, null,
                            null);
                    // ECM base URL
                    String ecmBaseUrl = cmsService.getEcmDomain(cmsContext);
                    // On click action
                    StringBuilder onClick = new StringBuilder();
                    onClick.append("javascript:setCallbackFromEcmParams('");
                    onClick.append(callbackUrl);
                    onClick.append("', '");
                    onClick.append(ecmBaseUrl);
                    onClick.append("');");


                    MenubarDropdown parent = this.menubarService.getDropdown(portalControllerContext, MenubarDropdown.CMS_EDITION_DROPDOWN_MENU_ID);
                    MenubarItem item = new MenubarItem("MASS_VALIDATION_URL", bundle.getString("MASS_VALIDATION"),
                            "glyphicons glyphicons-cogwheels", parent, 1, url.toString(), null, onClick.toString(), "fancyframe_refresh");

                    menubar.add(item);
                }
            }
        }
    }

}
