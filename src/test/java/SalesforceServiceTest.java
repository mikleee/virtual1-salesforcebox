import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.Contact;
import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.model.Exchange;
import com.virtual1.salesforcebox.sf.model.Site;
import com.virtual1.salesforcebox.sf.util.MappingRegistry;
import org.junit.BeforeClass;
import org.junit.Test;
import salesforceflow.AccountTestFlow;
import salesforceflow.ContactTestFlow;
import salesforceflow.EndCustomerTestFlow;
import salesforceflow.ExchangeTestFlow;
import salesforceflow.SiteTestFlow;

/**
 * @author Mikhail Tkachenko created on 30.09.16 9:46
 */

public class SalesforceServiceTest {

    private AccountTestFlow accountTestFlow = new AccountTestFlow();
    private ContactTestFlow contactTestFlow = new ContactTestFlow();
    private EndCustomerTestFlow endCustomerTestFlow = new EndCustomerTestFlow();
    private ExchangeTestFlow exchangeTestFlow = new ExchangeTestFlow();
    private SiteTestFlow siteTestFlow = new SiteTestFlow();

    @BeforeClass
    public static void init() {
        new MappingRegistry();
    }

    @Test
    public void retrieveAccount() {
        Account account = accountTestFlow.findExisting();
        accountTestFlow.findByNameBasedOnExistingAccount(account);
    }

    @Test
    public void retrieveContact() {
        Contact contact = contactTestFlow.findExisting();
        contactTestFlow.findByEmailBasedOnExistingContact(contact);
        contactTestFlow.findByRoleBasedOnExistingContact(contact);
    }

    @Test
    public void modifyContact() {
        Contact contact = contactTestFlow.create();
        contact = contactTestFlow.update(contact);
        contactTestFlow.delete(contact.getId());
    }

    @Test
    public void retrieveEndCustomer() {
        EndCustomer endCustomer = endCustomerTestFlow.findExisting();
        endCustomerTestFlow.findByNameBasedOnExistingEndCustomer(endCustomer);
        endCustomerTestFlow.getAccountListBasedOnExistingEndCustomer(endCustomer);
    }

    @Test
    public void modifyEndCustomer() {
        EndCustomer endCustomer = endCustomerTestFlow.create();
        endCustomerTestFlow.update(endCustomer);
        endCustomerTestFlow.delete(endCustomer.getId());
    }

    @Test
    public void retrieveExchange() {
        Exchange exchange = exchangeTestFlow.findExisting();
        exchangeTestFlow.findByNameBasedOnExistingWxchange(exchange);
    }

    @Test
    public void modifyExchange() {
        Exchange exchange = exchangeTestFlow.create();
        endCustomerTestFlow.delete(exchange.getId());
    }

    @Test
    public void retrieveSite() {
        Site site = siteTestFlow.findExisting();
        siteTestFlow.findAccountSitesBasedOnExistingSite(site);
        siteTestFlow.findByEndCustomerAndNameBasedOnExistingSite(site);
    }

    @Test
    public void modifySite() {
        Site site = siteTestFlow.create();
        siteTestFlow.update(site);
        siteTestFlow.delete(site.getId());
    }

}
