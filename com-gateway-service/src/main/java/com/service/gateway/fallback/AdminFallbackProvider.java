package com.service.gateway.fallback;

import com.core.commons.constants.MessageConstant;
import com.core.commons.constants.ServiceIdConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 模块异常回调
 *
 * @author xiaoMing
 * Create on 2020-08-02.
 */
@Slf4j
@Component
public class AdminFallbackProvider implements FallbackProvider {

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR, route, cause);
    }

    private ClientHttpResponse response(final HttpStatus status, String route, Throwable cause) {

        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() {
                return status;
            }

            @Override
            public int getRawStatusCode() {
                return status.value();
            }

            @Override
            public String getStatusText() {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() {
                if (cause != null && cause.getMessage() != null) {
                    log.error("调用:{} 异常：{}", route, cause.getMessage());
                    return new ByteArrayInputStream(cause.getMessage().getBytes());
                } else {
                    log.error("调用:{} 异常：{}", route, MessageConstant.BUSINESS_ADMIN_NOTSUPPORT);
                    return new ByteArrayInputStream(MessageConstant.BUSINESS_ADMIN_NOTSUPPORT.getBytes());
                }
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }

    @Override
    public String getRoute() {
        return ServiceIdConstant.ADMIN_SERVICE;
    }
}
