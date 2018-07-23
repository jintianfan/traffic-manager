package com.goldskyer.traffic.manager.common;

import java.util.function.Function;

/**
 * A wrapper for json response as per our web api spec at <a href="http://git.caimi-inc.com/caimi/arch/wikis/common/web-api-specification">关于Web API的规范意见</a>
 * <p>
 * Since there is no such an explicit json library like libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.4", a wrapper solution is preferred for spring mvc.
 * <p>
 * Created by yunshi on 8/10/15.
 */
public class WebApiResponse<T> {

    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 1;

    private int code;
    private String error;
    private T data;

    /**
     * 是否需要重试。只有在 code != 0，也就是说有错的时候才有意义。
     * 为真时表示一定要重试，为假时表示一定不要重试，为空时由调用方自行决定。
     */
    private Boolean isNeedRetry;


    public static <T> WebApiResponse<T> success(T data) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(SUCCESS_CODE);
        response.setData(data);
        return response;
    }

    public static <T> WebApiResponse<T> error(String errorMessage) {
        return WebApiResponse.error(errorMessage, ERROR_CODE);
    }

    public static <T> WebApiResponse<T> error(String errorMessage, int errorCode) {
        return WebApiResponse.error(errorMessage, errorCode, null);
    }

    public static <T> WebApiResponse<T> error(String errorMessage, Boolean isNeedRetry) {
        return WebApiResponse.error(errorMessage, ERROR_CODE, isNeedRetry);
    }

    public static <T> WebApiResponse<T> error(String errorMessage, int errorCode, Boolean isNeedRetry) {
        WebApiResponse<T> response = new WebApiResponse<>();
        response.setCode(errorCode);
        response.setError(errorMessage);
        response.setNeedRetry(isNeedRetry);
        return response;
    }

    public static <T> WebApiResponse<T> asProcess(Procedure<T> procedure) {
        return asProcess(procedure, Exception::toString);
    }

    public static <T> WebApiResponse<T> asProcess(Procedure<T> procedure, Function<Exception, String> exceptionHandler) {
        try {
            return success(procedure.apply());
        } catch (Exception e) {
            return error(exceptionHandler.apply(e));
        }
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getNeedRetry() {
        return isNeedRetry;
    }

    public void setNeedRetry(Boolean needRetry) {
        isNeedRetry = needRetry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebApiResponse<?> that = (WebApiResponse<?>) o;

        if (code != that.code) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        return data.equals(that.data);

    }

    @Override
    public int hashCode() {
        int result = code;
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WebApiResponse{" +
                "code=" + code +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }

    @FunctionalInterface
    public interface Procedure<T> {
        T apply() throws Exception;
    }
}
