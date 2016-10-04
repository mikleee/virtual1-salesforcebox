package com.virtual1.salesforcebox.sf.model;


import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceId;
import com.virtual1.salesforcebox.sf.cache.CachedSalesforceObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.virtual1.salesforcebox.sf.annotation.SalesforceField.AccessLevel.READ_ONLY;

public abstract class BaseSalesforceObject extends CachedSalesforceObject {
    @SalesforceId
    @SalesforceField(name = "Id")
    protected String id;
    @SalesforceField(name = "CreatedDate", accessLevel = READ_ONLY)
    private Date createdDate;
    @SalesforceField(name = "LastModifiedDate", accessLevel = READ_ONLY)
    private Date lastModifiedDate;

    protected String name;
    private String recordTypeId;
    private String recordType;

    private final Map<String, Object> customFields = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecordType() {
        return recordType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordTypeId() {
        return recordTypeId;
    }

    public void setRecordTypeId(String recordTypeId) {
        this.recordTypeId = recordTypeId;
    }

    public Map<String, Object> getCustomFields() {
        return customFields;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder().append(getClass().getSimpleName()).append(": ");
        if (id != null) builder.append("id=").append(id).append(" ");
        if (recordType != null) builder.append("recordType=").append(recordType);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseSalesforceObject that = (BaseSalesforceObject) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        return lastModifiedDate != null ? lastModifiedDate.equals(that.lastModifiedDate) : that.lastModifiedDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
        return result;
    }
}
