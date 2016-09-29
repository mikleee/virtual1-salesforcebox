package com.virtual1.salesforcebox.sf;

import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.Contact;
import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.model.User;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 29.09.16 10:30
 */
public interface SalesforceApi {

    Account getAccount(String id);

    Account getAccountByName(String name);

    Contact getContact(String id);

    Contact getContactByEmail(String accountId, String email);

    List<Contact> getContactsByRole(String accountId, String role);

    EndCustomer getEndCustomer(String id);

    EndCustomer getEndCustomerByName(String accountId, String name);

    void testConnection();

    User getUser(String id);


}
