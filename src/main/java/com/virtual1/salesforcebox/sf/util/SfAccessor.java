package com.virtual1.salesforcebox.sf.util;

import java.lang.reflect.Field;

/**
 * @author Mikhail Tkachenko created on 09.08.16 15:33
 */
class SfAccessor extends Accessor {
    private final String sfField;

    SfAccessor(Field field, String sfField) {
        super(field);
        this.sfField = sfField;
    }


    public String getSfField() {
        return sfField;
    }

}