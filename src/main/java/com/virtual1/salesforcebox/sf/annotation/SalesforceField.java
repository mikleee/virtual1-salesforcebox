package com.virtual1.salesforcebox.sf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * crated by m.tkachenko on 25.11.15 11:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SalesforceField {
    String name();

    boolean immutable() default false;
}