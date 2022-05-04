package com.jd.edi.intercepter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.jd.edi.auth.AuthContext;
import com.jd.edi.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    private Set<String> excludePath = null;

    public void setExcludePath(String path) {
        if(!StringUtils.isEmpty(path)){
            this.excludePath = new HashSet<>(Arrays.asList(path.split("[;,]")));
            log.info("auth exclude path :{}", excludePath);
        }
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if (isExcludePath(path)) {
            return true;
        }
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已经登录，用户名: 【{}】", request.getSession().getAttribute("employee"));
            AuthContext.setCurrentUser((Long) request.getSession().getAttribute("employee"));
            return true;
        }
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NO LOGIN")));
        return false;
    }

    public boolean isExcludePath (String path) {
        Iterator<String> iterator = excludePath.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            /**
             * 注意问题： 匹配的时候 next有有空字符，导致匹配失败；
             */
            if (path.startsWith(next)) {
                return true;
            }
        }
        return false;
    }

}
