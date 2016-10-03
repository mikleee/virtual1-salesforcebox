package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.annotation.SalesforceId;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 30.09.16 12:53
 */
public class SfObjectAccessor {

    private Map<Field, SfAccessor> accessors = new HashMap<>();

    public Object getId(Object target) {
        Field idField = getIdField();
        return idField == null ? null : accessors.get(idField).get(target);
    }

    public void setId(Object target, Object value) {
        Field idField = getIdField();
        if (idField != null) {
            accessors.get(idField).set(target, value);
        }
    }

    public Field getIdField() {
        for (Field field : accessors.keySet()) {
            if (field.isAnnotationPresent(SalesforceId.class)) {
                return field;
            }
        }
        return null;
    }

    public Map<Field, SfAccessor> getAccessors() {
        return accessors;
    }
}
