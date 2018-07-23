package com.goldskyer.traffic.manager.common.exception;

import com.goldskyer.traffic.manager.common.ApiException;

public class LoginErrorException extends ApiException {

    private static final long serialVersionUID = 1L;

    public LoginErrorException() {
    }

    public LoginErrorException(String message) {
        super(message);
    }

    public LoginErrorException(Throwable cause) {
        super(cause);
    }

    public LoginErrorException(String message, Throwable cause) {
        super(message, cause);
    }

}