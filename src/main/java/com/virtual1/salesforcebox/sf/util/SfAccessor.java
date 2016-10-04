package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceParentId;
import com.virtual1.salesforcebox.sf.annotation.SalesforceRelation;

import java.lang.reflect.Field;

/**
 * @author Mikhail Tkachenko created on 09.08.16 15:33
 */
class SfAccessor extends Accessor {
    private final String sfField;
    private final boolean readOnly;
    private final boolean immutable;


    SfAccessor(Field field, SalesforceField a) {
        super(field);
        this.sfField = a.name();
        this.readOnly = a.readOnly();
        this.immutable = a.immutable();
    }


    SfAccessor(Field field, SalesforceParentId a) {
        super(field);
        this.sfField = a.name();
        this.readOnly = a.readOnly();
        this.immutable = a.immutable();
    }

    SfAccessor(Field field, SalesforceRelation a) {
        super(field);
        this.sfField = a.name();
        this.readOnly = a.readOnly();
        this.immutable = a.immutable();
    }


    public String getSfField() {
        return sfField;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public boolean isImmutable() {
        return immutable;
    }
}