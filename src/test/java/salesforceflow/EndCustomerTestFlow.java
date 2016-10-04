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
        endCustomer.setName(randomString());
        endCustomer.setCompanyRegistration(randomString());
        endCustomer.setPrtgLogin(randomString());
        endCustomer.setPrtgPassword(randomString());
        endCustomer.setAccountId(ACCOUNT_ID);

        endCustomer = getSalesforceService().create(endCustomer);
        Assert.assertNotNull(endCustomer.getId());
        try {
            checkIsSalesforce(endCustomer);
        } catch (Exception e) {
            delete(endCustomer.getId());
            throw e;
        }

        return endCustomer;
    }

    public EndCustomer update(EndCustomer endCustomer) {
        endCustomer.setName(randomString());
        endCustomer.setCompanyRegistration(randomString());
        endCustomer.setPrtgLogin(randomString());
        endCustomer.setPrtgPassword(randomString());
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
        getSalesforceService().delete(id);
        EndCustomer endCustomer = getSalesforceService().getEndCustomer(id);
        Assert.assertNull(endCustomer);
    }

    private void assertEquals(EndCustomer o1, EndCustomer o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(o1.getCompanyRegistration(), o2.getCompanyRegistration());
        Assert.assertEquals(o1.getPrtgLogin(), o2.getPrtgLogin());
        Assert.assertEquals(o1.getPrtgPassword(), o2.getPrtgPassword());
        Assert.assertEquals(formatSfId(o1.getAccountId()), formatSfId(o2.getAccountId()));
    }


}
