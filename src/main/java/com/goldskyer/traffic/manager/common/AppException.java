package com.goldskyer.traffic.manager.common;

public class AppException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException() {
        super();
    }

}
