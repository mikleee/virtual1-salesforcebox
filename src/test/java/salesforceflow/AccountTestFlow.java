package salesforceflow;

import com.virtual1.salesforcebox.sf.model.Account;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class AccountTestFlow extends AbstractTestFlow {

    public Account findExistingAccount() {
        Account account = getSalesforceService().getAccount(ACCOUNT_ID);
        Assert.assertNotNull(account);
        return account;
    }

    public Account findAccountByNameBasedOnExistingAccount(Account account) {
        Account account2 = getSalesforceService().getAccountByName(account.getName());
        Assert.assertNotNull(account2);
        assertEquals(account, account2);
        return account2;
    }

    private void assertEquals(Account a1, Account a2) {
        Assert.assertEquals(a1.getId(), a2.getId());
        Assert.assertEquals(a1.getName(), a2.getName());
    }


}
