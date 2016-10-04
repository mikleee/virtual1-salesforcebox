package salesforceflow;

import com.virtual1.salesforcebox.sf.model.ChargeType;
import com.virtual1.salesforcebox.sf.model.Contact;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class ChargeTypeTestFlow extends AbstractTestFlow {


    public ChargeType findExisting() {
        ChargeType chargeType = getSalesforceService().getChargeType(CHARGE_TYPE_ID);
        Assert.assertNotNull(chargeType);
        return chargeType;
    }

    public ChargeType findByNameBasedOnExistingChargeType(ChargeType chargeType) {
        ChargeType chargeType1 = getSalesforceService().getChargeTypeByName(chargeType.getName());
        assertEquals(chargeType1, chargeType);
        return chargeType;
    }


    public ChargeType create() {
        ChargeType chargeType = new ChargeType();
        chargeType.setName(randomString());

        chargeType = getSalesforceService().create(chargeType);
        Assert.assertNotNull(chargeType.getId());
        try {
            checkInSalesforce(chargeType);
        } catch (Exception e) {
            delete(chargeType.getId());
            throw e;
        }

        return chargeType;
    }

    private void checkInSalesforce(ChargeType chargeType) {
        ChargeType inSf = getSalesforceService().getChargeType(chargeType.getId());
        assertEquals(chargeType, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(new String[]{id});
        Contact contact = getSalesforceService().getContact(id);
        Assert.assertNull(contact);
    }

    private void assertEquals(ChargeType o1, ChargeType o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
    }


}
