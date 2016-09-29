
import com.virtual1.salesforcebox.sf.SalesforceService;
import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.model.User;
import com.virtual1.salesforcebox.sf.util.MappingRegistry;

/**
 * @author Mikhail Tkachenko created on 29.08.16 10:16
 */
public class Main {
    private static String userName = "system.attendant@virtual1.com.v1testing";
    private static String password = "p4GuCbdGPL";
    private static String token = "";
    private static boolean sandbox = true;
    private static String identifier = "misha test";

    public static void main(String[] args) {
       new MappingRegistry();
        SalesforceService salesforceService = new SalesforceService(userName, password, token, sandbox, identifier);
//        Account account = salesforceService.getAccount("0013000000pI4zg");
//
//        User user = salesforceService.getUser("005a0000009d6mq");
        EndCustomer endCustomer = salesforceService.getEndCustomer("a0cS0000001i5Fq");
        EndCustomer endCustomer1 = salesforceService.getEndCustomerByName(endCustomer.getAccountId(), endCustomer.getName());


    }


}