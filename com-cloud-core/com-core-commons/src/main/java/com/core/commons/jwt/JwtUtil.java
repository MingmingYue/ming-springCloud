package com.core.commons.jwt;


import com.alibaba.ttl.TransmittableThreadLocal;
import com.core.commons.constants.CommonConstant;
import com.core.commons.constants.SecurityConstant;
import com.core.commons.utils.StringHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author xiaoMing
 * Create on 2020-08-12.
 */
@Slf4j
public class JwtUtil {

    private static final ThreadLocal<String> THREAD_LOCAL_USER = new TransmittableThreadLocal<>();

    public static String getUserName(String token, String jwtkey) {
        Claims claims = buildClaims(buildToken(token), jwtkey);
        if (null == claims) return "";

        return claims.get(SecurityConstant.JWT_USER_NAME).toString();
    }

    /**
     * 根据用户请求中的token 获取用户名
     *
     * @param request Request
     * @return ""、username
     */
    public static String getUserName(HttpServletRequest request, String jwtkey) {
        Claims claims = buildClaims(getToken(request), jwtkey);
        if (null == claims) return "";

        return claims.get(SecurityConstant.JWT_USER_NAME).toString();
    }

    /**
     * 根据请求heard中的token获取用户角色
     *
     * @param httpServletRequest request
     * @return 角色名
     */
    public static List<String> getRole(HttpServletRequest httpServletRequest, String jwtkey) {
        return getRole(getToken(httpServletRequest), jwtkey);
    }

    /**
     * 根据请求heard中的token获取用户角色
     * * @return 角色名
     */
    public static List<String> getRole(String token, String jwtkey) {
        Claims claims = buildClaims(buildToken(token), jwtkey);
        if (null == claims) return new ArrayList<>();

        @SuppressWarnings("unchecked")
        List<String> roleCodes = (List<String>) claims.get(SecurityConstant.JWT_USER_AUTHORITIES);
        return roleCodes;
    }

    /**
     * 获取请求中token
     */
    public static String getToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(CommonConstant.REQ_HEADER);
        return buildToken(authorization);
    }

    /**
     * jwt 解密
     */
    private static Claims buildClaims(String token, String jwtkey) {
        if (StringHelper.isBlank(token) || StringHelper.isBlank(jwtkey)) return null;

        String key = "";
        try {
            key = Base64.getEncoder().encodeToString(jwtkey.getBytes());
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception ex) {
            log.error("用户TOKEN解析异常,token:{},key:{}", token, key);
        }
        return null;
    }

    private static String buildToken(String token) {
        if (StringHelper.isBlank(token)) return null;
        if (!token.contains(CommonConstant.TOKEN_SPLIT)) return token;
        return StringHelper.substringAfter(token, CommonConstant.TOKEN_SPLIT);
    }

    /**
     * 设置用户信息
     *
     * @param username 用户名
     */
    public static void setUser(String username) {
        THREAD_LOCAL_USER.set(username);
        MDC.put(CommonConstant.KEY_USER, username);
    }

    /**
     * 从threadlocal 获取用户名
     *
     * @return 用户名
     */
    public static String getUser() {
        return THREAD_LOCAL_USER.get();
    }

    /**
     * 如果没有登录，返回null
     *
     * @return 用户名
     */
    public static String getUserName() {
        return THREAD_LOCAL_USER.get();
    }

    /**
     * 清除所有
     */
    public static void clearAll() {
        THREAD_LOCAL_USER.remove();
        MDC.remove(CommonConstant.KEY_USER);
    }
}
