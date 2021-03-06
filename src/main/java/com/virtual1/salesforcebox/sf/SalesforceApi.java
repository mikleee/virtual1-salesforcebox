package com.virtual1.salesforcebox.sf;

import com.virtual1.salesforcebox.sf.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mikhail Tkachenko created on 29.09.16 10:30
 */
public interface SalesforceApi {

    Account getAccount(String id);

    Account getAccountByName(String name);

    AnalogueLine getAnalogueLine(String id);

    List<AnalogueLine> getAnalogueLinesByAccess(String accessId);

    AnalogueLine create(AnalogueLine analogueLine);

    Attachment getAttachment(String id);

    Attachment create(Attachment attachment);

    Attachment update(Attachment attachment);

    ChargeType getChargeType(String id);

    ChargeType getChargeTypeByName(String name);

    ChargeType create(ChargeType chargeType);

    Contact getContact(String id);

    Contact getContactByEmail(String accountId, String email);

    List<Contact> getContactsByRole(String accountId, String role);

    Contact create(Contact contact);

    Contact update(Contact contact);

    EndCustomer getEndCustomer(String id);

    EndCustomer getEndCustomerByName(String accountId, String name);

    List<EndCustomer> getEndCustomers(String accountId);

    EndCustomer create(EndCustomer endCustomer);

    EndCustomer update(EndCustomer endCustomer);

    Exchange getExchange(String id);

    Exchange getExchangeByName(String name);

    Exchange create(Exchange exchange);

    /**
     * @deprecated http://salesforce.stackexchange.com/questions/110855/rest-api-feeditem-contentdata-download
     */
    FeedItem getFeedItem(String id);

    /**
     * @deprecated http://salesforce.stackexchange.com/questions/110855/rest-api-feeditem-contentdata-download
     */
    FeedItem create(FeedItem feedItem);

    Map<String, Set<String>> getPickListValues(String sObjectType);

    NNI getNni(String id);

    NNI getNniByName(String name);

    NNI getNniByUpstreamDeviceName(String carrierProviderName, String nniType, String upstreamDeviceName);

    RecordType getRecordType(String id);

    RecordType getRecordTypeByObjectTypeAndName(String objectType, String name);

    Site getSite(String id);

    List<Site> getSites(String accountId);

    List<Site> getEndCustomersSitesByName(String endCustomerId, String siteName);

    Site create(Site site);

    Site update(Site site);

    void testConnection();

    List<Virtual1DataCenter> getVirtual1DataCenters();

    User getUser(String id);


    String delete(String id);
}
