package salesforceflow;

import com.virtual1.salesforcebox.sf.SalesforceService;
import com.virtual1.salesforcebox.sf.model.BaseSalesforceObject;
import org.junit.Assert;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:21
 */
public class AbstractTestFlow {
    final static String ACCOUNT_ID = "0013000000pI4zg";
    final static String CONTACT_ID = "003a000002Nfv4t";
    final static String END_CUSTOMER_ID = "a0cS0000001i5Fq";
    final static String END_CUSTOMER_ID_2 = "a0cS0000001i5Ge";
    final static String EXCHANGE_ID = "a0Ta000000943CpEAI";
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

    String formatAccountId(String id) {
        return id == null ? null : id.replaceAll("AAC", "");
    }

    String formatEndCustomerId(String id) {
        return id == null ? null : id.replaceAll("IAI", "");
    }
}
