package salesforceflow;

import com.virtual1.salesforcebox.sf.model.AnalogueLine;
import com.virtual1.salesforcebox.sf.model.Contact;
import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.model.Site;
import org.junit.Assert;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class AnalogueLineTestFlow extends AbstractTestFlow {

    public AnalogueLine findExisting() {
        AnalogueLine analogueLine = getSalesforceService().getAnalogueLine(ANALOGUE_LINE_ID);
        Assert.assertNotNull(analogueLine);
        return analogueLine;
    }

    public List<AnalogueLine> findByAssetBasedOnExistingAnalogueLine(AnalogueLine analogueLine) {
        List<AnalogueLine> list = getSalesforceService().getAnalogueLinesByAccess(analogueLine.getAccessId());
        assertContains(list, analogueLine);
        return list;
    }

    public List<Site> findByEndCustomerAndNameBasedOnExistingSite(Site site) {
        List<Site> list = getSalesforceService().getEndCustomersSitesByName(site.getEndCustomer().getId(), site.getName());
        assertContains(list, site);
        return list;
    }


    public Site create() {
        Site site = new Site();
        site.setName("atest site name");
        site.setAddress("atest site address");
        site.setPostCode("E34JW");
        site.setPhone("01234567980");
        site.setSiteContact("atest contact");
        site.setUnitBuildingNumber("1");
        site.setBuildingName("atest building");
        site.setStreetNumber("1");
        site.setBuildingName("atest street");
        site.setTownCity("London");
        site.setCounty("UK");
        site.setAddressRef("atest address ref");
        site.setDistrictCode("atestDC");
        site.setQualifier("atest qualifier");
        site.setBuildConstructedBefore2000("Yes");
        site.setAsbestos("Yes");

        EndCustomer endCustomer = new EndCustomer();
        endCustomer.setId(END_CUSTOMER_ID);
        site.setEndCustomer(endCustomer);

        String id = getSalesforceService().create(site);
        site.setId(id);
        Assert.assertNotNull(id);
        try {
            checkInSalesforce(site);
        } catch (Exception e) {
            delete(id);
            throw e;
        }

        return site;
    }

    public Site update(Site site) {
        site.setName("atest site name1");
        site.setAddress("atest site address1");
        site.setPostCode("RH41EA");
        site.setPhone("01234567981");
        site.setSiteContact("atest contact1");
        site.setUnitBuildingNumber("11");
        site.setBuildingName("atest building1");
        site.setStreetNumber("11");
        site.setBuildingName("atest street1");
        site.setTownCity("Liverpool");
        site.setCounty("UK1");
        site.setAddressRef("atest address ref1");
        site.setDistrictCode("atestDC1");
        site.setQualifier("atest qualifier1");
        site.setBuildConstructedBefore2000("No");
        site.setAsbestos("No");

        EndCustomer endCustomer = new EndCustomer();
        endCustomer.setId(END_CUSTOMER_ID_2);
        site.setEndCustomer(endCustomer);

        getSalesforceService().update(site);
        checkInSalesforce(site);
        return site;
    }

    private void checkInSalesforce(Site site) {
        Site inSf = getSalesforceService().getSite(site.getId());
        assertEquals(site, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(new String[]{id});
        Contact contact = getSalesforceService().getContact(id);
        Assert.assertNull(contact);
    }

    private void assertEquals(Site o1, Site o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(o1.getAddress(), o2.getAddress());
        Assert.assertEquals(o1.getPostCode(), o2.getPostCode());
        Assert.assertEquals(o1.getPhone(), o2.getPhone());
        Assert.assertEquals(o1.getSiteContact(), o2.getSiteContact());
        Assert.assertEquals(o1.getUnitBuildingNumber(), o2.getUnitBuildingNumber());
        Assert.assertEquals(o1.getBuildingName(), o2.getBuildingName());
        Assert.assertEquals(o1.getTownCity(), o2.getTownCity());
        Assert.assertEquals(o1.getCounty(), o2.getCounty());
        Assert.assertEquals(o1.getAddressRef(), o2.getAddressRef());
        Assert.assertEquals(o1.getDistrictCode(), o2.getDistrictCode());
        Assert.assertEquals(o1.getQualifier(), o2.getQualifier());
        Assert.assertEquals(o1.getBuildConstructedBefore2000(), o2.getBuildConstructedBefore2000());
        Assert.assertEquals(o1.getAsbestos(), o2.getAsbestos());
        Assert.assertEquals(formatEndCustomerId(o1.getEndCustomer().getId()), formatEndCustomerId(o2.getEndCustomer().getId()));
    }


}
