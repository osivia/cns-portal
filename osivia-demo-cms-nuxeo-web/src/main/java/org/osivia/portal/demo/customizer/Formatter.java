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
 *
 */
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
     * @param time display time indicator
     * @return formatted date
     */
    public static String formatDate(Document document, Locale locale, boolean time) {
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

        DateFormat dateFormat;
        if (time) {
            dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, checkedLocale);
        } else {
            dateFormat = DateFormat.getDateInstance(DateFormat.LONG, checkedLocale);
        }
        return dateFormat.format(date);
    }

}
