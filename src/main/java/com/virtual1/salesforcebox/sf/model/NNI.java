package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;

import static com.virtual1.salesforcebox.sf.annotation.SalesforceField.RelationType.RELATION;

@SalesforceObject(table = "NNI__c")
public class NNI extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Name")
    private String name;
    @SalesforceField(name = "Carrier_NNI_ID__c")
    private String carrierNniId;
    @SalesforceField(name = "NNI_Description__c")
    private String nniDescription;
    @SalesforceField(name = "Upstream_Device_Name__c")
    private String upstreamDeviceName;
    @SalesforceField(name = "Status__c")
    private String status;
    @SalesforceField(name = "NNI_Type__c")
    private String nniType;
    @SalesforceField(name = "Carrier_Provider__c", relationType = RELATION)
    private Account carrierProvider;
    @SalesforceField(name = "RecordType", relationType = RELATION)
    private RecordType recordType;


    public Account getCarrierProvider() {
        return carrierProvider;
    }

    public void setCarrierProvider(Account carrierProvider) {
        this.carrierProvider = carrierProvider;
    }

    public String getCarrierNniId() {
        return carrierNniId;
    }

    public void setCarrierNniId(String carrierNniId) {
        this.carrierNniId = carrierNniId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNniDescription() {
        return nniDescription;
    }

    public void setNniDescription(String nniDescription) {
        this.nniDescription = nniDescription;
    }

    public String getNniType() {
        return nniType;
    }

    public void setNniType(String nniType) {
        this.nniType = nniType;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpstreamDeviceName() {
        return upstreamDeviceName;
    }

    public void setUpstreamDeviceName(String upstreamDeviceName) {
        this.upstreamDeviceName = upstreamDeviceName;
    }
}
