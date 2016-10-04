package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;

import java.lang.reflect.Field;

/**
 * @author Mikhail Tkachenko created on 09.08.16 15:33
 */
class SfAccessor extends Accessor {
    private final String sfField;
    private final SalesforceField.RelationType relationType;
    private final SalesforceField.AccessLevel accessLevel;


    SfAccessor(Field field, SalesforceField a) {
        super(field);
        this.sfField = a.name();
        this.relationType = a.relationType();
        this.accessLevel = a.accessLevel();
    }


    public String getSfField() {
        return sfField;
    }


    public SalesforceField.AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public SalesforceField.RelationType getRelationType() {
        return relationType;
    }
}