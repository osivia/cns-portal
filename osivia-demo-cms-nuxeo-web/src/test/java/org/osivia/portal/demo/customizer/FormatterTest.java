package org.osivia.portal.demo.customizer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nuxeo.ecm.automation.client.model.Document;
import org.nuxeo.ecm.automation.client.model.PropertyMap;

/**
 * Formatter test class.
 * 
 * @author Cédric Krommenhoek
 * @see Formatter
 */
public class FormatterTest {

    /** Nuxeo document mock. */
    private Document documentMock;
    /** Nuxeo document property map. */
    private final PropertyMap properties;


    /**
     * Default constructor.
     */
    public FormatterTest() {
        super();
        this.properties = new PropertyMap();
    }


    /**
     * Test set-up.
     */
    @Before
    public void setUp() {
        this.documentMock = EasyMock.createMock("Document", Document.class);
        EasyMock.expect(this.documentMock.getProperties()).andReturn(this.properties).anyTimes();
        EasyMock.replay(this.documentMock);
    }

    /**
     * Test for {@link Formatter#formatDate(Document, Locale)}.
     */
    @Test
    public final void testFormatDate() {
        String formattedDate;

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2014, Calendar.FEBRUARY, 10);
        Date createdDate = calendar.getTime();
        calendar.set(2014, Calendar.FEBRUARY, 12, 14, 50);
        Date modifiedDate = calendar.getTime();

        // Test 1 : no properties
        formattedDate = Formatter.formatDate(this.documentMock, Locale.FRANCE, false);
        Assert.assertEquals("", formattedDate);

        // Test 2 : no modified Date
        this.properties.set("dc:created", createdDate);
        formattedDate = Formatter.formatDate(this.documentMock, Locale.FRANCE, false);
        Assert.assertEquals("10 février 2014", formattedDate);

        // Test 3 : with modified Date
        this.properties.set("dc:modified", modifiedDate);
        formattedDate = Formatter.formatDate(this.documentMock, Locale.FRANCE, false);
        Assert.assertEquals("12 février 2014", formattedDate);

        // Test 4 : without locale
        formattedDate = Formatter.formatDate(this.documentMock, null, false);
        Assert.assertTrue(StringUtils.isNotBlank(formattedDate));

        // Test 5 : with time
        this.properties.set("dc:modified", modifiedDate);
        formattedDate = Formatter.formatDate(this.documentMock, Locale.FRANCE, true);
        Assert.assertEquals("12 février 2014 14:50", formattedDate);
    }

}
