package com.goldskyer.traffic.manager.handlers;

import com.goldskyer.traffic.manager.common.Errors;
import com.goldskyer.traffic.manager.common.WebApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = {RestController.class})
public class ApiExceptionHandler {
    /**
     * 默认的错误信息。
     */
    private static final String DEFAULT_ERROR_MSG = "服务异常, 请稍后重试";

    protected final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<WebApiResponse<String>> globalHandler(HttpServletRequest request, Throwable e) {
        Errors error = Errors.forException(e);
        switch (error.logType()) {
        case DEBUG:
            LOG.debug("errorCode:" + error.name() + ",errorMsg:" + e.getMessage());
            break;
        case INFO:
            LOG.info("errorCode:" + error.name() + ",errorMsg:" + e.getMessage());
            break;
        case WARN:
            LOG.warn("errorCode:" + error.name() + ",errorMsg:" + e.getMessage(), e);
            break;
        case ERROR:
            LOG.error("errorCode:" + error.name() + ",errorMsg:" + e.getMessage(), e);
            break;
        default:
            LOG.error("服务端未知异常" + e.getMessage(), e);
        }
        WebApiResponse<String> errorResp = WebApiResponse
                .error("[" + error.name() + "]" + e.getMessage(), error.code());
        return new ResponseEntity<>(errorResp, getStatus(request));
    }

    /**
     * copied from BasicErrorController
     */
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ignored) {
            }
        }
        return HttpStatus.OK;
    }
}