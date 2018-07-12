package org.sakura.anchan.controller.common;

import org.sakura.anchan.POJO.User;
import org.sakura.anchan.common.Const;
import org.sakura.anchan.util.CookieUtil;
import org.sakura.anchan.util.JsonUtil;
import org.sakura.anchan.util.RedisPoolUtil;
import org.codehaus.jackson.type.TypeReference;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Anchan on 2018/5/20.
 */
public class SessionExpireFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (loginToken != null) {
            String userJsonStr = RedisPoolUtil.get(loginToken);
            User user = JsonUtil.string2Obj(userJsonStr, new TypeReference<User>() {});
            if (user != null) {
                RedisPoolUtil.expire(loginToken, Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
            }
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
