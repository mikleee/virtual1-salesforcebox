package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;

@SalesforceObject(table = "Site__c")
public class Site extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Name")
    private String name;
    @SalesforceField(name = "Address_c__c")
    private String address;
    @SalesforceField(name = "Post_Code__c")
    private String postCode;
    @SalesforceField(name = "Phone__c")
    private String phone;
    @SalesforceField(name = "Site_Contact_End__c")
    private String siteContact;
    @SalesforceField(name = "Unit_Building_number__c")
    private String unitBuildingNumber;
    @SalesforceField(name = "Building_name__c")
    private String buildingName;
    @SalesforceField(name = "Street_number__c")
    private String streetNumber;
    @SalesforceField(name = "Street_name__c")
    private String streetName;
    @SalesforceField(name = "Town_City__c")
    private String townCity;
    @SalesforceField(name = "County__c")
    private String county;
    @SalesforceField(name = "Address_Ref__c")
    private String addressRef;
    @SalesforceField(name = "District_Code__c")
    private String districtCode;
    @SalesforceField(name = "Qualifier__c")
    private String qualifier;
    @SalesforceField(name = "Building_Constructed_before_year_2000__c")
    private String buildConstructedBefore2000;
    @SalesforceField(name = "Asbestos_Register_Available_on_Site__c")
    private String asbestos;
    @SalesforceField(name = "End_Customer_Name__c", relationType = SalesforceField.RelationType.RELATION)
    private EndCustomer endCustomer = new EndCustomer();


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressRef() {
        return addressRef;
    }

    public void setAddressRef(String addressRef) {
        this.addressRef = addressRef;
    }

    public String getAsbestos() {
        return asbestos;
    }

    public void setAsbestos(String asbestos) {
        this.asbestos = asbestos;
    }

    public String getBuildConstructedBefore2000() {
        return buildConstructedBefore2000;
    }

    public void setBuildConstructedBefore2000(String buildConstructedBefore2000) {
        this.buildConstructedBefore2000 = buildConstructedBefore2000;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public EndCustomer getEndCustomer() {
        return endCustomer;
    }

    public void setEndCustomer(EndCustomer endCustomer) {
        this.endCustomer = endCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getSiteContact() {
        return siteContact;
    }

    public void setSiteContact(String siteContact) {
        this.siteContact = siteContact;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getUnitBuildingNumber() {
        return unitBuildingNumber;
    }

    public void setUnitBuildingNumber(String unitBuildingNumber) {
        this.unitBuildingNumber = unitBuildingNumber;
    }
}
