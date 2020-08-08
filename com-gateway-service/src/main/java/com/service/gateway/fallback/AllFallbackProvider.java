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
import java.nio.charset.Charset;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@Slf4j
@Component
public class AllFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return ServiceIdConstant.ALL_SERVICE;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return this.response(HttpStatus.SERVICE_UNAVAILABLE, route, cause);
    }

    private ClientHttpResponse response(final HttpStatus status, String route, Throwable cause) {
        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() {
                return status;
            }

            @Override
            public String getStatusText() {
                return status.getReasonPhrase();
            }

            @Override
            public int getRawStatusCode() {
                return status.value();
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
                    log.error("调用:{} 异常：{}", route, MessageConstant.COMMONS_AUTH_NOTSUPPORT);
                    return new ByteArrayInputStream(MessageConstant.COMMONS_AUTH_NOTSUPPORT.getBytes());
                }
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}