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

    public SfQueryBuilder where(String where, String... args) {
        if (isWithoutCondition()) {
            query.append(" WHERE ").append(String.format(where, args));
        } else {
            throw new SalesforceException("Query is invalid: " + query.append(" WHERE ").append(String.format(where, args)));
        }
        return this;
    }

    public SfQueryBuilder and(String and, String... args) {
        if (isWithoutCondition()) {
            query.append(" WHERE ");
        }
        query.append(" AND ").append(String.format(and, args));
        return this;
    }

    public SfQueryBuilder or(String or, String... args) {
        if (isWithoutCondition()) {
            query.append(" WHERE ");
        }
        query.append(" OR ").append(String.format(or, args));
        return this;
    }

    private boolean isWithoutCondition() {
        return query.indexOf(" WHERE ") == -1;
    }

    @Override
    public String toString() {
        return query.toString();
    }
}
