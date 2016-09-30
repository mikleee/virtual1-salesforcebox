package com.virtual1.salesforcebox.sf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mikhail Tkachenko created on 29.09.16 12:17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SalesforceObject {
    String table();

    String type() default "";

    String staticClause() default "";

}
