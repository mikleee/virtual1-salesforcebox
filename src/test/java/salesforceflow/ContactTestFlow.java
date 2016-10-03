package salesforceflow;

import com.virtual1.salesforcebox.sf.SalesforceConstants;
import com.virtual1.salesforcebox.sf.model.Contact;
import org.junit.Assert;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class ContactTestFlow extends AbstractTestFlow {


    public Contact findExistingContact() {
        Contact contact = getSalesforceService().getContact(CONTACT_ID);
        Assert.assertNotNull(contact);
        return contact;
    }

    public Contact findContactByEmailBasedOnExistingContact(Contact contact) {
        Contact contact2 = getSalesforceService().getContactByEmail(contact.getAccountId(), contact.getEmail());
        Assert.assertNotNull(contact2);
        assertEquals(contact, contact2);
        return contact2;
    }

    public Contact findContactByRoleBasedOnExistingContact(Contact contact) {
        List<Contact> contacts = getSalesforceService().getContactsByRole(contact.getAccountId(), contact.getRoles());
        assertContains(contacts, contact);
        return contact;
    }

    public Contact createContact() {
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

        String id = getSalesforceService().createContact(contact);
        contact.setId(id);
        Assert.assertNotNull(id);
        try {
            checkIsSalesforce(contact);
        } catch (Exception e) {
            delete(id);
            throw e;
        }

        return contact;
    }

    public Contact updateContact(Contact contact) {
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

        getSalesforceService().updateContact(contact);
        checkIsSalesforce(contact);
        return contact;
    }

    private Contact checkIsSalesforce(Contact contact) {
        Contact inSf = getSalesforceService().getContact(contact.getId());
        assertEquals(contact, inSf);
        return contact;
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
        Assert.assertEquals(formatAccountId(c1.getAccountId()), formatAccountId(c2.getAccountId()));
    }



}
