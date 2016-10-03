package salesforceflow;

import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.model.Exchange;
import org.junit.Assert;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class ExchangeTestFlow extends AbstractTestFlow {

    public Exchange findExisting() {
        Exchange exchange = getSalesforceService().getExchange(EXCHANGE_ID);
        Assert.assertNotNull(exchange);
        return exchange;
    }

    public Exchange findByNameBasedOnExistingWxchange(Exchange exchange) {
        Exchange exchange1 = getSalesforceService().getExchangeByName(exchange.getName());
        Assert.assertNotNull(exchange1);
        assertEquals(exchange, exchange1);
        return exchange1;
    }

    public List<EndCustomer> getAccountListBasedOnExistingEndCustomer(EndCustomer endCustomer) {
        List<EndCustomer> endCustomers = getSalesforceService().getEndCustomers(endCustomer.getAccountId());
        assertContains(endCustomers, endCustomer);
        return endCustomers;
    }

    public Exchange create() {
        Exchange exchange = new Exchange();
        exchange.setName("Automated test");
        exchange.setExchangeName("Automated test");

        String id = getSalesforceService().create(exchange);
        exchange.setId(id);
        Assert.assertNotNull(id);
        try {
            checkIsSalesforce(exchange);
        } catch (Exception e) {
            delete(id);
            throw e;
        }

        return exchange;
    }

    private void checkIsSalesforce(Exchange exchange) {
        Exchange inSf = getSalesforceService().getExchange(EXCHANGE_ID);
        assertEquals(exchange, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(new String[]{id});
        Exchange exchange = getSalesforceService().getExchange(id);
        Assert.assertNull(exchange);
    }

    private void assertEquals(Exchange o1, Exchange o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(o1.getExchangeName(), o2.getExchangeName());
    }


}