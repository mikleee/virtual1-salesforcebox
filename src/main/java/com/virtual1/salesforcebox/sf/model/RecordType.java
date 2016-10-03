package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;

@SalesforceObject(table = "RecordType", staticClause = "IsActive=True")
public class RecordType extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Name")
    private String name;
    @SalesforceField(name = "SobjectType")
    private String sObjectType;
    @SalesforceField(name = "Description")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSObjectType() {
        return sObjectType;
    }

    public void setSObjectType(String sObjectType) {
        this.sObjectType = sObjectType;
    }
}
