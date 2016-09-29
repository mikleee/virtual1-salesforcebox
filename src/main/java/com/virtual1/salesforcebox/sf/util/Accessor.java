package com.virtual1.salesforcebox.sf.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Mikhail Tkachenko created on 09.08.16 15:33
 */
public class Accessor {
    private Field field;
    private Method getter;
    private Method setter;

    Accessor(Field field) {
        this.field = field;
        String capitalised = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        try {
            getter = findMethod(field.getDeclaringClass(), "get" + capitalised);
        } catch (Exception e) {
            if (getType() == Boolean.class || getType() == boolean.class) {
                getter = findMethod(field.getDeclaringClass(), "is" + capitalised);
            } else {
                throw e;
            }
        }
        setter = findMethod(field.getDeclaringClass(), "set" + capitalised, field.getType());
    }

    public Object get(Object target) throws RuntimeException {
        try {
            return getter.invoke(target);
        } catch (Exception e) {
            throw new RuntimeException("Specify a valid getter for " + getName() + " in " + target.getClass(), e);
        }
    }

    public void set(Object target, Object value) throws RuntimeException {
        try {
            setter.invoke(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Specify a valid setter for " + getName() + " in " + target.getClass(), e);
        }
    }

    public String getName() {
        return field.getName();
    }

    public Class<?> getType() {
        return field.getType();
    }

    public boolean isApplicable(Class<?> type) {
        return field.getType() == type;
    }


    private Method findMethod(Class<?> type, String name, Class<?>... args) {
        Class currentType = type;
        while (currentType != null) {
            try {
                return currentType.getDeclaredMethod(name, args);
            } catch (NoSuchMethodException ignore) {
                currentType = currentType.getSuperclass();
            }
        }

        throw new SalesforceConfigurationException("Cant find method " + name + " in " + type);
    }

}