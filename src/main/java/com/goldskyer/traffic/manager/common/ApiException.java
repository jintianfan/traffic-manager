package com.goldskyer.traffic.manager.common;

public class ApiException extends AppException {

    private static final long serialVersionUID = 1L;

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException() {
        super();
    }

    /* avoid the expensive and useless stack trace for api exceptions */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
