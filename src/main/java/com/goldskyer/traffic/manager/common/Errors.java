package com.goldskyer.traffic.manager.common;

import com.goldskyer.traffic.manager.common.exception.IllegalParameterException;
import com.goldskyer.traffic.manager.common.exception.LoginErrorException;
import com.goldskyer.traffic.manager.common.exception.UnknownServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title TDOD
 * @Description: TODO
 * @Author yuren
 * @Date 2018/7/21 上午9:47
 * @Version V2.0.0
 */
public enum Errors {
    UNKNOWN_SERVER_ERROR(LogType.ERROR, -1, "The server experienced an unexpected error when processing the request",
            new ApiExceptionBuilder() {
                @Override
                public ApiException build(String message) {
                    return new UnknownServerException(message);
                }
            }),
    LOGIN_ERROR(LogType.WARN, 100, "登录异常", new ApiExceptionBuilder() {
        @Override
        public ApiException build(String message) {
            return new LoginErrorException(message);
        }
    }),
    ILLEGAL_PARAMETER(LogType.WARN, 1, "请求参数异常", new ApiExceptionBuilder() {
        @Override
        public ApiException build(String message) {
            return new IllegalParameterException(message);
        }
    })
    ;

    private static final Logger log = LoggerFactory.getLogger(Errors.class);

    private static Map<Class<?>, Errors> classToError = new HashMap<>();
    private static Map<Integer, Errors> codeToError = new HashMap<>();

    static {
        for (Errors error : Errors.values()) {
            codeToError.put(error.code(), error);
            if (error.exception != null)
                classToError.put(error.exception.getClass(), error);
        }
    }

    private final int code;
    private final LogType logType;
    private final ApiExceptionBuilder builder;
    private final ApiException exception;
    Errors(LogType logType, int code, String defaultExceptionString, ApiExceptionBuilder builder) {
        this.logType = logType;
        this.code = (short) code;
        this.builder = builder;
        this.exception = builder.build(defaultExceptionString);
    }

    /**
     * Throw the exception if there is one
     */
    public static Errors forCode(int code) {
        Errors error = codeToError.get(code);
        if (error != null) {
            return error;
        } else {
            log.warn("Unexpected error code: {}.", code);
            return UNKNOWN_SERVER_ERROR;
        }
    }

    /**
     * Return the error instance associated with this exception or any of its superclasses (or UNKNOWN if there is none).
     * If there are multiple matches in the class hierarchy, the first match starting from the bottom is used.
     */
    public static Errors forException(Throwable t) {
        Class<?> clazz = t.getClass();
        while (clazz != null) {
            Errors error = classToError.get(clazz);
            if (error != null)
                return error;
            clazz = clazz.getSuperclass();
        }
        return UNKNOWN_SERVER_ERROR;
    }

    /**
     * An instance of the exception
     */
    public ApiException exception() {
        return this.exception;
    }

    /**
     * Create an instance of the ApiException that contains the given error message.
     *
     * @param message The message string to set.
     * @return The exception.
     */
    public ApiException exception(String message) {
        if (message == null) {
            // If no error message was specified, return an exception with the default error message.
            return exception;
        }
        // Return an exception with the given error message.
        return builder.build(message);
    }

    /**
     * Returns the class name of the exception or null if this is {@code Errors.NONE}.
     */
    public String exceptionName() {
        return exception == null ? null : exception.getClass().getName();
    }

    /**
     * The error code for the exception
     */
    public int code() {
        return this.code;
    }

    public LogType logType() {
        return logType;
    }

    /**
     * Throw the exception corresponding to this error if there is one
     */
    public void maybeThrow() {
        if (exception != null) {
            throw this.exception;
        }
    }

    /**
     * Get a friendly description of the error (if one is available).
     *
     * @return the error message
     */
    public String message() {
        if (exception != null)
            return exception.getMessage();
        return toString();
    }

    private interface ApiExceptionBuilder {
        ApiException build(String message);
    }
}
