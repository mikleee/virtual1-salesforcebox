import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.Contact;
import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.util.MappingRegistry;
import org.junit.BeforeClass;
import org.junit.Test;
import salesforceflow.AccountTestFlow;
import salesforceflow.ContactTestFlow;
import salesforceflow.EndCustomerTestFlow;

/**
 * @author Mikhail Tkachenko created on 30.09.16 9:46
 */

public class SalesforceServiceTest {


    private AccountTestFlow accountTestFlow = new AccountTestFlow();
    private ContactTestFlow contactTestFlow = new ContactTestFlow();
    private EndCustomerTestFlow endCustomerTestFlow = new EndCustomerTestFlow();

    @BeforeClass
    public static void init() {
        new MappingRegistry();
    }

    @Test
    public void retrieveAccount() {
        Account account = accountTestFlow.findExistingAccount();
        accountTestFlow.findAccountByNameBasedOnExistingAccount(account);
    }

    @Test
    public void retrieveContact() {
        Contact contact = contactTestFlow.findExistingContact();
        contactTestFlow.findContactByEmailBasedOnExistingContact(contact);
        contactTestFlow.findContactByRoleBasedOnExistingContact(contact);
    }

    @Test
    public void modifyContact() {
        Contact contact = contactTestFlow.createContact();
        contact = contactTestFlow.updateContact(contact);
        contactTestFlow.delete(contact.getId());
    }

    @Test
    public void retrieveEndCustomer() {
        EndCustomer endCustomer = endCustomerTestFlow.findExistingEndCustomer();
        endCustomerTestFlow.findByNameBasedOnExistingEndCustomer(endCustomer);
    }
}
