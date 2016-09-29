package com.virtual1.salesforcebox.sf.model;

public class RecordType extends BaseSalesforceObject {

    private String description;
    private String sObjectType;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getsObjectType() {
        return sObjectType;
    }

    public void setsObjectType(String sObjectType) {
        this.sObjectType = sObjectType;
    }
}
