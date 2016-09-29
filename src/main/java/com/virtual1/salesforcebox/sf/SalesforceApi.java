package com.virtual1.salesforcebox.sf;

import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.User;

/**
 * @author Mikhail Tkachenko created on 29.09.16 10:30
 */
public interface SalesforceApi {

    void testConnection();

    Account getAccount(String id);

    Account getAccountByName(String name);

    User getUser(String id);


}
