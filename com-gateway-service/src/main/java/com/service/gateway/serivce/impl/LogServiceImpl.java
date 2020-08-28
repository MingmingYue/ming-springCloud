//package com.service.gateway.serivce.impl;
//
//import com.core.commons.constants.CommonConstant;
//import com.core.commons.utils.StringHelper;
//import com.netflix.zuul.context.RequestContext;
//import com.service.common.bean.AuthLog;
//import com.service.common.bean.Log;
//import com.service.gateway.serivce.LogService;
//import com.xiaoleilu.hutool.http.HttpUtil;
//import com.xiaoleilu.hutool.io.IoUtil;
//import com.xiaoleilu.hutool.util.URLUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Date;
//
///**
// * 日志异步实现
// *
// * @author xiaoMing
// * Create on 2020-08-12.
// */
//@Slf4j
//@Service("logService")
//public class LogServiceImpl implements LogService {
//
//    @Override
//    public void send(RequestContext requestContext) {
//        HttpServletRequest request = requestContext.getRequest();
//        String requestUri = request.getRequestURI();
//        String method = request.getMethod();
//        Log syslog = new Log();
//        syslog.setType(CommonConstant.STATUS_NORMAL);
//        syslog.setRemoteAddr(HttpUtil.getClientIP(request));
//        syslog.setRequestUri(URLUtil.getPath(requestUri));
//        syslog.setMethod(method);
//        syslog.setUserAgent(request.getHeader("user-agent"));
//        syslog.setParams(HttpUtil.toParams(request.getParameterMap()));
//        Long startTime = (Long) requestContext.get("startTime");
//        syslog.setTime(System.currentTimeMillis() - startTime);
//        Date currentDate = new Date();
//        syslog.setCreateTime(currentDate);
//        syslog.setUpdateTime(currentDate);
//        if (requestContext.get(CommonConstant.SERVICE_ID) != null) {
//            syslog.setServiceId(requestContext.get(CommonConstant.SERVICE_ID).toString());
//        }
//
//        // 正常发送服务异常解析
//        if (requestContext.getResponseStatusCode() != HttpStatus.SC_OK
//                && null != requestContext.getResponseDataStream()) {
//            InputStream responseStream = requestContext.getResponseDataStream();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            InputStream inputStream = null;
//            InputStream responseDataStream = null;
//            byte[] buffer = IoUtil.readBytes(responseStream);
//            try {
//                baos.write(buffer);
//                baos.flush();
//                inputStream = new ByteArrayInputStream(baos.toByteArray());
//                responseDataStream = new ByteArrayInputStream(baos.toByteArray());
//                String response = IoUtil.read(inputStream, CommonConstant.UTF8);
//                syslog.setType(CommonConstant.STATUS_LOCK);
//                syslog.setException(response);
//                requestContext.setResponseDataStream(responseDataStream);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } finally {
//                IoUtil.close(responseDataStream);
//                IoUtil.close(baos);
//                IoUtil.close(responseStream);
//            }
//        }
//
//        // 网关内部异常
//        Throwable throwable = requestContext.getThrowable();
//        if (throwable != null) {
//            syslog.setException(throwable.getMessage());
//        }
//
//        AuthLog authLog = new AuthLog();
//        // 保存发往MQ（只保存授权）
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && StringHelper.isNotBlank(authentication.getName())) {
//            syslog.setCreateBy(authentication.getName());
//            authLog.setLog(syslog);
//            log.info(authLog.toString());
//        }
//    }
//}
