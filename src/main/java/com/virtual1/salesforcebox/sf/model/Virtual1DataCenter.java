package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;


@SalesforceObject(table = "Virtual1Datacentres__c", staticClause = "IsDeleted=false")
public class Virtual1DataCenter extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    public static final String RECORD_TYPE_VPN = "Datacentre Postcode";


    @SalesforceField(name = "Name")
    private String name;
    @SalesforceField(name = "Postcode__c")
    private String postCode;
    @SalesforceField(name = "Virtual1_Exchange_Name__c")
    private String exchangeName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

}
