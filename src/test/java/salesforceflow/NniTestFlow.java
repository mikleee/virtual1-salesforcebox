package salesforceflow;

import com.virtual1.salesforcebox.sf.model.NNI;
import org.junit.Assert;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class NniTestFlow extends AbstractTestFlow {

    public NNI findExisting() {
        NNI nni = getSalesforceService().getNni(NNI_ID);
        Assert.assertNotNull(nni);
        return nni;
    }

    public NNI findByNameBasedOnExistingNni(NNI nni) {
        NNI nni1 = getSalesforceService().getNniByName(nni.getName());
        Assert.assertNotNull(nni1);
        assertEquals(nni, nni1);
        return nni1;
    }

    public NNI findByUpstreamDeviceNameBasedOnExistingNni(NNI nni) {
        NNI nni1 = getSalesforceService().getNniByUpstreamDeviceName(nni.getCarrierProvider().getName(), nni.getNniType(), nni.getUpstreamDeviceName());
        Assert.assertNotNull(nni1);
        assertEquals(nni, nni1);
        return nni1;
    }

    private void assertEquals(NNI o1, NNI o2) {
        Assert.assertEquals(o1.getId(), o2.getId());
        Assert.assertEquals(o1.getName(), o2.getName());
        Assert.assertEquals(o1.getCarrierNniId(), o2.getCarrierNniId());
        Assert.assertEquals(o1.getNniDescription(), o2.getNniDescription());
        Assert.assertEquals(o1.getUpstreamDeviceName(), o2.getUpstreamDeviceName());
        Assert.assertEquals(o1.getStatus(), o2.getStatus());
        Assert.assertEquals(o1.getNniType(), o2.getNniType());
        Assert.assertEquals(formatSfId(o1.getCarrierProvider().getId()), formatSfId(o2.getCarrierProvider().getId()));
        Assert.assertEquals(formatSfId(o1.getRecordType().getId()), formatSfId(o2.getRecordType().getId()));
    }


}
