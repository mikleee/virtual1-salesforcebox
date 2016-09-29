package com.virtual1.salesforcebox.sf;

/**
 * @author Mikhail Tkachenko created on 23.08.16 14:22
 */
public class SalesforceException extends RuntimeException {
    public SalesforceException() {
    }

    public SalesforceException(Throwable cause) {
        super(cause);
    }

    public SalesforceException(String message) {
        super(message);
    }

    public SalesforceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SalesforceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
