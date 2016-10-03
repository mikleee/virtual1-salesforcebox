package salesforceflow;

import com.virtual1.salesforcebox.sf.model.Account;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class AccountTestFlow extends AbstractTestFlow {

    public Account findExisting() {
        Account account = getSalesforceService().getAccount(ACCOUNT_ID);
        Assert.assertNotNull(account);
        return account;
    }

    public Account findByNameBasedOnExistingAccount(Account account) {
        Account account2 = getSalesforceService().getAccountByName(account.getName());
        Assert.assertNotNull(account2);
        assertEquals(account, account2);
        return account2;
    }

    private void assertEquals(Account o1, Account o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
    }


}
