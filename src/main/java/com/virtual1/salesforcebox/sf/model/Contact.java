package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import com.virtual1.salesforcebox.sf.annotation.SalesforceParentId;
import org.apache.commons.lang.StringUtils;

@SalesforceObject(table = "Contact", type = "Contact", staticClause = "Left_the_Company__c = false")
public class Contact extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Name", readOnly = true)
    private String name;
    @SalesforceField(name = "FirstName")
    private String firstName;
    @SalesforceField(name = "LastName")
    private String lastName;
    @SalesforceField(name = "Phone")
    private String telephone;
    @SalesforceField(name = "Email")
    private String email;
    @SalesforceField(name = "Title")
    private String jobTitle;
    @SalesforceField(name = "Role__c")
    private String roles;
    @SalesforceField(name = "X1Portal_User__c")
    private Boolean x1PortalUSer = true;
    @SalesforceField(name = "HasOptedOutOfEmail")
    private Boolean hasOptedOutOfEmail = false;
    @SalesforceField(name = "DoNotCall")
    private Boolean doNotCall = false;
    @SalesforceField(name = "Department")
    private String department = StringUtils.EMPTY;
    @SalesforceParentId(name = "AccountId")
    private String accountId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getX1PortalUSer() {
        return x1PortalUSer;
    }

    public void setX1PortalUSer(Boolean x1PortalUSer) {
        this.x1PortalUSer = x1PortalUSer;
    }

    public Boolean getHasOptedOutOfEmail() {
        return hasOptedOutOfEmail;
    }

    public void setHasOptedOutOfEmail(Boolean hasOptedOutOfEmail) {
        this.hasOptedOutOfEmail = hasOptedOutOfEmail;
    }

    public Boolean getDoNotCall() {
        return doNotCall;
    }

    public void setDoNotCall(Boolean doNotCall) {
        this.doNotCall = doNotCall;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
