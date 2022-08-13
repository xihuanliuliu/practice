package com.jd.edi.intercepter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.edi.annotation.AutoIdempotent;
import com.jd.edi.auth.AuthContext;
import com.jd.edi.common.R;
import com.jd.edi.exception.RedisException;
import com.jd.edi.service.TokenService;
import com.jd.edi.service.impl.TokenServiceImpl;
import com.jd.edi.utils.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Slf4j
public class AutoIdempotentInterceptor implements HandlerInterceptor {

    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 注解
        AutoIdempotent autoIdempotent = method.getAnnotation(AutoIdempotent.class);
        try {
            if (autoIdempotent != null) {
                System.out.println("进入方法");
                tokenService = SpringBeanUtil.getBean("tokenService", TokenService.class);
                System.out.println("tokenBean: " + tokenService.toString());
                return tokenService.checkToken(request);
            }
        } catch (RedisException e) {
            writeReturnJson(response, JSONObject.toJSONString(new byte[0]));
        }
        return true;
    }

    /**
     * 返回的json值
     * @param response
     * @param json
     * @throws Exception
     */
    private void writeReturnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try{
            writer = response.getWriter();
            writer.print(json);
        } catch(IOException e) {
        } finally{
            if(writer != null)
                writer.close();
        }
    }


}
