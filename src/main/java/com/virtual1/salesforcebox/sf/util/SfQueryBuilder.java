package com.virtual1.salesforcebox.sf.util;

import com.virtual1.salesforcebox.sf.SalesforceException;
import com.virtual1.salesforcebox.sf.model.BaseSalesforceObject;

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

    public SfQueryBuilder where(String where) {
        if (isWithoutCondition()) {
            query.append(" WHERE ").append(where);
        } else {
            throw new SalesforceException("Query is invalid: " + query.append(" WHERE ").append(where));
        }
        return this;
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
