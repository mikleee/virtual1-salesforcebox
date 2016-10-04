package salesforceflow;

import com.virtual1.salesforcebox.sf.SalesforceConstants;
import com.virtual1.salesforcebox.sf.model.Contact;
import org.junit.Assert;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class ContactTestFlow extends AbstractTestFlow {


    public Contact findExisting() {
        Contact contact = getSalesforceService().getContact(CONTACT_ID);
        Assert.assertNotNull(contact);
        return contact;
    }

    public Contact findByEmailBasedOnExistingContact(Contact contact) {
        Contact contact2 = getSalesforceService().getContactByEmail(contact.getAccountId(), contact.getEmail());
        Assert.assertNotNull(contact2);
        assertEquals(contact, contact2);
        return contact2;
    }

    public Contact findByRoleBasedOnExistingContact(Contact contact) {
        List<Contact> contacts = getSalesforceService().getContactsByRole(contact.getAccountId(), contact.getRoles());
        assertContains(contacts, contact);
        return contact;
    }

    public Contact create() {
        Contact contact = new Contact();
        contact.setName("atest atest");
        contact.setFirstName("atest");
        contact.setLastName("atest");
        contact.setTelephone("01234567890");
        contact.setEmail("atest@atest.atest");
        contact.setJobTitle("Job Title");
        contact.setRoles(SalesforceConstants.ROLE_PROVISIONING);
        contact.setX1PortalUSer(true);
        contact.setHasOptedOutOfEmail(true);
        contact.setDoNotCall(true);
        contact.setDepartment("Department");
        contact.setAccountId(ACCOUNT_ID);

        contact = getSalesforceService().create(contact);
        Assert.assertNotNull(contact.getId());
        try {
            checkInSalesforce(contact);
        } catch (Exception e) {
            delete(contact.getId());
            throw e;
        }

        return contact;
    }

    public Contact update(Contact contact) {
        contact.setName("atest1 atest1");
        contact.setFirstName("atest1");
        contact.setLastName("atest1");
        contact.setTelephone("01234567891");
        contact.setEmail("atest1@atest.atest");
        contact.setJobTitle("Job Title1");
        contact.setRoles(SalesforceConstants.ROLE_PROVISIONING);
        contact.setX1PortalUSer(false);
        contact.setHasOptedOutOfEmail(false);
        contact.setDoNotCall(false);
        contact.setDepartment("Department1");
        contact.setAccountId(null);

        getSalesforceService().update(contact);
        checkInSalesforce(contact);
        return contact;
    }

    private void checkInSalesforce(Contact contact) {
        Contact inSf = getSalesforceService().getContact(contact.getId());
        assertEquals(contact, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(new String[]{id});
        Contact contact = getSalesforceService().getContact(id);
        Assert.assertNull(contact);
    }

    private void assertEquals(Contact c1, Contact c2) {
        Assert.assertEquals(c1.getId(), c2.getId());
        Assert.assertEquals(c1.getFirstName(), c2.getFirstName());
        Assert.assertEquals(c1.getLastName(), c2.getLastName());
        Assert.assertEquals(c1.getTelephone(), c2.getTelephone());
        Assert.assertEquals(c1.getJobTitle(), c2.getJobTitle());
        Assert.assertEquals(c1.getRoles(), c2.getRoles());
        Assert.assertEquals(c1.getX1PortalUSer(), c2.getX1PortalUSer());
        Assert.assertEquals(c1.getHasOptedOutOfEmail(), c2.getHasOptedOutOfEmail());
        Assert.assertEquals(c1.getDoNotCall(), c2.getDoNotCall());
        Assert.assertEquals(c1.getDepartment(), c2.getDepartment());
        Assert.assertEquals(formatSfId(c1.getAccountId()), formatSfId(c2.getAccountId()));
    }


}
