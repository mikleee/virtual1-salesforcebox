package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import com.virtual1.salesforcebox.sf.annotation.SalesforceParentId;

@SalesforceObject(table = "End_Customer__c")
public class EndCustomer extends BaseSalesforceObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Name")
    private String name;
    @SalesforceField(name = "Company_Registration__c")
    private String companyRegistration;
    @SalesforceField(name = "PRTG_Username__c")
    private String prtgLogin;
    @SalesforceField(name = "PRTG_Password__c")
    private String prtgPassword;
    @SalesforceParentId
    @SalesforceField(name = "Account_Name__c")
    private String accountId;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCompanyRegistration() {
        return companyRegistration;
    }

    public void setCompanyRegistration(String companyRegistration) {
        this.companyRegistration = companyRegistration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrtgLogin() {
        return prtgLogin;
    }

    public void setPrtgLogin(String prtgLogin) {
        this.prtgLogin = prtgLogin;
    }

    public String getPrtgPassword() {
        return prtgPassword;
    }

    public void setPrtgPassword(String prtgPassword) {
        this.prtgPassword = prtgPassword;
    }

}
