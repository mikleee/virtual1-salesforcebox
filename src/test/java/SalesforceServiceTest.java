import com.virtual1.salesforcebox.sf.model.*;
import com.virtual1.salesforcebox.sf.util.MappingRegistry;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import salesforceflow.*;

import java.util.Map;
import java.util.Set;

/**
 * @author Mikhail Tkachenko created on 30.09.16 9:46
 */

public class SalesforceServiceTest {

    private AccountTestFlow accountTestFlow = new AccountTestFlow();
    private AnalogueLineTestFlow analogueLineTestFlow = new AnalogueLineTestFlow();
    private AttachmentTestFlow attachmentTestFlow = new AttachmentTestFlow();
    private ContactTestFlow contactTestFlow = new ContactTestFlow();
    private ChargeTypeTestFlow chargeTypeTestFlow = new ChargeTypeTestFlow();
    private EndCustomerTestFlow endCustomerTestFlow = new EndCustomerTestFlow();
    private ExchangeTestFlow exchangeTestFlow = new ExchangeTestFlow();
    private FeedItemTestFlow feedItemTestFlow = new FeedItemTestFlow();
    private RecordTypeTestFlow recordTypeTestFlow = new RecordTypeTestFlow();
    private SiteTestFlow siteTestFlow = new SiteTestFlow();
    private Virtual1DataCenterTestFlow virtual1DataCenterTestFlow = new Virtual1DataCenterTestFlow();

    @BeforeClass
    public static void init() {
        MappingRegistry.init();
    }

    @Test
    public void retrieveAccount() {
        Account account = accountTestFlow.findExisting();
        accountTestFlow.findByNameBasedOnExistingAccount(account);
    }

    @Test
    public void retrieveAnalogueLine() {
        AnalogueLine analogueLine = analogueLineTestFlow.findExisting();
        analogueLineTestFlow.findByAssetBasedOnExistingAnalogueLine(analogueLine);
    }

    @Test
    public void modifyAnalogueLine() {
        AnalogueLine analogueLine = analogueLineTestFlow.create();
        analogueLineTestFlow.delete(analogueLine.getId());
    }

    @Test
    public void retrieveAttachment() {
        attachmentTestFlow.findExisting();
    }

    @Test
    public void modifyAttachment() {
        Attachment attachment = attachmentTestFlow.create();
        attachmentTestFlow.update(attachment);
        attachmentTestFlow.delete(attachment.getId());
    }

    @Test
    public void retrieveChargeType() {
        ChargeType chargeType = chargeTypeTestFlow.findExisting();
        chargeTypeTestFlow.findByNameBasedOnExistingChargeType(chargeType);
    }

    @Test
    public void modifyChargeType() {
        ChargeType chargeType = chargeTypeTestFlow.create();
        chargeTypeTestFlow.delete(chargeType.getId());
    }

    @Test
    public void retrieveContact() {
        Contact contact = contactTestFlow.findExisting();
        contactTestFlow.findByEmailBasedOnExistingContact(contact);
        contactTestFlow.findByRoleBasedOnExistingContact(contact);
    }

    @Test
    public void modifyContact() {
        Contact contact = contactTestFlow.create();
        contact = contactTestFlow.update(contact);
        contactTestFlow.delete(contact.getId());
    }

    @Test
    public void retrieveEndCustomer() {
        EndCustomer endCustomer = endCustomerTestFlow.findExisting();
        endCustomerTestFlow.findByNameBasedOnExistingEndCustomer(endCustomer);
        endCustomerTestFlow.getAccountListBasedOnExistingEndCustomer(endCustomer);
    }

    @Test
    public void modifyEndCustomer() {
        EndCustomer endCustomer = endCustomerTestFlow.create();
        endCustomerTestFlow.update(endCustomer);
        endCustomerTestFlow.delete(endCustomer.getId());
    }

    @Test
    public void retrieveExchange() {
        Exchange exchange = exchangeTestFlow.findExisting();
        exchangeTestFlow.findByNameBasedOnExistingWxchange(exchange);
    }

    @Test
    public void modifyExchange() {
        Exchange exchange = exchangeTestFlow.create();
        endCustomerTestFlow.delete(exchange.getId());
    }

    @Test
    public void retrieveFeedItem() {
        feedItemTestFlow.findExisting();
    }

    @Test
    public void modifyFeedItem() {
        FeedItem feedItem = feedItemTestFlow.create();
        feedItemTestFlow.delete(feedItem.getId());
    }

    @Test
    public void retrievePickListValues() {
        Map<String, Set<String>> values = AbstractTestFlow.getSalesforceService().getPickListValues("Case");
        Assert.assertFalse(values.isEmpty());
        values = AbstractTestFlow.getSalesforceService().getPickListValues("Account");
        Assert.assertFalse(values.isEmpty());
    }

    @Test
    public void retrieveRecordType() {
        RecordType recordType = recordTypeTestFlow.findExisting();
        recordTypeTestFlow.findByObjectTypeAndNameBasedOnExistingRecordType(recordType);
    }

    @Test
    public void retrieveSite() {
        Site site = siteTestFlow.findExisting();
        siteTestFlow.findAccountSitesBasedOnExistingSite(site);
        siteTestFlow.findByEndCustomerAndNameBasedOnExistingSite(site);
    }

    @Test
    public void modifySite() {
        Site site = siteTestFlow.create();
        siteTestFlow.update(site);
        siteTestFlow.delete(site.getId());
    }

    @Test
    public void retrieveVirtual1DataCenters() {
        virtual1DataCenterTestFlow.findExisting();
    }

    @Test
    public void testConnection() {
        AbstractTestFlow.getSalesforceService().testConnection();
    }

}
