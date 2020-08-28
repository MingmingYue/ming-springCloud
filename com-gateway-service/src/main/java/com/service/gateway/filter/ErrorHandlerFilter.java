//package com.service.gateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ERROR_TYPE;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;
//
///**
// * @author xiaoMing
// * Create on 2020-08-12.
// */
//@Slf4j
//@Component
//public class ErrorHandlerFilter extends ZuulFilter {
//
//    @Override
//    public String filterType() {
//        return ERROR_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return SEND_RESPONSE_FILTER_ORDER + 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        return requestContext.getThrowable() != null;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        log.error("{}",requestContext);
//        return null;
//    }
//}
