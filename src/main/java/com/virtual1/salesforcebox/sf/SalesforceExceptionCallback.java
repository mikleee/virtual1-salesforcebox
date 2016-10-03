package com.virtual1.salesforcebox.sf;

import com.sforce.soap.partner.fault.ApiFault;

public interface SalesforceExceptionCallback {

    void onError(ApiFault apiFault);

}
