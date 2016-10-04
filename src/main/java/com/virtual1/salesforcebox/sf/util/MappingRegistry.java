package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import com.virtual1.salesforcebox.sf.model.*;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 29.09.16 11:16
 */
public class MappingRegistry {

    private final static Class[] CONTEXT = {
            Account.class,
            AnalogueLine.class,
            Attachment.class,
            Contact.class,
            ChargeType.class,
            EndCustomer.class,
            Exchange.class,
            FeedItem.class,
            RecordType.class,
            Site.class,
            User.class,
            Virtual1DataCenter.class
    };

    private static Map<Class<?>, SfObjectAccessor> accessors = new HashMap<>();
    private static Map<Class<?>, String> queries = new HashMap<>();


    public static SfObjectAccessor getAccessor(Class<?> type) {
        SfObjectAccessor objectAccessor = accessors.get(type);
        if (objectAccessor == null) {
            throw new SalesforceConfigurationException(type + " is not present in salesforce mapping registry context. Add that");
        }

        return objectAccessor;
    }

    public static String getBaseQuery(Class<?> type) {
        String query = queries.get(type);
        if (query == null) {
            throw new SalesforceConfigurationException(type + " is not present in salesforce mapping registry context. Add that");
        }
        return query;
    }


    public static void init() {
        for (Class<?> type : CONTEXT) {
            accessors.put(type, getModelAccessors(type));
        }

        for (Class<?> type : accessors.keySet()) {
            queries.put(type, buildBaseQuery(type));
        }
    }

    private static SfObjectAccessor getModelAccessors(Class<?> type) {
        SfObjectAccessor objectAccessor = new SfObjectAccessor();
        Map<Field, SfAccessor> map = objectAccessor.getAccessors();

        Class<?> currentType = type;
        while (true) {
            for (Field field : currentType.getDeclaredFields()) {
                if (field.isAnnotationPresent(SalesforceField.class)) {
                    map.put(field, new SfAccessor(field, field.getAnnotation(SalesforceField.class)));
                }
            }

            currentType = currentType.getSuperclass();
            if (currentType == Object.class) {
                break;
            }
        }
        return objectAccessor;
    }

    private static String buildBaseQuery(Class<?> type) {
        String table = type.getAnnotation(SalesforceObject.class).table();
        String fieldSequence = getFieldSequence(type);
        String staticClause = type.getAnnotation(SalesforceObject.class).staticClause();

        SfQueryBuilder builder = SfQueryBuilder.select(fieldSequence, table);

        if (StringUtils.isNotBlank(staticClause)) {
            builder.where(staticClause);
        }

        return builder.toString();
    }

    private static String getFieldSequence(Class<?> type) {
        StringBuilder result = new StringBuilder();

        String simpleFields = getFieldSequence(type, null);
        result.append(simpleFields);

        Map<Field, SfAccessor> accessors = getAccessorsByTypes(type, SalesforceField.RelationType.RELATION);
        for (Map.Entry<Field, SfAccessor> entry : accessors.entrySet()) {
            String sfRelationName = SfQueryBuilder.normalizeRelationField(entry.getValue().getSfField());
            String fieldSequence = getFieldSequence(entry.getKey().getType(), sfRelationName);
            result.append(", ").append(fieldSequence);
        }

        return result.toString();
    }

    private static String getFieldSequence(Class<?> type, String allias) {
        StringBuilder result = new StringBuilder();
        Map<Field, SfAccessor> accessors = getAccessorsByTypes(type, SalesforceField.RelationType.SIMPLE_FIELD, SalesforceField.RelationType.RELATION_ID);

        int position = 0;
        for (Map.Entry<Field, SfAccessor> accessorEntry : accessors.entrySet()) {
            if (StringUtils.isNotBlank(allias)) {
                result.append(allias).append('.');
            }
            result
                    .append(accessorEntry.getValue().getSfField())
                    .append(position++ == accessors.size() - 1 ? ' ' : ',');
        }
        return result.toString();
    }

    private static Map<Field, SfAccessor> getAccessorsByTypes(Class<?> objectType, SalesforceField.RelationType... relationTypes) {
        Map<Field, SfAccessor> result = new HashMap<>();
        for (Map.Entry<Field, SfAccessor> entry : getAccessor(objectType).getAccessors().entrySet()) {
            SalesforceField.RelationType relationType = entry.getKey().getAnnotation(SalesforceField.class).relationType();
            for (SalesforceField.RelationType t : relationTypes) {
                if (relationType == t) {
                    result.put(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
        return result;
    }

}
