package com.goldskyer.traffic.manager.common;

import org.apache.commons.lang3.StringUtils;

public class Assert {
    public static void notNull(Object o, Errors error) {
        if (o == null) {
            throw error.exception();
        }
    }

    public static void notNull(Object o, Errors error, String msg) {
        if (o == null) {
            throw error.exception(msg);
        }
    }

    public static void isNotBlank(String str, Errors error) {
        if (StringUtils.isBlank(str)) {
            throw error.exception();
        }
    }

    public static void isNotBlank(String str, Errors error, String msg) {
        if (StringUtils.isBlank(str)) {
            throw error.exception(msg);
        }
    }

    public static void isTrue(boolean o, Errors error) {
        if (!o) {
            throw error.exception();
        }
    }

    public static void isTrue(boolean o, Errors error, String msg) {
        if (!o) {
            throw error.exception(msg);
        }
    }
}
