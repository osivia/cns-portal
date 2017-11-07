package fr.gouv.education.cns.directory.v2.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.FieldUtils;
import org.osivia.directory.v2.dao.PersonDaoImpl;
import org.osivia.portal.api.directory.v2.model.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.BinaryLogicalFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.stereotype.Repository;

/**
 * CNS person DAO implementation.
 * 
 * @author Cédric Krommenhoek
 * @see PersonDaoImpl
 */
@Repository
@Primary
public class CnsPersonDaoImpl extends PersonDaoImpl {

    /**
     * Constructor.
     */
    public CnsPersonDaoImpl() {
        super();
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Person> findByCriteria(Person criteria) {
        // Global filter
        BinaryLogicalFilter globalFilter = new AndFilter();

        // Quick search filter
        String[] quickSearchNames = new String[]{"uid", "cn", "sn", "mail", "displayName", "givenName"};
        List<Filter> quickSearchFilters = new ArrayList<>(quickSearchNames.length);
        for (String name : quickSearchNames) {
            Filter filter = this.getQueryFilter(criteria, name);
            if (filter != null) {
                quickSearchFilters.add(filter);
            }
        }
        if (!quickSearchFilters.isEmpty()) {
            BinaryLogicalFilter quickSearchFilter = new OrFilter();
            quickSearchFilter.appendAll(quickSearchFilters);

            globalFilter.append(quickSearchFilter);
        }

        // Profiles
        Filter profilesFilter = this.getQueryFilter(criteria, "profiles");
        if (profilesFilter != null) {
            globalFilter.append(profilesFilter);
        }

        // External indicator
        Filter externalFilter = this.getQueryFilter(criteria, "external");
        if (externalFilter != null) {
            globalFilter.append(externalFilter);
        }

        // Entity
        Filter entityFilter = this.getQueryFilter(criteria, "entity");
        if (entityFilter != null) {
            globalFilter.append(entityFilter);
        }

        return (List<Person>) this.template.find(this.sample.buildBaseDn(), globalFilter, this.getSearchControls(), this.sample.getClass());
    }


    /**
     * Get LDAP query filter.
     * 
     * @param criteria LDAP search criteria
     * @param name criteria field name
     * @return LDAP query filter
     */
    private Filter getQueryFilter(Object criteria, String name) {
        // Field
        Field field = FieldUtils.getDeclaredField(criteria.getClass(), name, true);
        // Field annotation
        Attribute annotation;
        if (field == null) {
            annotation = null;
        } else {
            annotation = field.getAnnotation(Attribute.class);
        }

        // LDAP attribute name
        String attribute;
        if (annotation == null) {
            attribute = name;
        } else {
            attribute = StringUtils.defaultIfEmpty(annotation.name(), name);
        }

        // LDAP attribute value
        Object value;
        try {
            value = PropertyUtils.getProperty(criteria, name);
        } catch (ReflectiveOperationException e) {
            value = null;
        }

        // Filter
        Filter filter;
        if ((attribute == null) || (value == null)) {
            filter = null;
        } else if (value instanceof Boolean) {
            Boolean booleanValue = (Boolean) value;
            filter = new LikeFilter(attribute, BooleanUtils.toString(booleanValue, "TRUE", "FALSE", null));
        } else if (value instanceof Collection) {
            // Sub-query values
            Collection<?> subQueryValues = (Collection<?>) value;

            // Sub-query filters
            Collection<Filter> subQueryFilters = new ArrayList<Filter>(subQueryValues.size());
            for (Object subQueryValue : subQueryValues) {
                // Sub-query filter
                Filter subQueryFilter = new LikeFilter(attribute, subQueryValue.toString());
                subQueryFilters.add(subQueryFilter);
            }

            if (subQueryFilters.isEmpty()) {
                filter = null;
            } else {
                // "OR" filter
                BinaryLogicalFilter orFilter = new OrFilter();
                orFilter.appendAll(subQueryFilters);

                filter = orFilter;
            }
        } else {
            filter = new LikeFilter(attribute, value.toString());
        }

        return filter;
    }

}
