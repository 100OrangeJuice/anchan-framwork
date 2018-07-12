package org.sakura.anchan.util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Anchan on 2018/5/13.
 */
@Slf4j
public class CookieUtil {
    private final static String COOKIE_DOMAIN = ".wolf-and-spice.xin";

    private final static String COOKIE_LOGIN = "mmall_login_token";

    /**
     * 在响应中写入cookie
     * @param response
     * @param token
     */
    public static void writeLoginToken(HttpServletResponse response, String token){
        Cookie cookie = new Cookie(COOKIE_LOGIN, token);
//        cookie.setDomain(COOKIE_DOMAIN);
//        cookie.setPath("/");

        /*
         * 单位是秒
         * 如果不设置的话，cookie不会写入硬盘，而是写入内存。只在当前页有效。
         * 如果是-1的话，代表永久
         */
        cookie.setMaxAge(60 * 60 * 24 * 365);
        log.info("write cookieName:{},cookieValue:{}", cookie.getName(), cookie.getValue());
        response.addCookie(cookie);
    }

    /**
     * 读取响应中的cookie
     * @param request
     * @return
     */
    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies){
                log.info("read cookieName:{},cookieValue{}", cookie.getName(), cookie.getValue());
                if (StringUtils.equals(cookie.getName(), COOKIE_LOGIN)){
                    log.info("return cookieName:{},cookieValue{}", cookie.getName(), cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 删除cookie的方法
     * @param request
     * @param response
     */
    public static void deleteLoginToken(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for ( Cookie cookie : cookies){
                if (StringUtils.equals(cookie.getName(), COOKIE_LOGIN)){
                    cookie.setDomain(COOKIE_DOMAIN);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    log.info("del cookieName:{},cookieValue{}", cookie.getName(), cookie.getValue());
                    response.addCookie(cookie);
                    //设置完cookie则直接return推出方法
                    return;
                }
            }
        }
    }


}
