package com.virtual1.salesforcebox.sf.model;


/**
 * crated by m.tkachenko on 29.02.16 21:01
 */
public class SfEmail extends ChildObject {
    private static final long serialVersionUID = 1L;

    private String subject;
    private String fromName;
    private String fromAddress;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
