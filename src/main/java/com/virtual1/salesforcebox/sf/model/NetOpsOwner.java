package com.virtual1.salesforcebox.sf.model;

import java.util.ArrayList;
import java.util.List;

public class NetOpsOwner extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    private List<NetOpsCase> cases = new ArrayList<>();

    public List<NetOpsCase> getCases() {
        return cases;
    }

    public void setCases(List<NetOpsCase> cases) {
        this.cases = cases;
    }

}
