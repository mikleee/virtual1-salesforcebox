package salesforceflow;

import com.virtual1.salesforcebox.sf.SalesforceService;
import com.virtual1.salesforcebox.sf.model.BaseSalesforceObject;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:21
 */
public class AbstractTestFlow {
    final static String ACCOUNT_ID = "0013000000pI4zg";
    final static String ANALOGUE_LINE_ID = "a0Qa000000L8ItbEAF";
    final static String ATTACHMENT_ID = "00PS0000001xrO9MAI";
    final static String ATTACHMENT_PARENT_ID = "a06S0000006o881";
    final static String CONTACT_ID = "003a000002Nfv4t";
    final static String END_CUSTOMER_ID = "a0cS0000001i5Fq";
    final static String END_CUSTOMER_ID_2 = "a0cS0000001i5Ge";
    final static String EXCHANGE_ID = "a0Ta000000943CpEAI";
    final static String FEED_ITEM_ID = "0D5S000000F3I3xKAF";
    final static String RECORD_TYPE_ID = "01230000000rpB9AAI";
    final static String SITE_ID = "a08S000000CV8lG";

    private static SalesforceService salesforceService;

    public static SalesforceService getSalesforceService() {
        if (salesforceService == null) {
            salesforceService = new SalesforceService(
                    "system.attendant@virtual1.com.v1testing",
                    "p4GuCbdGPL",
                    true,
                    "Mikhail Tkachenko automated testing"
            );
        }
        return salesforceService;
    }

    String randomString() {
        return "AutoTest-" + System.currentTimeMillis();
    }

    void assertContains(List<? extends BaseSalesforceObject> list, BaseSalesforceObject object) {
        boolean contains = false;
        for (BaseSalesforceObject o : list) {
            if (o.getId().equals(object.getId())) {
                contains = true;
                break;
            }
        }

        Assert.assertTrue("Collection of salesforce objects does not contain " + object.getId(), contains);
    }

    void assertEquals(BigDecimal o1, BigDecimal o2) {
        if (o1 != null & o2 != null) {
            Assert.assertEquals(o1.doubleValue(), o1.doubleValue(), 4);
        } else {
            Assert.assertEquals(o1, o1);
        }
    }

    String formatSfId(String id) {
        return id != null && id.length() == 18 ? id.substring(0, 15) : id;
    }

    byte[] getTestAttachment() {
        try (InputStream is = getClass().getResourceAsStream("/test-attachment.png")) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int read;
            while ((read = is.read()) != -1) {
                os.write(read);
            }
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
