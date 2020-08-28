//package com.service.gateway.handler;
//
//import com.core.commons.R;
//import com.core.commons.constants.CommonConstant;
//import com.core.commons.constants.MessageConstant;
//import com.core.exception.DeniedException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @author xiaoMing
// * Create on 2020-08-09.
// */
//@Slf4j
//@Component
//public class AccessDeniedHandler extends OAuth2AccessDeniedHandler {
//
//    @Resource
//    private ObjectMapper objectMapper;
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException, ServletException {
//        log.info("授权失败，禁止访问 {}", request.getRequestURI());
//        response.setCharacterEncoding(CommonConstant.UTF8);
//        response.setContentType(CommonConstant.CONTENT_TYPE);
//        R<String> result = new R<String>().failure(new DeniedException(MessageConstant.COMMONS_AUTH_NOTSUPPORT));
//        response.setStatus(HttpStatus.SC_FORBIDDEN);
//        PrintWriter printWriter = response.getWriter();
//        printWriter.append(objectMapper.writeValueAsString(result));
//    }
//}
