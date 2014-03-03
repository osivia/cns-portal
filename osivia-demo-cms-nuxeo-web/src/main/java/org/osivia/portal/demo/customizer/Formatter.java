package org.osivia.portal.demo.customizer;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.PropertyMap;

/**
 * Utility class used to format customized Nuxeo document content view.
 * 
 * @author Cédric Krommenhoek
 */
public final class Formatter {

    /**
     * Private constructor : prevent instantiation.
     */
    private Formatter() {
        throw new AssertionError();
    }


    /**
     * Format date into long format.
     * 
     * @param document Nuxeo document
     * @param locale current locale, may be null
     * @return formatted date
     */
    public static String formatDate(Document document, Locale locale) {
        if (document == null) {
            return null;
        }
        Locale checkedLocale = locale;
        if (checkedLocale == null) {
            checkedLocale = Locale.getDefault();
        }

        PropertyMap properties = document.getProperties();
        Date date = properties.getDate("dc:modified");
        if (date == null) {
            date = properties.getDate("dc:created");
        }
        if (date == null) {
            return StringUtils.EMPTY;
        }

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, checkedLocale);
        return dateFormat.format(date);
    }

}
