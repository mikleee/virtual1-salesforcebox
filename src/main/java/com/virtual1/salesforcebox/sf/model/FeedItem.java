package com.virtual1.salesforcebox.sf.model;

/**
 * @author Mikhail Tkachenko created on 17.08.16 14:51
 */
public class FeedItem extends ChildObject {
    private static final long serialVersionUID = 1L;

    private String body;
    private String fileName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
