package com.virtual1.salesforcebox.sf.model;


import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;

import java.nio.charset.Charset;

/**
 * crated by m.tkachenko on 29.02.16 21:01
 */
@SalesforceObject(table = "Attachment")
public class Attachment extends ChildObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Body")
    private byte[] body;


    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body.getBytes(Charset.forName("UTF-8"));
    }

}
