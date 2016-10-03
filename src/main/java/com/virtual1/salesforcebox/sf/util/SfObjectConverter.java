package com.virtual1.salesforcebox.sf.util;

import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.bind.XmlObject;
import com.sforce.ws.util.Base64;
import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import com.virtual1.salesforcebox.sf.annotation.SalesforceParentId;
import com.virtual1.salesforcebox.sf.annotation.SalesforceRelation;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 29.09.16 12:38
 */
public class SfObjectConverter {

    public <T> T convert(XmlObject sObject, Class<T> type) {
        T t = instantiate(type);
        Map<Field, SfAccessor> accessors = MappingRegistry.getAccessors(type).getAccessors();
        for (Map.Entry<Field, SfAccessor> entry : accessors.entrySet()) {
            SfAccessor accessor = entry.getValue();
            Object value = retrieveFieldValue(accessor.getType(), accessor.getSfField(), sObject);
            accessor.set(t, value);
        }
        return t;
    }


    public SObject convert(Object source) {
        SObject sObject = new SObject();
        Class<?> type = source.getClass();
        sObject.setType(type.getAnnotation(SalesforceObject.class).table());

        Map<Field, SfAccessor> accessors = MappingRegistry.getAccessors(type).getAccessors();
        for (Field field : accessors.keySet()) {
            SfAccessor accessor = accessors.get(field);
            if (field.isAnnotationPresent(SalesforceField.class)) {
                setSalesforceField(sObject, accessor, source);
            } else if (field.isAnnotationPresent(SalesforceRelation.class)) {
                setSalesforceRelation(sObject, accessor, source);
            } else if (field.isAnnotationPresent(SalesforceParentId.class)) {
                setSalesforceParentId(sObject, accessor, source);
            }
        }

        return sObject;
    }

    private void setSalesforceField(SObject sObject, SfAccessor accessor, Object source) {
        if (!accessor.isImmutable()) {
            sObject.setField(accessor.getSfField(), accessor.get(source));
        }
    }

    private void setSalesforceRelation(SObject sObject, SfAccessor accessor, Object source) {
        Object value = accessor.get(source);
        if (value == null) {
            sObject.setFieldsToNull(new String[]{accessor.getSfField()});
        } else {
            SfObjectAccessor relationAccessor = MappingRegistry.getAccessors(accessor.getType());
            sObject.setField(accessor.getSfField(), relationAccessor.getId(value));
        }
    }

    private void setSalesforceParentId(SObject sObject, SfAccessor accessor, Object source) {
        Object value = accessor.get(source);
        if (value == null) {
            sObject.setFieldsToNull(new String[]{accessor.getSfField()});
        } else {
            sObject.setField(accessor.getSfField(), value);
        }
    }


    private <T> T instantiate(Class<T> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new SalesforceConfigurationException("Specify default constructor for " + type);
        }
    }

    private Object retrieveFieldValue(Class<?> type, String key, XmlObject sObject) {
        if (type == String.class) {
            return getString(sObject, key);
        } else if (type == Date.class) {
            return getDate(sObject, key);
        } else if (type == Boolean.class || type == boolean.class) {
            return getBoolean(sObject, key);
        } else if (type.isAnnotationPresent(SalesforceObject.class)) {
            key = SfQueryBuilder.normalizeRelationField(key);
            XmlObject child = sObject.getChild(key);
            return child != null && child.hasChildren() ? convert(child, type) : null;
        }

        throw new SalesforceConfigurationException("Cant retrieve value with type " + type + " from raw salesforce object. If it is Salesforce object it must be marked as @SalesforceObject");
    }

    private String getString(XmlObject sObject, String key) {
        return (String) sObject.getField(key);
    }

    private Date getDate(XmlObject sObject, String fieldName) {
        String strDate = (String) sObject.getField(fieldName);
        return StringUtils.isNotBlank(strDate) ? DatatypeConverter.parseDateTime(strDate).getTime() : null;
    }

    private boolean getBoolean(XmlObject sObject, String fieldName) {
        String str = (String) sObject.getField(fieldName);
        return Boolean.parseBoolean(str);
    }

    private byte[] getBytes(XmlObject sObject, String fieldName) {
        String str = (String) sObject.getField(fieldName);
        return str != null ? Base64.decode(str.getBytes()) : null;
    }


}




