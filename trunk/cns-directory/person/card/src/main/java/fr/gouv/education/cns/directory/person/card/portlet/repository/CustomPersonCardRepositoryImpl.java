package fr.gouv.education.cns.directory.person.card.portlet.repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.PortletException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Blob;
import org.nuxeo.ecm.automation.client.model.Document;
import org.osivia.portal.api.cache.services.CacheInfo;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.services.person.card.portlet.model.PersonEditionForm;
import org.osivia.services.person.card.portlet.model.PersonNuxeoProfile;
import org.osivia.services.person.card.portlet.repository.PersonCardRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import fr.gouv.education.cns.directory.person.card.portlet.model.CustomPersonEditionForm;
import fr.gouv.education.cns.directory.person.card.portlet.model.CustomPersonNuxeoProfile;
import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;
import fr.gouv.education.cns.directory.person.util.portlet.model.comparator.PersonEntityComparator;
import fr.gouv.education.cns.directory.person.util.portlet.repository.command.GetEntitiesCommand;
import fr.toutatice.portail.cms.nuxeo.api.INuxeoCommand;
import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.services.NuxeoCommandContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Customized person card portlet repository implementation.
 * 
 * @author Cédric Krommenhoek
 * @see PersonCardRepositoryImpl
 * @see CustomPersonCardRepository
 */
@Repository
@Primary
public class CustomPersonCardRepositoryImpl extends PersonCardRepositoryImpl implements CustomPersonCardRepository {

    /** Administrative entity Nuxeo document property. */
    private static final String ADMINISTRATIVE_ENTITY_PROPERTY = "cnsprofile:entite_adm";
    /** Generic mail Nuxeo document property. */
    private static final String GENERIC_MAIL_PROPERTY = "cnsprofile:mail_generique";
    /** Referer Nuxeo document property. */
    private static final String REFERER_PROPERTY = "cnsprofile:referent";


    /** Application context. */
    @Autowired
    private ApplicationContext applicationContext;

    /** Entity comparator. */
    @Autowired
    private PersonEntityComparator entityComparator;


    /**
     * Constructor.
     */
    public CustomPersonCardRepositoryImpl() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected CustomPersonNuxeoProfile convertNuxeoProfile(Document document) throws PortletException {
        // Nuxeo profile
        PersonNuxeoProfile nuxeoProfile = super.convertNuxeoProfile(document);

        // Customized Nuxeo profile
        CustomPersonNuxeoProfile customNuxeoProfile;

        if ((nuxeoProfile != null) && (nuxeoProfile instanceof CustomPersonNuxeoProfile)) {
            customNuxeoProfile = (CustomPersonNuxeoProfile) nuxeoProfile;
            customNuxeoProfile.setAdministrativeEntity(document.getString(ADMINISTRATIVE_ENTITY_PROPERTY));
            customNuxeoProfile.setGenericMail(document.getString(GENERIC_MAIL_PROPERTY));
            customNuxeoProfile.setReferer(document.getString(REFERER_PROPERTY));
        } else {
            customNuxeoProfile = null;
        }

        return customNuxeoProfile;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getNuxeoProperties(PortalControllerContext portalControllerContext, PersonEditionForm form) throws PortletException {
        Map<String, String> properties = super.getNuxeoProperties(portalControllerContext, form);

        if (form instanceof CustomPersonEditionForm) {
            CustomPersonEditionForm customForm = (CustomPersonEditionForm) form;

            properties.put(ADMINISTRATIVE_ENTITY_PROPERTY, StringUtils.trimToEmpty(customForm.getAdministrativeEntity()));
            properties.put(GENERIC_MAIL_PROPERTY, StringUtils.trimToEmpty(customForm.getGenericMail()));
            properties.put(REFERER_PROPERTY, StringUtils.trimToEmpty(customForm.getReferer()));
        }

        return properties;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Set<PersonEntity> getEntities(PortalControllerContext portalControllerContext) throws PortletException {
        // Nuxeo controller
        NuxeoController nuxeoController = new NuxeoController(portalControllerContext);
        nuxeoController.setAuthType(NuxeoCommandContext.AUTH_TYPE_SUPERUSER);
        nuxeoController.setCacheType(CacheInfo.CACHE_SCOPE_GLOBAL);

        // Nuxeo command
        INuxeoCommand command = this.applicationContext.getBean(GetEntitiesCommand.class);
        Object result = nuxeoController.executeNuxeoCommand(command);

        Set<PersonEntity> entities;

        if (result instanceof Blob) {
            Blob blob = (Blob) result;
            String content;
            try {
                content = IOUtils.toString(blob.getStream(), "UTF-8");
            } catch (IOException e) {
                throw new PortletException(e);
            }
            JSONArray array = JSONArray.fromObject(content);

            entities = new TreeSet<>(this.entityComparator);

            Iterator<?> iterator = array.iterator();
            while (iterator.hasNext()) {
                JSONObject object = (JSONObject) iterator.next();
                String key = object.getString("key");
                String value;
                try {
                    value = URLDecoder.decode(object.getString("value"), CharEncoding.UTF_8);
                } catch (UnsupportedEncodingException e) {
                    throw new PortletException(e);
                }

                PersonEntity entity = this.applicationContext.getBean(PersonEntity.class);
                entity.setCode(key);
                entity.setLabel(value);

                entities.add(entity);
            }
        } else {
            entities = null;
        }

        return entities;
    }

}
