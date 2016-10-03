package salesforceflow;

import com.virtual1.salesforcebox.sf.model.EndCustomer;
import org.junit.Assert;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class EndCustomerTestFlow extends AbstractTestFlow {

    public EndCustomer findExisting() {
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

    public List<EndCustomer> getAccountListBasedOnExistingEndCustomer(EndCustomer endCustomer) {
        List<EndCustomer> endCustomers = getSalesforceService().getEndCustomers(endCustomer.getAccountId());
        assertContains(endCustomers, endCustomer);
        return endCustomers;
    }

    public EndCustomer create() {
        EndCustomer endCustomer = new EndCustomer();
        endCustomer.setName("Automated test");
        endCustomer.setCompanyRegistration("Automated test");
        endCustomer.setPrtgLogin("Automated test");
        endCustomer.setPrtgPassword("Automated test");
        endCustomer.setAccountId(ACCOUNT_ID);

        String id = getSalesforceService().create(endCustomer);
        endCustomer.setId(id);
        Assert.assertNotNull(id);
        try {
            checkIsSalesforce(endCustomer);
        } catch (Exception e) {
            delete(id);
            throw e;
        }

        return endCustomer;
    }

    public EndCustomer update(EndCustomer endCustomer) {
        endCustomer.setName("Automated test 2");
        endCustomer.setCompanyRegistration("Automated test 2");
        endCustomer.setPrtgLogin("Automated test 2");
        endCustomer.setPrtgPassword("Automated test 2");
        endCustomer.setAccountId(null);

        getSalesforceService().update(endCustomer);
        checkIsSalesforce(endCustomer);
        return endCustomer;
    }

    private void checkIsSalesforce(EndCustomer endCustomer) {
        EndCustomer inSf = getSalesforceService().getEndCustomer(endCustomer.getId());
        assertEquals(endCustomer, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(new String[]{id});
        EndCustomer endCustomer = getSalesforceService().getEndCustomer(id);
        Assert.assertNull(endCustomer);
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
