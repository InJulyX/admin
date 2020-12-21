package com.admin.config.aspect;

import com.admin.config.AsyncFactory;
import com.admin.config.AsyncManager;
import com.admin.entity.database.OperaLog;
import com.admin.utils.IpUtils;
import com.admin.utils.ServletUtils;
import com.admin.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.admin.config.aspect.Log)")
    public void logPointCut() {
    }

    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }

            String username = (String) SecurityUtils.getSubject().getPrincipal();

            OperaLog operaLog = new OperaLog();
            operaLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            operaLog.setJsonResult(JSON.toJSONString(jsonResult));
            operaLog.setOperaIp(ip);
            operaLog.setOpenUrl(ServletUtils.getRequest().getRequestURI());
            operaLog.setOperaName(username);
            if (e != null) {
                operaLog.setStatus(BusinessStatus.FAIL.ordinal());
                operaLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operaLog.setMethod(className + "." + methodName + "()");
            operaLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            getControllerMethodDescription(joinPoint, controllerLog, operaLog);
            AsyncManager.me().execute(AsyncFactory.insertOperaLog(operaLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperaLog operaLog) throws Exception {
        operaLog.setBusinessType(log.businessType().ordinal());
        operaLog.setTitle(log.title());
        if (log.isSaveRequestData()) {
            setRequestValue(joinPoint, operaLog);
        }
    }

    private void setRequestValue(JoinPoint joinPoint, OperaLog operaLog) throws Exception {
        String requestMethod = operaLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operaLog.setOperaParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operaLog.setOperaParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (!isFilterObject(o)) {
                    Object jsonObj = JSON.toJSON(o);
                    params.append(jsonObj.toString()).append(" ");
                }
            }
        }
        return params.toString().trim();

    }

    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }

    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}
