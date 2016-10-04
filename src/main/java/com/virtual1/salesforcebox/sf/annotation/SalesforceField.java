package com.virtual1.salesforcebox.sf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * crated by m.tkachenko on 25.11.15 11:38
 * <p>
 * annotation maps the salesforce field to java class field
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SalesforceField {

    /**
     * name of the corresponding column
     */
    String name();

    RelationType relationType() default RelationType.SIMPLE_FIELD;

    AccessLevel accessLevel() default AccessLevel.FULL_ACCESS;


    enum RelationType {
        /**
         * simple field without any relations
         */
        SIMPLE_FIELD,
        /**
         * field is converted to corresponding java-salesforce object
         */
        RELATION,
        /**
         * string field which points to salesforce object id
         */
        RELATION_ID,

        COLLECTION
    }

    enum AccessLevel {
        /**
         * full read-write access
         */
        FULL_ACCESS,
        /**
         * field is allowed to be read only
         */
        READ_ONLY,
        /** field is allowed to be write during creation only it cant be updated */
        IMMUTABLE
    }

}