package com.virtual1.salesforcebox.sf.util;

import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.bind.XmlObject;
import com.sforce.ws.util.Base64;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 29.09.16 12:38
 */
public class SfObjectConverter {

    public <T> T convert(XmlObject sObject, Class<T> type) {
        T t = instantiate(type);
        Map<Field, SfAccessor> accessors = MappingRegistry.getAccessor(type).getAccessors();
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

        SfObjectAccessor objectAccessor = MappingRegistry.getAccessor(type);
        for (Map.Entry<Field, SfAccessor> e : objectAccessor.getAccessors().entrySet()) {
            SfAccessor fieldAccessor = e.getValue();

            if (shouldValueBeSet(fieldAccessor, source)) {
                switch (fieldAccessor.getRelationType()) {
                    case SIMPLE_FIELD:
                        setSalesforceField(sObject, fieldAccessor, source);
                        break;
                    case RELATION:
                        setSalesforceRelation(sObject, fieldAccessor, source);
                        break;
                    case RELATION_ID:
                        setSalesforceParentId(sObject, fieldAccessor, source);
                        break;
                }
            }
        }

        return sObject;
    }

    private boolean shouldValueBeSet(SfAccessor accessor, Object source) {
        switch (accessor.getAccessLevel()) {
            case READ_ONLY:
                return false;
            case IMMUTABLE:
                Object id = MappingRegistry.getAccessor(source.getClass()).getId(source);
                return id == null;
            default:
                return true;
        }
    }

    public <T> T setId(T source, String id) {
        SfObjectAccessor accessor = MappingRegistry.getAccessor(source.getClass());
        accessor.setId(source, id);
        return source;
    }

    private void setSalesforceField(SObject sObject, SfAccessor accessor, Object source) {
        Object value = accessor.get(source);
        sObject.setField(accessor.getSfField(), convertSimpleSalesforceFieldValue(value));
    }

    private Object convertSimpleSalesforceFieldValue(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            return value.toString();
        } else {
            return value;
        }

    }

    private void setSalesforceRelation(SObject sObject, SfAccessor accessor, Object source) {
        Object value = accessor.get(source);
        if (value == null) {
            sObject.setFieldsToNull(new String[]{accessor.getSfField()});
        } else {
            SfObjectAccessor relationAccessor = MappingRegistry.getAccessor(accessor.getType());
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
        } else if (type == Integer.class || type == int.class) {
            return getInteger(sObject, key);
        } else if (type == BigDecimal.class) {
            return getBigDecimal(sObject, key);
        } else if (type == byte[].class) {
            return getBytes(sObject, key);
        } else if (type.isAnnotationPresent(SalesforceObject.class)) {
            key = SfQueryBuilder.normalizeRelationField(key);
            XmlObject child = sObject.getChild(key);
            return child != null && child.hasChildren() ? convert(child, type) : null;
        }

        throw new SalesforceConfigurationException("Cant retrieve value with relationType " + type + " from raw salesforce object. If it is Salesforce object it must be marked as @SalesforceObject");
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

    private Integer getInteger(XmlObject sObject, String fieldName) {
        String str = (String) sObject.getField(fieldName);
        return Double.valueOf(str).intValue();
    }

    private BigDecimal getBigDecimal(XmlObject sObject, String fieldName) {
        String str = (String) sObject.getField(fieldName);
        return str == null ? null : new BigDecimal(str);
    }

    private byte[] getBytes(XmlObject sObject, String fieldName) {
        String str = (String) sObject.getField(fieldName);
        return str != null ? Base64.decode(str.getBytes()) : null;
    }


}




