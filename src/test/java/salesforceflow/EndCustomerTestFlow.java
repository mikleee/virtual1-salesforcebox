package salesforceflow;

import com.virtual1.salesforcebox.sf.model.EndCustomer;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class EndCustomerTestFlow extends AbstractTestFlow {

    public EndCustomer findExistingEndCustomer() {
        EndCustomer endCustomer = getSalesforceService().getEndCustomer(END_CUSTOMER_ID);
        Assert.assertNotNull(endCustomer);
        return endCustomer;
    }

    public EndCustomer findByNameBasedOnExistingEndCustomer(EndCustomer endCustomer) {
        EndCustomer endCustomer2 = getSalesforceService().getEndCustomerByName(endCustomer.getAccountId(), endCustomer.getName());
        Assert.assertNotNull(endCustomer2);
        assertEquals(endCustomer, endCustomer2);
        return endCustomer2;
    }

    private void assertEquals(EndCustomer o1, EndCustomer o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(o1.getCompanyRegistration(), o2.getCompanyRegistration());
        Assert.assertEquals(o1.getPrtgLogin(), o2.getPrtgLogin());
        Assert.assertEquals(o1.getPrtgPassword(), o2.getPrtgPassword());
        Assert.assertEquals(formatAccountId(o1.getAccountId()), formatAccountId(o2.getAccountId()));
    }


}
