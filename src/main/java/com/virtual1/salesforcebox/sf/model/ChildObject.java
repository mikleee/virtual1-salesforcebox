package com.virtual1.salesforcebox.sf.model;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;

import static com.virtual1.salesforcebox.sf.annotation.SalesforceField.AccessLevel.IMMUTABLE;
import static com.virtual1.salesforcebox.sf.annotation.SalesforceField.RelationType.RELATION_ID;

/**
 * @author Mikhail Tkachenko created on 17.08.16 15:05
 */
public abstract class ChildObject extends BaseSalesforceObject {
    @SalesforceField(name = "Name")
    private String name;
    @SalesforceField(name = "ParentId", relationType = RELATION_ID, accessLevel = IMMUTABLE)
    private String parentId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(super.toString());
        if (parentId != null) builder.append(", parentId=").append(parentId);
        return builder.toString();
    }

}
