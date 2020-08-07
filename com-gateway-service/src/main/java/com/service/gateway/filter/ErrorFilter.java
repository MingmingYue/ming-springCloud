package com.service.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

/**
 * @author xiaoMing
 * Create on 2020-08-07.
 */
@Slf4j
@Component
public class ErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext requestContext = RequestContext.getCurrentContext();
            Object e = requestContext.get("throwable");

            if (e != null && e instanceof ZuulException) {
                System.out.println("=======================");
            }
        } catch (Exception ex) {
            log.error("Exception filtering in custom error filter（网关调用出错）：", ex);
        }

        return null;
    }
}
