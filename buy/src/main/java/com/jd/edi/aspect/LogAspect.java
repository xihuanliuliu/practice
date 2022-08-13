package com.jd.edi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Aspect
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    private Long endTime;

    private Long startTime;


    // 类.方法(参数)
    @Pointcut("execution(* com.jd.edi.controller.*.*(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String remoteAddr = request.getRemoteAddr();
        System.out.println("remoteAddr: " + remoteAddr);
        String remoteHost = request.getRemoteHost();
        System.out.println("remoteHost: " + remoteHost);

        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("format: " + format);

        System.out.println("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);

    }


    /**
     * 返回通知：
     * 1. 在目标方法正常结束之后执行
     * 1. 在返回通知中补充请求日志信息，如返回时间，方法耗时，返回值，并且保存日志信息
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        endTime = System.currentTimeMillis();
        log.info("请求结束时间：{}" + LocalDateTime.now());
        log.info("请求耗时：{}" + (endTime - startTime));
        // 处理完请求，返回内容
        log.info("请求返回 : {}" + ret);
    }

    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 1. 在异常通知中设置异常信息，并将其保存
     *
     * @param throwable
     */
    @AfterThrowing(value = "pointcut()", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.error("发生异常时间：{}" + LocalDateTime.now());
        log.error("抛出异常：{}" + throwable.getMessage());
    }
}
