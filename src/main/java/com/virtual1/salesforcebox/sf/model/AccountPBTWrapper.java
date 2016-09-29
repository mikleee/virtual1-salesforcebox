package com.virtual1.salesforcebox.sf.model;

import java.io.Serializable;
import java.util.List;

public class AccountPBTWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private PbtValue status;
    private List<String> values;
    private String selectedValue;

    public enum PbtValue {
        NONE, SELECTED
    }

    public PbtValue getStatus() {
        return status;
    }

    public void setStatus(PbtValue status) {
        this.status = status;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }
}
