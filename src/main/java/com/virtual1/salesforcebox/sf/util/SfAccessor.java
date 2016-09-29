package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;

import java.lang.reflect.Field;

/**
 * @author Mikhail Tkachenko created on 09.08.16 15:33
 */
class SfAccessor extends Accessor {
    private final String sfField;

    SfAccessor(Field field, SalesforceField annotation) {
        super(field);
        this.sfField = annotation.name();
    }


    public String getSfField() {
        return sfField;
    }

}