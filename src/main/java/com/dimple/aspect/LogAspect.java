package com.dimple.aspect;


import com.alibaba.fastjson.JSONObject;
import com.dimple.annotation.Log;
import com.dimple.entity.SysLog;
import com.dimple.exception.GlobalExceptionHandler;
import com.dimple.service.SysLogService;
import com.dimple.shiro.UserRealm;
import com.dimple.utils.WebUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Autowired
    private GlobalExceptionHandler exceptionHandle;

    @Autowired
    SysLogService sysLogService;

    private SysLog sysLog = null;

    @Pointcut("@annotation(com.dimple.annotation.Log)")
    public void webLog() {
        System.out.println("111111");
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = (HttpSession) attributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        sysLog = new SysLog();
        sysLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        sysLog.setHttpMethod(request.getMethod());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object o = args[i];
            if (o instanceof ServletRequest || (o instanceof ServletResponse) || o instanceof MultipartFile) {
                args[i] = o.toString();
            }
        }
        String str = JSONObject.toJSONString(args);
        sysLog.setData(str.length() > 5000 ? JSONObject.toJSONString("请求参数数据过长不与显示") : str);
        String ip = WebUtils.getClientIp(request);
        if ("0.0.0.0".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "localhost".equals(ip) || "127.0.0.1".equals(ip)) {
            ip = "127.0.0.1";
        }
        sysLog.setRemoteAddr(ip);
        sysLog.setRequestUri(request.getRequestURL().toString());
        if (session != null) {
            sysLog.setSessionId(session.getId());
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log mylog = method.getAnnotation(Log.class);
        if (mylog != null) {
            //注解上的描述
            sysLog.setTitle(mylog.value());
        }

        Map<String, String> browserMap = WebUtils.getOsAndBrowserInfo(request);

        sysLog.setBrowser(browserMap.get("os") + "-" + browserMap.get("browser"));

        if (!"127.0.0.1".equals(ip)) {
            Map<String, String> map = (Map<String, String>) session.getAttribute("addressIp");
            if (map == null) {
                map = WebUtils.getAddressByIP(WebUtils.getClientIp(request));
                session.setAttribute("addressIp", map);
            }
            sysLog.setArea(map.get("area"));
            sysLog.setProvince(map.get("province"));
            sysLog.setCity(map.get("city"));
            sysLog.setIsp(map.get("isp"));
        }
        sysLog.setType(WebUtils.isAjax(request) ? "Ajax请求" : "普通请求");
        UserRealm.ShiroUser principals = (UserRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (principals != null) {
            sysLog.setUsername(StringUtils.isNotBlank(principals.getNickName()) ? principals.getNickName() : principals.getLoginName());
        }
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            Object obj = proceedingJoinPoint.proceed();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            sysLog.setException(e.getMessage());
            throw e;
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        UserRealm.ShiroUser principals = (UserRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (principals != null) {
            sysLog.setUsername(StringUtils.isNotBlank(principals.getNickName()) ? principals.getNickName() : principals.getLoginName());
        }
        String retString = JSONObject.toJSONString(ret);
        sysLog.setResponse(retString.length() > 5000 ? JSONObject.toJSONString("请求参数数据过长不与显示") : retString);
        sysLog.setUseTime(System.currentTimeMillis() - startTime.get());
        sysLogService.insert(sysLog);
    }
}
