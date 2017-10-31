package fr.gouv.education.cns.directory.person.management.portlet.repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.portlet.PortletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Blob;
import org.osivia.portal.api.cache.services.CacheInfo;
import org.osivia.portal.api.context.PortalControllerContext;
import org.osivia.portal.api.directory.v2.model.Person;
import org.osivia.services.person.management.portlet.model.PersonManagementForm;
import org.osivia.services.person.management.portlet.repository.PersonManagementRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import fr.gouv.education.cns.directory.person.management.portlet.model.CustomPersonManagementForm;
import fr.gouv.education.cns.directory.person.util.portlet.model.PersonEntity;
import fr.gouv.education.cns.directory.person.util.portlet.model.comparator.PersonEntityComparator;
import fr.gouv.education.cns.directory.person.util.portlet.repository.command.GetEntitiesCommand;
import fr.gouv.education.cns.directory.v2.model.CnsPerson;
import fr.toutatice.portail.cms.nuxeo.api.INuxeoCommand;
import fr.toutatice.portail.cms.nuxeo.api.NuxeoController;
import fr.toutatice.portail.cms.nuxeo.api.services.NuxeoCommandContext;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Customized person management portlet repository implementation.
 * 
 * @author Cédric Krommenhoek
 * @see PersonManagementRepositoryImpl
 * @see CustomPersonManagementRepository
 */
@Repository
@Primary
public class CustomPersonManagementRepositoryImpl extends PersonManagementRepositoryImpl implements CustomPersonManagementRepository {

    /** Application context. */
    @Autowired
    private ApplicationContext applicationContext;

    /** Entity comparator. */
    @Autowired
    private PersonEntityComparator entityComparator;


    /**
     * Constructor.
     */
    public CustomPersonManagementRepositoryImpl() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected Person getSearchCriteria(PortalControllerContext portalControllerContext, PersonManagementForm form) {
        // HTTP servlet request
        HttpServletRequest servletRequest = portalControllerContext.getHttpServletRequest();
        // Host
        String host = servletRequest.getHeader("host");

        // Search criteria
        Person criteria = super.getSearchCriteria(portalControllerContext, form);

        // External indicator
        if (!StringUtils.contains(host, "forums")) {
            // Only CNS members
            criteria.setExternal(true);
        }

        if ((form instanceof CustomPersonManagementForm) && (criteria instanceof CnsPerson)) {
            CustomPersonManagementForm customForm = (CustomPersonManagementForm) form;
            CnsPerson cnsCriteria = (CnsPerson) criteria;

            // Entity
            String entity = customForm.getEntity();
            if (StringUtils.isNotEmpty(entity)) {
                cnsCriteria.setEntity(entity);
            }
        }

        return criteria;
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
