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
@Deprecated
public @interface SalesforceParentId {
    String name() default "";

    boolean readOnly() default false;

    boolean immutable() default false;
}