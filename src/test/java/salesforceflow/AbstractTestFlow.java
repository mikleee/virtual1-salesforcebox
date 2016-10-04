package salesforceflow;

import com.virtual1.salesforcebox.sf.SalesforceService;
import com.virtual1.salesforcebox.sf.model.BaseSalesforceObject;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:21
 */
public class AbstractTestFlow {
    final static String ACCOUNT_ID = "0013000000pI4zg";
    final static String ANALOGUE_LINE_ID = "a0Qa000000L8ItbEAF";
    final static String CONTACT_ID = "003a000002Nfv4t";
    final static String END_CUSTOMER_ID = "a0cS0000001i5Fq";
    final static String END_CUSTOMER_ID_2 = "a0cS0000001i5Ge";
    final static String EXCHANGE_ID = "a0Ta000000943CpEAI";
    final static String RECORD_TYPE_ID = "01230000000rpB9AAI";
    final static String SITE_ID = "a08S000000CV8lG";

    private static SalesforceService salesforceService;

    SalesforceService getSalesforceService() {
        if (salesforceService == null) {
            salesforceService = new SalesforceService(
                    "system.attendant@virtual1.com.v1testing",
                    "p4GuCbdGPL",
                    "",
                    true,
                    "Mikhail Tkachenko automated testing"
            );
        }
        return salesforceService;
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

//    String formatAccountId(String id) {
//        return id == null ? null : id.replaceAll("AAC", "");
//    }
//
//    String formatAccessId(String id) {
//        return id == null ? null : id.replaceAll("AAI", "");
//    }
//
//    String formatProjectId(String id) {
//        return id == null ? null : id.replaceAll("AAO", "");
//    }
//
//    String formatEndCustomerId(String id) {
//        return id == null ? null : id.replaceAll("IAI", "");
//    }
}
