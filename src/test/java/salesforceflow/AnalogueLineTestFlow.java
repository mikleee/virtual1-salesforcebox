package salesforceflow;

import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.AnalogueLine;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class AnalogueLineTestFlow extends AbstractTestFlow {
    private final static String ANALOGUE_LINE_ACCESS_ID = "a09a000000d2zYc";
    private final static String ANALOGUE_LINE_PROJECT_ID = "a06a000000dXXKs";
    private final static String ANALOGUE_LINE_CARRIER_ID = "0013000000pK6CE";

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


    public AnalogueLine create() {
        AnalogueLine analogueLine = new AnalogueLine();
        analogueLine.setName("atest");
        analogueLine.setAnnualRentalCost(new BigDecimal("10.00"));
        analogueLine.setCarrierContractMths(10);
        analogueLine.setCarrierProductName("atest product name");
        analogueLine.setCarrierServiceId("atest carrier service id");
        analogueLine.setOneOffCost(new BigDecimal("12"));
        analogueLine.setCallBarring(true);
        analogueLine.setTelephoneNumber("01480367890");
        analogueLine.setAccessId(ANALOGUE_LINE_ACCESS_ID);
        analogueLine.setProjectId(ANALOGUE_LINE_PROJECT_ID);

        Account account = new Account();
        account.setId(ANALOGUE_LINE_CARRIER_ID);
        analogueLine.setCarrierProvider(account);

        analogueLine = getSalesforceService().create(analogueLine);
        Assert.assertNotNull(analogueLine.getId());
        try {
            checkInSalesforce(analogueLine);
        } catch (Exception e) {
            delete(analogueLine.getId());
            throw e;
        }

        return analogueLine;
    }

    private void checkInSalesforce(AnalogueLine analogueLine) {
        AnalogueLine inSf = getSalesforceService().getAnalogueLine(analogueLine.getId());
        assertEquals(analogueLine, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(id);
        AnalogueLine analogueLine = getSalesforceService().getAnalogueLine(id);
        Assert.assertNull(analogueLine);
    }

    private void assertEquals(AnalogueLine o1, AnalogueLine o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        assertEquals(o1.getAnnualRentalCost(), o2.getAnnualRentalCost());
        Assert.assertEquals(o1.getCarrierContractMths(), o2.getCarrierContractMths());
        Assert.assertEquals(o1.getCarrierProductName(), o2.getCarrierProductName());
        Assert.assertEquals(o1.getCarrierServiceId(), o2.getCarrierServiceId());
        assertEquals(o1.getOneOffCost(), o2.getOneOffCost());
        Assert.assertEquals(o1.isCallBarring(), o2.isCallBarring());
        Assert.assertEquals(o1.getTelephoneNumber(), o2.getTelephoneNumber());
        Assert.assertEquals(formatSfId(o1.getAccessId()), formatSfId(o2.getAccessId()));
        Assert.assertEquals(formatSfId(o1.getProjectId()), formatSfId(o2.getProjectId()));
        Assert.assertEquals(formatSfId(o1.getCarrierProvider().getId()), formatSfId(o2.getCarrierProvider().getId()));
    }


}
