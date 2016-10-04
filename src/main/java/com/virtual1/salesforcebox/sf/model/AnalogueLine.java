package com.virtual1.salesforcebox.sf.model;


import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;

import java.math.BigDecimal;
import java.util.Date;

@SalesforceObject(table = "Analogue_Line__c")
public class AnalogueLine extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Name", readOnly = true)
    private String name;
    @SalesforceField(name = "Annual_Rental_Cost__c")
    private BigDecimal annualRentalCost;
    @SalesforceField(name = "Carrier_Contract_mths__c")
    private Integer carrierContractMths;
    @SalesforceField(name = "Carrier_End_Date__c", readOnly = true)
    private Date carrierEndDate;
    @SalesforceField(name = "Carrier_Live_Date__c")
    private Date carrierLiveDate;
    @SalesforceField(name = "Carrier_Product_Name__c")
    private String carrierProductName;
    @SalesforceField(name = "Carrier_Service_ID__c")
    private String carrierServiceId;
    @SalesforceField(name = "End_Customer_Name__c", readOnly = true)
    private String endCustomerName;
    @SalesforceField(name = "One_Off_Cost__c")
    private BigDecimal oneOffCost;
    @SalesforceField(name = "Project_Status__c", readOnly = true)
    private String projectStatus;
    @SalesforceField(name = "Site_Name__c")
    private String siteName;
    @SalesforceField(name = "Status__c")
    private String status;
    @SalesforceField(name = "Call_Barring__c")
    private boolean callBarring;
    @SalesforceField(name = "Telephone_Number__c")
    private String telephoneNumber;
    @SalesforceField(name = "Access_ID__c", relationType = SalesforceField.RelationType.RELATION_ID)
    private String accessId;
    @SalesforceField(name = "Project_Number__c", relationType = SalesforceField.RelationType.RELATION_ID)
    private String projectId;
    @SalesforceField(name = "Carrier_Provider__c", relationType = SalesforceField.RelationType.RELATION)
    private Account carrierProvider;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessID) {
        this.accessId = accessID;
    }

    public BigDecimal getAnnualRentalCost() {
        return annualRentalCost;
    }

    public void setAnnualRentalCost(BigDecimal annualRentalCost) {
        this.annualRentalCost = annualRentalCost;
    }

    public Integer getCarrierContractMths() {
        return carrierContractMths;
    }

    public void setCarrierContractMths(Integer carrierContractMths) {
        if (carrierContractMths > 0) {
            this.carrierContractMths = carrierContractMths;
        }
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Date getCarrierEndDate() {
        return carrierEndDate;
    }

    public void setCarrierEndDate(Date carrierEndDate) {
        this.carrierEndDate = carrierEndDate;
    }

    public Date getCarrierLiveDate() {
        return carrierLiveDate;
    }

    public void setCarrierLiveDate(Date carrierLiveDate) {
        this.carrierLiveDate = carrierLiveDate;
    }

    public String getCarrierProductName() {
        return carrierProductName;
    }

    public void setCarrierProductName(String carrierProductName) {
        this.carrierProductName = carrierProductName;
    }

    public Account getCarrierProvider() {
        return carrierProvider;
    }

    public void setCarrierProvider(Account carrierProvider) {
        this.carrierProvider = carrierProvider;
    }

    public String getCarrierServiceId() {
        return carrierServiceId;
    }

    public void setCarrierServiceId(String carrierServiceId) {
        this.carrierServiceId = carrierServiceId;
    }

    public String getEndCustomerName() {
        return endCustomerName;
    }

    public void setEndCustomerName(String endCustomerName) {
        this.endCustomerName = endCustomerName;
    }

    public BigDecimal getOneOffCost() {
        return oneOffCost;
    }

    public void setOneOffCost(BigDecimal oneOffCost) {
        this.oneOffCost = oneOffCost;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCallBarring() {
        return callBarring;
    }

    public void setCallBarring(boolean callBarring) {
        this.callBarring = callBarring;
    }

}

