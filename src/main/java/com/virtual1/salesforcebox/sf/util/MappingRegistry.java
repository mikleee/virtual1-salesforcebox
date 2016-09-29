package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import com.virtual1.salesforcebox.sf.annotation.SalesforceRelation;
import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.Contact;
import com.virtual1.salesforcebox.sf.model.EndCustomer;
import com.virtual1.salesforcebox.sf.model.User;
import org.apache.commons.lang.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 29.09.16 11:16
 */
public class MappingRegistry {

    private final static List<Class<?>> CONTEXT = new ArrayList<Class<?>>() {{
        add(Account.class);
        add(Contact.class);
        add(EndCustomer.class);
        add(User.class);
    }};

    private static Map<Class<?>, Map<Field, SfAccessor>> accessors = new HashMap<>();
    private static Map<Class<?>, String> queries = new HashMap<>();


    public static Map<Field, SfAccessor> getAccessors(Class<?> type) {
        return accessors.get(type);
    }

    public static Map<Field, SfAccessor> getAccessors(Class<?> type, Class<? extends Annotation> annotation) {
        Map<Field, SfAccessor> result = new HashMap<>();
        for (Map.Entry<Field, SfAccessor> entry : getAccessors(type).entrySet()) {
            if (entry.getKey().isAnnotationPresent(annotation)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public static String getBaseQuery(Class<?> type) {
        return queries.get(type);
    }

    static {
        init();
    }

    private static void init() {
        for (Class<?> type : CONTEXT) {
            accessors.put(type, getModelAccessors(type));
        }

        for (Class<?> type : accessors.keySet()) {
            queries.put(type, buildBaseQuery(type));
        }
    }

    private static Map<Field, SfAccessor> getModelAccessors(Class<?> type) {
        Map<Field, SfAccessor> result = new HashMap<>();

        Class<?> currentType = type;
        while (true) {
            for (Field field : currentType.getDeclaredFields()) {
                if (field.isAnnotationPresent(SalesforceField.class)) {
                    result.put(field, new SfAccessor(field, field.getAnnotation(SalesforceField.class).name()));
                } else if (field.isAnnotationPresent(SalesforceRelation.class)) {
                    result.put(field, new SfAccessor(field, field.getAnnotation(SalesforceRelation.class).name()));
                }
            }

            currentType = currentType.getSuperclass();
            if (currentType == Object.class) {
                break;
            }
        }
        return result;
    }

    private static String buildBaseQuery(Class<?> type) {
        String table = type.getAnnotation(SalesforceObject.class).name();
        String fieldSequence = getFieldSequence(type);
        String staticClause = type.getAnnotation(SalesforceObject.class).staticClause();

        StringBuilder result = new StringBuilder()
                .append("SELECT ")
                .append(fieldSequence)
                .append(" FROM ")
                .append(table);

        if (StringUtils.isNotBlank(staticClause)) {
            result
                    .append(" WHERE ")
                    .append(staticClause)
                    .append(" ");
        }

        return result.toString();
    }

    private static String getFieldSequence(Class<?> type) {
        StringBuilder result = new StringBuilder();

        String simpleFields = getFieldSequence(type, null);
        result.append(simpleFields);

        Map<Field, SfAccessor> accessors = getAccessors(type, SalesforceRelation.class);
        for (Map.Entry<Field, SfAccessor> entry : accessors.entrySet()) {
            String fieldSequence = getFieldSequence(entry.getKey().getType(), entry.getValue().getSfField());
            result.append(" ,").append(fieldSequence);
        }

        return result.toString();
    }

    private static String getFieldSequence(Class<?> type, String allias) {
        StringBuilder result = new StringBuilder();
        Map<Field, SfAccessor> accessors = getAccessors(type, SalesforceField.class);
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

}
