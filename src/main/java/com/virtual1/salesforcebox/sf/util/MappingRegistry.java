package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.annotation.SalesforceField;
import com.virtual1.salesforcebox.sf.annotation.SalesforceObject;
import com.virtual1.salesforcebox.sf.model.Account;
import com.virtual1.salesforcebox.sf.model.BaseSalesforceObject;
import com.virtual1.salesforcebox.sf.model.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Tkachenko created on 29.09.16 11:16
 */
public class MappingRegistry {
    private final static List<Class<? extends BaseSalesforceObject>> CONTEXT = new ArrayList<Class<? extends BaseSalesforceObject>>() {{
        add(Account.class);
        add(User.class);
    }};

    private static Map<Class<? extends BaseSalesforceObject>, Map<Field, SfAccessor>> accessors = new HashMap<>();
    private static Map<Class<? extends BaseSalesforceObject>, String> queries = new HashMap<>();


    public static Map<Field, SfAccessor> getAccessors(Class<? extends BaseSalesforceObject> type) {
        return accessors.get(type);
    }

    public static String getBaseQuery(Class<? extends BaseSalesforceObject> type) {
        return queries.get(type);
    }

    static {
        init();
    }

    private static void init() {
        for (Class<? extends BaseSalesforceObject> type : CONTEXT) {
            accessors.put(type, getModelAccessors(type));
            queries.put(type, buildbaseQuery(type));
        }
    }

    private static Map<Field, SfAccessor> getModelAccessors(Class<? extends BaseSalesforceObject> type) {
        Map<Field, SfAccessor> result = new HashMap<>();

        Class<?> currentType = type;
        while (true) {
            for (Field field : currentType.getDeclaredFields()) {
                if (field.isAnnotationPresent(SalesforceField.class)) {
                    result.put(field, new SfAccessor(field, field.getAnnotation(SalesforceField.class)));
                }
            }
            if (currentType == BaseSalesforceObject.class) {
                break;
            } else {
                currentType = currentType.getSuperclass();
            }
        }
        return result;
    }

    private static String buildbaseQuery(Class<? extends BaseSalesforceObject> type) {
        String table = type.getAnnotation(SalesforceObject.class).name();
        String fieldSequence = getFieldSequence(type);

        return String.format("SELECT %s FROM %s ", fieldSequence, table);
    }

    private static String getFieldSequence(Class<? extends BaseSalesforceObject> type) {
        StringBuilder result = new StringBuilder();
        Map<Field, SfAccessor> accessors = getAccessors(type);
        int position = 0;
        for (Map.Entry<Field, SfAccessor> accessorEntry : accessors.entrySet()) {
            result
                    .append(accessorEntry.getValue().getSfField())
                    .append(position++ == accessors.size() - 1 ? ' ' : ',');
        }
        return result.toString();
    }

}
