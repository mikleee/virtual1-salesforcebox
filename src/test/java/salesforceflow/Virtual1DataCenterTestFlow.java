package salesforceflow;

import com.virtual1.salesforcebox.sf.model.Virtual1DataCenter;

import java.util.List;

/**
 * @author Mikhail Tkachenko created on 03.10.16 11:13
 */
public class Virtual1DataCenterTestFlow extends AbstractTestFlow {

    public List<Virtual1DataCenter> findExisting() {
        return getSalesforceService().getVirtual1DataCenters();
    }

}
