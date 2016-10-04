package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;

/**
 * @author Mikhail Tkachenko created on 17.08.16 14:51
 */
@SalesforceObject(table = "FeedItem")
public class FeedItem extends ChildObject {
    private static final long serialVersionUID = 1L;

    @SalesforceField(name = "Type")
    private String type = "ContentPost";
    @SalesforceField(name = "Body")
    private String body;
    @SalesforceField(name = "ContentFileName")
    private String contentFileName;
    @SalesforceField(name = "ContentData")
    private byte[] contentData;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getContentData() {
        return contentData;
    }

    public void setContentData(byte[] contentData) {
        this.contentData = contentData;
    }

    public String getContentFileName() {
        return contentFileName;
    }

    public void setContentFileName(String contentFileName) {
        this.contentFileName = contentFileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
