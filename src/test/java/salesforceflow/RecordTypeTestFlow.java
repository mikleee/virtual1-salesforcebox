package salesforceflow;

import com.virtual1.salesforcebox.sf.model.RecordType;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class RecordTypeTestFlow extends AbstractTestFlow {


    public RecordType findExisting() {
        RecordType recordType = getSalesforceService().getRecordType(RECORD_TYPE_ID);
        Assert.assertNotNull(recordType);
        return recordType;
    }

    public RecordType findByObjectTypeAndNameBasedOnExistingRecordType(RecordType recordType) {
        RecordType recordType1 = getSalesforceService().getRecordTypeByObjectTypeAndName(recordType.getSObjectType(), recordType.getName());
        assertEquals(recordType, recordType1);
        return recordType1;
    }

    private void checkInSalesforce(RecordType recordType) {
        RecordType inSf = getSalesforceService().getRecordType(recordType.getId());
        assertEquals(recordType, inSf);
    }

    public void delete(String id) {
        getSalesforceService().delete(id);
        RecordType recordType = getSalesforceService().getRecordType(id);
        Assert.assertNull(recordType);
    }

    private void assertEquals(RecordType o1, RecordType o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(o1.getDescription(), o2.getDescription());
        Assert.assertEquals(o1.getSObjectType(), o2.getSObjectType());
    }


}
