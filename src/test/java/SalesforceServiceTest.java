import com.virtual1.salesforcebox.sf.SalesforceService;
import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.Contact;
import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.util.MappingRegistry;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 30.09.16 9:46
 */

public class SalesforceServiceTest {
    private static String userName = "system.attendant@virtual1.com.v1testing";
    private static String password = "p4GuCbdGPL";
    private static String token = "";
    private static boolean sandbox = true;
    private static String identifier = "Mikhail Tkachenko automated testing";

    private static SalesforceService salesforceService = new SalesforceService(userName, password, token, sandbox, identifier);

    @BeforeClass
    public static void init() {
        new MappingRegistry();
    }

    @Test
    public void account() {
        String id = "0013000000pI4zg";
        Account account = salesforceService.getAccount(id);
        Assert.assertNotNull(account);
        Account account2 = salesforceService.getAccountByName(account.getName());
        Assert.assertNotNull(account2);
        Assert.assertEquals(account.getId(), account2.getId());
    }

    @Test
    public void contact() {
        Contact contact;
        {
            final String id = "003a000002Nfv4t";
            contact = salesforceService.getContact(id);
            Assert.assertNotNull(contact);
            Contact contact2 = salesforceService.getContactByEmail(contact.getAccountId(), contact.getEmail());
            Assert.assertNotNull(contact2);
            Assert.assertEquals(contact, contact2);
            List<Contact> contactsByRoles = salesforceService.getContactsByRole(contact.getAccountId(), contact.getRoles());
            Assert.assertNotEquals(0, contactsByRoles.size());
        }

        Contact toBeCreated = new Contact();
        {
            toBeCreated.setAccountId(contact.getAccountId());
            toBeCreated.setEmail("automatedtest@automatedtest.automatedtest");
            toBeCreated.setName("automatedtest");
            toBeCreated.setFirstName("automatedtestF");
            toBeCreated.setLastName("automatedtestL");
            String newId = salesforceService.createContact(toBeCreated);
            toBeCreated.setId(newId);

            Contact justCreated = salesforceService.getContact(toBeCreated.getId());
            Assert.assertNotNull(justCreated);
            Assert.assertEquals(toBeCreated.getLastName(), justCreated.getLastName());
        }

        {
            toBeCreated.setAccountId(contact.getAccountId());
            toBeCreated.setEmail("automatedtest2@automatedtest.automatedtest");
            salesforceService.updateContact(toBeCreated);

            Contact justUpdated = salesforceService.getContact(toBeCreated.getId());
            Assert.assertNotNull(justUpdated);
            Assert.assertEquals(toBeCreated.getLastName(), justUpdated.getLastName());
        }

        salesforceService.delete(new String[]{toBeCreated.getId()});
        Assert.assertNull(salesforceService.getContact(toBeCreated.getId()));
    }

    @Test
    public void endCustomer() {
        String id = "a0cS0000001i5Fq";
        EndCustomer endCustomer = salesforceService.getEndCustomer(id);
        Assert.assertNotNull(endCustomer);
        EndCustomer endCustomer2 = salesforceService.getEndCustomerByName(endCustomer.getAccountId(), endCustomer.getName());
        Assert.assertNotNull(endCustomer2);
        Assert.assertEquals(endCustomer.getId(), endCustomer2.getId());
    }
}
