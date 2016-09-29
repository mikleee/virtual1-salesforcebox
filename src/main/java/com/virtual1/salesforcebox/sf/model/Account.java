package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import com.virtual1.salesforcebox.sf.annotation.SalesforceRelation;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

@SalesforceObject(name = "Account")
public class Account extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Name")
    private String name;
    @SalesforceField(name = "PRTG_Username__c")
    private String oneViewUsername;
    @SalesforceField(name = "PRTG_Password__c")
    private String oneViewPassword;
    @SalesforceField(name = "PO_Number_Required__c")
    private boolean poNumberRequired;
    @SalesforceField(name = "BillingStreet")
    private String billingStreet;
    @SalesforceField(name = "BillingCity")
    private String billingCity;
    @SalesforceField(name = "BillingState")
    private String billingState;
    @SalesforceField(name = "BillingPostalCode")
    private String billingPostalCode;
    @SalesforceField(name = "BillingCountry")
    private String billingCountry;
    @SalesforceField(name = "ShippingStreet")
    private String shippingStreet;
    @SalesforceField(name = "ShippingCity")
    private String shippingCity;
    @SalesforceField(name = "ShippingState")
    private String shippingState;
    @SalesforceField(name = "ShippingPostalCode")
    private String shippingPostalCode;
    @SalesforceField(name = "ShippingCountry")
    private String shippingCountry;
    @SalesforceField(name = "Registered_Name__c")
    private String companyName;
    @SalesforceField(name = "Co_Reg__c")
    private String coReg;
    @SalesforceField(name = "COE_Approved__c")
    private String coeApproved;
    @SalesforceField(name = "RID_Number__c")
    private String ridNumber;
    @SalesforceField(name = "X1Cloud_Demo_Request_Blackout__c")
    private Date x1CloudDemoRequestBlackout;
    @SalesforceField(name = "Status__c")
    private String status;
    @SalesforceField(name = "PBT__c")
    private String pbt;
    @SalesforceField(name = "Sales_Order_Special_Instructions__c")
    private String salesOrderSpecialInstructions;
    @SalesforceRelation(name = "Owner")
    private User owner;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getCoeApproved() {
        return coeApproved;
    }

    public void setCoeApproved(String coeApproved) {
        this.coeApproved = coeApproved;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCoReg() {
        return coReg;
    }

    public void setCoReg(String coReg) {
        this.coReg = coReg;
    }

    public String getOneViewPassword() {
        return oneViewPassword;
    }

    public void setOneViewPassword(String oneViewPassword) {
        this.oneViewPassword = oneViewPassword;
    }

    public String getOneViewUsername() {
        return oneViewUsername;
    }

    public void setOneViewUsername(String oneViewUsername) {
        this.oneViewUsername = oneViewUsername;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public boolean isPoNumberRequired() {
        return poNumberRequired;
    }

    public void setPoNumberRequired(boolean poNumberRequired) {
        this.poNumberRequired = poNumberRequired;
    }

    public String getRidNumber() {
        return ridNumber;
    }

    public void setRidNumber(String ridNumber) {
        this.ridNumber = ridNumber;
    }

    public String getSalesOrderSpecialInstructions() {
        return salesOrderSpecialInstructions;
    }

    public void setSalesOrderSpecialInstructions(String salesOrderSpecialInstructions) {
        this.salesOrderSpecialInstructions = salesOrderSpecialInstructions;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingStreet() {
        return shippingStreet;
    }

    public void setShippingStreet(String shippingStreet) {
        this.shippingStreet = shippingStreet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getX1CloudDemoRequestBlackout() {
        return x1CloudDemoRequestBlackout;
    }

    public void setX1CloudDemoRequestBlackout(Date x1CloudDemoRequestBlackout) {
        this.x1CloudDemoRequestBlackout = x1CloudDemoRequestBlackout;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isActive() {
        return "Active".equals(status);
    }

    /**
     * is the billing address (shipping address in salesforcebox) empty
     */
    public boolean isBillingAddressMissing() {
        return StringUtils.isBlank(shippingStreet) &&
                StringUtils.isBlank(shippingCity) &&
                StringUtils.isBlank(shippingPostalCode) &&
                StringUtils.isBlank(shippingState) &&
                StringUtils.isBlank(shippingCountry);
    }
}
