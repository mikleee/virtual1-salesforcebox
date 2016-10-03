package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.model.BaseSalesforceObject;
import org.apache.commons.lang.StringUtils;

/**
 * @author Mikhail Tkachenko created on 29.09.16 12:50
 */
public class SfQueryBuilder {
    private StringBuilder query;

    private SfQueryBuilder(String query) {
        this.query = new StringBuilder(query);
    }

    public static SfQueryBuilder queryFor(Class<? extends BaseSalesforceObject> type) {
        String baseQuery = MappingRegistry.getBaseQuery(type);
        return new SfQueryBuilder(baseQuery);
    }

    public static SfQueryBuilder select(String fieldSequence, String fromTable) {
        return new SfQueryBuilder("SELECT " + fieldSequence + " FROM " + fromTable);
    }

    public static String normalizeRelationField(String fieldKey) {
        final String customRelationMarker = "__c";
        final String customRelationPath = "__r";

        if (fieldKey.endsWith(customRelationMarker)) {
            fieldKey = fieldKey.substring(0, fieldKey.length() - customRelationMarker.length()) + customRelationPath;
        }
        return fieldKey;
    }

    public SfQueryBuilder where(String where) {
        if (isWithoutCondition()) {
            query.append(" WHERE ").append(where);
            return this;
        } else {
            return and(where);
        }
    }

    public SfQueryBuilder where(String field, String value) {
        return where(String.format("%s='%s'", field, value));
    }

    public SfQueryBuilder and(String and) {
        if (isWithoutCondition()) {
            query.append(" WHERE ");
        }
        query.append(" AND ").append(and).append(" ");
        return this;
    }

    public SfQueryBuilder and(String field, String value) {
        return and(String.format("%s='%s'", field, value));
    }

    public SfQueryBuilder or(String or) {
        if (isWithoutCondition()) {
            query.append(" WHERE ");
        }
        query.append(" OR ").append(or).append(" ");
        return this;
    }

    public SfQueryBuilder or(String field, String value) {
        return or(String.format("%s='%s'", field, value));
    }

    public SfQueryBuilder andIncludes(String field, String... values) {
        if (isWithoutCondition()) {
            query.append(" WHERE ");
        }
        query.append(" AND ").append(field).append(" includes ").append("('").append(StringUtils.join(values, ";")).append("') ");
        return this;
    }

    public SfQueryBuilder orIncludes(String field, String... values) {
        if (isWithoutCondition()) {
            query.append(" WHERE ");
        }
        query.append(" OR ").append(field).append(" includes ").append("(").append(StringUtils.join(values, ",")).append(") ");
        return this;
    }

    public String byId(String id) {
        return byField("Id", id);
    }

    public String byField(String field, String value) {
        return where(field, value).toString();
    }

    private boolean isWithoutCondition() {
        return query.indexOf(" WHERE ") == -1;
    }

    @Override
    public String toString() {
        return query.toString();
    }
}
