package com.jtl.ssm.controller;

import com.jtl.ssm.domain.SysLog;
import com.jtl.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author JT.L
 * @date 2020/1/6 12:54:54
 * @description
 */
@Component //当前不是一个Controller它只是一个bean
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    /**
     * 开始时间
     */
    private Date visitTime;
    /**
     * 访问的类
     */
    private Class clazz;
    /**
     * 访问的方法
     */
    private Method method;

    /**
     * 前置通知
     * 主要是获取开始时间，执行的是哪一个类，执行的是哪一个方法
     *
     * @param jp
     */
    @Before("execution(* com.jtl.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        // 当前时间就是开始访问的时间
        visitTime = new Date();
        // 具体要访问的类
        clazz = jp.getTarget().getClass();
        // 获取访问的方法的名称
        String methodName = jp.getSignature().getName();
        // 获取访问的方法的参数
        Object[] args = jp.getArgs();

        // 获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < classArgs.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知
     *
     * @param jp
     */
    @After("execution(* com.jtl.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        // 获取访问时长
        /*long time1 = new Date().getTime() - visitTime.getTime();*/
        long time = System.currentTimeMillis() - visitTime.getTime();

        String url = "";
        // 获取URL（使用了注解，通过反射对注解进行操作）
        // 让clazz != SysLogController.class 屏蔽查询日志自身这个操作
        if (clazz != null && method != null && clazz != LogAop.class && clazz != SysLogController.class) {
            // 1.获取类上的@RequestMapping("/orders")
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] clazzValue = clazzAnnotation.value();
                // 2.获取方法上的@RequestMapping("findAll.do")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methdoValue = methodAnnotation.value();
                    url = clazzValue[0] + methdoValue[0];
                }
            }
            // 获取访问的ip
            String ip = request.getRemoteAddr();
            /*// 这样获取到的是/bsmg_web/orders/findAll.do
            ip = request.getRequestURI();*/

            // 获取当前操作的用户
            // 从上下文中获取当前登录的用户
            SecurityContext context = SecurityContextHolder.getContext();
            User user = (User) context.getAuthentication().getPrincipal();
            String username = user.getUsername();

            /*User user = (User) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
            String username = user.getUsername();*/

            // 将日志相关信息封装到SysLog对象
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(time);
            sysLog.setIp(ip);
            sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
            sysLog.setUrl(url);
            sysLog.setUsername(username);
            sysLog.setVisitTime(visitTime);

            // 调用Service完成操作
            sysLogService.save(sysLog);
        }
    }
}
