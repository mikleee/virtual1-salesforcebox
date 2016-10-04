package salesforceflow;

import com.virtual1.salesforcebox.sf.model.Exchange;
import org.junit.Assert;

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

    public Exchange create() {
        Exchange exchange = new Exchange();
        exchange.setName("Automated test");
        exchange.setExchangeName("Automated test");

        exchange = getSalesforceService().create(exchange);
        Assert.assertNotNull(exchange.getId());
        try {
            checkIsSalesforce(exchange);
        } catch (Exception e) {
            delete(exchange.getId());
            throw e;
        }

        return exchange;
    }

    private void checkIsSalesforce(Exchange exchange) {
        Exchange inSf = getSalesforceService().getExchange(EXCHANGE_ID);
        assertEquals(exchange, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(id);
        Exchange exchange = getSalesforceService().getExchange(id);
        Assert.assertNull(exchange);
    }

    private void assertEquals(Exchange o1, Exchange o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(o1.getExchangeName(), o2.getExchangeName());
    }


}
