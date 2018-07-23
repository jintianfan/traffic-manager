package com.goldskyer.traffic.manager.common.exception;

import com.goldskyer.traffic.manager.common.ApiException;

public class IllegalParameterException extends ApiException {

    private static final long serialVersionUID = 1L;

    public IllegalParameterException() {
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(Throwable cause) {
        super(cause);
    }

    public IllegalParameterException(String message, Throwable cause) {
        super(message, cause);
    }

}